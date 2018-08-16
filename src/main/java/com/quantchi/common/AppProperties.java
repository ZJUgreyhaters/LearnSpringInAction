package com.quantchi.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppProperties {

    private static final Logger logger = LoggerFactory.getLogger(AppProperties.class);

    private static AppProperties instance = null;
    Properties properties = null;

    private AppProperties(){

        try(InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("app.properties"),"UTF-8");){
            this.properties = new Properties();
            this.properties.load(inputStreamReader);
            inputStreamReader.close();
        }catch (IOException e){
            logger.error("get properties error: {}",e.getMessage());
        }

    }

    public static AppProperties getInstance() {
        if (instance == null) {
            instance = new AppProperties();
        }

        return instance;
    }

    public static String get(String key) {
        return getInstance().properties.getProperty(key);
    }

    public static String getWithDefault(String key, String defaultKey){
        String ret = "";
        if (defaultKey != null){
            ret = defaultKey;
        }else {
            ret = getInstance().properties.getProperty(key);
        }
        return ret;
    }

    public static boolean getBoolean(String key) {
        String value = get(key);
        return value != null && value.trim().equals("true");
    }

    public static int getInt(String key) {
        String value = get(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    //获取app.properties文件的paramKey参数，解析参数，封装到paramValue中返回
    public static Map<String,String> getPropertiesMap(String paramKey){
        Map<String,String> paramValue = new HashMap<>();
        //获取app.properties文件的solr.param参数，解析参数，封装到param中
        String solrParam = get(paramKey);
        String[] solrParams = solrParam.split("&");
        for(int i=0; i < solrParams.length; i++){
            String[] entry = solrParams[i].split("=");
            paramValue.put(entry[0],entry[1]);
        }
        return paramValue;
    }
}
