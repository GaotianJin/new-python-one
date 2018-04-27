package com.fms.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HolidaysExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public HolidaysExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
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
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
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

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andHolidayIsNull() {
            addCriterion("holiday is null");
            return (Criteria) this;
        }

        public Criteria andHolidayIsNotNull() {
            addCriterion("holiday is not null");
            return (Criteria) this;
        }

        public Criteria andHolidayEqualTo(String value) {
            addCriterion("holiday =", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayNotEqualTo(String value) {
            addCriterion("holiday <>", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayGreaterThan(String value) {
            addCriterion("holiday >", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayGreaterThanOrEqualTo(String value) {
            addCriterion("holiday >=", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayLessThan(String value) {
            addCriterion("holiday <", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayLessThanOrEqualTo(String value) {
            addCriterion("holiday <=", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayLike(String value) {
            addCriterion("holiday like", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayNotLike(String value) {
            addCriterion("holiday not like", value, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayIn(List<String> values) {
            addCriterion("holiday in", values, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayNotIn(List<String> values) {
            addCriterion("holiday not in", values, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayBetween(String value1, String value2) {
            addCriterion("holiday between", value1, value2, "holiday");
            return (Criteria) this;
        }

        public Criteria andHolidayNotBetween(String value1, String value2) {
            addCriterion("holiday not between", value1, value2, "holiday");
            return (Criteria) this;
        }

        public Criteria andBusstypeIsNull() {
            addCriterion("busstype is null");
            return (Criteria) this;
        }

        public Criteria andBusstypeIsNotNull() {
            addCriterion("busstype is not null");
            return (Criteria) this;
        }

        public Criteria andBusstypeEqualTo(String value) {
            addCriterion("busstype =", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeNotEqualTo(String value) {
            addCriterion("busstype <>", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeGreaterThan(String value) {
            addCriterion("busstype >", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeGreaterThanOrEqualTo(String value) {
            addCriterion("busstype >=", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeLessThan(String value) {
            addCriterion("busstype <", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeLessThanOrEqualTo(String value) {
            addCriterion("busstype <=", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeLike(String value) {
            addCriterion("busstype like", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeNotLike(String value) {
            addCriterion("busstype not like", value, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeIn(List<String> values) {
            addCriterion("busstype in", values, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeNotIn(List<String> values) {
            addCriterion("busstype not in", values, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeBetween(String value1, String value2) {
            addCriterion("busstype between", value1, value2, "busstype");
            return (Criteria) this;
        }

        public Criteria andBusstypeNotBetween(String value1, String value2) {
            addCriterion("busstype not between", value1, value2, "busstype");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdIsNull() {
            addCriterion("opeartor_id is null");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdIsNotNull() {
            addCriterion("opeartor_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdEqualTo(Integer value) {
            addCriterion("opeartor_id =", value, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdNotEqualTo(Integer value) {
            addCriterion("opeartor_id <>", value, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdGreaterThan(Integer value) {
            addCriterion("opeartor_id >", value, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("opeartor_id >=", value, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdLessThan(Integer value) {
            addCriterion("opeartor_id <", value, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdLessThanOrEqualTo(Integer value) {
            addCriterion("opeartor_id <=", value, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdIn(List<Integer> values) {
            addCriterion("opeartor_id in", values, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdNotIn(List<Integer> values) {
            addCriterion("opeartor_id not in", values, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdBetween(Integer value1, Integer value2) {
            addCriterion("opeartor_id between", value1, value2, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andOpeartorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("opeartor_id not between", value1, value2, "opeartorId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_holidays
     *
     * @mbggenerated do_not_delete_during_merge Mon Jul 04 14:09:42 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_holidays
     *
     * @mbggenerated Mon Jul 04 14:09:42 CST 2016
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