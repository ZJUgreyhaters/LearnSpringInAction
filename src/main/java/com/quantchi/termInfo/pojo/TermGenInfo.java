package com.quantchi.termInfo.pojo;

import java.util.List;

public class TermGenInfo {

    private PhysicalTableInfo tableInfo;

    private List<PhysicalFieldInfo> fieldInfoList;

    private TermMainInfo termMainInfo;

    private MdTermExternalInfo termExternalInfo;

    public MdTermExternalInfo getTermExternalInfo() {
        return termExternalInfo;
    }

    public void setTermExternalInfo(MdTermExternalInfo termExternalInfo) {
        this.termExternalInfo = termExternalInfo;
    }

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