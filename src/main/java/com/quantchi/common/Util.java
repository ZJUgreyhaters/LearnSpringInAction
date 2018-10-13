package com.quantchi.common;


/*import org.json.JSONArray;
import org.json.JSONObject;*/
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

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
            String pswdWithSalt = new String(Base64.getDecoder().decode(password));
            String salt = AppProperties.getWithDefault("base64.salt","sha1$liangzhi@dmpxitong-secret3366");
            return pswdWithSalt.replace(salt,"");
        }catch (Exception e){
            logger.error("DecodePassword error:"+e.getMessage());
            return "";
        }
    }

    public static String dateToString(Date time){
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        return formatter.format(time);
    }

    public static Object chkEmptyStringToNull(Object obj){
        if(obj instanceof String){
            if("".equals(obj))
                obj = null;
        }
        return obj;
    }

    public static String processTemplate(String template, Map<String, Object> params){
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);
        while (m.find()) {
            String param = m.group();
            Object value = params.get(param.substring(2, param.length() - 1));
            m.appendReplacement(sb, value==null ? "" : value.toString());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
