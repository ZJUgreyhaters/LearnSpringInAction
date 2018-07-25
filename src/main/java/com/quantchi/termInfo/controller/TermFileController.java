package com.quantchi.termInfo.controller;

import com.quantchi.common.ExcelUtil;
import com.quantchi.common.JsonResult;
import com.quantchi.common.ResultCode;
import java.util.List;
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

  /**
   * @api {get} /term/import 文件上传接口
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
  @RequestMapping(value = "/import", method = {RequestMethod.POST}, produces = "application/json")
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
    if ("standard".equals(typeOne) && "mapping".equals(typeTwo)) {
      return "标准映射模板插入成功";
    } else if ("target".equals(typeOne) && "mapping".equals(typeTwo)) {
      return "指标映射模板插入成功";
    } else {
      return "插入成功";
    }
  }


}
