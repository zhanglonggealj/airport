package com.esop.airport.domain.middle.smodel;


import com.esop.airport.domain.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_day_bill")
public class STDayBill extends BaseModel {

  private static final long serialVersionUID = -1262749165249597743L;

  @Id
  private Long id;
  private Long batchNo;
  private String purchaseId;
  private Date purchaseDate;
  private String consNo;
  private Long meterId;
  private String chargeId;
  private Long purchaseCount;
  private BigDecimal purchaseMoney;
  private BigDecimal additionMoney;
  private BigDecimal initMoney;
  private BigDecimal sendMoney;
  private Date sendDate;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "STDayBill{" +
            "id=" + id +
            ", batchNo=" + batchNo +
            ", purchaseId='" + purchaseId + '\'' +
            ", purchaseDate=" + purchaseDate +
            ", consNo='" + consNo + '\'' +
            ", meterId=" + meterId +
            ", chargeId='" + chargeId + '\'' +
            ", purchaseCount=" + purchaseCount +
            ", purchaseMoney=" + purchaseMoney +
            ", additionMoney=" + additionMoney +
            ", initMoney=" + initMoney +
            ", sendMoney=" + sendMoney +
            ", sendDate=" + sendDate +
            ", createTime=" + createTime +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(Long batchNo) {
    this.batchNo = batchNo;
  }

  public String getPurchaseId() {
    return purchaseId;
  }

  public void setPurchaseId(String purchaseId) {
    this.purchaseId = purchaseId;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
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

  public String getChargeId() {
    return chargeId;
  }

  public void setChargeId(String chargeId) {
    this.chargeId = chargeId;
  }

  public Long getPurchaseCount() {
    return purchaseCount;
  }

  public void setPurchaseCount(Long purchaseCount) {
    this.purchaseCount = purchaseCount;
  }

  public BigDecimal getPurchaseMoney() {
    return purchaseMoney;
  }

  public void setPurchaseMoney(BigDecimal purchaseMoney) {
    this.purchaseMoney = purchaseMoney;
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

  public Date getSendDate() {
    return sendDate;
  }

  public void setSendDate(Date sendDate) {
    this.sendDate = sendDate;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }
}
