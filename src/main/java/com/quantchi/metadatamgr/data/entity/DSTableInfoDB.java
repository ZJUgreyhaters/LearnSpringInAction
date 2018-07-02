package com.quantchi.metadatamgr.data.entity;

public class DSTableInfoDB {

  private Integer id;

  private String tableEnglishName;

  private String tableChineseName;

  private String datasourceId;

  private String primaryKey;

  private String business;

  private Integer status;

  private Integer page;

  private Integer page_size;

  private Integer nums;

  private Integer foreignNums;

  public Integer getNums() {
    return nums;
  }

  public void setNums(Integer nums) {
    this.nums = nums;
  }

  public Integer getForeignNums() {
    return foreignNums;
  }

  public void setForeignNums(Integer foreignNums) {
    this.foreignNums = foreignNums;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPage_size() {
    return page_size;
  }

  public void setPage_size(Integer page_size) {
    this.page_size = page_size;
  }

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
    this.tableEnglishName = tableEnglishName;
  }

  public String getTableChineseName() {
    return tableChineseName;
  }

  public void setTableChineseName(String tableChineseName) {
    this.tableChineseName = tableChineseName;
  }

  public String getDatasourceId() {
    return datasourceId;
  }

  public void setDatasourceId(String datasourceId) {
    this.datasourceId = datasourceId;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
  }

  private String tableType;

  public String getTableType() {
    return tableType;
  }

  public void setTableType(String tableType) {
    this.tableType = tableType;
  }
}