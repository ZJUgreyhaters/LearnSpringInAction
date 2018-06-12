package com.quantchi.customer.service;

import com.quantchi.customer.pojo.CustomerGroup;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/11.
 */
public interface CustomerGroupService {

  Map<String, Object> selectCustomerGroup(CustomerGroup role, Integer pageIndex,
      Integer pagesize);
}
