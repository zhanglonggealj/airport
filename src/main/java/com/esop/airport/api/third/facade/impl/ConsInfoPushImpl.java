package com.esop.airport.api.third.facade.impl;

import com.alibaba.fastjson.JSONObject;
import com.esop.airport.api.third.controller.ConsPushController;
import com.esop.airport.api.third.facade.ConsInfoPushFacade;
import com.esop.airport.api.third.vo.BuyNotifiy;
import com.esop.airport.api.third.vo.AddCons;
import com.esop.airport.api.third.vo.ChangeCons;
import com.esop.airport.common.ConstDef;
import com.esop.airport.common.MyRequestExecption;
import com.esop.airport.common.MyTransactionExecption;
import com.esop.airport.domain.middle.smodel.STDayBill;
import com.esop.airport.domain.middle.smodel.TDayElectricityFreeze;
import com.esop.airport.domain.middle.smodel.TDayMoneyResidue;
import com.esop.airport.domain.middle.smodel.TDayTransFlag;
import com.esop.airport.domain.middle.sservice.ElectricityFreezeService;
import com.esop.airport.domain.middle.sservice.MoneyResidueService;
import com.esop.airport.domain.middle.sservice.SBillService;
import com.esop.airport.domain.middle.sservice.TransFlagService;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasMeter;
import com.esop.airport.domain.model.TDayFreeze;
import com.esop.airport.domain.model.TDayResidualMoney;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.domain.service.DayResidualMoneyService;
import com.esop.airport.domain.service.FreezeService;
import com.esop.airport.domain.service.MeterService;
import com.esop.airport.utils.StringUtils;
import com.esop.airport.domain.model.*;
import com.esop.airport.domain.service.*;
import com.esop.airport.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-14 15:31
 **/
@Service
public class ConsInfoPushImpl implements ConsInfoPushFacade {

    private final Logger logger = LoggerFactory.getLogger(ConsInfoPushImpl.class);


    @Autowired
    ConsService consService;

    @Autowired
    MeterService meterService;

    @Autowired
    DayResidualMoneyService dayResidualMoneyService;

    @Autowired
    TextPushService textPushService;

    @Transactional(value = "airportTransactionManager")
    @Override
    public int addConsAndMeter(TBasCons cons, TBasMeter meter) throws RuntimeException {

        TBasCons oldCons = consService.findConsByconsNo(cons.getConsNo());

        if (oldCons != null) {
            return 1;
        }

        int ret = consService.saveCons(cons);

        if (ret <= 0) {
            throw new MyTransactionExecption("新增商户错误");
        }

        meter.setConsNo(cons.getConsNo());

        //初始化预支金额和补加扣减  如果有值 赋值为1
        if (meter.getInitMoney() == null || meter.getInitMoney() == 0) {
            meter.setUsePrepayFlag(1);
        } else {
            meter.setUsePrepayFlag(0); //0表示需要做处理
        }

        meter.setUseAdditionFlag(1);
        meter.setUseCarryFlag(1);

        ret = meterService.saveMeter(meter);

        if (ret <= 0) {
            throw new MyTransactionExecption("新增电表错误");
        }

        //TODO 向第三方平台发送数据

        JSONObject consJson = new JSONObject();

        consJson.put("consNo", cons.getConsNo());
        consJson.put("consName", cons.getConsName());
        consJson.put("consAddress", cons.getConsAddress());
        consJson.put("consType", cons.getConsType());
        consJson.put("tariffName", cons.getTariffId());
        consJson.put("orgNo", cons.getOrgNo());
        consJson.put("buildDate", cons.getBuildDate());
        consJson.put("meterId", meter.getMeterId());
        consJson.put("statusCode", cons.getStatusCode());


        TBasTextPush textConsPush = new TBasTextPush();
        textConsPush.setType(3);
        textConsPush.setStatus(0);
        textConsPush.setData(consJson.toJSONString());

        ret = textPushService.saveTextPush(textConsPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textConsPush");
        }


        JSONObject meterJson = new JSONObject();

        meterJson.put("meterId", meter.getMeterId());
        meterJson.put("manufacturer", meter.getManufacturer());
        meterJson.put("commMode", meter.getCommMode());
        meterJson.put("protocolCode", meter.getProtocolCode());
        meterJson.put("tariffName", cons.getTariffName());
        meterJson.put("orgNo", cons.getOrgNo());
        meterJson.put("voltCode", cons.getVoltCode());
        meterJson.put("ratedCurrent", meter.getRatedCurrent());
        meterJson.put("typeCode", meter.getTypeCode());
        meterJson.put("meterMadeNo", meter.getMeterMadeNo());
        meterJson.put("meterCode", meter.getMeterCode());
        meterJson.put("consNo", cons.getConsNo());
        meterJson.put("wiringMode", meter.getWiringMode());

        TBasTextPush textMeterPush = new TBasTextPush();
        textMeterPush.setType(2);
        textMeterPush.setStatus(0);
        textMeterPush.setData(meterJson.toJSONString());

        ret = textPushService.saveTextPush(textMeterPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textMeterPush");
        }
//

        return ret;
    }

