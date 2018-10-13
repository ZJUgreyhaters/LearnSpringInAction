package com.quantchi.authority.sqlparser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowAndColumnPermission {

    private Map<String, List<String>> rowPermission = new HashMap<>();
    private Map<String, List<String>> columnPermission = new HashMap<>();
    private JSONObject rowPermissionJson = new JSONObject();
    private JSONObject columnPermissionJson = new JSONObject();

    RowAndColumnPermission(){

    }

    public void addSimpleRowRule(String tableName, String rowRule){
        if(rowPermission.containsKey(tableName)){
            List<String> rowSimpleRule = rowPermission.get(tableName);
            rowSimpleRule.add(rowRule);
            rowPermission.replace(tableName, rowSimpleRule);
        }
        else{
            List<String> tmp = new ArrayList<>();
            tmp.add(rowRule);
            rowPermission.put(tableName,tmp);
        }
    }

    public void addSimpleColumnRule(String tableName, String columnRule){
        if(columnPermission.containsKey(tableName)){
            List<String> columnSimpleRule = columnPermission.get(tableName);
            columnSimpleRule.add(columnRule);
            columnPermission.replace(tableName,columnSimpleRule);
        }
        else{
            List<String> tmp = new ArrayList<>();
            tmp.add(columnRule);
            this.columnPermission.put(tableName,tmp);
        }
    }

    public JSONObject getRowPermissionJson(){
        JSONObject reRowJson = new JSONObject();
        for(String key:rowPermission.keySet()){
            List<String> rowConditionSet = rowPermission.get(key);
            JSONArray conditonArray = new JSONArray();
            for(int i = 0;i < rowConditionSet.size();i++){
                conditonArray.add(rowConditionSet.get(i));
            }
            reRowJson.put(key,conditonArray);
        }
        return reRowJson;
    }
    public JSONObject getColumnPermissionJson(){
        JSONObject reColumnJson = new JSONObject();
        for(String key:columnPermission.keySet()){
            List<String> coloumConditionSet = columnPermission.get(key);
            JSONArray conditionArray = new JSONArray();
            for(int i = 0;i < coloumConditionSet.size();i++){
                conditionArray.add(coloumConditionSet.get(i));
            }
            reColumnJson.put(key,conditionArray);
        }
        return reColumnJson;
    }

}
