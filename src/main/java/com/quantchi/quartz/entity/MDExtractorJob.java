package com.quantchi.quartz.entity;

import com.quantchi.quartz.util.JobConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MDExtractorJob extends MDExtractorBase {

    private String status = JobConstant.ACTIVE;

    private String cronexpr;

    private String datasourceName;

    private String frequency;

    private String whichDay;

    private String time;

    private String startDate;

    private String endDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCronexpr() {
        return cronexpr;
    }

    public void setCronexpr(String cronexpr) {
        this.cronexpr = cronexpr == null ? null : cronexpr.trim();
    }

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName){
        this.datasourceName = datasourceName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

	public String getWhichDay() {
		return whichDay;
	}

	public void setWhichDay(String whichDay) {
		this.whichDay = whichDay;
	}
}