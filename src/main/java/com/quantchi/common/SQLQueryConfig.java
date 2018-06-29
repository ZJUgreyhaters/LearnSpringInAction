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

  @Value("${sel.sql.top}")
  private String SEL_SQL_TOP;

  @Value("${sel.sql.bottom}")
  private String SEL_SQL_BOTTOM;

  @Value("${sel.insert.sql.top}")
  private String SEL_INSERT_SQL_TOP;

  @Value("${sel.sel.sql}")
  private String SEL_SEL_SQL;

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

  public String getSEL_SQL_TOP() {
    return SEL_SQL_TOP;
  }

  public void setSEL_SQL_TOP(String SEL_SQL_TOP) {
    this.SEL_SQL_TOP = SEL_SQL_TOP;
  }

  public String getSEL_SQL_BOTTOM() {
    return SEL_SQL_BOTTOM;
  }

  public void setSEL_SQL_BOTTOM(String SEL_SQL_BOTTOM) {
    this.SEL_SQL_BOTTOM = SEL_SQL_BOTTOM;
  }

  public String getSEL_INSERT_SQL_TOP() {
    return SEL_INSERT_SQL_TOP;
  }

  public void setSEL_INSERT_SQL_TOP(String SEL_INSERT_SQL_TOP) {
    this.SEL_INSERT_SQL_TOP = SEL_INSERT_SQL_TOP;
  }

  public String getSEL_SEL_SQL() {
    return SEL_SEL_SQL;
  }

  public void setSEL_SEL_SQL(String SEL_SEL_SQL) {
    this.SEL_SEL_SQL = SEL_SEL_SQL;
  }
}