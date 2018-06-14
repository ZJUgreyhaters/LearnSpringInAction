package com.quantchi.customer.service;

import com.quantchi.customer.pojo.CustomerGroup;
import java.util.List;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/11.
 */
public interface CustomerGroupService {

  public Map<String, Object> selectCustomerGroup(CustomerGroup group, Integer pageIndex,
      Integer pagesize);

  public Map<String, Object> createCustomerGroup(CustomerGroup group,
      List<Map<String, String>> customerGroupCriteria);

  public Map<String, Object> deleteCustomerGroup(CustomerGroup group);

  public Map<String, Object> updateCustomerGroup(CustomerGroup group);

  public Map<String,Object> listCustomersWithDim(CustomerGroup group);

  public Map<String,Object> listCustomersByCustomerGroupId(CustomerGroup group);
}
