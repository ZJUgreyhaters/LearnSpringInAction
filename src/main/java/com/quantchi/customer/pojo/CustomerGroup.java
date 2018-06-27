package com.quantchi.customer.pojo;

/**
 * Created by 49537 on 2018/6/11.
 */
public class CustomerGroup {

  private Integer id;
  private String cust_group_id;
  private String cust_group_name;
  private Integer condition_nums;
  private String create_time;
  private String create_user_id;
  private String last_refresh_time;
  private Integer cust_nums;
  private String condition_desc;
  private String condition_statement;
  private String refresh_status;
  private String delete_status;
  private String history_status;
  private String default_analysis;
  private Integer page;
  private Integer page_size;
  private String customer_name;
  private String field;
  private String order;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getCustomer_name() {
    return customer_name;
  }

  public void setCustomer_name(String customer_name) {
    this.customer_name = customer_name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCust_group_id() {
    return cust_group_id;
  }

  public void setCust_group_id(String cust_group_id) {
    this.cust_group_id = cust_group_id;
  }

  public String getCust_group_name() {
    return cust_group_name;
  }

  public void setCust_group_name(String cust_group_name) {
    this.cust_group_name = cust_group_name;
  }

  public Integer getCondition_nums() {
    return condition_nums;
  }

  public void setCondition_nums(Integer condition_nums) {
    this.condition_nums = condition_nums;
  }

  public String getCreate_time() {
    return create_time;
  }

  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }

  public String getCreate_user_id() {
    return create_user_id;
  }

  public void setCreate_user_id(String create_user_id) {
    this.create_user_id = create_user_id;
  }

  public String getLast_refresh_time() {
    return last_refresh_time;
  }

  public void setLast_refresh_time(String last_refresh_time) {
    this.last_refresh_time = last_refresh_time;
  }

  public Integer getCust_nums() {
    return cust_nums;
  }

  public void setCust_nums(Integer cust_nums) {
    this.cust_nums = cust_nums;
  }

  public String getCondition_desc() {
    return condition_desc;
  }

  public void setCondition_desc(String condition_desc) {
    this.condition_desc = condition_desc;
  }

  public String getCondition_statement() {
    return condition_statement;
  }

  public void setCondition_statement(String condition_statement) {
    this.condition_statement = condition_statement;
  }

  public String getRefresh_status() {
    return refresh_status;
  }

  public void setRefresh_status(String refresh_status) {
    this.refresh_status = refresh_status;
  }

  public String getDelete_status() {
    return delete_status;
  }

  public void setDelete_status(String delete_status) {
    this.delete_status = delete_status;
  }

  public String getHistory_status() {
    return history_status;
  }

  public void setHistory_status(String history_status) {
    this.history_status = history_status;
  }

  public String getDefault_analysis() {
    return default_analysis;
  }

  public void setDefault_analysis(String default_analysis) {
    this.default_analysis = default_analysis;
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
}
