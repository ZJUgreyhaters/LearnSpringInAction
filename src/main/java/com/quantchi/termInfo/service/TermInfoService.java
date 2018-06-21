package com.quantchi.termInfo.service;

import com.quantchi.termInfo.pojo.TermInfoPojo;

/**
 * Created by 49537 on 2018/6/20.
 */
public interface TermInfoService {

  String selectTermAll(TermInfoPojo termInfoPojo);

  String selectTerm(TermInfoPojo termInfoPojo);

}
