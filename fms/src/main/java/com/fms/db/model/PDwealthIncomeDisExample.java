package com.fms.db.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PDwealthIncomeDisExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public PDwealthIncomeDisExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
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
     * This method corresponds to the database table t_pd_wealth_income_dis
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
     * This method corresponds to the database table t_pd_wealth_income_dis
     *
     * @mbggenerated Mon Nov 16 15:12:23 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_pd_wealth_income_dis
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
     * This class corresponds to the database table t_pd_wealth_income_dis
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

        public Criteria andPdWealthIncomeDistributeIdIsNull() {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdIsNotNull() {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdEqualTo(Long value) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID =", value, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdNotEqualTo(Long value) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID <>", value, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdGreaterThan(Long value) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID >", value, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID >=", value, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdLessThan(Long value) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID <", value, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdLessThanOrEqualTo(Long value) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID <=", value, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdIn(List<Long> values) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID in", values, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdNotIn(List<Long> values) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID not in", values, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdBetween(Long value1, Long value2) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID between", value1, value2, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIncomeDistributeIdNotBetween(Long value1, Long value2) {
            addCriterion("PD_WEALTH_INCOME_DISTRIBUTE_ID not between", value1, value2, "pdWealthIncomeDistributeId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdIsNull() {
            addCriterion("PD_WEALTH_ID is null");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdIsNotNull() {
            addCriterion("PD_WEALTH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdEqualTo(Long value) {
            addCriterion("PD_WEALTH_ID =", value, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdNotEqualTo(Long value) {
            addCriterion("PD_WEALTH_ID <>", value, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdGreaterThan(Long value) {
            addCriterion("PD_WEALTH_ID >", value, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PD_WEALTH_ID >=", value, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdLessThan(Long value) {
            addCriterion("PD_WEALTH_ID <", value, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdLessThanOrEqualTo(Long value) {
            addCriterion("PD_WEALTH_ID <=", value, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdIn(List<Long> values) {
            addCriterion("PD_WEALTH_ID in", values, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdNotIn(List<Long> values) {
            addCriterion("PD_WEALTH_ID not in", values, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdBetween(Long value1, Long value2) {
            addCriterion("PD_WEALTH_ID between", value1, value2, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPdWealthIdNotBetween(Long value1, Long value2) {
            addCriterion("PD_WEALTH_ID not between", value1, value2, "pdWealthId");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateIsNull() {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateIsNotNull() {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateEqualTo(BigDecimal value) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE =", value, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateNotEqualTo(BigDecimal value) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE <>", value, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateGreaterThan(BigDecimal value) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE >", value, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE >=", value, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateLessThan(BigDecimal value) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE <", value, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE <=", value, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateIn(List<BigDecimal> values) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE in", values, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateNotIn(List<BigDecimal> values) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE not in", values, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE between", value1, value2, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andPrincipalDistributeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRINCIPAL_DISTRIBUTE_RATE not between", value1, value2, "principalDistributeRate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateIsNull() {
            addCriterion("DISTRIBUTE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDistributeDateIsNotNull() {
            addCriterion("DISTRIBUTE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeDateEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE =", value, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE <>", value, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE >", value, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE >=", value, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateLessThan(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE <", value, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE <=", value, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateIn(List<Date> values) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE in", values, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE not in", values, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE between", value1, value2, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISTRIBUTE_DATE not between", value1, value2, "distributeDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateIsNull() {
            addCriterion("DISTRIBUTE_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateIsNotNull() {
            addCriterion("DISTRIBUTE_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE =", value, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE <>", value, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE >", value, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE >=", value, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateLessThan(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE <", value, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE <=", value, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE in", values, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE not in", values, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE between", value1, value2, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISTRIBUTE_START_DATE not between", value1, value2, "distributeStartDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateIsNull() {
            addCriterion("DISTRIBUTE_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateIsNotNull() {
            addCriterion("DISTRIBUTE_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE =", value, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE <>", value, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE >", value, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE >=", value, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateLessThan(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE <", value, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE <=", value, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE in", values, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE not in", values, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE between", value1, value2, "distributeEndDate");
            return (Criteria) this;
        }

        public Criteria andDistributeEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISTRIBUTE_END_DATE not between", value1, value2, "distributeEndDate");
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
     * This class corresponds to the database table t_pd_wealth_income_dis
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
     * This class corresponds to the database table t_pd_wealth_income_dis
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