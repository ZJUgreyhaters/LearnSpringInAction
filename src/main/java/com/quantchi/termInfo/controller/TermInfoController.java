package com.quantchi.termInfo.controller;

import com.quantchi.common.JsonResult;
import com.quantchi.termInfo.pojo.TermGenInfo;
import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.service.TermInfoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 49537 on 2018/6/20.
 */
@Controller
@Api(value = "TermInfoController")
@RequestMapping(value = "/term")
public class TermInfoController {


  @Autowired
  private TermInfoService service;

  //查询
  @ResponseBody
  @ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = TermInfoPojo.class, notes = "根据用户名获取用户对象")
  @RequestMapping(value = "/term", method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
  public String selectTermAll(@ApiParam(
      value = "dateType <--- D"
  )TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }

  //查询
  @ResponseBody
  @RequestMapping(value = "/term/{EntityId}", method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
  public String selectTerm(@PathVariable String EntityId,TermInfoPojo termInfoPojo) {
    termInfoPojo.setEntityId(EntityId);
    return service.selectTerm(termInfoPojo);
  }

  //插入
  @ResponseBody
  @RequestMapping(value = "/term", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public String selectTerm(@RequestBody ArrayList<TermGenInfo> termGenInfos) {
    try{
      return service.insertTerm(termGenInfos);
    }catch (Exception e){
      return JsonResult.errorJson(e.getMessage());
    }
  }

  //termLogicCategory
  @ResponseBody
  @RequestMapping(value = "/insertTermLogic", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public String insertTermLogic(@RequestBody Map<String,Object> requestMap){
    try {
      return service.insertTermLogic(requestMap);
    }catch (Exception e){
      return JsonResult.errorJson(e.getMessage());
    }
  }

}
