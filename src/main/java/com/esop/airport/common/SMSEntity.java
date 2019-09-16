package com.esop.airport.common;

import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-28 08:52
 **/
public class SMSEntity {


    /**
     * ext :
     * extend :
     * sig : ecab4881ee80ad3d76bb1da68387428ca752eb885e52621a3129dcf4d9bc4fd4
     * tpl_id : 19
     * sign : 腾讯云
     * tel : {"mobile":"13788888888","nationcode":"86"}
     * time : 1457336869
     * params : ["验证码","1234","4"]
     */
    private String ext;
    private String extend;
    private String sig;
    private int tpl_id;
    private String sign;
    private TelEntity tel;
    private int time;
    private List<String> params;

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public void setTpl_id(int tpl_id) {
        this.tpl_id = tpl_id;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setTel(TelEntity tel) {
        this.tel = tel;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public String getExt() {
        return ext;
    }

    public String getExtend() {
        return extend;
    }

    public String getSig() {
        return sig;
    }

    public int getTpl_id() {
        return tpl_id;
    }

    public String getSign() {
        return sign;
    }

    public TelEntity getTel() {
        return tel;
    }

    public int getTime() {
        return time;
    }

    public List<String> getParams() {
        return params;
    }

    public static class TelEntity {
        /**
         * mobile : 13788888888
         * nationcode : 86
         */
        private String mobile;
        private String nationcode;

        public TelEntity(String mobile, String nationcode) {
            this.mobile = mobile;
            this.nationcode = nationcode;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setNationcode(String nationcode) {
            this.nationcode = nationcode;
        }

        public String getMobile() {
            return mobile;
        }

        public String getNationcode() {
            return nationcode;
        }
    }
}
