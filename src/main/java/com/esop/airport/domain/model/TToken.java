package com.esop.airport.domain.model;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_token")
public class TToken extends BaseModel {

    private static final long serialVersionUID = -7744139564426406263L;

    @Id
    private Long id;
    private Long bindId;
    private Integer category;
    private String tokenCode;
    private java.sql.Timestamp createTime;

    @Override
    public String toString() {
        return "TToken{" +
                "id=" + id +
                ", bindId=" + bindId +
                ", category=" + category +
                ", tokenCode='" + tokenCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Long getBindId() {
        return bindId;
    }

    public void setBindId(Long bindId) {
        this.bindId = bindId;
    }


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }


    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

}
