package com.quantchi.termInfo.serviceImpl;

import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.termInfo.mapper.StandInfoMapper;
import com.quantchi.termInfo.mapper.TermFileMapper;
import com.quantchi.termInfo.pojo.StandardMainInfo;
import com.quantchi.termInfo.pojo.TermMainInfo;
import com.quantchi.termInfo.service.StandInfoService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.quantchi.termInfo.util.TermStandConstant;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 49537 on 2018/7/27.
 */
@Service
public class StandInfoServiceImpl implements StandInfoService {

  private static final Logger logger = LoggerFactory.getLogger(StandInfoServiceImpl.class);

  @Autowired
  private StandInfoMapper standInfoMapper;

  @Autowired
  private TermFileMapper termFileMapper;

  @Override
  public String selectListCategory(Map<String, Object> mapCategory) {
    try {
      List<Map<String, Object>> list = standInfoMapper.selectListCategory(mapCategory);
      List<Map<String, Object>> listMap = new ArrayList<>();
      List<Object> domainNameList = new ArrayList<>();
      List<Object> levelNameList = new ArrayList<>();
      for (Map<String, Object> map : list) {
        if (!domainNameList.contains(map.get("domainId"))) {
          domainNameList.add(map.get("domainId"));
        }
        if (!levelNameList.contains(map.get("levels"))) {
          levelNameList.add(map.get("levels"));
        }
      }
      for (Object levelName : levelNameList) {
        List<Map<String, Object>> levelList = new ArrayList<>();
        Map<String, Object> level = new HashMap<>();
        level.put("name", levelName);
        level.put("id", levelName);
        for (Object dominName : domainNameList) {
          List<Map<String, Object>> domainList = new ArrayList<>();
          Map<String, Object> domain = new HashMap<>();
          StringBuilder name = new StringBuilder();
          name.append(levelName).append("_").append(dominName);
          domain.put("id", name);
          domain.put("name", dominName);
          int a = 0;
          for (Map<String, Object> map : list) {
            if (levelName.equals(map.get("levels")) && dominName.equals(map.get("domainId"))) {
              if (map.get("parentId") != null && map.get("parentId").toString().length() > 0) {
                continue;
              } else {
                a = 1;
                Map<String, Object> map2 = new HashMap();
                map2.put("id", map.get("id"));
                map2.put("name", map.get("name"));
                List<Map<String, Object>> childrenTwoList = new ArrayList<>();
                for (Map<String, Object> map1 : list) {
                  if (Objects.equals(map1.get("parentId"), map.get("id").toString())) {
                    Map<String, Object> mapChilden = new HashMap<>();
                    mapChilden.put("id", map1.get("id"));
                    mapChilden.put("name", map1.get("name"));
                    mapChilden.put("parentId", map1.get("parentId"));
                    List<Map<String, Object>> childrenList = new ArrayList<>();
                    for (Map<String, Object> map3 : list) {
                      if (Objects.equals(map3.get("parentId"), map1.get("id").toString())) {
                        Map<String, Object> mapChilden1 = new HashMap<>();
                        mapChilden1.put("id", map3.get("id"));
                        mapChilden1.put("name", map3.get("name"));
                        mapChilden1.put("parentId", map3.get("parentId"));
                        childrenList.add(mapChilden1);
                      }
                    }
                    mapChilden.put("children", childrenList);
                    childrenTwoList.add(mapChilden);
                  }
                }
                map2.put("children", childrenTwoList);
                domainList.add(map2);
              }
            }
          }
          if (a == 1) {
            domain.put("children", domainList);
            levelList.add(domain);
          }
        }
        level.put("children", levelList);
        listMap.add(level);
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
      if (standardMainInfo.getEntityDomainId() != null
          && standardMainInfo.getEntityDomainId().length() > 0) {
        String entityDomainId = standardMainInfo.getEntityDomainId();
        String[] split = entityDomainId.split("_");
        standardMainInfo.setOneName(split[0]);
        standardMainInfo.setTwoName(split[1]);
        List<Map<String, Object>> list = standInfoMapper.selectIdByDomainId(standardMainInfo);
        StringBuilder ids = new StringBuilder();
        int a = 1;
        for (Map<String, Object> map : list) {
          if (a == 1) {
            ids.append("'").append(map.get("id")).append("'");
          } else {
            ids.append(",").append("'").append(map.get("id")).append("'");
          }
          a++;
        }
        standardMainInfo.setEntityCategory(ids.toString());
      }
      if (standardMainInfo.getEntityType() != null
          && standardMainInfo.getEntityType().length() > 0) {
        standardMainInfo.setOneName(standardMainInfo.getEntityType());
        List<Map<String, Object>> list = standInfoMapper.selectIdByDomainId(standardMainInfo);
        StringBuilder ids = new StringBuilder();
        int a = 1;
        for (Map<String, Object> map : list) {
          if (a == 1) {
            ids.append("'").append(map.get("id")).append("'");
          } else {
            ids.append(",").append("'").append(map.get("id")).append("'");
          }
          a++;
        }
        standardMainInfo.setEntityCategory(ids.toString());
      }
      List<Map<String, Object>> resultList = standInfoMapper.selectList(standardMainInfo);
      /*for (Map<String, Object> map : resultList) {
        List<Map<String, Object>> list = standInfoMapper.selectListCategory(map);
        if (list != null && list.get(0) != null) {
          Map<String, Object> entityCategory = new HashMap<>();
          entityCategory.put("value", list.get(0).get("id"));
          entityCategory.put("label", list.get(0).get("name"));
          map.put("entityCategory", entityCategory);
        }
      }*/
      String total = resultList.size() + "";
      if (standardMainInfo.getPage_size() != null && standardMainInfo.getPage() != null) {
        resultList = Paging
            .pagingPlug(resultList, standardMainInfo.getPage_size(), standardMainInfo.getPage());
      }
      return JsonResult.successJson(total, resultList);
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
        if (a == 0) {
          ids.append("'").append(map.get("id")).append("'");
        } else {
          ids.append(",").append("'").append(map.get("id")).append("'");
        }
        a++;
      }
      s = selectThreeId(ids.toString());
    }
    return s;
  }

