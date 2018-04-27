package com.fms.service.trade;

import java.util.Map;

import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

public interface TradeBalanceService {
	
	@SuppressWarnings("rawtypes")
	public ResultInfo tradeBalance(Map paramMap);
	
	@SuppressWarnings({"rawtypes" })
	public DataGrid queryBalanceTradeInfoList(DataGridModel dgm, Map paramMap,LoginInfo loginInfo);
}
