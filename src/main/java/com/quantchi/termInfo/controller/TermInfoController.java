package com.quantchi.termInfo.controller;

import com.quantchi.metadatamgr.controller.MetaDataMgrTableApiController;
import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.service.TermInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api")
public class TermInfoController {

  private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrTableApiController.class);

  @Autowired
  private TermInfoService service;

  //查询
  @ResponseBody
  @RequestMapping(value = "/select", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
  public String selectTermAll(@RequestBody TermInfoPojo termInfoPojo) {
    return service.selectTermAll(termInfoPojo);
  }




}
