package com.esop.airport.common;

import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.service.RedisCacheService;
import com.esop.airport.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liliqiang on 2018/1/31.
 */
@Component
public class BaseController {


    @Autowired
    RedisCacheService redisCacheService;

    /**
     * 短信验证码验证
     *
     * @param code
     * @param phone
     * @return
     */
    public JsonResult isSMS(String code, String phone) {

        if (org.springframework.util.StringUtils.isEmpty(code) || org.springframework.util.StringUtils.isEmpty(phone)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        String srt = (String) redisCacheService.getObj(MD5Utils.getMd5(phone));
        if (org.springframework.util.StringUtils.isEmpty(srt)) {
            return new JsonResult(ResultDef.CERR_SMS_CODE.code, ResultDef.CERR_SMS_CODE.msg);
        }

        String[] codePhone = ((String) (redisCacheService.getObj(MD5Utils.getMd5(phone)))).split("&");
        if (!code.equals(codePhone[0])) {//不一致验证失败
            return new JsonResult(ResultDef.CERR_SMS_CODE.code, ResultDef.CERR_SMS_CODE.msg);
        }

        redisCacheService.remove(MD5Utils.getMd5(phone));//匹配上的话，清除这个手机号存的验证码信息。

        return null;
    }


    /**
     * 鉴权后的用户获取User实体对象
     *
     * @return
     */
    public TBasUser getUser() {

        Object o = MemUserCache.get(MemUserCache.ORG_WX_USER);

        if (o instanceof TBasUser) {
            return (TBasUser) o;
        }

        return null;
    }

    public JsonResult getBindErrMsg(BindingResult bind) {

        if (bind.hasErrors()) {
            List<String> list = new ArrayList<>();
            for (ObjectError err : bind.getAllErrors()) {
                list.add(err.getDefaultMessage());
            }
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg, list.toString());
        }

        return null;
    }

}
