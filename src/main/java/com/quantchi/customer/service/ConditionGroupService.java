package com.quantchi.customer.service;

import com.quantchi.customer.pojo.ConditionGroup;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ConditionGroupService {
    Map<String,Object> listCustomerGroupCriterias(Integer page_size, Integer page) throws SQLException;
    void createCustomerGroupCriteria(Map<String, Object> map) throws SQLException;
    Map<String, Object> findCustomerGroup(String customerGroupId);
    Map<String, Object> deleteCustomerGroup(String customerGroupId);
}
