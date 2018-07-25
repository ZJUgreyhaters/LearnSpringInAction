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
public class TermFileController {

  @ResponseBody
  @RequestMapping(value = "/import", method = {RequestMethod.POST}, produces = "application/json")
  public String importExcel(
      @RequestParam(value = "file", required = false) MultipartFile file,
      String groupName
  ) {
    List<String[]> result;
    try {
      result = ExcelUtil.readExcel(file);
    } catch (Exception e) {
      return JsonResult.errorJson(ResultCode.FILE_FORMAT_ERROR, "excel格式错误，上传的关联名单文件请另存为新的xlsx文件后上传");
    }


    return "";
  }


}