    @Transactional(value = "airportTransactionManager")
    @Override
    public int delcons(Long meterId, String consNo, String residueMoney) throws RuntimeException {

        TBasCons cons = consService.findConsByconsNo(consNo);

        if (cons == null) {
            throw new MyRequestExecption("请求的商户不存在");
        }
        cons.setStatusCode(ConstDef.CONS_OFF_STATUS);

        TBasMeter meter = meterService.findMeterByConsNo(consNo);

        if (!meter.getMeterId().equals(meterId)) {
            throw new MyRequestExecption("电表id和原电表id 不匹配");
        }

        meter.setMeterCode(ConstDef.METER_OFF_STATUS);

        int ret = consService.updateCons(cons);

        if (ret <= 0) {
            throw new MyTransactionExecption("商户 && 电表 信息状态更改错误");
        }

        ret = meterService.updateMeter(meter);

        if (ret <= 0) {
            throw new MyTransactionExecption("商户 && 电表 信息状态更改错误");
        }

        BigDecimal money = new BigDecimal(residueMoney);

        TDayResidualMoney tDayResidualMoney = new TDayResidualMoney();

        tDayResidualMoney.setBatchNo(-1L);
        tDayResidualMoney.setRemainMoney(money);
        tDayResidualMoney.setConsNo(consNo);
        tDayResidualMoney.setMeterId(meterId);
        tDayResidualMoney.setDataDate(new Date());

        ret = dayResidualMoneyService.saveDayResidualMoney(tDayResidualMoney);

        if (ret <= 0) {
            throw new MyTransactionExecption("商户余额插入失败");
        }

        JSONObject consJson = new JSONObject();

        consJson.put("consNo", cons.getConsNo());
        consJson.put("statusCode", ConstDef.CONS_OFF_STATUS);


        TBasTextPush textConsPush = new TBasTextPush();
        textConsPush.setType(3);
        textConsPush.setStatus(0);
        textConsPush.setData(consJson.toJSONString());

        ret = textPushService.saveTextPush(textConsPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textConsPush");
        }


        JSONObject meterJson = new JSONObject();

        meterJson.put("meterId", meter.getMeterId());

        meterJson.put("meterCode", ConstDef.METER_OFF_STATUS);

        TBasTextPush textMeterPush = new TBasTextPush();
        textMeterPush.setType(2);
        textMeterPush.setStatus(0);
        textMeterPush.setData(meterJson.toJSONString());

        ret = textPushService.saveTextPush(textMeterPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textMeterPush");
        }


        return ret;
    }

