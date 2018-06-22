package com.quantchi.termInfo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhysicalTableInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhysicalTableInfoExample() {
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

        public Criteria andPhysicalTableHashIsNull() {
            addCriterion("physical_table_hash is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashIsNotNull() {
            addCriterion("physical_table_hash is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashEqualTo(String value) {
            addCriterion("physical_table_hash =", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashNotEqualTo(String value) {
            addCriterion("physical_table_hash <>", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashGreaterThan(String value) {
            addCriterion("physical_table_hash >", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashGreaterThanOrEqualTo(String value) {
            addCriterion("physical_table_hash >=", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashLessThan(String value) {
            addCriterion("physical_table_hash <", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashLessThanOrEqualTo(String value) {
            addCriterion("physical_table_hash <=", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashLike(String value) {
            addCriterion("physical_table_hash like", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashNotLike(String value) {
            addCriterion("physical_table_hash not like", value, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashIn(List<String> values) {
            addCriterion("physical_table_hash in", values, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashNotIn(List<String> values) {
            addCriterion("physical_table_hash not in", values, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashBetween(String value1, String value2) {
            addCriterion("physical_table_hash between", value1, value2, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableHashNotBetween(String value1, String value2) {
            addCriterion("physical_table_hash not between", value1, value2, "physicalTableHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableIsNull() {
            addCriterion("physical_table is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableIsNotNull() {
            addCriterion("physical_table is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableEqualTo(String value) {
            addCriterion("physical_table =", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotEqualTo(String value) {
            addCriterion("physical_table <>", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableGreaterThan(String value) {
            addCriterion("physical_table >", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableGreaterThanOrEqualTo(String value) {
            addCriterion("physical_table >=", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableLessThan(String value) {
            addCriterion("physical_table <", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableLessThanOrEqualTo(String value) {
            addCriterion("physical_table <=", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableLike(String value) {
            addCriterion("physical_table like", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotLike(String value) {
            addCriterion("physical_table not like", value, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableIn(List<String> values) {
            addCriterion("physical_table in", values, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotIn(List<String> values) {
            addCriterion("physical_table not in", values, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableBetween(String value1, String value2) {
            addCriterion("physical_table between", value1, value2, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalTableNotBetween(String value1, String value2) {
            addCriterion("physical_table not between", value1, value2, "physicalTable");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbIsNull() {
            addCriterion("physical_db is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbIsNotNull() {
            addCriterion("physical_db is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbEqualTo(String value) {
            addCriterion("physical_db =", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbNotEqualTo(String value) {
            addCriterion("physical_db <>", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbGreaterThan(String value) {
            addCriterion("physical_db >", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbGreaterThanOrEqualTo(String value) {
            addCriterion("physical_db >=", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbLessThan(String value) {
            addCriterion("physical_db <", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbLessThanOrEqualTo(String value) {
            addCriterion("physical_db <=", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbLike(String value) {
            addCriterion("physical_db like", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbNotLike(String value) {
            addCriterion("physical_db not like", value, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbIn(List<String> values) {
            addCriterion("physical_db in", values, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbNotIn(List<String> values) {
            addCriterion("physical_db not in", values, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbBetween(String value1, String value2) {
            addCriterion("physical_db between", value1, value2, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andPhysicalDbNotBetween(String value1, String value2) {
            addCriterion("physical_db not between", value1, value2, "physicalDb");
            return (Criteria) this;
        }

        public Criteria andTableTypeIsNull() {
            addCriterion("table_type is null");
            return (Criteria) this;
        }

        public Criteria andTableTypeIsNotNull() {
            addCriterion("table_type is not null");
            return (Criteria) this;
        }

        public Criteria andTableTypeEqualTo(String value) {
            addCriterion("table_type =", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotEqualTo(String value) {
            addCriterion("table_type <>", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeGreaterThan(String value) {
            addCriterion("table_type >", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeGreaterThanOrEqualTo(String value) {
            addCriterion("table_type >=", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeLessThan(String value) {
            addCriterion("table_type <", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeLessThanOrEqualTo(String value) {
            addCriterion("table_type <=", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeLike(String value) {
            addCriterion("table_type like", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotLike(String value) {
            addCriterion("table_type not like", value, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeIn(List<String> values) {
            addCriterion("table_type in", values, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotIn(List<String> values) {
            addCriterion("table_type not in", values, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeBetween(String value1, String value2) {
            addCriterion("table_type between", value1, value2, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableTypeNotBetween(String value1, String value2) {
            addCriterion("table_type not between", value1, value2, "tableType");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableDescIsNull() {
            addCriterion("table_desc is null");
            return (Criteria) this;
        }

        public Criteria andTableDescIsNotNull() {
            addCriterion("table_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTableDescEqualTo(String value) {
            addCriterion("table_desc =", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescNotEqualTo(String value) {
            addCriterion("table_desc <>", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescGreaterThan(String value) {
            addCriterion("table_desc >", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescGreaterThanOrEqualTo(String value) {
            addCriterion("table_desc >=", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescLessThan(String value) {
            addCriterion("table_desc <", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescLessThanOrEqualTo(String value) {
            addCriterion("table_desc <=", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescLike(String value) {
            addCriterion("table_desc like", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescNotLike(String value) {
            addCriterion("table_desc not like", value, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescIn(List<String> values) {
            addCriterion("table_desc in", values, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescNotIn(List<String> values) {
            addCriterion("table_desc not in", values, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescBetween(String value1, String value2) {
            addCriterion("table_desc between", value1, value2, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andTableDescNotBetween(String value1, String value2) {
            addCriterion("table_desc not between", value1, value2, "tableDesc");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeIsNull() {
            addCriterion("last_modified_time is null");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeIsNotNull() {
            addCriterion("last_modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeEqualTo(Date value) {
            addCriterion("last_modified_time =", value, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeNotEqualTo(Date value) {
            addCriterion("last_modified_time <>", value, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeGreaterThan(Date value) {
            addCriterion("last_modified_time >", value, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_modified_time >=", value, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeLessThan(Date value) {
            addCriterion("last_modified_time <", value, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_modified_time <=", value, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeIn(List<Date> values) {
            addCriterion("last_modified_time in", values, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeNotIn(List<Date> values) {
            addCriterion("last_modified_time not in", values, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("last_modified_time between", value1, value2, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andLastModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_modified_time not between", value1, value2, "lastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagIsNull() {
            addCriterion("partition_flag is null");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagIsNotNull() {
            addCriterion("partition_flag is not null");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagEqualTo(String value) {
            addCriterion("partition_flag =", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagNotEqualTo(String value) {
            addCriterion("partition_flag <>", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagGreaterThan(String value) {
            addCriterion("partition_flag >", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagGreaterThanOrEqualTo(String value) {
            addCriterion("partition_flag >=", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagLessThan(String value) {
            addCriterion("partition_flag <", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagLessThanOrEqualTo(String value) {
            addCriterion("partition_flag <=", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagLike(String value) {
            addCriterion("partition_flag like", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagNotLike(String value) {
            addCriterion("partition_flag not like", value, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagIn(List<String> values) {
            addCriterion("partition_flag in", values, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagNotIn(List<String> values) {
            addCriterion("partition_flag not in", values, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagBetween(String value1, String value2) {
            addCriterion("partition_flag between", value1, value2, "partitionFlag");
            return (Criteria) this;
        }

        public Criteria andPartitionFlagNotBetween(String value1, String value2) {
            addCriterion("partition_flag not between", value1, value2, "partitionFlag");
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