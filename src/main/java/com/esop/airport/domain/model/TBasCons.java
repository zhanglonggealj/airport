package com.esop.airport.domain.model;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_bas_cons")
public class TBasCons extends BaseModel implements Serializable {

    private static final long serialVersionUID = 6401256918289394751L;

    @Id
    private Long id;
    private String consNo;
    private String consName;
    private String consAddress;
    private String consType;
    private String orgNo;
    private Date buildDate;
    private Long tariffId;
    private String tariffName;
    private BigDecimal contractCap;
    private String voltCode;
    private String tgId;
    private Long lineId;
    private String mobilePhone;
    private String remark;
    private String statusCode;
    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp updateTime;
    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp createTime;


    @Transient
    private Long meterId; //一对一，通过注解@Transient 保证不参与表映射
    @Transient
    private String meterMadeNo;//电表出厂编号
    //meterNo
    @Override
    public String toString() {
        return "TBasCons{" +
                "id=" + id +
                ", consNo='" + consNo + '\'' +
                ", consName='" + consName + '\'' +
                ", consAddress='" + consAddress + '\'' +
                ", consType='" + consType + '\'' +
                ", orgNo='" + orgNo + '\'' +
                ", buildDate=" + buildDate +
                ", tariffId=" + tariffId +
                ", tariffName='" + tariffName + '\'' +
                ", contractCap=" + contractCap +
                ", voltCode='" + voltCode + '\'' +
                ", tgId='" + tgId + '\'' +
                ", lineId=" + lineId +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", remark='" + remark + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", meterId=" + meterId +
                ", meterMadeNo='" + meterMadeNo + '\'' +
                '}';
    }

    public String getMeterMadeNo() {
        return meterMadeNo;
    }

    public void setMeterMadeNo(String meterMadeNo) {
        this.meterMadeNo = meterMadeNo;
    }

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
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

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public BigDecimal getContractCap() {
        return contractCap;
    }

    public void setContractCap(BigDecimal contractCap) {
        this.contractCap = contractCap;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }

    public String getTgId() {
        return tgId;
    }

    public void setTgId(String tgId) {
        this.tgId = tgId;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