    @Transactional(value = "airportTransactionManager")
    @Override
    public int changeMeter(String consNo, long meterId, TBasMeter newMeter) throws RuntimeException {

        TBasMeter oldMeter = meterService.findMeterByConsNo(consNo);

        if (oldMeter == null || !oldMeter.getMeterId().equals(meterId)) {
            throw new MyRequestExecption("旧电表不存在 或者 旧电表信息错误");
        }

        oldMeter.setMeterCode(ConstDef.METER_OFF_STATUS);

        int ret = meterService.updateMeter(oldMeter);

        if (ret <= 0) {
            throw new MyTransactionExecption("商户 && 电表 信息状态更改错误");
        }

        ret = meterService.saveMeter(newMeter);

        if (ret <= 0) {
            throw new MyTransactionExecption("电表 信息保存错误");
        }


        JSONObject oldMeterJson = new JSONObject();

        oldMeterJson.put("meterId", oldMeter.getMeterId());

        oldMeterJson.put("meterCode", ConstDef.METER_OFF_STATUS);

        TBasTextPush textMeterPush = new TBasTextPush();
        textMeterPush.setType(2);
        textMeterPush.setStatus(0);
        textMeterPush.setData(oldMeterJson.toJSONString());

        ret = textPushService.saveTextPush(textMeterPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textMeterPush");
        }


        TBasCons cons = consService.findConsByconsNo(consNo);

        JSONObject meterJson = new JSONObject();

        meterJson.put("meterId", newMeter.getMeterId());
        meterJson.put("manufacturer", newMeter.getManufacturer());
        meterJson.put("commMode", newMeter.getCommMode());
        meterJson.put("protocolCode", newMeter.getProtocolCode());
        meterJson.put("tariffName", cons.getTariffName());
        meterJson.put("orgNo", cons.getOrgNo());
        meterJson.put("voltCode", cons.getVoltCode());
        meterJson.put("ratedCurrent", newMeter.getRatedCurrent());
        meterJson.put("typeCode", newMeter.getTypeCode());
        meterJson.put("meterMadeNo", newMeter.getMeterMadeNo());
        meterJson.put("meterCode", newMeter.getMeterCode());
        meterJson.put("consNo", newMeter.getConsNo());
        meterJson.put("wiringMode", newMeter.getWiringMode());

        TBasTextPush newMeterPush = new TBasTextPush();
        newMeterPush.setType(2);
        newMeterPush.setStatus(0);
        newMeterPush.setData(meterJson.toJSONString());

        ret = textPushService.saveTextPush(newMeterPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textMeterPush");
        }


        return ret;
    }

    @Transactional(value = "airportTransactionManager")
    @Override
    public int changeCons(String consNo, AddCons.ConsMessEntity newCons) throws RuntimeException {

        TBasCons cons = consService.findConsByconsNo(consNo);
        if (cons == null) {
            throw new MyRequestExecption("商户信息不存在");
        }

        cons.setMobilePhone(newCons.getMobilePhone());
        cons.setConsNo(null);

        Date date = TimeUtils.strToDateLong(newCons.getBuildDate());
        if (date == null) {
            throw new MyRequestExecption("buildDate 时间格式错误 正确时间 2019-07-12 11:11:11");
        }
        cons.setBuildDate(date);
        cons.setConsAddress(newCons.getConsAddress());
        cons.setConsType(newCons.getConsType());
        if (StringUtils.isNotBlank(newCons.getContractCap())) {
            cons.setContractCap(new BigDecimal(newCons.getContractCap()));
        }
        cons.setLineId(newCons.getLineId());
        cons.setConsName(newCons.getConsName());
        cons.setTariffId(newCons.getTariffId());
        cons.setOrgNo(newCons.getOrgNo());
        cons.setStatusCode(newCons.getStatusCode());
        cons.setTariffName(newCons.getTariffName());
        cons.setVoltCode(newCons.getVoltCode());
        cons.setTgId(newCons.getTgId());
        cons.setRemark(newCons.getRemark());

        int ret = consService.updateCons(cons);

        if (ret <= 0) {
            throw new MyTransactionExecption("商户信息状态更改错误");
        }

        JSONObject consJson = new JSONObject();

        TBasMeter meter = meterService.findMeterByConsNo(consNo);

        consJson.put("consNo", cons.getConsNo());
        consJson.put("consName", cons.getConsName());
        consJson.put("consAddress", cons.getConsAddress());
        consJson.put("consType", cons.getConsType());
        consJson.put("tariffName", cons.getTariffId());
        consJson.put("orgNo", cons.getOrgNo());
        consJson.put("buildDate", cons.getBuildDate());
        consJson.put("meterId", meter.getMeterId());
        consJson.put("statusCode", cons.getStatusCode());


        TBasTextPush textConsPush = new TBasTextPush();
        textConsPush.setType(3);
        textConsPush.setStatus(0);
        textConsPush.setData(consJson.toJSONString());

        ret = textPushService.saveTextPush(textConsPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textConsPush");
        }

        return ret;
    }


