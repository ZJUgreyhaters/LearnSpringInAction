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

    public String getSEL_KLINE_COUNTRY_BY_COUNTRY() {
        return SEL_KLINE_COUNTRY_BY_COUNTRY;
    }

    public void setSEL_KLINE_COUNTRY_BY_COUNTRY(String SEL_KLINE_COUNTRY_BY_COUNTRY) {
        this.SEL_KLINE_COUNTRY_BY_COUNTRY = SEL_KLINE_COUNTRY_BY_COUNTRY;
    }
}