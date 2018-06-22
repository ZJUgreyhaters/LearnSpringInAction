package com.quantchi.termInfo.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PhysicalFieldInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhysicalFieldInfoExample() {
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

        public Criteria andEntityIdIsNull() {
            addCriterion("entity_id is null");
            return (Criteria) this;
        }

        public Criteria andEntityIdIsNotNull() {
            addCriterion("entity_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntityIdEqualTo(String value) {
            addCriterion("entity_id =", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotEqualTo(String value) {
            addCriterion("entity_id <>", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThan(String value) {
            addCriterion("entity_id >", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThanOrEqualTo(String value) {
            addCriterion("entity_id >=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThan(String value) {
            addCriterion("entity_id <", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThanOrEqualTo(String value) {
            addCriterion("entity_id <=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLike(String value) {
            addCriterion("entity_id like", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotLike(String value) {
            addCriterion("entity_id not like", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdIn(List<String> values) {
            addCriterion("entity_id in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotIn(List<String> values) {
            addCriterion("entity_id not in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdBetween(String value1, String value2) {
            addCriterion("entity_id between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotBetween(String value1, String value2) {
            addCriterion("entity_id not between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashIsNull() {
            addCriterion("physical_field_hash is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashIsNotNull() {
            addCriterion("physical_field_hash is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashEqualTo(String value) {
            addCriterion("physical_field_hash =", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashNotEqualTo(String value) {
            addCriterion("physical_field_hash <>", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashGreaterThan(String value) {
            addCriterion("physical_field_hash >", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashGreaterThanOrEqualTo(String value) {
            addCriterion("physical_field_hash >=", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashLessThan(String value) {
            addCriterion("physical_field_hash <", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashLessThanOrEqualTo(String value) {
            addCriterion("physical_field_hash <=", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashLike(String value) {
            addCriterion("physical_field_hash like", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashNotLike(String value) {
            addCriterion("physical_field_hash not like", value, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashIn(List<String> values) {
            addCriterion("physical_field_hash in", values, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashNotIn(List<String> values) {
            addCriterion("physical_field_hash not in", values, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashBetween(String value1, String value2) {
            addCriterion("physical_field_hash between", value1, value2, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldHashNotBetween(String value1, String value2) {
            addCriterion("physical_field_hash not between", value1, value2, "physicalFieldHash");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldIsNull() {
            addCriterion("physical_field is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldIsNotNull() {
            addCriterion("physical_field is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldEqualTo(String value) {
            addCriterion("physical_field =", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldNotEqualTo(String value) {
            addCriterion("physical_field <>", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldGreaterThan(String value) {
            addCriterion("physical_field >", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldGreaterThanOrEqualTo(String value) {
            addCriterion("physical_field >=", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldLessThan(String value) {
            addCriterion("physical_field <", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldLessThanOrEqualTo(String value) {
            addCriterion("physical_field <=", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldLike(String value) {
            addCriterion("physical_field like", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldNotLike(String value) {
            addCriterion("physical_field not like", value, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldIn(List<String> values) {
            addCriterion("physical_field in", values, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldNotIn(List<String> values) {
            addCriterion("physical_field not in", values, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldBetween(String value1, String value2) {
            addCriterion("physical_field between", value1, value2, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldNotBetween(String value1, String value2) {
            addCriterion("physical_field not between", value1, value2, "physicalField");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescIsNull() {
            addCriterion("physical_field_desc is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescIsNotNull() {
            addCriterion("physical_field_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescEqualTo(String value) {
            addCriterion("physical_field_desc =", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescNotEqualTo(String value) {
            addCriterion("physical_field_desc <>", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescGreaterThan(String value) {
            addCriterion("physical_field_desc >", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescGreaterThanOrEqualTo(String value) {
            addCriterion("physical_field_desc >=", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescLessThan(String value) {
            addCriterion("physical_field_desc <", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescLessThanOrEqualTo(String value) {
            addCriterion("physical_field_desc <=", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescLike(String value) {
            addCriterion("physical_field_desc like", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescNotLike(String value) {
            addCriterion("physical_field_desc not like", value, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescIn(List<String> values) {
            addCriterion("physical_field_desc in", values, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescNotIn(List<String> values) {
            addCriterion("physical_field_desc not in", values, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescBetween(String value1, String value2) {
            addCriterion("physical_field_desc between", value1, value2, "physicalFieldDesc");
            return (Criteria) this;
        }

        public Criteria andPhysicalFieldDescNotBetween(String value1, String value2) {
            addCriterion("physical_field_desc not between", value1, value2, "physicalFieldDesc");
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

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("data_type like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("data_type not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNull() {
            addCriterion("data_length is null");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNotNull() {
            addCriterion("data_length is not null");
            return (Criteria) this;
        }

        public Criteria andDataLengthEqualTo(Integer value) {
            addCriterion("data_length =", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotEqualTo(Integer value) {
            addCriterion("data_length <>", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThan(Integer value) {
            addCriterion("data_length >", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_length >=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThan(Integer value) {
            addCriterion("data_length <", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThanOrEqualTo(Integer value) {
            addCriterion("data_length <=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthIn(List<Integer> values) {
            addCriterion("data_length in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotIn(List<Integer> values) {
            addCriterion("data_length not in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthBetween(Integer value1, Integer value2) {
            addCriterion("data_length between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("data_length not between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionIsNull() {
            addCriterion("data_precision is null");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionIsNotNull() {
            addCriterion("data_precision is not null");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionEqualTo(Integer value) {
            addCriterion("data_precision =", value, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionNotEqualTo(Integer value) {
            addCriterion("data_precision <>", value, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionGreaterThan(Integer value) {
            addCriterion("data_precision >", value, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_precision >=", value, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionLessThan(Integer value) {
            addCriterion("data_precision <", value, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionLessThanOrEqualTo(Integer value) {
            addCriterion("data_precision <=", value, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionIn(List<Integer> values) {
            addCriterion("data_precision in", values, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionNotIn(List<Integer> values) {
            addCriterion("data_precision not in", values, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionBetween(Integer value1, Integer value2) {
            addCriterion("data_precision between", value1, value2, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPrecisionNotBetween(Integer value1, Integer value2) {
            addCriterion("data_precision not between", value1, value2, "dataPrecision");
            return (Criteria) this;
        }

        public Criteria andDataPatternIsNull() {
            addCriterion("data_pattern is null");
            return (Criteria) this;
        }

        public Criteria andDataPatternIsNotNull() {
            addCriterion("data_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andDataPatternEqualTo(String value) {
            addCriterion("data_pattern =", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternNotEqualTo(String value) {
            addCriterion("data_pattern <>", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternGreaterThan(String value) {
            addCriterion("data_pattern >", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternGreaterThanOrEqualTo(String value) {
            addCriterion("data_pattern >=", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternLessThan(String value) {
            addCriterion("data_pattern <", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternLessThanOrEqualTo(String value) {
            addCriterion("data_pattern <=", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternLike(String value) {
            addCriterion("data_pattern like", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternNotLike(String value) {
            addCriterion("data_pattern not like", value, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternIn(List<String> values) {
            addCriterion("data_pattern in", values, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternNotIn(List<String> values) {
            addCriterion("data_pattern not in", values, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternBetween(String value1, String value2) {
            addCriterion("data_pattern between", value1, value2, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataPatternNotBetween(String value1, String value2) {
            addCriterion("data_pattern not between", value1, value2, "dataPattern");
            return (Criteria) this;
        }

        public Criteria andDataUnitIsNull() {
            addCriterion("data_unit is null");
            return (Criteria) this;
        }

        public Criteria andDataUnitIsNotNull() {
            addCriterion("data_unit is not null");
            return (Criteria) this;
        }

        public Criteria andDataUnitEqualTo(String value) {
            addCriterion("data_unit =", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitNotEqualTo(String value) {
            addCriterion("data_unit <>", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitGreaterThan(String value) {
            addCriterion("data_unit >", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitGreaterThanOrEqualTo(String value) {
            addCriterion("data_unit >=", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitLessThan(String value) {
            addCriterion("data_unit <", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitLessThanOrEqualTo(String value) {
            addCriterion("data_unit <=", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitLike(String value) {
            addCriterion("data_unit like", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitNotLike(String value) {
            addCriterion("data_unit not like", value, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitIn(List<String> values) {
            addCriterion("data_unit in", values, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitNotIn(List<String> values) {
            addCriterion("data_unit not in", values, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitBetween(String value1, String value2) {
            addCriterion("data_unit between", value1, value2, "dataUnit");
            return (Criteria) this;
        }

        public Criteria andDataUnitNotBetween(String value1, String value2) {
            addCriterion("data_unit not between", value1, value2, "dataUnit");
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

        public Criteria andUdcRuleNameIsNull() {
            addCriterion("udc_rule_name is null");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameIsNotNull() {
            addCriterion("udc_rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameEqualTo(String value) {
            addCriterion("udc_rule_name =", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameNotEqualTo(String value) {
            addCriterion("udc_rule_name <>", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameGreaterThan(String value) {
            addCriterion("udc_rule_name >", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("udc_rule_name >=", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameLessThan(String value) {
            addCriterion("udc_rule_name <", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameLessThanOrEqualTo(String value) {
            addCriterion("udc_rule_name <=", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameLike(String value) {
            addCriterion("udc_rule_name like", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameNotLike(String value) {
            addCriterion("udc_rule_name not like", value, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameIn(List<String> values) {
            addCriterion("udc_rule_name in", values, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameNotIn(List<String> values) {
            addCriterion("udc_rule_name not in", values, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameBetween(String value1, String value2) {
            addCriterion("udc_rule_name between", value1, value2, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcRuleNameNotBetween(String value1, String value2) {
            addCriterion("udc_rule_name not between", value1, value2, "udcRuleName");
            return (Criteria) this;
        }

        public Criteria andUdcCodeIsNull() {
            addCriterion("udc_code is null");
            return (Criteria) this;
        }

        public Criteria andUdcCodeIsNotNull() {
            addCriterion("udc_code is not null");
            return (Criteria) this;
        }

        public Criteria andUdcCodeEqualTo(String value) {
            addCriterion("udc_code =", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeNotEqualTo(String value) {
            addCriterion("udc_code <>", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeGreaterThan(String value) {
            addCriterion("udc_code >", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeGreaterThanOrEqualTo(String value) {
            addCriterion("udc_code >=", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeLessThan(String value) {
            addCriterion("udc_code <", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeLessThanOrEqualTo(String value) {
            addCriterion("udc_code <=", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeLike(String value) {
            addCriterion("udc_code like", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeNotLike(String value) {
            addCriterion("udc_code not like", value, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeIn(List<String> values) {
            addCriterion("udc_code in", values, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeNotIn(List<String> values) {
            addCriterion("udc_code not in", values, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeBetween(String value1, String value2) {
            addCriterion("udc_code between", value1, value2, "udcCode");
            return (Criteria) this;
        }

        public Criteria andUdcCodeNotBetween(String value1, String value2) {
            addCriterion("udc_code not between", value1, value2, "udcCode");
            return (Criteria) this;
        }

        public Criteria andMaxIsNull() {
            addCriterion("max is null");
            return (Criteria) this;
        }

        public Criteria andMaxIsNotNull() {
            addCriterion("max is not null");
            return (Criteria) this;
        }

        public Criteria andMaxEqualTo(BigDecimal value) {
            addCriterion("max =", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotEqualTo(BigDecimal value) {
            addCriterion("max <>", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxGreaterThan(BigDecimal value) {
            addCriterion("max >", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max >=", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxLessThan(BigDecimal value) {
            addCriterion("max <", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max <=", value, "max");
            return (Criteria) this;
        }

        public Criteria andMaxIn(List<BigDecimal> values) {
            addCriterion("max in", values, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotIn(List<BigDecimal> values) {
            addCriterion("max not in", values, "max");
            return (Criteria) this;
        }

        public Criteria andMaxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max between", value1, value2, "max");
            return (Criteria) this;
        }

        public Criteria andMaxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max not between", value1, value2, "max");
            return (Criteria) this;
        }

        public Criteria andMinIsNull() {
            addCriterion("min is null");
            return (Criteria) this;
        }

        public Criteria andMinIsNotNull() {
            addCriterion("min is not null");
            return (Criteria) this;
        }

        public Criteria andMinEqualTo(BigDecimal value) {
            addCriterion("min =", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotEqualTo(BigDecimal value) {
            addCriterion("min <>", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinGreaterThan(BigDecimal value) {
            addCriterion("min >", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min >=", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinLessThan(BigDecimal value) {
            addCriterion("min <", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min <=", value, "min");
            return (Criteria) this;
        }

        public Criteria andMinIn(List<BigDecimal> values) {
            addCriterion("min in", values, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotIn(List<BigDecimal> values) {
            addCriterion("min not in", values, "min");
            return (Criteria) this;
        }

        public Criteria andMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min between", value1, value2, "min");
            return (Criteria) this;
        }

        public Criteria andMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min not between", value1, value2, "min");
            return (Criteria) this;
        }

        public Criteria andAvgIsNull() {
            addCriterion("avg is null");
            return (Criteria) this;
        }

        public Criteria andAvgIsNotNull() {
            addCriterion("avg is not null");
            return (Criteria) this;
        }

        public Criteria andAvgEqualTo(BigDecimal value) {
            addCriterion("avg =", value, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgNotEqualTo(BigDecimal value) {
            addCriterion("avg <>", value, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgGreaterThan(BigDecimal value) {
            addCriterion("avg >", value, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg >=", value, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgLessThan(BigDecimal value) {
            addCriterion("avg <", value, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg <=", value, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgIn(List<BigDecimal> values) {
            addCriterion("avg in", values, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgNotIn(List<BigDecimal> values) {
            addCriterion("avg not in", values, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg between", value1, value2, "avg");
            return (Criteria) this;
        }

        public Criteria andAvgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg not between", value1, value2, "avg");
            return (Criteria) this;
        }

        public Criteria andDataNullIsNull() {
            addCriterion("data_null is null");
            return (Criteria) this;
        }

        public Criteria andDataNullIsNotNull() {
            addCriterion("data_null is not null");
            return (Criteria) this;
        }

        public Criteria andDataNullEqualTo(BigDecimal value) {
            addCriterion("data_null =", value, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullNotEqualTo(BigDecimal value) {
            addCriterion("data_null <>", value, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullGreaterThan(BigDecimal value) {
            addCriterion("data_null >", value, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("data_null >=", value, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullLessThan(BigDecimal value) {
            addCriterion("data_null <", value, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullLessThanOrEqualTo(BigDecimal value) {
            addCriterion("data_null <=", value, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullIn(List<BigDecimal> values) {
            addCriterion("data_null in", values, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullNotIn(List<BigDecimal> values) {
            addCriterion("data_null not in", values, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("data_null between", value1, value2, "dataNull");
            return (Criteria) this;
        }

        public Criteria andDataNullNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("data_null not between", value1, value2, "dataNull");
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