package com.quantchi.authority.pojo;


public class Tdatacolumndetail {

  private long l_Columndetailid;
  private String c_Tablename;
  private String c_Column;
  private String c_Isenable;
  private String c_Policytype;
  private String c_Database;

  public long getL_columndeailid() {
    return l_Columndetailid;
  }

  public void setL_Columndeailid(long l_Columndeailid) {
    this.l_Columndetailid = l_Columndeailid;
  }

  public String getC_database() {
    return c_Database;
  }

  public void setC_Database(String c_Database) {
    this.c_Database = c_Database;
  }

  public String getC_tablename() {
    return c_Tablename;
  }

  public void setC_Tablename(String c_Tablename) {
    this.c_Tablename = c_Tablename;
  }


  public String getC_column() {
    return c_Column;
  }

  public void setC_Column(String c_Column) {
    this.c_Column = c_Column;
  }


  public String getC_isenable() {
    return c_Isenable;
  }

  public void setC_Isenable(String c_Isenable) {
    this.c_Isenable = c_Isenable;
  }


  public String getC_policytype() {
    return c_Policytype;
  }

  public void setC_Policytype(String c_Policytype) {
    this.c_Policytype = c_Policytype;
  }

}
