package com.esop.airport.api.third.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-17 09:44
 **/
public class ChangeMeter {


    /**
     * additionMoney : 100.11
     * initMoney : 100
     * newMeterMess : {"voltCode":"","tFactor":"10.22","remark":"","consNo":"","prepayFlag":"","typeCode":"","manufacturer":"","ratedCurrent":"","madeDate":"2019-11-11 11:11:11","protocolCode":"","initMoney":1000,"meterId":16,"instLoc":"","commAddr":"通讯地址","meterCode":"","carrierWave_id":1328973265203}
     * carryMoney : 100.11
     * oldMeterId : 16
     * consNo :
     */
    @NotBlank(message = "additionMoney 不能为空")
    private String additionMoney;
    @Valid
    @NotNull(message = "newMeterMess 不能为空")
    private AddCons.MeterMessEntity newMeterMess;
    @NotBlank(message = "carryMoney 不能为空")
    private String carryMoney;
    @NotNull(message = "oldMeterId 不能为空")
    private Long oldMeterId;
    @NotBlank(message = "consNo 不能为空")
    private String consNo;

    @Override
    public String toString() {
        return "ChangeMeter{" +
                "additionMoney='" + additionMoney + '\'' +
                ", newMeterMess=" + newMeterMess +
                ", carryMoney='" + carryMoney + '\'' +
                ", oldMeterId=" + oldMeterId +
                ", consNo='" + consNo + '\'' +
                '}';
    }

    public String getAdditionMoney() {
        return additionMoney;
    }

    public void setAdditionMoney(String additionMoney) {
        this.additionMoney = additionMoney;
    }

    public String getCarryMoney() {
        return carryMoney;
    }

    public void setCarryMoney(String carryMoney) {
        this.carryMoney = carryMoney;
    }

    public Long getOldMeterId() {
        return oldMeterId;
    }

    public void setOldMeterId(Long oldMeterId) {
        this.oldMeterId = oldMeterId;
    }

    public String getConsNo() {
        return consNo;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public AddCons.MeterMessEntity getNewMeterMess() {
        return newMeterMess;
    }

    public void setNewMeterMess(AddCons.MeterMessEntity newMeterMess) {
        this.newMeterMess = newMeterMess;
    }
}
