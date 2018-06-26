package com.quantchi.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 从配置文件sql_query_config.properties获取具体SQL语句
 *
 * Created by luchisheng on 2017/11/6.
 */

@Configuration
@PropertySource("classpath:sql_query_config.properties")
public class SQLQueryConfig {

    @Value("${sel.hive.customer.customer.groupId}")
    private String SEL_KLINE_COUNTRY_BY_COUNTRY;

    @Value("${sel.ids}")
    private String SEL_IDS;

    @Value("${sel.names}")
    private String SEL_NAMES;

    @Value("${sel.sqls}")
    private String SEL_SQLS;

    @Value("${sel.ids.names}")
    private String SEL_IDS_NAMES;

    @Value("${restTemplate.url}")
    private String REST_TEMPLATE_URL;

    @Value("${sel.hive.customer.ratio}")
    private String SEL_HIVE_COUNTRY_RATIO;

    @Value("${sel.hive.customer.analyze}")
    private String SEL_HIVE_COUNTRY_ANALYZE;

    @Value("${sel.hive.customer.yield}")
    private String SEL_HIVE_COUNTRY_YIELD;

    @Value("${sel.hive.customer.grade}")
    private String SEL_HIVE_COUNTRY_GRADE;

    @Value("${sel.hive.customer.department}")
    private String SEL_HIVE_COUNTRY_DEPARTMENT;

    @Value("${sel.hive.customer.preference}")
    private String SEL_HIVE_COUNTRY_PREFERENCE;

    public String getSEL_HIVE_COUNTRY_RATIO() {
        return SEL_HIVE_COUNTRY_RATIO;
    }

    public void setSEL_HIVE_COUNTRY_RATIO(String SEL_HIVE_COUNTRY_RATIO) {
        this.SEL_HIVE_COUNTRY_RATIO = SEL_HIVE_COUNTRY_RATIO;
    }

    public String getSEL_HIVE_COUNTRY_ANALYZE() {
        return SEL_HIVE_COUNTRY_ANALYZE;
    }

    public void setSEL_HIVE_COUNTRY_ANALYZE(String SEL_HIVE_COUNTRY_ANALYZE) {
        this.SEL_HIVE_COUNTRY_ANALYZE = SEL_HIVE_COUNTRY_ANALYZE;
    }

    public String getSEL_HIVE_COUNTRY_YIELD() {
        return SEL_HIVE_COUNTRY_YIELD;
    }

    public void setSEL_HIVE_COUNTRY_YIELD(String SEL_HIVE_COUNTRY_YIELD) {
        this.SEL_HIVE_COUNTRY_YIELD = SEL_HIVE_COUNTRY_YIELD;
    }

    public String getSEL_HIVE_COUNTRY_GRADE() {
        return SEL_HIVE_COUNTRY_GRADE;
    }

    public void setSEL_HIVE_COUNTRY_GRADE(String SEL_HIVE_COUNTRY_GRADE) {
        this.SEL_HIVE_COUNTRY_GRADE = SEL_HIVE_COUNTRY_GRADE;
    }

    public String getSEL_HIVE_COUNTRY_DEPARTMENT() {
        return SEL_HIVE_COUNTRY_DEPARTMENT;
    }

    public void setSEL_HIVE_COUNTRY_DEPARTMENT(String SEL_HIVE_COUNTRY_DEPARTMENT) {
        this.SEL_HIVE_COUNTRY_DEPARTMENT = SEL_HIVE_COUNTRY_DEPARTMENT;
    }

    public String getSEL_HIVE_COUNTRY_PREFERENCE() {
        return SEL_HIVE_COUNTRY_PREFERENCE;
    }

    public void setSEL_HIVE_COUNTRY_PREFERENCE(String SEL_HIVE_COUNTRY_PREFERENCE) {
        this.SEL_HIVE_COUNTRY_PREFERENCE = SEL_HIVE_COUNTRY_PREFERENCE;
    }

    public String getREST_TEMPLATE_URL() {
        return REST_TEMPLATE_URL;
    }

    public void setREST_TEMPLATE_URL(String REST_TEMPLATE_URL) {
        this.REST_TEMPLATE_URL = REST_TEMPLATE_URL;
    }

    public String getSEL_IDS_NAMES() {
        return SEL_IDS_NAMES;
    }

    public void setSEL_IDS_NAMES(String SEL_IDS_NAMES) {
        this.SEL_IDS_NAMES = SEL_IDS_NAMES;
    }

    public String getSEL_NAMES() {
        return SEL_NAMES;
    }

    public void setSEL_NAMES(String SEL_NAMES) {
        this.SEL_NAMES = SEL_NAMES;
    }

    public String getSEL_SQLS() {
        return SEL_SQLS;
    }

    public void setSEL_SQLS(String SEL_SQLS) {
        this.SEL_SQLS = SEL_SQLS;
    }

    public String getSEL_IDS() {
        return SEL_IDS;
    }

    public void setSEL_IDS(String SEL_IDS) {
        this.SEL_IDS = SEL_IDS;
    }

    public String getSEL_KLINE_COUNTRY_BY_COUNTRY() {
        return SEL_KLINE_COUNTRY_BY_COUNTRY;
    }

    public void setSEL_KLINE_COUNTRY_BY_COUNTRY(String SEL_KLINE_COUNTRY_BY_COUNTRY) {
        this.SEL_KLINE_COUNTRY_BY_COUNTRY = SEL_KLINE_COUNTRY_BY_COUNTRY;
    }
}