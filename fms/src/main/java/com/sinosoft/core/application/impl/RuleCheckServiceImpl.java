package com.sinosoft.core.application.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.core.application.RuleCheckService;
import com.sinosoft.core.application.RuleService;
import com.sinosoft.core.application.util.CalBase;
import com.sinosoft.core.domain.model.rule.TcPdRuleverify;
import com.sinosoft.core.domain.model.rule.TfNbUwError;
import com.sinosoft.core.domain.model.rule.TfNbUwErrorTrace;
import com.sinosoft.core.domain.model.rule.dao.TcPdRuleverifyDAO;
import com.sinosoft.core.domain.model.rule.dao.TfNbUwErrorDAO;
import com.sinosoft.core.domain.model.rule.dao.TfNbUwErrorTraceDAO;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.LoginInfo;

/**
 * 核保规则校验类
 * 
 * @author likai
 * 
 */
@Service
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RuleCheckServiceImpl implements RuleCheckService {

	private static final Logger log = Logger
			.getLogger(RuleCheckServiceImpl.class);
	@Autowired
	private TcPdRuleverifyDAO tcPdRuleverifyDAO;
	@Autowired
	private RuleService ruleService;
	
	//登陆信息
	private LoginInfo loginInfo;

	@Autowired
	private TfNbUwErrorDAO tfNbUwErrorDAO;

	@Autowired
	private TfNbUwErrorTraceDAO tfNbUwErrortraceDAO;

	private CalBase calBase;

	private String verifyType;

	private int riskId;

	// 核保层级 1：保单层级
	// 2：险种层级
	private String uwLayer;

	// 核保关联号码
	private int uWRelationID;

	// 核保批次号，每核保一次+1
	private int uWBatchNo = 0;
	
	@Transactional
	public String calculate(CalBase calBase, String verifyType, int riskId,LoginInfo loginInfo) {
		this.calBase = calBase;
		this.verifyType = verifyType;
		this.riskId = riskId;
		this.loginInfo = loginInfo;
		if (riskId == 0) {
			uWRelationID = Integer.parseInt(calBase.getnBPolicyID());
			uwLayer = "1";
		} else {
			uWRelationID = Integer.parseInt(calBase.getnBPolicyPlanID());
			uwLayer = "2";
		}
		// 先把现有的核保不通过记录转存至trace表
		transferUWError();
		return getVerifyResult();
	}

	@Transactional
	private String getVerifyResult() {
		// 是否通过
		boolean isPass = true;
		StringBuffer tReturn = new StringBuffer();

		// 查询险种对应的自核规则
		List<TcPdRuleverify> ruleList = tcPdRuleverifyDAO.findByRiskAndType(riskId,verifyType);
		if (ruleList.size() > 0) {
			for (int i = 0; i < ruleList.size(); i++) {
				try {
					TcPdRuleverify tcPdRuleverify = ruleList.get(i);
					String result = ruleService.calculate(tcPdRuleverify.getRuleId(), calBase);
					if (result == null || result.equals("")) {
						log.error("自核计算出错");
						return "";
					}
					int flag = Integer.parseInt(result);
					// 自核不通过
					if (flag == 1) {
						isPass = false;
						// 将自核不通过的结果存储下来
						//提示语需要解析
						String uwInfo =  ruleService.interpretFactorInSQL(tcPdRuleverify.getRemark(), calBase);
						saveUWResult(tcPdRuleverify,uwInfo);
						tReturn.append( uwInfo+ "|");
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("自核计算出错");
					return "";
				}
			}

		} else {
			log.error("自核计算SQL不存在");
			return "";
		}
		// 自核不通过
		if (!isPass) {
			return tReturn.toString();
		}
		// 自核通过
		else {
			return "0";
		}
	}

	/**
	 * 把原核保不通过信息转移到trace表
	 */
	@Transactional
	public void transferUWError() {

		List<TfNbUwError> tfNbUwErrorList = tfNbUwErrorDAO.getTfNbUwErrorList(uWRelationID,uwLayer,verifyType); 
		if (tfNbUwErrorList.size() > 0) {
			for (int i = 0; i < tfNbUwErrorList.size(); i++) {
				TfNbUwErrorTrace tfNbUwErrortrace = new TfNbUwErrorTrace();
				TfNbUwError tfNbUwError = tfNbUwErrorList.get(i);
				int uWBatchNo = tfNbUwError.getUwBatchNo();
				if (uWBatchNo > this.uWBatchNo) {
					this.uWBatchNo = uWBatchNo;
				}
				BeanUtils.copyProperties(tfNbUwError, tfNbUwErrortrace);
				tfNbUwErrortrace.setUwMasterId(tfNbUwError.getId());

				tfNbUwErrortraceDAO.save(tfNbUwErrortrace);
				tfNbUwErrorDAO.delete(tfNbUwError);
			}
		}
	}

	/**
	 * 保存核保错误信息表
	 * 
	 * @param tcPdRuleverify
	 * @param uwInfo 
	 */
	@Transactional
	public void saveUWResult(TcPdRuleverify tcPdRuleverify, String uwInfo) {
		TfNbUwError tfNbUwError = new TfNbUwError();
		tfNbUwError.setUwLayer(uwLayer);
		tfNbUwError.setUwRelationId(uWRelationID);
		tfNbUwError.setUwBatchNo(uWBatchNo + 1);
		tfNbUwError.setUwRuleId(tcPdRuleverify.getRuleId() + "");
		tfNbUwError.setUwInfo(uwInfo);
		tfNbUwError.setUwInfoLevelFlag(tcPdRuleverify.getPassFlag());
		tfNbUwError.setUwType(tcPdRuleverify.getVerifyType());
//		tfNbUwError.setOperatorId(loginInfo.getUserId());
		tfNbUwError.setMakeTime(DateUtils.getCurrentTimestamp());
		tfNbUwError.setUwInfoLevelFlag(tcPdRuleverify.getPassFlag());
		tfNbUwError.setUwType(tcPdRuleverify.getVerifyType());
	//	tfNbUwError.setOperatorId(loginInfo.getUserId());
		tfNbUwError.setMakeTime(DateUtils.getCurrentTimestamp());
		tfNbUwError.setModifyTime(DateUtils.getCurrentTimestamp());

		tfNbUwErrorDAO.save(tfNbUwError);
	}

}