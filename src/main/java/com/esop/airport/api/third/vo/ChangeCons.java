package com.esop.airport.api.third.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-06-17 10:35
 **/
public class ChangeCons {

    /**
     * newcons : {"voltCode":"","lineId":11,"consName":"","consAddress":"","buildDate":"2019-11-1 10:10:10","consNo":"","contractCap":11.21,"consType":"","orgNo":"","mobilePhone":"17333336638","tariffName":"电价名称","tgId":"","tariffId":16}
     * consNo : consNo
     */
    @Valid
    @NotNull(message = "newcons 不能为null")
    private AddCons.ConsMessEntity newcons;
    @NotBlank(message = "consNo 不能为null")
    private String consNo;

    public void setNewcons(AddCons.ConsMessEntity newcons) {
        this.newcons = newcons;
    }

    public void setConsNo(String consNo) {
        this.consNo = consNo;
    }

    public AddCons.ConsMessEntity getNewcons() {
        return newcons;
    }

    public String getConsNo() {
        return consNo;
    }

    @Override
    public String toString() {
        return "ChangeCons{" +
                "newcons=" + newcons +
                ", consNo='" + consNo + '\'' +
                '}';
    }
}