    @Transactional(value = "airportTransactionManager")
    @Override
    public int changePrice(String consNo, long meterId, long newTariffId, String newTariffName) throws RuntimeException {

        TBasCons cons = consService.findConsByconsNo(consNo);

        if (cons == null) {
            throw new MyRequestExecption("商户信息不存在");
        }

        TBasMeter oldMeter = meterService.findMeterByConsNo(consNo);

        if (!oldMeter.getMeterId().equals(meterId)) {
            throw new MyRequestExecption("电表信息有误");
        }

        cons.setTariffId(newTariffId);
        cons.setTariffName(newTariffName);

        int ret = consService.updateCons(cons);

        if (ret <= 0) {
            throw new MyTransactionExecption("商户信息状态更改错误");
        }

        JSONObject consJson = new JSONObject();

        consJson.put("consNo", cons.getConsNo());
        consJson.put("consName", cons.getConsName());
        consJson.put("consAddress", cons.getConsAddress());
        consJson.put("consType", cons.getConsType());
        consJson.put("tariffName", cons.getTariffId());
        consJson.put("orgNo", cons.getOrgNo());
        consJson.put("buildDate", cons.getBuildDate());
        consJson.put("meterId", oldMeter.getMeterId());
        consJson.put("statusCode", cons.getStatusCode());


        TBasTextPush textConsPush = new TBasTextPush();
        textConsPush.setType(3);
        textConsPush.setStatus(0);
        textConsPush.setData(consJson.toJSONString());

        ret = textPushService.saveTextPush(textConsPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textConsPush");
        }

        return ret;
    }

    @Autowired
    TransFlagService transFlagService;

    @Autowired
    ElectricityFreezeService electricityFreezeService;

    @Autowired
    FreezeService freezeService;

    @Transactional(value = "airportTransactionManager")
    @Override
    public int freezeHandler(String dataDate, Long batchNo) {

        TDayTransFlag transFlag = transFlagService.findTransFlagByBatchNo(batchNo);

        if (transFlag.getReadFlag().equals("1")) {
            return 1;
        }

        List<TDayElectricityFreeze> freezeList = electricityFreezeService.findFreezeListByBatchNo(batchNo);

        if (freezeList == null || freezeList.isEmpty()) {
            return -1;
        }

        List<TDayFreeze> insertList = new ArrayList<>();

        for (TDayElectricityFreeze freeze : freezeList) {

            TDayFreeze f =  new TDayFreeze();
            f.setBatchNo(batchNo);
            f.setConsNo(freeze.getConsNo());
            f.setOrgNo(freeze.getOrgNo());
            f.setDataDate(freeze.getDataDate());
            f.setColTime(freeze.getColTime());
            f.setMeterId(freeze.getMeterId());
            f.setPapR(freeze.getPapR());
            f.setPapR1(freeze.getPapR1());
            f.setPapR2(freeze.getPapR2());
            f.setPapR3(freeze.getPapR3());
            f.setPapR4(freeze.getPapR4());
            f.setDataResFlag(freeze.getDataResFlag());
            f.setCrc(freeze.getCrc());

            insertList.add(f);
        }

        int ret = freezeService.saveFreeze(insertList);

        if (ret != insertList.size()) {
            throw new MyTransactionExecption("日冻结电表示数录入错误" + batchNo);
        }

        ret = transFlagService.updateReadFlag(transFlag.getId());

        if (ret <= 0) {
            throw new MyTransactionExecption("更新中间库标志位错误"  + batchNo);
        }

        return 1;
    }

    @Autowired
    MoneyResidueService moneyResidueService;

    @Autowired
    DayResidualMoneyService residualMoneyService;