  @Override
  public String selectMetric(TermMainInfo termMainInfo) {
    try {
      //logger.info("begin to select Metric");
      if (termMainInfo.getEntityCategory() != null
          && termMainInfo.getEntityCategory().length() > 0) {
        String ids = termMainInfo.getEntityCategory();
        String[] splits = ids.split("--");
        if (splits.length == 1) {
          termMainInfo.setOneName(splits[0]);
        } else if (splits.length == 2) {
          termMainInfo.setOneName(splits[0]);
          termMainInfo.setTwoName(splits[1]);
        } else if (splits.length == 3) {
          termMainInfo.setOneName(splits[0]);
          termMainInfo.setTwoName(splits[1]);
          termMainInfo.setThreeName(splits[2]);
        }
      }

      //logger.info("begin to  call sql select Metric");
      List<Map<String, Object>> list = standInfoMapper.selectMetric(termMainInfo);
      //logger.info("begin to  loop result");
      //不用每次都去db查，一次查询，建好层级关系，然后相应的更新 entityCategory
      //这块会导致性能很差
      List<Map<String, Object>> list1 = standInfoMapper.selectBusiness(null);
      Map<String, Object> entityCategoryMap = list1.stream().collect(Collectors.toMap(i->i.get("id").toString(),i->i));

      //update 10.6 这块其实是对没有格式化的那些entityCategory进行补充
      //历史遗留问题导致部分没有被格式化
      //后期观察确定是否还需要该段逻辑
      for (Map<String, Object> map : list) {
        //如果已经格式化了就不需要再处理了
        if(map.get("entityCategory").toString().indexOf("--") > 0)
          continue;

        if(entityCategoryMap.containsKey(map.get("entityCategory").toString())){
          Map<String,Objects> item = (Map<String, Objects>) entityCategoryMap.get(map.get("entityCategory").toString());
          StringBuilder str = new StringBuilder();
          /*str.append(list1.get(0).get("businessTypeId")).append("--")
                  .append(list1.get(0).get("domainId")).append("--")
                  .append(list1.get(0).get("logicTableId"));*/
          str.append(item.get("businessTypeId")).append("--")
                  .append(item.get("domainId")).append("--")
                  .append(item.get("logicTableId"));
          map.put("entityCategory", str);
        }
      }

      //logger.info("begin to  paging");
      String total = list.size() + "";
      if (termMainInfo.getPage_size() != null && termMainInfo.getPage() != null) {
        list = Paging
            .pagingPlug(list, termMainInfo.getPage_size(), termMainInfo.getPage());
      }
      //logger.info("begin to return");
      return JsonResult.successJson(total, list);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }
  }

