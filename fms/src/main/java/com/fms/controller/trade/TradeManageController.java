package com.fms.controller.trade;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.core.domain.model.user.User;
import com.sinosoft.core.interfaces.user.UserController;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.JsonUtils;

@Controller
@RequestMapping("/trade")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class TradeManageController {

	private static final Logger log = Logger.getLogger(UserController.class);
	
	/**
	 * 获取交易录入交易列表页面url
	 */
	@RequestMapping(value = "/tradeInputListUrl", method = RequestMethod.GET)
	public String tradeInputList() {
		return "/fms/trade/tradeInputListUrl";
	}
	
	/**
	 * 获取交易录入页面url
	 */
//	@RequestMapping(value = "/tradeInputInfo", method = RequestMethod.GET)
//	public String tradeInput(Model model) {
//		return "fms/trade/tradeInputInfo";
//	}
	
	@RequestMapping(value = "/tradeInputInfo", method = RequestMethod.GET)
	public ModelAndView tradeInput(@RequestParam("param") String param) {
		log.info("==请求数据合同号===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/trade/tradeInputInfo",reqParamMap);
	}
	/**
	 * 获取交易复核交易列表页面url
	 */
	@RequestMapping(value = "/tradeCheckListUrl", method = RequestMethod.GET)
	public String tardeCheckList(Model model) {
		return "fms/trade/tradeCheckListUrl";
	}

	
	
	/**
	 * 获取交易复核页面url
	 */
	@RequestMapping(value = "/tradeCheckInfo", method = RequestMethod.GET)
	public String tradeCheck() {
		return "/fms/trade/tradeCheckInfo";
	}
	
	/**
	 * 获取交易审核交易列表页面url
	 */
	@RequestMapping(value = "/tradeAuditListUrl", method = RequestMethod.GET)
	public String tardeAuditList(Model model) {
		return "fms/trade/tradeAuditListUrl";
	}

	
	
	/**
	 * 获取交易审核页面url
	 */
	@RequestMapping(value = "/tradeAuditInfo", method = RequestMethod.GET)
	public String tardeAudit() {
		return "/fms/trade/tradeAuditInfo";
	}
	
	

}
