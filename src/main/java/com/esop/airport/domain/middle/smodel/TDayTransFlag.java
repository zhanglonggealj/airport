package com.esop.airport.domain.middle.smodel;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_day_trans_flag")
public class TDayTransFlag {

    @Id
    private Long id;
    private String dataItem;
    private Date dataDate;
    private Long batchNo;
    private Date inputTime;
    private Date readTime;
    private String readFlag;

    @Override
    public String toString() {
        return "TDayTransFlag{" +
                "id=" + id +
                ", dataItem='" + dataItem + '\'' +
                ", dataDate=" + dataDate +
                ", batchNo=" + batchNo +
                ", inputTime=" + inputTime +
                ", readTime=" + readTime +
                ", readFlag='" + readFlag + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataItem() {
        return dataItem;
    }

    public void setDataItem(String dataItem) {
        this.dataItem = dataItem;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }
}
