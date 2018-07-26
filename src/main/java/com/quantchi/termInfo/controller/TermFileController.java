package com.quantchi.termInfo.controller;

import com.quantchi.common.ExcelUtil;
import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import com.quantchi.common.SQLQueryConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 49537 on 2018/7/25.
 */
@Controller
@RequestMapping(value = "term")
public class TermFileController {

  @Autowired
  private SQLQueryConfig sqlQueryConfig;

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
  @RequestMapping(value = "/import", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
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
    try{
    String[] strings = result.get(0);
    List<String> list = new LinkedList<>();
    for (String str : strings) {
      if (map.get(str)!=null) {
        list.add(map.get(str));
      }
    }
    if(list.size()!=strings.length){
      return JsonResult.errorJson("配置字段与传入字段不匹配");
    }
    List<Map<String, Map<String, Object>>> list1 = new ArrayList<>();
    Map<String, Map<String, Object>> map3= new HashMap<>();
    if ("standard".equals(typeOne)&&"mapping".equals(typeTwo)) {
      return JsonResult.successJson("上传成功！");
    } else if ("target".equals(typeOne) && "mapping".equals(typeTwo)) {
      return JsonResult.successJson("上传成功！");
    } else {
      for (int i = 1; i < result.size(); i++) {
        String[] values = result.get(i);
        Map<String, Object> map1 = new HashMap<>();
        for (int j = 0; j < values.length; j++) {
          map1.put(list.get(j), values[j]);
        }
        map3.put("termMainInfo",map1);
        list1.add(map3);
      }
      JSONArray jsonArray2 = JSONArray.fromObject(list1);

      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
      headers.setContentType(type);
      headers.add("Accept", MediaType.APPLICATION_JSON.toString());
      HttpEntity<String> formEntity = new HttpEntity<String>(jsonArray2.toString(), headers);

      String re = restTemplate.postForObject("http://192.168.2.136:8082/term", formEntity, String.class);
      if(re.contains("code:200")){
        return JsonResult.successJson("上传成功！");
      }else{
        return JsonResult.errorJson("上传失败！");
      }
    }
    }catch (Exception e) {
      return JsonResult.errorJson("上传失败！");
    }
  }


}
