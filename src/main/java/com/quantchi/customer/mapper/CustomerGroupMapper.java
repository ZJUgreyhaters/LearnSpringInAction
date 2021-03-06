package com.quantchi.customer.mapper;

import com.quantchi.customer.pojo.CustomerGroup;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 49537 on 2018/6/11.
 */
public interface CustomerGroupMapper {

  List<Map<String,Object>> selectCustomerGroup(CustomerGroup customerGroup);

  void createCustomerGroup(CustomerGroup group);

  String selectCustGroupId();

  void deleteCustomerGroup(CustomerGroup group);

  void updateCustomerGroup(CustomerGroup group);

  String selectCondition(CustomerGroup group);

  List<Map<String,Object>> selectUdc(@Param("entityIds") String entityIds);

  List<Map<String,Object>> selectName(CustomerGroup customerGroup);

  List<Map<String, Object>> selectIdAndNames(@Param("ids")String ids);
}
