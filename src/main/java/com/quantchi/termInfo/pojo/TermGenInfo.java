package com.quantchi.termInfo.pojo;

import java.util.Date;
import java.util.List;

public class TermGenInfo {

    private PhysicalTableInfo tableInfo;

    private List<PhysicalFieldInfo> fieldInfoList;

    private TermMainInfo termMainInfo;

    public PhysicalTableInfo getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(PhysicalTableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public List<PhysicalFieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<PhysicalFieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    public TermMainInfo getTermMainInfo() {
        return termMainInfo;
    }

    public void setTermMainInfo(TermMainInfo termMainInfo) {
        this.termMainInfo = termMainInfo;
    }
}