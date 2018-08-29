package com.quantchi.authority.pojo;


public class Tauthority {

  private long l_Authid;
  private String c_Authname;
  private String c_Isenable;
  private String c_Authtype;
  private java.sql.Date d_Createdate;


  public long getL_authid() {
    return l_Authid;
  }

  public void setL_Authid(long l_Authid) {
    this.l_Authid = l_Authid;
  }


  public String getC_authname() {
    return c_Authname;
  }

  public void setC_Authname(String c_Authname) {
    this.c_Authname = c_Authname;
  }


  public String getC_isenable() {
    return c_Isenable;
  }

  public void setC_Isenable(String c_Isenable) {
    this.c_Isenable = c_Isenable;
  }


  public String getC_authtype() {
    return c_Authtype;
  }

  public void setC_Authtype(String c_Authtype) {
    this.c_Authtype = c_Authtype;
  }


  public java.sql.Date getD_createdate() {
    return d_Createdate;
  }

  public void setD_Createdate(java.sql.Date d_Createdate) {
    this.d_Createdate = d_Createdate;
  }

}
