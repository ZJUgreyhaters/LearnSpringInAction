package com.quantchi.metadatamgr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.common.JsonResult;
import com.quantchi.common.Paging;
import com.quantchi.metadatamgr.data.entity.DSFieldInfoDB;
import com.quantchi.metadatamgr.data.mapper.DSFieldInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.MDFieldInfoDBMapper;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataMgrFieldApiService {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrFieldApiService.class);

  @Autowired
  private DSFieldInfoDBMapper dsFieldInfoDBMapper;

  @Autowired
  private MDFieldInfoDBMapper mdFieldInfoDBMapper;

  public Map<String, Object> search(Map<String, Object> map) {

    int page = Integer.parseInt(map.get("page").toString());
    int page_size = Integer.parseInt(map.get("page_size").toString());

    PageHelper.startPage(page, page_size);
    List<DSFieldInfoDB> list = dsFieldInfoDBMapper.selectFieldInfoBytableId(map);
    PageInfo<DSFieldInfoDB> pageInfo = new PageInfo<>(list);
    map.put("total", pageInfo.getTotal());
    map.put("data", list);
    return map;
  }

  public String searchField(Map<String, Object> map) {
    try {
      List<Map<String, Object>> listResult = mdFieldInfoDBMapper.searchField(map);
      String total = String.valueOf(listResult.size());
      if (Paging.judgment(map)) {
        int page = Integer.parseInt(map.get("page").toString());
        int page_size = Integer.parseInt(map.get("page_size").toString());
        listResult = Paging.pagingPlug(listResult, page_size, page);
      }
      return JsonResult.successJson(total,listResult);
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("searchField error", e);
      return JsonResult.errorJson("searchField error");
    }

  }

  public String insertField(Map<String, Object> map) {
    try {
      String uuid = UUID.randomUUID().toString().replace("-", "");
      map.put("entity_id",uuid);
      mdFieldInfoDBMapper.insertField(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("insertField error", e);
      return JsonResult.errorJson("insertField error");
    }
  }

  public String updateField(Map<String, Object> map) {
    try {
      mdFieldInfoDBMapper.updateField(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("updateField error", e);
      return JsonResult.errorJson("updateField error");
    }
  }

  public String deleteField(Map<String, Object> map) {
    try {
      mdFieldInfoDBMapper.deleteField(map);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      logger.info("updateField error", e);
      return JsonResult.errorJson("updateField error");
    }
  }

}