  @Override
  public String selectCodeDefinition(Map<String, Object> map) {
    try {
      List<Map<String, Object>> list = standInfoMapper.selectCodeDefinition(map);
      return JsonResult.successJson(list);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }
  }

  @Override
  public String selectBusiness(Map<String, Object> map) {
    try {
      List<Map<String, Object>> list = standInfoMapper.selectBusiness(map);
      List<String> name = new ArrayList<>();
      List<Map<String, Object>> listMap = new ArrayList<>();
      for (Map<String, Object> map1 : list) {
        if (name.contains(map1.get("businessTypeName"))) {
          continue;
        } else {
          name.add(map1.get("businessTypeName").toString());
          Map<String, Object> businessTypeName = new HashMap<>();
          List<Map<String, Object>> list1 = new ArrayList<>();
          businessTypeName.put("id", map1.get("businessTypeId"));
          businessTypeName.put("name", map1.get("businessTypeName"));
          List<String> name1 = new ArrayList<>();
          for (Map<String, Object> map2 : list) {
            if (map1.get("businessTypeName").equals(map2.get("businessTypeName")) && !name1
                .contains(map2.get("domainName"))) {
              name1.add(map2.get("domainName").toString());
              Map<String, Object> domainName = new HashMap<>();
              List<Map<String, Object>> list2 = new ArrayList<>();
              StringBuilder ids = new StringBuilder();
              ids.append(map1.get("businessTypeId")).append("--").append(map2.get("domainId"));
              domainName.put("id", ids);
              domainName.put("name", map2.get("domainName"));
              for (Map<String, Object> map3 : list) {
                if (map3.get("businessTypeName").equals(map1.get("businessTypeName")) && map3
                    .get("domainName").equals(map2.get("domainName"))) {
                  Map<String, Object> logicTableName = new HashMap<>();
                  StringBuilder logicTableIds = new StringBuilder();
                  logicTableIds.append(map1.get("businessTypeId")).append("--")
                      .append(map2.get("domainId")).append("--").append(map3.get("logicTableId"));
                  logicTableName.put("id", logicTableIds);
                  logicTableName.put("name", map3.get("logicTableName"));
                  list2.add(logicTableName);
                }
              }
              domainName.put("children", list2);
              list1.add(domainName);
            }
          }
          businessTypeName.put("children", list1);
          listMap.add(businessTypeName);
        }
      }
      return JsonResult.successJson(listMap);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }
  }

  @Override
  public String selectPhysicalProperty(Map<String, Object> map) {
    try {
      List<Map<String, Object>> list = standInfoMapper.selectPhysicalProperty(map);
      return JsonResult.successJson(list);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }
  }

  @Override
  public List<Map<String, Object>> selectMetricByEntityName(StandardMainInfo standardMainInfo) {
    return standInfoMapper.selectMetricByEntityName(standardMainInfo);
  }

  @Override
  public String selectMapping(Map<String, Object> map) {
    try {
      List<Map<String, Object>> list = termFileMapper.selectPhysicalFile(map);
      String total = String.valueOf(list.size());
      if (Paging.judgment(map)) {
        int page = Integer.parseInt(map.get("psge").toString());
        int pageSize = Integer.parseInt(map.get("psge_size").toString());
        list = Paging.pagingPlug(list, pageSize, page);
      }
      return JsonResult.successJson(total, list);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("select error！");
    }
  }

