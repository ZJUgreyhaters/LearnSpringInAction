package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.common.util;
import com.quantchi.customer.mapper.CustomerGroupMapper;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerGroupService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/6/11.
 */
@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

  @Autowired
  private CustomerGroupMapper mapper;

  @Override
  public Map<String, Object> selectCustomerGroup(CustomerGroup customerGroup, Integer pageIndex,
      Integer pagesize) {
    try {
      PageHelper.startPage(pageIndex, pagesize);
      // 执行查询
      List<CustomerGroup> list = mapper.selectCustomerGroup(customerGroup);
      // 取分页信息
      PageInfo<CustomerGroup> pageInfo = new PageInfo<>(list);
      // 返回处理结果
      Map<String, Object> result = new HashMap<String, Object>();
      // 获取总条数
      result.put("total", pageInfo.getTotal());
      // 结果rows数据
      result.put("data", pageInfo.getList());
      return util
          .genRet(200, result.get("data"), "ok", Integer.parseInt(result.get("total").toString()));
    } catch (Exception e) {
      e.printStackTrace();
      return util.genRet(500, null, "select CustomerGroup error", 0);
    }
  }

  public Map<String, Object> createCustomerGroup(CustomerGroup group,List<Map<String,String>> customerGroupCriteria) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      String str = mapper.selectCustGroupId();
      if (str==null){
        group.setCust_group_id("CG000001");
      }
      if(customerGroupCriteria.isEmpty()||customerGroupCriteria==null){
        result.put("code","500");
        result.put("msg","条件不能为空！");
        return result;
      }

      group.setCondition_desc(customerGroupCriteria.get(0).get("value"));
      group.setCust_group_id(str);
      group.setRefresh_status("0");
      group.setDelete_status("0");
      group.setHistory_status("0");
      mapper.createCustomerGroup(group);
      result.put("code","200");
      result.put("msg","ok");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code","500");
      result.put("msg","error");
      return result;
    }
  }

  public Map<String, Object> deleteCustomerGroup(CustomerGroup group) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      mapper.deleteCustomerGroup(group);
      result.put("code","200");
      result.put("msg","ok");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code","500");
      result.put("msg","error");
      return result;
    }
  }
  public Map<String, Object> updateCustomerGroup(CustomerGroup group){
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      mapper.updateCustomerGroup(group);
      result.put("code","200");
      result.put("msg","ok");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code","500");
      result.put("msg","error");
      return result;
    }
  }

  //selectTerm
}
