package com.fms.db.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SlySalaryExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */public SlySalaryExample(){oredCriteria=new ArrayList<Criteria>();}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */public List<Criteria> getOredCriteria(){return oredCriteria;}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */protected abstract static class GeneratedCriteria {protected List<Criterion> criteria;protected GeneratedCriteria(){super();criteria=new ArrayList<Criterion>();}public boolean isValid(){return criteria.size() > 0;}public List<Criterion> getAllCriteria(){return criteria;}public List<Criterion> getCriteria(){return criteria;}protected void addCriterion(String condition){if (condition == null){throw new RuntimeException("Value for condition cannot be null");}criteria.add(new Criterion(condition));}protected void addCriterion(String condition,Object value,String property){if (value == null){throw new RuntimeException("Value for " + property + " cannot be null");}criteria.add(new Criterion(condition,value));}protected void addCriterion(String condition,Object value1,Object value2,String property){if (value1 == null || value2 == null){throw new RuntimeException("Between values for " + property + " cannot be null");}criteria.add(new Criterion(condition,value1,value2));}public Criteria andSlySalaryIdIsNull(){addCriterion("sly_salary_id is null");return (Criteria)this;}public Criteria andSlySalaryIdIsNotNull(){addCriterion("sly_salary_id is not null");return (Criteria)this;}public Criteria andSlySalaryIdEqualTo(Long value){addCriterion("sly_salary_id =",value,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdNotEqualTo(Long value){addCriterion("sly_salary_id <>",value,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdGreaterThan(Long value){addCriterion("sly_salary_id >",value,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdGreaterThanOrEqualTo(Long value){addCriterion("sly_salary_id >=",value,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdLessThan(Long value){addCriterion("sly_salary_id <",value,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdLessThanOrEqualTo(Long value){addCriterion("sly_salary_id <=",value,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdIn(List<Long> values){addCriterion("sly_salary_id in",values,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdNotIn(List<Long> values){addCriterion("sly_salary_id not in",values,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdBetween(Long value1,Long value2){addCriterion("sly_salary_id between",value1,value2,"slySalaryId");return (Criteria)this;}public Criteria andSlySalaryIdNotBetween(Long value1,Long value2){addCriterion("sly_salary_id not between",value1,value2,"slySalaryId");return (Criteria)this;}public Criteria andMonthIsNull(){addCriterion("month is null");return (Criteria)this;}public Criteria andMonthIsNotNull(){addCriterion("month is not null");return (Criteria)this;}public Criteria andMonthEqualTo(String value){addCriterion("month =",value,"month");return (Criteria)this;}public Criteria andMonthNotEqualTo(String value){addCriterion("month <>",value,"month");return (Criteria)this;}public Criteria andMonthGreaterThan(String value){addCriterion("month >",value,"month");return (Criteria)this;}public Criteria andMonthGreaterThanOrEqualTo(String value){addCriterion("month >=",value,"month");return (Criteria)this;}public Criteria andMonthLessThan(String value){addCriterion("month <",value,"month");return (Criteria)this;}public Criteria andMonthLessThanOrEqualTo(String value){addCriterion("month <=",value,"month");return (Criteria)this;}public Criteria andMonthLike(String value){addCriterion("month like",value,"month");return (Criteria)this;}public Criteria andMonthNotLike(String value){addCriterion("month not like",value,"month");return (Criteria)this;}public Criteria andMonthIn(List<String> values){addCriterion("month in",values,"month");return (Criteria)this;}public Criteria andMonthNotIn(List<String> values){addCriterion("month not in",values,"month");return (Criteria)this;}public Criteria andMonthBetween(String value1,String value2){addCriterion("month between",value1,value2,"month");return (Criteria)this;}public Criteria andMonthNotBetween(String value1,String value2){addCriterion("month not between",value1,value2,"month");return (Criteria)this;}public Criteria andSlyBaseSalaryIdIsNull(){addCriterion("SLY__BASE_SALARY_ID is null");return (Criteria)this;}public Criteria andSlyBaseSalaryIdIsNotNull(){addCriterion("SLY__BASE_SALARY_ID is not null");return (Criteria)this;}public Criteria andSlyBaseSalaryIdEqualTo(Long value){addCriterion("SLY__BASE_SALARY_ID =",value,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdNotEqualTo(Long value){addCriterion("SLY__BASE_SALARY_ID <>",value,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdGreaterThan(Long value){addCriterion("SLY__BASE_SALARY_ID >",value,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdGreaterThanOrEqualTo(Long value){addCriterion("SLY__BASE_SALARY_ID >=",value,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdLessThan(Long value){addCriterion("SLY__BASE_SALARY_ID <",value,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdLessThanOrEqualTo(Long value){addCriterion("SLY__BASE_SALARY_ID <=",value,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdIn(List<Long> values){addCriterion("SLY__BASE_SALARY_ID in",values,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdNotIn(List<Long> values){addCriterion("SLY__BASE_SALARY_ID not in",values,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdBetween(Long value1,Long value2){addCriterion("SLY__BASE_SALARY_ID between",value1,value2,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyBaseSalaryIdNotBetween(Long value1,Long value2){addCriterion("SLY__BASE_SALARY_ID not between",value1,value2,"slyBaseSalaryId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdIsNull(){addCriterion("SLY_GUOJIN_COMMISSION_ID is null");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdIsNotNull(){addCriterion("SLY_GUOJIN_COMMISSION_ID is not null");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdEqualTo(Long value){addCriterion("SLY_GUOJIN_COMMISSION_ID =",value,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdNotEqualTo(Long value){addCriterion("SLY_GUOJIN_COMMISSION_ID <>",value,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdGreaterThan(Long value){addCriterion("SLY_GUOJIN_COMMISSION_ID >",value,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdGreaterThanOrEqualTo(Long value){addCriterion("SLY_GUOJIN_COMMISSION_ID >=",value,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdLessThan(Long value){addCriterion("SLY_GUOJIN_COMMISSION_ID <",value,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdLessThanOrEqualTo(Long value){addCriterion("SLY_GUOJIN_COMMISSION_ID <=",value,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdIn(List<Long> values){addCriterion("SLY_GUOJIN_COMMISSION_ID in",values,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdNotIn(List<Long> values){addCriterion("SLY_GUOJIN_COMMISSION_ID not in",values,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdBetween(Long value1,Long value2){addCriterion("SLY_GUOJIN_COMMISSION_ID between",value1,value2,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyGuojinCommissionIdNotBetween(Long value1,Long value2){addCriterion("SLY_GUOJIN_COMMISSION_ID not between",value1,value2,"slyGuojinCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdIsNull(){addCriterion("sly_overseas_commission_id is null");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdIsNotNull(){addCriterion("sly_overseas_commission_id is not null");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdEqualTo(Long value){addCriterion("sly_overseas_commission_id =",value,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdNotEqualTo(Long value){addCriterion("sly_overseas_commission_id <>",value,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdGreaterThan(Long value){addCriterion("sly_overseas_commission_id >",value,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdGreaterThanOrEqualTo(Long value){addCriterion("sly_overseas_commission_id >=",value,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdLessThan(Long value){addCriterion("sly_overseas_commission_id <",value,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdLessThanOrEqualTo(Long value){addCriterion("sly_overseas_commission_id <=",value,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdIn(List<Long> values){addCriterion("sly_overseas_commission_id in",values,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdNotIn(List<Long> values){addCriterion("sly_overseas_commission_id not in",values,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdBetween(Long value1,Long value2){addCriterion("sly_overseas_commission_id between",value1,value2,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyOverseasCommissionIdNotBetween(Long value1,Long value2){addCriterion("sly_overseas_commission_id not between",value1,value2,"slyOverseasCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdIsNull(){addCriterion("SLY_PROJECT_COMMISSION_ID is null");return (Criteria)this;}public Criteria andSlyProjectCommissionIdIsNotNull(){addCriterion("SLY_PROJECT_COMMISSION_ID is not null");return (Criteria)this;}public Criteria andSlyProjectCommissionIdEqualTo(Long value){addCriterion("SLY_PROJECT_COMMISSION_ID =",value,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdNotEqualTo(Long value){addCriterion("SLY_PROJECT_COMMISSION_ID <>",value,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdGreaterThan(Long value){addCriterion("SLY_PROJECT_COMMISSION_ID >",value,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdGreaterThanOrEqualTo(Long value){addCriterion("SLY_PROJECT_COMMISSION_ID >=",value,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdLessThan(Long value){addCriterion("SLY_PROJECT_COMMISSION_ID <",value,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdLessThanOrEqualTo(Long value){addCriterion("SLY_PROJECT_COMMISSION_ID <=",value,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdIn(List<Long> values){addCriterion("SLY_PROJECT_COMMISSION_ID in",values,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdNotIn(List<Long> values){addCriterion("SLY_PROJECT_COMMISSION_ID not in",values,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdBetween(Long value1,Long value2){addCriterion("SLY_PROJECT_COMMISSION_ID between",value1,value2,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyProjectCommissionIdNotBetween(Long value1,Long value2){addCriterion("SLY_PROJECT_COMMISSION_ID not between",value1,value2,"slyProjectCommissionId");return (Criteria)this;}public Criteria andSlyReissueIdIsNull(){addCriterion("sly_reissue_id is null");return (Criteria)this;}public Criteria andSlyReissueIdIsNotNull(){addCriterion("sly_reissue_id is not null");return (Criteria)this;}public Criteria andSlyReissueIdEqualTo(Long value){addCriterion("sly_reissue_id =",value,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdNotEqualTo(Long value){addCriterion("sly_reissue_id <>",value,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdGreaterThan(Long value){addCriterion("sly_reissue_id >",value,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdGreaterThanOrEqualTo(Long value){addCriterion("sly_reissue_id >=",value,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdLessThan(Long value){addCriterion("sly_reissue_id <",value,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdLessThanOrEqualTo(Long value){addCriterion("sly_reissue_id <=",value,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdIn(List<Long> values){addCriterion("sly_reissue_id in",values,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdNotIn(List<Long> values){addCriterion("sly_reissue_id not in",values,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdBetween(Long value1,Long value2){addCriterion("sly_reissue_id between",value1,value2,"slyReissueId");return (Criteria)this;}public Criteria andSlyReissueIdNotBetween(Long value1,Long value2){addCriterion("sly_reissue_id not between",value1,value2,"slyReissueId");return (Criteria)this;}public Criteria andSlySaleCommissionIdIsNull(){addCriterion("SLY_SALE_COMMISSION_ID is null");return (Criteria)this;}public Criteria andSlySaleCommissionIdIsNotNull(){addCriterion("SLY_SALE_COMMISSION_ID is not null");return (Criteria)this;}public Criteria andSlySaleCommissionIdEqualTo(Long value){addCriterion("SLY_SALE_COMMISSION_ID =",value,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdNotEqualTo(Long value){addCriterion("SLY_SALE_COMMISSION_ID <>",value,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdGreaterThan(Long value){addCriterion("SLY_SALE_COMMISSION_ID >",value,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdGreaterThanOrEqualTo(Long value){addCriterion("SLY_SALE_COMMISSION_ID >=",value,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdLessThan(Long value){addCriterion("SLY_SALE_COMMISSION_ID <",value,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdLessThanOrEqualTo(Long value){addCriterion("SLY_SALE_COMMISSION_ID <=",value,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdIn(List<Long> values){addCriterion("SLY_SALE_COMMISSION_ID in",values,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdNotIn(List<Long> values){addCriterion("SLY_SALE_COMMISSION_ID not in",values,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdBetween(Long value1,Long value2){addCriterion("SLY_SALE_COMMISSION_ID between",value1,value2,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlySaleCommissionIdNotBetween(Long value1,Long value2){addCriterion("SLY_SALE_COMMISSION_ID not between",value1,value2,"slySaleCommissionId");return (Criteria)this;}public Criteria andSlyWithholdIdIsNull(){addCriterion("sly_withhold_id is null");return (Criteria)this;}public Criteria andSlyWithholdIdIsNotNull(){addCriterion("sly_withhold_id is not null");return (Criteria)this;}public Criteria andSlyWithholdIdEqualTo(Long value){addCriterion("sly_withhold_id =",value,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdNotEqualTo(Long value){addCriterion("sly_withhold_id <>",value,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdGreaterThan(Long value){addCriterion("sly_withhold_id >",value,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdGreaterThanOrEqualTo(Long value){addCriterion("sly_withhold_id >=",value,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdLessThan(Long value){addCriterion("sly_withhold_id <",value,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdLessThanOrEqualTo(Long value){addCriterion("sly_withhold_id <=",value,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdIn(List<Long> values){addCriterion("sly_withhold_id in",values,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdNotIn(List<Long> values){addCriterion("sly_withhold_id not in",values,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdBetween(Long value1,Long value2){addCriterion("sly_withhold_id between",value1,value2,"slyWithholdId");return (Criteria)this;}public Criteria andSlyWithholdIdNotBetween(Long value1,Long value2){addCriterion("sly_withhold_id not between",value1,value2,"slyWithholdId");return (Criteria)this;}public Criteria andRcStateIsNull(){addCriterion("RC_STATE is null");return (Criteria)this;}public Criteria andRcStateIsNotNull(){addCriterion("RC_STATE is not null");return (Criteria)this;}public Criteria andRcStateEqualTo(String value){addCriterion("RC_STATE =",value,"rcState");return (Criteria)this;}public Criteria andRcStateNotEqualTo(String value){addCriterion("RC_STATE <>",value,"rcState");return (Criteria)this;}public Criteria andRcStateGreaterThan(String value){addCriterion("RC_STATE >",value,"rcState");return (Criteria)this;}public Criteria andRcStateGreaterThanOrEqualTo(String value){addCriterion("RC_STATE >=",value,"rcState");return (Criteria)this;}public Criteria andRcStateLessThan(String value){addCriterion("RC_STATE <",value,"rcState");return (Criteria)this;}public Criteria andRcStateLessThanOrEqualTo(String value){addCriterion("RC_STATE <=",value,"rcState");return (Criteria)this;}public Criteria andRcStateLike(String value){addCriterion("RC_STATE like",value,"rcState");return (Criteria)this;}public Criteria andRcStateNotLike(String value){addCriterion("RC_STATE not like",value,"rcState");return (Criteria)this;}public Criteria andRcStateIn(List<String> values){addCriterion("RC_STATE in",values,"rcState");return (Criteria)this;}public Criteria andRcStateNotIn(List<String> values){addCriterion("RC_STATE not in",values,"rcState");return (Criteria)this;}public Criteria andRcStateBetween(String value1,String value2){addCriterion("RC_STATE between",value1,value2,"rcState");return (Criteria)this;}public Criteria andRcStateNotBetween(String value1,String value2){addCriterion("RC_STATE not between",value1,value2,"rcState");return (Criteria)this;}public Criteria andCreateDateIsNull(){addCriterion("CREATE_DATE is null");return (Criteria)this;}public Criteria andCreateDateIsNotNull(){addCriterion("CREATE_DATE is not null");return (Criteria)this;}public Criteria andCreateDateEqualTo(Date value){addCriterion("CREATE_DATE =",value,"createDate");return (Criteria)this;}public Criteria andCreateDateNotEqualTo(Date value){addCriterion("CREATE_DATE <>",value,"createDate");return (Criteria)this;}public Criteria andCreateDateGreaterThan(Date value){addCriterion("CREATE_DATE >",value,"createDate");return (Criteria)this;}public Criteria andCreateDateGreaterThanOrEqualTo(Date value){addCriterion("CREATE_DATE >=",value,"createDate");return (Criteria)this;}public Criteria andCreateDateLessThan(Date value){addCriterion("CREATE_DATE <",value,"createDate");return (Criteria)this;}public Criteria andCreateDateLessThanOrEqualTo(Date value){addCriterion("CREATE_DATE <=",value,"createDate");return (Criteria)this;}public Criteria andCreateDateIn(List<Date> values){addCriterion("CREATE_DATE in",values,"createDate");return (Criteria)this;}public Criteria andCreateDateNotIn(List<Date> values){addCriterion("CREATE_DATE not in",values,"createDate");return (Criteria)this;}public Criteria andCreateDateBetween(Date value1,Date value2){addCriterion("CREATE_DATE between",value1,value2,"createDate");return (Criteria)this;}public Criteria andCreateDateNotBetween(Date value1,Date value2){addCriterion("CREATE_DATE not between",value1,value2,"createDate");return (Criteria)this;}public Criteria andModifyDateIsNull(){addCriterion("MODIFY_DATE is null");return (Criteria)this;}public Criteria andModifyDateIsNotNull(){addCriterion("MODIFY_DATE is not null");return (Criteria)this;}public Criteria andModifyDateEqualTo(Date value){addCriterion("MODIFY_DATE =",value,"modifyDate");return (Criteria)this;}public Criteria andModifyDateNotEqualTo(Date value){addCriterion("MODIFY_DATE <>",value,"modifyDate");return (Criteria)this;}public Criteria andModifyDateGreaterThan(Date value){addCriterion("MODIFY_DATE >",value,"modifyDate");return (Criteria)this;}public Criteria andModifyDateGreaterThanOrEqualTo(Date value){addCriterion("MODIFY_DATE >=",value,"modifyDate");return (Criteria)this;}public Criteria andModifyDateLessThan(Date value){addCriterion("MODIFY_DATE <",value,"modifyDate");return (Criteria)this;}public Criteria andModifyDateLessThanOrEqualTo(Date value){addCriterion("MODIFY_DATE <=",value,"modifyDate");return (Criteria)this;}public Criteria andModifyDateIn(List<Date> values){addCriterion("MODIFY_DATE in",values,"modifyDate");return (Criteria)this;}public Criteria andModifyDateNotIn(List<Date> values){addCriterion("MODIFY_DATE not in",values,"modifyDate");return (Criteria)this;}public Criteria andModifyDateBetween(Date value1,Date value2){addCriterion("MODIFY_DATE between",value1,value2,"modifyDate");return (Criteria)this;}public Criteria andModifyDateNotBetween(Date value1,Date value2){addCriterion("MODIFY_DATE not between",value1,value2,"modifyDate");return (Criteria)this;}public Criteria andOperComIdIsNull(){addCriterion("OPER_COM_ID is null");return (Criteria)this;}public Criteria andOperComIdIsNotNull(){addCriterion("OPER_COM_ID is not null");return (Criteria)this;}public Criteria andOperComIdEqualTo(Long value){addCriterion("OPER_COM_ID =",value,"operComId");return (Criteria)this;}public Criteria andOperComIdNotEqualTo(Long value){addCriterion("OPER_COM_ID <>",value,"operComId");return (Criteria)this;}public Criteria andOperComIdGreaterThan(Long value){addCriterion("OPER_COM_ID >",value,"operComId");return (Criteria)this;}public Criteria andOperComIdGreaterThanOrEqualTo(Long value){addCriterion("OPER_COM_ID >=",value,"operComId");return (Criteria)this;}public Criteria andOperComIdLessThan(Long value){addCriterion("OPER_COM_ID <",value,"operComId");return (Criteria)this;}public Criteria andOperComIdLessThanOrEqualTo(Long value){addCriterion("OPER_COM_ID <=",value,"operComId");return (Criteria)this;}public Criteria andOperComIdIn(List<Long> values){addCriterion("OPER_COM_ID in",values,"operComId");return (Criteria)this;}public Criteria andOperComIdNotIn(List<Long> values){addCriterion("OPER_COM_ID not in",values,"operComId");return (Criteria)this;}public Criteria andOperComIdBetween(Long value1,Long value2){addCriterion("OPER_COM_ID between",value1,value2,"operComId");return (Criteria)this;}public Criteria andOperComIdNotBetween(Long value1,Long value2){addCriterion("OPER_COM_ID not between",value1,value2,"operComId");return (Criteria)this;}public Criteria andCreateUserIdIsNull(){addCriterion("CREATE_USER_ID is null");return (Criteria)this;}public Criteria andCreateUserIdIsNotNull(){addCriterion("CREATE_USER_ID is not null");return (Criteria)this;}public Criteria andCreateUserIdEqualTo(Long value){addCriterion("CREATE_USER_ID =",value,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdNotEqualTo(Long value){addCriterion("CREATE_USER_ID <>",value,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdGreaterThan(Long value){addCriterion("CREATE_USER_ID >",value,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdGreaterThanOrEqualTo(Long value){addCriterion("CREATE_USER_ID >=",value,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdLessThan(Long value){addCriterion("CREATE_USER_ID <",value,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdLessThanOrEqualTo(Long value){addCriterion("CREATE_USER_ID <=",value,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdIn(List<Long> values){addCriterion("CREATE_USER_ID in",values,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdNotIn(List<Long> values){addCriterion("CREATE_USER_ID not in",values,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdBetween(Long value1,Long value2){addCriterion("CREATE_USER_ID between",value1,value2,"createUserId");return (Criteria)this;}public Criteria andCreateUserIdNotBetween(Long value1,Long value2){addCriterion("CREATE_USER_ID not between",value1,value2,"createUserId");return (Criteria)this;}public Criteria andModifyUserIdIsNull(){addCriterion("MODIFY_USER_ID is null");return (Criteria)this;}public Criteria andModifyUserIdIsNotNull(){addCriterion("MODIFY_USER_ID is not null");return (Criteria)this;}public Criteria andModifyUserIdEqualTo(Long value){addCriterion("MODIFY_USER_ID =",value,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdNotEqualTo(Long value){addCriterion("MODIFY_USER_ID <>",value,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdGreaterThan(Long value){addCriterion("MODIFY_USER_ID >",value,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdGreaterThanOrEqualTo(Long value){addCriterion("MODIFY_USER_ID >=",value,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdLessThan(Long value){addCriterion("MODIFY_USER_ID <",value,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdLessThanOrEqualTo(Long value){addCriterion("MODIFY_USER_ID <=",value,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdIn(List<Long> values){addCriterion("MODIFY_USER_ID in",values,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdNotIn(List<Long> values){addCriterion("MODIFY_USER_ID not in",values,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdBetween(Long value1,Long value2){addCriterion("MODIFY_USER_ID between",value1,value2,"modifyUserId");return (Criteria)this;}public Criteria andModifyUserIdNotBetween(Long value1,Long value2){addCriterion("MODIFY_USER_ID not between",value1,value2,"modifyUserId");return (Criteria)this;}}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_sly_salary
	 * @mbggenerated  Wed Sep 28 10:34:53 CST 2016
	 */public static class Criterion {private String condition;private Object value;private Object secondValue;private boolean noValue;private boolean singleValue;private boolean betweenValue;private boolean listValue;private String typeHandler;public String getCondition(){return condition;}public Object getValue(){return value;}public Object getSecondValue(){return secondValue;}public boolean isNoValue(){return noValue;}public boolean isSingleValue(){return singleValue;}public boolean isBetweenValue(){return betweenValue;}public boolean isListValue(){return listValue;}public String getTypeHandler(){return typeHandler;}protected Criterion(String condition){super();this.condition=condition;this.typeHandler=null;this.noValue=true;}protected Criterion(String condition,Object value,String typeHandler){super();this.condition=condition;this.value=value;this.typeHandler=typeHandler;if (value instanceof List<?>){this.listValue=true;} else {this.singleValue=true;}}protected Criterion(String condition,Object value){this(condition,value,null);}protected Criterion(String condition,Object value,Object secondValue,String typeHandler){super();this.condition=condition;this.value=value;this.secondValue=secondValue;this.typeHandler=typeHandler;this.betweenValue=true;}protected Criterion(String condition,Object value,Object secondValue){this(condition,value,secondValue,null);}}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_sly_salary
     *
     * @mbggenerated do_not_delete_during_merge Mon Sep 26 15:18:22 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}