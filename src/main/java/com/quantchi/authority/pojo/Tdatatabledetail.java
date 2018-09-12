package com.quantchi.authority.pojo;


public class Tdatatabledetail {

  private long l_Datatabledetailid;
  private String c_Tablename;
  private String c_Isenable;
  private String c_Policytype;
  private String c_Database;

  public String getC_database() {
    return c_Database;
  }

  public void setC_Database(String c_Database) {
    this.c_Database = c_Database;
  }

  public long getL_datatabledetailid() {
    return l_Datatabledetailid;
  }

  public void setL_Datatabledetailid(long l_Datatabledetailid) {
    this.l_Datatabledetailid = l_Datatabledetailid;
  }


  public String getC_tablename() {
    return c_Tablename;
  }

  public void setC_Tablename(String c_Tablename) {
    this.c_Tablename = c_Tablename;
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
