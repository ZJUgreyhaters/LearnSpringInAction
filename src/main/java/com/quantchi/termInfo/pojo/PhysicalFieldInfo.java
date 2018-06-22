package com.quantchi.termInfo.pojo;

import java.math.BigDecimal;

public class PhysicalFieldInfo {
    private Integer id;

    private String entityId;

    private String physicalFieldHash;

    private String physicalField;

    private String physicalFieldDesc;

    private String physicalTable;

    private String physicalDb;

    private String dataType;

    private Integer dataLength;

    private Integer dataPrecision;

    private String dataPattern;

    private String dataUnit;

    private String partitionFlag;

    private String udcRuleName;

    private String udcCode;

    private BigDecimal max;

    private BigDecimal min;

    private BigDecimal avg;

    private BigDecimal dataNull;

    private String distribution;

    private String enumeration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    public String getPhysicalFieldHash() {
        return physicalFieldHash;
    }

    public void setPhysicalFieldHash(String physicalFieldHash) {
        this.physicalFieldHash = physicalFieldHash == null ? null : physicalFieldHash.trim();
    }

    public String getPhysicalField() {
        return physicalField;
    }

    public void setPhysicalField(String physicalField) {
        this.physicalField = physicalField == null ? null : physicalField.trim();
    }

    public String getPhysicalFieldDesc() {
        return physicalFieldDesc;
    }

    public void setPhysicalFieldDesc(String physicalFieldDesc) {
        this.physicalFieldDesc = physicalFieldDesc == null ? null : physicalFieldDesc.trim();
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(Integer dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public String getDataPattern() {
        return dataPattern;
    }

    public void setDataPattern(String dataPattern) {
        this.dataPattern = dataPattern == null ? null : dataPattern.trim();
    }

    public String getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit == null ? null : dataUnit.trim();
    }

    public String getPartitionFlag() {
        return partitionFlag;
    }

    public void setPartitionFlag(String partitionFlag) {
        this.partitionFlag = partitionFlag == null ? null : partitionFlag.trim();
    }

    public String getUdcRuleName() {
        return udcRuleName;
    }

    public void setUdcRuleName(String udcRuleName) {
        this.udcRuleName = udcRuleName == null ? null : udcRuleName.trim();
    }

    public String getUdcCode() {
        return udcCode;
    }

    public void setUdcCode(String udcCode) {
        this.udcCode = udcCode == null ? null : udcCode.trim();
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getDataNull() {
        return dataNull;
    }

    public void setDataNull(BigDecimal dataNull) {
        this.dataNull = dataNull;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution == null ? null : distribution.trim();
    }

    public String getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration = enumeration == null ? null : enumeration.trim();
    }
}