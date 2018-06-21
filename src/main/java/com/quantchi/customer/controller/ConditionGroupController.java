package com.quantchi.customer.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.customer.pojo.ConditionGroup;
import com.quantchi.customer.service.ConditionGroupService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.noggit.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping("api")
public class ConditionGroupController {

    private static final Logger logger = LoggerFactory.getLogger(ConditionGroupController.class);

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

        try{
            HttpPost httpPost = new HttpPost("http://localhost:8081/termInfo");
            CloseableHttpClient client = HttpClients.createDefault();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("keyword","111");
            StringEntity entity = new StringEntity(jsonObject.toString(), "utf-8");
            entity.setContentType("UTF-8");
            entity.setContentType("application/json");

            httpPost.setEntity(entity);
            HttpResponse resp = client.execute(httpPost);
            String respContent = null;
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he,"UTF-8");
                JSONObject responseJson = new JSONObject();
                JSONObject resultJson = (JSONObject) JSONObject.parse(respContent);
//                List<Map<String,Object>> mapList = (List<Map<String,Object>>)resultJson.get("entitys");
                JSONObject entityJson = (JSONObject)resultJson.get("entitys");

                Iterator iterator = (Iterator) entityJson.entrySet();
                while (iterator.hasNext()){
                }

//                responseMap.put("data",jsonObject1);
            }
            responseMap.put("code",200);
            responseMap.put("msg","成功");
            return responseMap;

        }catch (Exception e){
            logger.error(e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }


    }
}
