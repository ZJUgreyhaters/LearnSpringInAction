package com.quantchi.customer.controller;

import com.quantchi.customer.pojo.ConditionGroup;
import com.quantchi.customer.service.ConditionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
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
            map = conditionGroupService.listCustomerGroupCriterias(page_size,page);
            map.put("code",200);
            map.put("msg","查询成功");
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","查询失败");
            return map;
        }
    }

    @RequestMapping(value = "/findCustomerCondition", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCustomerGroup(@RequestBody Map<String, String> map){
        Map<String, Object> responseMap = new HashMap<>();
        if(map.get("customer_condition_id") == null || map.get("customer_condition_id").equals("")){
            responseMap.put("code",400);
            responseMap.put("msg","请输入正确条件ID");
            return  responseMap;
        }
        responseMap = conditionGroupService.findCustomerGroup(map.get("customer_condition_id"));
        return  responseMap;
    }

    @RequestMapping(value = "/createCustomerGroupCriteria", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> createCustomerGroupCriteria(@RequestBody Map<String, Object> requestMap){
        Map<String, Object> responseMap = new HashMap<>();
        String condition_desc = "";
        String condition_desc_id = "";
        try {
            List<Map<String, Object>> CustomerGroupCriteriaDef = (List<Map<String, Object>>) requestMap.get("customer_group_criteria_def");
            for(Map<String, Object> listMap : CustomerGroupCriteriaDef){
                condition_desc += listMap.get("name") + ":" + String.join(",",(List)listMap.get("value")) + "|";
                condition_desc_id += "id:" + listMap.get("id") + ",type:" + listMap.get("type") +"|";
            }
            requestMap.put("condition_desc", condition_desc);
            requestMap.put("condition_desc_id", condition_desc_id);
            conditionGroupService.createCustomerGroupCriteria(requestMap);
            responseMap.put("code",200);
            responseMap.put("msg","创建成功");
            return responseMap;
        }catch (DuplicateKeyException duplicateKeyException){
            duplicateKeyException.printStackTrace();
            responseMap.put("code",500);
            responseMap.put("msg","条件名已经存在，请使用其他条件名");
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("code",500);
            responseMap.put("msg","创建失败");
            return responseMap;
        }
    }

    @RequestMapping(value = "/deleteCustomerGroupCondition", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteCustomerGroup(@RequestBody Map<String, String> map){
        Map<String, Object> responseMap = new HashMap<>();
        if(map.get("customer_condition_id") == null || map.get("customer_condition_id").equals("")){
            responseMap.put("code",400);
            responseMap.put("msg","请输入正确条件ID");
            return  responseMap;
        }
        return conditionGroupService.deleteCustomerGroup(map.get("customer_condition_id"));
    }

    @RequestMapping(value = "/getDdlDimData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getDdlDimData(@RequestBody Map<String,String> map){
        Map<String,Object> responseMap = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("id",1);
        conditionMap.put("type","value");
        conditionMap.put("name","营业部地址");
        List<String> listValue = new ArrayList<>();
        listValue.add("杭州");
        conditionMap.put("value",listValue);
        list.add(conditionMap);

        Map<String,Object> conditionMap2 = new HashMap<>();
        conditionMap2.put("id",2);
        conditionMap2.put("type","select");
        conditionMap2.put("name","客户性别");
        List<String> listValue2 = new ArrayList<>();
        listValue2.add("男");
        listValue2.add("女");
        conditionMap2.put("value",listValue2);
        list.add(conditionMap2);

        Map<String,Object> conditionMap3 = new HashMap<>();
        conditionMap3.put("id",3);
        conditionMap3.put("type","select");
        conditionMap3.put("name","客户类别");
        List<String> listValue3 = new ArrayList<>();
        listValue3.add("个人客户");
        listValue3.add("机构客户");
        listValue3.add("私募");
        conditionMap3.put("value",listValue3);
        list.add(conditionMap3);

        Map<String,Object> conditionMap4 = new HashMap<>();
        conditionMap4.put("id",4);
        conditionMap4.put("type","area");
        conditionMap4.put("name","维保比例");
        List<String> listValue4 = new ArrayList<>();
        listValue4.add("0");
        listValue4.add("无穷大");
        conditionMap4.put("value",listValue4);
        list.add(conditionMap4);

        Map<String,Object> conditionMap5 = new HashMap<>();
        conditionMap5.put("id",5);
        conditionMap5.put("type","area");
        conditionMap5.put("name","年日均总资产");
        List<String> listValue5 = new ArrayList<>();
        listValue5.add("0");
        listValue5.add("无穷大");
        conditionMap5.put("value",listValue5);
        list.add(conditionMap5);

        responseMap.put("code",200);
        responseMap.put("data",list);
        return responseMap;
    }
}
