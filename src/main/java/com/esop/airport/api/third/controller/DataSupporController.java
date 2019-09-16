package com.esop.airport.api.third.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.api.third.vo.AddCons;
import com.esop.airport.common.BaseController;
import com.esop.airport.common.JsonResult;
import com.esop.airport.common.ResultDef;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TPurchaseOrder;
import com.esop.airport.domain.service.ConsService;
import com.esop.airport.domain.service.PurchaseOrderService;
import com.esop.airport.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * 给第三方数据提供支持
 *
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-13 14:27
 **/
@RestController
@RequestMapping("/data/suppor")
public class DataSupporController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(DataSupporController.class);

    @Autowired
    ConsService consService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    /**
     * 大屏数据展示 获取购电人数、当月交易总金额、台区数、线损率
     * @return
     */
    @RequestMapping(value = "/screenShow", method = RequestMethod.GET)
    public JsonResult screenShow() {


        JSONObject retJson = new JSONObject();

        retJson.put("consCount", consService.findConsConut());  //购电用户数
        String startDate = TimeUtils.getLastMaxMonthDate();
        String endDate = TimeUtils.getPerFirstDayOfMonth();
        retJson.put("monthMoney", purchaseOrderService.findMonthMoney(startDate, endDate) / 100); //当月交易总金额
        retJson.put("tgConut", 1);    //台区数量
        retJson.put("lineLoss", "0.00");    //线损率

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, retJson);
    }


    /**
     * 当月购电金额
     * @return
     */
    @RequestMapping(value = "/monthPurchase", method = RequestMethod.GET)
    public JsonResult monthPurchase() {


        JSONArray retarray = new JSONArray();

        JSONObject json = new JSONObject();
        String startDate = TimeUtils.getLastMaxMonthDate();
        String endDate = TimeUtils.getPerFirstDayOfMonth();
        json.put("monthMoney", purchaseOrderService.findMonthMoney(startDate, endDate) / 100);   //当月交易总金额
        json.put("userName", "大兴国际机场");   //当月交易总金额
        json.put("tgConut", 1);   //当月交易总金额

        retarray.add(json);

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, retarray);
    }

    /**
     * 当月总线损率
     * @return
     */
    @RequestMapping(value = "/monthLineLoss", method = RequestMethod.GET)
    public JsonResult monthLineLoss() {

        JSONArray retarray = new JSONArray();


        JSONObject json = new JSONObject();

        json.put("lineLoss", "0.00");    //线损率
        json.put("userName", "大兴国际机场");   //当月交易总金额

        retarray.add(json);
        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, retarray);
    }


    /**
     * 当天购电数据
     * @return
     */
    @RequestMapping(value = "/dayPurchase", method = RequestMethod.GET)
    public JsonResult dayPurchase() {

        String startDate = TimeUtils.getBeforeDay(Calendar.getInstance());
        String endDate = TimeUtils.getAfterDay(Calendar.getInstance());
        List<TPurchaseOrder> tPurchaseOrders = purchaseOrderService.dayPurchaseOrederList(startDate, endDate);

        JSONObject json = new JSONObject();

        long money =  0;

        List<String> consNos = new ArrayList<>();

        for (TPurchaseOrder order : tPurchaseOrders) {
            money += order.getTotalFee();
            consNos.add(order.getConsNo());
        }

        List<TBasCons> ConsList = consService.findConsByConsNos(consNos);

        Map<String, TBasCons> consMap = new HashMap<>();

        for (TBasCons cons : ConsList) {
            consMap.put(cons.getConsNo(), cons);
        }

        JSONArray buyList =  new JSONArray();

        for (TPurchaseOrder order : tPurchaseOrders) {
            JSONObject buyJson = new JSONObject();
            if (consMap.containsKey(order.getConsNo())) {
                buyJson.put("consName", consMap.get(order.getConsNo()).getConsName());
                buyJson.put("buyDate", order.getNotifyTime());
                buyJson.put("money", order.getTotalFee() / 100);
                buyList.add(buyJson);
            }

        }

        json.put("dayConut", tPurchaseOrders.size());    //当日够电数
        json.put("dayMoney", money / 100);   //当日交易总金额
        json.put("dayBuyList", buyList);     //当日购电交易记录

//        JSONArray lineLossList = new JSONArray();
//
//        for (int i = 0; i < 7; i++) {
//            JSONObject lossJson = new JSONObject();
//            lossJson.put("tgName", "测试台区" + i);
//            lossJson.put("lineLoss", "0.00");
//            lineLossList.add(lossJson);
//        }
//
//        json.put("lineLossList", lineLossList);     //线损柱状图

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, json);
    }


    /**
     * 获取购电年月统计数据
     * @return
     */
    @RequestMapping(value = "/purchaseData", method = RequestMethod.GET)
    public JsonResult purchaseData() {

        String startDate = TimeUtils.getBeforeDay(Calendar.getInstance());
        String endDate = TimeUtils.getAfterDay(Calendar.getInstance());

        List<TPurchaseOrder> tPurchaseOrders = purchaseOrderService.dayPurchaseOrederList(startDate, endDate);

        String startMonthDate = TimeUtils.getLastMaxMonthDate();
        String endMonthDate = TimeUtils.getPerFirstDayOfMonth();

        List<Map<String, Object>> monthData = purchaseOrderService.findMonthData(startMonthDate, endMonthDate);

        String startYearDate = TimeUtils.getLastMaxYearDate();
        String endYearDate = TimeUtils.getPerFirstDayOfYear();

        List<Map<String, Object>> yearData = purchaseOrderService.findYearData(startYearDate, endYearDate);


        JSONObject retJson = new JSONObject();

        retJson.put("dayCount", tPurchaseOrders == null ? 0 : tPurchaseOrders.size());

        long dayMax = 0;
        long dayMin = 0;
        long dayAverage = 0;
        long dayCount = 0;

        if (tPurchaseOrders != null && tPurchaseOrders.size() > 0) {
            for (int i = 0; i < tPurchaseOrders.size(); i++) {
                TPurchaseOrder order = tPurchaseOrders.get(i);
                long total = order.getTotalFee() / 100;
                if (total > dayMax) {
                    dayMax = total;
                }
                if (dayMin == 0) {
                    dayMin = total;
                }
                if (total < dayMin) {
                    dayMin = total;
                }
                dayCount += total;
                if (i == tPurchaseOrders.size() - 1) {
                    dayAverage = dayCount / tPurchaseOrders.size();
                }
            }
        }

        retJson.put("dayMax", dayMax);
        retJson.put("dayMin", dayMin);
        retJson.put("dayAverage", dayAverage);
        retJson.put("dayCount", dayCount);


        long monthMax = 0;
        long monthMin = 0;
        long monthAverage = 0;
        long monthCount = 0;

        if (monthData != null && monthData.size() > 0) {
            for (int i = 0; i < monthData.size(); i++) {
                Map<String, Object> map = monthData.get(i);

                long totalNum = ((BigDecimal)map.get("total")).longValue() / 100;
                if (totalNum > monthMax) {
                    monthMax = totalNum;
                }
                if (monthMin == 0) {
                    monthMin = totalNum;
                }
                if (totalNum < monthMin) {
                    monthMin = totalNum;
                }
                monthCount += totalNum;
                if (i == monthData.size() -1) {
                    monthAverage =  monthCount / monthData.size();
                }
            }
        }

        retJson.put("monthMax", monthMax);
        retJson.put("monthMin", monthMin);
        retJson.put("monthAverage", monthAverage);
        retJson.put("monthCount", monthCount);
        retJson.put("monthData", monthData);


        long yearMax = 0;
        long yearMin = 0;
        long yearAverage = 0;
        long yearCount = 0;

        if (yearData != null && yearData.size() > 0) {
            for (int i = 0; i < yearData.size(); i++) {
                Map<String, Object> map = yearData.get(i);
                long totalNum = ((BigDecimal)map.get("total")).longValue() /100;
                if (totalNum > yearMax) {
                    yearMax = totalNum;
                }
                if (yearMin == 0) {
                    yearMin = totalNum;
                }
                if (totalNum < yearMin) {
                    yearMin = totalNum;
                }
                yearCount += totalNum;
                if (i == yearData.size() -1) {
                    yearAverage =  yearCount / yearData.size();
                }
            }
        }

        retJson.put("yearMax", yearMax);
        retJson.put("yearMin", yearMin);
        retJson.put("yearAverage", yearAverage);
        retJson.put("yearCount", yearCount);
        retJson.put("yearData", yearData);


        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, retJson);
    }

}
