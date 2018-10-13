package com.quantchi.metadatamgr.data.entity;

import java.util.Date;

public class MDCheckTechRule {
    private Integer id;

    private String techRuleId;

    private String businId;

    private String checkStatus;

    private String ruleName;

    private String sourceData;

    private String sourceTable;

    private String sourdeCol;

    private String ruleFirstType;

    private String ruleSecondType;

    private Date createDate;

    private String creator;

    private String ruleSqlText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTechRuleId() {
        return techRuleId;
    }

    public void setTechRuleId(String techRuleId) {
        this.techRuleId = techRuleId;
    }

    public String getBusinId() {
        return businId;
    }

    public void setBusinId(String businId) {
        this.businId = businId;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getSourceData() {
        return sourceData;
    }

    public void setSourceData(String sourceData) {
        this.sourceData = sourceData;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getSourdeCol() {
        return sourdeCol;
    }

    public void setSourdeCol(String sourdeCol) {
        this.sourdeCol = sourdeCol;
    }

    public String getRuleFirstType() {
        return ruleFirstType;
    }

    public void setRuleFirstType(String ruleFirstType) {
        this.ruleFirstType = ruleFirstType;
    }

    public String getRuleSecondType() {
        return ruleSecondType;
    }

    public void setRuleSecondType(String ruleSecondType) {
        this.ruleSecondType = ruleSecondType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRuleSqlText() {
        return ruleSqlText;
    }

    public void setRuleSqlText(String ruleSqlText) {
        this.ruleSqlText = ruleSqlText;
    }
}