package com.quantchi.customer.controller;

import com.quantchi.customer.pojo.ConditionGroup;
import com.quantchi.customer.service.ConditionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api")
public class ConditionGroupController {

    @Autowired
    private ConditionGroupService conditionGroupService;

    @RequestMapping(value = "/listCustomerGroupCriterias", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> listCustomerGroupCriterias(@RequestBody Map<String,String> requestMap){
        Integer page_size =Integer.parseInt(requestMap.get("page_size"));
        Integer page =Integer.parseInt(requestMap.get("page"));
        Map<String, Object> map = new HashMap<>();
        List<Object> list = null;
        try {
            list = conditionGroupService.listCustomerGroupCriterias(page_size,page);
            map.put("code",200);
            map.put("msg","查询成功");
            map.put("data",list);
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg",e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/findCustomerGroup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCustomerGroup(@RequestBody Map<String, String> map){
        Map<String, Object> responseMap = conditionGroupService.findCustomerGroup(map.get("cunstomerGroupId"));
        responseMap.put("code",200);
        responseMap.put("msg","查询成功");
        return  responseMap;
    }

    @RequestMapping(value = "/createCustomerGroupCriteria", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createCustomerGroupCriteria(@RequestBody Map<String, Object> requestMap){
        Map<String, String> responseMap = new HashMap<>();
        String condition_desc = "";
        String condition_desc_id = "";
        try {
            List<Map<String, Object>> CustomerGroupCriteriaDef = (List<Map<String, Object>>) requestMap.get("CustomerGroupCriteriaDef");
            for(Map<String, Object> listMap : CustomerGroupCriteriaDef){
                condition_desc += listMap.get("name") + ":" + listMap.get("value") + "|";
                condition_desc_id += "id:" + listMap.get("id") + ",type:" + listMap.get("type") +"|";
            }
            requestMap.put("condition_desc", condition_desc);
            requestMap.put("condition_desc_id", condition_desc_id);
            conditionGroupService.createCustomerGroupCriteria(requestMap);
            responseMap.put("code","200");
            responseMap.put("msg","成功");
            return responseMap;
        } catch (SQLException e) {
            e.printStackTrace();
            responseMap.put("code","500");
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }

    }
}
