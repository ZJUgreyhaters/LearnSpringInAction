package com.quantchi.termInfo.controller;

import com.quantchi.common.ExcelUtil;
import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.common.SQLQueryConfig;
import com.quantchi.intelquery.search.SearchEng;
import com.quantchi.intelquery.search.SolrEng;
import com.quantchi.lineage.exception.SqlParserException;
import com.quantchi.lineage.metric.MetricLineage;
import com.quantchi.termInfo.service.TermFileService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 49537 on 2018/7/25.
 */
@Controller
@RequestMapping(value = "term")
public class TermFileController {

  @Autowired
  private SQLQueryConfig sqlQueryConfig;

  @Autowired
  private TermFileService termFileService;

  private static final String SEARCHTYPE = "solr";

  private static final Logger logger = LoggerFactory.getLogger(TermFileController.class);

  /**
   * @api {post} /term/import 文件上传接口
   * @apiVersion 1.0.0
   * @apiSampleRequest http://192.168.2.61:8082/quantchiAPI/term/import
   * @apiName importExcel
   * @apiGroup TermFileController
   * @apiParam {MultipartFile} file 文件
   * @apiParam {String} [typeOne] 类型1（标准：standard，指标：target）
   * @apiParam {String} [typeTwo] 类型2（映射：mapping,普通：common）
   * @apiSuccess {String} code 成功或者错误代码200成功，500错误
   * @apiSuccess {String} msg  成功或者错误信息
   */

