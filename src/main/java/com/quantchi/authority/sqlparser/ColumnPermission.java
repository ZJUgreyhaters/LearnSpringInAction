package com.quantchi.authority.sqlparser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ColumnPermission
 * @Description: TODO
 * @Author: wbchen
 * @Date: 2018/8/29 10:25
 * @Version 1.0.0
 **/
public class ColumnPermission {
    private Map<String, List<String>> columnPermission = new HashMap<>();

    ColumnPermission() {

    }

    public void addSimpleColumnRule(String tableName, String columnRule){
        if(columnPermission.containsKey(tableName)){
            List<String> columnSimpleRule = columnPermission.get(tableName);
            columnSimpleRule.add(columnRule);
            columnPermission.replace(tableName, columnSimpleRule);
        }
        else{
            List<String> tmp = new ArrayList<>();
            tmp.add(columnRule);
            columnPermission.put(tableName, tmp);
        }
    }

    public JSONObject getColumnPermissionJson(){
        JSONObject reColumnJson = new JSONObject();
        for(String key: columnPermission.keySet()){
            List<String> columnRule = columnPermission.get(key);
            JSONArray columnJson = new JSONArray();
            for(int i = 0;i < columnRule.size();i++){
                columnJson.add(columnRule.get(i));
            }
            reColumnJson.put(key,columnJson);
        }
        return reColumnJson;
    }

}
