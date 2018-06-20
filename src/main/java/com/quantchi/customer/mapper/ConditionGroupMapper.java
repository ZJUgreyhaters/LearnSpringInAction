package com.quantchi.customer.mapper;

import com.quantchi.customer.pojo.ConditionGroup;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.Map;

public interface ConditionGroupMapper {
    void createCustomerGroupCriteria(Map<String, Object> map);
    List<Object> listCustomerGroupCriterias();
    ConditionGroup findCustomerGroup(String customerConditionId);
    int deleteCustomerGroup(String customerConditionId);
}
