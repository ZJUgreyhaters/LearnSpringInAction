package com.quantchi.metadatamgr.data.entity;

public class DSTableInfoDB {
    private Integer id;

    private String tableEnglishName;

    private String tableChineseName;

    private String datasourceId;

    private String entity;

    private String entityField;

    private String business;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableEnglishName() {
        return tableEnglishName;
    }

    public void setTableEnglishName(String tableEnglishName) {
        this.tableEnglishName = tableEnglishName == null ? null : tableEnglishName.trim();
    }

    public String getTableChineseName() {
        return tableChineseName;
    }

    public void setTableChineseName(String tableChineseName) {
        this.tableChineseName = tableChineseName == null ? null : tableChineseName.trim();
    }

    public String getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId == null ? null : datasourceId.trim();
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity == null ? null : entity.trim();
    }

    public String getEntityField() {
        return entityField;
    }

    public void setEntityField(String entityField) {
        this.entityField = entityField == null ? null : entityField.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}