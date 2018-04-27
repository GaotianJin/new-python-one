package com.fms.service.trade;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fms.db.model.TradeStatusInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface TradeStatusService {
	@SuppressWarnings("rawtypes")
	public DataGrid queryStausList(DataGridModel dgm,Map paramMap,LoginInfo loginInfo);
	@SuppressWarnings("rawtypes")
	public ResultInfo getTradeDetailInfo(Map paramMap);
	public ResultInfo saveTradeStatus(TradeStatusInfo tradeStatusInfo,LoginInfo loginInfo);
	public ResultInfo saveTradeStatus(List<TradeStatusInfo> tradeStatusInfoList,LoginInfo loginInfo);
	/**
	 * 获取最后一次维护的交易状态信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultInfo getLastTradeStatusInfo(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public ResultInfo getRiskTradeInfo(Map paramMap,String operate);
	
	/**
	 * 浮动产品份额导入
	 * @return
	 */
	public ResultInfo importFloatCopiesFile(MultipartFile[] importFileNameList, LoginInfo loginInfo);
	/**
	 * 产品成立邮件批处理
	 * @return
	 */
	public ResultInfo productFoundEmailBatch();
}
