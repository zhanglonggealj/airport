package com.esop.airport.domain.middle.smodel;


import com.esop.airport.domain.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_day_electricity_freeze")
public class TDayElectricityFreeze extends BaseModel {

  private static final Long serialVersionUID = 1590329030006155543L;
  
  @Id
  private Long id;
  private Long batchNo;
  private String consNo;
  private Long meterId;
  private Date dataDate;
  private String orgNo;
  private Date colTime;
  private BigDecimal papR;
  private BigDecimal papR1;
  private BigDecimal papR2;
  private BigDecimal papR3;
  private BigDecimal papR4;
  private BigDecimal papR5;
  private BigDecimal papR6;
  private BigDecimal papR7;
  private BigDecimal papR8;
  private BigDecimal papR9;
  private BigDecimal papR10;
  private BigDecimal papR11;
  private BigDecimal papR12;
  private BigDecimal papR13;
  private BigDecimal prpR;
  private BigDecimal prpR1;
  private BigDecimal prpR2;
  private BigDecimal prpR3;
  private BigDecimal prpR4;
  private BigDecimal prpR5;
  private BigDecimal prpR6;
  private BigDecimal prpR7;
  private BigDecimal prpR8;
  private BigDecimal prpR9;
  private BigDecimal prpR10;
  private BigDecimal prpR11;
  private BigDecimal prpR12;
  private BigDecimal prpR13;
  private BigDecimal prpR14;
  private BigDecimal rp1R;
  private BigDecimal rp2R;
  private BigDecimal rp3R;
  private BigDecimal rp4R;
  private Integer dataResFlag;
  private BigDecimal crc;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "TDayElectricityFreeze{" +
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
            ", papR5=" + papR5 +
            ", papR6=" + papR6 +
            ", papR7=" + papR7 +
            ", papR8=" + papR8 +
            ", papR9=" + papR9 +
            ", papR10=" + papR10 +
            ", papR11=" + papR11 +
            ", papR12=" + papR12 +
            ", papR13=" + papR13 +
            ", prpR=" + prpR +
            ", prpR1=" + prpR1 +
            ", prpR2=" + prpR2 +
            ", prpR3=" + prpR3 +
            ", prpR4=" + prpR4 +
            ", prpR5=" + prpR5 +
            ", prpR6=" + prpR6 +
            ", prpR7=" + prpR7 +
            ", prpR8=" + prpR8 +
            ", prpR9=" + prpR9 +
            ", prpR10=" + prpR10 +
            ", prpR11=" + prpR11 +
            ", prpR12=" + prpR12 +
            ", prpR13=" + prpR13 +
            ", prpR14=" + prpR14 +
            ", rp1R=" + rp1R +
            ", rp2R=" + rp2R +
            ", rp3R=" + rp3R +
            ", rp4R=" + rp4R +
            ", dataResFlag=" + dataResFlag +
            ", crc=" + crc +
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

  public BigDecimal getPapR5() {
    return papR5;
  }

  public void setPapR5(BigDecimal papR5) {
    this.papR5 = papR5;
  }

  public BigDecimal getPapR6() {
    return papR6;
  }

  public void setPapR6(BigDecimal papR6) {
    this.papR6 = papR6;
  }

  public BigDecimal getPapR7() {
    return papR7;
  }

  public void setPapR7(BigDecimal papR7) {
    this.papR7 = papR7;
  }

  public BigDecimal getPapR8() {
    return papR8;
  }

  public void setPapR8(BigDecimal papR8) {
    this.papR8 = papR8;
  }

  public BigDecimal getPapR9() {
    return papR9;
  }

  public void setPapR9(BigDecimal papR9) {
    this.papR9 = papR9;
  }

  public BigDecimal getPapR10() {
    return papR10;
  }

  public void setPapR10(BigDecimal papR10) {
    this.papR10 = papR10;
  }

  public BigDecimal getPapR11() {
    return papR11;
  }

  public void setPapR11(BigDecimal papR11) {
    this.papR11 = papR11;
  }

  public BigDecimal getPapR12() {
    return papR12;
  }

  public void setPapR12(BigDecimal papR12) {
    this.papR12 = papR12;
  }

  public BigDecimal getPapR13() {
    return papR13;
  }

  public void setPapR13(BigDecimal papR13) {
    this.papR13 = papR13;
  }

  public BigDecimal getPrpR() {
    return prpR;
  }

  public void setPrpR(BigDecimal prpR) {
    this.prpR = prpR;
  }

  public BigDecimal getPrpR1() {
    return prpR1;
  }

  public void setPrpR1(BigDecimal prpR1) {
    this.prpR1 = prpR1;
  }

  public BigDecimal getPrpR2() {
    return prpR2;
  }

  public void setPrpR2(BigDecimal prpR2) {
    this.prpR2 = prpR2;
  }

  public BigDecimal getPrpR3() {
    return prpR3;
  }

  public void setPrpR3(BigDecimal prpR3) {
    this.prpR3 = prpR3;
  }

  public BigDecimal getPrpR4() {
    return prpR4;
  }

  public void setPrpR4(BigDecimal prpR4) {
    this.prpR4 = prpR4;
  }

  public BigDecimal getPrpR5() {
    return prpR5;
  }

  public void setPrpR5(BigDecimal prpR5) {
    this.prpR5 = prpR5;
  }

  public BigDecimal getPrpR6() {
    return prpR6;
  }

  public void setPrpR6(BigDecimal prpR6) {
    this.prpR6 = prpR6;
  }

  public BigDecimal getPrpR7() {
    return prpR7;
  }

  public void setPrpR7(BigDecimal prpR7) {
    this.prpR7 = prpR7;
  }

  public BigDecimal getPrpR8() {
    return prpR8;
  }

  public void setPrpR8(BigDecimal prpR8) {
    this.prpR8 = prpR8;
  }

  public BigDecimal getPrpR9() {
    return prpR9;
  }

  public void setPrpR9(BigDecimal prpR9) {
    this.prpR9 = prpR9;
  }

  public BigDecimal getPrpR10() {
    return prpR10;
  }

  public void setPrpR10(BigDecimal prpR10) {
    this.prpR10 = prpR10;
  }

  public BigDecimal getPrpR11() {
    return prpR11;
  }

  public void setPrpR11(BigDecimal prpR11) {
    this.prpR11 = prpR11;
  }

  public BigDecimal getPrpR12() {
    return prpR12;
  }

  public void setPrpR12(BigDecimal prpR12) {
    this.prpR12 = prpR12;
  }

  public BigDecimal getPrpR13() {
    return prpR13;
  }

  public void setPrpR13(BigDecimal prpR13) {
    this.prpR13 = prpR13;
  }

  public BigDecimal getPrpR14() {
    return prpR14;
  }

  public void setPrpR14(BigDecimal prpR14) {
    this.prpR14 = prpR14;
  }

  public BigDecimal getRp1R() {
    return rp1R;
  }

  public void setRp1R(BigDecimal rp1R) {
    this.rp1R = rp1R;
  }

  public BigDecimal getRp2R() {
    return rp2R;
  }

  public void setRp2R(BigDecimal rp2R) {
    this.rp2R = rp2R;
  }

  public BigDecimal getRp3R() {
    return rp3R;
  }

  public void setRp3R(BigDecimal rp3R) {
    this.rp3R = rp3R;
  }

  public BigDecimal getRp4R() {
    return rp4R;
  }

  public void setRp4R(BigDecimal rp4R) {
    this.rp4R = rp4R;
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

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }
}
