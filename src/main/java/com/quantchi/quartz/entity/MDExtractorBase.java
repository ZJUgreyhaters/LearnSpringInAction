package com.quantchi.quartz.entity;

import com.quantchi.quartz.util.JobConstant;

import java.util.HashMap;
import java.util.Map;

public class MDExtractorBase {

    private Integer id;

    private String jobName;

    private String datasourceId;

    private String parentTreeId;

    private String quartzId;

    private String jobClzName;

    private String status;

    private Map<String,Object> jobParam = new HashMap<>();

    public Map<String, Object> getJobParam() {
        return jobParam;
    }

    public void setJobParam(Map<String, Object> jobParam) {
        this.jobParam = jobParam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getQuartzId() {
        return quartzId;
    }

    public void setQuartzId(String quartzId) {
        this.quartzId = quartzId;
    }

    public String getJobClzName() {
        return jobClzName;
    }

    public void setJobClzName(String jobClzName) {
        this.jobClzName = jobClzName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentTreeId() {
        return parentTreeId;
    }

    public void setParentTreeId(String parentTreeId) {
        this.parentTreeId = parentTreeId;
    }
}