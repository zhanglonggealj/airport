package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_day_residual_money")
public class TDayResidualMoney extends BaseModel{

  private static final long serialVersionUID = 2181590014648692135L;

  @Id
  private Long id;
  private Long batchNo;
  private String consNo;
  private Long meterId;
  private Date dataDate;
  private String orgNo;
  private Date colTime;
  private BigDecimal remainEnegy;
  private BigDecimal remainMoney;
  private BigDecimal alarmEnegy;
  private BigDecimal failEnegy;
  private BigDecimal sumEnegy;
  private BigDecimal sumMoney;
  private Long buyNum;
  private BigDecimal overdrLimit;
  private BigDecimal overdrEnegy;

  @Column(insertable = false)
  private Long status;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp updateTime;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "TDayResidualMoney{" +
            "id=" + id +
            ", batchNo=" + batchNo +
            ", consNo='" + consNo + '\'' +
            ", meterId=" + meterId +
            ", dataDate=" + dataDate +
            ", orgNo='" + orgNo + '\'' +
            ", colTime=" + colTime +
            ", remainEnegy=" + remainEnegy +
            ", remainMoney=" + remainMoney +
            ", alarmEnegy=" + alarmEnegy +
            ", failEnegy=" + failEnegy +
            ", sumEnegy=" + sumEnegy +
            ", sumMoney=" + sumMoney +
            ", buyNum=" + buyNum +
            ", overdrLimit=" + overdrLimit +
            ", overdrEnegy=" + overdrEnegy +
            ", status=" + status +
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

  public Long getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(Long batchNo) {
    this.batchNo = batchNo;
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

  public Date getDataDate() {
    return dataDate;
  }

  public void setDataDate(Date dataDate) {
    this.dataDate = dataDate;
  }

  public String getOrgNo() {
    return orgNo;
  }

  public void setOrgNo(String orgNo) {
    this.orgNo = orgNo;
  }

  public Date getColTime() {
    return colTime;
  }

  public void setColTime(Date colTime) {
    this.colTime = colTime;
  }

  public BigDecimal getRemainEnegy() {
    return remainEnegy;
  }

  public void setRemainEnegy(BigDecimal remainEnegy) {
    this.remainEnegy = remainEnegy;
  }

  public BigDecimal getRemainMoney() {
    return remainMoney;
  }

  public void setRemainMoney(BigDecimal remainMoney) {
    this.remainMoney = remainMoney;
  }

  public BigDecimal getAlarmEnegy() {
    return alarmEnegy;
  }

  public void setAlarmEnegy(BigDecimal alarmEnegy) {
    this.alarmEnegy = alarmEnegy;
  }

  public BigDecimal getFailEnegy() {
    return failEnegy;
  }

  public void setFailEnegy(BigDecimal failEnegy) {
    this.failEnegy = failEnegy;
  }

  public BigDecimal getSumEnegy() {
    return sumEnegy;
  }

  public void setSumEnegy(BigDecimal sumEnegy) {
    this.sumEnegy = sumEnegy;
  }

  public BigDecimal getSumMoney() {
    return sumMoney;
  }

  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }

  public Long getBuyNum() {
    return buyNum;
  }

  public void setBuyNum(Long buyNum) {
    this.buyNum = buyNum;
  }

  public BigDecimal getOverdrLimit() {
    return overdrLimit;
  }

  public void setOverdrLimit(BigDecimal overdrLimit) {
    this.overdrLimit = overdrLimit;
  }

  public BigDecimal getOverdrEnegy() {
    return overdrEnegy;
  }

  public void setOverdrEnegy(BigDecimal overdrEnegy) {
    this.overdrEnegy = overdrEnegy;
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
}
