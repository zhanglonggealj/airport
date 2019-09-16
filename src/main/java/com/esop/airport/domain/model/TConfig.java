package com.esop.airport.domain.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "t_config")
public class TConfig extends BaseModel{

  private static final long serialVersionUID = -7619177897393350028L;

  @Id
  private Long id;
  private String configName;
  private String configData;

  private Long status;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp updateTime;
  @Column(updatable = false,insertable = false)
  private java.sql.Timestamp createTime;

  @Override
  public String toString() {
    return "TConfig{" +
            "id=" + id +
            ", configName='" + configName + '\'' +
            ", configData='" + configData + '\'' +
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

  public String getConfigName() {
    return configName;
  }

  public void setConfigName(String configName) {
    this.configName = configName;
  }

  public String getConfigData() {
    return configData;
  }

  public void setConfigData(String configData) {
    this.configData = configData;
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
