package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_bas_msg_push")
public class TBasMsgPush extends BaseModel{

  private static final long serialVersionUID = 6080000678856213760L;

  @Id
  private Long id;
  private Integer eventType;
  private Long pushType;
  private String consNo;
  private String mobilePhone;
  private Date pushTime;
  private String pushText;
  @Column(insertable = false)
  private Long status;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp updateTime;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "TBasMsgPush{" +
            "id=" + id +
            ", eventType=" + eventType +
            ", pushType=" + pushType +
            ", consNo='" + consNo + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", pushTime=" + pushTime +
            ", pushText='" + pushText + '\'' +
            ", status=" + status +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
            '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getEventType() {
    return eventType;
  }

  public void setEventType(Integer eventType) {
    this.eventType = eventType;
  }

  public Long getPushType() {
    return pushType;
  }

  public void setPushType(Long pushType) {
    this.pushType = pushType;
  }

  public String getConsNo() {
    return consNo;
  }

  public void setConsNo(String consNo) {
    this.consNo = consNo;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public Date getPushTime() {
    return pushTime;
  }

  public void setPushTime(Date pushTime) {
    this.pushTime = pushTime;
  }

  public String getPushText() {
    return pushText;
  }

  public void setPushText(String pushText) {
    this.pushText = pushText;
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
