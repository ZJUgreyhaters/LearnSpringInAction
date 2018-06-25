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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
      List<Map<String, Object>> list = mapper.selectCustomerGroup(customerGroup);
      int total = list.size();
      if (customerGroup.getPage() != null && customerGroup.getPage_size() != null) {
        list = Paging.pagingPlug(list, customerGroup.getPage_size(), customerGroup.getPage());
      }
      Map<String, Object> result = new HashMap<String, Object>();
      // 获取总条数
      result.put("total", total);
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
      sub(customerGroupCriteria);
      String str = mapper.selectCustGroupId();
      if (str == null) {
        group.setCust_group_id("CG000001");
      }
      if (customerGroupCriteria.isEmpty() || customerGroupCriteria == null) {
        result.put("code", "500");
        result.put("msg", "条件不能为空！");
        return result;
      }
      List<Map<String, Object>> assemble = assemble(customerGroupCriteria);
      String Sql = jointSql(assemble);
      int a = insertCustomerInfo(Sql);
      if (a < -400) {
        result.put("code", "500");
        result.put("msg", "error");
        return result;
      }
      StringBuilder builder = new StringBuilder();
      for (Map<String, Object> map : customerGroupCriteria) {
        List<String> list = (List<String>) map.get("value");
        String name = map.get("name").toString();
        String obj = String.join(",", list);
        builder.append(name + ":");
        builder.append(obj + ";");
      }
      group.setCondition_desc(builder.toString());
      group.setCondition_statement(Sql);
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

  List<Map<String, Object>> assemble(List<Map<String, Object>> customerGroupCriteria) {
    RestTemplate restTemplate = new RestTemplate();
    List<Map<String, Object>> condition = new ArrayList<>();
    for (Map<String, Object> map : customerGroupCriteria) {
      Map<String, Object> conditionMap = new HashedMap();
      String id = map.get("id").toString();
      List<String> list = (List<String>) map.get("value");
      ResponseEntity<String> responseEntity = restTemplate
          .getForEntity("http://localhost:8081/term/{EntityId}", String.class, id);
      String body = responseEntity.getBody();
      JSONObject json = JSONObject.fromObject(body);
      JSONArray data = (JSONArray) json.get("data");
      String englishName = ((JSONObject) data.get(0)).get("entityName").toString();
      String type = ((JSONObject) data.get(0)).get("displayType").toString();
      String logicType = ((JSONObject) data.get(0)).get("logicType").toString();
      JSONObject physical = JSONObject.fromObject(((JSONObject) data.get(0)).get("physicalField"));
      String table = physical.get("physicalDB") + "." + physical.get("physicalTable");
      String str = null;
      if (type.equals("Select-list") && logicType.equals("代码")) {
        StringBuilder v = new StringBuilder();
        int a = 0;
        for (String split : list) {
          if (a == 0) {
            v.append("'").append(split).append("'");
          } else {
            v.append(",").append("'").append(split).append("'");
          }
          a++;
        }
        str = englishName + " in (" + v + ")";
      }
      if (type.equals("Text") && logicType.equals("文本")) {
        str = englishName + " like %" + list.get(0) + "%";
      }
      if (type.equals("Area") && logicType.equals("度量")) {
        if (list.get(0).equals("大于")) {
          str = englishName + " > " + list.get(1);
        }
        if (list.get(0).equals("小于")) {
          str = englishName + " < " + list.get(1);
        }
        if (list.get(0).equals("大于等于")) {
          str = englishName + " >= " + list.get(1);
        }
        if (list.get(0).equals("小于等于")) {
          str = englishName + " <= " + list.get(1);
        }
        if (list.get(0).equals("等于")) {
          str = englishName + " = " + list.get(1);
        }
        if (list.get(0).equals("介于")) {
          str = englishName + " between " + list.get(1) + " and " + list.get(2);
        }
      }
      if (type.equals("DateTime") && logicType.equals("日期")) {
        str = englishName + " = " + list.get(0);
      }
      if (type.equals("Text") && logicType.equals("编号")) {
        String[] splits = list.get(0).split(",");
        StringBuilder v = new StringBuilder();
        int a = 0;
        for (String split : splits) {
          if (a == 0) {
            v.append("'").append(split).append("'");
          } else {
            v.append(",").append("'").append(split).append("'");
          }
          a++;
        }
        str = englishName + " in (" + v + ")";
      }
      conditionMap.put(table, str);
      condition.add(conditionMap);
    }
    return condition;
  }

  String jointSql(List<Map<String, Object>> jointConditions) {
    Map<String, String> map = new LinkedHashMap<>();
    List<String> keys = new ArrayList<>();
    for (Map<String, Object> jointCondition : jointConditions) {
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
      for (Map<String, Object> jointCondition : jointConditions) {
        String str = jointCondition.get(keys.get(j)).toString();
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

  public String listCustomersWithDim(Map<String, Object> map) {
    try {
      CustomerGroup group = new CustomerGroup();
      if (map.get("page_size") != null && map.get("page") != null) {
        group.setPage_size(Integer.valueOf(map.get("page_size").toString()));
        group.setPage(Integer.valueOf(map.get("page").toString()));
      }
      List<Map<String, Object>> condition = (List<Map<String, Object>>) map.get("condition");
      List<Map<String, Object>> subcondition = (List<Map<String, Object>>) map.get("subcondition");
      if (subcondition != null && !subcondition.isEmpty()) {
        for (Map<String, Object> map1 : subcondition) {
          condition.add(map1);
        }
      }
      List<Map<String, Object>> assemble = assemble(condition);
      String jointSql = jointSql(assemble);
      StringBuilder sql = new StringBuilder();
      String names = sqlQueryConfig.getSEL_NAMES();
      String string = null;
      if (names != null && names.length() > 0) {
        string =
            "select '20160101' as init_date," + names
                + "condition01.customer_no,cust.customer_name,round(nvl(main_data.fin_balance/10000,0),2) as fin_balance,round(nvl(main_data.total_asset/10000,0),2) as total_asset,round(nvl(main_data.assure_debit_rate,0)*100,2) as assure_debit_rate,round(nvl(main_data.concentrate,0)*100,2) as concentrate,0 as profit_rate_y from (";
      } else {
        string =
            "select '20160101' as init_date,condition01.customer_no,cust.customer_name,round(nvl(main_data.fin_balance/10000,0),2) as fin_balance,round(nvl(main_data.total_asset/10000,0),2) as total_asset,round(nvl(main_data.assure_debit_rate,0)*100,2) as assure_debit_rate,round(nvl(main_data.concentrate,0)*100,2) as concentrate,0 as profit_rate_y from (";
      }
      sql.append(string);
      String z = " left join mtoi.dim_customer cust on condition01.customer_no=cust.customer_no";
      String w = " left join (select * from mtoi.agg_cust_statistics where part_date = 20160101) main_data on condition01.customer_no=main_data.customer_no ";
      String sqls = sqlQueryConfig.getSEL_SQLS();
      if (sqls != null && sqls.length() > 0) {
        sql.append(jointSql).append(z).append(w).append(sqls);
      } else {
        sql.append(jointSql).append(z).append(w);
      }
      List<Map<String, Object>> Resultlist = HiveLink.selectHive(sql.toString(), jdbcPool);
      List<Map<String, Object>> sub = sub(Resultlist);
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
      resultInfo.put("subcondition", sub);
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

  int insertCustomerInfo(String sql1) {
    StringBuilder sql = new StringBuilder();
    String String =
        "insert overwrite table mtoi.cust_group_detail partition (part_group_id = 'CG000002',PART_DATE = 20160103) select 'CG000002' as part_group_id, '20160101' as init_date,condition01.customer_no,"
            + "'Customer Group Service' as audit_date,from_unixtime(unix_timestamp(),'yyyy-MM-dd HH:mm:ss') as audit_time from (";
    sql.append(String).append(sql1);
    return HiveLink.elseHive(sql.toString(), jdbcPool);
  }

  public String listCustomersByCustomerGroupId(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_KLINE_COUNTRY_BY_COUNTRY();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      List<Map<String, Object>> list = HiveLink.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      int total = list.size();
      if (group.getPage() != null && group.getPage_size() != null) {
        list = Paging.pagingPlug(list, group.getPage_size(), group.getPage());
      }

      PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
      String str = JsonResult.successJson(total + "", list);
      return str.replaceAll("customer_no", "客户号")
          .replaceAll("customer_name", "姓名").replaceAll("fin_balance", "融资负债（万元）")
          .replaceAll("total_asset", "总资产（万元}").replaceAll("assure_debit_rate", "维保比例")
          .replaceAll("concentrate", "当前仓位").replaceAll("profit_rate_y", "年度收益率 ");
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
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
      if (sql == null) {
        result.put("code", "500");
        result.put("msg", "条件不能为空！");
        return result;
      }
      int a = updateCustomerInfo(sql);
      if (a < -400) {
        result.put("code", "500");
        result.put("msg", "error");
        return result;
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

  List<Map<String, Object>> sub(List<Map<String, Object>> Resultlist) {
    String ids = sqlQueryConfig.getSEL_IDS();
    List<Map<String, Object>> list1 = mapper.selectUdc(ids);
    String idsNames = sqlQueryConfig.getSEL_IDS_NAMES();
    String names = sqlQueryConfig.getSEL_NAMES();
    String[] split2 = names.split(",");
    List<String> nameList = new ArrayList<>();
    for (String sp : split2) {
      String[] split = sp.split("\\.");
      nameList.add(split[1]);
    }
    List<Map<String, Object>> list = new ArrayList<>();
    for (String name : nameList) {
      for (Map<String, Object> map1 : list1) {
        String dataUDCDesc = map1.get("dataUDCDesc").toString();
        String entityId = map1.get("entityId").toString();
        Map<String, Object> map2 = new HashedMap();
        int a = 0;
        for (Map<String, Object> result : Resultlist) {
          if (dataUDCDesc.equals(result.get(name))) {
            a++;
          }
        }
        map2.put("id", entityId);
        map2.put("value", dataUDCDesc);
        map2.put("number", a);
        list.add(map2);
      }
    }
    String[] split = idsNames.split(",");
    List<Map<String, Object>> list3 = new ArrayList<>();
    for (String idName : split) {
      String[] split1 = idName.split(":");
      Map<String, Object> map = new HashedMap();
      List<Map<String, Object>> list2 = new ArrayList<>();
      for (Map<String, Object> listMap : list) {
        if (split1[0].equals(listMap.get("id"))) {
          list2.add(listMap);
        }
      }
      map.put("id", split1[0]);
      map.put("name", split1[1]);
      map.put("option", list2);
      list3.add(map);
    }
    return list3;
  }


}
