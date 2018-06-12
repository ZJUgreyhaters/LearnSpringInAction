package com.quantchi.metadatamgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.util;
import com.quantchi.metadatamgr.service.MetaDataMgrApiService;
import com.quantchi.metadatamgr.service.MetaDataMgrTableApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "metadata")
public class MetaDataMgrTableApiController {

    private static final Logger logger = LoggerFactory.getLogger(MetaDataMgrTableApiController.class);

    @Autowired
    private MetaDataMgrTableApiService metaDataMgrTableApiService;

    @RequestMapping(value = "/search", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> search (@RequestBody String bodyString) {
        try{

            return util.genRet(500,null,"",0);
            //return util.genRet(200,_ret.get("data"),"ok",Integer.parseInt(_ret.get("total").toString()));
        }catch (Exception e){
            logger.error("list func err:",e.getMessage());
            return util.genRet(500,null,e.getMessage(),0);
        }



    }



}
