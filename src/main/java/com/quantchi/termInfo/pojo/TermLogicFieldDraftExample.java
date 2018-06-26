package com.quantchi.termInfo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TermLogicFieldDraftExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermLogicFieldDraftExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andChineseNameIsNull() {
            addCriterion("chinese_name is null");
            return (Criteria) this;
        }

        public Criteria andChineseNameIsNotNull() {
            addCriterion("chinese_name is not null");
            return (Criteria) this;
        }

        public Criteria andChineseNameEqualTo(String value) {
            addCriterion("chinese_name =", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotEqualTo(String value) {
            addCriterion("chinese_name <>", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameGreaterThan(String value) {
            addCriterion("chinese_name >", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameGreaterThanOrEqualTo(String value) {
            addCriterion("chinese_name >=", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameLessThan(String value) {
            addCriterion("chinese_name <", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameLessThanOrEqualTo(String value) {
            addCriterion("chinese_name <=", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameLike(String value) {
            addCriterion("chinese_name like", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotLike(String value) {
            addCriterion("chinese_name not like", value, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameIn(List<String> values) {
            addCriterion("chinese_name in", values, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotIn(List<String> values) {
            addCriterion("chinese_name not in", values, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameBetween(String value1, String value2) {
            addCriterion("chinese_name between", value1, value2, "chineseName");
            return (Criteria) this;
        }

        public Criteria andChineseNameNotBetween(String value1, String value2) {
            addCriterion("chinese_name not between", value1, value2, "chineseName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameIsNull() {
            addCriterion("english_name is null");
            return (Criteria) this;
        }

        public Criteria andEnglishNameIsNotNull() {
            addCriterion("english_name is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishNameEqualTo(String value) {
            addCriterion("english_name =", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotEqualTo(String value) {
            addCriterion("english_name <>", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameGreaterThan(String value) {
            addCriterion("english_name >", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameGreaterThanOrEqualTo(String value) {
            addCriterion("english_name >=", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameLessThan(String value) {
            addCriterion("english_name <", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameLessThanOrEqualTo(String value) {
            addCriterion("english_name <=", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameLike(String value) {
            addCriterion("english_name like", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotLike(String value) {
            addCriterion("english_name not like", value, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameIn(List<String> values) {
            addCriterion("english_name in", values, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotIn(List<String> values) {
            addCriterion("english_name not in", values, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameBetween(String value1, String value2) {
            addCriterion("english_name between", value1, value2, "englishName");
            return (Criteria) this;
        }

        public Criteria andEnglishNameNotBetween(String value1, String value2) {
            addCriterion("english_name not between", value1, value2, "englishName");
            return (Criteria) this;
        }

        public Criteria andLogicCateIsNull() {
            addCriterion("logic_cate is null");
            return (Criteria) this;
        }

        public Criteria andLogicCateIsNotNull() {
            addCriterion("logic_cate is not null");
            return (Criteria) this;
        }

        public Criteria andLogicCateEqualTo(String value) {
            addCriterion("logic_cate =", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateNotEqualTo(String value) {
            addCriterion("logic_cate <>", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateGreaterThan(String value) {
            addCriterion("logic_cate >", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateGreaterThanOrEqualTo(String value) {
            addCriterion("logic_cate >=", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateLessThan(String value) {
            addCriterion("logic_cate <", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateLessThanOrEqualTo(String value) {
            addCriterion("logic_cate <=", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateLike(String value) {
            addCriterion("logic_cate like", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateNotLike(String value) {
            addCriterion("logic_cate not like", value, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateIn(List<String> values) {
            addCriterion("logic_cate in", values, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateNotIn(List<String> values) {
            addCriterion("logic_cate not in", values, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateBetween(String value1, String value2) {
            addCriterion("logic_cate between", value1, value2, "logicCate");
            return (Criteria) this;
        }

        public Criteria andLogicCateNotBetween(String value1, String value2) {
            addCriterion("logic_cate not between", value1, value2, "logicCate");
            return (Criteria) this;
        }

        public Criteria andDataCateIsNull() {
            addCriterion("data_cate is null");
            return (Criteria) this;
        }

        public Criteria andDataCateIsNotNull() {
            addCriterion("data_cate is not null");
            return (Criteria) this;
        }

        public Criteria andDataCateEqualTo(String value) {
            addCriterion("data_cate =", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateNotEqualTo(String value) {
            addCriterion("data_cate <>", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateGreaterThan(String value) {
            addCriterion("data_cate >", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateGreaterThanOrEqualTo(String value) {
            addCriterion("data_cate >=", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateLessThan(String value) {
            addCriterion("data_cate <", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateLessThanOrEqualTo(String value) {
            addCriterion("data_cate <=", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateLike(String value) {
            addCriterion("data_cate like", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateNotLike(String value) {
            addCriterion("data_cate not like", value, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateIn(List<String> values) {
            addCriterion("data_cate in", values, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateNotIn(List<String> values) {
            addCriterion("data_cate not in", values, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateBetween(String value1, String value2) {
            addCriterion("data_cate between", value1, value2, "dataCate");
            return (Criteria) this;
        }

        public Criteria andDataCateNotBetween(String value1, String value2) {
            addCriterion("data_cate not between", value1, value2, "dataCate");
            return (Criteria) this;
        }

        public Criteria andCateIsNull() {
            addCriterion("cate is null");
            return (Criteria) this;
        }

        public Criteria andCateIsNotNull() {
            addCriterion("cate is not null");
            return (Criteria) this;
        }

        public Criteria andCateEqualTo(String value) {
            addCriterion("cate =", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotEqualTo(String value) {
            addCriterion("cate <>", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateGreaterThan(String value) {
            addCriterion("cate >", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateGreaterThanOrEqualTo(String value) {
            addCriterion("cate >=", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateLessThan(String value) {
            addCriterion("cate <", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateLessThanOrEqualTo(String value) {
            addCriterion("cate <=", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateLike(String value) {
            addCriterion("cate like", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotLike(String value) {
            addCriterion("cate not like", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateIn(List<String> values) {
            addCriterion("cate in", values, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotIn(List<String> values) {
            addCriterion("cate not in", values, "cate");
            return (Criteria) this;
        }

        public Criteria andCateBetween(String value1, String value2) {
            addCriterion("cate between", value1, value2, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotBetween(String value1, String value2) {
            addCriterion("cate not between", value1, value2, "cate");
            return (Criteria) this;
        }

        public Criteria andDefinitionIsNull() {
            addCriterion("definition is null");
            return (Criteria) this;
        }

        public Criteria andDefinitionIsNotNull() {
            addCriterion("definition is not null");
            return (Criteria) this;
        }

        public Criteria andDefinitionEqualTo(String value) {
            addCriterion("definition =", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotEqualTo(String value) {
            addCriterion("definition <>", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionGreaterThan(String value) {
            addCriterion("definition >", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionGreaterThanOrEqualTo(String value) {
            addCriterion("definition >=", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionLessThan(String value) {
            addCriterion("definition <", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionLessThanOrEqualTo(String value) {
            addCriterion("definition <=", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionLike(String value) {
            addCriterion("definition like", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotLike(String value) {
            addCriterion("definition not like", value, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionIn(List<String> values) {
            addCriterion("definition in", values, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotIn(List<String> values) {
            addCriterion("definition not in", values, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionBetween(String value1, String value2) {
            addCriterion("definition between", value1, value2, "definition");
            return (Criteria) this;
        }

        public Criteria andDefinitionNotBetween(String value1, String value2) {
            addCriterion("definition not between", value1, value2, "definition");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaIsNull() {
            addCriterion("bussiness_criteria is null");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaIsNotNull() {
            addCriterion("bussiness_criteria is not null");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaEqualTo(String value) {
            addCriterion("bussiness_criteria =", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaNotEqualTo(String value) {
            addCriterion("bussiness_criteria <>", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaGreaterThan(String value) {
            addCriterion("bussiness_criteria >", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaGreaterThanOrEqualTo(String value) {
            addCriterion("bussiness_criteria >=", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaLessThan(String value) {
            addCriterion("bussiness_criteria <", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaLessThanOrEqualTo(String value) {
            addCriterion("bussiness_criteria <=", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaLike(String value) {
            addCriterion("bussiness_criteria like", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaNotLike(String value) {
            addCriterion("bussiness_criteria not like", value, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaIn(List<String> values) {
            addCriterion("bussiness_criteria in", values, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaNotIn(List<String> values) {
            addCriterion("bussiness_criteria not in", values, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaBetween(String value1, String value2) {
            addCriterion("bussiness_criteria between", value1, value2, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andBussinessCriteriaNotBetween(String value1, String value2) {
            addCriterion("bussiness_criteria not between", value1, value2, "bussinessCriteria");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andManagementIsNull() {
            addCriterion("management is null");
            return (Criteria) this;
        }

        public Criteria andManagementIsNotNull() {
            addCriterion("management is not null");
            return (Criteria) this;
        }

        public Criteria andManagementEqualTo(Integer value) {
            addCriterion("management =", value, "management");
            return (Criteria) this;
        }

        public Criteria andManagementNotEqualTo(Integer value) {
            addCriterion("management <>", value, "management");
            return (Criteria) this;
        }

        public Criteria andManagementGreaterThan(Integer value) {
            addCriterion("management >", value, "management");
            return (Criteria) this;
        }

        public Criteria andManagementGreaterThanOrEqualTo(Integer value) {
            addCriterion("management >=", value, "management");
            return (Criteria) this;
        }

        public Criteria andManagementLessThan(Integer value) {
            addCriterion("management <", value, "management");
            return (Criteria) this;
        }

        public Criteria andManagementLessThanOrEqualTo(Integer value) {
            addCriterion("management <=", value, "management");
            return (Criteria) this;
        }

        public Criteria andManagementIn(List<Integer> values) {
            addCriterion("management in", values, "management");
            return (Criteria) this;
        }

        public Criteria andManagementNotIn(List<Integer> values) {
            addCriterion("management not in", values, "management");
            return (Criteria) this;
        }

        public Criteria andManagementBetween(Integer value1, Integer value2) {
            addCriterion("management between", value1, value2, "management");
            return (Criteria) this;
        }

        public Criteria andManagementNotBetween(Integer value1, Integer value2) {
            addCriterion("management not between", value1, value2, "management");
            return (Criteria) this;
        }

        public Criteria andActivationIsNull() {
            addCriterion("activation is null");
            return (Criteria) this;
        }

        public Criteria andActivationIsNotNull() {
            addCriterion("activation is not null");
            return (Criteria) this;
        }

        public Criteria andActivationEqualTo(String value) {
            addCriterion("activation =", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationNotEqualTo(String value) {
            addCriterion("activation <>", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationGreaterThan(String value) {
            addCriterion("activation >", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationGreaterThanOrEqualTo(String value) {
            addCriterion("activation >=", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationLessThan(String value) {
            addCriterion("activation <", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationLessThanOrEqualTo(String value) {
            addCriterion("activation <=", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationLike(String value) {
            addCriterion("activation like", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationNotLike(String value) {
            addCriterion("activation not like", value, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationIn(List<String> values) {
            addCriterion("activation in", values, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationNotIn(List<String> values) {
            addCriterion("activation not in", values, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationBetween(String value1, String value2) {
            addCriterion("activation between", value1, value2, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationNotBetween(String value1, String value2) {
            addCriterion("activation not between", value1, value2, "activation");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIsNull() {
            addCriterion("activation_time is null");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIsNotNull() {
            addCriterion("activation_time is not null");
            return (Criteria) this;
        }

        public Criteria andActivationTimeEqualTo(Date value) {
            addCriterionForJDBCDate("activation_time =", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("activation_time <>", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("activation_time >", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("activation_time >=", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeLessThan(Date value) {
            addCriterionForJDBCDate("activation_time <", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("activation_time <=", value, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeIn(List<Date> values) {
            addCriterionForJDBCDate("activation_time in", values, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("activation_time not in", values, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("activation_time between", value1, value2, "activationTime");
            return (Criteria) this;
        }

        public Criteria andActivationTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("activation_time not between", value1, value2, "activationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeIsNull() {
            addCriterion("deactivation_time is null");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeIsNotNull() {
            addCriterion("deactivation_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeEqualTo(Date value) {
            addCriterionForJDBCDate("deactivation_time =", value, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("deactivation_time <>", value, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("deactivation_time >", value, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deactivation_time >=", value, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeLessThan(Date value) {
            addCriterionForJDBCDate("deactivation_time <", value, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deactivation_time <=", value, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeIn(List<Date> values) {
            addCriterionForJDBCDate("deactivation_time in", values, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("deactivation_time not in", values, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deactivation_time between", value1, value2, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andDeactivationTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deactivation_time not between", value1, value2, "deactivationTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andReferenceIsNull() {
            addCriterion("reference is null");
            return (Criteria) this;
        }

        public Criteria andReferenceIsNotNull() {
            addCriterion("reference is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceEqualTo(String value) {
            addCriterion("reference =", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotEqualTo(String value) {
            addCriterion("reference <>", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceGreaterThan(String value) {
            addCriterion("reference >", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceGreaterThanOrEqualTo(String value) {
            addCriterion("reference >=", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceLessThan(String value) {
            addCriterion("reference <", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceLessThanOrEqualTo(String value) {
            addCriterion("reference <=", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceLike(String value) {
            addCriterion("reference like", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotLike(String value) {
            addCriterion("reference not like", value, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceIn(List<String> values) {
            addCriterion("reference in", values, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotIn(List<String> values) {
            addCriterion("reference not in", values, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceBetween(String value1, String value2) {
            addCriterion("reference between", value1, value2, "reference");
            return (Criteria) this;
        }

        public Criteria andReferenceNotBetween(String value1, String value2) {
            addCriterion("reference not between", value1, value2, "reference");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgIsNull() {
            addCriterion("commitment_msg is null");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgIsNotNull() {
            addCriterion("commitment_msg is not null");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgEqualTo(String value) {
            addCriterion("commitment_msg =", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgNotEqualTo(String value) {
            addCriterion("commitment_msg <>", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgGreaterThan(String value) {
            addCriterion("commitment_msg >", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgGreaterThanOrEqualTo(String value) {
            addCriterion("commitment_msg >=", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgLessThan(String value) {
            addCriterion("commitment_msg <", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgLessThanOrEqualTo(String value) {
            addCriterion("commitment_msg <=", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgLike(String value) {
            addCriterion("commitment_msg like", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgNotLike(String value) {
            addCriterion("commitment_msg not like", value, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgIn(List<String> values) {
            addCriterion("commitment_msg in", values, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgNotIn(List<String> values) {
            addCriterion("commitment_msg not in", values, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgBetween(String value1, String value2) {
            addCriterion("commitment_msg between", value1, value2, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitmentMsgNotBetween(String value1, String value2) {
            addCriterion("commitment_msg not between", value1, value2, "commitmentMsg");
            return (Criteria) this;
        }

        public Criteria andCommitterIsNull() {
            addCriterion("committer is null");
            return (Criteria) this;
        }

        public Criteria andCommitterIsNotNull() {
            addCriterion("committer is not null");
            return (Criteria) this;
        }

        public Criteria andCommitterEqualTo(Integer value) {
            addCriterion("committer =", value, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterNotEqualTo(Integer value) {
            addCriterion("committer <>", value, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterGreaterThan(Integer value) {
            addCriterion("committer >", value, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterGreaterThanOrEqualTo(Integer value) {
            addCriterion("committer >=", value, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterLessThan(Integer value) {
            addCriterion("committer <", value, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterLessThanOrEqualTo(Integer value) {
            addCriterion("committer <=", value, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterIn(List<Integer> values) {
            addCriterion("committer in", values, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterNotIn(List<Integer> values) {
            addCriterion("committer not in", values, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterBetween(Integer value1, Integer value2) {
            addCriterion("committer between", value1, value2, "committer");
            return (Criteria) this;
        }

        public Criteria andCommitterNotBetween(Integer value1, Integer value2) {
            addCriterion("committer not between", value1, value2, "committer");
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

        public Criteria andTechCriteriaParamsIsNull() {
            addCriterion("tech_criteria_params is null");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsIsNotNull() {
            addCriterion("tech_criteria_params is not null");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsEqualTo(String value) {
            addCriterion("tech_criteria_params =", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsNotEqualTo(String value) {
            addCriterion("tech_criteria_params <>", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsGreaterThan(String value) {
            addCriterion("tech_criteria_params >", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsGreaterThanOrEqualTo(String value) {
            addCriterion("tech_criteria_params >=", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsLessThan(String value) {
            addCriterion("tech_criteria_params <", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsLessThanOrEqualTo(String value) {
            addCriterion("tech_criteria_params <=", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsLike(String value) {
            addCriterion("tech_criteria_params like", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsNotLike(String value) {
            addCriterion("tech_criteria_params not like", value, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsIn(List<String> values) {
            addCriterion("tech_criteria_params in", values, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsNotIn(List<String> values) {
            addCriterion("tech_criteria_params not in", values, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsBetween(String value1, String value2) {
            addCriterion("tech_criteria_params between", value1, value2, "techCriteriaParams");
            return (Criteria) this;
        }

        public Criteria andTechCriteriaParamsNotBetween(String value1, String value2) {
            addCriterion("tech_criteria_params not between", value1, value2, "techCriteriaParams");
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

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCommonNameIsNull() {
            addCriterion("common_name is null");
            return (Criteria) this;
        }

        public Criteria andCommonNameIsNotNull() {
            addCriterion("common_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommonNameEqualTo(String value) {
            addCriterion("common_name =", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameNotEqualTo(String value) {
            addCriterion("common_name <>", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameGreaterThan(String value) {
            addCriterion("common_name >", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameGreaterThanOrEqualTo(String value) {
            addCriterion("common_name >=", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameLessThan(String value) {
            addCriterion("common_name <", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameLessThanOrEqualTo(String value) {
            addCriterion("common_name <=", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameLike(String value) {
            addCriterion("common_name like", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameNotLike(String value) {
            addCriterion("common_name not like", value, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameIn(List<String> values) {
            addCriterion("common_name in", values, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameNotIn(List<String> values) {
            addCriterion("common_name not in", values, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameBetween(String value1, String value2) {
            addCriterion("common_name between", value1, value2, "commonName");
            return (Criteria) this;
        }

        public Criteria andCommonNameNotBetween(String value1, String value2) {
            addCriterion("common_name not between", value1, value2, "commonName");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyIsNull() {
            addCriterion("statistical_frequency is null");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyIsNotNull() {
            addCriterion("statistical_frequency is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyEqualTo(String value) {
            addCriterion("statistical_frequency =", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyNotEqualTo(String value) {
            addCriterion("statistical_frequency <>", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyGreaterThan(String value) {
            addCriterion("statistical_frequency >", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("statistical_frequency >=", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyLessThan(String value) {
            addCriterion("statistical_frequency <", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyLessThanOrEqualTo(String value) {
            addCriterion("statistical_frequency <=", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyLike(String value) {
            addCriterion("statistical_frequency like", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyNotLike(String value) {
            addCriterion("statistical_frequency not like", value, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyIn(List<String> values) {
            addCriterion("statistical_frequency in", values, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyNotIn(List<String> values) {
            addCriterion("statistical_frequency not in", values, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyBetween(String value1, String value2) {
            addCriterion("statistical_frequency between", value1, value2, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andStatisticalFrequencyNotBetween(String value1, String value2) {
            addCriterion("statistical_frequency not between", value1, value2, "statisticalFrequency");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignIsNull() {
            addCriterion("regulatory_sign is null");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignIsNotNull() {
            addCriterion("regulatory_sign is not null");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignEqualTo(String value) {
            addCriterion("regulatory_sign =", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignNotEqualTo(String value) {
            addCriterion("regulatory_sign <>", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignGreaterThan(String value) {
            addCriterion("regulatory_sign >", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignGreaterThanOrEqualTo(String value) {
            addCriterion("regulatory_sign >=", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignLessThan(String value) {
            addCriterion("regulatory_sign <", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignLessThanOrEqualTo(String value) {
            addCriterion("regulatory_sign <=", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignLike(String value) {
            addCriterion("regulatory_sign like", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignNotLike(String value) {
            addCriterion("regulatory_sign not like", value, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignIn(List<String> values) {
            addCriterion("regulatory_sign in", values, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignNotIn(List<String> values) {
            addCriterion("regulatory_sign not in", values, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignBetween(String value1, String value2) {
            addCriterion("regulatory_sign between", value1, value2, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andRegulatorySignNotBetween(String value1, String value2) {
            addCriterion("regulatory_sign not between", value1, value2, "regulatorySign");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdIsNull() {
            addCriterion("logic_online_id is null");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdIsNotNull() {
            addCriterion("logic_online_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdEqualTo(Integer value) {
            addCriterion("logic_online_id =", value, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdNotEqualTo(Integer value) {
            addCriterion("logic_online_id <>", value, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdGreaterThan(Integer value) {
            addCriterion("logic_online_id >", value, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("logic_online_id >=", value, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdLessThan(Integer value) {
            addCriterion("logic_online_id <", value, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdLessThanOrEqualTo(Integer value) {
            addCriterion("logic_online_id <=", value, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdIn(List<Integer> values) {
            addCriterion("logic_online_id in", values, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdNotIn(List<Integer> values) {
            addCriterion("logic_online_id not in", values, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdBetween(Integer value1, Integer value2) {
            addCriterion("logic_online_id between", value1, value2, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andLogicOnlineIdNotBetween(Integer value1, Integer value2) {
            addCriterion("logic_online_id not between", value1, value2, "logicOnlineId");
            return (Criteria) this;
        }

        public Criteria andTermTypeIsNull() {
            addCriterion("term_type is null");
            return (Criteria) this;
        }

        public Criteria andTermTypeIsNotNull() {
            addCriterion("term_type is not null");
            return (Criteria) this;
        }

        public Criteria andTermTypeEqualTo(String value) {
            addCriterion("term_type =", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotEqualTo(String value) {
            addCriterion("term_type <>", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeGreaterThan(String value) {
            addCriterion("term_type >", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeGreaterThanOrEqualTo(String value) {
            addCriterion("term_type >=", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeLessThan(String value) {
            addCriterion("term_type <", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeLessThanOrEqualTo(String value) {
            addCriterion("term_type <=", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeLike(String value) {
            addCriterion("term_type like", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotLike(String value) {
            addCriterion("term_type not like", value, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeIn(List<String> values) {
            addCriterion("term_type in", values, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotIn(List<String> values) {
            addCriterion("term_type not in", values, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeBetween(String value1, String value2) {
            addCriterion("term_type between", value1, value2, "termType");
            return (Criteria) this;
        }

        public Criteria andTermTypeNotBetween(String value1, String value2) {
            addCriterion("term_type not between", value1, value2, "termType");
            return (Criteria) this;
        }

        public Criteria andModelUrlIsNull() {
            addCriterion("model_url is null");
            return (Criteria) this;
        }

        public Criteria andModelUrlIsNotNull() {
            addCriterion("model_url is not null");
            return (Criteria) this;
        }

        public Criteria andModelUrlEqualTo(String value) {
            addCriterion("model_url =", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlNotEqualTo(String value) {
            addCriterion("model_url <>", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlGreaterThan(String value) {
            addCriterion("model_url >", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlGreaterThanOrEqualTo(String value) {
            addCriterion("model_url >=", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlLessThan(String value) {
            addCriterion("model_url <", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlLessThanOrEqualTo(String value) {
            addCriterion("model_url <=", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlLike(String value) {
            addCriterion("model_url like", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlNotLike(String value) {
            addCriterion("model_url not like", value, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlIn(List<String> values) {
            addCriterion("model_url in", values, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlNotIn(List<String> values) {
            addCriterion("model_url not in", values, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlBetween(String value1, String value2) {
            addCriterion("model_url between", value1, value2, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andModelUrlNotBetween(String value1, String value2) {
            addCriterion("model_url not between", value1, value2, "modelUrl");
            return (Criteria) this;
        }

        public Criteria andIsSumIsNull() {
            addCriterion("is_sum is null");
            return (Criteria) this;
        }

        public Criteria andIsSumIsNotNull() {
            addCriterion("is_sum is not null");
            return (Criteria) this;
        }

        public Criteria andIsSumEqualTo(String value) {
            addCriterion("is_sum =", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumNotEqualTo(String value) {
            addCriterion("is_sum <>", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumGreaterThan(String value) {
            addCriterion("is_sum >", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumGreaterThanOrEqualTo(String value) {
            addCriterion("is_sum >=", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumLessThan(String value) {
            addCriterion("is_sum <", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumLessThanOrEqualTo(String value) {
            addCriterion("is_sum <=", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumLike(String value) {
            addCriterion("is_sum like", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumNotLike(String value) {
            addCriterion("is_sum not like", value, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumIn(List<String> values) {
            addCriterion("is_sum in", values, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumNotIn(List<String> values) {
            addCriterion("is_sum not in", values, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumBetween(String value1, String value2) {
            addCriterion("is_sum between", value1, value2, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsSumNotBetween(String value1, String value2) {
            addCriterion("is_sum not between", value1, value2, "isSum");
            return (Criteria) this;
        }

        public Criteria andIsGroupIsNull() {
            addCriterion("is_group is null");
            return (Criteria) this;
        }

        public Criteria andIsGroupIsNotNull() {
            addCriterion("is_group is not null");
            return (Criteria) this;
        }

        public Criteria andIsGroupEqualTo(String value) {
            addCriterion("is_group =", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupNotEqualTo(String value) {
            addCriterion("is_group <>", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupGreaterThan(String value) {
            addCriterion("is_group >", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupGreaterThanOrEqualTo(String value) {
            addCriterion("is_group >=", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupLessThan(String value) {
            addCriterion("is_group <", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupLessThanOrEqualTo(String value) {
            addCriterion("is_group <=", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupLike(String value) {
            addCriterion("is_group like", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupNotLike(String value) {
            addCriterion("is_group not like", value, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupIn(List<String> values) {
            addCriterion("is_group in", values, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupNotIn(List<String> values) {
            addCriterion("is_group not in", values, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupBetween(String value1, String value2) {
            addCriterion("is_group between", value1, value2, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsGroupNotBetween(String value1, String value2) {
            addCriterion("is_group not between", value1, value2, "isGroup");
            return (Criteria) this;
        }

        public Criteria andIsUniqIsNull() {
            addCriterion("is_uniq is null");
            return (Criteria) this;
        }

        public Criteria andIsUniqIsNotNull() {
            addCriterion("is_uniq is not null");
            return (Criteria) this;
        }

        public Criteria andIsUniqEqualTo(String value) {
            addCriterion("is_uniq =", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqNotEqualTo(String value) {
            addCriterion("is_uniq <>", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqGreaterThan(String value) {
            addCriterion("is_uniq >", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqGreaterThanOrEqualTo(String value) {
            addCriterion("is_uniq >=", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqLessThan(String value) {
            addCriterion("is_uniq <", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqLessThanOrEqualTo(String value) {
            addCriterion("is_uniq <=", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqLike(String value) {
            addCriterion("is_uniq like", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqNotLike(String value) {
            addCriterion("is_uniq not like", value, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqIn(List<String> values) {
            addCriterion("is_uniq in", values, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqNotIn(List<String> values) {
            addCriterion("is_uniq not in", values, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqBetween(String value1, String value2) {
            addCriterion("is_uniq between", value1, value2, "isUniq");
            return (Criteria) this;
        }

        public Criteria andIsUniqNotBetween(String value1, String value2) {
            addCriterion("is_uniq not between", value1, value2, "isUniq");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(String value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(String value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(String value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(String value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(String value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLike(String value) {
            addCriterion("value_type like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotLike(String value) {
            addCriterion("value_type not like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<String> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<String> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(String value1, String value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(String value1, String value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
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