package com.quantchi.metadatamgr.data;

import java.util.ArrayList;
import java.util.List;

public class TableEntity {
    private String databaseName;
    private String tblName;
    private String tblType;
    private List<FieldEntity> fieldEntityList = new ArrayList<>();

    public TableEntity(String tblName) {
        this.tblName = tblName;
        this.fieldEntityList = fieldEntityList;
    }

    public TableEntity(String databaseName,String tblName) {
        this.databaseName = databaseName;
        this.tblName = tblName;
        this.fieldEntityList = fieldEntityList;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTblName() {
        return tblName;
    }

    public String getTblType() {
        return tblType;
    }

    public void setTblType(String tblType) {
        this.tblType = tblType;
    }

    public void setFieldEntityList(List<FieldEntity> fieldEntityList) {
        this.fieldEntityList = fieldEntityList;
    }

    public List<FieldEntity> getFieldEntityList() {
        return fieldEntityList;
    }
}
