package com.fms.controller.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fms.db.mapper.PDProductMapper;
import com.fms.db.model.DefPrintInfo;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDProductExample;
import com.fms.db.model.TradeCustRoleInfo;
import com.fms.db.model.TradeInfo;
import com.fms.service.mapper.ProductServiceMapper;
import com.fms.service.trade.TradeService;
import com.google.gson.Gson;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;


@Controller
@RequestMapping("/trade")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class TradeController {
	
	private static final Logger log = Logger.getLogger(TradeController.class);
	
	@Autowired
	private TradeService tradeService;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private PDProductMapper pdProductMapper;
	
	/**
	 * 交易状体维护 
	 */
	@RequestMapping(value = "/listTradeStatusUrl", method = RequestMethod.GET)
	public String listTrade(Model model) {
		return "fms/trade/listTradeStatus";
	}
	
	
	/**
	 * 认购申请书打印
	 */
	@RequestMapping(value = "/printDown", method = RequestMethod.GET)
	public String listApplyOfPrint(Model model) {
		return "fms/trade/printDown";
	}
	/**
	 * 认购确认书打印
	 */
	@RequestMapping(value = "/printConfirmDown", method = RequestMethod.GET)
	public String listApplyOfConfirmPrint(Model model) {
		return "fms/trade/confirmPrintDown";
	}
	/**
	 * 获取增加顾问页面url
	 */
	@RequestMapping(value = "/addTradeUrl", method = RequestMethod.GET)
	public String addTradeUrl(Model model) {
		return "fms/trade/addTrade";
	}
	/**
	 * 交易录入页面查询交易录入信息列表
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/tradeInputQueryTradeList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid tradeInputQueryTradeList(DataGridModel dgm,String queryParam,@RequestParam String tradeStaus,ModelMap model) {
		Gson gson = new Gson();
		log.info(gson.toJson(dgm));
		log.info("====tradeInputQueryTradeList param==="+queryParam);
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = tradeService.tradeInputQueryTradeList(dgm,paramMap,tradeStaus,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 交易录入新增页面 查询客户信息  
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/queryCustomInfoInputGrid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCustomInfoInputGrid(@RequestParam HashMap paramMap) {
		try {
			List<Map<String,String>> codeMapList = tradeService.queryCustomInfoInputGrid(paramMap);
			String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
			return codeMapJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 交易录入新增页面 根据客户id初始化datagrid  
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/queryCustomInfoInputGridById", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCustomInfoInputGridById(@RequestParam HashMap paramMap) {
		try {
			List<Map<String,String>> codeMapList = tradeService.queryCustomInfoInputGridById(paramMap);
			String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
			return codeMapJson;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 交易录入新增页面 查询客户信息
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/queryCustomerCombo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCustomerCombo(@RequestParam HashMap paramMap) {
		/*List<Map> listDeleted = new ArrayList<Map>();
		listDeleted = JSON.parseArray((String)paramMap.get("customInfoInputIdData"), Map.class); 
		for(int i=0;i<listDeleted.size();i++){
			listDeleted.get(i).get("custBaseInfoId");
			listDeleted.get(i).get("tradeCode");
			listDeleted.get(i).get("tradeType");
		}*/
		List<Map<String, String>> codeMapList = tradeService.queryCustomerCombo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeRiskProId",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeRiskProId(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeRiskProId(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeRiskProtObj",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeRiskProtObj(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeRiskProtObj(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeWealthProInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeWealthProInfo(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeWealthProInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeRiskProInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeRiskProInfo(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeRiskProInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeRoleInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeRoleInfo(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeRoleInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeComId",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeComId(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeComId(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	/*@RequestMapping(value = "/queryTradeStoreId",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeStoreId(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeStoreId(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}*/
	
	@RequestMapping(value = "/queryTradeAgentId",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeAgentId(@RequestParam HashMap paramMap,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		paramMap.put("userId", loginInfo.getUserId());
		List<Map<String,String>> codeMapList = tradeService.queryTradeAgentId(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryRiskProInfoObjList",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryRiskProInfoObjList(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryRiskProInfoObjList(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	
	@RequestMapping(value = "/queryMonryProInfoObjList",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryMonryProInfoObjList(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryMonryProInfoObjList(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	/**
	 * @param paramMap
	 * 查询转让交易产品录入要素信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryTransFerMoneyProInfoObjList",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTransFerMoneyProInfoObjList(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTransFerMoneyProInfoObjList(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	@RequestMapping(value = "/queryTradeConclusion",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeConclusion(@RequestParam HashMap paramMap) {
		HashMap codeMap = tradeService.queryTradeConclusion(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMap);
		return codeMapJson;
	}
	
	
	
	///////////////////////////////////////////////
	
	@RequestMapping(value = "/queryTradeConclusion2",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeConclusion2(@RequestParam HashMap paramMap) {
		HashMap codeMap = tradeService.queryTradeConclusion2(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMap);
		return codeMapJson;
	}
	/////
	
	@RequestMapping(value = "/queryTradeProductFactor",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeProductFactor(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.queryTradeProductFactor(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeBankInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeBankInfo(@RequestParam HashMap paramMap) {
		List<Map<String, String>> codeMapList = tradeService.queryTradeBankInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/searchTradeBankInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String searchTradeBankInfo(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.searchTradeBankInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	
	@RequestMapping(value = "/getAllTBankInfoByCustId",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAllTBankInfoByCustId(@RequestParam HashMap paramMap) {
		List<Map> codeMapList = tradeService.getAllTBankInfoByCustId(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryTradeAddressInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryTradeAddressInfo(@RequestParam HashMap paramMap) {
		List<Map<String, String>> codeMapList = tradeService.queryTradeAddressInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/searchTradeAddressInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String searchTradeAddressInfo(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.searchTradeAddressInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	@RequestMapping(value = "/getTradeAddressInfo",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getTradeAddressInfo(@RequestParam HashMap paramMap) {
		List<Map<String,String>> codeMapList = tradeService.getTradeAddressInfo(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	@RequestMapping(value = "/queryDefCode",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryDefCode(@RequestParam HashMap paramMap) {
		List<Map<String, String>> codeMapList = tradeService.queryDefCode(paramMap);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	
	
	@RequestMapping(value = "/checkUpFile",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkUpFile(@RequestParam HashMap paramMap) {
		
		String codeMapList = tradeService.checkUpFile(paramMap);
		//String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapList;
	}
	
	@RequestMapping(value = "/checkCustIDFile",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String checkCustIDFile(@RequestParam HashMap paramMap) {
		
		String codeMapList = tradeService.checkCustIDFile(paramMap);
		return codeMapList;
	}
	
	/**
	 * 交易录入新增页面 保存交易信息
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveTradeInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(param, TradeInfo.class);
			resultInfo = tradeService.saveTradeInfo(tradeInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 交易录入新增页面 保存角色信息
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeRole", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveTradeRole(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			
			resultInfo = tradeService.saveTradeRole(param,loginInfo); 
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 交易录入新增页面 保存保险产品信息
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeRiskPro", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveTradeRiskPro(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			resultInfo = tradeService.saveTradeRiskPro(param,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 交易录入新增页面 保存财富产品信息
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeWealthPro", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveTradeWealthPro(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
////			paramMap = JsonUtils.decodeUrlParams(paramMap, "utf-8");
//			paramMap.put("userId", loginInfo.getUserId());
//			paramMap.put("userCode", loginInfo.getUserCode());
//			paramMap.put("comId", loginInfo.getComId());
			resultInfo=	tradeService.saveTradeWealthPro(param,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/**
	 * 交易录入新增页面 提交复核
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/saveTradeInput", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeInput(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = tradeService.saveTradeInput(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 删除交易信息
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/delTradeInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delTradeInfo(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = tradeService.delTradeInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	
	
	/**
	 * 删除交易信息
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/cancelTradeInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo cancelTradeInfo(@RequestParam String tradeInfoParam,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if (tradeInfoParam!=null&&!"".equals(tradeInfoParam)) {
				log.info("===交易撤销请求参数==="+tradeInfoParam);
				TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(tradeInfoParam, TradeInfo.class);
				resultInfo = tradeService.cancelTradeInfo(tradeInfo, loginInfo);
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到交易信息，交易撤销失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 获取最新交易状态信息
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/getTradeStatusInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getTradeStatusInfo(String tradeInfoId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (tradeInfoId!=null&&!"".equals(tradeInfoId)) {
				log.info("===获取最新交易状态信息请求参数==="+tradeInfoId);
				resultInfo = tradeService.getTradeStatusInfo(tradeInfoId);
			}else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到交易状态信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/getTradeInfoNo", method = RequestMethod.GET)
	@ResponseBody
	public String getTradeInfoNo(@RequestParam HashMap paramMap) {
		Map resultMap = new HashMap();
		try {
			//paramMap = tradeService.delTradeInfo(paramMap);
			String productType = (String)paramMap.get("productType");
			String tradeInfoNo = commonService.createTradeNo(productType);
			return tradeInfoNo;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	@RequestMapping(value = "/applyTradeCheckInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> applyTradeCheckInfo(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = tradeService.applyTradeCheckInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/applyTradeAuditInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> applyTradeAuditInfo(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = tradeService.applyTradeAuditInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/saveTradeCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeCheck(@RequestParam HashMap paramMap) {
		Map resultMap = new HashMap();
		try {
			resultMap = tradeService.saveTradeCheck(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/saveTradeAudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeAudit(@RequestParam HashMap paramMap) {
		Map resultMap = new HashMap();
		try {
			resultMap = tradeService.saveTradeAudit(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveTradeCusInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveTradeCusInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			String customerListStr = JsonUtils.getJsonValueByKey("customerList", param);
			List<Map<String,String>> tradeCustInfoList = JsonUtils.jsonArrStrToListMap(customerListStr);
			String tradeInfoStr = JsonUtils.getJsonValueByKey("tradeInfo", param);
			TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(tradeInfoStr, TradeInfo.class);
			resultInfo = tradeService.saveTradeCusInfo(tradeCustInfoList,tradeInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/delRoleInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delRoleInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			String tradeInfoStr = JsonUtils.getJsonValueByKey("tradeInfo", param);
			TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(tradeInfoStr, TradeInfo.class);
			String tradeCustRoleInfoStr = JsonUtils.getJsonValueByKey("tradeCustRoleInfo", param);
			TradeCustRoleInfo tradeCustRoleInfo = (TradeCustRoleInfo)JsonUtils.jsonStrToObject(tradeCustRoleInfoStr, TradeCustRoleInfo.class);
			resultInfo = tradeService.delRoleInfo(tradeInfo,tradeCustRoleInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/delRiskProInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delRiskProInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			String tradeInfoStr = JsonUtils.getJsonValueByKey("tradeInfo", param);
			TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(tradeInfoStr, TradeInfo.class);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tradeInfo", tradeInfo);
			paramMap.put("productId", JsonUtils.getJsonValueByKey("productId", param));
			paramMap.put("riskTotalAssets", JsonUtils.getJsonValueByKey("riskTotalAssets", param));
		
			resultInfo = tradeService.delRiskProInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/delMonProInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delMonProInfo(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap.put("userId", loginInfo.getUserId());
			paramMap.put("userCode", loginInfo.getUserCode());
			paramMap.put("comId", loginInfo.getComId());
			resultMap = tradeService.delMonProInfo(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/delTradeCusInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo delTradeCusInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			String tradeCustInfoStr = JsonUtils.getJsonValueByKey("tradeCustInfo", param);
			Map<String, String> tradeCustInfoMap = JsonUtils.jsonStrToMap(tradeCustInfoStr);
			String tradeInfoStr = JsonUtils.getJsonValueByKey("tradeInfo", param);
			TradeInfo tradeInfo = (TradeInfo)JsonUtils.jsonStrToObject(tradeInfoStr, TradeInfo.class);
			resultInfo = tradeService.delTradeCusInfo(tradeCustInfoMap,tradeInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败，原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/saveTradeBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeBankInfo(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			resultMap = tradeService.saveTradeBankInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/saveTradeAddressInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveTradeAddressInfo(@RequestParam HashMap paramMap,ModelMap model) {
		Map resultMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			resultMap = tradeService.saveTradeAddressInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", "false");
			resultMap.put("msg",  "操作失败，原因是" + e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/queryOpenDateList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> queryOpenDateList(@RequestParam String productId) {
		List<Map<String, String>> openDateList = new ArrayList<Map<String,String>>();
		try {
			if (productId==null||"".equals(productId)) {
				return openDateList;
			}
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			openDateList = tradeService.queryOpenDateByProductId(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openDateList;
	}
	
	@RequestMapping(value = "/queryClosedPeriodsList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> queryClosedPeriodsList(@RequestParam String productId) {
		List<Map<String, String>> closedPeriodsList = new ArrayList<Map<String,String>>();
		try {
			if (productId==null||"".equals(productId)) {
				return closedPeriodsList;
			}
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			closedPeriodsList = tradeService.queryClosedPeriodsByProductId(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return closedPeriodsList;
	}
	
	/**
	 * 打印认购书
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/tradePrintList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo tradePrint(@RequestParam("tradeInfoId") List<Integer> tradeInfoId,HttpServletRequest request,ModelMap model){
		Map resultMap = new HashMap();
		String uploadDir = request.getSession().getServletContext().getRealPath("/");
		System.out.println(System.getProperty("user.dir")); 
		System.out.println("uploadDir====="+uploadDir);
		ResultInfo resultInfo = new ResultInfo();
		try
		{
			for (Integer id : tradeInfoId)
			{
				System.out.println(id);
				LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
				log.info("---------------->"+uploadDir);
				Long tradeId=new Long(id);
				resultInfo=tradeService.printTrade(tradeId,uploadDir,loginInfo);
				return resultInfo;
			}
		}
		catch (NullPointerException e1)
		{
			e1.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请录入正确的客户信息或产品信息！");
			return resultInfo;
		}
		catch (MyBatisSystemException e2)
		{
			e2.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("请检查此交易产品是否存在多条！");
			return resultInfo;
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("交易信息读取失败！");
			return resultInfo;
		}
		resultInfo.setSuccess(false);
		resultInfo.setMsg("读取交易信息失败！");
		return resultInfo;// 重定向
	}
	/**
	 * 认购书下载初始页面
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/printNum",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid getTradeDownload(DataGridModel dgm,String param){
		DataGrid dataGrid = new DataGrid();
		try {
			//log.info("==请求数据===" + param);
			Map paramMap = new HashMap();
			System.out.println("---------------------->"+param);
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = JsonUtils.jsonStrToMap(param);
				//BigDecimal par=new BigDecimal(param);
//				paramMap.put("tradeNo", par);
			}
			dataGrid=tradeService.getTradeDownload(dgm,paramMap);
		} catch (Exception e) {
		}
		return dataGrid;
	}
	
	
	@RequestMapping(value = "/downloadPrint", method = RequestMethod.POST)
	//@ResponseBody
	public void downloadPrint(HttpServletRequest request,HttpServletResponse response,@RequestParam("param") String param,ModelMap model){
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			param = StringUtils.encodeStr(param);
			System.out.println("文件相关参数param：==="+param);	
			DefPrintInfo defPrintInfo = (DefPrintInfo)JsonUtils.jsonStrToObject(param, DefPrintInfo.class);
			tradeService.downloadPrint(request, response, defPrintInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/queryWealthProductDetailInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo queryWealthProductDetailInfo(@RequestParam String productId) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			if (productId==null||"".equals(productId)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品编码为空，未获取到产品详细信息");
				return resultInfo;
			}
			resultInfo = tradeService.queryWealthProductDetailInfo(productId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 生成认购确认书
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/tradeConfirmPrintList",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo tradeConfirmPrintList(@RequestParam("param") String param,HttpServletRequest request,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			String uploadDir = request.getSession().getServletContext().getRealPath("/");
			log.info("=======生成认购确认书前台传入数据========="+param);
			Map paramMap = JsonUtils.jsonStrToMap(param);
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取产品子类型并进行判断
			String productName = (String)paramMap.get("productName");
			PDProductExample pdProductExample = new PDProductExample();
			pdProductExample.createCriteria().andProductNameEqualTo(productName)
			.andRcStateEqualTo(Constants.EFFECTIVE_RECORD);
			List<PDProduct> pdProductList = pdProductMapper.selectByExample(pdProductExample);
			String productSubType = pdProductList.get(0).getProductSubType();
			if (productSubType.equals("01")) {
				resultInfo = tradeService.tradeConfirmPrintFloatList(paramMap,uploadDir,loginInfo);
			}
			if (productSubType.equals("02")) {
				resultInfo = tradeService.tradeConfirmPrintList(paramMap,uploadDir,loginInfo);
			}
			if (productSubType.equals("03")||productSubType.equals("04")) {
				resultInfo = tradeService.tradeConfirmPrintFloatList03(paramMap,uploadDir,loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("生成认购确认书出现异常");
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/checkRiskLevel",  method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo checkRiskLevel(@RequestParam HashMap paramMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			resultInfo= tradeService.checkRiskLevel(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
		}
		return resultInfo;
	}
	
	/** 
	 * 获取产品预约成功之后生成交易的基本信息
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTradeBaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getTradeBaseInfo(@RequestParam("param") String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		log.info("=============获取交易基本信息=============="+param);
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = tradeService.getTradeBaseInfo(paramMap,loginInfo);
		}catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;		
	}
	
}
