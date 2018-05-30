package com.quantchi.common;

import java.io.IOException;
import java.io.InputStream;
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

    public static boolean getBoolean(String key) throws IOException {
        String value = get(key);
        return value != null && value.trim().equals("true");
    }

    public static int getInt(String key) throws IOException {
        String value = get(key);
        return value != null ? Integer.parseInt(value) : 0;
    }
}
