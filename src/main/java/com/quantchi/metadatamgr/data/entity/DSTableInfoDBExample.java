package com.quantchi.metadatamgr.data.entity;

import java.util.ArrayList;
import java.util.List;

public class DSTableInfoDBExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DSTableInfoDBExample() {
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

        public Criteria andTableEnglishNameIsNull() {
            addCriterion("table_english_name is null");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameIsNotNull() {
            addCriterion("table_english_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameEqualTo(String value) {
            addCriterion("table_english_name =", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameNotEqualTo(String value) {
            addCriterion("table_english_name <>", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameGreaterThan(String value) {
            addCriterion("table_english_name >", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_english_name >=", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameLessThan(String value) {
            addCriterion("table_english_name <", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("table_english_name <=", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameLike(String value) {
            addCriterion("table_english_name like", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameNotLike(String value) {
            addCriterion("table_english_name not like", value, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameIn(List<String> values) {
            addCriterion("table_english_name in", values, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameNotIn(List<String> values) {
            addCriterion("table_english_name not in", values, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameBetween(String value1, String value2) {
            addCriterion("table_english_name between", value1, value2, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableEnglishNameNotBetween(String value1, String value2) {
            addCriterion("table_english_name not between", value1, value2, "tableEnglishName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameIsNull() {
            addCriterion("table_chinese_name is null");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameIsNotNull() {
            addCriterion("table_chinese_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameEqualTo(String value) {
            addCriterion("table_chinese_name =", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameNotEqualTo(String value) {
            addCriterion("table_chinese_name <>", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameGreaterThan(String value) {
            addCriterion("table_chinese_name >", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_chinese_name >=", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameLessThan(String value) {
            addCriterion("table_chinese_name <", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameLessThanOrEqualTo(String value) {
            addCriterion("table_chinese_name <=", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameLike(String value) {
            addCriterion("table_chinese_name like", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameNotLike(String value) {
            addCriterion("table_chinese_name not like", value, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameIn(List<String> values) {
            addCriterion("table_chinese_name in", values, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameNotIn(List<String> values) {
            addCriterion("table_chinese_name not in", values, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameBetween(String value1, String value2) {
            addCriterion("table_chinese_name between", value1, value2, "tableChineseName");
            return (Criteria) this;
        }

        public Criteria andTableChineseNameNotBetween(String value1, String value2) {
            addCriterion("table_chinese_name not between", value1, value2, "tableChineseName");
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

        public Criteria andEntityIsNull() {
            addCriterion("entity is null");
            return (Criteria) this;
        }

        public Criteria andEntityIsNotNull() {
            addCriterion("entity is not null");
            return (Criteria) this;
        }

        public Criteria andEntityEqualTo(String value) {
            addCriterion("entity =", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityNotEqualTo(String value) {
            addCriterion("entity <>", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityGreaterThan(String value) {
            addCriterion("entity >", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityGreaterThanOrEqualTo(String value) {
            addCriterion("entity >=", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityLessThan(String value) {
            addCriterion("entity <", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityLessThanOrEqualTo(String value) {
            addCriterion("entity <=", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityLike(String value) {
            addCriterion("entity like", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityNotLike(String value) {
            addCriterion("entity not like", value, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityIn(List<String> values) {
            addCriterion("entity in", values, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityNotIn(List<String> values) {
            addCriterion("entity not in", values, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityBetween(String value1, String value2) {
            addCriterion("entity between", value1, value2, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityNotBetween(String value1, String value2) {
            addCriterion("entity not between", value1, value2, "entity");
            return (Criteria) this;
        }

        public Criteria andEntityFieldIsNull() {
            addCriterion("entity_field is null");
            return (Criteria) this;
        }

        public Criteria andEntityFieldIsNotNull() {
            addCriterion("entity_field is not null");
            return (Criteria) this;
        }

        public Criteria andEntityFieldEqualTo(String value) {
            addCriterion("entity_field =", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldNotEqualTo(String value) {
            addCriterion("entity_field <>", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldGreaterThan(String value) {
            addCriterion("entity_field >", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldGreaterThanOrEqualTo(String value) {
            addCriterion("entity_field >=", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldLessThan(String value) {
            addCriterion("entity_field <", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldLessThanOrEqualTo(String value) {
            addCriterion("entity_field <=", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldLike(String value) {
            addCriterion("entity_field like", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldNotLike(String value) {
            addCriterion("entity_field not like", value, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldIn(List<String> values) {
            addCriterion("entity_field in", values, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldNotIn(List<String> values) {
            addCriterion("entity_field not in", values, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldBetween(String value1, String value2) {
            addCriterion("entity_field between", value1, value2, "entityField");
            return (Criteria) this;
        }

        public Criteria andEntityFieldNotBetween(String value1, String value2) {
            addCriterion("entity_field not between", value1, value2, "entityField");
            return (Criteria) this;
        }

        public Criteria andBusinessIsNull() {
            addCriterion("business is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIsNotNull() {
            addCriterion("business is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessEqualTo(String value) {
            addCriterion("business =", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotEqualTo(String value) {
            addCriterion("business <>", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessGreaterThan(String value) {
            addCriterion("business >", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessGreaterThanOrEqualTo(String value) {
            addCriterion("business >=", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessLessThan(String value) {
            addCriterion("business <", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessLessThanOrEqualTo(String value) {
            addCriterion("business <=", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessLike(String value) {
            addCriterion("business like", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotLike(String value) {
            addCriterion("business not like", value, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessIn(List<String> values) {
            addCriterion("business in", values, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotIn(List<String> values) {
            addCriterion("business not in", values, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessBetween(String value1, String value2) {
            addCriterion("business between", value1, value2, "business");
            return (Criteria) this;
        }

        public Criteria andBusinessNotBetween(String value1, String value2) {
            addCriterion("business not between", value1, value2, "business");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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