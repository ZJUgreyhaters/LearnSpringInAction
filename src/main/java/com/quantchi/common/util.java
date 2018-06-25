package com.quantchi.common;


/*import org.json.JSONArray;
import org.json.JSONObject;*/
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.quantchi.metadatamgr.controller.MetaDataMgrApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class util {

    private static final Logger logger = LoggerFactory.getLogger(util.class);

    public static Map<String, Object> genRet(int code,Object data,String retMsg,int total){
        Map<String, Object> _ret = new HashMap<String, Object>();
        _ret.put("code",code);
        if(data == null)
            _ret.put("data",new ArrayList<>());
        else
            _ret.put("data",data);
        _ret.put("msg",retMsg);
        _ret.put("total",total);
        return _ret;
    }

    public static JSONArray convertResultSetIntoJSON(ResultSet resultSet) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = resultSet.getObject(i + 1);
                if (columnValue == null){
                    columnValue = "null";
                }

                if (obj.containsKey(columnName)){
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            jsonArray.add(obj);
        }
        return jsonArray;
    }

    public static String DecodePassword(String password){

        try {
            String pswdWithSalt = new String((new BASE64Decoder()).decodeBuffer(password));
            String salt = AppProperties.getWithDefault("base64.salt","sha1$liangzhi@dmpxitong-secret3366");
            return pswdWithSalt.replace(salt,"");
        }catch (Exception e){
            logger.error("DecodePassword error:"+e.getMessage());
            return "";
        }
    }
}