    @Transactional(value = "airportTransactionManager")
    @Override
    public int residueHandler(String dataDate, Long batchNo) {

        TDayTransFlag transFlag = transFlagService.findTransFlagByBatchNo(batchNo);

        if (transFlag.getReadFlag().equals("1")) {
            return 1;
        }

        List<TDayMoneyResidue> moneyList = moneyResidueService.findMoneyResiduesByBatchNo(batchNo);


        if (moneyList == null || moneyList.size() <= 0) {
            return -1;
        }

        List<TDayResidualMoney> insertList = new ArrayList<>();

        for (TDayMoneyResidue money : moneyList) {
            TDayResidualMoney m = new TDayResidualMoney();
            m.setDataDate(money.getDataDate());
            m.setMeterId(money.getMeterId());
            m.setConsNo(money.getConsNo());
            m.setRemainMoney(money.getRemainMoney());
            m.setRemainEnegy(money.getRemainEnegy());
            m.setAlarmEnegy(money.getAlarmEnegy());
            m.setColTime(money.getColTime());
            m.setBuyNum(money.getBuyNum());
            m.setFailEnegy(money.getFailEnegy());
            m.setOrgNo(money.getOrgNo());
            m.setBatchNo(money.getBatchNo());
            m.setOverdrEnegy(money.getOverdrEnegy());
            m.setSumEnegy(money.getSumEnegy());
            m.setSumMoney(money.getSumMoney());
            m.setOverdrLimit(money.getOverdrLimit());
            insertList.add(m);
        }

        int ret = residualMoneyService.saveDayResidualMoneyList(insertList);

        if (ret != insertList.size()) {
            throw new MyTransactionExecption("商户余额录入错误"  + batchNo);
        }

        ret = transFlagService.updateReadFlag(transFlag.getId());

        if (ret <= 0) {
            throw new MyTransactionExecption("更新中间库标志位错误"  + batchNo);
        }

        return 1;
    }

    @Autowired
    SBillService sBillService;
    @Autowired
    BillService billService;


