package com.quantchi.termInfo.controller;

import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.service.TermInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 49537 on 2018/6/20.
 */
@Controller
public class TermInfoController {


  @Autowired
  private TermInfoService service;

  //查询
  @ResponseBody
  @RequestMapping(value = "/term", method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
  public String selectTermAll(TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }

  //查询
  @ResponseBody
  @RequestMapping(value = "/term/{EntityId}", method = {RequestMethod.GET},produces = "application/json;charset=UTF-8")
  public String selectTerm(@PathVariable String EntityId,TermInfoPojo termInfoPojo) {
    termInfoPojo.setEntityId(EntityId);
    return service.selectTerm(termInfoPojo);
  }


}
