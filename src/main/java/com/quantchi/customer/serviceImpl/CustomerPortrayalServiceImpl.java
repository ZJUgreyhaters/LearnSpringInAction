package com.quantchi.customer.serviceImpl;

import com.quantchi.common.HiveConnection;
import com.quantchi.common.JsonResult;
import com.quantchi.common.SQLQueryConfig;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerPortrayalService;
import com.quantchi.tianshu.common.JdbcPool;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/6/26.
 */
@Service
public class CustomerPortrayalServiceImpl implements CustomerPortrayalService {

  @Autowired
  private SQLQueryConfig sqlQueryConfig;

  @Autowired
  @Qualifier("hiveJdbcPool")
  private JdbcPool jdbcPool;

  @Override
  public String selectRatio(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_HIVE_COUNTRY_RATIO();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      return JsonResult.successJson(mapping(list));
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectAnalyze(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_HIVE_COUNTRY_ANALYZE();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      return JsonResult.successJson(mapping(list));
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectYield(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_HIVE_COUNTRY_YIELD();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      return JsonResult.successJson(mapping(list));
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectGrade(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_HIVE_COUNTRY_GRADE();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      return JsonResult.successJson(mapping(list));
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectDepartment(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_HIVE_COUNTRY_DEPARTMENT();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      return JsonResult.successJson(departmentMapping(list));
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectPreference(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_HIVE_COUNTRY_PREFERENCE();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      return JsonResult.successJson(mapping(list));
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  Map<String, Object> mapping(List<Map<String, Object>> list) {

    Map<String, Object> result = new LinkedHashMap<>();
    if (list != null && list.size() > 0) {
      Set<String> strings = list.get(0).keySet();
      List<String> keys = new ArrayList<>(strings);
      for (String str : keys) {
        List<Object> values = new LinkedList<>();
        for (Map<String, Object> map : list) {
          values.add(map.get(str));
        }
        result.put(str, values);
      }
    }
    return result;
  }

  Map<String, Object> departmentMapping(List<Map<String, Object>> list) {
    Map<String, Object> result = new LinkedHashMap<>();
    List<Object> list1 = new ArrayList<>();
    List<Object> list2 = new ArrayList<>();
    List<Object> list3 = new ArrayList<>();
    DecimalFormat df4 = new DecimalFormat("#0.0000");
    for (Map<String, Object> map : list) {
      map.put("fin_slo_balance",df4.format(map.get("fin_slo_balance")));
      Object province = map.get("province");
      Object city = map.get("city");
      Object branchName = map.get("branch_name");
      if (!list1.contains(province)) {
        list1.add(province);
      }
      if (!list2.contains(city)) {
        list2.add(city);
      }
      if (!list3.contains(branchName)) {
        list3.add(branchName);
      }
    }
    List<Map<String, Object>> list4 = new ArrayList<>();
    for (Object obj : list1) {
      Double balance = 0.0;
      Map<String, Object> map1 = new HashMap();
      for (Map<String, Object> map : list) {
        if (obj.equals(map.get("province"))) {
          Double fin_slo_balance = Double.parseDouble(map.get("fin_slo_balance").toString()) ;
          balance = balance + fin_slo_balance;
        }
      }
      map1.put("province", obj);
      map1.put("fin_slo_balance", df4.format(balance));
      list4.add(map1);
    }
    List<Map<String, Object>> list6 = new ArrayList<>();
    for(Object obj :list2){
      Double balance = 0.0;
      Map<String, Object> map1 = new HashMap<>();
      List<Map<String,Object>> list5 = new ArrayList<>();
      List<Map<String,Object>> list7 = new ArrayList<>();
      Map<String, Object> map2 = new HashMap<>();
      for (Map<String, Object> map : list) {
        if (obj.equals(map.get("city"))) {
          Double fin_slo_balance = Double.parseDouble(map.get("fin_slo_balance").toString());
          balance = balance + fin_slo_balance;
          list5.add(map);
        }
      }
      map2.put("city", obj);
      map2.put("fin_slo_balance", df4.format(balance));
      list7.add(map2);
      map1.put("balanceDate",list5);
      map1.put("cityDate",list7);
      list6.add(map1);
    }
    result.put("nationwideDate",list4);
    result.put("provinceDate",list6);
    return result;
  }


}
