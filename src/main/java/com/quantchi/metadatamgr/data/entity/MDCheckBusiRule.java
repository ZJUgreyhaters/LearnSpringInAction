package com.quantchi.metadatamgr.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MDCheckBusiRule {
    private Integer id;

    private String businId;

    private String checkSystem;

    private String checkStatus;

    private String ruleName;

    private String ruleFirstType;

    private String ruleSecondType;

    private Date createDate;

    private String creator;

    private List<MetricPhysicalInfo> metricPhysicalInfo = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinId() {
        return businId;
    }

    public void setBusinId(String businId) {
        this.businId = businId;
    }

    public String getCheckSystem() {
        return checkSystem;
    }

    public void setCheckSystem(String checkSystem) {
        this.checkSystem = checkSystem;
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

    public List<MetricPhysicalInfo> getMetricPhysicalInfo() {
        return metricPhysicalInfo;
    }

    public void setMetricPhysicalInfo(List<MetricPhysicalInfo> metricPhysicalInfo) {
        this.metricPhysicalInfo = metricPhysicalInfo;
    }
}