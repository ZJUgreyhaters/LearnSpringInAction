package com.quantchi.termInfo.service;

import com.quantchi.termInfo.pojo.TermGenInfo;
import com.quantchi.termInfo.pojo.TermInfoPojo;
import com.quantchi.termInfo.pojo.TermLogicCatagory;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/20.
 */
public interface TermInfoService {

  String selectTermAll(TermInfoPojo termInfoPojo);

  String selectTerm(TermInfoPojo termInfoPojo);

  String insertTerm(ArrayList<TermGenInfo> termGenInfos);

  String insertTermLogic(Map<String,Object> requestMap);
}
