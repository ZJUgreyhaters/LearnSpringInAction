package com.quantchi.customer.serviceImpl;

import com.quantchi.common.HiveLink;
import com.quantchi.common.JsonResult;
import com.quantchi.common.SQLQueryConfig;
import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerPortrayalService;
import com.quantchi.tianshu.common.JdbcPool;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.map.HashedMap;
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
      List<Map<String, Object>> list = HiveLink.selectHive(sqlQuery, jdbcPool);
      if (list.toString().contains("select error")) {
        return JsonResult.errorJson("error");
      }
      Map<String, Object> result = new HashedMap();
      if (list != null && list.size() > 0) {
        Set<String> strings = list.get(0).keySet();
        List<String> keys = new ArrayList<>(strings);
        for (String str : keys) {
          List<Object> values = new LinkedList<>();
          for (Map<String, Object> map : list) {
            values.add(map.get(str));
          }

        }
      }
      return JsonResult.successJson(list);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectAnalyze(CustomerGroup group) {
    try {
      return "";
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectYield(CustomerGroup group) {
    try {
      return "";
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectGrade(CustomerGroup group) {
    try {
      return "";
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectDepartment(CustomerGroup group) {
    try {
      return "";
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }

  @Override
  public String selectPreference(CustomerGroup group) {
    try {
      return "";
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("error");
    }
  }
}
