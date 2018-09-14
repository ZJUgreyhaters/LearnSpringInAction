package com.quantchi.authority.sqlparser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RowPermission
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/8/29 10:05
 * @Version 1.0.0
 **/
public class RowPermission {
    /*
    存储行权限
     */
    private Map<String, List<String>> rowPermission = new HashMap<>();

    public RowPermission(){}

    public void addSimpleRowRule(String tableName,String rowRule){
        if(rowPermission.containsKey(tableName)){
            List<String> rowSimpleRule = rowPermission.get(tableName);
            rowSimpleRule.add(rowRule);
            rowPermission.replace(tableName, rowSimpleRule);
        }
        else{
            List<String> tmp = new ArrayList<>();
            tmp.add(rowRule);
            rowPermission.put(tableName, tmp);
        }
    }

    public JSONObject getRowPermissionJson(){
        JSONObject reRowJson = new JSONObject();
        for(String key: rowPermission.keySet()){
            List<String> rowRule = rowPermission.get(key);
            JSONArray rowJson = new JSONArray();
            for(int i = 0;i < rowRule.size();i++){
                rowJson.add(rowRule.get(i));
            }
            reRowJson.put(key,rowJson);
        }
        return reRowJson;
    }

    public void addJsonRowRule(JSONArray authTableJsonArray){
        for(int j = 0;j < authTableJsonArray.size();j++) {
            String tableName = authTableJsonArray.getJSONObject(j).getString("c_tablename");
            String filterCondition = authTableJsonArray.getJSONObject(j).getString("c_fiter");
            this.addSimpleRowRule(tableName, filterCondition);
        }
    }
}
