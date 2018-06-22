package com.quantchi.termInfo.pojo;

import java.util.Date;

public class TermMainInfo {
    private Integer id;

    private String entityType;

    private String entityId;

    private String entityHash;

    private String entityName;

    private String entityDesc;

    private String entityStatus;

    private Date createTime;

    private Date offlineTime;

    private Integer creator;

    private String controlDept;

    private String assistDept;

    private String regulatory;

    private String logicType;

    private String displayType;

    private String entityAlias;

    private String businessRule;

    private String techniqueRule;

    private String devPolicy;

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
        this.entityType = entityType == null ? null : entityType.trim();
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    public String getEntityHash() {
        return entityHash;
    }

    public void setEntityHash(String entityHash) {
        this.entityHash = entityHash == null ? null : entityHash.trim();
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc == null ? null : entityDesc.trim();
    }

    public String getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(String entityStatus) {
        this.entityStatus = entityStatus == null ? null : entityStatus.trim();
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
        this.controlDept = controlDept == null ? null : controlDept.trim();
    }

    public String getAssistDept() {
        return assistDept;
    }

    public void setAssistDept(String assistDept) {
        this.assistDept = assistDept == null ? null : assistDept.trim();
    }

    public String getRegulatory() {
        return regulatory;
    }

    public void setRegulatory(String regulatory) {
        this.regulatory = regulatory == null ? null : regulatory.trim();
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType == null ? null : logicType.trim();
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType == null ? null : displayType.trim();
    }

    public String getEntityAlias() {
        return entityAlias;
    }

    public void setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias == null ? null : entityAlias.trim();
    }

    public String getBusinessRule() {
        return businessRule;
    }

    public void setBusinessRule(String businessRule) {
        this.businessRule = businessRule == null ? null : businessRule.trim();
    }

    public String getTechniqueRule() {
        return techniqueRule;
    }

    public void setTechniqueRule(String techniqueRule) {
        this.techniqueRule = techniqueRule == null ? null : techniqueRule.trim();
    }

    public String getDevPolicy() {
        return devPolicy;
    }

    public void setDevPolicy(String devPolicy) {
        this.devPolicy = devPolicy == null ? null : devPolicy.trim();
    }
}