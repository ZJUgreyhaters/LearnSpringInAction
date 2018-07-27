package com.quantchi.termInfo.service;

import com.quantchi.termInfo.pojo.StandardMainInfo;

/**
 * Created by 49537 on 2018/7/27.
 */
public interface StandInfoService {

  String selectListCategory(StandardMainInfo standardMainInfo);

  String selectList(StandardMainInfo standardMainInfo);
}
