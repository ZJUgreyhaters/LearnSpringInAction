package com.quantchi.customer.pojo;

public class ConditionGroup {

    private Integer cust_condition_id;
    private String cust_condition_name;
    private String create_user_id;
    private String condition_desc;
    private String condition_desc_id;
    private String condition_statement;

    public Integer getCust_condition_id() {
        return cust_condition_id;
    }

    public String getCust_condition_name() {
        return cust_condition_name;
    }

    public String getCreate_user_id() {
        return create_user_id;
    }

    public String getCondition_desc() {
        return condition_desc;
    }

    public String getCondition_desc_id() {
        return condition_desc_id;
    }

    public String getCondition_statement() {
        return condition_statement;
    }

    public void setCust_condition_id(Integer cust_condition_id) {
        this.cust_condition_id = cust_condition_id;
    }

    public void setCust_condition_name(String cust_condition_name) {
        this.cust_condition_name = cust_condition_name;
    }

    public void setCreate_user_id(String create_user_id) {
        this.create_user_id = create_user_id;
    }

    public void setCondition_desc(String condition_desc) {
        this.condition_desc = condition_desc;
    }

    public void setCondition_desc_id(String condition_desc_id) {
        this.condition_desc_id = condition_desc_id;
    }

    public void setCondition_statement(String condition_statement) {
        this.condition_statement = condition_statement;
    }
}
