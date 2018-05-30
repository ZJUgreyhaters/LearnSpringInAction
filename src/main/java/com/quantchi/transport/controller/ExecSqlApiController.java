package com.quantchi.transport.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.common.util;
import com.quantchi.tianshu.common.JdbcPool;
import com.quantchi.transport.service.ExecSqlApiService;
import com.quantchi.transport.service.SearchApiService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping(value = "api")
public class ExecSqlApiController {
    @Autowired
    private ExecSqlApiService execSqlApiService;



    @RequestMapping(value = "/execsql", method = { RequestMethod.POST })
    public @ResponseBody
    Map<String, Object> execsql (@RequestBody String bodyString) throws Exception {

        JSONObject paramObj =  JSON.parseObject(bodyString);
        String sql = paramObj.getString("sql");
        if(!sql.equals("")){
            Map<String, Object> _sqlRet = execSqlApiService.execsql(sql);
            return util.genRet(200,_sqlRet.get("data"),"ok",Integer.parseInt(_sqlRet.get("total").toString()));
            //return util.genRet(500,paramObj,"miss sql param",0);
        }else{
            return util.genRet(500,null,"miss sql param",0);
        }

    }

}
