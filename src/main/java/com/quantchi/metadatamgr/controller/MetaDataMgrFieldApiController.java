package com.quantchi.metadatamgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.Util;
import com.quantchi.metadatamgr.service.MetaDataMgrFieldApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "api/metadata/field")
public class MetaDataMgrFieldApiController {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrFieldApiController.class);

    @Autowired
    private MetaDataMgrFieldApiService metaDataMgrFieldApiService;

    @RequestMapping(value = "/search", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> search (@RequestBody String bodyString) {
        Map<String, Object> responseMap = new HashMap<>();
        try{
            JSONObject json = JSONObject.parseObject(bodyString);
            if(json.getString("data_source_name") == null){
                throw new Exception("miss data source name");
            }
            if(json.getString("table_name") == null){
                throw new Exception("miss table name");
            }
            responseMap = metaDataMgrFieldApiService.search(json);
            responseMap.put("code",200);
            responseMap.put("msg","查询成功");
            return responseMap;
            //return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
        }catch (Exception e){
            logger.error("list func err:",e.getMessage());
            responseMap.put("code",500);
            responseMap.put("msg",e.getMessage());
            return responseMap;
        }



    }

    @RequestMapping(value = "/edit", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> edit (@RequestBody String bodyString) {
        try{
            return Util.genRet(500,null,"",0);
            //return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
        }catch (Exception e){
            logger.error("list func err:",e.getMessage());
            return Util.genRet(500,null,e.getMessage(),0);
        }



    }

}
