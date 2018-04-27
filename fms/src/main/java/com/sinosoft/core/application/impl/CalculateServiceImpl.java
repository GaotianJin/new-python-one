package com.sinosoft.core.application.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sinosoft.core.application.CalculateService;

/**
 * 保额保费计算服务
 * 
 * @author likai
 * 
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CalculateServiceImpl implements CalculateService {
	// private static final Logger log = Logger
	// .getLogger(CalculateServiceImpl.class);
	//
	// private TfApPolicy tfApPolicy;
	// private TfApPolicyPlan tfApPolicyPlan;
	// private TcPdPlan tcPdPlan;
	// private String type;
	// @Autowired
	// private TcPdPlanDAO tcPdPlanDAO;
	// @Autowired
	// private RuleService ruleService;
	//
	// @Override
	// public TfApPolicyPlan calculate(TfApPolicy tfApPolicy,TfApPolicyPlan
	// tfApPolicyPlan, String type) {
	//
	// this.tfApPolicy = tfApPolicy;
	// this.tfApPolicyPlan = tfApPolicyPlan;
	// this.type = type;
	//
	// // 保费算法
	// tcPdPlan = tcPdPlanDAO.load(this.tfApPolicyPlan.getPdPlanId());
	// if (tcPdPlan == null) {
	// log.error("没有找到该责任组合所对应的产品定义");
	// throw new CisCoreException("没有找到该责任组合所对应的产品定义");
	// }
	// // 保额算法
	// Integer saCalSqlId = tcPdPlan.getSaCalSqlId();
	// if (saCalSqlId == null) {
	// log.error("没有找到保额计算算法");
	// throw new CisCoreException("没有找到保额计算算法");
	// }
	// // 保费算法
	// Integer premCalSqlId = tcPdPlan.getPremCalSqlId();
	// if (premCalSqlId == null) {
	// log.error("没有找到保费计算算法");
	// throw new CisCoreException("没有找到保费计算算法");
	// }
	// initBaseNbPolicyPlan();
	// CalBase calBase = new CalBase();
	// initCalBase(calBase);
	// String sumAssured = ruleService.calculate(saCalSqlId, calBase);
	// String curHandPrem = ruleService.calculate(premCalSqlId, calBase);
	//
	// tfApPolicyPlan.setSumAssured(new BigDecimal(sumAssured));
	// tfApPolicyPlan.setCurrentModePremium(new BigDecimal(curHandPrem));
	// tfApPolicyPlan.setNextModePremium(getNextHandPrem());
	// return this.tfApPolicyPlan;
	// }
	//
	// /**
	// * 初始化险种责任组合的一些字段
	// */
	// private void initBaseNbPolicyPlan() {
	// Integer paidUpYear = tfApPolicyPlan.getPaidUpYear();
	// String paidUpYearFlag = tfApPolicyPlan.getPaidUpYearFlag();
	// if (paidUpYear == null) {
	// paidUpYear = tcPdPlan.getPaidUpYearMin();
	// tfApPolicyPlan.setPaidUpYear(paidUpYear);
	// }
	// if (paidUpYearFlag == null || "".equals(paidUpYearFlag)) {
	// paidUpYearFlag = tcPdPlan.getPaidUpYearFlag();
	// tfApPolicyPlan.setPaidUpYearFlag(paidUpYearFlag);
	// }
	//
	// Integer insuYear = tfApPolicyPlan.getInsuYear();
	// String insuYearFlag = tfApPolicyPlan.getInsuYearFlag();
	// if (insuYear == null) {
	// insuYear = tcPdPlan.getInsuYearMin();
	// tfApPolicyPlan.setInsuYear(insuYear);
	// }
	// if (insuYearFlag == null || "".equals(insuYearFlag)) {
	// insuYearFlag = tcPdPlan.getInsuYearFlag();
	// tfApPolicyPlan.setInsuYearFlag(insuYearFlag);
	// }
	// Integer payMode = tfApPolicyPlan.getPayMode();
	// if (payMode == null) {
	// payMode = tcPdPlan.getPayMode();
	// tfApPolicyPlan.setPayMode(payMode);
	// }
	//
	// Date payToDate = tfApPolicyPlan.getPayToDate();
	// log.info("初始PayToDate:" + payToDate);
	// // 新单和续保
	// if ("1".equals(type)) {
	// if ("0".equals(payMode)) {
	// payToDate = tfApPolicyPlan.getPaidUpDate();
	// }
	// if ("1".equals(payMode) || "3".equals(payMode)
	// || "6".equals(payMode) || "12".equals(payMode)) {
	// payToDate = DateUtils.calDate(
	// tfApPolicyPlan.getPlanEffectiveDate(), payMode, "M");
	// }
	// }
	// // 续期
	// if ("2".equals(type)) {
	// if (payToDate == null) {
	// log.info("续期计算保费，交至日期不能为空");
	// throw new CisCoreException("续期计算保费，交至日期不能为空");
	// }
	// if ("0".equals(payMode)) {
	// payToDate = tfApPolicyPlan.getPaidUpDate();
	// }
	// if ("1".equals(payMode) || "3".equals(payMode)
	// || "6".equals(payMode) || "12".equals(payMode)) {
	// payToDate = DateUtils.calDate(payToDate, payMode, "M");
	// }
	// }
	// // 保全
	// if ("3".equals(type)) {
	// if (payToDate == null) {
	// log.info("保全重算保费，交至日期不能为空");
	// throw new CisCoreException("保全重算保费，交至日期不能为空");
	// }
	// }
	// log.info("重置后的PayToDate:" + payToDate);
	// tfApPolicyPlan.setPayToDate(payToDate);
	//
	// }
	//
	// /**
	// * 计算下一期保费
	// *
	// * @return BigDecimal
	// */
	// private BigDecimal getNextHandPrem() {
	// BigDecimal bigDecimal;
	// // 缴费期满
	// if ("0".equals(tfApPolicyPlan.getPayMode())
	// || tfApPolicyPlan.getPaidUpDate().equals(
	// tfApPolicyPlan.getPayToDate())) {
	// bigDecimal = new BigDecimal(0);
	// } else {
	// // 保费算保额
	// if ("1".equals(tcPdPlan.getPremCalSaFlag())) {
	// bigDecimal = tfApPolicyPlan.getCurrentModePremium();
	// } else {
	// // 重新计算保费
	// CalBase calBase = new CalBase();
	// initCalBase(calBase);
	// String result = ruleService.calculate(
	// tcPdPlan.getPremCalSqlId(), calBase);
	// bigDecimal = new BigDecimal(result);
	// }
	// }
	// log.info("下一期保费：" + bigDecimal.doubleValue());
	// return bigDecimal;
	// }
	//
	// /**
	// * 初始化算法计算要素
	// */
	// private void initCalBase(CalBase calBase) {
	// }

}