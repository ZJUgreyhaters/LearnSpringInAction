package com.quantchi.customer.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.quantchi.common.HiveConnection;
import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.common.SQLQueryConfig;
import com.quantchi.common.Util;
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
      return Util
          .genRet(200, result.get("data"), "ok", Integer.parseInt(result.get("total").toString()));
    } catch (Exception e) {
      e.printStackTrace();
      return Util.genRet(500, null, "select CustomerGroup error", 0);
    }
  }

  public Map<String, Object> createCustomerGroup(CustomerGroup group,
      List<Map<String, Object>> customerGroupCriteria, List<Map<String, Object>> subcondition) {
    Map<String, Object> result = new HashMap<String, Object>();
    try {
      List<Map<String, Object>> list1 = mapper.selectName(group);
      if (list1 != null && list1.size() > 0) {
        result.put("code", "500");
        result.put("msg", "客群名称已存在！");
        return result;
      }
      if (subcondition != null && !subcondition.isEmpty()) {
        for (Map<String, Object> map1 : subcondition) {
          customerGroupCriteria.add(map1);
        }
      }
      // sub(customerGroupCriteria);
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
      int a = insertCustomerInfo(Sql, str);
      if (a < -400) {
        result.put("code", "500");
        result.put("msg", "error");
        return result;
      }
      StringBuilder builder = new StringBuilder();
      for (Map<String, Object> map : customerGroupCriteria) {
        List<String> list = null;
        if (map.get("names") == null) {
          list = (List<String>) map.get("value");
        } else {
          list = (List<String>) map.get("names");
        }
        String name = map.get("name").toString();
        String obj = String.join(",", list);
        builder.append(name + ":");
        builder.append(obj + ";");
      }
      group.setCondition_desc(builder.toString());
      group.setCondition_statement(Sql);
      group.setCust_group_id(str);
      group.setCondition_nums(customerGroupCriteria.size());
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
      String url = sqlQueryConfig.getREST_TEMPLATE_URL();
      ResponseEntity<String> responseEntity = restTemplate
          .getForEntity(url, String.class, id);
      String body = responseEntity.getBody();
      JSONObject json = JSONObject.fromObject(body);
      JSONArray data = (JSONArray) json.get("data");
      String englishName = ((JSONObject) data.get(0)).get("entityName").toString();
      String type = ((JSONObject) data.get(0)).get("displayType").toString();
      String logicType = ((JSONObject) data.get(0)).get("logicType").toString();
      JSONObject physical = JSONObject.fromObject(((JSONObject) data.get(0)).get("physicalField"));
      String table = physical.get("physicalDB") + "." + physical.get("physicalTable");
     /* if(physical.get("tablePartition").equals("1")){
        JSONArray partitionInfo = (JSONArray) physical.get("partitionInfo");
        for(partitionInfo){

        }
      }*/
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
        str = englishName + " like '%" + list.get(0) + "%'";
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
        if (jointCondition.get(keys.get(j)) == null) {
          continue;
        }
        String str = jointCondition.get(keys.get(j)).toString();
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
      Map<String, Object> configuration = configuration();
      String names = configuration.get("names").toString();
      String sqlop = sqlQueryConfig.getSEL_SQL_TOP();
      sqlop = MessageFormat.format(sqlop, names);
      sql.append(sqlop);
      String sqlBottom = sqlQueryConfig.getSEL_SQL_BOTTOM();
      sql.append(jointSql).append(" ").append(sqlBottom).append(configuration.get("sqls"));
      List<Map<String, Object>> Resultlist = HiveConnection.selectHive(sql.toString(), jdbcPool);
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

  int insertCustomerInfo(String sql1, String str) {
    StringBuilder sql = new StringBuilder();
    String string = sqlQueryConfig.getSEL_INSERT_SQL_TOP();
    string = MessageFormat.format(string, str);
    sql.append(string).append(sql1);
    return HiveConnection.elseHive(sql.toString(), jdbcPool);
  }

  public String listCustomersByCustomerGroupId(CustomerGroup group) {
    try {
      String sqlQuery = sqlQueryConfig.getSEL_KLINE_COUNTRY_BY_COUNTRY();
      sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
      if (group.getCustomer_name() != null && group.getCustomer_name().length() > 0) {
        sqlQuery = sqlQuery + " where cust.customer_name like '%" + group.getCustomer_name() + "%'";
      }
      if (group.getField() != null && group.getOrder() != null) {
        sqlQuery = sqlQuery + " order by " + group.getField() + " " + group.getOrder();
      }
      List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      int total = list.size();
      if (group.getPage() != null && group.getPage_size() != null) {
        list = Paging.pagingPlug(list, group.getPage_size(), group.getPage());
      }
      PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
      Map<String, Object> result = new LinkedHashMap<>();
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("customer_no", "客户号");
      map.put("customer_name", "姓名");
      map.put("fin_balance", "融资余额（万元）");
      map.put("total_asset", "总资产（万元）");
      map.put("assure_debit_rate", "维保比例");
      map.put("concentrate", "当前仓位");
      map.put("profit_rate_y", "年度收益率");
      result.put("title", map);
      result.put("result", list);
      String str = JsonResult.successJson(total + "", result);
      return str;
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  public List<Map<String, Object>> exportCustomerList(CustomerGroup group) {
    String sqlQuery = sqlQueryConfig.getSEL_KLINE_COUNTRY_BY_COUNTRY();
    sqlQuery = MessageFormat.format(sqlQuery, group.getCust_group_id());
    if (group.getCustomer_name() != null && group.getCustomer_name().length() > 0) {
      sqlQuery = sqlQuery + " where cust.customer_name like '%" + group.getCustomer_name() + "%'";
    }
    List<Map<String, Object>> list = HiveConnection.selectHive(sqlQuery, jdbcPool);
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
      int a = insertCustomerInfo(sql, group.getCust_group_id());
      if (a < -400) {
        result.put("code", "500");
        result.put("msg", "error");
        return result;
      }
      List<Map<String, Object>> list = selectCustomerInfo(sql, group.getCust_group_id());
      group.setCust_nums(list.size());
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

  List<Map<String, Object>> selectCustomerInfo(String sql1, String id) {
    StringBuilder sql = new StringBuilder();
    String string = sqlQueryConfig.getSEL_SEL_SQL();
    string = MessageFormat.format(string, id);
    sql.append(string).append(sql1);
    return HiveConnection.selectHive(sql.toString(), jdbcPool);
  }

  List<Map<String, Object>> sub(List<Map<String, Object>> Resultlist) {
    String ids = sqlQueryConfig.getSEL_IDS();
    List<Map<String, Object>> list3 = new ArrayList<>();
    if (ids.length() == 0 || ids == null) {
      return list3;
    }
    Map<String, Object> configuration = configuration();
    List<Map<String, Object>> list1 = mapper.selectUdc(ids);
    String idsNames = configuration.get("idNames").toString();
    String names = configuration.get("names").toString();
    String[] split2 = names.split(",");
    List<String> nameList = new ArrayList<>();
    for (String sp : split2) {
      String[] split = sp.split("\\.");
      nameList.add(split[1]);
    }
    List<Map<String, Object>> list = new ArrayList<>();
    for (Map<String, Object> map1 : list1) {
      String dataUDCValue = map1.get("dataUDCValue").toString();
      String dataUDCDesc = map1.get("dataUDCDesc").toString();
      String entityName = map1.get("entityName").toString();
      String entityId = map1.get("entityId").toString();
      Map<String, Object> map2 = new HashedMap();
      int a = 0;
      for (String name : nameList) {
        for (Map<String, Object> result : Resultlist) {
          if (entityName.equals(name) && dataUDCValue.equals(result.get(name))) {
            a++;
          }
        }
      }
      map2.put("id", entityId);
      map2.put("value", dataUDCValue);
      map2.put("name", dataUDCDesc);
      map2.put("number", a);
      list.add(map2);
    }
    for (String name : nameList) {
      for (Map<String, Object> result : Resultlist) {
        result.remove(name);
      }
    }
    String[] split = idsNames.split(",");
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


  public Map<String, Object> configuration() {
    Map<String, Object> map = new HashMap<>();
    StringBuilder idNames = new StringBuilder();
    StringBuilder Names = new StringBuilder();
    StringBuilder sqls = new StringBuilder();
    String ids = sqlQueryConfig.getSEL_IDS();
    if (ids.length() == 0 || ids == null) {
      map.put("sqls", sqls);
      map.put("names", Names);
      map.put("idNames", idNames);
      return map;
    }
    List<Map<String, Object>> list4 = mapper.selectIdAndNames(ids);
    List<Map<String, Object>> listData = new ArrayList<>();
    List<String> listkey = new ArrayList<>();
    for (Map<String, Object> map1 : list4) {
      StringBuilder dataBase = new StringBuilder();
      Map<String, Object> mapData = new HashMap<>();
      idNames.append(map1.get("entityId")).append(":").append(map1.get("entityDesc")).append(",");
      dataBase.append(map1.get("physicalDB")).append(".").append(map1.get("physicalTable"));
      if ((dataBase.toString()).equals("mtoi.dim_customer")) {
        Names.append("cust.").append(map1.get("entityName")).append(",");
      } else {
        if (!listkey.contains(dataBase.toString())) {
          listkey.add(dataBase.toString());
        }
        mapData.put(dataBase.toString(), map1.get("entityName"));
        listData.add(mapData);
      }
    }
    for (int i = 0; i < listkey.size(); i++) {
      int j = i + 1;
      sqls.append(" left join ").append(listkey.get(i)).append(" cust0" + j)
          .append(" on condition01.customer_no = ").append(" cust0" + j)
          .append(".customer_no ");
      for (Map<String, Object> map1 : listData) {
        if (map1.get(listkey.get(i)) != null) {
          Names.append(" cust0" + j).append(".")
              .append(map1.get(listkey.get(i))).append(",");
        }
      }
    }
    map.put("sqls", sqls);
    map.put("names", Names);
    map.put("idNames", idNames);
    return map;
  }

}
