package com.esop.airport.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: airport
 * @description: 常量默认值
 * @author: Mr.Li
 * @create: 2019-06-10 16:22
 **/
public class ConstDef {

    public static final int SMS_RESEND_GAP = 60;            //60秒只能获取一次短信验证码

    public static final int GET_VERIFICATION_CODE = 15;     //每天短信验证码上线次数

    public static final int WX_MINI = 1;                    //微信小程序

    public static final int PAGE_SIZE = 5;


    //短信模板id
    public static final  int RESIDUAL_MONEY_WARN = 362231;  //余额预警	您的商户:{1}当前电量余额已经不足{2}元
    public static final int SMS_VERIFICATION_CODE = 362228;//短信验证码  您的验证码是{1}


    //三方平台字典数据
    public static final String CONS_NORMAL_STATUS = "1";        //商户正常状态
    public static final String CONS_OFF_STATUS = "2";           //商户注销

    public static final String METER_RUN_STATUS = "007";        //电表运行状态
    public static final String METER_STOCK_STATUS = "008";      //电表在库状态
    public static final String METER_OFF_STATUS = "009";        //电表拆除状态

    public static final Map<Integer, String> THREE_DATA_URL = new HashMap<>();

    static {
        THREE_DATA_URL.put(1, "/modbushplc/hplc/eleBillApi/receiveEleBill"); //购电单
        THREE_DATA_URL.put(2, "/modbushplc/hplc/eleBillApi/receiveEleMeter"); //资产电表
        THREE_DATA_URL.put(3, "/modbushplc/hplc/eleBillApi/receiveEleBillCustomer"); //客户数据
    }
}


