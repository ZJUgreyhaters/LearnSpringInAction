package com.quantchi.termInfo.pojo;

import java.util.Date;

public class TermLogicCatagory {
    private Integer id;

    private String categoryName;

    private Integer parentId;

    private Date createTime;

    private String logicTable;

    private String physicalTable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLogicTable() {
        return logicTable;
    }

    public void setLogicTable(String logicTable) {
        this.logicTable = logicTable == null ? null : logicTable.trim();
    }

    public String getPhysicalTable() {
        return physicalTable;
    }

    public void setPhysicalTable(String physicalTable) {
        this.physicalTable = physicalTable == null ? null : physicalTable.trim();
    }
}