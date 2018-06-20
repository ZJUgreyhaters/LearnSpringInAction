package com.quantchi.metadatamgr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quantchi.common.JsonResult;
import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.data.mapper.DSTableInfoDBMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetaDataMgrTableApiService {
  @Autowired
  DSTableInfoDBMapper mapper;
  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrTableApiService.class);

  public String search(DSTableInfoDB tableInfo) {
    try {
      if (tableInfo.getPage() != null && tableInfo.getPage_size() != null) {
        PageHelper.startPage(tableInfo.getPage(), tableInfo.getPage_size());
      }
      // 执行查询
      List<DSTableInfoDB> search = mapper.search(tableInfo);
      // 取分页信息
      PageInfo<DSTableInfoDB> pageInfo = new PageInfo<>(search);
      // 返回处理结果
      Map<String, Object> result = new HashMap<String, Object>();
      // 获取总条数
      result.put("total", pageInfo.getTotal());
      // 结果rows数据
      result.put("result",pageInfo.getList());
      return JsonResult.successJson(result);
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
}
