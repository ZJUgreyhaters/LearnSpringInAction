package com.quantchi.metadatamgr.controller;

import com.quantchi.metadatamgr.data.entity.DSTableInfoDB;
import com.quantchi.metadatamgr.service.MetaDataMgrTableApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api/metadata/table")
public class MetaDataMgrTableApiController {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrTableApiController.class);

    @Autowired
    private MetaDataMgrTableApiService metaDataMgrTableApiService;

    //搜索
    @ResponseBody
    @RequestMapping(value = "/search", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public String search(@RequestBody DSTableInfoDB tableInfo) {
        return metaDataMgrTableApiService.search(tableInfo);
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/del", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public String delete(@RequestBody DSTableInfoDB tableInfo) {
        return metaDataMgrTableApiService.delete(tableInfo);
    }

    //修改
    @ResponseBody
    @RequestMapping(value = "/edit", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public String update(@RequestBody DSTableInfoDB tableInfo) {
        return metaDataMgrTableApiService.update(tableInfo);
    }

    //审核
    @ResponseBody
    @RequestMapping(value = "/check", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public String check(@RequestBody DSTableInfoDB tableInfo) {
        return metaDataMgrTableApiService.check(tableInfo);
    }

    //表外键搜索接口
    @ResponseBody
    @RequestMapping(value = "foreignkeys",  method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public Map<String,Object> foreignkeys(@RequestBody Map<String, Object> requestMap){
        Map<String,Object> responseMap = new HashMap<>();
        try{
            List<Map<String,String>> mapList = metaDataMgrTableApiService.foreignkeys(requestMap);
            responseMap.put("data",mapList);
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
