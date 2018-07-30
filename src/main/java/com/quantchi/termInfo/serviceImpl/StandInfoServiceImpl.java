package com.quantchi.termInfo.serviceImpl;

import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.termInfo.mapper.StandInfoMapper;
import com.quantchi.termInfo.pojo.StandardMainInfo;
import com.quantchi.termInfo.service.StandInfoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/7/27.
 */
@Service
public class StandInfoServiceImpl implements StandInfoService {

  @Autowired
  private StandInfoMapper standInfoMapper;

  @Override
  public String selectListCategory(StandardMainInfo standardMainInfo) {
    try {
      List<Map<String, Object>> list = standInfoMapper.selectListCategory(standardMainInfo);
      List<Map<String, Object>> listMap = new ArrayList<>();
      for (Map<String, Object> map : list) {
        if (map.get("parentId") != null && map.get("parentId").toString().length() > 0) {
          continue;
        } else {
          Map<String, Object> level = new HashMap();
          Map<String, Object> domain = new HashMap();
          Map<String, Object> map2 = new HashMap();
          map2.put("id", map.get("id"));
          map2.put("name", map.get("name"));
          for (Map<String, Object> map1 : list) {
            if (Objects.equals(map1.get("parentId"), map.get("id").toString())) {
              Map<String, Object> mapChilden = new HashMap();
              mapChilden.put("id", map1.get("id"));
              mapChilden.put("name", map1.get("name"));
              mapChilden.put("parentId", map1.get("parentId"));
              for (Map<String, Object> map3 : list) {
                if (Objects.equals(map3.get("parentId"), map1.get("id").toString())) {
                  Map<String, Object> mapChilden1 = new HashMap();
                  mapChilden1.put("id", map3.get("id"));
                  mapChilden1.put("name", map3.get("name"));
                  mapChilden1.put("parentId", map3.get("parentId"));
                  mapChilden.put("children", mapChilden1);
                }
              }
              map2.put("children", mapChilden);
            }
          }
          domain.put("name", map.get("domainName"));
          domain.put("id", map.get("domainId"));
          domain.put("children",map2);
          level.put("name",map.get("levels"));
          level.put("children",domain);
          listMap.add(level);
        }
      }
      return JsonResult.successJson(listMap);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }

  }

  @Override
  public String selectList(StandardMainInfo standardMainInfo) {
    try {
      if (standardMainInfo.getEntityCategory() != null
          && standardMainInfo.getEntityCategory().length() > 0) {
        StringBuilder ids = new StringBuilder();
        ids.append("'").append(standardMainInfo.getEntityCategory()).append("'");
        String id = selectThreeId(ids.toString());
        standardMainInfo.setEntityCategory(id);
      }
      List<Map<String, Object>> resultList = standInfoMapper.selectList(standardMainInfo);
      String total =resultList.size()+"";
      if (standardMainInfo.getPage_size() != null && standardMainInfo.getPage() != null) {
        resultList = Paging
            .pagingPlug(resultList, standardMainInfo.getPage_size(), standardMainInfo.getPage());
      }
      return JsonResult.successJson(total,resultList);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }
  }

  String selectThreeId(String id) {
    List<Map<String, Object>> list = standInfoMapper.selectThreeId(id);
    String s = id;
    if (list != null && !list.isEmpty()) {
      StringBuilder ids = new StringBuilder();
      int a = 0;
      for (Map<String, Object> map : list) {
        if(a == 0){
          ids.append("'").append(map.get("id")).append("'");
        }else{
          ids.append(",").append("'").append(map.get("id")).append("'");
        }
        a++;
      }
      s = selectThreeId(ids.toString());
    }
    return s;
  }
}
