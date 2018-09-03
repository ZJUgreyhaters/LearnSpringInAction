package com.quantchi.metadatamgr.data.entity;

public class DSEntityInfoDB {
    private Integer id;

    private String entityName;

    private String business;

    private String datasourceId;

    private String mainTable;

    private String entityField;

    private String nonMainTable;

    private String mainEntityFieldName;

    private String datasourceName;

    public String getDatasourceName() {
        return datasourceName;
    }

    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    public String getMainEntityFieldName() {
        return mainEntityFieldName;
    }

    public void setMainEntityFieldName(String mainEntityFieldName) {
        this.mainEntityFieldName = mainEntityFieldName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName == null ? null : entityName.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId == null ? null : datasourceId.trim();
    }

    public String getMainTable() {
        return mainTable;
    }

    public void setMainTable(String mainTable) {
        this.mainTable = mainTable == null ? null : mainTable.trim();
    }

    public String getEntityField() {
        return entityField;
    }

    public void setEntityField(String entityField) {
        this.entityField = entityField == null ? null : entityField.trim();
    }

    public String getNonMainTable() {
        return nonMainTable;
    }

    public void setNonMainTable(String nonMainTable) {
        this.nonMainTable = nonMainTable == null ? null : nonMainTable.trim();
    }
}