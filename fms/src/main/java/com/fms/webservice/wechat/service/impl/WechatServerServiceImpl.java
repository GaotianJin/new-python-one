package com.fms.webservice.wechat.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.mapper.HolidaysMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentExample;
import com.fms.db.model.Holidays;
import com.fms.db.model.HolidaysExample;
import com.fms.service.mapper.AgentServiceMapper;
import com.fms.service.mapper.CustomerServiceMapper;
import com.fms.webservice.wechat.service.WechatServerService;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.ResultInfo;

import net.sf.jasperreports.export.parameters.ParametersOutputStreamExporterOutput;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WechatServerServiceImpl  implements WechatServerService {

	private static final Logger logger = Logger.getLogger(WechatServerServiceImpl.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private AgentServiceMapper agentServiceMapper;
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	@Autowired
    private HolidaysMapper holidaysMapper;
	
	@Autowired
    private AgentMapper agentMapper;

    
    
	
				/////////////////////Zhu//////////////////////////
	@SuppressWarnings("rawtypes")
	@Override
	/**
	 * 登录验证
	 * @author zhuyueming
	 */
	public String login(String requestInfo,String ip) {
		ResultInfo resultInfo = new ResultInfo();
		try {//获取请求参数
			logger.info("===login====requestInfo====" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("===login====secretKey====" + secretKey);
			// 判断加密密文是否一致
			  if(!JsonUtils.checkWechatJsonLogin(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			 return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 根据请求参数查询是否存在理财经理信息 并判断是否是客户
			Map resultMap = customerServiceMapper.wechatLoginForAgent(requestParamMap);
			if (resultMap == null) {
				//若不是理财经理 则判断是否为客户
				resultMap = customerServiceMapper.wechatLogin(requestParamMap);
			}
			if (resultMap == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("登录信息有误，请核对您的登录信息");
				resultInfo.setObj(null);
			} else {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("登录成功");
				resultInfo.setObj(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("登录出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 我的固定产品投资
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String myFixedAsset(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey=======" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			  return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			//根据客户号查询客户认购额
		    Map<String, String> investTotal=customerServiceMapper.getFixedAsset(requestParamMap);
			Map<String, Object> resultMap=customerServiceMapper.getTodayAsset(requestParamMap);
			List<Map<String, String>> holdProduct=customerServiceMapper.getFixedProduct(requestParamMap);
			resultMap.putAll(investTotal);
			resultMap.put("holdProduct", holdProduct);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 我的浮动投资
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	public String myFloatAsset(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey=======" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			  return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// ----浮动产品
			Map<String, Object> resultMap = customerServiceMapper.getFloatAsset(requestParamMap);
			Map<String, String> forecastAsset = customerServiceMapper.getFloatForecastAsset(requestParamMap);
			List<Map<String, String>> holdProduct = customerServiceMapper.getFloatProduct03(requestParamMap);
			resultMap.putAll(forecastAsset);
			resultMap.put("holdProduct", holdProduct);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 股权产品详情
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String stockProductDetail(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey=======" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			 return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 根据客户号和产品号查询特定股权产品详细信息
			Map resultMap = customerServiceMapper.getStockDetail(requestParamMap);
			
			
			/*根据endDate获取5个工作日后的日期***************************************/
			/*String endDate=(String) resultMap.get("endDate");
			if(endDate!=null&&!"".equals(endDate)){
			String toAccountDate = DateUtils.calDate(endDate, 5, "D");
			HolidaysExample holidaysExample = new HolidaysExample();
	 	 		holidaysExample.createCriteria().andHolidayEqualTo(toAccountDate);
	 	 		List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
	 	 		while(!holidays.isEmpty()){
	 	 			toAccountDate = DateUtils.calDate(toAccountDate, 1, "D"); 	 			
	 	 			//toAccountDate = sdf.format(cal.getTime());
	 				HolidaysExample holidaysExample1 = new HolidaysExample();
	 				holidaysExample1.createCriteria().andHolidayEqualTo(toAccountDate);
	 				holidays = holidaysMapper.selectByExample(holidaysExample1);
	 	 		}
	 	    结束***************************************************************
	 	 	resultMap.put("toAccountDate", toAccountDate);
			}*/
			//获取股权产品分配记录
			List stockProductRecordList = new ArrayList();
			List<Map<String, Object>> stockProductRecordLists=customerServiceMapper.getStockProductRecord(requestParamMap);
			if (!stockProductRecordLists.isEmpty()||stockProductRecordLists.size() > 0) {
				//循环处理数据
				for (Map stockProductRecord : stockProductRecordLists) {
					String distributeDate = stockProductRecord.get("distributeDate").toString();
					requestParamMap.put("distributeDate", distributeDate);
					List<Map> sumAmount = customerServiceMapper.getStockProductSumTotal(requestParamMap);
					Double amount = (Double)sumAmount.get(0).get("distributeMoney");
					Double subscriptionFee = Double.parseDouble(stockProductRecord.get("subscriptionFee").toString());
					Map distributeRecord = new HashMap();
					if (subscriptionFee < amount) {
						DecimalFormat    df   = new DecimalFormat("######0.00");
						Double distributeMoney = Double.parseDouble(stockProductRecord.get("distributeMoney").toString());
						distributeRecord.put("distributeInterests", df.format(distributeMoney));
						distributeRecord.put("distributePrincipal", "0.00");
					}else {
						DecimalFormat    df   = new DecimalFormat("######0.00");
						Double distributeMoney = Double.parseDouble(stockProductRecord.get("distributeMoney").toString());
						distributeRecord.put("distributePrincipal", df.format(distributeMoney));
						distributeRecord.put("distributeInterests", "0.00");
					}
					distributeRecord.put("distributeDate", distributeDate);
					stockProductRecordList.add(distributeRecord);
				}
			}
			resultMap.put("distributeRecord", stockProductRecordList);
			/*获取产品合同*/
			List productContractList=customerServiceMapper.getProductContract(requestParamMap);
			resultMap.put("productContract", productContractList);
			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			/*获取产品报告*/
			List PproductReportList=customerServiceMapper.getPproductReport(requestParamMap);
			resultMap.put("productReport", PproductReportList);
			
			
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	///////////////////////////// HSH////////////////////////////////////
	/**
	 * 浮收产品详情
	 * 
	 * @author HSH
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String floatProductDetail(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			  if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
			    resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
			    return JsonUtils.objectToJsonStr(resultInfo); 
			  }
			 
			// 根据客户号和产品ID查询产品详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = customerServiceMapper.getFloatDetail(requestParamMap);
			List<Map<String, String>> redemptionRecordList = customerServiceMapper.getRedemptionRecord(requestParamMap);
			resultMap.put("redemptionRecord", redemptionRecordList);
			
			List<Map<String, String>> netValueList = customerServiceMapper.getNetValues(requestParamMap);
			resultMap.put("netValueList", netValueList);
			
			/*获取产品合同*/
			List productContractList=customerServiceMapper.getProductContract(requestParamMap);
			resultMap.put("productContract", productContractList);
			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			/*获取产品报告*/
			List PproductReportList=customerServiceMapper.getPproductReport(requestParamMap);
			resultMap.put("productReport", PproductReportList);
			
			
			
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取浮收产品详情出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 固收产品详情
	 * 
	 * @author HSH
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String fixedProductDetail(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			}
			
			// 根据客户号和产品ID查询产品详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = customerServiceMapper.getFixedDetail1(requestParamMap);
			
			
			/*根据endDate获取5个工作日后的日期***************************************/
			String endDate=(String) resultMap.get("endDate");
			if(endDate!=null&&!"".equals(endDate)){
			String toAccountDate = DateUtils.calDate(endDate, 5, "D");
			HolidaysExample holidaysExample = new HolidaysExample();
	 	 		holidaysExample.createCriteria().andHolidayEqualTo(toAccountDate);
	 	 		List<Holidays> holidays = holidaysMapper.selectByExample(holidaysExample);
	 	 		while(!holidays.isEmpty()){
	 	 			toAccountDate = DateUtils.calDate(toAccountDate, 1, "D"); 	 			
	 	 			//toAccountDate = sdf.format(cal.getTime());
	 				HolidaysExample holidaysExample1 = new HolidaysExample();
	 				holidaysExample1.createCriteria().andHolidayEqualTo(toAccountDate);
	 				holidays = holidaysMapper.selectByExample(holidaysExample1);
	 	 		}
	 	    /*结束****************************************************************/
	 	 	resultMap.put("toAccountDate", toAccountDate);
			}
			
			//获取分配记录
			List<Map<String, String>> distributeRecord = customerServiceMapper.getDistributeRecord(requestParamMap);
			resultMap.put("distributeRecord", distributeRecord);
			
			
			/*获取产品合同*/
			List productContractList=customerServiceMapper.getProductContract(requestParamMap);
			resultMap.put("productContract", productContractList);
			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			/*获取产品报告*/
			List PproductReportList=customerServiceMapper.getPproductReport(requestParamMap);
			resultMap.put("productReport", PproductReportList);
			
			
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取固收产品详情出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	/**
	 * 股权产品历史记录
	 * 
	 * @author CJJ
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String stockProductHistory(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据客户号查询产品历史详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultList = customerServiceMapper.getStockHistory(requestParamMap);
			if (!resultList.isEmpty()) {
				for (int i = 0; i < resultList.size(); i++) {
					Map productTemp = resultList.get(i);
					Map paramStr = new HashMap();
					paramStr.put("productId", productTemp.get("productId"));
					//获取产品报告
					List<Map<String, String>> productReportList = customerServiceMapper.getPproductReport(paramStr);
					productTemp.put("productReport", productReportList);
					//获取分配记录
					Map paramStr2 = new HashMap();
					paramStr2.put("productId", productTemp.get("productId"));
					paramStr2.put("customerNo", requestParamMap.get("customerNo"));
					List<Map<String, String>> distributeRecordList = customerServiceMapper.getDistributeRecord(paramStr2);
					productTemp.put("distributeRecord", distributeRecordList);
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultList);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取股权产品历史记录出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 定期产品历史记录
	 * 
	 * @author CJJ
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String fixedProductHistory(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			}
			// 根据客户号查询定期产品历史详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultList = customerServiceMapper.getFixedHistory(requestParamMap);
			if (!resultList.isEmpty()) {
				for (int i = 0; i < resultList.size(); i++) {
					Map productTemp = resultList.get(i);
					Map paramStr = new HashMap();
					paramStr.put("productId", productTemp.get("productId"));
					//获取产品报告
					List<Map<String, String>> productReportList = customerServiceMapper.getPproductReport(paramStr);
					productTemp.put("productReport", productReportList);
					//获取分配记录
					Map paramStr2 = new HashMap();
					paramStr2.put("productId", productTemp.get("productId"));
					paramStr2.put("customerNo", requestParamMap.get("customerNo"));
					List<Map<String, String>> distributeRecordList = customerServiceMapper.getDistributeRecord(paramStr2);
					productTemp.put("distributeRecord", distributeRecordList);
				}
			}		
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultList);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取定期产品历史记录出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	//////////////////// HSH///////////////////////////////////////////

	/**
	 * 根据客户号查询该客户的理财经理信息
	 * 
	 * @author zhuyueming
	 * @param requestInfo
	 * @return
	 */

	@Override
	public String findAgentInfoByCustNo(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				 resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配。"); 
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = agentServiceMapper.findAgentInfoByCustNo(requestParamMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取理财经理出错!!!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 投资分析客户资产配置
	 * 
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	public String customerAssetAllocation(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("========requestInfo========" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 检验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 查询
			Map resultMap = this.customerServiceMapper.getCustomerRiskLevelAndRiskLevelScore(requestParamMap);
			List<Map<String,String>> assetAllocation = this.customerServiceMapper.getCustomerAssetAllocation01(requestParamMap);
			List<Map<String,String>> assetAllocation02 = this.customerServiceMapper.getCustomerAssetAllocation02(requestParamMap);
			List<Map<String,String>> assetAllocation03 = this.customerServiceMapper.getCustomerAssetAllocation03(requestParamMap);
			List<Map<String,String>> assetAllocation04cny = this.customerServiceMapper.getCustomerAssetAllocation04CNY(requestParamMap);
			List<Map<String,String>> assetAllocation04usd = this.customerServiceMapper.getCustomerAssetAllocation04USD(requestParamMap);
			assetAllocation.addAll(assetAllocation02);
			assetAllocation.addAll(assetAllocation03);
			assetAllocation.addAll(assetAllocation04cny);
			assetAllocation.addAll(assetAllocation04usd);
			if(resultMap== null){
				resultMap = new HashMap<String, Object>();
			}
			resultMap.put("assetAllocation", assetAllocation);
			// 封装
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取投资分析客户资产配置出现异常!!!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 投资分析历史收益排名接口
	 * 
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String historyIncomeRanking(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("============requestInfo============" + requestInfo);
			// 获取两方约定的密匙
			String secretkey = commonService.getCodeName("secretKey", "02");
			logger.info("============secretkey============" + secretkey);
			// 校验加密密文是否一致
			if (!JsonUtils.checkWechatJson(secretkey, requestInfo)) {
				resultInfo.setMsg("密匙不匹配!");
				resultInfo.setSuccess(false);
				return JsonUtils.objectToJsonStr(resultInfo);
			}
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 查询
			List<Map<String, String>> resultMap = this.customerServiceMapper.getHistoryIncomeRanking(requestParamMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取投资分析历史收益排名出现异常!");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 投资分析固收类产品回款预期接口
	 * 
	 * @author zhangjie
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String solidProductReturnedMoney(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 查询
			List<Map<String, String>> resultMap = this.customerServiceMapper
					.getSolidProductReturnedMoney(requestParamMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取投资分析固收类产品回款异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	@Override
	public String findAllProduct(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("-----------requestInfo----------"+requestInfo);
			String secreKey = commonService.getCodeName("secretKey", "02");
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); 
				 resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
				String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
				String page = JsonUtils.getJsonValueByKey("page", wechatContent);
				Map<String, Integer> requestParamMap=new HashMap<String, Integer>();
                requestParamMap.put("page", (Integer.parseInt(page)-1)*50);
		    //查询
			List<Map<String, String>> products = this.customerServiceMapper.findAllProduct(requestParamMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(products);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("查询产品出现异常!!!");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 查询客户所有已购产品
	 * 
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAllBoughtProduct(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			String productId=JsonUtils.getJsonValueByKey("productId", wechatContent);
			String page=JsonUtils.getJsonValueByKey("page", wechatContent);
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("productId", productId);
            requestParamMap.put("page", (Integer.parseInt(page)-1)*50);
			// 查询
			List<Map<String, String>> resultMap = this.customerServiceMapper.findAllBoughtProduct(requestParamMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取客户已购产品异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/* ==========================二期新增接口============================== */
	
	/**
	 * 基金产品历史记录
	 * 
	 * @author  CJJ
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String floatProductHistory(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据客户号查询产品历史详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultList = customerServiceMapper.getFloatProductHistory(requestParamMap);
			if (!resultList.isEmpty()){
				for (int i = 0; i < resultList.size(); i++) {
					Map productTemp = resultList.get(i);
					Map paramStr = new HashMap();
					String productId = productTemp.get("productId").toString();
					if (!"XXX".equals(productId)) {
						paramStr.put("productId", productId);	
						//获取产品报告
						List<Map<String, String>> productReportList = customerServiceMapper.getPproductReport(paramStr);
						productTemp.put("productReport", productReportList);
						//获取赎回记录
						Map paramStr2 = new HashMap();
						paramStr2.put("productId", productTemp.get("productId"));
						paramStr2.put("customerNo", requestParamMap.get("customerNo"));
						List<Map<String, String>> redemptionRecordList = customerServiceMapper.getRedemptionRecord(paramStr2);
						productTemp.put("redemptionRecord", redemptionRecordList);
					}else {
						resultList.clear();
					}
					
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultList);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取浮收产品历史记录出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}


								/* ================LOUAO================ */
	@Override
	public String isOldCustomer(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			  if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
			    resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
			    return JsonUtils.objectToJsonStr(resultInfo); 
			  }
			 
			// 
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Integer num = customerServiceMapper.isOldCustomerCount1(requestParamMap); 
			Integer num2 = customerServiceMapper.isOldCustomerCount2(requestParamMap);
			Map<String,String> map=new HashMap<String,String>();
			//判断输入的手机号码是否存在
			if(num!=0||num2!=0){
				map.put("isOldCustomer", "true");
			}else{
				map.put("isOldCustomer", "false");
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("判断是否系统老用户出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	@Override
	public String isOldCustInfo(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			  if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
			    resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
			    return JsonUtils.objectToJsonStr(resultInfo); 
			  }
			 
			// 
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Integer num = customerServiceMapper.isOldCustomerCount1(requestParamMap); //判断rm
			Integer num2 = customerServiceMapper.isOldCustomerCount2(requestParamMap);//判断客户
			Map<String,String> map=new HashMap<String,String>();
			//判断输入的手机号码是否存在
			if(num!=0||num2!=0){
				Map<String,String> resultMap=new HashMap<String,String>();
				//若为rm则返回rm信息 包括rm为客户的情况
				if (num!=0) {
					resultMap = customerServiceMapper.getAgentInfoByMobile(requestParamMap);
				}else {
					//查客户的信息
					resultMap = customerServiceMapper.getCustInfoByMobile(requestParamMap);
				}
				resultInfo.setSuccess(true);
				resultInfo.setMsg("查询成功");
				resultInfo.setObj(resultMap);
			}else{
				map.put("isOldCustomer", "false");
				resultInfo.setSuccess(true);
				resultInfo.setMsg("查询成功");
				resultInfo.setObj(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("判断是否系统老用户出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 查询定期产品列表
	 * 
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAllFixedProduct(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("-----------requestInfo----------"+requestInfo);
			String secreKey = commonService.getCodeName("secretKey", "02");
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); 
				 resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			/*// 校验通过后解析请求报文获取请求参数
				String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
				String page = JsonUtils.getJsonValueByKey("page", wechatContent);
				Map<String, Integer> requestParamMap=new HashMap<String, Integer>();
                requestParamMap.put("page", (Integer.parseInt(page)-1)*50);*/
		    //查询
			List<Map<String, String>> products = this.customerServiceMapper.findAllFixedProduct();
			Map<String, Object> map=new HashMap<String, Object>();
			//将查询出来的放在一个map中，对应json格式
			map.put("fixedProductList", products);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("查询定期产品出现异常!!!");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	/**
	 * 查询定期产品详情
	 * 
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findFixedProductDetail(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			  if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
			    resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
			    return JsonUtils.objectToJsonStr(resultInfo); 
			  }
			 
			// 
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = customerServiceMapper.getFixedProductDetail(requestParamMap);
			//List<Map<String, String>> subscriptionRecord = customerServiceMapper.getFloatDetail2(requestParamMap);
			//resultMap.put("subscriptionRecord", subscriptionRecord);
			
			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取定期产品详情出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	/**
	 * 查询股权产品列表
	 * 
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAllStockProduct(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("-----------requestInfo----------"+requestInfo);
			String secreKey = commonService.getCodeName("secretKey", "02");
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); 
				 resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			/*// 校验通过后解析请求报文获取请求参数
				String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
				String page = JsonUtils.getJsonValueByKey("page", wechatContent);
				Map<String, Integer> requestParamMap=new HashMap<String, Integer>();
                requestParamMap.put("page", (Integer.parseInt(page)-1)*50);*/
		    //查询
			List<Map<String, String>> products = this.customerServiceMapper.findAllStockProduct();
			Map<String, Object> map=new HashMap<String, Object>();
			//将查询出来的放在一个map中，对应json格式
			map.put("stockProductList", products);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("查询股权产品出现异常!!!");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	/**
	 * 查询股权产品详情
	 * 
	 * @author ao
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findStockProductDetail(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			
			  if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
			    resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
			    return JsonUtils.objectToJsonStr(resultInfo); 
			  }
			 
			// 
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = customerServiceMapper.getStockProductDetail(requestParamMap);
			//List<Map<String, String>> subscriptionRecord = customerServiceMapper.getFloatDetail2(requestParamMap);
			//resultMap.put("subscriptionRecord", subscriptionRecord);
			
			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取股权产品详情出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

									/* ================SHIYUNPENG================ */
	/**
	 * 查询浮动商品列表
	 * 
	 * @author SYP
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findFloatProductList(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			Map<String, List> map = new HashMap<String, List>();
			List<Map<String, String>> resultMap = this.customerServiceMapper.findFloatProductList();
			map.put("floatProductList", resultMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("查询产品信息异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	/**
	 * 查询股权产品详情
	 */
	@Override
	public String findFloatProductDetails(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			String productId=JsonUtils.getJsonValueByKey("productId", wechatContent);
			
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("productId", productId);
          
			// 查询
			Map resultMap = customerServiceMapper.findFloatProductDetails(requestParamMap);

			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			
			/*获取产品净值列表*/
			List<Map<String, String>> netValueList = customerServiceMapper.getFloatNetValues(requestParamMap);
			resultMap.put("netValueList", netValueList);
			
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取产品详情异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
		
	}

	@Override
	public String findOverseasProductList(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			Map<String, List> map = new HashMap<String, List>();
			List<Map<String, String>> resultMap = this.customerServiceMapper.findOverseasProductList();
			map.put("overseasProductList", resultMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("查询海外产品信息异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

	@Override
	public String findOverseasProductDetails(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			String productId=JsonUtils.getJsonValueByKey("productId", wechatContent);
			
            Map<String, Object> requestParamMap=new HashMap<String, Object>();
            requestParamMap.put("productId", productId);
          
			//查询
			Map resultMap = customerServiceMapper.findOverseasProductDetails(requestParamMap);
			
			/*获取产品推介材料*/
			List ProductMaterialList=customerServiceMapper.getProductMaterial(requestParamMap);
			resultMap.put("productMaterial", ProductMaterialList);
			
			
			/*获取产品净值列表*/
			List<Map<String, String>> netValueList = customerServiceMapper.getFloatNetValues(requestParamMap);
			resultMap.put("netValueList", netValueList);
			
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取海外产品详情异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}

									/* ================ZYM================ */
	/**
	 * 查询理财经理名下客户
	 * 
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String findAllCustomer(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			 String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			 Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 查询
			List<Map<String, String>> resultMap = this.customerServiceMapper.findAllCustomer(requestParamMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取理财经理名下客户列表异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 查询客户基本信息
	 * 
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String queryCustBaseInfo(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			 String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			 Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 查询
			List<Map<String, String>> resultMap = this.customerServiceMapper.queryCustBaseInfo(requestParamMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取客户基本信息异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 查询个人账户信息
	 * 
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@Override
	public String queryPersonalInfo(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==========c===========" + requestInfo);
			// 获取两方约定的密匙
			String secreKey = commonService.getCodeName("secretKey", "02");
			logger.info("==========secreKey============" + secreKey);
			// 校验加密密文是否匹配
			 if(!JsonUtils.checkWechatJson(secreKey, requestInfo)){
				 resultInfo.setMsg("密匙不匹配!"); resultInfo.setSuccess(false);
				 return JsonUtils.objectToJsonStr(resultInfo);
			 }
			// 校验通过后解析请求报文获取请求参数
			 String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			 Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// 查询
			List<Map<String, String>> resultMap = this.customerServiceMapper.queryPersonalInfo(requestParamMap);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setMsg("获取客户基本信息异常...");
			resultInfo.setSuccess(false);
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 我的股权资产
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String myStockAsset(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey=======" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			  return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			// ----股权产品
			Map<String, Object> resultMap = customerServiceMapper.getStockAsset(requestParamMap);
			Map<String, String> forecastAsset = customerServiceMapper.getStockForecastAsset(requestParamMap);
			List<Map<String, String>> holdProduct = customerServiceMapper.getStockProduct(requestParamMap);
			resultMap.putAll(forecastAsset);
			resultMap.put("holdProduct", holdProduct);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 我的海外资产
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String myOverseasAsset(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey=======" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			  return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			
			
			List<Map<String, String>> investTotal = customerServiceMapper.getOverseasAssetCNY(requestParamMap);
			Map<String, String> usd = customerServiceMapper.getOverseasAssetUSD(requestParamMap);
			investTotal.add(usd);
			List<Map<String, String>> forecastAsset = customerServiceMapper.getOverseasForecastCNY(requestParamMap);
			Map<String, String> usd1 = customerServiceMapper.getOverseasForecastUSD(requestParamMap);
			forecastAsset.add(usd1);
			List<Map<String, String>> holdProduct = customerServiceMapper.getOverseasProduct(requestParamMap);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("investTotal", investTotal);
			resultMap.put("forecastAsset", forecastAsset);
			resultMap.put("holdProduct", holdProduct);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**
	 * 我的投资总额
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String myInvestAmount(String requestInfo) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey=======" + secretKey);
			// 判断加密密文是否一致
			if(!JsonUtils.checkWechatJson(secretKey, requestInfo))
			  {
			  resultInfo.setSuccess(false);
			  resultInfo.setMsg("登录密匙不匹配，非正常登录！");
			  return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 校验通过后解析请求报文获取请求参数
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			//查询认购总额
			List<Map<String, String>> investTotalAmount = new ArrayList<Map<String,String>>();
				//股权产品投资金额
				Map<String, Object> stockMap = customerServiceMapper.getStockAsset(requestParamMap);
				String stockInvestAmount = stockMap.get("investTotal").toString().replace(",", "");
				//固定产品投资金额
				Map<String, String> fixedMap=customerServiceMapper.getFixedAsset(requestParamMap);
				String fixedInvestAmount = fixedMap.get("investTotal").toString().replace(",", "");
				//浮动产品投资金额
				Map<String, Object> floatMap = customerServiceMapper.getFloatAsset(requestParamMap);
				String floatInvestAmount = floatMap.get("investTotal").toString().replace(",", "");
				//海外产品投资金额
				List<Map<String, String>> overseasMap = customerServiceMapper.getOverseasAssetCNY(requestParamMap);
				String overseasInvestAmount = overseasMap.get(0).get("investTotal").toString().replace(",", "");
			Double investTotal = (Double.parseDouble(stockInvestAmount)+Double.parseDouble(fixedInvestAmount)+
					Double.parseDouble(floatInvestAmount)+Double.parseDouble(overseasInvestAmount))/10000;
			logger.info("=======investTotal=======" + investTotal);
			String investTotalStr = new DecimalFormat("#0.00").format(investTotal);
			Map<String, String> cny = new HashMap<String, String>();
			cny.put("investTotalCNY", investTotalStr+"万");
			Map<String, String> usd = customerServiceMapper.getInvestTotalUSD(requestParamMap);
			//查询理财经理电话 和客服热线
			Map<String, String> agentMobile = customerServiceMapper.getAgentMobile(requestParamMap);
			//股权产品历史投资金额
			Map<String, Object> stockHistoryMap = customerServiceMapper.getStockHistoryAmount(requestParamMap);
			String stockHistoryInvestAmount = stockHistoryMap.get("historyAmount").toString();
			//固定产品历史投资金额
			Map<String, Object> fixedHistoryMap=customerServiceMapper.getFixedHistoryAmount(requestParamMap);
			String fixedHistoryInvestAmount = fixedHistoryMap.get("historyAmount").toString();
			//浮动产品历史投资金额
			Map<String, Object> floatHistoryMap = customerServiceMapper.getFloatHistoryAmount(requestParamMap);
			String floatHistoryInvestAmount = floatHistoryMap.get("historyAmount").toString();
			//浮动产品历史投资金额
			Map<String, Object> overseasHistoryMap = customerServiceMapper.getOverseasHistoryAmountCNY(requestParamMap);
			String overseasHistoryInvestAmountCNY = overseasHistoryMap.get("historyAmount").toString();
			Double historyAmount = (Double.parseDouble(stockHistoryInvestAmount)+Double.parseDouble(fixedHistoryInvestAmount)+
					Double.parseDouble(floatHistoryInvestAmount)+Double.parseDouble(overseasHistoryInvestAmountCNY))/10000;
			logger.info("=======historyAmount=======" + historyAmount);
			String historyAmountStr = new DecimalFormat("#0.00").format(historyAmount);
			Map<String, String> cnyHistory = new HashMap<String, String>();
			cnyHistory.put("historyAmountCNY", historyAmountStr+"万");
			Map<String, String> usdHistory = customerServiceMapper.getOverseasHistoryAmountUSD(requestParamMap);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.putAll(cny);
			resultMap.putAll(usd);
			resultMap.putAll(cnyHistory);
			resultMap.putAll(usdHistory);
			resultMap.putAll(agentMobile);
			resultInfo.setMsg("查询成功");
			resultInfo.setSuccess(true);
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("查询出现异常！");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	
	/**热门产品列表
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	public String hotProductList(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据客户号查询产品历史详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = new HashMap();
			List<Map<String, String>> fixedList = customerServiceMapper.getFixedHotProductList(requestParamMap);
			List<Map<String, String>> stockList = customerServiceMapper.getStockHotProductList(requestParamMap);
			List<Map<String, String>> floatList = customerServiceMapper.getFloatHotProductList(requestParamMap);
			List<Map<String, String>> overseasList = customerServiceMapper.getOverseasHotProductList(requestParamMap);
			resultMap.put("fixedList", fixedList);
			resultMap.put("stockList", stockList);
			resultMap.put("floatList", floatList);
			resultMap.put("overseasList", overseasList);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取热门产品出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/* ================CJJ================ */
	/**海外产品历史列表
	 * @author CJJ
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String overseasProductHistory(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据客户号查询产品历史详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultList = customerServiceMapper.getOverseasHistory(requestParamMap);
			if (!resultList.isEmpty()) {
				for (int i = 0; i < resultList.size(); i++) {
					Map productTemp = resultList.get(i);
					Map paramStr = new HashMap();
					String productId = productTemp.get("productId").toString();
					if (!"XXX".equals(productId)) {
						paramStr.put("productId", productTemp.get("productId"));
						//获取产品报告
						List<Map<String, String>> productReportList = customerServiceMapper.getPproductReport(paramStr);
						productTemp.put("productReport", productReportList);
						//获取赎回记录
						Map paramStr2 = new HashMap();
						paramStr2.put("productId", productTemp.get("productId"));
						paramStr2.put("customerNo", requestParamMap.get("customerNo"));
						List<Map<String, String>> redemptionRecordList = customerServiceMapper.getRedemptionRecord(paramStr2);
						productTemp.put("redemptionRecord", redemptionRecordList);
					}else {
						resultList.clear();
					}
				}
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultList);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取环球产品历史记录出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/////////////////////////////新增接口/////////////////////////////
	/**根据客户号和时间查鲸币
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String whaleCoin(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据客户号查询产品历史详情
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map<String, String> resultMap = customerServiceMapper.getWhaleCoin(requestParamMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取鲸币信息出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	
	/**根据客户号查预约记录
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String productOrderRecord(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据客户号查预约记录
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultMap = customerServiceMapper.getProductOrderRecord(requestParamMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取客户预约记录出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**根据理财经理Id查rm详情
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAgentDetailInfo(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据agentId查详细信息
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			Map resultMap = customerServiceMapper.getAgentDetailInfo(requestParamMap);
			//查询从业经历i和所获证书
			List<Map<String, String>> certificationList = customerServiceMapper.getCertificationList(requestParamMap);
			List<Map<String, String>> workList = customerServiceMapper.getWorkList(requestParamMap);
			resultMap.put("certificationList", certificationList);
			resultMap.put("workList", workList);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取理财顾问详细信息出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**根据产品Id/类型查所有净值
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAllNetValue(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatNetvalueJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 根据productId和type查详细信息
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultMap = customerServiceMapper.getAllNetValue(requestParamMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品净值信息出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**根据手机号查询是否是员工
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String isEmployee(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			String mobile = requestParamMap.get("mobile");
			AgentExample agentExample = new AgentExample();
			agentExample.createCriteria().andMobileEqualTo(mobile).andRcStateEqualTo("E");
			List<Agent> agentList = agentMapper.selectByExample(agentExample);
			String returnStr = "";
			if (agentList.isEmpty() || agentList.size() < 1) {
				returnStr = "02";
			}else {
				returnStr = "01";
			}
			Map resultMap = new HashMap();
			resultMap.put("resultStr", returnStr);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取是否是员工信息出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	/**查询所有浮动产品
	 * @author ZYM
	 * @param requestInfo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String queryAllFloatProducts(String requestInfo){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("=======requestInfo=======" + requestInfo);
			// 获取两方约定的密匙
			String secretKey = commonService.getCodeName("secretKey", "02");
			logger.info("=======secretKey====" + secretKey);
			// 判断加密密文是否一致
			 if(!JsonUtils.checkWechatJson(secretKey, requestInfo)){
				resultInfo.setSuccess(false); resultInfo.setMsg("密匙不匹配！"); 
				return JsonUtils.objectToJsonStr(resultInfo); 
			 }
			// 查所有浮动产品
			String wechatContent = JsonUtils.getJsonValueByKey("wechatContent", requestInfo);
			Map<String, String> requestParamMap = JsonUtils.jsonStrToMap(wechatContent);
			List<Map<String, String>> resultMap = customerServiceMapper.queryAllFloatProducts(requestParamMap);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("查询成功");
			resultInfo.setObj(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取鲸选基金产品出错!");
		}
		return JsonUtils.objectToJsonStr(resultInfo);
	}
}
