package com.esop.airport.api.third.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-13 17:27
 **/
public class AddCons {

    /**
     * meterMess : {"voltCode":"","tFactor":"10.22","remark":"","consNo":"","prepayFlag":"","typeCode":"","manufacturer":"","ratedCurrent":"","madeDate":"2019-11-11 11:11:11","protocolCode":"","initMoney":1000,"carrierWaveId":1328973265203,"meterId":16,"instLoc":"","commAddr":"通讯地址","meterCode":""}
     * consMess : {"voltCode":"","tariff_name":"电价名称","lineId":11,"consName":"","consAddress":"","buildDate":"2019-11-1 10:10:10","consNo":"","contractCap":11.21,"consType":"","orgNo":"","mobilePhone":"17333336638","tgId":"","tariffId":16}
     */
    @Valid
    @NotNull(message = "meterMess 不能为空")
    private MeterMessEntity meterMess;
    @Valid
    @NotNull(message = "consMess 不能为空")
    private ConsMessEntity consMess;

    @Override
    public String toString() {
        return "AddCons{" +
                "meterMess=" + meterMess +
                ", consMess=" + consMess +
                '}';
    }

    public void setMeterMess(MeterMessEntity meterMess) {
        this.meterMess = meterMess;
    }

    public void setConsMess(ConsMessEntity consMess) {
        this.consMess = consMess;
    }

    public MeterMessEntity getMeterMess() {
        return meterMess;
    }

    public ConsMessEntity getConsMess() {
        return consMess;
    }

    public static class MeterMessEntity {
        /**
         * voltCode :
         * tFactor : 10.22
         * remark :
         * consNo :
         * prepayFlag :
         * typeCode :
         * manufacturer :
         * ratedCurrent :
         * madeDate : 2019-11-11 11:11:11
         * protocolCode :
         * initMoney : 1000
         * carrierWaveId : 1328973265203
         * meterId : 16
         * instLoc :
         * commAddr : 通讯地址
         * meterCode :
         */
        private String voltCode;
        @NotBlank(message = "tFactor 不能为空")
        private String tFactor;
        private String remark;
//        @NotBlank(message = "consNo 不能为空")
        private String consNo;
        @NotBlank(message = "prepayFlag 不能为空")
        private String prepayFlag;
        @NotBlank(message = "typeCode 不能为空")
        private String typeCode;
        @NotBlank(message = "manufacturer 不能为空")
        private String manufacturer;
        private String ratedCurrent;
        private String madeDate;
        private String protocolCode;
        private Integer initMoney;
        private  String wiringMode;
        private Long carrierWaveId;
        @NotNull(message = "meterId 不能为空")
        private Long meterId;
        @NotNull(message = "instLoc 不能为空")
        private String instLoc;
        private String commAddr;
        @NotBlank(message = "meterCode 不能为空")
        @Pattern(regexp = "(007|008|009)", message = "meterCode err")
        private String meterCode;
        @NotBlank(message = "meterMadeNo 不能为空")
        private String meterMadeNo;
        @NotBlank(message = "orgNo 不能为空")
        private String orgNo;

        private String commMode;

        @Override
        public String toString() {
            return "MeterMessEntity{" +
                    "voltCode='" + voltCode + '\'' +
                    ", tFactor='" + tFactor + '\'' +
                    ", remark='" + remark + '\'' +
                    ", consNo='" + consNo + '\'' +
                    ", prepayFlag='" + prepayFlag + '\'' +
                    ", typeCode='" + typeCode + '\'' +
                    ", manufacturer='" + manufacturer + '\'' +
                    ", ratedCurrent='" + ratedCurrent + '\'' +
                    ", madeDate='" + madeDate + '\'' +
                    ", protocolCode='" + protocolCode + '\'' +
                    ", initMoney=" + initMoney +
                    ", wiringMode='" + wiringMode + '\'' +
                    ", carrierWaveId=" + carrierWaveId +
                    ", meterId=" + meterId +
                    ", instLoc='" + instLoc + '\'' +
                    ", commAddr='" + commAddr + '\'' +
                    ", meterCode='" + meterCode + '\'' +
                    ", meterMadeNo='" + meterMadeNo + '\'' +
                    ", orgNo='" + orgNo + '\'' +
                    ", commMode='" + commMode + '\'' +
                    '}';
        }

        public String getWiringMode() {
            return wiringMode;
        }

        public void setWiringMode(String wiringMode) {
            this.wiringMode = wiringMode;
        }

        public String getCommMode() {
            return commMode;
        }

        public void setCommMode(String commMode) {
            this.commMode = commMode;
        }

        public String getVoltCode() {
            return voltCode;
        }

        public void setVoltCode(String voltCode) {
            this.voltCode = voltCode;
        }

        public String gettFactor() {
            return tFactor;
        }

