package com.quantchi.metadatamgr.data;

import java.util.ArrayList;
import java.util.List;

public class TableEntity {
    private String tblName;
    private List<FieldEntity> fieldEntityList;

    public TableEntity(String tblName, List<FieldEntity> fieldEntityList) {
        this.tblName = tblName;
        this.fieldEntityList = fieldEntityList;
    }

    public String getTblName() {
        return tblName;
    }

    public List<FieldEntity> getFieldEntityList() {
        return fieldEntityList;
    }
}
