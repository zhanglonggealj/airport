package com.esop.airport.api.open.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.esop.airport.api.open.facade.Test;
import com.esop.airport.api.wxmini.controller.PurchaseController;
import com.esop.airport.common.*;
import com.esop.airport.domain.mapper.MeterMapper;
import com.esop.airport.domain.middle.smapper.SBillMapper;
import com.esop.airport.domain.model.TBasCons;
import com.esop.airport.domain.model.TBasMeter;
import com.esop.airport.domain.model.TBasUser;
import com.esop.airport.domain.model.TToken;
import com.esop.airport.domain.model.mongo.TStatDayCompany;
import com.esop.airport.domain.model.mongo.TestMongo;
//import com.esop.airport.domain.mongo.TestMongoRepository;
//import com.esop.airport.domain.mongo.TestPointRepository;
import com.esop.airport.domain.service.*;
import com.esop.airport.task.UserTask;
import com.esop.airport.utils.*;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.parser.Token;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开发权限接口
 * @author arvin liliqiang
 * @create 2019-05-28 08:56
 **/
@RestController
@RequestMapping("/open")
public class OpenController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(OpenController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ConsService consService;

    @Autowired
    private RedisCacheService redisServer;

    @Autowired
    private TokenService tokenService;

    @Autowired
    MeterService meterService;

    @Autowired
    UserTask userTask;

    @Autowired
    Test test;

    @Autowired
    SMSUtil smsUtil;

//    @Autowired
//    BillMapper billMapper;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public JsonResult hello(String a) {

//        PageHelper.startPage(1, 1);

//        test.Test();
        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);
    }

    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getSMSCode", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSMSCode(String phone) {

        if (StringUtils.isEmpty(phone)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        if (!AccountValidatorUtil.isMobile(phone)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        final String countTag = "#count#";
        int count = 0;
        Map<String, Integer> smsMap = (Map<String, Integer>) redisServer.getObj(phone + "_" + MD5Utils.getMd5(phone + countTag));

        if (smsMap != null) {
            count = smsMap.get("count");
            int smstime = smsMap.get("smstime");
            long time = (System.currentTimeMillis() / 1000) - smstime;
            if (time < ConstDef.SMS_RESEND_GAP) { //60s 内不能重复发送
                return new JsonResult(ResultDef.CERR_SMS_TIME_ERR.code, (ConstDef.SMS_RESEND_GAP - time) + ResultDef.CERR_SMS_TIME_ERR.msg);
            }
        }

        if (count > ConstDef.GET_VERIFICATION_CODE) {
            return new JsonResult(ResultDef.CERR_SMS_ERR.code, ResultDef.CERR_SMS_ERR.msg);
        }
        // 产生1000-9999的随机数
        String smsCode = "" + ((int) (Math.random() * (9999 - 1000 + 1)) + 1000);

        redisServer.putObj(MD5Utils.getMd5(phone), smsCode + "&" + phone, 60L * 10);//手机号MD5加密，随机数&手机号

        Map<String, Integer> map = new HashMap<>(2);
        map.put("count", ++count);
        map.put("smstime", (int) (System.currentTimeMillis() / 1000));

        logger.info("arvin sms code >> " + smsCode);

        // 下发短信
        smsUtil.sendEnentSMS(ConstDef.SMS_VERIFICATION_CODE, phone, smsCode);//短信验证码模板 调用第三方接口

        //保存记录
        redisServer.putObj(phone + "_" + MD5Utils.getMd5(phone + countTag), map, 60L * 60 * 24);//1天 次数时间

        return new JsonResult(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);
    }

    /**
     * 获取微信openId
     * @param map
     * @return
     */
    @RequestMapping(value = "/getWxOpenId", method = RequestMethod.POST)
    public JsonResult getWxOpenId(@RequestBody Map<String, String> map) {

        if (!map.containsKey("code")) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg, "code 无效");
        }
        //appid表示的是小程序的id
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx99d12f2a7c45c76b&secret=f2ae727b4282df1065245a13e8cb0588&js_code=" +
                map.get("code")
                + "&grant_type=authorization_code";

        Response response = OkHttpManager.getInstance().getSync(null, url);

        JSONObject jsonObject = null;

        try {
            jsonObject = JSON.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        logger.info("arvin >>> " + jsonObject.toJSONString());

        String openId = jsonObject.getString("openid");

        if (StringUtils.isEmpty(openId)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, openId);
    }

    /**
     * 微信小程序用户登录
     * @param map
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody Map<String, String> map) {


        String openId = map.get("openId");
        if (StringUtils.isBlank(openId)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }
        TBasUser user;

        String phone = map.get("phone");
        String pass = map.get("pass");

        if (StringUtils.isNotBlank(pass, phone)) {
            user = userService.findUserByPhonePass(phone, MD5Utils.getMd5(pass));
            if (user == null) {
                return new JsonResult(ResultDef.CERR_USER_PASSWORD.code, ResultDef.CERR_USER_PASSWORD.msg);
            }
            user.setWxOpenid(openId);

        } else {

            user = userService.findUserByOpenId(openId);//手机号密码不存在 直接用openId登陆，这个地方的业务逻辑，
        }


        if (user != null) {

            user.setLastLoginTime(new Date(System.currentTimeMillis()));

            userTask.updateUser(user); //异步更新user 更新时间,微信用户改名啊，openid

            tokenService.findTokensByUserIdAndCategory(user.getId(), ConstDef.WX_MINI)//根据user表的主键找到user下的所有token信息
                    .forEach(token -> tokenService.clearCacheByToken(token.getTokenCode()));
            tokenService.delToken(user.getId(), ConstDef.WX_MINI);//删除token表里的记录  后面的人会顶掉前面那个人的登陆信息，叫做单点登录

            String uuid = UUID.getUUID();
            TToken t = new TToken();
            t.setCategory(ConstDef.WX_MINI);
            t.setTokenCode(uuid);
            t.setBindId(user.getId());
            int tret = tokenService.saveToken(t);//保留一个新的token  tokencode是uuid

            if (tret <= 0) {
                return new JsonResult(ResultDef.CERR.code, ResultDef.CERR.msg);
            }

            List<TBasCons> consList = consService.findConsListByPhone(user.getMobilePhone());//根据预留手机号查找对应商户


            JSONObject retJson = new JSONObject();

            if (consList != null && consList.size() > 0) {

                List<String> consNos = new ArrayList<>();
                for (TBasCons cons : consList) {
                    consNos.add(cons.getConsNo());
                }
                List<TBasMeter> meterList = meterService.findMeterListByConsNo(consNos);//根据商户号集合，查找电表运行code码为007的

                Map<String, TBasMeter> meterMap = new HashMap<>();

                for (TBasMeter meter : meterList) {//一个商户号  对应的电表信息
                    meterMap.put(meter.getConsNo(), meter);
                }

                for (TBasCons cons : consList) {
                    TBasMeter meter = meterMap.get(cons.getConsNo());
                    if (meter != null) {
                        cons.setMeterId(meter.getMeterId());
                        cons.setMeterMadeNo(meter.getMeterMadeNo());
                    }
                }

                retJson.put("cons", consList);
                retJson.put("user", user);
                retJson.put("userType", 1);
                retJson.put("token", uuid);
                return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, retJson);//如果有商户，将商户信息（关联的电表id）返回
            } else {
                retJson.put("user", user);
                retJson.put("userType", 2);
                retJson.put("token", uuid);
                return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, retJson);//无商户也返回
            }

        }

        return new JsonResult(ResultDef.CERR_USER_NOT_EXIST.code, ResultDef.CERR_USER_NOT_EXIST.msg);
    }


    /**
     * 小程序用户注册店主还是非店主
     * @param map 用户编号，表计编号。
     * @return
     */
    @RequestMapping(value = "confirmIsShopkeeper",method =RequestMethod.POST)//一个商户只能有一个正在运行的电表
    public JsonResult confirmIsShopkeeper(@RequestBody Map<String, String> map){
            if(!map.containsKey("consNo")||!map.containsKey("metermadeno")){
                return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
            }
            String consNo=map.get("consNo");//商户号
            String metermadeno=map.get("metermadeno");//电表号
            TBasCons cons=consService.findConsByconsNo(consNo);
            if (cons == null) {
                return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
            }

            //TBasMeter meter=meterService.findMeterByConsNoAndMeterMadeNo(consNo,metermadeno);

            TBasMeter meter=meterService.findMeterByConsNo(consNo);  //一个商户只有一个正在运行的电表。
            if (meter == null) {
                return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
            }
            if(!metermadeno.equals(meter.getMeterMadeNo())) {//校验电表的出厂编号。
                return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
            }

            return new JsonResult(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);

    }


    /**
     * 小程序用户注册店主还是非店主!!!
     * @param map 用户编号，表计编号。
     * @return
     */
    @RequestMapping(value = "confirmIsShopkeeper1",method =RequestMethod.POST)//一个商户只能有一个正在运行的电表
    public JsonResult confirmIsShopkeeper1(@RequestBody Map<String, String> map){
        if(!map.containsKey("consNo")||!map.containsKey("metermadeno")){
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }
        String consNo=map.get("consNo");//商户号
        String metermadeno=map.get("metermadeno");//电表号
        TBasCons cons=consService.findConsByconsNo(consNo);
        if (cons == null) {
            return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
        }


        TBasMeter meter=meterService.findMeterByConsNo(consNo);  //一个商户只有一个正在运行的电表。
        if (meter == null) {
            return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
        }
        if(!metermadeno.equals(meter.getMeterMadeNo())) {//校验电表的出厂编号。
            return new JsonResult(ResultDef.CERR_CONS_NOT_EXIST.code, ResultDef.CERR_CONS_NOT_EXIST.msg);
        }

        TBasUser user=getUser();//拿到手机号
        TBasCons cons1=new TBasCons();
        cons1.setId(cons.getId());
        cons1.setMobilePhone(user.getMobilePhone()); //不加事物也可以
        int res=consService.updateCons(cons1);

        return new JsonResult(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);

    }

    /***
     *
     * 小程序用户注册,手机号由我们维护，第三方不维护这个手机号。根据是店主还是店员更新这个手机号。店主才能看明细
     * @param map
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody Map<String, String> map) {
        if(!map.containsKey("consNo")){//判断是店主还是店员注册  type为true是店主，false为店员
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        if (!map.containsKey("openId")) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        if (!map.containsKey("nickName") || !map.containsKey("headPortrait") || !map.containsKey("phone")
                || !map.containsKey("smsCode") || !map.containsKey("pass")) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        String mobilePhone = map.get("phone");
        if (!AccountValidatorUtil.isMobile(mobilePhone)) {
            return new JsonResult(ResultDef.CERR_MOBILE_ILLEGAL.code, ResultDef.CERR_MOBILE_ILLEGAL.msg);
        }
        String smsCode = map.get("smsCode");

        //判断短信验证码是否正确
        JsonResult retSMS = isSMS(smsCode, mobilePhone);//从redis根据手机号取，

        if (retSMS != null) {
            return retSMS;
        }
        //上面校验完就清除redis了

        String consNo=map.get("consNo");
        if(!StringUtils.isEmpty(consNo)){//不为空表示是店主
            TBasCons cons=consService.findConsByconsNo(consNo);
            if(cons==null){
                return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
            }
            TBasCons cons1=new TBasCons();
            cons1.setId(cons.getId());
            cons1.setMobilePhone(mobilePhone); //不加事物也可以
            int res=consService.updateCons(cons1);
        }

        TBasUser user = userService.findUserByPhone(mobilePhone);//状态为1

        if (user != null) {
            return new JsonResult(ResultDef.CERR_USER_EXIST.code, ResultDef.CERR_USER_EXIST.msg);
        }


        String openId = map.get("openId");
        String nickName = map.get("nickName");
        String headPortrait = map.get("headPortrait");
        String pass = map.get("pass");

        int sex = 0;
        try {
            sex = Integer.parseInt(map.get("sex"));
        } catch (Exception e) {
            logger.error("arvin >>> " + e.getMessage());
        }

        user = userService.findUserByOpenId(openId);

        if (user != null) {
            user.setWxOpenid("reg-" + UUID.getUUID());//更新openId，把openId改为uuid
            userService.updataUser(user);
        }

        user = new TBasUser();
        user.setWxOpenid(openId);//注册的openId
        user.setMobilePhone(mobilePhone);
        user.setHeadPortrait(headPortrait);
        user.setNickName(nickName);
        user.setSex(sex);
        user.setPassword(MD5Utils.getMd5(pass));

        int ret = userService.saveUser(user);

        return ret > 0 ? new JsonResult(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult(ResultDef.CERR.code, ResultDef.CERR.msg);//失败，性别失败通过日志来看。
    }

    /**
     * 小程序用户忘记密码
     * @param map
     * @return
     */
    @RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
    public JsonResult forgotPass(@RequestBody Map<String, String> map) {


        if (!map.containsKey("phone") || !map.containsKey("smsCode") || !map.containsKey("pass")) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        String phone = map.get("phone");

        if (!AccountValidatorUtil.isMobile(phone)) {
            return new JsonResult(ResultDef.CERR_REQ_PARAMS.code, ResultDef.CERR_REQ_PARAMS.msg);
        }

        String smsCode = map.get("smsCode");

        //判断短信验证码是否正确
        JsonResult retSMS = isSMS(smsCode, phone);

        if (retSMS != null) {
            return retSMS;
        }

        String pass = map.get("pass");


        TBasUser user = userService.findUserByPhone(phone);

        if (user == null) {
            return new JsonResult(ResultDef.CERR_USER_NOT_REGISTER.code, ResultDef.CERR_USER_NOT_REGISTER.msg);
        }

        user.setPassword(MD5Utils.getMd5(pass));

        int ret = userService.updataUser(user);

        return ret > 0 ? new JsonResult(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg)
                : new JsonResult(ResultDef.CERR.code, ResultDef.CERR.msg);
    }

