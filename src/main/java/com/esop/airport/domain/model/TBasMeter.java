package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Table(name = "t_bas_meter")
public class TBasMeter extends BaseModel {

    private static final long serialVersionUID = 751322126009742820L;

    @Id
    private Long id;
    private String consNo;
    private Long meterId;
    private String meterMadeNo;
    private String typeCode;
    private String instLoc;
    private BigDecimal tFactor;
    private String commAddr;
    private String protocolCode;
    private String commMode;
    private String wiringMode;
    private String manufacturer;
    private String madeDate;
    private String voltCode;
    private String ratedCurrent;
    private Long carrierWaveId;
    private String prepayFlag;
    private Integer initMoney;
    private BigDecimal carryMoney;
    private BigDecimal additionMoney;
    private String remark;
    private String meterCode;
    private Integer purchaseCount;
    private Integer usePrepayFlag;
    private Integer useCarryFlag;
    private Integer useAdditionFlag;

    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp updateTime;
    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp createTime;

    @Override
    public String toString() {
        return "TBasMeter{" +
                "id=" + id +
                ", consNo='" + consNo + '\'' +
                ", meterId=" + meterId +
                ", meterMadeNo='" + meterMadeNo + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", instLoc='" + instLoc + '\'' +
                ", tFactor=" + tFactor +
                ", commAddr='" + commAddr + '\'' +
                ", protocolCode='" + protocolCode + '\'' +
                ", commMode='" + commMode + '\'' +
                ", wiringMode='" + wiringMode + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", madeDate='" + madeDate + '\'' +
                ", voltCode='" + voltCode + '\'' +
                ", ratedCurrent='" + ratedCurrent + '\'' +
                ", carrierWaveId=" + carrierWaveId +
                ", prepayFlag='" + prepayFlag + '\'' +
                ", initMoney=" + initMoney +
                ", carryMoney=" + carryMoney +
                ", additionMoney=" + additionMoney +
                ", remark='" + remark + '\'' +
                ", meterCode='" + meterCode + '\'' +
                ", purchaseCount=" + purchaseCount +
                ", usePrepayFlag=" + usePrepayFlag +
                ", useCarryFlag=" + useCarryFlag +
                ", useAdditionFlag=" + useAdditionFlag +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }

    public String getWiringMode() {
        return wiringMode;
    }

    public void setWiringMode(String wiringMode) {
        this.wiringMode = wiringMode;
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

    public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public String getMeterMadeNo() {
        return meterMadeNo;
    }

    public void setMeterMadeNo(String meterMadeNo) {
        this.meterMadeNo = meterMadeNo;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getInstLoc() {
        return instLoc;
    }

    public void setInstLoc(String instLoc) {
        this.instLoc = instLoc;
    }

    public BigDecimal gettFactor() {
        return tFactor;
    }

    public void settFactor(BigDecimal tFactor) {
        this.tFactor = tFactor;
    }

    public String getCommAddr() {
        return commAddr;
    }

    public void setCommAddr(String commAddr) {
        this.commAddr = commAddr;
    }

    public String getProtocolCode() {
        return protocolCode;
    }

    public void setProtocolCode(String protocolCode) {
        this.protocolCode = protocolCode;
    }

    public String getCommMode() {
        return commMode;
    }

    public void setCommMode(String commMode) {
        this.commMode = commMode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(String madeDate) {
        this.madeDate = madeDate;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }

    public String getRatedCurrent() {
        return ratedCurrent;
    }

    public void setRatedCurrent(String ratedCurrent) {
        this.ratedCurrent = ratedCurrent;
    }

    public Long getCarrierWaveId() {
        return carrierWaveId;
    }

    public void setCarrierWaveId(Long carrierWaveId) {
        this.carrierWaveId = carrierWaveId;
    }

    public String getPrepayFlag() {
        return prepayFlag;
    }

    public void setPrepayFlag(String prepayFlag) {
        this.prepayFlag = prepayFlag;
    }

    public Integer getInitMoney() {
        return initMoney;
    }

    public void setInitMoney(Integer initMoney) {
        this.initMoney = initMoney;
    }

    public BigDecimal getCarryMoney() {
        return carryMoney;
    }

    public void setCarryMoney(BigDecimal carryMoney) {
        this.carryMoney = carryMoney;
    }

    public BigDecimal getAdditionMoney() {
        return additionMoney;
    }

    public void setAdditionMoney(BigDecimal additionMoney) {
        this.additionMoney = additionMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Integer getUsePrepayFlag() {
        return usePrepayFlag;
    }

    public void setUsePrepayFlag(Integer usePrepayFlag) {
        this.usePrepayFlag = usePrepayFlag;
    }

    public Integer getUseCarryFlag() {
        return useCarryFlag;
    }

    public void setUseCarryFlag(Integer useCarryFlag) {
        this.useCarryFlag = useCarryFlag;
    }

    public Integer getUseAdditionFlag() {
        return useAdditionFlag;
    }

    public void setUseAdditionFlag(Integer useAdditionFlag) {
        this.useAdditionFlag = useAdditionFlag;
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
