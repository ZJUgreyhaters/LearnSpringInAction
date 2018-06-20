package com.quantchi.metadatamgr.data.entity;

import java.util.ArrayList;
import java.util.List;

public class DSEntityInfoDBExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DSEntityInfoDBExample() {
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

        public Criteria andEntityNameIsNull() {
            addCriterion("entity_name is null");
            return (Criteria) this;
        }

        public Criteria andEntityNameIsNotNull() {
            addCriterion("entity_name is not null");
            return (Criteria) this;
        }

        public Criteria andEntityNameEqualTo(String value) {
            addCriterion("entity_name =", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameNotEqualTo(String value) {
            addCriterion("entity_name <>", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameGreaterThan(String value) {
            addCriterion("entity_name >", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameGreaterThanOrEqualTo(String value) {
            addCriterion("entity_name >=", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameLessThan(String value) {
            addCriterion("entity_name <", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameLessThanOrEqualTo(String value) {
            addCriterion("entity_name <=", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameLike(String value) {
            addCriterion("entity_name like", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameNotLike(String value) {
            addCriterion("entity_name not like", value, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameIn(List<String> values) {
            addCriterion("entity_name in", values, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameNotIn(List<String> values) {
            addCriterion("entity_name not in", values, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameBetween(String value1, String value2) {
            addCriterion("entity_name between", value1, value2, "entityName");
            return (Criteria) this;
        }

        public Criteria andEntityNameNotBetween(String value1, String value2) {
            addCriterion("entity_name not between", value1, value2, "entityName");
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

        public Criteria andMainTableIsNull() {
            addCriterion("main_table is null");
            return (Criteria) this;
        }

        public Criteria andMainTableIsNotNull() {
            addCriterion("main_table is not null");
            return (Criteria) this;
        }

        public Criteria andMainTableEqualTo(String value) {
            addCriterion("main_table =", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableNotEqualTo(String value) {
            addCriterion("main_table <>", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableGreaterThan(String value) {
            addCriterion("main_table >", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableGreaterThanOrEqualTo(String value) {
            addCriterion("main_table >=", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableLessThan(String value) {
            addCriterion("main_table <", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableLessThanOrEqualTo(String value) {
            addCriterion("main_table <=", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableLike(String value) {
            addCriterion("main_table like", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableNotLike(String value) {
            addCriterion("main_table not like", value, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableIn(List<String> values) {
            addCriterion("main_table in", values, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableNotIn(List<String> values) {
            addCriterion("main_table not in", values, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableBetween(String value1, String value2) {
            addCriterion("main_table between", value1, value2, "mainTable");
            return (Criteria) this;
        }

        public Criteria andMainTableNotBetween(String value1, String value2) {
            addCriterion("main_table not between", value1, value2, "mainTable");
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

        public Criteria andNonMainTableIsNull() {
            addCriterion("non_main_table is null");
            return (Criteria) this;
        }

        public Criteria andNonMainTableIsNotNull() {
            addCriterion("non_main_table is not null");
            return (Criteria) this;
        }

        public Criteria andNonMainTableEqualTo(String value) {
            addCriterion("non_main_table =", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableNotEqualTo(String value) {
            addCriterion("non_main_table <>", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableGreaterThan(String value) {
            addCriterion("non_main_table >", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableGreaterThanOrEqualTo(String value) {
            addCriterion("non_main_table >=", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableLessThan(String value) {
            addCriterion("non_main_table <", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableLessThanOrEqualTo(String value) {
            addCriterion("non_main_table <=", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableLike(String value) {
            addCriterion("non_main_table like", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableNotLike(String value) {
            addCriterion("non_main_table not like", value, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableIn(List<String> values) {
            addCriterion("non_main_table in", values, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableNotIn(List<String> values) {
            addCriterion("non_main_table not in", values, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableBetween(String value1, String value2) {
            addCriterion("non_main_table between", value1, value2, "nonMainTable");
            return (Criteria) this;
        }

        public Criteria andNonMainTableNotBetween(String value1, String value2) {
            addCriterion("non_main_table not between", value1, value2, "nonMainTable");
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