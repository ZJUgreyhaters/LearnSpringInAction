package com.quantchi.termInfo.controller;

import com.quantchi.common.ExcelUtil;
import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.common.SQLQueryConfig;
import com.quantchi.termInfo.service.TermFileService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    String mapping = sqlQueryConfig.getSEL_DB_MAPPING();
    String[] split = mapping.split(",");
    Map<String, String> map = new HashMap<String, String>();
    for (String str : split) {
      String[] split1 = str.split(":");
      map.put(split1[0], split1[1]);
    }
    List<String[]> result;

    try {
      result = ExcelUtil.readExcel(file);
    } catch (Exception e) {
      return JsonResult
          .errorJson(ResultCode.FILE_FORMAT_ERROR, "excel格式错误，上传的模板文件请另存为新的xlsx文件后上传");
    }

    try {
      String[] strings = result.get(0);
      List<String> list = new LinkedList<>();
      for (String str : strings) {
        if (map.get(str) != null) {
          list.add(map.get(str));
        }
      }

      if (list.size() != strings.length) {
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
          for (int j = 0; j < values.length; j++) {
            map1.put(list.get(j), values[j]);
          }
          List<Map<String, Object>> PhysicalInfo = termFileService.selectPhysicalInfo(map1);
          map1.put("fieldId", PhysicalInfo.get(0).get("entity_id"));
          List<Map<String, Object>> PhysicalFile = termFileService.selectPhysicalFile(map1);
          if (PhysicalFile.isEmpty() || PhysicalFile == null) {
            termFileService.insertPhysicalFile(map1);
          } else {
            //termFileService.updatePhysicalFile(map1);
            continue;
          }
        }
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
            map1.put("domainId", null);
            map1.put("entityCategory", null);
          } else {
            map1.put("domainId", entityCategory.get("domainId"));
            map1.put("entityCategory", entityCategory.get("cid"));
          }
          List<Map<String, Object>> standardMain = termFileService.selectStandardMain(map1);
          if (map1.get("dataPrecision") == "") {
            map1.put("dataPrecision", null);
          }
          if (map1.get("dataLength") == "") {
            map1.put("dataLength", null);
          }
          if (standardMain.isEmpty() || standardMain == null) {
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
          String domainId = termFileService.selectDomainByName(map1);
          map1.put("domainId", domainId);
          List<Map<String, Object>> targetMain = termFileService.selectTargetMain(map1);
          if (map1.get("dataPrecision") == "") {
            map1.put("dataPrecision", null);
          }
          if (map1.get("dataLength") == "") {
            map1.put("dataLength", null);
          }
          if (targetMain.isEmpty() || targetMain == null) {
            termFileService.insertTargetMain(map1);
          } else {
            termFileService.updateTargetMain(map1);
          }
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