package com.quantchi.metadatamgr.data.entity;

public class DSFieldRelDB {
    private Integer relationId;

    private String relation;

    private String fieldId;

    private String tableId;

    private String foreignFieldId;

    private String foreignTableId;

    private Integer isprimary;

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId == null ? null : fieldId.trim();
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public String getForeignFieldId() {
        return foreignFieldId;
    }

    public void setForeignFieldId(String foreignFieldId) {
        this.foreignFieldId = foreignFieldId == null ? null : foreignFieldId.trim();
    }

    public String getForeignTableId() {
        return foreignTableId;
    }

    public Integer getIsprimary() {
        return isprimary;
    }

    public void setIsprimary(Integer isprimary) {
        this.isprimary = isprimary;
    }

    public void setForeignTableId(String foreignTableId) {
        this.foreignTableId = foreignTableId == null ? null : foreignTableId.trim();
    }
}