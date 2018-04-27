package com.sinosoft.core.application;

import com.sinosoft.core.application.util.CalBase;
import com.sinosoft.util.LoginInfo;

/**
 * 核保规则校验服务
 * @author likai
 * @version 1.0
 */
public interface RuleCheckService {
	
	/**
	 * 核保规则计算服务类
	 * @param calBase 计算要素
	 * @param verifyType 1 新单核保规则
	 * @param riskId 公共自核规则为0 险种自核规则为具体险种ID
	 * @param loginInfo 用户登录信息，可以从session中取得
	 * @return String
	 */
	public String calculate(CalBase calBase,String verifyType,int riskId,LoginInfo loginInfo);
}
