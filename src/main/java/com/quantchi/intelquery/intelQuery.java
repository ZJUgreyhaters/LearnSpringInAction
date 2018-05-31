package com.quantchi.intelquery;

import com.quantchi.common.AppProperties;
import com.quantchi.intelquery.date.formatter.NormalFormatter;
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
import java.time.format.DateTimeFormatter;
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
        }catch (Exception e){
            logger.error("LtpTokenizer error", e);
            totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),e.getMessage());
        }
        finally {
            return totalRet;
        }
    }

    public Map<String, Object> queryFromSearch(String serialization){

        Map<String, Object> totalRet = new HashMap<>();
        try {
            Query query = Query.fromSerializedString(serialization);
            QueryParser parser = QueryParser.getInstance();
            QueryResult queryResult = parser.parse(query).getFirstResult();
            totalRet.put("data",getResponseFromQueryResult(queryResult));

        } catch (IOException e) {
            logger.error("Failed parsing serialized string.", e);
            totalRet.put(Status.BAD_REQUEST.getStatus(),"Failed parsing serialized string.");
            //return ErrorResponse.create(Status.BAD_REQUEST, "Failed parsing serialized string.");
        } catch (ClassNotFoundException e) {
            logger.error("Class not found", e);
            totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),"Fatal Error");
            //return ErrorResponse.create(Status.INTERNAL_SERVER_ERROR, "Fatal Error");
        } catch (InterpreterException e) {
            logger.error("Failed to generate SQL", e);
            totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),"Failed to generate SQL.");
            //return ErrorResponse.create(Status.INTERNAL_SERVER_ERROR, "Failed to generate SQL. " + e.getMessage());
        }catch (Exception e){
            logger.error("internal server error happened", e.getMessage());
            totalRet.put(Status.INTERNAL_SERVER_ERROR.getStatus(),e.getMessage());
        }
        finally {
            return  totalRet;
        }
    }

    private  Map<String, Object>  getResponseFromQueryResult(QueryResult queryResult)
            throws InterpreterException ,Exception{
        Map<String, Object> response = new HashMap<>();
        String limitNum = AppProperties.get("sql.limit");
        if(limitNum == null )
            limitNum = "10";
        int num = Integer.parseInt(limitNum);
        SqlFormatter formatter = new SqlFormatter.Builder()
                .dateFormatter(new NormalFormatter(DateTimeFormatter.BASIC_ISO_DATE))
                .selectKey(false)
                .selectName(false)
                .selectRelated(false)
                .build();
        response = execSqlApiService.execsql(queryResult.getSql(formatter) + " limit "+num);
        return response;
    }
}
