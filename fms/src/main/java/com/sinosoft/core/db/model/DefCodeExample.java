package com.sinosoft.core.db.model;

import java.util.ArrayList;
import java.util.List;

public class DefCodeExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public DefCodeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
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

		public Criteria andCodeIsNull() {
			addCriterion("CODE is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("CODE is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(String value) {
			addCriterion("CODE =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(String value) {
			addCriterion("CODE <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(String value) {
			addCriterion("CODE >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(String value) {
			addCriterion("CODE >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(String value) {
			addCriterion("CODE <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(String value) {
			addCriterion("CODE <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLike(String value) {
			addCriterion("CODE like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotLike(String value) {
			addCriterion("CODE not like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<String> values) {
			addCriterion("CODE in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<String> values) {
			addCriterion("CODE not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(String value1, String value2) {
			addCriterion("CODE between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(String value1, String value2) {
			addCriterion("CODE not between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeTypeIsNull() {
			addCriterion("CODE_TYPE is null");
			return (Criteria) this;
		}

		public Criteria andCodeTypeIsNotNull() {
			addCriterion("CODE_TYPE is not null");
			return (Criteria) this;
		}

		public Criteria andCodeTypeEqualTo(String value) {
			addCriterion("CODE_TYPE =", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeNotEqualTo(String value) {
			addCriterion("CODE_TYPE <>", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeGreaterThan(String value) {
			addCriterion("CODE_TYPE >", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
			addCriterion("CODE_TYPE >=", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeLessThan(String value) {
			addCriterion("CODE_TYPE <", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeLessThanOrEqualTo(String value) {
			addCriterion("CODE_TYPE <=", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeLike(String value) {
			addCriterion("CODE_TYPE like", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeNotLike(String value) {
			addCriterion("CODE_TYPE not like", value, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeIn(List<String> values) {
			addCriterion("CODE_TYPE in", values, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeNotIn(List<String> values) {
			addCriterion("CODE_TYPE not in", values, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeBetween(String value1, String value2) {
			addCriterion("CODE_TYPE between", value1, value2, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeTypeNotBetween(String value1, String value2) {
			addCriterion("CODE_TYPE not between", value1, value2, "codeType");
			return (Criteria) this;
		}

		public Criteria andCodeNameIsNull() {
			addCriterion("CODE_NAME is null");
			return (Criteria) this;
		}

		public Criteria andCodeNameIsNotNull() {
			addCriterion("CODE_NAME is not null");
			return (Criteria) this;
		}

		public Criteria andCodeNameEqualTo(String value) {
			addCriterion("CODE_NAME =", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameNotEqualTo(String value) {
			addCriterion("CODE_NAME <>", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameGreaterThan(String value) {
			addCriterion("CODE_NAME >", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameGreaterThanOrEqualTo(String value) {
			addCriterion("CODE_NAME >=", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameLessThan(String value) {
			addCriterion("CODE_NAME <", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameLessThanOrEqualTo(String value) {
			addCriterion("CODE_NAME <=", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameLike(String value) {
			addCriterion("CODE_NAME like", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameNotLike(String value) {
			addCriterion("CODE_NAME not like", value, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameIn(List<String> values) {
			addCriterion("CODE_NAME in", values, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameNotIn(List<String> values) {
			addCriterion("CODE_NAME not in", values, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameBetween(String value1, String value2) {
			addCriterion("CODE_NAME between", value1, value2, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeNameNotBetween(String value1, String value2) {
			addCriterion("CODE_NAME not between", value1, value2, "codeName");
			return (Criteria) this;
		}

		public Criteria andCodeAliasIsNull() {
			addCriterion("CODE_ALIAS is null");
			return (Criteria) this;
		}

		public Criteria andCodeAliasIsNotNull() {
			addCriterion("CODE_ALIAS is not null");
			return (Criteria) this;
		}

		public Criteria andCodeAliasEqualTo(String value) {
			addCriterion("CODE_ALIAS =", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasNotEqualTo(String value) {
			addCriterion("CODE_ALIAS <>", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasGreaterThan(String value) {
			addCriterion("CODE_ALIAS >", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasGreaterThanOrEqualTo(String value) {
			addCriterion("CODE_ALIAS >=", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasLessThan(String value) {
			addCriterion("CODE_ALIAS <", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasLessThanOrEqualTo(String value) {
			addCriterion("CODE_ALIAS <=", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasLike(String value) {
			addCriterion("CODE_ALIAS like", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasNotLike(String value) {
			addCriterion("CODE_ALIAS not like", value, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasIn(List<String> values) {
			addCriterion("CODE_ALIAS in", values, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasNotIn(List<String> values) {
			addCriterion("CODE_ALIAS not in", values, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasBetween(String value1, String value2) {
			addCriterion("CODE_ALIAS between", value1, value2, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andCodeAliasNotBetween(String value1, String value2) {
			addCriterion("CODE_ALIAS not between", value1, value2, "codeAlias");
			return (Criteria) this;
		}

		public Criteria andOtherSignIsNull() {
			addCriterion("OTHER_SIGN is null");
			return (Criteria) this;
		}

		public Criteria andOtherSignIsNotNull() {
			addCriterion("OTHER_SIGN is not null");
			return (Criteria) this;
		}

		public Criteria andOtherSignEqualTo(String value) {
			addCriterion("OTHER_SIGN =", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignNotEqualTo(String value) {
			addCriterion("OTHER_SIGN <>", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignGreaterThan(String value) {
			addCriterion("OTHER_SIGN >", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignGreaterThanOrEqualTo(String value) {
			addCriterion("OTHER_SIGN >=", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignLessThan(String value) {
			addCriterion("OTHER_SIGN <", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignLessThanOrEqualTo(String value) {
			addCriterion("OTHER_SIGN <=", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignLike(String value) {
			addCriterion("OTHER_SIGN like", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignNotLike(String value) {
			addCriterion("OTHER_SIGN not like", value, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignIn(List<String> values) {
			addCriterion("OTHER_SIGN in", values, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignNotIn(List<String> values) {
			addCriterion("OTHER_SIGN not in", values, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignBetween(String value1, String value2) {
			addCriterion("OTHER_SIGN between", value1, value2, "otherSign");
			return (Criteria) this;
		}

		public Criteria andOtherSignNotBetween(String value1, String value2) {
			addCriterion("OTHER_SIGN not between", value1, value2, "otherSign");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_def_code
	 * @mbggenerated  Mon Nov 16 16:28:43 CST 2015
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
     * This class corresponds to the database table t_def_code
     *
     * @mbggenerated do_not_delete_during_merge Thu Nov 12 11:46:16 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}