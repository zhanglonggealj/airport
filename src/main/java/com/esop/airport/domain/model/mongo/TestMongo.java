package com.esop.airport.domain.model.mongo;

import com.esop.airport.domain.model.BaseModel;
//import org.springframework.data.mongodb.core.index.CompoundIndex;
//import org.springframework.data.mongodb.core.index.CompoundIndexes;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-07-31 11:35
 **/
//@Document
//@CompoundIndexes(
//        {
//                @CompoundIndex(name = "test_2_companyid_hisdate_measurecode_meterid",def = "{'companyid':-1,'hisdate':-1,'measurecode':-1,'meterid':-1}")
//        })
public class TestMongo extends BaseModel implements Serializable {
    private static final long serialVersionUID = -1741437063971126026L;


    /**
     * companyid : 123
     * data : [{"value":200,"point":1,"timestamp":1564544076}]
     * measurecode : 1234
     * hisdate : 123
     */
    private String companyid;
    private String measurecode;
    private String hisdate;
    private String meterid;
    private List<DataEntity> data;


    @Override
    public String toString() {
        return "TestMongo{" +
                "companyid='" + companyid + '\'' +
                ", measurecode='" + measurecode + '\'' +
                ", hisdate='" + hisdate + '\'' +
                ", meterid='" + meterid + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMeterid() {
        return meterid;
    }

    public void setMeterid(String meterid) {
        this.meterid = meterid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setMeasurecode(String measurecode) {
        this.measurecode = measurecode;
    }

    public void setHisdate(String hisdate) {
        this.hisdate = hisdate;
    }

    public String getCompanyid() {
        return companyid;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getMeasurecode() {
        return measurecode;
    }

    public String getHisdate() {
        return hisdate;
    }

    public static class DataEntity {
        /**
         * value : 200
         * point : 1
         * timestamp : 1564544076
         */
        private int value;
        private int point;
        private int timestamp;

        public void setValue(int value) {
            this.value = value;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getValue() {
            return value;
        }

        public int getPoint() {
            return point;
        }

        public int getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "value=" + value +
                    ", point=" + point +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}
