package com.quantchi.termInfo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TermMainInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermMainInfoExample() {
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

        public Criteria andEntityTypeIsNull() {
            addCriterion("entity_type is null");
            return (Criteria) this;
        }

        public Criteria andEntityTypeIsNotNull() {
            addCriterion("entity_type is not null");
            return (Criteria) this;
        }

        public Criteria andEntityTypeEqualTo(String value) {
            addCriterion("entity_type =", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotEqualTo(String value) {
            addCriterion("entity_type <>", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeGreaterThan(String value) {
            addCriterion("entity_type >", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeGreaterThanOrEqualTo(String value) {
            addCriterion("entity_type >=", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeLessThan(String value) {
            addCriterion("entity_type <", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeLessThanOrEqualTo(String value) {
            addCriterion("entity_type <=", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeLike(String value) {
            addCriterion("entity_type like", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotLike(String value) {
            addCriterion("entity_type not like", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeIn(List<String> values) {
            addCriterion("entity_type in", values, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotIn(List<String> values) {
            addCriterion("entity_type not in", values, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeBetween(String value1, String value2) {
            addCriterion("entity_type between", value1, value2, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotBetween(String value1, String value2) {
            addCriterion("entity_type not between", value1, value2, "entityType");
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

        public Criteria andEntityHashIsNull() {
            addCriterion("entity_hash is null");
            return (Criteria) this;
        }

        public Criteria andEntityHashIsNotNull() {
            addCriterion("entity_hash is not null");
            return (Criteria) this;
        }

        public Criteria andEntityHashEqualTo(String value) {
            addCriterion("entity_hash =", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashNotEqualTo(String value) {
            addCriterion("entity_hash <>", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashGreaterThan(String value) {
            addCriterion("entity_hash >", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashGreaterThanOrEqualTo(String value) {
            addCriterion("entity_hash >=", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashLessThan(String value) {
            addCriterion("entity_hash <", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashLessThanOrEqualTo(String value) {
            addCriterion("entity_hash <=", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashLike(String value) {
            addCriterion("entity_hash like", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashNotLike(String value) {
            addCriterion("entity_hash not like", value, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashIn(List<String> values) {
            addCriterion("entity_hash in", values, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashNotIn(List<String> values) {
            addCriterion("entity_hash not in", values, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashBetween(String value1, String value2) {
            addCriterion("entity_hash between", value1, value2, "entityHash");
            return (Criteria) this;
        }

        public Criteria andEntityHashNotBetween(String value1, String value2) {
            addCriterion("entity_hash not between", value1, value2, "entityHash");
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

        public Criteria andEntityDescIsNull() {
            addCriterion("entity_desc is null");
            return (Criteria) this;
        }

        public Criteria andEntityDescIsNotNull() {
            addCriterion("entity_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEntityDescEqualTo(String value) {
            addCriterion("entity_desc =", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescNotEqualTo(String value) {
            addCriterion("entity_desc <>", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescGreaterThan(String value) {
            addCriterion("entity_desc >", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescGreaterThanOrEqualTo(String value) {
            addCriterion("entity_desc >=", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescLessThan(String value) {
            addCriterion("entity_desc <", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescLessThanOrEqualTo(String value) {
            addCriterion("entity_desc <=", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescLike(String value) {
            addCriterion("entity_desc like", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescNotLike(String value) {
            addCriterion("entity_desc not like", value, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescIn(List<String> values) {
            addCriterion("entity_desc in", values, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescNotIn(List<String> values) {
            addCriterion("entity_desc not in", values, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescBetween(String value1, String value2) {
            addCriterion("entity_desc between", value1, value2, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityDescNotBetween(String value1, String value2) {
            addCriterion("entity_desc not between", value1, value2, "entityDesc");
            return (Criteria) this;
        }

        public Criteria andEntityStatusIsNull() {
            addCriterion("entity_status is null");
            return (Criteria) this;
        }

        public Criteria andEntityStatusIsNotNull() {
            addCriterion("entity_status is not null");
            return (Criteria) this;
        }

        public Criteria andEntityStatusEqualTo(String value) {
            addCriterion("entity_status =", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusNotEqualTo(String value) {
            addCriterion("entity_status <>", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusGreaterThan(String value) {
            addCriterion("entity_status >", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusGreaterThanOrEqualTo(String value) {
            addCriterion("entity_status >=", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusLessThan(String value) {
            addCriterion("entity_status <", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusLessThanOrEqualTo(String value) {
            addCriterion("entity_status <=", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusLike(String value) {
            addCriterion("entity_status like", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusNotLike(String value) {
            addCriterion("entity_status not like", value, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusIn(List<String> values) {
            addCriterion("entity_status in", values, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusNotIn(List<String> values) {
            addCriterion("entity_status not in", values, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusBetween(String value1, String value2) {
            addCriterion("entity_status between", value1, value2, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andEntityStatusNotBetween(String value1, String value2) {
            addCriterion("entity_status not between", value1, value2, "entityStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeIsNull() {
            addCriterion("offline_time is null");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeIsNotNull() {
            addCriterion("offline_time is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeEqualTo(Date value) {
            addCriterion("offline_time =", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeNotEqualTo(Date value) {
            addCriterion("offline_time <>", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeGreaterThan(Date value) {
            addCriterion("offline_time >", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("offline_time >=", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeLessThan(Date value) {
            addCriterion("offline_time <", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeLessThanOrEqualTo(Date value) {
            addCriterion("offline_time <=", value, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeIn(List<Date> values) {
            addCriterion("offline_time in", values, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeNotIn(List<Date> values) {
            addCriterion("offline_time not in", values, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeBetween(Date value1, Date value2) {
            addCriterion("offline_time between", value1, value2, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andOfflineTimeNotBetween(Date value1, Date value2) {
            addCriterion("offline_time not between", value1, value2, "offlineTime");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andControlDeptIsNull() {
            addCriterion("control_dept is null");
            return (Criteria) this;
        }

        public Criteria andControlDeptIsNotNull() {
            addCriterion("control_dept is not null");
            return (Criteria) this;
        }

        public Criteria andControlDeptEqualTo(String value) {
            addCriterion("control_dept =", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptNotEqualTo(String value) {
            addCriterion("control_dept <>", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptGreaterThan(String value) {
            addCriterion("control_dept >", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptGreaterThanOrEqualTo(String value) {
            addCriterion("control_dept >=", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptLessThan(String value) {
            addCriterion("control_dept <", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptLessThanOrEqualTo(String value) {
            addCriterion("control_dept <=", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptLike(String value) {
            addCriterion("control_dept like", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptNotLike(String value) {
            addCriterion("control_dept not like", value, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptIn(List<String> values) {
            addCriterion("control_dept in", values, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptNotIn(List<String> values) {
            addCriterion("control_dept not in", values, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptBetween(String value1, String value2) {
            addCriterion("control_dept between", value1, value2, "controlDept");
            return (Criteria) this;
        }

        public Criteria andControlDeptNotBetween(String value1, String value2) {
            addCriterion("control_dept not between", value1, value2, "controlDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptIsNull() {
            addCriterion("assist_dept is null");
            return (Criteria) this;
        }

        public Criteria andAssistDeptIsNotNull() {
            addCriterion("assist_dept is not null");
            return (Criteria) this;
        }

        public Criteria andAssistDeptEqualTo(String value) {
            addCriterion("assist_dept =", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptNotEqualTo(String value) {
            addCriterion("assist_dept <>", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptGreaterThan(String value) {
            addCriterion("assist_dept >", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptGreaterThanOrEqualTo(String value) {
            addCriterion("assist_dept >=", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptLessThan(String value) {
            addCriterion("assist_dept <", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptLessThanOrEqualTo(String value) {
            addCriterion("assist_dept <=", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptLike(String value) {
            addCriterion("assist_dept like", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptNotLike(String value) {
            addCriterion("assist_dept not like", value, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptIn(List<String> values) {
            addCriterion("assist_dept in", values, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptNotIn(List<String> values) {
            addCriterion("assist_dept not in", values, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptBetween(String value1, String value2) {
            addCriterion("assist_dept between", value1, value2, "assistDept");
            return (Criteria) this;
        }

        public Criteria andAssistDeptNotBetween(String value1, String value2) {
            addCriterion("assist_dept not between", value1, value2, "assistDept");
            return (Criteria) this;
        }

        public Criteria andRegulatoryIsNull() {
            addCriterion("regulatory is null");
            return (Criteria) this;
        }

        public Criteria andRegulatoryIsNotNull() {
            addCriterion("regulatory is not null");
            return (Criteria) this;
        }

        public Criteria andRegulatoryEqualTo(String value) {
            addCriterion("regulatory =", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryNotEqualTo(String value) {
            addCriterion("regulatory <>", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryGreaterThan(String value) {
            addCriterion("regulatory >", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryGreaterThanOrEqualTo(String value) {
            addCriterion("regulatory >=", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryLessThan(String value) {
            addCriterion("regulatory <", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryLessThanOrEqualTo(String value) {
            addCriterion("regulatory <=", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryLike(String value) {
            addCriterion("regulatory like", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryNotLike(String value) {
            addCriterion("regulatory not like", value, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryIn(List<String> values) {
            addCriterion("regulatory in", values, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryNotIn(List<String> values) {
            addCriterion("regulatory not in", values, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryBetween(String value1, String value2) {
            addCriterion("regulatory between", value1, value2, "regulatory");
            return (Criteria) this;
        }

        public Criteria andRegulatoryNotBetween(String value1, String value2) {
            addCriterion("regulatory not between", value1, value2, "regulatory");
            return (Criteria) this;
        }

        public Criteria andLogicTypeIsNull() {
            addCriterion("logic_type is null");
            return (Criteria) this;
        }

        public Criteria andLogicTypeIsNotNull() {
            addCriterion("logic_type is not null");
            return (Criteria) this;
        }

        public Criteria andLogicTypeEqualTo(String value) {
            addCriterion("logic_type =", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeNotEqualTo(String value) {
            addCriterion("logic_type <>", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeGreaterThan(String value) {
            addCriterion("logic_type >", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeGreaterThanOrEqualTo(String value) {
            addCriterion("logic_type >=", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeLessThan(String value) {
            addCriterion("logic_type <", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeLessThanOrEqualTo(String value) {
            addCriterion("logic_type <=", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeLike(String value) {
            addCriterion("logic_type like", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeNotLike(String value) {
            addCriterion("logic_type not like", value, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeIn(List<String> values) {
            addCriterion("logic_type in", values, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeNotIn(List<String> values) {
            addCriterion("logic_type not in", values, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeBetween(String value1, String value2) {
            addCriterion("logic_type between", value1, value2, "logicType");
            return (Criteria) this;
        }

        public Criteria andLogicTypeNotBetween(String value1, String value2) {
            addCriterion("logic_type not between", value1, value2, "logicType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeIsNull() {
            addCriterion("display_type is null");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeIsNotNull() {
            addCriterion("display_type is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeEqualTo(String value) {
            addCriterion("display_type =", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeNotEqualTo(String value) {
            addCriterion("display_type <>", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeGreaterThan(String value) {
            addCriterion("display_type >", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("display_type >=", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeLessThan(String value) {
            addCriterion("display_type <", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeLessThanOrEqualTo(String value) {
            addCriterion("display_type <=", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeLike(String value) {
            addCriterion("display_type like", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeNotLike(String value) {
            addCriterion("display_type not like", value, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeIn(List<String> values) {
            addCriterion("display_type in", values, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeNotIn(List<String> values) {
            addCriterion("display_type not in", values, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeBetween(String value1, String value2) {
            addCriterion("display_type between", value1, value2, "displayType");
            return (Criteria) this;
        }

        public Criteria andDisplayTypeNotBetween(String value1, String value2) {
            addCriterion("display_type not between", value1, value2, "displayType");
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