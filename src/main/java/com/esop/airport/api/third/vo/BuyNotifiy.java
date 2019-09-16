package com.esop.airport.api.third.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-25 15:19
 **/
public class BuyNotifiy {

    /**
     * purchaseDate : 2019-11-11 11:11:11
     * purchaseCount : 1
     * issuedType : 1
     * chargeId : 微信支付订单号
     * meterId : 11
     * purchaseId : 购电业务单号
     * residueMoney : 11.11
     * consNo :
     * sendMoney : 11
     * sendTime : 2019-11-11 11:11:11
     */
    @NotBlank(message = "purchaseDate 不能为空")
    private String purchaseDate;
    @NotNull
    private Long purchaseCount;
    @NotNull
    private Integer issuedType;
//    @NotBlank(message = "chargeId 不能为空")
    private String chargeId;
    @NotNull
    private Long meterId;
    @NotBlank(message = "purchaseId 不能为空")
    private String purchaseId;
    @NotBlank(message = "residueMoney 不能为空")
    private String residueMoney;
    @NotNull
    private String consNo;
    @NotNull
    private Integer sendMoney;
    @NotBlank(message = "sendTime 不能为空")
    private String sendTime;

    @Override
    public String toString() {
        return "BuyNotifiy{" +
                "purchaseDate='" + purchaseDate + '\'' +
                ", purchaseCount=" + purchaseCount +
                ", issuedType='" + issuedType + '\'' +
                ", chargeId='" + chargeId + '\'' +
                ", meterId=" + meterId +
                ", purchaseId='" + purchaseId + '\'' +
                ", residueMoney='" + residueMoney + '\'' +
                ", consNo='" + consNo + '\'' +
                ", sendMoney=" + sendMoney +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Integer getIssuedType() {
        return issuedType;
    }

    public void setIssuedType(Integer issuedType) {
        this.issuedType = issuedType;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getResidueMoney() {
        return residueMoney;
    }

    public void setResidueMoney(String residueMoney) {
        this.residueMoney = residueMoney;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public Integer getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(Integer sendMoney) {
        this.sendMoney = sendMoney;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
