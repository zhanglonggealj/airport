package com.esop.airport.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.common.MyTransactionExecption;
import com.esop.airport.common.SMSUtil;
import com.esop.airport.domain.model.*;
import com.esop.airport.domain.service.*;
import com.esop.airport.schedule.facade.ThreeDataFacade;
import com.esop.airport.task.OrderTask;
import com.esop.airport.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 学习中心定时器
 *
 * @author wxb
 * @create 2018-09-21 8:21
 **/
@Component
@Configurable
@EnableScheduling
public class AirportSchedule {

    Logger logger = LoggerFactory.getLogger(AirportSchedule.class);


    @Autowired
    DayResidualMoneyService dayResidualMoneyService;

    @Autowired
    MsgPushService msgPushService;

    @Autowired
    ConfigService configService;

    @Autowired
    ConsService consService;

    @Autowired
    SMSUtil smsUtil;

    @PreDestroy
    public void handlePre() {

    }

    @Autowired
    OrderTask orderTask;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Scheduled(cron = "0 0/2 * * * ?") //每五分钟 检查订单是否有下单失败情况
    public void sendElectricCheck() {

        try {
            List<TPurchaseOrder> buyOrders = purchaseOrderService.findBuyOrders();

            if (buyOrders != null && buyOrders.size() > 0) {
                for (TPurchaseOrder order : buyOrders) {
                    if (order.getNotifyTime().getTime() / 1000 > 60 * 2) {
                        orderTask.sendOrder(order);
                    } else {
                        logger.info("sendElectricCheck >>> 下电时间小于 2分钟暂时不进行处理");
                    }
                }
            }

        } catch (Exception e) {
            logger.error(" 定时任务处理失败 >>>> sendElectricCheck >>>> " + e.getMessage());
        }

    }

    @Scheduled(cron = "0 30 5 * * ?")  //每天早上5点30分 进行余额预警处理
    public void handleResidualMoney() {

        try {
            List<TDayResidualMoney> listByNowDateList = dayResidualMoneyService.findListByNowDate();

            if (listByNowDateList == null || listByNowDateList.isEmpty()) {
                logger.error(" 定时任务处理 >>>> handleResidualMoney >>>> " + listByNowDateList.isEmpty());
                return;
            }

            TConfig config = configService.findConfigById(1L);

            JSONObject confJson = JSON.parseObject(config.getConfigData());

            BigDecimal l1 = confJson.getBigDecimal("1");
            BigDecimal l2 = confJson.getBigDecimal("2");
            BigDecimal l3 = confJson.getBigDecimal("3");

            TConfig msgTextConfig = configService.findConfigById(2L);

            String msgText = msgTextConfig.getConfigData();

            for (TDayResidualMoney money : listByNowDateList) {

                int flag = -1;

                if (money.getRemainMoney().compareTo(l3) < 0) {
                    flag = 3;
                }

                if (money.getRemainMoney().compareTo(l2) < 0) {
                    flag = 2;
                }

                if (money.getRemainMoney().compareTo(l1) < 0) {
                    flag = 1;
                }

                if (flag == -1) {
                    continue;
                }
                //TODO 充值后要把 msg.getEventType() 改为-1
                TBasMsgPush msg = msgPushService.findLastMsgByCons(money.getConsNo());

                if (msg != null) {
                    if (msg.getEventType() == flag) {
                        continue;
                    }
                }

                TBasCons cons = consService.findConsByconsNo(money.getConsNo());


                TBasMsgPush newMsg = new TBasMsgPush();
                newMsg.setConsNo(money.getConsNo());
                newMsg.setEventType(flag);
                newMsg.setMobilePhone(cons.getMobilePhone());
                newMsg.setPushType(1L);
                newMsg.setPushText(msgText);

                msgPushService.SaveMsgPush(newMsg);
            }

        } catch (Exception e) {
            logger.error(" 定时任务处理失败 >>>> handleResidualMoney >>>> " + e.getMessage());
        }


    }

    @Scheduled(cron = "0 30 8-20 * * ?") //每天早上8点30分 - 20点30分轮训处理需要发的消息
    public void handlePushMsg() {
        try {

            List<TBasMsgPush> msgList = msgPushService.findListByStatus(1, 1L);

            for (TBasMsgPush msgPush : msgList) {

                int twoday = TimeUtils.getTwoDay(TimeUtils.getStringDate(), TimeUtils.dateToStrLong(msgPush.getCreateTime()));

                //toodo 超期3天的短信 将不再发送
                if (twoday > 3) {
                    msgPushService.updateMsgPushStatus(msgPush.getId(), -1L);
                    continue;
                }

//                smsUtil.sendEnentSMS(msgPush.getMobilePhone(), msgPush.getPushText());
                msgPushService.updateMsgPushStatus(msgPush.getId(), 2L);
            }

        } catch (Exception e) {
            logger.error(" 定时任务处理失败 >>>> handlePushMsg >>>> " + e.getMessage());
        }

    }

    @Scheduled(cron = "0 0 10 * * ?")  //每天早上10点  获取微信对账单
    public void downloadbill() {

        try {


        } catch (Exception e) {
            logger.error(" 定时任务处理失败 >>>> downloadbill >>>> " + e.getMessage());
        }
    }

    @Autowired
    TextPushService textPushService;
    @Autowired
    ThreeDataFacade threeDataFacade;

    //TODO 暂未开启推送
//    @Scheduled(cron = "0 * * * * ?") //每分钟 检查是否有待推送消息需要进行推送
    public void sendThreeData() {

        try {
            List<TBasTextPush> list = textPushService.findTextPushListByStatus(0);

            if (list != null && list.size() > 0) {

                for (TBasTextPush textPush : list) {
//                    try {
                        threeDataFacade.sendData(textPush.getId(), textPush.getType(), textPush.getData());
//                    } catch (MyTransactionExecption myTransactionExecption) {
//                        logger.error("第三方数据单条发送失败 " + myTransactionExecption.getMessage());
//                    }
                }

            }

        } catch (Exception e) {
            logger.error(" 定时任务处理失败 >>>> sendThreeData >>>> " + e.getMessage());
        }

    }
}
