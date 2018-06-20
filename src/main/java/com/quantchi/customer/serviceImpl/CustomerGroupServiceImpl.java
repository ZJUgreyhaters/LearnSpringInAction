package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.quantchi.common.HiveLink;
import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.common.SQLQueryConfig;
import com.quantchi.common.util;
import com.quantchi.customer.mapper.CustomerGroupMapper;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerGroupService;
import com.quantchi.tianshu.common.JdbcPool;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.map.HashedMap;
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

  @Autowired
  private SQLQueryConfig sqlQueryConfig;

  @Override
  public Map<String, Object> selectCustomerGroup(CustomerGroup customerGroup) {
    try {
      // 执行查询
      List<Map<String,Object>> list = mapper.selectCustomerGroup(customerGroup);
      int total = list.size();
      if (customerGroup.getPage() != null && customerGroup.getPage_size() != null) {
        list = Paging.pagingPlug(list, customerGroup.getPage_size(), customerGroup.getPage());
      }
      Map<String, Object> result = new HashMap<String, Object>();
      // 获取总条数
      result.put("total",total);
      // 结果rows数据
      result.put("data", list);
      return util
          .genRet(200, result.get("data"), "ok", Integer.parseInt(result.get("total").toString()));
    } catch (Exception e) {
      e.printStackTrace();
      return util.genRet(500, null, "select CustomerGroup error", 0);
    }
  }

  public Map<String, Object> createCustomerGroup(CustomerGroup group,
      List<Map<String, Object>> customerGroupCriteria) {
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
      int a = insertCustomerInfo();
      if (a < -400) {
        result.put("code", "500");
        result.put("msg", "error");
      }
      List<String> list = (List<String>) customerGroupCriteria.get(0).get("value");
      String obj = String.join(";", list);
      group.setCondition_desc(obj);
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
      if (lists.isEmpty() || keys.contains(lists.get(0))) {
        continue;
      }
      keys.add(lists.get(0));
    }
    for (int j = 0; j < keys.size(); j++) {
      int i = 0;
      StringBuilder value = new StringBuilder();
      value.append(" select distinct customer_no from " + keys.get(j));
      for (Map<String, String> jointCondition : jointConditions) {
        String str = jointCondition.get(keys.get(j));
        if (str == null) {
          continue;
        }
        i++;
        if (i == 1) {
          value.append(" where " + str);
        } else {
          value.append(" and " + str);
        }
      }
      value.append(") condition0" + (j + 1));
      map.put(keys.get(j), value.toString());
    }
    StringBuilder sql = new StringBuilder();
    //String string =
    //"select '20160101' as init_date,condition01.customer_no,cust.customer_name,round(nvl(main_data.fin_balance/10000,0),2) as fin_balance,round(nvl(main_data.total_asset/10000,0),2) as total_asset,round(nvl(main_data.assure_debit_rate,0)*100,2) as assure_debit_rate,round(nvl(main_data.concentrate,0)*100,2) as concentrate,0 as profit_rate_y,0 as stock_choose_value,0 as exchange_value,0 as avg_concentrate,0 as avg_loss_rate from (";
    // sql.append("select condition01.customer_no from (");
    // sql.append(string);
    if (keys.size() == 0) {
      return sql.toString();
    } else {
      int a = 0;
      for (String str : keys) {
        a++;
        if (a == 1) {
          sql.append(map.get(str));
        } else {
          sql.append(" inner join (").append(map.get(str))
              .append(" on condition01.customer_no=condition0" + a + ".customer_no");
        }
      }
    }
    return sql.toString();
  }

  public String listCustomersWithDim(CustomerGroup group) {
    try {
      StringBuilder sql = new StringBuilder();
      String string =
          "select '20160101' as init_date,condition01.customer_no,cust.customer_name,round(nvl(main_data.fin_balance/10000,0),2) as fin_balance,round(nvl(main_data.total_asset/10000,0),2) as total_asset,round(nvl(main_data.assure_debit_rate,0)*100,2) as assure_debit_rate,round(nvl(main_data.concentrate,0)*100,2) as concentrate,0 as profit_rate_y from (";
      sql.append(string);
      List<Map<String, String>> list = new ArrayList<>();
      Map<String, String> result = new HashMap<String, String>();
      result.put("mtoi.dim_customer", "open_date>20140101");
      list.add(result);
      Map<String, String> result1 = new HashMap<String, String>();
      result1.put("mtoi.agg_cust_statistics", "assure_debit_rate>=1.3");
      list.add(result1);
      Map<String, String> result2 = new HashMap<String, String>();
      result2.put("mtoi.agg_cust_statistics", "branch_no in ('101')");
      list.add(result2);
      String sql1 = jointSql(list);
      String z = " left join mtoi.dim_customer cust on condition01.customer_no=cust.customer_no";
      String w = " left join (select * from mtoi.agg_cust_statistics where part_date = 20160101) main_data on condition01.customer_no=main_data.customer_no";
      sql.append(sql1).append(z).append(w);
      List<Map<String, Object>> Resultlist = HiveLink.selectHive(sql.toString(), jdbcPool);
      if (Resultlist.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      int total = Resultlist.size();
      if (group.getPage() != null && group.getPage_size() != null) {
        Resultlist = Paging.pagingPlug(Resultlist, group.getPage_size(), group.getPage());
      }
      Map<String, Object> resultInfo = new HashMap<String, Object>();
      resultInfo.put("total", total);
      resultInfo.put("data_source", Resultlist);
      resultInfo.put("cust_nums", total);
      resultInfo.put("subcondition", sub());
      String str = JsonResult.successJson(resultInfo).replaceAll("customer_no", "客户号")
          .replaceAll("customer_name", "姓名").replaceAll("fin_balance", "融资负债（万元）")
          .replaceAll("total_asset", "总资产（万元}").replaceAll("assure_debit_rate", "维保比例")
          .replaceAll("concentrate", "当前仓位").replaceAll("profit_rate_y", "年度收益率 ");
      return str;
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  int insertCustomerInfo() {
    StringBuilder sql = new StringBuilder();
    String String =
        "insert overwrite table mtoi.cust_group_detail partition (part_group_id = 'CG000002',PART_DATE = 20160103) select 'CG000002' as part_group_id, '20160101' as init_date,condition01.customer_no,"
            + "'Customer Group Service' as audit_date,from_unixtime(unix_timestamp(),'yyyy-MM-dd HH:mm:ss') as audit_time from (";
    List<Map<String, String>> list = new ArrayList<>();
    Map<String, String> result = new HashMap<String, String>();
    result.put("mtoi.dim_customer", "open_date>20140101");
    list.add(result);
    Map<String, String> result1 = new HashMap<String, String>();
    result1.put("mtoi.agg_cust_statistics", "assure_debit_rate>=1.3");
    list.add(result1);
    Map<String, String> result2 = new HashMap<String, String>();
    result2.put("mtoi.agg_cust_statistics", "branch_no in ('101')");
    list.add(result2);
    String sql1 = jointSql(list);
    sql.append(String).append(sql1);
    return HiveLink.elseHive(sql.toString(), jdbcPool);
  }

  public Map<String, Object> listCustomersByCustomerGroupId(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_KLINE_COUNTRY_BY_COUNTRY();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveLink.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return util.genRet(500, null, "select CustomerGroup error", 0);
      }
      int total = list.size();
      if (group.getPage() != null && group.getPage_size() != null) {
        list = Paging.pagingPlug(list, group.getPage_size(), group.getPage());
      }

      PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
      Map<String, Object> result = new HashMap<String, Object>();
      result.put("total", total);
      result.put("data", list);
      return util
          .genRet(200, result.get("data"), "ok", Integer.parseInt(result.get("total").toString()));
    } catch (Exception e) {
      e.printStackTrace();
      return util.genRet(500, null, "select CustomerGroup error", 0);
    }
  }

  public List<Map<String, Object>> exportCustomerList(CustomerGroup group) {
    String sqlQuery = sqlQueryConfig.getSEL_KLINE_COUNTRY_BY_COUNTRY();
    sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
    List<Map<String, Object>> list = HiveLink.selectHive(sqlQuery, jdbcPool);
    return list;
  }

  public Map<String, Object> refreshCustomerGroup(CustomerGroup group) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      String sql = mapper.selectCondition(group);
      int a = updateCustomerInfo(sql);
      if (a < -400) {
        result.put("code", "500");
        result.put("msg", "error");
      }

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

  int updateCustomerInfo(String sql1) {
    StringBuilder sql = new StringBuilder();
    String String =
        "insert overwrite table mtoi.cust_group_detail partition (part_group_id = 'CG000002',PART_DATE = 20160103) select 'CG000002' as part_group_id, '20160101' as init_date,condition01.customer_no,"
            + "'Customer Group Service' as audit_date,from_unixtime(unix_timestamp(),'yyyy-MM-dd HH:mm:ss') as audit_time from (";
    sql.append(String).append(sql1);
    return HiveLink.elseHive(sql.toString(), jdbcPool);
  }

  Map<String, Object> sub() {
    Map<String, Object> map = new HashedMap();
    List<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> map1 = new HashedMap();
    Map<String, Object> map2 = new HashedMap();
    map1.put("id", "1");
    map1.put("value", "收益30%以上");
    map1.put("number", "111");
    map2.put("id", "2");
    map2.put("value", "收益30%以上");
    map2.put("number", "111");
    list.add(map1);
    list.add(map2);
    map.put("id", "1");
    map.put("name", "收益率");
    map.put("option", list);
    return map;
  }
}
