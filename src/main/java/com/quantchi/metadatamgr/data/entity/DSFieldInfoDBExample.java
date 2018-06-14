package com.quantchi.metadatamgr.data.entity;

import java.util.ArrayList;
import java.util.List;

public class DSFieldInfoDBExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DSFieldInfoDBExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameIsNull() {
            addCriterion("field_english_name is null");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameIsNotNull() {
            addCriterion("field_english_name is not null");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameEqualTo(String value) {
            addCriterion("field_english_name =", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameNotEqualTo(String value) {
            addCriterion("field_english_name <>", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameGreaterThan(String value) {
            addCriterion("field_english_name >", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("field_english_name >=", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameLessThan(String value) {
            addCriterion("field_english_name <", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("field_english_name <=", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameLike(String value) {
            addCriterion("field_english_name like", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameNotLike(String value) {
            addCriterion("field_english_name not like", value, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameIn(List<String> values) {
            addCriterion("field_english_name in", values, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameNotIn(List<String> values) {
            addCriterion("field_english_name not in", values, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameBetween(String value1, String value2) {
            addCriterion("field_english_name between", value1, value2, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldEnglishNameNotBetween(String value1, String value2) {
            addCriterion("field_english_name not between", value1, value2, "fieldEnglishName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameIsNull() {
            addCriterion("field_chinese_name is null");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameIsNotNull() {
            addCriterion("field_chinese_name is not null");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameEqualTo(String value) {
            addCriterion("field_chinese_name =", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameNotEqualTo(String value) {
            addCriterion("field_chinese_name <>", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameGreaterThan(String value) {
            addCriterion("field_chinese_name >", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameGreaterThanOrEqualTo(String value) {
            addCriterion("field_chinese_name >=", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameLessThan(String value) {
            addCriterion("field_chinese_name <", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameLessThanOrEqualTo(String value) {
            addCriterion("field_chinese_name <=", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameLike(String value) {
            addCriterion("field_chinese_name like", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameNotLike(String value) {
            addCriterion("field_chinese_name not like", value, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameIn(List<String> values) {
            addCriterion("field_chinese_name in", values, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameNotIn(List<String> values) {
            addCriterion("field_chinese_name not in", values, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameBetween(String value1, String value2) {
            addCriterion("field_chinese_name between", value1, value2, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andFieldChineseNameNotBetween(String value1, String value2) {
            addCriterion("field_chinese_name not between", value1, value2, "fieldChineseName");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(String value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(String value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(String value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(String value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(String value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(String value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLike(String value) {
            addCriterion("table_id like", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotLike(String value) {
            addCriterion("table_id not like", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<String> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<String> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(String value1, String value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(String value1, String value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIsNull() {
            addCriterion("datasource_id is null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIsNotNull() {
            addCriterion("datasource_id is not null");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdEqualTo(String value) {
            addCriterion("datasource_id =", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotEqualTo(String value) {
            addCriterion("datasource_id <>", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdGreaterThan(String value) {
            addCriterion("datasource_id >", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("datasource_id >=", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLessThan(String value) {
            addCriterion("datasource_id <", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLessThanOrEqualTo(String value) {
            addCriterion("datasource_id <=", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdLike(String value) {
            addCriterion("datasource_id like", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotLike(String value) {
            addCriterion("datasource_id not like", value, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdIn(List<String> values) {
            addCriterion("datasource_id in", values, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotIn(List<String> values) {
            addCriterion("datasource_id not in", values, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdBetween(String value1, String value2) {
            addCriterion("datasource_id between", value1, value2, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andDatasourceIdNotBetween(String value1, String value2) {
            addCriterion("datasource_id not between", value1, value2, "datasourceId");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNull() {
            addCriterion("field_type is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("field_type is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(String value) {
            addCriterion("field_type =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(String value) {
            addCriterion("field_type <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(String value) {
            addCriterion("field_type >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(String value) {
            addCriterion("field_type >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(String value) {
            addCriterion("field_type <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(String value) {
            addCriterion("field_type <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLike(String value) {
            addCriterion("field_type like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotLike(String value) {
            addCriterion("field_type not like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<String> values) {
            addCriterion("field_type in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<String> values) {
            addCriterion("field_type not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(String value1, String value2) {
            addCriterion("field_type between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(String value1, String value2) {
            addCriterion("field_type not between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldLengthIsNull() {
            addCriterion("field_length is null");
            return (Criteria) this;
        }

        public Criteria andFieldLengthIsNotNull() {
            addCriterion("field_length is not null");
            return (Criteria) this;
        }

        public Criteria andFieldLengthEqualTo(String value) {
            addCriterion("field_length =", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotEqualTo(String value) {
            addCriterion("field_length <>", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthGreaterThan(String value) {
            addCriterion("field_length >", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthGreaterThanOrEqualTo(String value) {
            addCriterion("field_length >=", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthLessThan(String value) {
            addCriterion("field_length <", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthLessThanOrEqualTo(String value) {
            addCriterion("field_length <=", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthLike(String value) {
            addCriterion("field_length like", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotLike(String value) {
            addCriterion("field_length not like", value, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthIn(List<String> values) {
            addCriterion("field_length in", values, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotIn(List<String> values) {
            addCriterion("field_length not in", values, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthBetween(String value1, String value2) {
            addCriterion("field_length between", value1, value2, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andFieldLengthNotBetween(String value1, String value2) {
            addCriterion("field_length not between", value1, value2, "fieldLength");
            return (Criteria) this;
        }

        public Criteria andIstermIsNull() {
            addCriterion("isterm is null");
            return (Criteria) this;
        }

        public Criteria andIstermIsNotNull() {
            addCriterion("isterm is not null");
            return (Criteria) this;
        }

        public Criteria andIstermEqualTo(Integer value) {
            addCriterion("isterm =", value, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermNotEqualTo(Integer value) {
            addCriterion("isterm <>", value, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermGreaterThan(Integer value) {
            addCriterion("isterm >", value, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermGreaterThanOrEqualTo(Integer value) {
            addCriterion("isterm >=", value, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermLessThan(Integer value) {
            addCriterion("isterm <", value, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermLessThanOrEqualTo(Integer value) {
            addCriterion("isterm <=", value, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermIn(List<Integer> values) {
            addCriterion("isterm in", values, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermNotIn(List<Integer> values) {
            addCriterion("isterm not in", values, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermBetween(Integer value1, Integer value2) {
            addCriterion("isterm between", value1, value2, "isterm");
            return (Criteria) this;
        }

        public Criteria andIstermNotBetween(Integer value1, Integer value2) {
            addCriterion("isterm not between", value1, value2, "isterm");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}