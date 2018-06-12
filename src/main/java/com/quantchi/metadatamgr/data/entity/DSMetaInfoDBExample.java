package com.quantchi.metadatamgr.data.entity;

import java.util.ArrayList;
import java.util.List;

public class DSMetaInfoDBExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DSMetaInfoDBExample() {
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

        public Criteria andDsNameIsNull() {
            addCriterion("ds_name is null");
            return (Criteria) this;
        }

        public Criteria andDsNameIsNotNull() {
            addCriterion("ds_name is not null");
            return (Criteria) this;
        }

        public Criteria andDsNameEqualTo(String value) {
            addCriterion("ds_name =", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotEqualTo(String value) {
            addCriterion("ds_name <>", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameGreaterThan(String value) {
            addCriterion("ds_name >", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameGreaterThanOrEqualTo(String value) {
            addCriterion("ds_name >=", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameLessThan(String value) {
            addCriterion("ds_name <", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameLessThanOrEqualTo(String value) {
            addCriterion("ds_name <=", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameLike(String value) {
            addCriterion("ds_name like", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotLike(String value) {
            addCriterion("ds_name not like", value, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameIn(List<String> values) {
            addCriterion("ds_name in", values, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotIn(List<String> values) {
            addCriterion("ds_name not in", values, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameBetween(String value1, String value2) {
            addCriterion("ds_name between", value1, value2, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsNameNotBetween(String value1, String value2) {
            addCriterion("ds_name not between", value1, value2, "dsName");
            return (Criteria) this;
        }

        public Criteria andDsTypeIsNull() {
            addCriterion("ds_type is null");
            return (Criteria) this;
        }

        public Criteria andDsTypeIsNotNull() {
            addCriterion("ds_type is not null");
            return (Criteria) this;
        }

        public Criteria andDsTypeEqualTo(String value) {
            addCriterion("ds_type =", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotEqualTo(String value) {
            addCriterion("ds_type <>", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeGreaterThan(String value) {
            addCriterion("ds_type >", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ds_type >=", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeLessThan(String value) {
            addCriterion("ds_type <", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeLessThanOrEqualTo(String value) {
            addCriterion("ds_type <=", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeLike(String value) {
            addCriterion("ds_type like", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotLike(String value) {
            addCriterion("ds_type not like", value, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeIn(List<String> values) {
            addCriterion("ds_type in", values, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotIn(List<String> values) {
            addCriterion("ds_type not in", values, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeBetween(String value1, String value2) {
            addCriterion("ds_type between", value1, value2, "dsType");
            return (Criteria) this;
        }

        public Criteria andDsTypeNotBetween(String value1, String value2) {
            addCriterion("ds_type not between", value1, value2, "dsType");
            return (Criteria) this;
        }

        public Criteria andHostIsNull() {
            addCriterion("host is null");
            return (Criteria) this;
        }

        public Criteria andHostIsNotNull() {
            addCriterion("host is not null");
            return (Criteria) this;
        }

        public Criteria andHostEqualTo(String value) {
            addCriterion("host =", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotEqualTo(String value) {
            addCriterion("host <>", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThan(String value) {
            addCriterion("host >", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostGreaterThanOrEqualTo(String value) {
            addCriterion("host >=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThan(String value) {
            addCriterion("host <", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLessThanOrEqualTo(String value) {
            addCriterion("host <=", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostLike(String value) {
            addCriterion("host like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotLike(String value) {
            addCriterion("host not like", value, "host");
            return (Criteria) this;
        }

        public Criteria andHostIn(List<String> values) {
            addCriterion("host in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotIn(List<String> values) {
            addCriterion("host not in", values, "host");
            return (Criteria) this;
        }

        public Criteria andHostBetween(String value1, String value2) {
            addCriterion("host between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andHostNotBetween(String value1, String value2) {
            addCriterion("host not between", value1, value2, "host");
            return (Criteria) this;
        }

        public Criteria andPortIsNull() {
            addCriterion("port is null");
            return (Criteria) this;
        }

        public Criteria andPortIsNotNull() {
            addCriterion("port is not null");
            return (Criteria) this;
        }

        public Criteria andPortEqualTo(String value) {
            addCriterion("port =", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotEqualTo(String value) {
            addCriterion("port <>", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThan(String value) {
            addCriterion("port >", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortGreaterThanOrEqualTo(String value) {
            addCriterion("port >=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThan(String value) {
            addCriterion("port <", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLessThanOrEqualTo(String value) {
            addCriterion("port <=", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortLike(String value) {
            addCriterion("port like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotLike(String value) {
            addCriterion("port not like", value, "port");
            return (Criteria) this;
        }

        public Criteria andPortIn(List<String> values) {
            addCriterion("port in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotIn(List<String> values) {
            addCriterion("port not in", values, "port");
            return (Criteria) this;
        }

        public Criteria andPortBetween(String value1, String value2) {
            addCriterion("port between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andPortNotBetween(String value1, String value2) {
            addCriterion("port not between", value1, value2, "port");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlIsNull() {
            addCriterion("hive_meta_mysql_url is null");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlIsNotNull() {
            addCriterion("hive_meta_mysql_url is not null");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlEqualTo(String value) {
            addCriterion("hive_meta_mysql_url =", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlNotEqualTo(String value) {
            addCriterion("hive_meta_mysql_url <>", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlGreaterThan(String value) {
            addCriterion("hive_meta_mysql_url >", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlGreaterThanOrEqualTo(String value) {
            addCriterion("hive_meta_mysql_url >=", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlLessThan(String value) {
            addCriterion("hive_meta_mysql_url <", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlLessThanOrEqualTo(String value) {
            addCriterion("hive_meta_mysql_url <=", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlLike(String value) {
            addCriterion("hive_meta_mysql_url like", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlNotLike(String value) {
            addCriterion("hive_meta_mysql_url not like", value, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlIn(List<String> values) {
            addCriterion("hive_meta_mysql_url in", values, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlNotIn(List<String> values) {
            addCriterion("hive_meta_mysql_url not in", values, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlBetween(String value1, String value2) {
            addCriterion("hive_meta_mysql_url between", value1, value2, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaMysqlUrlNotBetween(String value1, String value2) {
            addCriterion("hive_meta_mysql_url not between", value1, value2, "hiveMetaMysqlUrl");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameIsNull() {
            addCriterion("hive_meta_username is null");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameIsNotNull() {
            addCriterion("hive_meta_username is not null");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameEqualTo(String value) {
            addCriterion("hive_meta_username =", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameNotEqualTo(String value) {
            addCriterion("hive_meta_username <>", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameGreaterThan(String value) {
            addCriterion("hive_meta_username >", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("hive_meta_username >=", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameLessThan(String value) {
            addCriterion("hive_meta_username <", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameLessThanOrEqualTo(String value) {
            addCriterion("hive_meta_username <=", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameLike(String value) {
            addCriterion("hive_meta_username like", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameNotLike(String value) {
            addCriterion("hive_meta_username not like", value, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameIn(List<String> values) {
            addCriterion("hive_meta_username in", values, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameNotIn(List<String> values) {
            addCriterion("hive_meta_username not in", values, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameBetween(String value1, String value2) {
            addCriterion("hive_meta_username between", value1, value2, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaUsernameNotBetween(String value1, String value2) {
            addCriterion("hive_meta_username not between", value1, value2, "hiveMetaUsername");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdIsNull() {
            addCriterion("hive_meta_pswd is null");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdIsNotNull() {
            addCriterion("hive_meta_pswd is not null");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdEqualTo(String value) {
            addCriterion("hive_meta_pswd =", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdNotEqualTo(String value) {
            addCriterion("hive_meta_pswd <>", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdGreaterThan(String value) {
            addCriterion("hive_meta_pswd >", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdGreaterThanOrEqualTo(String value) {
            addCriterion("hive_meta_pswd >=", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdLessThan(String value) {
            addCriterion("hive_meta_pswd <", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdLessThanOrEqualTo(String value) {
            addCriterion("hive_meta_pswd <=", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdLike(String value) {
            addCriterion("hive_meta_pswd like", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdNotLike(String value) {
            addCriterion("hive_meta_pswd not like", value, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdIn(List<String> values) {
            addCriterion("hive_meta_pswd in", values, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdNotIn(List<String> values) {
            addCriterion("hive_meta_pswd not in", values, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdBetween(String value1, String value2) {
            addCriterion("hive_meta_pswd between", value1, value2, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andHiveMetaPswdNotBetween(String value1, String value2) {
            addCriterion("hive_meta_pswd not between", value1, value2, "hiveMetaPswd");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("create_user_id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("create_user_id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
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