package com.esop.airport.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.utils.OkHttpManager;
import com.esop.airport.utils.SHA256Util;
import com.esop.airport.utils.StringUtils;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @author arvin liliqiang
 * @create 2018-06-05 下午3:49
 **/
@Component
public class SMSUtil {

    private static Logger logger = LoggerFactory.getLogger(SMSUtil.class);

    @Value("${sms.url}")
    String smsUrl;

    @Value("${sms.sdkkey}")
    String smsSdkKey;

    @Value("${sms.sdkappid}")
    String smsSdkappId;

    public void sendEnentSMS(int tplId, String phone, String... params) {


        String strMobile = phone; //tel 的 mobile 字段的内容
        String strAppKey = smsSdkKey; //sdkappid 对应的 appkey，需要业务方高度保密
        String strRand = ((long) (Math.random() * (9999999999L - 1000000000 + 1)) + 1000000000) + "";
        String strTime = System.currentTimeMillis() / 1000L + ""; //UNIX 时间戳

        String sig = "appkey=" + strAppKey + "&random=" + strRand + "&time=" + strTime + "&mobile=" + strMobile;
        sig = SHA256Util.getSHA256StrJava(sig);

        List<String> strings = Arrays.asList(params);
        SMSEntity entity = new SMSEntity();
        entity.setParams(strings);
        entity.setTel(new SMSEntity.TelEntity(phone, "86"));
        entity.setSig(sig);
        entity.setSign("九州恒盛");
        entity.setTpl_id(tplId);
        entity.setTime(Integer.parseInt(strTime));

        String sendSMS = JSON.toJSONString(entity);

        logger.info(sendSMS);

        String url = smsUrl + "?sdkappid="+smsSdkappId + "&random=" + strRand;

        Response response = OkHttpManager.getInstance().postSync(url, null, sendSMS);//调用第三方接口

        try {
            String body = response.body().string();
//            logger.info("sms >>> body >>> " + body);
            if (StringUtils.isNotBlank(body)) {
                logger.info("sms >>> send >>> " + body);
            }
        } catch (IOException e) {
            logger.info("sms >>> send >>> " + e.getMessage());
            e.printStackTrace();
        }
    }

}




//{
//        "ext": "",
//        "extend": "",
//        "params": [
//        "验证码",
//        "1234",
//        "4"
//        ],
//        "sig": "ecab4881ee80ad3d76bb1da68387428ca752eb885e52621a3129dcf4d9bc4fd4",
//        "sign": "腾讯云",
//        "tel": {
//        "mobile": "13788888888",
//        "nationcode": "86"
//        },
//        "time": 1457336869,
//        "tpl_id": 19
//        }

