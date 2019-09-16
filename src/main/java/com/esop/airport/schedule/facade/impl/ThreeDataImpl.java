package com.esop.airport.schedule.facade.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.common.ConstDef;
import com.esop.airport.common.MyTransactionExecption;
import com.esop.airport.domain.service.TextPushService;
import com.esop.airport.schedule.facade.ThreeDataFacade;
import com.esop.airport.utils.OkHttpManager;
import com.esop.airport.utils.ParamsSignatureUtil;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-12 09:03
 **/
@Service
public class ThreeDataImpl implements ThreeDataFacade {
    Logger logger = LoggerFactory.getLogger(ThreeDataImpl.class);


    @Value("${three.url}")
    String threeUrl;

    @Value("${three.md5key}")
    String md5key;

    @Autowired
    TextPushService textPushService;

    @Transactional(value = "airportTransactionManager")
    @Override
    public int sendData(Long textId, int type,String data) throws MyTransactionExecption {

        int ret = textPushService.updateTextPushStatusById(textId);

        if (ret > 0) {

            String  url = threeUrl + ConstDef.THREE_DATA_URL.get(type);

//            switch (type) {
//                case 1:
//                    url = threeUrl + ConstDef.THREE_DATA_URL.get(type);
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    break;
//                default:
//                    return 0;
//            }

            logger.info("arvin RequestBody >>> url >>>" + url + " >>> " + data);

            JSONObject jsonObject = JSON.parseObject(data);
            JSONObject signature = ParamsSignatureUtil.signature(jsonObject, md5key);

            Map<String, String> header =  new HashMap<>();
            header.put("Token", "Token");
            header.put("Version", "Version");

            Response response = OkHttpManager.getInstance().postSync(url, header, signature.toJSONString());

            try {
                String retJosn = response.body().string();

                JSONObject json = JSON.parseObject(retJosn);

                logger.info("arvin retData >>> " + json.toJSONString());

                if (json.getIntValue("resultCode") == 200) {
                   //第三方数据推送成功
                } else {
                    throw  new MyTransactionExecption("三方数据推送失败 推送标志进行回滚操作 id：" + textId);
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw  new MyTransactionExecption("HTTP ERR MSG" + e.getMessage());
            }
        }

        return 1;
    }

}
