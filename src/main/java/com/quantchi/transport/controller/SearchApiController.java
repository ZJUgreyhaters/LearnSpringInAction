package com.quantchi.transport.controller;


import com.quantchi.common.util;
import com.quantchi.transport.service.SearchApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping(value = "api")
public class SearchApiController {
    @Autowired
    private SearchApiService searchApiService;

    @RequestMapping(value = "/query", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> query (@RequestBody String bodyString) throws Exception {
        searchApiService.search();
        return util.genRet(200,"test","ok",1);
    }

}
