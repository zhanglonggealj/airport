package com.esop.airport.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @program: airport
 * @description:
 * @author: Mr.Li
 * @create: 2019-08-12 08:45
 **/

@Table(name = "t_bas_text_push")
public class TBasTextPush extends BaseModel implements Serializable {
    private static final long serialVersionUID = -7108035110901072170L;


    @Id
    private Long id;

    private String data;

    private Integer type;

    @Column(updatable = false, insertable = false)
    private Integer status;

    @Column(updatable = false,insertable = false)
    private java.sql.Timestamp updateTime;

    @Column(updatable = false, insertable = false)
    private java.sql.Timestamp createTime;

    @Override
    public String toString() {
        return "TBasTextPush{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
