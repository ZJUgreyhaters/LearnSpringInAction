package com.quantchi.transport.controller;


import com.quantchi.common.util;
import com.quantchi.transport.service.SearchApiService;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping(value = "api")
public class SearchApiController {
    @Autowired
    private SearchApiService searchApiService;

    @RequestMapping(value = "/query", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> query (@RequestParam("q") String q) throws Exception {

        //
        searchApiService.isIndex(q);



        SolrDocumentList rets = searchApiService.search(q);

        //处理后的结果集
        SolrDocumentList afterHandle = searchApiService.handle(q,rets);

//        return util.genRet(200,rets,"ok",rets.size());
        return util.genRet(200,afterHandle,"ok",rets.size());
    }

    @RequestMapping(value = "/queryInstance", method = { RequestMethod.GET })
    public @ResponseBody
    Map<String, Object> queryInstance (@RequestParam("q") String q) throws Exception {
        SolrDocumentList rets = searchApiService.searchInstance(q);
        return util.genRet(200,rets,"ok",rets.size());
    }


}