  @Override
  public String insertMapping(Map<String, Object> map) {
    try {
      termFileMapper.insertPhysicalFile(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("insert error！");
    }
  }

  @Override
  public String deleteMapping(Map<String, Object> map) {
    try {
      String ids = map.get("id").toString();
      ids = "'" + ids.replaceAll(",", "','") + "'";
      map.put("id",ids);
      termFileMapper.deleteMapping(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("delete error！");
    }
  }

  @Override
  public String insertStandard(Map<String, Object> map) {
    try {
      if (map.get("entity_id") != null && map.get("entity_id").toString().length() > 0) {
        termFileMapper.updateStandardMain(map);
      } else {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        map.put("entityId", uuid);
        termFileMapper.insertStandardMain(map);
      }
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("insertStandard error！");
    }
  }

  @Override
  public String deleteStandard(Map<String, Object> map) {
    try {
      String ids = map.get("id").toString();
      ids = "'" + ids.replaceAll(",", "','") + "'";
      map.put("id",ids);
      termFileMapper.deleteStandard(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("deleteStandard error！");
    }
  }

  @Override
  public String insertStandardRelation(Map<String, Object> map) {
    try {
      List<Map<String, Object>> resultList = termFileMapper.selectStandard(map);
      if (resultList == null || resultList.isEmpty()) {
        termFileMapper.insertStandard(map);
      } else {
        termFileMapper.updateStandard(map);
      }
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("deleteStandardRelation error！");
    }
  }

  @Override
  public String deleteStandardRelation(Map<String, Object> map) {
    try {
      String ids = map.get("id").toString();
      ids = "'" + ids.replaceAll(",", "','") + "'";
      map.put("id",ids);
      termFileMapper.deleteStandardRelation(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("deleteStandardRelation error！");
    }
  }

  @Override
  public String selectStandardRelation(Map<String, Object> map) {
    try {
      List<Map<String, Object>> result = termFileMapper.selectStandardRelation(map);
      String total = String.valueOf(result.size());
      if (Paging.judgment(map)) {
        int page = Integer.parseInt(map.get("psge").toString());
        int pageSize = Integer.parseInt(map.get("psge_size").toString());
        result = Paging.pagingPlug(result, pageSize, page);
      }
      return JsonResult.successJson(total, result);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("selectStandardRelation error！");
    }
  }

  @Override
  public String selectOperation(Map<String, Object> map) {
    try {
      List<Map<String, Object>> result = termFileMapper.selectOperation(map);
      String total = String.valueOf(result.size());
      if (Paging.judgment(map)) {
        int page = Integer.parseInt(map.get("psge").toString());
        int pageSize = Integer.parseInt(map.get("psge_size").toString());
        result = Paging.pagingPlug(result, pageSize, page);
      }
      return JsonResult.successJson(total, result);
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("selectOperation error！");
    }
  }

  public String getTermEntityId(String type){
    String entityId = "";
    switch (type){
      case TermStandConstant.BAISC_TERM_TYPE:
        entityId = TermStandConstant.BAISC_TERM_PREV;
        break;
      case TermStandConstant.DERIVATIVE_TERM_TYPE:
        entityId = TermStandConstant.DERIVATIVE_TERM_PREV;
        break;
      default:
        entityId = TermStandConstant.BAISC_TERM_PREV;
        break;
    }

    Integer count = standInfoMapper.getMaxNum(entityId);
    entityId += String.format("%06d", ++count);
    return entityId;
  }

  @Override
  public String deleteTarget(Map<String, Object> map) {
    try {
      String ids = map.get("id").toString();
      ids = "'" + ids.replaceAll(",", "','") + "'";
      map.put("id",ids);
      termFileMapper.deleteTarget(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("deleteStandardRelation error！");
    }
  }
}
