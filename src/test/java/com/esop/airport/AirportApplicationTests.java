package com.esop.airport;

import com.alibaba.fastjson.JSONObject;
import com.esop.airport.api.wxmini.controller.AssistantController;
import com.esop.airport.common.ConstDef;
import com.esop.airport.common.MyTransactionExecption;
import com.esop.airport.common.SMSUtil;
import com.esop.airport.domain.mapper.PurchaseOrderMapper;
import com.esop.airport.domain.model.*;
import com.esop.airport.domain.service.*;
import com.esop.airport.task.OrderTask;
import com.esop.airport.utils.TimeUtils;
import com.esop.airport.utils.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportApplicationTests {


	@Autowired
	TokenService tokenService;

	@Autowired
	ConsService consService;

	@Autowired
	UserService userService;

	@Autowired
	AssistantController assistantController;

	@Autowired
	SMSUtil smsUtil;

	@Autowired
	PurchaseOrderService purchaseOrderService;
	@Autowired
	OrderTask orderTask;

	@Autowired
	TextPushService textPushService;

	@Test
	public void taskTest() {

		TPurchaseOrder oredr = purchaseOrderService.findOredrByOrderId("555288201908191408884");
		orderTask.sendOrder(oredr);

		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void contextLoads() {
//
//		TToken t = new TToken();
//		t.setBindId(1L);
//		t.setCategory(1);
//		t.setTokenCode("0000");
//		tokenService.saveToken(t);
//
//		System.out.println(tokenService.findTokenByCode("0000"));

//		System.out.println(assistantController.getCons("500", ""));

//		throw new RuntimeException("");

//		smsUtil.sendEnentSMS(ConstDef.SMS_VERIFICATION_CODE,"17330586638", "1234");

//		smsUtil.sendEnentSMS(ConstDef.RESIDUAL_MONEY_WARN,"15810629348", "星巴克", "1000");


		//新增购电单
//		JSONObject orderJson = new JSONObject();
//
//
//		orderJson.put("meterId", 11011);
//		orderJson.put("purchaseId", UUID.getUUID());
//		orderJson.put("consName", "测试商户");
//		orderJson.put("consNo", "102600000837");
//		orderJson.put("tariffName", "平民电价");
//		orderJson.put("orgNo", "1");
//		orderJson.put("sendType", 1);
//		orderJson.put("purchaseMoney", 100);
//		orderJson.put("sendMoney", "101.11");
//		orderJson.put("purchaseDate", TimeUtils.getStringDate());
//		orderJson.put("sendDate", TimeUtils.getStringDate());
//
//		TBasTextPush textOrderPush = new TBasTextPush();
//		textOrderPush.setType(1);
//		textOrderPush.setStatus(0);
//		textOrderPush.setData(orderJson.toString());
//
//		int ret = textPushService.saveTextPush(textOrderPush);

//		JSONObject consJson = new JSONObject();
//
//		consJson.put("consNo", "102600000837");
//		consJson.put("consName", "测试商户");
//		consJson.put("consAddress", "测试地址");
//		consJson.put("consType", "1");
//		consJson.put("tariffName", "平民电价");
//		consJson.put("orgNo", "1");
//		consJson.put("buildDate", TimeUtils.getStringDate());
//		consJson.put("meterId", 11011);
//		consJson.put("statusCode", "1");
//
//
//		TBasTextPush textConsPush = new TBasTextPush();
//		textConsPush.setType(3);
//		textConsPush.setStatus(0);
//		textConsPush.setData(consJson.toJSONString());
//
//		ret = textPushService.saveTextPush(textConsPush);
//
//
//
//		JSONObject meterJson = new JSONObject();
//
//		meterJson.put("meterId", 11011);
//		meterJson.put("manufacturer", "00003");
//		meterJson.put("commMode", "001");
//		meterJson.put("protocolCode", "001");
//		meterJson.put("tariffName", "平民电价");
//		meterJson.put("orgNo", "1");
//		meterJson.put("voltCode", "1");
//		meterJson.put("ratedCurrent", "2");
//		meterJson.put("typeCode", "1");
//		meterJson.put("meterMadeNo", "190610544941");
//		meterJson.put("meterCode", "007");
//
//		TBasTextPush textMeterPush = new TBasTextPush();
//		textMeterPush.setType(2);
//		textMeterPush.setStatus(0);
//		textMeterPush.setData(meterJson.toJSONString());
//
//		ret = textPushService.saveTextPush(textMeterPush);
//
//
////		删除电表
//
//		JSONObject delmeterJson = new JSONObject();
//
//		delmeterJson.put("meterId", 11011);
//
//		delmeterJson.put("meterCode", "009");
//
//		TBasTextPush delMeterPush = new TBasTextPush();
//		delMeterPush.setType(2);
//		delMeterPush.setStatus(0);
//		delMeterPush.setData(delmeterJson.toJSONString());
//
//		ret = textPushService.saveTextPush(delMeterPush);
//
//		JSONObject updateconsJson = new JSONObject();
//
//		updateconsJson.put("consNo", "102600000837");
//		updateconsJson.put("consName", "测试商户更改");
//		updateconsJson.put("consAddress", "测试地址更改更改");
//		updateconsJson.put("consType", "1");
//		updateconsJson.put("tariffName", "平民电价2222");
//		updateconsJson.put("orgNo", "1");
//		updateconsJson.put("buildDate", TimeUtils.getStringDate());
//		updateconsJson.put("meterId", 11011);
//		updateconsJson.put("statusCode", "1");
//
//
//		TBasTextPush newtextConsPush = new TBasTextPush();
//		newtextConsPush.setType(3);
//		newtextConsPush.setStatus(0);
//		newtextConsPush.setData(updateconsJson.toJSONString());
//
//		ret = textPushService.saveTextPush(newtextConsPush);


//		JSONObject delConsJson = new JSONObject();
//
//		delConsJson.put("consNo", "102600000837");
//
//		delConsJson.put("statusCode", "2");
//
//
//		TBasTextPush delConsPush = new TBasTextPush();
//		delConsPush.setType(3);
//		delConsPush.setStatus(0);
//		delConsPush.setData(delConsJson.toJSONString());
//
//		textPushService.saveTextPush(delConsPush);
	}

	@Autowired
	PurchaseOrderMapper purchaseOrderMapper;

	@Test
	public void testSql() {
//		List<Map<String, Object>> yearData = purchaseOrderMapper.findYearData();
//		System.out.println(yearData);
//
//		List<Map<String, Object>> monthData = purchaseOrderMapper.findMonthData();
//		System.out.println(monthData);
	}


}