    @Transactional(value = "airportTransactionManager")
    @Override
    public int billHandler(String dataDate, Long batchNo) {

        TDayTransFlag transFlag = transFlagService.findTransFlagByBatchNo(batchNo);

        if (transFlag.getReadFlag().equals("1")) {
            return 1;
        }

        List<STDayBill> dayBills = sBillService.findBillByBatchNo(batchNo);

        if (dayBills == null || dayBills.size() <= 0) {
            return  -1;
        }

        List<String> outTradeNoList = new ArrayList<>();

        Map<String, TPurchaseOrder> orderMap = new HashMap<>();

        Map<String, TDayBill> myBillMap = new HashMap<>();

        for (STDayBill bill : dayBills) {
            outTradeNoList.add(bill.getPurchaseId());
        }

        List<TPurchaseOrder> orders = purchaseOrderService.findOrdersByInOrderId(outTradeNoList);

        for (TPurchaseOrder order :  orders) {
            orderMap.put(order.getOrderId(), order);
        }

        List<TDayBill> myBillList = billService.findListByPurchaseIds(outTradeNoList);

        if (myBillList != null && myBillList.size() > 0) {
            for (TDayBill bill : myBillList) {
                myBillMap.put(bill.getPurchaseId(), bill);
            }
        }


        List<TDayBill> newBillList = new ArrayList<>();

        //TODO 考虑使用多线程 提升性能
        for (int i = 0; i < dayBills.size(); i++) {

            STDayBill bill =  dayBills.get(i);

            TDayBill myBill = myBillMap.get(bill.getPurchaseId());

            TPurchaseOrder purchaseOrder = orderMap.get(bill.getPurchaseId());

            if (myBill == null) {
                TDayBill newBill = new TDayBill();
                newBill.setPurchaseDate(purchaseOrder.getNotifyTime());
                newBill.setMeterId(purchaseOrder.getMeterId());
                newBill.setConsNo(purchaseOrder.getConsNo());
                newBill.setTransactionId(purchaseOrder.getTransactionId());
                newBill.setPurchaseMoney(new BigDecimal(purchaseOrder.getTotalFee() / 100));
                newBill.setAdditionMoney(purchaseOrder.getAdditionMoney());
                newBill.setInitMoney(purchaseOrder.getInitMoney());
                newBill.setSendMoney(purchaseOrder.getSendMoney());
                newBill.setAbnormalAdditionMoney(bill.getAdditionMoney());
                newBill.setAdditionMoney(bill.getInitMoney());
                newBill.setAbnormalPurchaseMoney(bill.getPurchaseMoney());
                newBill.setAbnormalSendMoney(bill.getSendMoney());
                newBill.setPlatformOk(1L);
                newBillList.add(newBill);

                continue;
            }

            //TODO 考虑到特殊情况 兼容先处理微信对账单还能再处理数据平台的对账单

            myBill.setPlatformOk(1L);
            myBill.setAbnormalAdditionMoney(bill.getAdditionMoney());
            myBill.setAdditionMoney(bill.getInitMoney());
            myBill.setAbnormalPurchaseMoney(bill.getPurchaseMoney());
            myBill.setAbnormalSendMoney(bill.getSendMoney());

            if (myBill.getPurchaseMoney().compareTo(bill.getPurchaseMoney()) == 0 &&
                myBill.getWxTotalFee().compareTo(bill.getPurchaseMoney()) == 0 &&
                myBill.getSendMoney().compareTo(bill.getSendMoney()) == 0) {
                myBill.setBillStatus(1L);
                myBill.setPlatformOk(1L);
            } else {
                myBill.setPlatformOk(1L);
                myBill.setBillStatus(-1L);
            }

            billService.updateBillStatus(myBill);

        }

        int ret = billService.saveBillList(newBillList);

        if (ret != newBillList.size()) {
            throw new MyTransactionExecption("对账单处理错误"  + batchNo);
        }

        return 1;
    }


    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Transactional(value = "airportTransactionManager")
    @Override
    public int buyNotifiy(BuyNotifiy buyNotifiy) {

        TPurchaseOrder purchaseOrder = purchaseOrderService.findOredrByOrderId(buyNotifiy.getPurchaseId());
        //订单补加扣减跟预支金后的金额与这个比较
        if (purchaseOrder == null || purchaseOrder.getSendMoney().compareTo(new BigDecimal(buyNotifiy.getSendMoney())) != 0) {
            logger.info("arvin >>> SendMoney" + purchaseOrder.getSendMoney().compareTo(new BigDecimal(buyNotifiy.getSendMoney())));
            return -1;
        }

        if (purchaseOrder.getType2().equals(3L)) {//3L表示成功
            return 1;
        }

        int ret;

        BigDecimal money = new BigDecimal(buyNotifiy.getResidueMoney());

        TDayResidualMoney tDayResidualMoney = new TDayResidualMoney();

        tDayResidualMoney.setBatchNo(-1L);
        tDayResidualMoney.setRemainMoney(money);
        tDayResidualMoney.setConsNo(buyNotifiy.getConsNo());
        tDayResidualMoney.setMeterId(buyNotifiy.getMeterId());
        tDayResidualMoney.setDataDate(new Date());

        ret = dayResidualMoneyService.saveDayResidualMoney(tDayResidualMoney);

        if (ret <= 0) {
            throw new MyTransactionExecption("余额信息更新失败");
        }

        ret = purchaseOrderService.updateType2Status(purchaseOrder.getId(), 3L, buyNotifiy.getIssuedType(), buyNotifiy.getSendTime());

        if (ret <= 0) {
            throw new MyTransactionExecption("订单状态更新失败");
        }


        TBasCons cons = consService.findConsByconsNo(purchaseOrder.getConsNo());

        //TODO 向第三方平台发送数据

        JSONObject orderJson = new JSONObject();


        orderJson.put("meterId", purchaseOrder.getMeterId());
        orderJson.put("purchaseId", purchaseOrder.getOrderId());
        orderJson.put("consName", cons.getConsName());
        orderJson.put("consNo", cons.getConsNo());
        orderJson.put("tariffName", cons.getTariffName());
        orderJson.put("orgNo", cons.getOrgNo());
        orderJson.put("sendType", purchaseOrder.getIssuedType());
        orderJson.put("purchaseMoney", purchaseOrder.getTotalFee() / 100);
        orderJson.put("sendMoney", purchaseOrder.getSendMoney().toString());
        orderJson.put("purchaseDate", purchaseOrder.getNotifyTime());
        orderJson.put("sendDate", buyNotifiy.getSendTime());
        orderJson.put("payType", purchaseOrder.getPayType());
        TBasTextPush textOrderPush = new TBasTextPush();
        textOrderPush.setType(1);
        textOrderPush.setStatus(0);
        textOrderPush.setData(textOrderPush.toString());

        ret = textPushService.saveTextPush(textOrderPush);

        if (ret <= 0) {
            throw new MyTransactionExecption("向待发送数据库写入数据失败 textOrderPush");
        }

        return 1;
    }
}
