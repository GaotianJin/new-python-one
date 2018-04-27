package com.fms.service.balance;

import java.util.Map;

import com.sinosoft.util.ResultInfo;

public interface FeeCalculationService {

	/**保险产品计算手续费
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo calculateCounterFee(Map calParamsMap);
	
	/**财富固定收益类产品计算咨询服务费（销售费用）
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo calculateConsultationServiceFee(Map calParamsMap);
	
	/**财富非固定收益类计算固定管理费
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo calculateFixedManagementFee(Map calParamsMap);
	
	/**财富非固定收益类计算浮动管理费
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo calculateFloatManagementFee(Map calParamsMap);
	
	/**财富非固定收益类计算认购费
	 * @param calParamsMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo calculateSubscriptionFee(Map calParamsMap);
}