//    @Autowired
//    TestMongoRepository testMongoRepository;
//
//    @RequestMapping(value = "/testMongo", method = RequestMethod.POST)
//    public JsonResult testMongo(@RequestBody Map<String, String> map) {
//
//        String cid = map.get("cid");
//        String hisdate = map.get("hisdate");
//        String measurecode = map.get("measurecode");
//        String meterid = map.get("meterid");
//        String startHisdate = map.get("startHisdate");
//        String endHisdate = map.get("endHisdate");
//        List<TestMongo> list2 = null;
//        if (StringUtils.isNotBlank(startHisdate, endHisdate)) {
//            list2 = testMongoRepository.findByHisdateBetweenAndCompanyidAndMeasurecodeAndMeterid(startHisdate, endHisdate, cid, measurecode,meterid);
//            return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, list2);
//        }
//
//        list2 = testMongoRepository.findByCompanyidAndHisdateAndMeasurecodeAndMeterid(cid, hisdate, measurecode,meterid);
//
//        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg, list2);
//    }
//
//
//    @RequestMapping(value = "/testMongoSave", method = RequestMethod.POST)
//    public JsonResult testMongoSave(@RequestBody List<TestMongo> list) {
//
//
//        testMongoRepository.saveAll(list);
//
//        return new JsonResult<>(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);
//    }
//    //////TODO 测试用
//
//    @Autowired
//    PurchaseController purchaseController;
//
//    @RequestMapping(value = "/hanlong", method = RequestMethod.POST)
//    public JsonResult hanlong(@RequestBody Map<String, String> map) {
//        TBasUser user = userService.findUserById(14L);
//        if (user != null) {
//            MemUserCache.set(MemUserCache.ORG_WX_USER, user);
//        }
//        return purchaseController.order(map);
//    }
//
//    @Autowired
//    MeterMapper meterMapper;
//
//    @RequestMapping(value = "/hanlong2", method = RequestMethod.POST)
//    public JsonResult hanlong2(@RequestBody Map<String, String> map) {
//        String count = map.get("count");
//
//        Integer c = Integer.parseInt(count);
//        TBasMeter tBasMeter = meterMapper.selectByPrimaryKey(11);
//        tBasMeter.setPurchaseCount(c);
//        meterMapper.updateByPrimaryKeySelective(tBasMeter);
//
//        return new JsonResult(ResultDef.SUCCESS.code, ResultDef.SUCCESS.msg);
//    }
}
