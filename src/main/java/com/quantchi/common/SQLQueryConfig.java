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