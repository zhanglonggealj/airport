package com.esop.airport.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_day_freeze")
public class TDayFreeze extends BaseModel{

  private static final long serialVersionUID = 8606885122951571786L;

  @Id
  private Long id;
  private Long batchNo;
  private String consNo;
  private Long meterId;
  private java.util.Date dataDate;
  private String orgNo;
  private java.util.Date colTime;
  private BigDecimal papR;
  private BigDecimal papR1;
  private BigDecimal papR2;
  private BigDecimal papR3;
  private BigDecimal papR4;
  private Integer dataResFlag;
  private BigDecimal crc;


  @Column(insertable = false)
  private Long status;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp updateTime;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;


  @Transient
  private String consName;
  @Transient
  private String consAddress;


  @Override
  public String toString() {
    return "TDayFreeze{" +
            "id=" + id +
            ", batchNo=" + batchNo +
            ", consNo='" + consNo + '\'' +
            ", meterId=" + meterId +
            ", dataDate=" + dataDate +
            ", orgNo='" + orgNo + '\'' +
            ", colTime=" + colTime +
            ", papR=" + papR +
            ", papR1=" + papR1 +
            ", papR2=" + papR2 +
            ", papR3=" + papR3 +
            ", papR4=" + papR4 +
            ", dataResFlag=" + dataResFlag +
            ", crc=" + crc +
            ", status=" + status +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
            ", consName='" + consName + '\'' +
            ", consAddress='" + consAddress + '\'' +
            '}';
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

  public java.util.Date getColTime() {
    return colTime;
  }

  public void setColTime(java.util.Date colTime) {
    this.colTime = colTime;
  }

  public BigDecimal getPapR() {
    return papR;
  }

  public void setPapR(BigDecimal papR) {
    this.papR = papR;
  }

  public BigDecimal getPapR1() {
    return papR1;
  }

  public void setPapR1(BigDecimal papR1) {
    this.papR1 = papR1;
  }

  public BigDecimal getPapR2() {
    return papR2;
  }

  public void setPapR2(BigDecimal papR2) {
    this.papR2 = papR2;
  }

  public BigDecimal getPapR3() {
    return papR3;
  }

  public void setPapR3(BigDecimal papR3) {
    this.papR3 = papR3;
  }

  public BigDecimal getPapR4() {
    return papR4;
  }

  public void setPapR4(BigDecimal papR4) {
    this.papR4 = papR4;
  }

  public Integer getDataResFlag() {
    return dataResFlag;
  }

  public void setDataResFlag(Integer dataResFlag) {
    this.dataResFlag = dataResFlag;
  }

  public BigDecimal getCrc() {
    return crc;
  }

  public void setCrc(BigDecimal crc) {
    this.crc = crc;
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
