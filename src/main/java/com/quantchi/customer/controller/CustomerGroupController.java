package com.quantchi.customer.controller;

import com.quantchi.customer.pojo.CustomerGroup;
import com.quantchi.customer.service.CustomerGroupService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 49537 on 2018/6/11.
 */
@Controller
@RequestMapping("api/customer")
public class CustomerGroupController {

  @Autowired
  private CustomerGroupService service;

  //客群列表查看
  @ResponseBody
  @RequestMapping(value = "/listCustomerGroups", method = {RequestMethod.POST})
  public Map<String, Object> listCustomerGroups(@RequestBody CustomerGroup role, Integer pageIndex,
      Integer pagesize) {
    return service.selectCustomerGroup(role, pageIndex, pagesize);
  }


}
