package com.quantchi.customer.service;

import com.quantchi.customer.pojo.CustomerGroup;
import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/11.
 */
public interface CustomerGroupService {

  public Map<String, Object> selectCustomerGroup(CustomerGroup group);

  public Map<String, Object> createCustomerGroup(CustomerGroup group,
                                                 List<Map<String, Object>> customerGroupCriteria);

  public Map<String, Object> deleteCustomerGroup(CustomerGroup group);

  public Map<String, Object> updateCustomerGroup(CustomerGroup group);

  public String listCustomersWithDim(Map<String, Object> map);

  public Map<String,Object> listCustomersByCustomerGroupId(CustomerGroup group);

  public List<Map<String,Object>> exportCustomerList(CustomerGroup group);

  public Map<String,Object> refreshCustomerGroup(CustomerGroup group);
}
