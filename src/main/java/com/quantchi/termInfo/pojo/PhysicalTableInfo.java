package com.quantchi.termInfo.pojo;

import java.util.Date;

public class PhysicalTableInfo {
    private Integer id;

    private String physicalTableHash;

    private String physicalTable;

    private String physicalDb;

    private String tableType;

    private String tableName;

    private String tableDesc;

    private Date lastModifiedTime;

    private String partitionFlag;

    private String primaryKey;

    private String foreignKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhysicalTableHash() {
        return physicalTableHash;
    }

    public void setPhysicalTableHash(String physicalTableHash) {
        this.physicalTableHash = physicalTableHash == null ? null : physicalTableHash.trim();
    }

    public String getPhysicalTable() {
        return physicalTable;
    }

    public void setPhysicalTable(String physicalTable) {
        this.physicalTable = physicalTable == null ? null : physicalTable.trim();
    }

    public String getPhysicalDb() {
        return physicalDb;
    }

    public void setPhysicalDb(String physicalDb) {
        this.physicalDb = physicalDb == null ? null : physicalDb.trim();
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType == null ? null : tableType.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc == null ? null : tableDesc.trim();
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getPartitionFlag() {
        return partitionFlag;
    }

    public void setPartitionFlag(String partitionFlag) {
        this.partitionFlag = partitionFlag == null ? null : partitionFlag.trim();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey == null ? null : primaryKey.trim();
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey == null ? null : foreignKey.trim();
    }
}