        public void settFactor(String tFactor) {
            this.tFactor = tFactor;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getConsNo() {
            return consNo;
        }

        public void setConsNo(String consNo) {
            this.consNo = consNo;
        }

        public String getPrepayFlag() {
            return prepayFlag;
        }

        public void setPrepayFlag(String prepayFlag) {
            this.prepayFlag = prepayFlag;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getRatedCurrent() {
            return ratedCurrent;
        }

        public void setRatedCurrent(String ratedCurrent) {
            this.ratedCurrent = ratedCurrent;
        }

        public String getMadeDate() {
            return madeDate;
        }

        public void setMadeDate(String madeDate) {
            this.madeDate = madeDate;
        }

        public String getProtocolCode() {
            return protocolCode;
        }

        public void setProtocolCode(String protocolCode) {
            this.protocolCode = protocolCode;
        }

        public Integer getInitMoney() {
            return initMoney;
        }

        public void setInitMoney(Integer initMoney) {
            this.initMoney = initMoney;
        }

        public Long getCarrierWaveId() {
            return carrierWaveId;
        }

        public void setCarrierWaveId(Long carrierWaveId) {
            this.carrierWaveId = carrierWaveId;
        }

        public Long getMeterId() {
            return meterId;
        }

        public void setMeterId(Long meterId) {
            this.meterId = meterId;
        }

        public String getInstLoc() {
            return instLoc;
        }

        public void setInstLoc(String instLoc) {
            this.instLoc = instLoc;
        }

        public String getCommAddr() {
            return commAddr;
        }

        public void setCommAddr(String commAddr) {
            this.commAddr = commAddr;
        }

        public String getMeterCode() {
            return meterCode;
        }

        public void setMeterCode(String meterCode) {
            this.meterCode = meterCode;
        }

        public String getMeterMadeNo() {
            return meterMadeNo;
        }

        public void setMeterMadeNo(String meterMadeNo) {
            this.meterMadeNo = meterMadeNo;
        }

        public String getOrgNo() {
            return orgNo;
        }

        public void setOrgNo(String orgNo) {
            this.orgNo = orgNo;
        }
    }

    public static class ConsMessEntity {
        /**
         * voltCode :
         * tariff_name : 电价名称
         * lineId : 11
         * consName :
         * consAddress :
         * buildDate : 2019-11-1 10:10:10
         * consNo :
         * contractCap : 11.21
         * consType :
         * orgNo :
         * mobilePhone : 17333336638
         * tgId :
         * tariffId : 16
         */
        private String voltCode;
        private String tariffName;
        private Long lineId;
        @NotBlank(message = "consName不能为空")
        private String consName;
        @NotBlank(message = "consAddress不能为空")
        private String consAddress;
        @NotBlank(message = "buildDate不能为空")
        private String buildDate;
        @NotBlank(message = "consNo不能为空")
        private String consNo;
        private String contractCap;
        private String consType;
        @NotBlank(message = "orgNo不能为空")
        private String orgNo;
        /*@NotBlank(message = "mobilePhone不能为空")
        @Pattern(regexp = "^((19[0-9])|(17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$", message = "mobilePhone err")*/
        private String mobilePhone;
        private String tgId;
        @NotNull(message = "tariffId不能为空")
        private Long tariffId;
        private String remark;
        @NotBlank(message = "statusCode 不能为空")
        @Pattern(regexp = "[1-2]", message = "statusCode err")
        private String statusCode;

        @Override
        public String toString() {
            return "ConsMessEntity{" +
                    "voltCode='" + voltCode + '\'' +
                    ", tariffName='" + tariffName + '\'' +
                    ", lineId=" + lineId +
                    ", consName='" + consName + '\'' +
                    ", consAddress='" + consAddress + '\'' +
                    ", buildDate='" + buildDate + '\'' +
                    ", consNo='" + consNo + '\'' +
                    ", contractCap='" + contractCap + '\'' +
                    ", consType='" + consType + '\'' +
                    ", orgNo='" + orgNo + '\'' +
                    ", mobilePhone='" + mobilePhone + '\'' +
                    ", tgId=" + tgId +
                    ", tariffId=" + tariffId +
                    ", remark='" + remark + '\'' +
                    ", statusCode='" + statusCode + '\'' +
                    '}';
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getVoltCode() {
            return voltCode;
        }

        public void setVoltCode(String voltCode) {
            this.voltCode = voltCode;
        }

        public String getTariffName() {
            return tariffName;
        }

        public void setTariffName(String tariffName) {
            this.tariffName = tariffName;
        }

        public Long getLineId() {
            return lineId;
        }

        public void setLineId(Long lineId) {
            this.lineId = lineId;
        }

        public String getConsName() {
            return consName;
        }

        public void setConsName(String consName) {
            this.consName = consName;
        }

        public String getConsAddress() {
            return consAddress;
        }

        public void setConsAddress(String consAddress) {
            this.consAddress = consAddress;
        }

        public String getBuildDate() {
            return buildDate;
        }

        public void setBuildDate(String buildDate) {
            this.buildDate = buildDate;
        }

        public String getConsNo() {
            return consNo;
        }

        public void setConsNo(String consNo) {
            this.consNo = consNo;
        }

        public String getContractCap() {
            return contractCap;
        }

        public void setContractCap(String contractCap) {
            this.contractCap = contractCap;
        }

        public String getConsType() {
            return consType;
        }

        public void setConsType(String consType) {
            this.consType = consType;
        }

        public String getOrgNo() {
            return orgNo;
        }

        public void setOrgNo(String orgNo) {
            this.orgNo = orgNo;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getTgId() {
            return tgId;
        }

        public void setTgId(String tgId) {
            this.tgId = tgId;
        }

        public Long getTariffId() {
            return tariffId;
        }

        public void setTariffId(Long tariffId) {
            this.tariffId = tariffId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
