package com.quantchi.quartz.entity;

import com.quantchi.quartz.util.JobConstant;

import java.util.HashMap;
import java.util.Map;

public class MDExtractorJobManual extends MDExtractorBase {

    private String fileTpl;
    private String strategy;
    private String filepath;
    private String type;

    public String getFileTpl() {
        return fileTpl;
    }

    public void setFileTpl(String fileTpl) {
        this.fileTpl = fileTpl;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}