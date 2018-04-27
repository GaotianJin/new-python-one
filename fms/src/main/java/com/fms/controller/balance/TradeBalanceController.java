package com.fms.controller.balance;

/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 用户控制器
 *****************************************************/
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fms.service.trade.TradeBalanceService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Controller
@RequestMapping("/balance")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class TradeBalanceController
{
	@Autowired
	private TradeBalanceService tradeBalanceService;

	private static final Logger log = Logger
			.getLogger(TradeBalanceController.class);

	/**
	 * 获取用户页面url
	 */
	@RequestMapping(value = "/listTradeBalanceUrl", method = RequestMethod.GET)
	public String list(Model model)
	{
		return "fms/balance/listTradeBalance";
	}
	
	/**
	 * 获取用户页面url
	 */
	@RequestMapping(value = "/listTradeBalanceImportUrl", method = RequestMethod.GET)
	public String listImport(Model model)
	{
		return "fms/balance/listTradeBalanceImport";
	}

	
	/**
	 * 查询用户列表
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryTradeBalanceList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryTradeBalanceList(DataGridModel dgm,String queryParam,ModelMap modelMap)
	{
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			Map paramMap = new HashMap();
			if(queryParam!=null&&!"".equals(queryParam)){
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			}
			dataGrid = tradeBalanceService.queryBalanceTradeInfoList(dgm, paramMap, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tradeBalance.xls", method = RequestMethod.GET)
	public ModelAndView tradeBalanceAndExport(HttpServletRequest request,
            HttpServletResponse response,@RequestParam String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			Map paramMap = new HashMap();
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				String balanceParam = JsonUtils.getJsonValueByKey("balanceParam", param);
				paramMap = (Map)JsonUtils.jsonStrToObject(balanceParam, paramMap);
			}
			//开始结算
			resultInfo = tradeBalanceService.tradeBalance(paramMap);
			//结算完成，生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("交易结算导出出现异常！");
		}
		return null;
	}
}