  @ResponseBody
  @RequestMapping(value = "/import", method = {
      RequestMethod.POST}, produces = "application/json;charset=UTF-8")
  public String importExcel(
      @RequestParam(value = "file", required = false) MultipartFile file, String typeOne,
      String typeTwo
  ) {
    List<String[]> result;
    try {
      result = ExcelUtil.readExcel(file);
    } catch (Exception e) {
      return JsonResult
          .errorJson(ResultCode.FILE_FORMAT_ERROR, "excel格式错误，上传的模板文件请另存为新的xlsx文件后上传");
    }

    String mapping = null;
    if ("standard".equals(typeOne) && "mapping".equals(typeTwo)) {
      mapping = sqlQueryConfig.getSEL_DB_STANDARD_MAPPING();
    } else if ("target".equals(typeOne) && "mapping".equals(typeTwo)) {
      mapping = sqlQueryConfig.getSEL_DB_TARGET_MAPPING();
    } else if ("standard".equals(typeOne) && "common".equals(typeTwo)) {
      mapping = sqlQueryConfig.getSEL_DB_STANDARD_COMMON();
    } else if ("target".equals(typeOne) && "common".equals(typeTwo)) {
      mapping = sqlQueryConfig.getSEL_DB_TARGET_COMMON();
    }
    String[] split = mapping.split(",");
    Map<String, String> map = new HashMap<String, String>();
    for (String str : split) {
      String[] split1 = str.split(":");
      map.put(split1[0], split1[1]);
    }
    try {
      String[] strings = result.get(0);
      List<String> list = new LinkedList<>();
      List<String> listStrings = new LinkedList<>();
      for (String str : strings) {
        if (str.length() == 0) {
          continue;
        }
        listStrings.add(str);
        if (map.get(str) != null) {
          list.add(map.get(str));
        }
      }

      if (list.size() != listStrings.size()) {
        return JsonResult.errorJson("配置字段与传入字段不匹配");
      }

      if ("standard".equals(typeOne) && "mapping".equals(typeTwo)) {
        for (int i = 1; i < result.size(); i++) {
          String[] values = result.get(i);
          Map<String, Object> map1 = new HashMap<>();
          for (int j = 0; j < values.length; j++) {
            map1.put(list.get(j), values[j]);
          }
          List<Map<String, Object>> resultList = termFileService.selectStandard(map1);
          if (resultList.isEmpty() || resultList == null) {
            termFileService.insertStandard(map1);
          } else {
            termFileService.updateStandard(map1);
          }
        }
        return JsonResult.successJson("上传成功！");
      } else if ("target".equals(typeOne) && "mapping".equals(typeTwo)) {
        for (int i = 1; i < result.size(); i++) {
          String[] values = result.get(i);
          Map<String, Object> map1 = new HashMap<>();
          for (int j = 0; j < listStrings.size(); j++) {
            map1.put(list.get(j), values[j]);
          }
          List<Map<String, Object>> PhysicalInfo = termFileService.selectPhysicalInfo(map1);
          if (PhysicalInfo != null && !PhysicalInfo.isEmpty()) {
            map1.put("fieldId", PhysicalInfo.get(0).get("entity_id"));
          } else {
            map1.put("fieldId", null);
          }
          List<Map<String, Object>> PhysicalFile = termFileService.selectPhysicalFile(map1);
          if (PhysicalFile == null || PhysicalFile.isEmpty()) {
            termFileService.insertPhysicalFile(map1);
          }
          /*else {
            //termFileService.updatePhysicalFile(map1);
            continue;
          }*/

          List<Map<String, Object>> list1 = termFileService.selectTargetMain(map1);

          if (list1 != null && list.size() > 0){
            //force update physical_field_desc
            Map<String, Object> updateMap = new HashMap<>(3);
            updateMap.put("fieldId", map1.get("fieldId"));
            updateMap.put("entityDesc", list1.get(0).get("entity_desc"));
            termFileService.updatePhysicalFieldChineseName(updateMap);
          }

          if (list1 != null && !list1.isEmpty() && list1.get(0).get("technique_rule") != null
              && list1.get(0).get("technique_rule").toString().length() > 0) {

            try {
              MetricLineage.loadLineage(list1.get(0).get("entity_id").toString(),
                  list1.get(0).get("technique_rule").toString());
            } catch (SqlParserException e) {
              logger.info("entity_id:" + list1.get(0).get("entity_id").toString());
              logger.info("sql:" + list1.get(0).get("technique_rule").toString());
              //e.printStackTrace();
            } catch (Exception e) {
              logger.info("entity_id:" + list1.get(0).get("entity_id").toString());
              logger.info("sql:" + list1.get(0).get("technique_rule").toString(),e);
              //e.printStackTrace();
            }
          }
        }

        //add reIndex
        SearchEng engObj = SearchEng.instanceOf("", SEARCHTYPE);
        ((SolrEng)engObj).metricsImport();

        return JsonResult.successJson("上传成功！");
      } else if ("standard".equals(typeOne) && "common".equals(typeTwo)) {
        for (int i = 1; i < result.size(); i++) {
          String[] values = result.get(i);
          Map<String, Object> map1 = new HashMap<>();
          for (int j = 0; j < values.length; j++) {
            map1.put(list.get(j), values[j]);
          }
          Map<String, Object> entityCategory = termFileService.selectStandardCategory(map1);
          if (entityCategory == null || entityCategory.isEmpty()) {
            map1.put("entityCategory", null);
          } else {
            map1.put("entityCategory", entityCategory.get("cid"));
          }
          List<Map<String, Object>> standardMain = termFileService.selectStandardMain(map1);
          if (Objects.equals(map1.get("dataPrecision"), "")) {
            map1.put("dataPrecision", null);
          }
          if (Objects.equals(map1.get("dataLength"), "")) {
            map1.put("dataLength", null);
          }
          if (standardMain == null || standardMain.isEmpty()) {
            termFileService.insertStandardMain(map1);
          } else {
            termFileService.updateStandardMain(map1);
          }
        }
        return JsonResult.successJson("上传成功！");
      } else if ("target".equals(typeOne) && "common".equals(typeTwo)) {
        for (int i = 1; i < result.size(); i++) {
          String[] values = result.get(i);
          Map<String, Object> map1 = new HashMap<>();
          for (int j = 0; j < values.length; j++) {
            map1.put(list.get(j), values[j]);
          }
          Map<String, Object> domainId = termFileService.selectDomainByName(map1);
          if (domainId != null) {
            map1.put("domainId", domainId.get("domainId"));
            map1.put("entityCategory", domainId.get("id"));
          }
          List<Map<String, Object>> targetMain = termFileService.selectTargetMain(map1);
          if (Objects.equals(map1.get("dataPrecision"), "")) {
            map1.put("dataPrecision", null);
          }
          if (Objects.equals(map1.get("dataLength"), "")) {
            map1.put("dataLength", null);
          }
          if (targetMain == null || targetMain.isEmpty()) {
            termFileService.insertTargetMain(map1);
          } else {
            termFileService.updateTargetMain(map1);
          }
         /* if (map1.get("techniqueRule") != null
              && map1.get("techniqueRule").toString().length() > 0) {
            List<Map<String, Object>> list1 = termFileService.selectPhysicalFile(map1);
            if (list1 != null && !list1.isEmpty()) {
              MetricLineage.loadLineage(map1.get("entityId").toString(),
                  map1.get("techniqueRule").toString());
            }
          }*/
        }
        return JsonResult.successJson("上传成功！");
      } else {
        return JsonResult.errorJson("上传失败！");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return JsonResult.errorJson("上传失败！");
    }
  }


}
