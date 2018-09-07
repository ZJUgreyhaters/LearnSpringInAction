package com.quantchi.metadatamgr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.common.JsonResult;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.data.mapper.DSFieldRelDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSMetaInfoDBMapper;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataMgrTableApiService {

  @Autowired
  DSFieldRelDBMapper dsFieldRelDBMapper;
  @Autowired
  DSTableInfoDBMapper mapper;
  @Autowired
  DSMetaInfoDBMapper dsMetaInfoDBMapper;

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrTableApiService.class);

  public String search(DSTableInfoDB tableInfo) {
    try {
      // 执行查询
      String data_source_id = tableInfo.getDataSourceId();
      if (data_source_id != null) {
        tableInfo.setDatasourceId(data_source_id);
      }
      if (tableInfo.getPage() != null && tableInfo.getPage_size() != null) {
        PageHelper.startPage(tableInfo.getPage(), tableInfo.getPage_size());
      }
      List<DSTableInfoDB> search = mapper.search(tableInfo);
      // 取分页信息
      PageInfo<DSTableInfoDB> pageInfo = new PageInfo<>(search);

//      List<DSTableInfoDB> dsTableInfoDBList = new ArrayList<>();
//      for(DSTableInfoDB dsTableInfoDB : pageInfo.getList()){
//        if(!dsTableInfoDB.getDatasourceId().matches("^[0-9]*$")){
//          DSMetaInfoDBExample dsMetaInfoDBExample = new DSMetaInfoDBExample();
//          dsMetaInfoDBExample.createCriteria().andIdEqualTo(Integer.parseInt(dsTableInfoDB.getDatasourceId()));
//          List<DSMetaInfoDB> dsMetaInfoDBList = dsMetaInfoDBMapper.selectAllByExample(dsMetaInfoDBExample);
//          dsTableInfoDB.setDatasourceId(dsMetaInfoDBList.get(0).getId().toString());
//        }
//        dsTableInfoDBList.add(dsTableInfoDB);
//      }
      return JsonResult.successJson(pageInfo.getTotal() + "", pageInfo.getList());
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("search tableInfo error");
    }
  }

  public String delete(DSTableInfoDB tableInfo) {
    try {
      mapper.delete(tableInfo);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("delete tableInfo error");
    }
  }

  public String update(DSTableInfoDB tableInfo) {
    try {
      mapper.update(tableInfo);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("update tableInfo error");
    }
  }

  public String check(DSTableInfoDB tableInfo) {
    try {
      mapper.update(tableInfo);
      return JsonResult.successJson();
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("check tableInfo error");
    }
  }

  public List<Map<String, String>> foreignkeys(Map<String, Object> map) throws Exception {

    return dsFieldRelDBMapper.foreignkeys(map);
  }
}
