package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.common.HiveLink;
import com.quantchi.common.util;
import com.quantchi.customer.mapper.CustomerGroupMapper;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerGroupService;
import com.quantchi.tianshu.common.JdbcPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/6/11.
 */
@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

  @Autowired
  @Qualifier("hiveJdbcPool")
  private JdbcPool jdbcPool;

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

  public Map<String, Object> createCustomerGroup(CustomerGroup group,
      List<Map<String, String>> customerGroupCriteria) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      String str = mapper.selectCustGroupId();
      if (str == null) {
        group.setCust_group_id("CG000001");
      }
      if (customerGroupCriteria.isEmpty() || customerGroupCriteria == null) {
        result.put("code", "500");
        result.put("msg", "条件不能为空！");
        return result;
      }

      List<Map<String, Object>> list = HiveLink.selectHive("SELECT * FROM loan_sum_info", jdbcPool);
      group.setCondition_desc(customerGroupCriteria.get(0).get("value"));
      group.setCust_group_id(str);
      group.setRefresh_status("0");
      group.setDelete_status("0");
      group.setHistory_status("0");
      mapper.createCustomerGroup(group);
      result.put("code", "200");
      result.put("msg", "ok");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code", "500");
      result.put("msg", "error");
      return result;
    }
  }

  public Map<String, Object> deleteCustomerGroup(CustomerGroup group) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      mapper.deleteCustomerGroup(group);
      result.put("code", "200");
      result.put("msg", "ok");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code", "500");
      result.put("msg", "error");
      return result;
    }
  }

  public Map<String, Object> updateCustomerGroup(CustomerGroup group) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      mapper.updateCustomerGroup(group);
      result.put("code", "200");
      result.put("msg", "ok");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      result.put("code", "500");
      result.put("msg", "error");
      return result;
    }
  }

  String assemble(List<Map<String, String>> customerGroupCriteria) {
    StringBuilder condition_statement = new StringBuilder();
    for (Map<String, String> map : customerGroupCriteria) {
      // String field = mapper.selectFieldById(map.get("id"));

    }
    return "";
  }

  String jointSql(List<Map<String, String>> jointConditions) {
    Map<String, String> map = new LinkedHashMap<>();
    List<String> keys = new ArrayList<>();
    for (Map<String, String> jointCondition : jointConditions) {
      Set<String> strings = jointCondition.keySet();
      List<String> lists = new ArrayList<>(strings);
      if (keys.contains(lists.get(0))) {
        continue;
      }
      keys.add(lists.get(0));
    }

    int i = 0;
    for (int j = 0; j < keys.size(); j++) {
      StringBuilder value = new StringBuilder();
      value.append("select distinct customer_no from" + keys.get(i));
      for (Map<String, String> jointCondition : jointConditions) {
        String str = jointCondition.get(keys.get(i));
        if (str == null) {
          continue;
        }
        i++;
        if (i == 1) {
          value.append("where" + str);
        } else {
          value.append("and" + str);
        }
      }
      value.append(") condition0" + j);
      map.put(keys.get(i), value.toString());
    }
    StringBuilder sql = new StringBuilder();
    String string ="";
    sql.append("select condition01.customer_no from (");
    if (keys.size() == 0) {
      return sql.toString();
    } else {
      int a = 0;
      for (String str : keys) {
        a++;
        if(a==1){
          sql.append(map.get(str));
        }else{
          sql.append("inner join (").append(map.get(str)).append("on condition01.customer_no=condition0"+a+".customer_no");
        }
      }
    }
    return sql.toString();
  }
}
