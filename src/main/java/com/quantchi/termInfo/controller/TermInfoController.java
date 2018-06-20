package com.quantchi.termInfo.controller;

import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.service.TermInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
  @RequestMapping(value = "/select", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public String selectTermAll(@RequestBody TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }




}
