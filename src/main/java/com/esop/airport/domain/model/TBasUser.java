package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;


@Table(name = "t_bas_user")
public class TBasUser extends BaseModel{

  private static final long serialVersionUID = -7196584978377064692L;

  @Id
  private Long id;
  private String mobilePhone;
  private String nickName;
  private String headPortrait;
  private Integer sex;
  private String password;
  private String wxOpenid;
  private String oldMobilePhone;
  private Date lastLoginTime;

  @Column(insertable = false)
  private Integer status;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp updateTime;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "TBasUser{" +
            "id=" + id +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", nickName='" + nickName + '\'' +
            ", headPortrait='" + headPortrait + '\'' +
            ", sex=" + sex +
            ", password='" + password + '\'' +
            ", wxOpenid='" + wxOpenid + '\'' +
            ", oldMobilePhone='" + oldMobilePhone + '\'' +
            ", lastLoginTime=" + lastLoginTime +
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

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getHeadPortrait() {
    return headPortrait;
  }

  public void setHeadPortrait(String headPortrait) {
    this.headPortrait = headPortrait;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getWxOpenid() {
    return wxOpenid;
  }

  public void setWxOpenid(String wxOpenid) {
    this.wxOpenid = wxOpenid;
  }

  public String getOldMobilePhone() {
    return oldMobilePhone;
  }

  public void setOldMobilePhone(String oldMobilePhone) {
    this.oldMobilePhone = oldMobilePhone;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
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
