package com.quantchi.intelquery;

import com.quantchi.intelquery.date.formatter.SparkSqlBasicIsoFormatter;
import com.quantchi.intelquery.define.EnumDef;
import com.quantchi.intelquery.exception.InterpreterException;
import com.quantchi.intelquery.exception.QPException;
import com.quantchi.tianshu.common.web.ErrorResponse;
import com.quantchi.tianshu.common.web.Status;
import com.quantchi.tianshu.common.web.SuccessResponse;
import com.quantchi.transport.service.ExecSqlApiService;
import com.quantchi.transport.service.SearchApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class intelQuery{

    private static final Logger logger = LoggerFactory.getLogger(intelQuery.class);

    @Autowired
    private ExecSqlApiService execSqlApiService;

    public intelQuery(){}

    public Map<String, Object> query(String q){
        Map<String, Object> totalRet = new HashMap<>();
        try {
            Query query = new BasicQuery(q);
            QueryParser parser = QueryParser.getInstance();
            QueryResult queryResult = parser.parse(query);

            Map<String, Object> res = new HashMap<>();
            if (queryResult.getStatus() == EnumDef.QueryStatus.MAPPED_NODES) {
                List<Map<String, Object>> responseList = new ArrayList<>();
                for (QueryResult searchResult : queryResult.getAllResults()) {
                    Map<String, Object> responseItem = new HashMap<>();
                    responseItem.put("serializedQuery", searchResult.toSerializedString());
                    responseItem.put("text", searchResult.getTextForUser());
                    responseList.add(responseItem);
                }
                res.put("phase", "afterSearch");
                res.put("searchResults", responseList);
            } else if (queryResult.getStatus() == EnumDef.QueryStatus.QUERY_TREE) {
                res.put("phase", "complete");
                res.put("searchResults", getResponseFromQueryResult(queryResult.getFirstResult()));
            } else {
                totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),queryResult.getTextForUser());
            }
            totalRet.put("data",res);
            //return totalRet;
        } catch (IOException e) {
            logger.error("Cannot get config file", e);
            totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),"Cannot get config file");
            //return totalRet;
        } catch (QPException e) {
            logger.error("LtpTokenizer error", e);
            totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),"LtpTokenizer error");
        }
        finally {
            return totalRet;
        }
    }

    private  Map<String, Object>  getResponseFromQueryResult(QueryResult queryResult)
            throws InterpreterException {
        Map<String, Object> response = new HashMap<>();
        /*response.put("originNodeList", null);
        response.put("nodeList", null);
        response.put("keyFields", null);
        SqlFormatter formatter = new SqlFormatter.Builder()
                .dateFormatter(new SparkSqlBasicIsoFormatter())
                .selectRelated(false)
                .selectKey(false)
                .selectName(false)
                .build();
        response.put("jobSql", queryResult.getSql(formatter));
        response.put("sql", queryResult.getSql());*/

        response = execSqlApiService.execsql(queryResult.getSql());
        return response;
    }
}
