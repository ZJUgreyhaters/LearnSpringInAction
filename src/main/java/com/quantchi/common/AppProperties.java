package com.quantchi.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppProperties {
    private static AppProperties instance = null;
    Properties properties = null;

    public AppProperties() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        this.properties = new Properties();
        this.properties.load(is);
        is.close();
        is = this.getClass().getClassLoader().getResourceAsStream("app_dev.properties");
        if (is != null) {
            Properties devProperties = new Properties();
            devProperties.load(is);
            is.close();
            this.properties.putAll(devProperties);
        }

    }

    public static AppProperties getInstance() throws IOException {
        if (instance == null) {
            instance = new AppProperties();
        }

        return instance;
    }

    public static String get(String key) throws IOException {
        String ret = null;
        ret = getInstance().properties.getProperty(key);
        return ret;
    }

    public static String getWithDefault(String key, String defaultKey){
        String ret = null;
        if (defaultKey != null){
            ret = defaultKey;
        }else {
            try {
                ret = getInstance().properties.getProperty(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static boolean getBoolean(String key) throws IOException {
        String value = get(key);
        return value != null && value.trim().equals("true");
    }

    public static int getInt(String key) throws IOException {
        String value = get(key);
        return value != null ? Integer.parseInt(value) : 0;
    }

    //获取app.properties文件的paramKey参数，解析参数，封装到paramValue中返回
    public static Map<String,String> getPropertiesMap(String paramKey){
        Map<String,String> paramValue = new HashMap<>();
        //获取app.properties文件的solr.param参数，解析参数，封装到param中
        String solrParam = null;
        try {
            solrParam = get(paramKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] solrParams = solrParam.split("&");
        for(int i=0; i < solrParams.length; i++){
            String[] entry = solrParams[i].split("=");
            paramValue.put(entry[0],entry[1]);
        }
        return paramValue;
    }
}
