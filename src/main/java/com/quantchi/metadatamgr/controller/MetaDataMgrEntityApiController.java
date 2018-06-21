package com.quantchi.metadatamgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.metadatamgr.service.MetaDataMgrEntityApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "api/metadata/entity")
public class MetaDataMgrEntityApiController {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrEntityApiController.class);

    @Autowired
    private MetaDataMgrEntityApiService metaDataMgrEntityApiService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> createEntity(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(metaDataMgrEntityApiService.createEntity(json) <= 0){
                throw new Exception("save fail");
            }
            responseMap.put("code",200);
            responseMap.put("msg","成功");
            return responseMap;
        }catch (DuplicateKeyException duplicateKeyException){
            logger.error(duplicateKeyException.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg","实体名已经存在");
            return responseMap;
        }catch (Exception e){
            logger.error(e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyEntity(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.get("entity_id") == null){
                throw new Exception("miss entity id");
            }
            if(metaDataMgrEntityApiService.modifyEntity(json) <=0){
                throw new Exception("update fail");
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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> searchEntity(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("data_source_name") == null){
                throw new Exception("miss data source name");
            }
            if(json.getString("business") == null){
                throw new Exception("miss business");
            }
            responseMap = metaDataMgrEntityApiService.searchEntity(json);
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

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteEntity(@RequestBody String bodyString){
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("entity_id") == null){
                throw new Exception("miss entity id");
            }
            if(metaDataMgrEntityApiService.deleteEntity(json.getString("entity_id")) <= 0){
                throw new Exception("删除id不存在");
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
