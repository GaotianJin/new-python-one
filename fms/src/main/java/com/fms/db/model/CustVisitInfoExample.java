package com.fms.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CustVisitInfoExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public CustVisitInfoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
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

		public Criteria andCustVisitorInfoIdIsNull() {
			addCriterion("CUST_VISITOR_INFO_ID is null");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdIsNotNull() {
			addCriterion("CUST_VISITOR_INFO_ID is not null");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdEqualTo(Long value) {
			addCriterion("CUST_VISITOR_INFO_ID =", value, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdNotEqualTo(Long value) {
			addCriterion("CUST_VISITOR_INFO_ID <>", value, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdGreaterThan(Long value) {
			addCriterion("CUST_VISITOR_INFO_ID >", value, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdGreaterThanOrEqualTo(Long value) {
			addCriterion("CUST_VISITOR_INFO_ID >=", value, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdLessThan(Long value) {
			addCriterion("CUST_VISITOR_INFO_ID <", value, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdLessThanOrEqualTo(Long value) {
			addCriterion("CUST_VISITOR_INFO_ID <=", value, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdIn(List<Long> values) {
			addCriterion("CUST_VISITOR_INFO_ID in", values, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdNotIn(List<Long> values) {
			addCriterion("CUST_VISITOR_INFO_ID not in", values, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdBetween(Long value1, Long value2) {
			addCriterion("CUST_VISITOR_INFO_ID between", value1, value2, "custVisitorInfoId");
			return (Criteria) this;
		}

		public Criteria andCustVisitorInfoIdNotBetween(Long value1, Long value2) {
			addCriterion("CUST_VISITOR_INFO_ID not between", value1, value2, "custVisitorInfoId");
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

		public Criteria andCustVisitTimeIsNull() {
			addCriterion("CUST_VISIT_TIME is null");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeIsNotNull() {
			addCriterion("CUST_VISIT_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeEqualTo(Date value) {
			addCriterion("CUST_VISIT_TIME =", value, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeNotEqualTo(Date value) {
			addCriterion("CUST_VISIT_TIME <>", value, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeGreaterThan(Date value) {
			addCriterion("CUST_VISIT_TIME >", value, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("CUST_VISIT_TIME >=", value, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeLessThan(Date value) {
			addCriterion("CUST_VISIT_TIME <", value, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeLessThanOrEqualTo(Date value) {
			addCriterion("CUST_VISIT_TIME <=", value, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeIn(List<Date> values) {
			addCriterion("CUST_VISIT_TIME in", values, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeNotIn(List<Date> values) {
			addCriterion("CUST_VISIT_TIME not in", values, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeBetween(Date value1, Date value2) {
			addCriterion("CUST_VISIT_TIME between", value1, value2, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTimeNotBetween(Date value1, Date value2) {
			addCriterion("CUST_VISIT_TIME not between", value1, value2, "custVisitTime");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeIsNull() {
			addCriterion("CUST_VISIT_TYPE is null");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeIsNotNull() {
			addCriterion("CUST_VISIT_TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeEqualTo(String value) {
			addCriterion("CUST_VISIT_TYPE =", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeNotEqualTo(String value) {
			addCriterion("CUST_VISIT_TYPE <>", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeGreaterThan(String value) {
			addCriterion("CUST_VISIT_TYPE >", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeGreaterThanOrEqualTo(String value) {
			addCriterion("CUST_VISIT_TYPE >=", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeLessThan(String value) {
			addCriterion("CUST_VISIT_TYPE <", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeLessThanOrEqualTo(String value) {
			addCriterion("CUST_VISIT_TYPE <=", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeLike(String value) {
			addCriterion("CUST_VISIT_TYPE like", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeNotLike(String value) {
			addCriterion("CUST_VISIT_TYPE not like", value, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeIn(List<String> values) {
			addCriterion("CUST_VISIT_TYPE in", values, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeNotIn(List<String> values) {
			addCriterion("CUST_VISIT_TYPE not in", values, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeBetween(String value1, String value2) {
			addCriterion("CUST_VISIT_TYPE between", value1, value2, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitTypeNotBetween(String value1, String value2) {
			addCriterion("CUST_VISIT_TYPE not between", value1, value2, "custVisitType");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionIsNull() {
			addCriterion("CUST_VISIT_ACTION is null");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionIsNotNull() {
			addCriterion("CUST_VISIT_ACTION is not null");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionEqualTo(String value) {
			addCriterion("CUST_VISIT_ACTION =", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionNotEqualTo(String value) {
			addCriterion("CUST_VISIT_ACTION <>", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionGreaterThan(String value) {
			addCriterion("CUST_VISIT_ACTION >", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionGreaterThanOrEqualTo(String value) {
			addCriterion("CUST_VISIT_ACTION >=", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionLessThan(String value) {
			addCriterion("CUST_VISIT_ACTION <", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionLessThanOrEqualTo(String value) {
			addCriterion("CUST_VISIT_ACTION <=", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionLike(String value) {
			addCriterion("CUST_VISIT_ACTION like", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionNotLike(String value) {
			addCriterion("CUST_VISIT_ACTION not like", value, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionIn(List<String> values) {
			addCriterion("CUST_VISIT_ACTION in", values, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionNotIn(List<String> values) {
			addCriterion("CUST_VISIT_ACTION not in", values, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionBetween(String value1, String value2) {
			addCriterion("CUST_VISIT_ACTION between", value1, value2, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitActionNotBetween(String value1, String value2) {
			addCriterion("CUST_VISIT_ACTION not between", value1, value2, "custVisitAction");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentIsNull() {
			addCriterion("CUST_VISIT_CONTENT is null");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentIsNotNull() {
			addCriterion("CUST_VISIT_CONTENT is not null");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentEqualTo(String value) {
			addCriterion("CUST_VISIT_CONTENT =", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentNotEqualTo(String value) {
			addCriterion("CUST_VISIT_CONTENT <>", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentGreaterThan(String value) {
			addCriterion("CUST_VISIT_CONTENT >", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentGreaterThanOrEqualTo(String value) {
			addCriterion("CUST_VISIT_CONTENT >=", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentLessThan(String value) {
			addCriterion("CUST_VISIT_CONTENT <", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentLessThanOrEqualTo(String value) {
			addCriterion("CUST_VISIT_CONTENT <=", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentLike(String value) {
			addCriterion("CUST_VISIT_CONTENT like", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentNotLike(String value) {
			addCriterion("CUST_VISIT_CONTENT not like", value, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentIn(List<String> values) {
			addCriterion("CUST_VISIT_CONTENT in", values, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentNotIn(List<String> values) {
			addCriterion("CUST_VISIT_CONTENT not in", values, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentBetween(String value1, String value2) {
			addCriterion("CUST_VISIT_CONTENT between", value1, value2, "custVisitContent");
			return (Criteria) this;
		}

		public Criteria andCustVisitContentNotBetween(String value1, String value2) {
			addCriterion("CUST_VISIT_CONTENT not between", value1, value2, "custVisitContent");
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

		public Criteria andNextVisitTimeIsNull() {
			addCriterion("NEXT_VISIT_TIME is null");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeIsNotNull() {
			addCriterion("NEXT_VISIT_TIME is not null");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeEqualTo(Date value) {
			addCriterion("NEXT_VISIT_TIME =", value, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeNotEqualTo(Date value) {
			addCriterion("NEXT_VISIT_TIME <>", value, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeGreaterThan(Date value) {
			addCriterion("NEXT_VISIT_TIME >", value, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("NEXT_VISIT_TIME >=", value, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeLessThan(Date value) {
			addCriterion("NEXT_VISIT_TIME <", value, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeLessThanOrEqualTo(Date value) {
			addCriterion("NEXT_VISIT_TIME <=", value, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeIn(List<Date> values) {
			addCriterion("NEXT_VISIT_TIME in", values, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeNotIn(List<Date> values) {
			addCriterion("NEXT_VISIT_TIME not in", values, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeBetween(Date value1, Date value2) {
			addCriterion("NEXT_VISIT_TIME between", value1, value2, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andNextVisitTimeNotBetween(Date value1, Date value2) {
			addCriterion("NEXT_VISIT_TIME not between", value1, value2, "nextVisitTime");
			return (Criteria) this;
		}

		public Criteria andAgentIdIsNull() {
			addCriterion("AGENT_ID is null");
			return (Criteria) this;
		}

		public Criteria andAgentIdIsNotNull() {
			addCriterion("AGENT_ID is not null");
			return (Criteria) this;
		}

		public Criteria andAgentIdEqualTo(Long value) {
			addCriterion("AGENT_ID =", value, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdNotEqualTo(Long value) {
			addCriterion("AGENT_ID <>", value, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdGreaterThan(Long value) {
			addCriterion("AGENT_ID >", value, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdGreaterThanOrEqualTo(Long value) {
			addCriterion("AGENT_ID >=", value, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdLessThan(Long value) {
			addCriterion("AGENT_ID <", value, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdLessThanOrEqualTo(Long value) {
			addCriterion("AGENT_ID <=", value, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdIn(List<Long> values) {
			addCriterion("AGENT_ID in", values, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdNotIn(List<Long> values) {
			addCriterion("AGENT_ID not in", values, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdBetween(Long value1, Long value2) {
			addCriterion("AGENT_ID between", value1, value2, "agentId");
			return (Criteria) this;
		}

		public Criteria andAgentIdNotBetween(Long value1, Long value2) {
			addCriterion("AGENT_ID not between", value1, value2, "agentId");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_cust_visit_info
	 * @mbggenerated  Mon Jan 25 20:52:05 CST 2016
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_cust_visit_info
     *
     * @mbggenerated do_not_delete_during_merge Wed Dec 02 11:28:15 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}