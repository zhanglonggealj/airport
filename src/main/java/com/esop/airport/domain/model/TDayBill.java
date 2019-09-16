package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_day_bill")
public class TDayBill extends BaseModel{

  private static final long serialVersionUID = 629763211708305697L;

  @Id
  private Long id;
  private String purchaseId;
  private String transactionId;
  private Date purchaseDate;
  private BigDecimal wxTotalFee;
  private String poundage;
  private String poundageRate;
  private String consNo;
  private Long meterId;
  private BigDecimal purchaseMoney;
  private BigDecimal additionMoney;
  private BigDecimal initMoney;
  private BigDecimal sendMoney;
  private BigDecimal abnormalPurchaseMoney;
  private BigDecimal abnormalAdditionMoney;
  private BigDecimal abnormalInitMoney;
  private BigDecimal abnormalSendMoney;
  private Date sendDate;
  private Long wxOk;
  private Long platformOk;
  private Long billStatus;

  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp updateTime;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "TDayBill{" +
            "id=" + id +
            ", purchaseId='" + purchaseId + '\'' +
            ", transactionId='" + transactionId + '\'' +
            ", purchaseDate=" + purchaseDate +
            ", wxTotalFee=" + wxTotalFee +
            ", poundage='" + poundage + '\'' +
            ", poundageRate='" + poundageRate + '\'' +
            ", consNo='" + consNo + '\'' +
            ", meterId=" + meterId +
            ", purchaseMoney=" + purchaseMoney +
            ", additionMoney=" + additionMoney +
            ", initMoney=" + initMoney +
            ", sendMoney=" + sendMoney +
            ", abnormalPurchaseMoney=" + abnormalPurchaseMoney +
            ", abnormalAdditionMoney=" + abnormalAdditionMoney +
            ", abnormalInitMoney=" + abnormalInitMoney +
            ", abnormalSendMoney=" + abnormalSendMoney +
            ", sendDate=" + sendDate +
            ", wxOk=" + wxOk +
            ", platformOk=" + platformOk +
            ", billStatus=" + billStatus +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(String purchaseId) {
    this.purchaseId = purchaseId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public String getPoundage() {
    return poundage;
  }

  public void setPoundage(String poundage) {
    this.poundage = poundage;
  }

  public String getPoundageRate() {
    return poundageRate;
  }

  public void setPoundageRate(String poundageRate) {
    this.poundageRate = poundageRate;
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

  public BigDecimal getAdditionMoney() {
    return additionMoney;
  }

  public void setAdditionMoney(BigDecimal additionMoney) {
    this.additionMoney = additionMoney;
  }

  public BigDecimal getInitMoney() {
    return initMoney;
  }

  public void setInitMoney(BigDecimal initMoney) {
    this.initMoney = initMoney;
  }

  public BigDecimal getSendMoney() {
    return sendMoney;
  }

  public void setSendMoney(BigDecimal sendMoney) {
    this.sendMoney = sendMoney;
  }

  public BigDecimal getAbnormalPurchaseMoney() {
    return abnormalPurchaseMoney;
  }

  public void setAbnormalPurchaseMoney(BigDecimal abnormalPurchaseMoney) {
    this.abnormalPurchaseMoney = abnormalPurchaseMoney;
  }

  public BigDecimal getAbnormalAdditionMoney() {
    return abnormalAdditionMoney;
  }

  public void setAbnormalAdditionMoney(BigDecimal abnormalAdditionMoney) {
    this.abnormalAdditionMoney = abnormalAdditionMoney;
  }

  public BigDecimal getAbnormalInitMoney() {
    return abnormalInitMoney;
  }

  public void setAbnormalInitMoney(BigDecimal abnormalInitMoney) {
    this.abnormalInitMoney = abnormalInitMoney;
  }

  public BigDecimal getAbnormalSendMoney() {
    return abnormalSendMoney;
  }

  public void setAbnormalSendMoney(BigDecimal abnormalSendMoney) {
    this.abnormalSendMoney = abnormalSendMoney;
  }

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  public Long getWxOk() {
    return wxOk;
  }

  public void setWxOk(Long wxOk) {
    this.wxOk = wxOk;
  }

  public Long getPlatformOk() {
    return platformOk;
  }

  public void setPlatformOk(Long platformOk) {
    this.platformOk = platformOk;
  }

  public Long getBillStatus() {
    return billStatus;
  }

  public void setBillStatus(Long billStatus) {
    this.billStatus = billStatus;
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

  public BigDecimal getWxTotalFee() {
    return wxTotalFee;
  }

  public void setWxTotalFee(BigDecimal wxTotalFee) {
    this.wxTotalFee = wxTotalFee;
  }

  public BigDecimal getPurchaseMoney() {
    return purchaseMoney;
  }

  public void setPurchaseMoney(BigDecimal purchaseMoney) {
    this.purchaseMoney = purchaseMoney;
  }
}
