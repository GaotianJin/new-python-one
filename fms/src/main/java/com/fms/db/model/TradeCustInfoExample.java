package com.fms.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeCustInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public TradeCustInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
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

        public Criteria andTradeCustInfoIdIsNull() {
            addCriterion("TRADE_CUST_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdIsNotNull() {
            addCriterion("TRADE_CUST_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdEqualTo(Long value) {
            addCriterion("TRADE_CUST_INFO_ID =", value, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdNotEqualTo(Long value) {
            addCriterion("TRADE_CUST_INFO_ID <>", value, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdGreaterThan(Long value) {
            addCriterion("TRADE_CUST_INFO_ID >", value, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TRADE_CUST_INFO_ID >=", value, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdLessThan(Long value) {
            addCriterion("TRADE_CUST_INFO_ID <", value, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("TRADE_CUST_INFO_ID <=", value, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdIn(List<Long> values) {
            addCriterion("TRADE_CUST_INFO_ID in", values, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdNotIn(List<Long> values) {
            addCriterion("TRADE_CUST_INFO_ID not in", values, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdBetween(Long value1, Long value2) {
            addCriterion("TRADE_CUST_INFO_ID between", value1, value2, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeCustInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("TRADE_CUST_INFO_ID not between", value1, value2, "tradeCustInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdIsNull() {
            addCriterion("TRADE_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdIsNotNull() {
            addCriterion("TRADE_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdEqualTo(Long value) {
            addCriterion("TRADE_INFO_ID =", value, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdNotEqualTo(Long value) {
            addCriterion("TRADE_INFO_ID <>", value, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdGreaterThan(Long value) {
            addCriterion("TRADE_INFO_ID >", value, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TRADE_INFO_ID >=", value, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdLessThan(Long value) {
            addCriterion("TRADE_INFO_ID <", value, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("TRADE_INFO_ID <=", value, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdIn(List<Long> values) {
            addCriterion("TRADE_INFO_ID in", values, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdNotIn(List<Long> values) {
            addCriterion("TRADE_INFO_ID not in", values, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdBetween(Long value1, Long value2) {
            addCriterion("TRADE_INFO_ID between", value1, value2, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andTradeInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("TRADE_INFO_ID not between", value1, value2, "tradeInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdIsNull() {
            addCriterion("CUST_BASE_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdIsNotNull() {
            addCriterion("CUST_BASE_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdEqualTo(Long value) {
            addCriterion("CUST_BASE_INFO_ID =", value, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdNotEqualTo(Long value) {
            addCriterion("CUST_BASE_INFO_ID <>", value, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdGreaterThan(Long value) {
            addCriterion("CUST_BASE_INFO_ID >", value, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_BASE_INFO_ID >=", value, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdLessThan(Long value) {
            addCriterion("CUST_BASE_INFO_ID <", value, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("CUST_BASE_INFO_ID <=", value, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdIn(List<Long> values) {
            addCriterion("CUST_BASE_INFO_ID in", values, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdNotIn(List<Long> values) {
            addCriterion("CUST_BASE_INFO_ID not in", values, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdBetween(Long value1, Long value2) {
            addCriterion("CUST_BASE_INFO_ID between", value1, value2, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andCustBaseInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("CUST_BASE_INFO_ID not between", value1, value2, "custBaseInfoId");
            return (Criteria) this;
        }

        public Criteria andRcStateIsNull() {
            addCriterion("RC_STATE is null");
            return (Criteria) this;
        }

        public Criteria andRcStateIsNotNull() {
            addCriterion("RC_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andRcStateEqualTo(String value) {
            addCriterion("RC_STATE =", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateNotEqualTo(String value) {
            addCriterion("RC_STATE <>", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateGreaterThan(String value) {
            addCriterion("RC_STATE >", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateGreaterThanOrEqualTo(String value) {
            addCriterion("RC_STATE >=", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateLessThan(String value) {
            addCriterion("RC_STATE <", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateLessThanOrEqualTo(String value) {
            addCriterion("RC_STATE <=", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateLike(String value) {
            addCriterion("RC_STATE like", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateNotLike(String value) {
            addCriterion("RC_STATE not like", value, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateIn(List<String> values) {
            addCriterion("RC_STATE in", values, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateNotIn(List<String> values) {
            addCriterion("RC_STATE not in", values, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateBetween(String value1, String value2) {
            addCriterion("RC_STATE between", value1, value2, "rcState");
            return (Criteria) this;
        }

        public Criteria andRcStateNotBetween(String value1, String value2) {
            addCriterion("RC_STATE not between", value1, value2, "rcState");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("MODIFY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("MODIFY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("MODIFY_DATE =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("MODIFY_DATE <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("MODIFY_DATE >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFY_DATE >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("MODIFY_DATE <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("MODIFY_DATE <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("MODIFY_DATE in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("MODIFY_DATE not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("MODIFY_DATE between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("MODIFY_DATE not between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andOperComIdIsNull() {
            addCriterion("OPER_COM_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperComIdIsNotNull() {
            addCriterion("OPER_COM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperComIdEqualTo(Long value) {
            addCriterion("OPER_COM_ID =", value, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdNotEqualTo(Long value) {
            addCriterion("OPER_COM_ID <>", value, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdGreaterThan(Long value) {
            addCriterion("OPER_COM_ID >", value, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OPER_COM_ID >=", value, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdLessThan(Long value) {
            addCriterion("OPER_COM_ID <", value, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdLessThanOrEqualTo(Long value) {
            addCriterion("OPER_COM_ID <=", value, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdIn(List<Long> values) {
            addCriterion("OPER_COM_ID in", values, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdNotIn(List<Long> values) {
            addCriterion("OPER_COM_ID not in", values, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdBetween(Long value1, Long value2) {
            addCriterion("OPER_COM_ID between", value1, value2, "operComId");
            return (Criteria) this;
        }

        public Criteria andOperComIdNotBetween(Long value1, Long value2) {
            addCriterion("OPER_COM_ID not between", value1, value2, "operComId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("CREATE_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("CREATE_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Long value) {
            addCriterion("CREATE_USER_ID =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Long value) {
            addCriterion("CREATE_USER_ID <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Long value) {
            addCriterion("CREATE_USER_ID >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_USER_ID >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Long value) {
            addCriterion("CREATE_USER_ID <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_USER_ID <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Long> values) {
            addCriterion("CREATE_USER_ID in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Long> values) {
            addCriterion("CREATE_USER_ID not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Long value1, Long value2) {
            addCriterion("CREATE_USER_ID between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_USER_ID not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNull() {
            addCriterion("MODIFY_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIsNotNull() {
            addCriterion("MODIFY_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdEqualTo(Long value) {
            addCriterion("MODIFY_USER_ID =", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotEqualTo(Long value) {
            addCriterion("MODIFY_USER_ID <>", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThan(Long value) {
            addCriterion("MODIFY_USER_ID >", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MODIFY_USER_ID >=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThan(Long value) {
            addCriterion("MODIFY_USER_ID <", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdLessThanOrEqualTo(Long value) {
            addCriterion("MODIFY_USER_ID <=", value, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdIn(List<Long> values) {
            addCriterion("MODIFY_USER_ID in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotIn(List<Long> values) {
            addCriterion("MODIFY_USER_ID not in", values, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdBetween(Long value1, Long value2) {
            addCriterion("MODIFY_USER_ID between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }

        public Criteria andModifyUserIdNotBetween(Long value1, Long value2) {
            addCriterion("MODIFY_USER_ID not between", value1, value2, "modifyUserId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated do_not_delete_during_merge Mon Nov 16 15:12:23 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_trade_cust_info
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
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