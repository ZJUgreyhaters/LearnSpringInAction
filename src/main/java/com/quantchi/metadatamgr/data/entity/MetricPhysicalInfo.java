package com.quantchi.metadatamgr.data.entity;


import java.util.ArrayList;
import java.util.List;

public class MetricPhysicalInfo {

    private String entityId;

    private String physicalTable;

    private String physicalDb;

    private String physicalField;

    private String checkType;

    private List<String> checkParam = new ArrayList<>();

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPhysicalTable() {
        return physicalTable;
    }

    public void setPhysicalTable(String physicalTable) {
        this.physicalTable = physicalTable;
    }

    public String getPhysicalDb() {
        return physicalDb;
    }

    public void setPhysicalDb(String physicalDb) {
        this.physicalDb = physicalDb;
    }

    public String getPhysicalField() {
        return physicalField;
    }

    public void setPhysicalField(String physicalField) {
        this.physicalField = physicalField;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public List<String> getCheckParam() {
        return checkParam;
    }

    public void setCheckParam(List<String> checkParam) {
        this.checkParam = checkParam;
    }
}