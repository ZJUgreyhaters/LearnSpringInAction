package com.quantchi.customer.controller;

import com.quantchi.common.ExportUtil;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerGroupService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 49537 on 2018/6/11.
 */
@Controller
@RequestMapping("api")
public class CustomerGroupController {

  @Autowired
  private CustomerGroupService service;

  //客群列表查看
  @ResponseBody
  @RequestMapping(value = "/listCustomerGroups", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public Map<String, Object> listCustomerGroups(@RequestBody CustomerGroup group) {
    return service.selectCustomerGroup(group);
  }

  //客群列表添加
  @ResponseBody
  @RequestMapping(value = "/createCustomerGroup", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public Map<String, Object> createCustomerGroup(@RequestBody Map<String, Object> map) {
    CustomerGroup group = new CustomerGroup();
    group.setCust_group_name(map.get("cust_group_name").toString());
    group.setCreate_user_id(map.get("create_user_id").toString());
    group.setCust_nums(Integer.valueOf(map.get("cust_nums").toString()));
    List<Map<String, Object>> customerGroupCriteria = (List<Map<String, Object>>) map
        .get("customer_group_criteria");
    group.setCondition_nums(customerGroupCriteria.size());
    return service.createCustomerGroup(group, customerGroupCriteria);
  }


  //客群列表删除
  @ResponseBody
  @RequestMapping(value = "/deleteCustomerGroup", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public Map<String, Object> deleteCustomerGroup(@RequestBody CustomerGroup group) {
    return service.deleteCustomerGroup(group);
  }

  //客群列表更新
  @ResponseBody
  @RequestMapping(value = "/updateCustomerGroup", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public Map<String, Object> updateCustomerGroup(@RequestBody CustomerGroup group) {
    return service.updateCustomerGroup(group);
  }

  //客群客户列表结果导出
  @ResponseBody
  @RequestMapping(value = "/exportCustomerList", method = {RequestMethod.GET})
  public Map<String, Object> exportCustomerList(HttpServletResponse response,CustomerGroup group) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      List<Map<String, Object>> list = service.exportCustomerList(group);
      if (list.toString().contains("select error")) {
        result.put("code", "500");
        result.put("msg", "export error");
        result.put("data", "excel");
      }
      String fileName = "客群分析-个体详情";
      String title = "客群分析-个体详情";
      String[] titles = {"客户号", "客户姓名", "融资负债（万元）", "总资产（万元）", "维保比例", "当前仓位", "年度收益率"};
      String msg = ExportUtil.exportRelationExcel(response, fileName, title, titles, list);
      result.put("code", "200");
      result.put("msg", msg);
      result.put("data", "excel");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code", "500");
      result.put("msg", "export error");
      result.put("data", "excel");
      return result;
    }

  }

  //客群客户检索结果展示
  @ResponseBody
  @RequestMapping(value = "/listCustomersWithDim", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public String listCustomersWithDim(@RequestBody Map<String, Object> map) {
    String result = service.listCustomersWithDim(map);
    return result;
  }

  //客群客户详情列表展示
  @ResponseBody
  @RequestMapping(value = "/listCustomersByCustomerGroupId", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public Map<String, Object> listCustomersByCustomerGroupId(@RequestBody CustomerGroup group) {

    return service.listCustomersByCustomerGroupId(group);
  }

  //刷新客群
  @ResponseBody
  @RequestMapping(value = "/refreshCustomerGroup", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public Map<String, Object> refreshCustomerGroup(@RequestBody CustomerGroup group) {

    return service.refreshCustomerGroup(group);
  }
}
