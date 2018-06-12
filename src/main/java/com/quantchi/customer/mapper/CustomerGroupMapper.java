package com.quantchi.customer.mapper;

import com.quantchi.customer.pojo.CustomerGroup;
import java.util.List;

/**
 * Created by 49537 on 2018/6/11.
 */
public interface CustomerGroupMapper {
  List<CustomerGroup> selectCustomerGroup(CustomerGroup customerGroup);
}
