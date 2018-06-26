package com.quantchi.customer.service;

import com.quantchi.customer.pojo.CustomerGroup;

/**
 * Created by 49537 on 2018/6/26.
 */
public interface CustomerPortrayalService {

  String selectRatio(CustomerGroup group);

  String selectAnalyze(CustomerGroup group);

  String selectYield(CustomerGroup group);

  String selectGrade(CustomerGroup group);

  String selectDepartment(CustomerGroup group);

  String selectPreference(CustomerGroup group);
}
