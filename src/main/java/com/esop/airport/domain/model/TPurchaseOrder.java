package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_purchase_order")
public class TPurchaseOrder extends BaseModel {

    private static final long serialVersionUID = -5485755935534718847L;

    @Id
    private Long id;
    private String orderId;
    private Long payType;
    private Long totalFee;
    private Long userId;
    private String consNo;
    private Long meterId;
    private String openid;
    private String transactionId;
    private Long type;
    private Date issuedTime;
    private Date sendTime;
    private Long issuedRun;
    private Long issuedIsrun;
    private Long type2;
    private String errMsg;
    private Date notifyTime;
    private Integer issuedType;
    private BigDecimal sendMoney;
    private BigDecimal additionMoney;
    private BigDecimal initMoney;

    @Column(insertable = false)
    private Long status;
    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp updateTime;
    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp createTime;

    @Transient
    private BigDecimal totalMoney;
    @Transient
    private String consName;
    @Transient
    private String consAddress;
    @Transient
    private String meterMadeNo;

    @Override
    public String toString() {
        return "TPurchaseOrder{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", payType=" + payType +
                ", totalFee=" + totalFee +
                ", userId=" + userId +
                ", consNo='" + consNo + '\'' +
                ", meterId=" + meterId +
                ", openid='" + openid + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", type=" + type +
                ", issuedTime=" + issuedTime +
                ", sendTime=" + sendTime +
                ", issuedRun=" + issuedRun +
                ", issuedIsrun=" + issuedIsrun +
                ", type2=" + type2 +
                ", errMsg='" + errMsg + '\'' +
                ", notifyTime=" + notifyTime +
                ", issuedType=" + issuedType +
                ", sendMoney=" + sendMoney +
                ", additionMoney=" + additionMoney +
                ", initMoney=" + initMoney +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", totalMoney=" + totalMoney +
                ", consName='" + consName + '\'' +
                ", consAddress='" + consAddress + '\'' +
                ", meterMadeNo='" + meterMadeNo + '\'' +
                '}';
    }

    public String getMeterMadeNo() {
        return meterMadeNo;
    }

    public void setMeterMadeNo(String meterMadeNo) {
        this.meterMadeNo = meterMadeNo;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
    }

    public BigDecimal getAdditionMoney() {
        return additionMoney;
    }

    public void setAdditionMoney(BigDecimal additionMoney) {
        this.additionMoney = additionMoney;
    }

    public Integer getIssuedType() {
        return issuedType;
    }

    public void setIssuedType(Integer issuedType) {
        this.issuedType = issuedType;
    }

    public String getConsAddress() {
        return consAddress;
    }

    public void setConsAddress(String consAddress) {
        this.consAddress = consAddress;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Date issuedTime) {
        this.issuedTime = issuedTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getIssuedRun() {
        return issuedRun;
    }

    public void setIssuedRun(Long issuedRun) {
        this.issuedRun = issuedRun;
    }

    public Long getIssuedIsrun() {
        return issuedIsrun;
    }

    public void setIssuedIsrun(Long issuedIsrun) {
        this.issuedIsrun = issuedIsrun;
    }

    public Long getType2() {
        return type2;
    }

    public void setType2(Long type2) {
        this.type2 = type2;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(BigDecimal initMoney) {
        this.initMoney = initMoney;
    }
}
