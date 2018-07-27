package com.quantchi.termInfo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StandardMainInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StandardMainInfoExample() {
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

        public Criteria andEntityCategoryIsNull() {
            addCriterion("entity_category is null");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryIsNotNull() {
            addCriterion("entity_category is not null");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryEqualTo(String value) {
            addCriterion("entity_category =", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryNotEqualTo(String value) {
            addCriterion("entity_category <>", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryGreaterThan(String value) {
            addCriterion("entity_category >", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("entity_category >=", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryLessThan(String value) {
            addCriterion("entity_category <", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryLessThanOrEqualTo(String value) {
            addCriterion("entity_category <=", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryLike(String value) {
            addCriterion("entity_category like", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryNotLike(String value) {
            addCriterion("entity_category not like", value, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryIn(List<String> values) {
            addCriterion("entity_category in", values, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryNotIn(List<String> values) {
            addCriterion("entity_category not in", values, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryBetween(String value1, String value2) {
            addCriterion("entity_category between", value1, value2, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityCategoryNotBetween(String value1, String value2) {
            addCriterion("entity_category not between", value1, value2, "entityCategory");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdIsNull() {
            addCriterion("entity_domain_id is null");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdIsNotNull() {
            addCriterion("entity_domain_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdEqualTo(String value) {
            addCriterion("entity_domain_id =", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdNotEqualTo(String value) {
            addCriterion("entity_domain_id <>", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdGreaterThan(String value) {
            addCriterion("entity_domain_id >", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdGreaterThanOrEqualTo(String value) {
            addCriterion("entity_domain_id >=", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdLessThan(String value) {
            addCriterion("entity_domain_id <", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdLessThanOrEqualTo(String value) {
            addCriterion("entity_domain_id <=", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdLike(String value) {
            addCriterion("entity_domain_id like", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdNotLike(String value) {
            addCriterion("entity_domain_id not like", value, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdIn(List<String> values) {
            addCriterion("entity_domain_id in", values, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdNotIn(List<String> values) {
            addCriterion("entity_domain_id not in", values, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdBetween(String value1, String value2) {
            addCriterion("entity_domain_id between", value1, value2, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andEntityDomainIdNotBetween(String value1, String value2) {
            addCriterion("entity_domain_id not between", value1, value2, "entityDomainId");
            return (Criteria) this;
        }

        public Criteria andStandardLevelIsNull() {
            addCriterion("standard_level is null");
            return (Criteria) this;
        }

        public Criteria andStandardLevelIsNotNull() {
            addCriterion("standard_level is not null");
            return (Criteria) this;
        }

        public Criteria andStandardLevelEqualTo(String value) {
            addCriterion("standard_level =", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelNotEqualTo(String value) {
            addCriterion("standard_level <>", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelGreaterThan(String value) {
            addCriterion("standard_level >", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelGreaterThanOrEqualTo(String value) {
            addCriterion("standard_level >=", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelLessThan(String value) {
            addCriterion("standard_level <", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelLessThanOrEqualTo(String value) {
            addCriterion("standard_level <=", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelLike(String value) {
            addCriterion("standard_level like", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelNotLike(String value) {
            addCriterion("standard_level not like", value, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelIn(List<String> values) {
            addCriterion("standard_level in", values, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelNotIn(List<String> values) {
            addCriterion("standard_level not in", values, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelBetween(String value1, String value2) {
            addCriterion("standard_level between", value1, value2, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andStandardLevelNotBetween(String value1, String value2) {
            addCriterion("standard_level not between", value1, value2, "standardLevel");
            return (Criteria) this;
        }

        public Criteria andAccordingIsNull() {
            addCriterion("according is null");
            return (Criteria) this;
        }

        public Criteria andAccordingIsNotNull() {
            addCriterion("according is not null");
            return (Criteria) this;
        }

        public Criteria andAccordingEqualTo(String value) {
            addCriterion("according =", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingNotEqualTo(String value) {
            addCriterion("according <>", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingGreaterThan(String value) {
            addCriterion("according >", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingGreaterThanOrEqualTo(String value) {
            addCriterion("according >=", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingLessThan(String value) {
            addCriterion("according <", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingLessThanOrEqualTo(String value) {
            addCriterion("according <=", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingLike(String value) {
            addCriterion("according like", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingNotLike(String value) {
            addCriterion("according not like", value, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingIn(List<String> values) {
            addCriterion("according in", values, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingNotIn(List<String> values) {
            addCriterion("according not in", values, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingBetween(String value1, String value2) {
            addCriterion("according between", value1, value2, "according");
            return (Criteria) this;
        }

        public Criteria andAccordingNotBetween(String value1, String value2) {
            addCriterion("according not between", value1, value2, "according");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleIsNull() {
            addCriterion("def_principle is null");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleIsNotNull() {
            addCriterion("def_principle is not null");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleEqualTo(String value) {
            addCriterion("def_principle =", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleNotEqualTo(String value) {
            addCriterion("def_principle <>", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleGreaterThan(String value) {
            addCriterion("def_principle >", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleGreaterThanOrEqualTo(String value) {
            addCriterion("def_principle >=", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleLessThan(String value) {
            addCriterion("def_principle <", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleLessThanOrEqualTo(String value) {
            addCriterion("def_principle <=", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleLike(String value) {
            addCriterion("def_principle like", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleNotLike(String value) {
            addCriterion("def_principle not like", value, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleIn(List<String> values) {
            addCriterion("def_principle in", values, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleNotIn(List<String> values) {
            addCriterion("def_principle not in", values, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleBetween(String value1, String value2) {
            addCriterion("def_principle between", value1, value2, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andDefPrincipleNotBetween(String value1, String value2) {
            addCriterion("def_principle not between", value1, value2, "defPrinciple");
            return (Criteria) this;
        }

        public Criteria andSupervisionIsNull() {
            addCriterion("supervision is null");
            return (Criteria) this;
        }

        public Criteria andSupervisionIsNotNull() {
            addCriterion("supervision is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisionEqualTo(String value) {
            addCriterion("supervision =", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionNotEqualTo(String value) {
            addCriterion("supervision <>", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionGreaterThan(String value) {
            addCriterion("supervision >", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionGreaterThanOrEqualTo(String value) {
            addCriterion("supervision >=", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionLessThan(String value) {
            addCriterion("supervision <", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionLessThanOrEqualTo(String value) {
            addCriterion("supervision <=", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionLike(String value) {
            addCriterion("supervision like", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionNotLike(String value) {
            addCriterion("supervision not like", value, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionIn(List<String> values) {
            addCriterion("supervision in", values, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionNotIn(List<String> values) {
            addCriterion("supervision not in", values, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionBetween(String value1, String value2) {
            addCriterion("supervision between", value1, value2, "supervision");
            return (Criteria) this;
        }

        public Criteria andSupervisionNotBetween(String value1, String value2) {
            addCriterion("supervision not between", value1, value2, "supervision");
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

        public Criteria andDataAreaIsNull() {
            addCriterion("data_area is null");
            return (Criteria) this;
        }

        public Criteria andDataAreaIsNotNull() {
            addCriterion("data_area is not null");
            return (Criteria) this;
        }

        public Criteria andDataAreaEqualTo(String value) {
            addCriterion("data_area =", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaNotEqualTo(String value) {
            addCriterion("data_area <>", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaGreaterThan(String value) {
            addCriterion("data_area >", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaGreaterThanOrEqualTo(String value) {
            addCriterion("data_area >=", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaLessThan(String value) {
            addCriterion("data_area <", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaLessThanOrEqualTo(String value) {
            addCriterion("data_area <=", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaLike(String value) {
            addCriterion("data_area like", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaNotLike(String value) {
            addCriterion("data_area not like", value, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaIn(List<String> values) {
            addCriterion("data_area in", values, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaNotIn(List<String> values) {
            addCriterion("data_area not in", values, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaBetween(String value1, String value2) {
            addCriterion("data_area between", value1, value2, "dataArea");
            return (Criteria) this;
        }

        public Criteria andDataAreaNotBetween(String value1, String value2) {
            addCriterion("data_area not between", value1, value2, "dataArea");
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

        public Criteria andUdcPrincipleIsNull() {
            addCriterion("udc_principle is null");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleIsNotNull() {
            addCriterion("udc_principle is not null");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleEqualTo(String value) {
            addCriterion("udc_principle =", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleNotEqualTo(String value) {
            addCriterion("udc_principle <>", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleGreaterThan(String value) {
            addCriterion("udc_principle >", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleGreaterThanOrEqualTo(String value) {
            addCriterion("udc_principle >=", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleLessThan(String value) {
            addCriterion("udc_principle <", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleLessThanOrEqualTo(String value) {
            addCriterion("udc_principle <=", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleLike(String value) {
            addCriterion("udc_principle like", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleNotLike(String value) {
            addCriterion("udc_principle not like", value, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleIn(List<String> values) {
            addCriterion("udc_principle in", values, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleNotIn(List<String> values) {
            addCriterion("udc_principle not in", values, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleBetween(String value1, String value2) {
            addCriterion("udc_principle between", value1, value2, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andUdcPrincipleNotBetween(String value1, String value2) {
            addCriterion("udc_principle not between", value1, value2, "udcPrinciple");
            return (Criteria) this;
        }

        public Criteria andSystemFromIsNull() {
            addCriterion("system_from is null");
            return (Criteria) this;
        }

        public Criteria andSystemFromIsNotNull() {
            addCriterion("system_from is not null");
            return (Criteria) this;
        }

        public Criteria andSystemFromEqualTo(String value) {
            addCriterion("system_from =", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromNotEqualTo(String value) {
            addCriterion("system_from <>", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromGreaterThan(String value) {
            addCriterion("system_from >", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromGreaterThanOrEqualTo(String value) {
            addCriterion("system_from >=", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromLessThan(String value) {
            addCriterion("system_from <", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromLessThanOrEqualTo(String value) {
            addCriterion("system_from <=", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromLike(String value) {
            addCriterion("system_from like", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromNotLike(String value) {
            addCriterion("system_from not like", value, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromIn(List<String> values) {
            addCriterion("system_from in", values, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromNotIn(List<String> values) {
            addCriterion("system_from not in", values, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromBetween(String value1, String value2) {
            addCriterion("system_from between", value1, value2, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemFromNotBetween(String value1, String value2) {
            addCriterion("system_from not between", value1, value2, "systemFrom");
            return (Criteria) this;
        }

        public Criteria andSystemUsedIsNull() {
            addCriterion("system_used is null");
            return (Criteria) this;
        }

        public Criteria andSystemUsedIsNotNull() {
            addCriterion("system_used is not null");
            return (Criteria) this;
        }

        public Criteria andSystemUsedEqualTo(String value) {
            addCriterion("system_used =", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedNotEqualTo(String value) {
            addCriterion("system_used <>", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedGreaterThan(String value) {
            addCriterion("system_used >", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedGreaterThanOrEqualTo(String value) {
            addCriterion("system_used >=", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedLessThan(String value) {
            addCriterion("system_used <", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedLessThanOrEqualTo(String value) {
            addCriterion("system_used <=", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedLike(String value) {
            addCriterion("system_used like", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedNotLike(String value) {
            addCriterion("system_used not like", value, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedIn(List<String> values) {
            addCriterion("system_used in", values, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedNotIn(List<String> values) {
            addCriterion("system_used not in", values, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedBetween(String value1, String value2) {
            addCriterion("system_used between", value1, value2, "systemUsed");
            return (Criteria) this;
        }

        public Criteria andSystemUsedNotBetween(String value1, String value2) {
            addCriterion("system_used not between", value1, value2, "systemUsed");
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

        public Criteria andFrequencyIsNull() {
            addCriterion("frequency is null");
            return (Criteria) this;
        }

        public Criteria andFrequencyIsNotNull() {
            addCriterion("frequency is not null");
            return (Criteria) this;
        }

        public Criteria andFrequencyEqualTo(String value) {
            addCriterion("frequency =", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotEqualTo(String value) {
            addCriterion("frequency <>", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThan(String value) {
            addCriterion("frequency >", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("frequency >=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThan(String value) {
            addCriterion("frequency <", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLessThanOrEqualTo(String value) {
            addCriterion("frequency <=", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyLike(String value) {
            addCriterion("frequency like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotLike(String value) {
            addCriterion("frequency not like", value, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyIn(List<String> values) {
            addCriterion("frequency in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotIn(List<String> values) {
            addCriterion("frequency not in", values, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyBetween(String value1, String value2) {
            addCriterion("frequency between", value1, value2, "frequency");
            return (Criteria) this;
        }

        public Criteria andFrequencyNotBetween(String value1, String value2) {
            addCriterion("frequency not between", value1, value2, "frequency");
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

        public Criteria andCoSectorIsNull() {
            addCriterion("co_sector is null");
            return (Criteria) this;
        }

        public Criteria andCoSectorIsNotNull() {
            addCriterion("co_sector is not null");
            return (Criteria) this;
        }

        public Criteria andCoSectorEqualTo(String value) {
            addCriterion("co_sector =", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorNotEqualTo(String value) {
            addCriterion("co_sector <>", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorGreaterThan(String value) {
            addCriterion("co_sector >", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorGreaterThanOrEqualTo(String value) {
            addCriterion("co_sector >=", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorLessThan(String value) {
            addCriterion("co_sector <", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorLessThanOrEqualTo(String value) {
            addCriterion("co_sector <=", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorLike(String value) {
            addCriterion("co_sector like", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorNotLike(String value) {
            addCriterion("co_sector not like", value, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorIn(List<String> values) {
            addCriterion("co_sector in", values, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorNotIn(List<String> values) {
            addCriterion("co_sector not in", values, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorBetween(String value1, String value2) {
            addCriterion("co_sector between", value1, value2, "coSector");
            return (Criteria) this;
        }

        public Criteria andCoSectorNotBetween(String value1, String value2) {
            addCriterion("co_sector not between", value1, value2, "coSector");
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

        public Criteria andEffectiveTimeIsNull() {
            addCriterion("effective_time is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeIsNotNull() {
            addCriterion("effective_time is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeEqualTo(Date value) {
            addCriterion("effective_time =", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotEqualTo(Date value) {
            addCriterion("effective_time <>", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeGreaterThan(Date value) {
            addCriterion("effective_time >", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("effective_time >=", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeLessThan(Date value) {
            addCriterion("effective_time <", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("effective_time <=", value, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeIn(List<Date> values) {
            addCriterion("effective_time in", values, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotIn(List<Date> values) {
            addCriterion("effective_time not in", values, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeBetween(Date value1, Date value2) {
            addCriterion("effective_time between", value1, value2, "effectiveTime");
            return (Criteria) this;
        }

        public Criteria andEffectiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("effective_time not between", value1, value2, "effectiveTime");
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