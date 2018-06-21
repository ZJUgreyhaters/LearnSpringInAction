package com.quantchi.termInfo.pojo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by 49537 on 2018/6/20.
 */
public class TermInfoPojo {
  private Integer id;

  private String entityType;

  private String entityId;

  private String entityHash;

  private String entityName;

  private String entityDesc;

  private String entityAlias;

  private String businessRule;

  private String techniqueRule;

  private String entityStatus;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date offlineTime;

  private Integer creator;

  private String controlDept;

  private String assistDept;

  private String devPolicy;

  private String regulatory;

  private String logicType;

  private String displayType;

  private Integer nums;

  public Integer getNums() {
    return nums;
  }

  public void setNums(Integer nums) {
    this.nums = nums;
  }

  /****************
   *set和get方法
   ****************/
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public String getEntityType() {
    return entityType;
  }

  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }
  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }
  public String getEntityHash() {
    return entityHash;
  }

  public void setEntityHash(String entityHash) {
    this.entityHash = entityHash;
  }
  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }
  public String getEntityDesc() {
    return entityDesc;
  }

  public void setEntityDesc(String entityDesc) {
    this.entityDesc = entityDesc;
  }
  public String getEntityAlias() {
    return entityAlias;
  }

  public void setEntityAlias(String entityAlias) {
    this.entityAlias = entityAlias;
  }
  public String getBusinessRule() {
    return businessRule;
  }

  public void setBusinessRule(String businessRule) {
    this.businessRule = businessRule;
  }
  public String getTechniqueRule() {
    return techniqueRule;
  }

  public void setTechniqueRule(String techniqueRule) {
    this.techniqueRule = techniqueRule;
  }
  public String getEntityStatus() {
    return entityStatus;
  }

  public void setEntityStatus(String entityStatus) {
    this.entityStatus = entityStatus;
  }
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public Date getOfflineTime() {
    return offlineTime;
  }

  public void setOfflineTime(Date offlineTime) {
    this.offlineTime = offlineTime;
  }
  public Integer getCreator() {
    return creator;
  }

  public void setCreator(Integer creator) {
    this.creator = creator;
  }
  public String getControlDept() {
    return controlDept;
  }

  public void setControlDept(String controlDept) {
    this.controlDept = controlDept;
  }
  public String getAssistDept() {
    return assistDept;
  }

  public void setAssistDept(String assistDept) {
    this.assistDept = assistDept;
  }
  public String getDevPolicy() {
    return devPolicy;
  }

  public void setDevPolicy(String devPolicy) {
    this.devPolicy = devPolicy;
  }
  public String getRegulatory() {
    return regulatory;
  }

  public void setRegulatory(String regulatory) {
    this.regulatory = regulatory;
  }
  public String getLogicType() {
    return logicType;
  }

  public void setLogicType(String logicType) {
    this.logicType = logicType;
  }
  public String getDisplayType() {
    return displayType;
  }

  public void setDisplayType(String displayType) {
    this.displayType = displayType;
  }

}
