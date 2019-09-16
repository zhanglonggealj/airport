package com.esop.airport.common;

/**
 * 接口请求结果常量枚举类
 * <p>
 * 响应code 3位数以内属于系统级(或者通用的响应code);
 * code=2xx 表示各种成功操作;
 * code=4xx 表示client侧请求状态码(如:请求违法/各种校验违规);
 * code=5xx 表示server侧信息状态码,也包括错误信息(如:业务处理捕捉到的异常/server侧的故障导致的无法接受client请求提示);
 * <p>
 * code=201XXX 表示xx
 * code=202XXX 表示xx
 *
 * @author arvin
 * @create 2018-08-12 16:39
 */
public enum ResultDef {

    SUCCESS(200, "成功！"),
    CERR(400, "请求失败！"),
    SERR(500, "服务故障！"),

    CERR_REQ_PARAMS(401, "请求参数错误！"),
    CERR_TOKEN(402, "登陆状态失效，请重新登陆！"),
    CERR_SMS_CODE(403, "短信验证码错误！"),
    CERR_USER_PASSWORD(404, "用户名或密码错误！"),
    CERR_OLD_PASSWORD(405, "原密码不正确！"),
    CERR_SMS_ERR(406, "短信验证码请求上限！"),
    CERR_LOGIN_VERIFYCODE(407, "验证码不正确！"),
    CERR_USER_NOT_EXIST(408, "用户不存在！"),
    CERR_MOBILE_ILLEGAL(409, "手机号格式不正确！"),
    CERR_SMS_MAX(410, "您今天已经用完5次获取验证码机会，请明天再试！"),
    CERR_SMS_INVALID(411, "验证码失效！"),
    CERR_REPEAT_PASSWORD(412, "两次输入的新密码不一致！"),
    CERR_PERMISSION_DENIED(413, "对不起，您没有权限！"),
    CERR_NEW_OLD_INFO_ERR(414, "新 旧信息验证错误！"),
    CERR_SMS_TIME_ERR(415, "秒后重新获取"),
    CERR_INFO_IMPORT_ERR(416, "信息录入出错，录入失败"),
    CERR_USER_EXIST(417, "用户已经注册！"),
    CERR_CONS_NOT_EXIST(418, "商户或电表错误！"),
    CERR_MONEY_EXIST(419, "购电金额超限制！"),
    CERR_NOT_PERMISSION(420, "用户无权限访问！"),
    CERR_USER_NOT_REGISTER(418, "用户没有注册！"),
    CERR_USER_FIRST_BUY_ORDER(419, "初次购电请大于%s元用于扣除电表预付款！"),




    CERR_CONS_PUSH_ERR(1001, "接受数据处理失败！"),
    ;




    public int code;
    public String msg;

    ResultDef(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
