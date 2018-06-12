package com.quantchi.customer.controller;

import com.quantchi.customer.service.CustomerGroupService;
import com.quantchi.tianshu.common.JdbcPool;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 49537 on 2018/6/11.
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerGroupController {

    @Autowired
    private CustomerGroupService customerGroupService;

    @RequestMapping(value = "/listCustomerGroupCriterias", method = RequestMethod.POST)
    public void listCustomerGroupCriterias(@RequestBody Map<String, String> map){
        int pagesize = Integer.parseInt(map.get("pagesize"));
        int pageIndex = Integer.parseInt(map.get("pageIndex"));
        customerGroupService.listCustomerGroupCriterias(pagesize, pageIndex);
    }

//    @RequestMapping(value = "/createCustomerGroup", method = RequestMethod.POST)
//    public Map<String, Object> createCustomerGroup(@RequestBody Map<String, Object> map){
//        customerGroupService
//
//    }

}
