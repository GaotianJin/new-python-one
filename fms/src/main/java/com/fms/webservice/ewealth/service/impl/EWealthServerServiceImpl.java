package com.fms.webservice.ewealth.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.fms.db.mapper.AgentMapper;
import com.fms.db.model.Agent;
import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.PDAmountOrderInfoExample;
import com.fms.service.customer.CustomerService;
import com.fms.service.customer.QueryCustomerService;
import com.fms.service.product.ProductOrderService;
import com.fms.service.product.ProductService;
import com.fms.service.product.impl.ProductServiceImpl;
import com.fms.service.trade.TradeService;
import com.fms.webservice.ewealth.service.EWealthServerService;
import com.sinosoft.core.db.mapper.DefCodeMapper;
import com.sinosoft.core.db.model.DefCode;
import com.sinosoft.core.db.model.DefCodeExample;
import com.sinosoft.core.service.CommonService;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.XmlUtils;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EWealthServerServiceImpl implements EWealthServerService {
	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	@Autowired
	PDAmountOrderInfoExample pdAmountOrderInfoMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private QueryCustomerService queryCustomerService;
	@Autowired
	private ProductOrderService productOrderService;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private DefCodeMapper defCodeMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 从码表中查询报文MD5加密密钥
	 * @author LIU YI
	 * @return
	 */
	private String getKey() {
		DefCodeExample defCodeExample = new DefCodeExample();
		DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
		defCodeCriteria.andCodeTypeEqualTo("secretKey");
		List<DefCode> defCodeList = defCodeMapper.selectByExample(defCodeExample);
		String key = "";
		if (defCodeList != null && defCodeList.size() > 0) {
			key = defCodeList.get(0).getCode();
		}
		return key;
	}

	/**
	 * 根据产品流水号获取产品详细信息
	 * 
	 * @param productId
	 * @return
	 */
	public String getProductInfo(String productId) {
		ResultInfo resultInfo = productService.getProductInfo(productId);
		return JsonUtils.objectToJsonStr(resultInfo);
	}
	
	
	/**
	 * 根据用户手机号与用户邮箱查询客户详细信息
	 * 
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public String getCustomerInfo(String requestXml) {
		// 请求报文requestXml存入数据库
		if (commonService.saveWebserviceLog("getCustomerInfo", "客户信息查询接口", "", requestXml, "01", "成功接收请求报文")) {
			log.info("请求报文成功存入数据库");
		} else {
			log.info("请求报文未成功存入数据库");
		}
		
		// xmlstringNoEnter与message对象处理返回报文字符串
		String xmlstringNoEnter = "";
		String message = "";
		/**********************************************************************************
		 * 第一模块：验证请求报文合法性
		 *********************************************************************************/
		//一.验证报文类型，报文参数类型为返回报文直接返回错误信息
		//二.
		//判断请求报文格式
		int messageContentStartPos = requestXml.indexOf("<eWealthContent>");
		int lableStart = requestXml.indexOf("</eWealthContent>");
		if ((-1 == messageContentStartPos) || (-1 == lableStart)) {
			log.info("请求报文体标签名<eWealthContent>不存在");
			log.info("返回空报文");
			return generateEmptyCustomerInfoResponseXml();
		}
		//截取请求报文报文体字符串
		int messageContentEndPos = lableStart + ("</eWealthContent>".length());
		String xmlMessageContent = requestXml.substring(messageContentStartPos, messageContentEndPos);
		//输出请求报文报文体
		log.info("请求报文报文体字符串为:" + xmlMessageContent);
		//获取密钥
		String secretKey = getKey(); 
		//根据密钥与报文体字符串生成MD5密文
		String signMsgGeneratedMD5 = XmlUtils.md5EncodeRequestXmlBody(secretKey,xmlMessageContent);
		//获取请求报文报文头携带MD5值
		Map<String, Object> XmlDataMap = new HashMap<String, Object>();
		try {
			XmlUtils.parseXmlToMap(requestXml, XmlDataMap);
		} catch (DocumentException e) {
			e.printStackTrace();
			log.info("解析报请求报文出错，将返回空报文");
			// -------------------生成空报文并将其返回给WebService请求端--------------
			return generateEmptyCustomerInfoResponseXml();
		}
		Map<String, Object> headerMap = (HashMap<String, Object>) XmlDataMap.get("eWealthHeader");
		String Md5InMessHeader = (String) headerMap.get("signMsg");
		// 请求报文中MD5值 不同于 WebService端自己生成的MD5值
		if (!Md5InMessHeader.equals(signMsgGeneratedMD5)) {
			log.info("服务端自己生成MD5值：" + signMsgGeneratedMD5);
			log.info("服务端生成MD5报文与请求报文头中MD5值不相同");
			log.info("返回空报文");
			return generateEmptyCustomerInfoResponseXml();
		}

		/*******************************************************************************************************
		 * 第二模块：获取请求报文报文体携带参数值
		 *****************************************************************************************************/
		Map<String, Object> parasInMessBodyMap = (Map<String, Object>) XmlDataMap.get("eWealthContent");

		// 20150922
		Map<String, Object> mobileNumberMap = new HashMap<String, Object>();
		mobileNumberMap.put("receiveMobile", parasInMessBodyMap.get("receiveMobile"));

		Map<String, Object> emailMap = new HashMap<String, Object>();
		emailMap.put("email", parasInMessBodyMap.get("email"));

		/**************************************************************************************
		 * 第三模块：将请求报文报文体携带参数传入下一层进行查询并 依据下一层返回类对象中状态标志进行相应报文封装
		 *************************************************************************************/
		// document,rootElement对象用于生成整个报文字符串
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");

		/****** 根据手机号与邮箱查询核心系统客户信息 ************/
		ResultInfo inviteCodeAndTradeInfo = queryCustomerService.queryCustomerInfo(parasInMessBodyMap);
		// 根据手机号与邮箱能够成功查询核心系统客户信息
		if (inviteCodeAndTradeInfo.isSuccess()) {
			Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
			// xmlBodyMap对象
			LinkedHashMap<String, Object> xmlBodyMap = new LinkedHashMap<String, Object>();
			// queryResultDataList对象存储数据库查询结果
			List<LinkedHashMap<String, Object>> queryResultDataList = (List<LinkedHashMap<String, Object>>) inviteCodeAndTradeInfo
					.getObj();
			//将查询结果中不存在的字段名与空值添加到查询结果Map集合
			if (null != queryResultDataList.get(0)) {
				addElementToMap((LinkedHashMap<String, Object>) queryResultDataList.get(0));
			}
			// -----------------生成返回报文报文体字符串----------------------------------------
			// documentTemp,rootElementTemp对象仅用于将报文体转为XML格式字符串
			Document documentTemp = DocumentHelper.createDocument();
			Element rootElementTemp = documentTemp.addElement("");
			// 将报文体信息键值对放入Map
			xmlBodyNodeDataMap.put("eWealthContent", queryResultDataList);
			// 将报文体Map对象转化为XML格式字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
			xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
			int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
			String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
			// 使用约定好的key对报文体内容字符串进行MD5加密
			String secretKeyEncode = getKey();
			String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
			// ------------------生成返回报文报文头字符串----------------------------------------
			// xmlHeadNodeDataMap对象和xmlLeafNodeDataMap对象
			Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
			Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
			// 将报文头所有叶子节点信息键值对放入Map集合中
			xmlLeafNodeDataMap.put("businessCode", "0001");
			xmlLeafNodeDataMap.put("systemCode", "fms");
			xmlLeafNodeDataMap.put("password", "");
			xmlLeafNodeDataMap.put("resultCode", "00");
			xmlLeafNodeDataMap.put("resultDesc", "success");
			xmlLeafNodeDataMap.put("signMsg", encryptionString);
			xmlLeafNodeDataMap.put("requestCode", "1");
			// 将报文头节点键值对放入Map集合中
			xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
			// -------------------生成返回报文字符串--------------------------------------
			// 将报文头Map对象转为XML字符串
			XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
			// 将报文体Map对象转为XML字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
			// 去除字符串中换行符
			xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
			System.out.println(xmlstringNoEnter);// 调试输出信息
			// --------------------返回报文字符串-------------------------------------------
			message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
			return message;
		}

		/******* 根据手机号查询核心系统客户信息 *******/
		ResultInfo customerInfoByMobile = queryCustomerService.obtainCustomerInfoOnlyByMobile(mobileNumberMap);
		// 根据手机号能够成功查询核心系统客户信息
		if (customerInfoByMobile.isSuccess()) {
			Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
			// xmlBodyMap对象
			LinkedHashMap<String, Object> xmlBodyMap = new LinkedHashMap<String, Object>();
			// queryResultDataList对象存储数据库查询结果
			List<LinkedHashMap<String, Object>> queryResultDataList = (List<LinkedHashMap<String, Object>>) customerInfoByMobile
					.getObj();
			// 将查询结果为空的字段名与默认值添加到查询结果Map集合
			if (null != queryResultDataList.get(0)) {
				addElementToMap((LinkedHashMap<String, Object>) queryResultDataList.get(0));
			}
			// -----------------生成返回报文报文体字符串----------------------------------------
			// documentTemp,rootElementTemp对象仅用于将报文体转为XML格式字符串
			Document documentTemp = DocumentHelper.createDocument();
			Element rootElementTemp = documentTemp.addElement("");
			// 将报文体信息键值对放入Map
			xmlBodyNodeDataMap.put("eWealthContent", queryResultDataList);
			// 将报文体Map对象转化为XML格式字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
			xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
			int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
			String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
			// 使用约定好的key对报文体内容字符串进行MD5加密
			String secretKeyEncode = getKey();
			String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
			// ------------------生成返回报文报文头字符串----------------------------------------
			// xmlHeadNodeDataMap对象和xmlLeafNodeDataMap对象
			Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
			Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
			// 将报文头所有叶子节点信息键值对放入Map集合中
			xmlLeafNodeDataMap.put("businessCode", "0001");
			xmlLeafNodeDataMap.put("systemCode", "fms");
			xmlLeafNodeDataMap.put("password", "");
			xmlLeafNodeDataMap.put("resultCode", "00");
			xmlLeafNodeDataMap.put("resultDesc", "success");
			xmlLeafNodeDataMap.put("signMsg", encryptionString);
			xmlLeafNodeDataMap.put("requestCode", "1");
			// 将报文头节点键值对放入Map集合中
			xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
			// -------------------生成返回报文字符串--------------------------------------
			// 将报文头Map对象转为XML字符串
			XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
			// 将报文体信息Map对象转为XML字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
			// 去除字符串中换行符
			xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
			System.out.println(xmlstringNoEnter);// 调试输出信息
			// --------------------返回整个报文字符串-------------------------------------------
			message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
			return message;
		}

		/****** 根据邮箱查询核心系统客户信息 ********/
		ResultInfo customerInfoByEmail = queryCustomerService.obtainCustomerInfoOnlyByEmail(emailMap);
		// 根据邮箱能够成功查询核心系统客户信息
		if (customerInfoByEmail.isSuccess()) {
			Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
			// xmlBodyMap对象
			LinkedHashMap<String, Object> xmlBodyMap = new LinkedHashMap<String, Object>();
			// queryResultDataList对象存储数据库查询结果
			List<LinkedHashMap<String, Object>> queryResultDataList = (List<LinkedHashMap<String, Object>>) customerInfoByEmail
					.getObj();
			// 将查询结果为空的字段名与默认值添加到查询结果Map集合
			if (null != queryResultDataList.get(0)) {
				addElementToMap((LinkedHashMap<String, Object>) queryResultDataList.get(0));
			}
			// -----------------生成返回报文报文体字符串----------------------------------------
			// documentTemp,rootElementTemp对象仅用于将报文体信息转为XML格式字符串
			Document documentTemp = DocumentHelper.createDocument();
			Element rootElementTemp = documentTemp.addElement("");
			// 将报文体信息键值对放入Map
			xmlBodyNodeDataMap.put("eWealthContent", queryResultDataList);
			// 将报文体Map对象转化为XML格式字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
			xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
			int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
			String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
			// 使用约定好的key对报文体内容字符串进行MD5加密
			String secretKeyEncode = getKey();
			String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
			// ------------------生成返回报文报文头字符串----------------------------------------
			// xmlHeadNodeDataMap对象和xmlLeafNodeDataMap对象
			Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
			Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
			// 将报文头所有叶子节点信息键值对放入Map集合中
			xmlLeafNodeDataMap.put("businessCode", "0001");
			xmlLeafNodeDataMap.put("systemCode", "fms");
			xmlLeafNodeDataMap.put("password", "");
			xmlLeafNodeDataMap.put("resultCode", "00");
			xmlLeafNodeDataMap.put("resultDesc", "success");
			xmlLeafNodeDataMap.put("signMsg", encryptionString);
			xmlLeafNodeDataMap.put("requestCode", "1");
			// 将报文头节点键值对放入Map集合中
			xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
			// -------------------生成返回报文字符串--------------------------------------
			// 将报文头Map对象转为XML字符串
			XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
			// 将报文体信息Map对象转为XML字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
			// 去除字符串中换行符
			xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
			System.out.println(xmlstringNoEnter);// 调试输出信息
			// --------------------返回整个报文字符串-------------------------------------------
			message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
			return message;
		}
		/************** 不能查询到核心系统客户信息 **********************/
		if (!customerInfoByEmail.isSuccess()) {
			//返回空报文
			return generateEmptyCustomerInfoResponseXml();
		}
		return "EWealthServiceImpl";
	}
	
	/**
	 * 该私有方法属于接口：根据用户手机号与用户邮箱查询客户详细信息
	 * 用于生成用户信息空报文
	 * @author LIWENTAO
	 * @return
	 */
	private String generateEmptyCustomerInfoResponseXml() {
		String xmlstringNoEnter = "";
		String message = "";
		// -------------------组报文体信息--------------
		// document,rootElement对象用于生成整个报文字符串
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");
		// a.声明局部Document对象，Element对象，仅用于将报文体信息转为XML格式字符串
		Document documentTemp = DocumentHelper.createDocument();
		Element rootElementTemp = documentTemp.addElement("");
		// b.新建两个Map对象存放报文体信息
		Map<String, Object> xmlContentDataMap = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> xmlContentLeafMap = new LinkedHashMap<String, Object>();
		// c.将空值信息添加到报文体内
		addElementToMap(xmlContentLeafMap);
		xmlContentLeafMap.put("annualIncome", "0.0");
		xmlContentLeafMap.put("birthday", "1800-01-01 00:00:00");
		xmlContentLeafMap.put("cardNoDate", "1800-01-01 00:00:00");
		// d.将报文体信息键值对放入Map
		xmlContentDataMap.put("eWealthContent", xmlContentLeafMap);
		// e.将报文体信息转化为XML格式字符串
		XmlUtils.mapToXml(xmlContentDataMap, rootElementTemp);
		xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
		int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
		String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
		// f.使用约定好的key对报文体内容字符串进行MD5加密
		String secretKeyEncode1 = getKey();
		String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode1, messageContent);
		// ---------------------组报文头信息----------------------
		// a.新建两个Map对象存放报文头信息键值对
		Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
		// b.将报文头所有节点信息键值对放入Map集合中
		xmlLeafNodeDataMap.put("businessCode", "0001");
		xmlLeafNodeDataMap.put("systemCode", "fms");
		xmlLeafNodeDataMap.put("password", "");
		xmlLeafNodeDataMap.put("resultCode", "01");
		xmlLeafNodeDataMap.put("resultDesc", "false");
		xmlLeafNodeDataMap.put("signMsg", encryptionString);
		xmlLeafNodeDataMap.put("requestCode", "1");
		// c.将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		// ---------------------------------组建整个报文------------------------------
		// 将报文头Map对象转为XML字符串
		XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
		// 将整个报文转为XML格式字符串
		XmlUtils.mapToXml(xmlContentDataMap, rootElement);
		xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
		System.out.println(xmlstringNoEnter);// 调试输出
		// --------------------返回报文字符串-------------
		message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
		return message;
	}
	
	/**
	 * 该私有方法属于接口：根据用户手机号与用户邮箱查询客户详细信息
	 * 用于添加查询结果中未查询到的标签名
	 * @author LIWENTAO
	 * @param xmlMap
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void addElementToMap(LinkedHashMap<String, Object> xmlMap) {
		/************************************* 客户信息空报文模板 ***********************************/
		LinkedHashMap<String, Object> customerTemplateMap = new LinkedHashMap<String, Object>();
		// 居住地址映射
		LinkedHashMap<String, Object> homeAddressMap = new LinkedHashMap<String, Object>();
		homeAddressMap.put("province", "");
		homeAddressMap.put("city", "");
		homeAddressMap.put("area", "");
		homeAddressMap.put("street", "");
		// 通讯地址映射
		LinkedHashMap<String, Object> contactAddressMap = new LinkedHashMap<String, Object>();
		contactAddressMap.put("province", "");
		contactAddressMap.put("city", "");
		contactAddressMap.put("area", "");
		contactAddressMap.put("street", "");
		// '客户'信息报文标签名与空值放入模板映射
		customerTemplateMap.put("custNo", "");
		customerTemplateMap.put("name", "");
		customerTemplateMap.put("cardType", "");
		customerTemplateMap.put("cardNo", "");
		customerTemplateMap.put("gender", "");
		customerTemplateMap.put("birthday", "1800-01-01 00:00:00");
		customerTemplateMap.put("cardNoDate", "1800-01-01 00:00:00");
		customerTemplateMap.put("mobile", "");
		customerTemplateMap.put("email", "");
		customerTemplateMap.put("qq", "");
		customerTemplateMap.put("marryState", "");
		customerTemplateMap.put("education", "");
		customerTemplateMap.put("workType", "");
		customerTemplateMap.put("occupationCode", "");
		customerTemplateMap.put("position", "");
		customerTemplateMap.put("workUnit", "");
		customerTemplateMap.put("annualIncome", 0.0);
		customerTemplateMap.put("agentCode", "");
		customerTemplateMap.put("openBranchName", "");
		customerTemplateMap.put("bankNo", "");
		customerTemplateMap.put("openBankName", "");
		customerTemplateMap.put("openAccountName", "");
		customerTemplateMap.put("homeAddress", homeAddressMap);
		customerTemplateMap.put("contactAddress", contactAddressMap);

		/********************************** 添加客户信息非空报文中空值标签 ****************************************/
		// 遍历空报文中每个标签
		Set<Entry<String, Object>> customerTemplateMapEntrySet = customerTemplateMap.entrySet();
		for (Entry<String, Object> entry : customerTemplateMapEntrySet) {
			// 判断键值对中值的类类型
			if (entry.getValue().getClass().equals(LinkedHashMap.class)) {
				// 判断类型为Map的值是否在客户报文中
				if (xmlMap.containsKey(entry.getKey())) {
					// 判断地址空报文与地址查询结果报文标签
					LinkedHashMap<String, Object> addressMap = (LinkedHashMap<String, Object>) entry.getValue();
					Set<Entry<String, Object>> addressKeyValueSet = addressMap.entrySet();
					for (Entry<String, Object> entryAddress : addressKeyValueSet) {
						// 判断空报文中地址Map中的键是否出现在客户信息报文中的地址Map
						LinkedHashMap<String, Object> addressInCustomerMessageMap = (LinkedHashMap<String, Object>) xmlMap
								.get(entry.getKey());
						if (!addressInCustomerMessageMap.containsKey(entryAddress.getKey())) {
							addressInCustomerMessageMap.put(entryAddress.getKey(), "");
						}
					}
				} else {
					xmlMap.put(entry.getKey(), entry.getValue());
				}
			} else {
				// 判断类型不为映射的值是否在客户信息报文中
				if (!xmlMap.containsKey(entry.getKey())) {
					xmlMap.put(entry.getKey(), "");
				}
			}
		}
	}

	/**
	 * 根据客户信息流水号获取客户账目信息 将数据封装成XML
	 * 
	 * @param customerNoId
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String getCustAccountInfo(String requestXml) {
		if(commonService.saveWebserviceLog("getCustAccountInfo", "客户账目信息接口", "", requestXml, "01", "成功")){
			log.info("客户账目信息接口接收成功");
		}
		Map<String, Object> xmlDataMap = new HashMap<String, Object>();
		Map<String, Object> xmleWealthContentMap = new HashMap<String, Object>();
		Map<String, Object> resultInfoMap = new HashMap<String, Object>();
		Map<String, Object> eWealthHeaderMap = new LinkedHashMap<String, Object>();
		Map<String, Object> eWealthContentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> messageTotalMap = new LinkedHashMap<String, Object>();
		Map<String, Object> fixedPdInfoMap = new LinkedHashMap<String, Object>();
		Map<String, Object> floatPdInfoMap = new LinkedHashMap<String, Object>();
		String md5document = "";
		ResultInfo resultInfo = new ResultInfo();
		Boolean keyBalance = false;
		String key = getKey();
		try {
			keyBalance = XmlUtils.checkEwealthXml(key, requestXml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		try {
			XmlUtils.parseXmlToMap(requestXml, xmlDataMap);
			xmleWealthContentMap = (HashMap<String, Object>) xmlDataMap.get("eWealthContent");
			String customerNoId = xmleWealthContentMap.get("customerNo").toString();
			if (keyBalance) {
				resultInfo = customerService.getCustAccountInfo(customerNoId);
				resultInfoMap = (HashMap<String, Object>) resultInfo.getObj();
			}
			if (keyBalance && resultInfo.isSuccess()) {
				// 成功
				// 订报文头
				eWealthHeaderMap.put("businessCode", "1"); // 业务类型
				eWealthHeaderMap.put("systemCode", "lc01");// 系统编码
				eWealthHeaderMap.put("password", "无");// 备用字段
				eWealthHeaderMap.put("resultCode", "0");// 处理结果编码
				eWealthHeaderMap.put("resultDesc", "成功");// 处理结果说明
				eWealthHeaderMap.put("signMsg", "");// 加密信息
				eWealthHeaderMap.put("requestCode", "1");// 请求报文标志
				// 报文体
				eWealthContentMap.put("customerName", resultInfoMap.get("custChnName"));// 客户姓名
				eWealthContentMap.put("cumulativeInveset", resultInfoMap.get("custAssetsAmount"));// 投资总额
				eWealthContentMap.put("cumulativeToday", resultInfoMap.get("custInvestingTotal"));// 在投金额
				eWealthContentMap.put("profitAmount", resultInfoMap.get("custinterestTotal"));// 总收益
				fixedPdInfoMap.put("principal", resultInfoMap.get("custPrincipalTotal"));// 固收持有本金
				fixedPdInfoMap.put("estimateProfit", resultInfoMap.get("expectedProfitAmountTotal"));// 固收预计收益
				floatPdInfoMap.put("principal", new BigDecimal(0.00));
				floatPdInfoMap.put("estimateProfit", new BigDecimal(0.00));
				eWealthContentMap.put("fixedPdInfo", fixedPdInfoMap);
				eWealthContentMap.put("floatPdInfo", floatPdInfoMap);
				messageTotalMap.put("eWealthHeader", eWealthHeaderMap);
				messageTotalMap.put("eWealthContent", eWealthContentMap);
				Document document = DocumentHelper.createDocument();
				Element rootElement = document.addElement("eWealthXml");
				XmlUtils.mapToXml(messageTotalMap, rootElement);
				String newXmlStr = document.asXML();
				md5document = XmlUtils.md5EncodeEwealthXml(key, newXmlStr);
				log.info("客户账目信息接口返回成功报文"+md5document);
				return md5document;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String resultDescStr = "";
		String resultMsg = resultInfo.getMsg();
		if (keyBalance == false) {
			resultDescStr = "密钥匹配失败!";
		} else if (resultInfo.isSuccess() == false && keyBalance == true) {
			resultDescStr = resultMsg;
		}
		// 失败
		// 订报文头
		eWealthHeaderMap.put("businessCode", "1"); // 业务类型
		eWealthHeaderMap.put("systemCode", "lc01");// 系统编码
		eWealthHeaderMap.put("password", "无");// 备用字段
		eWealthHeaderMap.put("resultCode", "1");// 处理结果编码
		eWealthHeaderMap.put("resultDesc", resultDescStr);// 处理结果说明
		eWealthHeaderMap.put("signMsg", "");// 加密信息
		eWealthHeaderMap.put("requestCode", "1");// 请求报文标志
		// 报文体
		eWealthContentMap.put("customerName", "");// 客户姓名
		eWealthContentMap.put("cumulativeInveset", new BigDecimal(0.00));// 投资总额
		eWealthContentMap.put("cumulativeToday", new BigDecimal(0.00));// 在投金额
		eWealthContentMap.put("profitAmount", new BigDecimal(0.00));// 总收益
		fixedPdInfoMap.put("principal", new BigDecimal(0.00));// 固收持有本金
		fixedPdInfoMap.put("estimateProfit", new BigDecimal(0.00));// 固收预计收益
		floatPdInfoMap.put("principal", new BigDecimal(0.00));
		floatPdInfoMap.put("estimateProfit", new BigDecimal(0.00));
		eWealthContentMap.put("fixedPdInfo", fixedPdInfoMap);
		eWealthContentMap.put("floatPdInfo", floatPdInfoMap);
		messageTotalMap.put("eWealthHeader", eWealthHeaderMap);
		messageTotalMap.put("eWealthContent", eWealthContentMap);
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");
		XmlUtils.mapToXml(messageTotalMap, rootElement);
		String newXmlStr = document.asXML();
		try {
			md5document = XmlUtils.md5EncodeEwealthXml(key, newXmlStr);
			log.info("客户账目信息接口返回失败报文"+md5document);
			return md5document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return md5document;
	}

	/**
	 * 根据客户Id查询客户保险交易信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getRiskTradeInfo(String riskTradeInfoXml) {
		if(commonService.saveWebserviceLog("getRiskTradeInfo", "查询客户保险交易信息", "", riskTradeInfoXml, "01", "成功")){
			log.info("查询客户保险交易信息接收成功");
		}
		ResultInfo resultInfo = new ResultInfo();
		Map xmlMessageTotalMap = new HashMap();
		Map xmleWealthContentMap = new HashMap();
		List<Map> resultList = new ArrayList<Map>();
		Map messageTotalMap = new HashMap();
		Map eWealthHeaderMap = new HashMap();
		Map eWealthContentMap = new HashMap();
		Boolean keyBalance = false;
		String key = getKey();
		String md5document = "";
		try {
			XmlUtils.parseXmlToMap(riskTradeInfoXml, xmlMessageTotalMap);
			xmleWealthContentMap = (HashMap) xmlMessageTotalMap.get("eWealthContent");
		} catch (DocumentException e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("解析xml成map出现异常！");
			return resultInfo;
		}
		try {
			keyBalance = XmlUtils.checkEwealthXml(key, riskTradeInfoXml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("密钥匹配出现异常！");
			return resultInfo;
		}
		if (keyBalance) {
			resultInfo = tradeService.getRiskTradeInfo(xmleWealthContentMap);
			resultList = (List<Map>) resultInfo.getObj();
		}
		if (resultList != null && resultList.size() > 0) {
			// 成功
			// 订报文头
			eWealthHeaderMap.put("businessCode", "1"); // 业务类型
			eWealthHeaderMap.put("systemCode", "lc01");// 系统编码
			eWealthHeaderMap.put("password", "无");// 备用字段
			eWealthHeaderMap.put("resultCode", "0");// 处理结果编码
			eWealthHeaderMap.put("resultDesc", "成功");// 处理结果说明
			eWealthHeaderMap.put("signMsg", "");// 加密信息
			eWealthHeaderMap.put("requestCode", "1");// 请求报文标志
			// 订报文体
			eWealthContentMap.put("tradeRiskList", resultList);
			messageTotalMap.put("eWealthHeader", eWealthHeaderMap);
			messageTotalMap.put("eWealthContent", eWealthContentMap);
			String newXmlStr = "";
			try {
				newXmlStr = XmlUtils.createXml("ewealth_fmtdef.xml", "tradeRiskInfo", messageTotalMap);
			} catch (DocumentException e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("将map封装成xml出现异常！");
				return resultInfo;
			}
			try {
				md5document = XmlUtils.md5EncodeEwealthXml(key, newXmlStr);
				log.info("查询客户保险交易信息成功报文"+md5document);
				resultInfo.setObj(md5document);
				return resultInfo;
			} catch (DocumentException e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("加密xml出现异常！");
				return resultInfo;
			}
		} else {
			log.info("查询客户保险交易信息失败为空！");
			return null;
		}

	}

	/**
	 * 根据客户Id查询财富保险信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultInfo getWealthPdInfo(String requestXml) {
		if(commonService.saveWebserviceLog("getWealthPdInfo", "查询财富保险信息", "", requestXml, "01", "成功")){
			log.info("查询财富保险信息接收成功");
		}
		ResultInfo resultInfo = new ResultInfo();
		Map xmlMessageTotalMap = new HashMap();
		Map xmleWealthContentMap = new HashMap();
		List<Map> resultList = new ArrayList<Map>();
		Map messageTotalMap = new HashMap();
		Map eWealthHeaderMap = new HashMap();
		Map eWealthContentMap = new HashMap();
		Boolean keyBalance = false;
		String key = getKey();
		String md5document = "";
		try {
			XmlUtils.parseXmlToMap(requestXml, xmlMessageTotalMap);
			xmleWealthContentMap = (HashMap) xmlMessageTotalMap.get("eWealthContent");
		} catch (DocumentException e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("解析xml成map出现异常！");
			return resultInfo;
		}
		try {
			keyBalance = XmlUtils.checkEwealthXml(key, requestXml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("密钥匹配出现异常！");
			return resultInfo;
		}
		if (keyBalance) {
			resultInfo = customerService.getWealthPdInfo(xmleWealthContentMap);
			resultList = (List<Map>) resultInfo.getObj();
		}
		if (resultList != null && resultList.size() > 0) {
			// 成功
			// 订报文头
			eWealthHeaderMap.put("businessCode", "1"); // 业务类型
			eWealthHeaderMap.put("systemCode", "lc01");// 系统编码
			eWealthHeaderMap.put("password", "无");// 备用字段
			eWealthHeaderMap.put("resultCode", "0");// 处理结果编码
			eWealthHeaderMap.put("resultDesc", "成功");// 处理结果说明
			eWealthHeaderMap.put("signMsg", "");// 加密信息
			eWealthHeaderMap.put("requestCode", "1");// 请求报文标志
			// 订报文体
			eWealthContentMap.put("WealthPdInfoList", resultList);
			messageTotalMap.put("eWealthHeader", eWealthHeaderMap);
			messageTotalMap.put("eWealthContent", eWealthContentMap);
			String newXmlStr = "";
			try {
				newXmlStr = XmlUtils.createXml("ewealth_fmtdef.xml", "getWealthPdInfo", messageTotalMap);
			} catch (DocumentException e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("将map封装成xml出现异常！");
				return resultInfo;
			}
			try {
				md5document = XmlUtils.md5EncodeEwealthXml(key, newXmlStr);
				log.info("查询客户财富交易信息成功报文"+md5document);
				resultInfo.setObj(md5document);
				return resultInfo;
			} catch (DocumentException e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("加密xml出现异常！");
				return resultInfo;
			}
		} else {
			log.info("查询客户财富交易信息失败报文为空");
			return null;
		}

	}

	/**
	 * 插入客户信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String insertCustInfo(String custInfoXml) {
		if(commonService.saveWebserviceLog("insertCustInfo", "插入客户信息", "", custInfoXml, "01", "成功")){
			log.info("插入客户信息接收成功");
		}
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> xmlMessageTotalMap = new HashMap<String, Object>();
		Map<String, Object> xmleWealthContentMap = new HashMap<String, Object>();
		Boolean keyBalance = false;
		String key = getKey();
		try {
			XmlUtils.parseXmlToMap(custInfoXml, xmlMessageTotalMap);
			xmleWealthContentMap = (HashMap<String, Object>) xmlMessageTotalMap.get("eWealthContent");
			keyBalance = XmlUtils.checkEwealthXml(key, custInfoXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		String md5eWealthXmlStr = "";
		String resultCode = "";
		String resultDesc = "";
		try {
			if (keyBalance) {
				resultInfo = customerService.insertCustInfo(xmleWealthContentMap);
				if (resultInfo.isSuccess()) {
					resultCode = "0";
					resultDesc = "返回成功";
				} else {
					resultCode = "1";
					resultDesc = resultInfo.getMsg();
				}
			} else {
				resultCode = "1";
				resultDesc = "密钥匹配失败";

			}
			Map<String, Object> sinoHeaderMap = new LinkedHashMap<String, Object>();
			Map<String, Object> eWealthContentMap = new LinkedHashMap<String, Object>();
			Map<String, Object> messageTotalMap = new LinkedHashMap<String, Object>();
			// 订报文头
			sinoHeaderMap.put("businessCode", "1"); // 业务类型
			sinoHeaderMap.put("systemCode", "lc01");// 系统编码
			sinoHeaderMap.put("password", "1");// 备用字段
			sinoHeaderMap.put("resultCode", resultCode);// 处理结果编码
			sinoHeaderMap.put("resultDesc", resultDesc);// 处理结果说明
			sinoHeaderMap.put("signMsg", "");// 加密信息
			sinoHeaderMap.put("requestCode", "1");// 请求报文标志
			// 订报文体
			if (resultInfo.isSuccess() == true && keyBalance == true) {
				String customerNo = resultInfo.getObj().toString();
				eWealthContentMap.put("customerNo", customerNo);
				DefCodeExample defCodeExample = new DefCodeExample();
				DefCodeExample.Criteria defCodeCriteria = defCodeExample.createCriteria();
				defCodeCriteria.andCodeTypeEqualTo("defaultAgent");
				List<DefCode> defCodes = defCodeMapper.selectByExample(defCodeExample);
				String agentCode = defCodes.get(0).getCode();
				eWealthContentMap.put("agentCode", agentCode);
			} else {
				eWealthContentMap.put("false", "失败");
			}
			messageTotalMap.put("eWealthHeader", sinoHeaderMap);
			messageTotalMap.put("eWealthContent", eWealthContentMap);
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("eWealthXml");
			XmlUtils.mapToXml(messageTotalMap, rootElement);
			String eWealthXmlStr = document.asXML();
			try {
				md5eWealthXmlStr = XmlUtils.md5EncodeEwealthXml(key, eWealthXmlStr);
				log.info("插入客户信息返回报文"+md5eWealthXmlStr);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return md5eWealthXmlStr;
	}

	/**
	 * 插入交易信息
	 */

	@Override
	@SuppressWarnings("unchecked")
	public String insertTradeInfo(String tradeInfoXml) {
		if(commonService.saveWebserviceLog("insertTradeInfo", "插入交易信息", "", tradeInfoXml, "01", "成功")){
			log.info("插入交易信息接收成功");
		}
		ResultInfo resultInfo = new ResultInfo();
		Map<String, Object> xmlMessageTotalMap = new HashMap<String, Object>();
		Map<String, Object> xmleWealthContentMap = new HashMap<String, Object>();
		Boolean keyBalance = false;
		String key = getKey();
		try {
			XmlUtils.parseXmlToMap(tradeInfoXml, xmlMessageTotalMap);
			xmleWealthContentMap = (HashMap<String, Object>) xmlMessageTotalMap.get("eWealthContent");
			keyBalance = XmlUtils.checkEwealthXml(key, tradeInfoXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		String md5eWealthXmlStr = "";
		String resultCode = "";
		String resultDesc = "";
		try {
			if (keyBalance) {
				resultInfo = tradeService.insertTradeInfo(xmleWealthContentMap);
				if (resultInfo.isSuccess()) {
					resultCode = "0";
					resultDesc = "返回成功";
				} else {
					resultCode = "1";
					resultDesc = resultInfo.getMsg();
				}
			} else {
				resultCode = "1";
				resultDesc = "密钥匹配失败";

			}
			Map<String, Object> sinoHeaderMap = new LinkedHashMap<String, Object>();
			Map<String, Object> eWealthContentMap = new LinkedHashMap<String, Object>();
			Map<String, Object> messageTotalMap = new LinkedHashMap<String, Object>();
			// 订报文头
			sinoHeaderMap.put("businessCode", "1"); // 业务类型
			sinoHeaderMap.put("systemCode", "lc01");// 系统编码
			sinoHeaderMap.put("password", "1");// 备用字段
			sinoHeaderMap.put("resultCode", resultCode);// 处理结果编码
			sinoHeaderMap.put("resultDesc", resultDesc);// 处理结果说明
			sinoHeaderMap.put("signMsg", "");// 加密信息
			sinoHeaderMap.put("requestCode", "1");// 请求报文标志
			// 订报文体
			if (resultInfo.isSuccess() == true && keyBalance == true) {
				eWealthContentMap.put("result", "true");
			} else {
				eWealthContentMap.put("result", "false");
			}
			messageTotalMap.put("eWealthHeader", sinoHeaderMap);
			messageTotalMap.put("eWealthContent", eWealthContentMap);
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("eWealthXml");
			XmlUtils.mapToXml(messageTotalMap, rootElement);
			String eWealthXmlStr = document.asXML();
			try {
				md5eWealthXmlStr = XmlUtils.md5EncodeEwealthXml(key, eWealthXmlStr);
				log.info("插入交易信息返回报文"+md5eWealthXmlStr);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return md5eWealthXmlStr;
	}

	/**
	 * 根据客户编号查询理财师详细信息
	 * 
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAgentBaseInfo(String requestXml) {
		// 请求报文requestXml存入数据库
		if (commonService.saveWebserviceLog("getAgentBaseInfo", "理财师信息查询接口", "", requestXml, "01", "成功接收请求报文")) {
			log.info("请求报文成功存入数据库");
		} else {
			log.info("请求报文未成功存入数据库");
		}
		// 返回信息字符串
		String xmlstringNoEnter = "";
		String message = "";
		// 使用约定值生成MD5值并截取XML格式字符串中报文体部分
		int messageContentStartPos = requestXml.indexOf("<eWealthContent>");
		int lableStart = requestXml.indexOf("</eWealthContent>");
		if ((-1 == messageContentStartPos) || (-1 == lableStart)) {
			log.info("请求报文体报文体节点不存在");
			log.info("将返回空报文");
			//生成空报文
			return generateEmptyAgentInfoXml();
		}
		//截取请求报文报文体字符串
		int messageContentEndPos = lableStart + ("</eWealthContent>".length());
		String xmlMessageContent = requestXml.substring(messageContentStartPos, messageContentEndPos);
		//输出请求报文报文体
		log.info("请求报文报文体字符串为:" + xmlMessageContent);
		// 1.XML格式字符串为请求报文，首先解析该报文获取参数
		Map<String, Object> nodeDataMap = new HashMap<String, Object>();
		try {
			XmlUtils.parseXmlToMap(requestXml, nodeDataMap);
		} catch (DocumentException e) {
			e.printStackTrace();
			log.info("解析请求报文出错将返回空报文给WebService请求端");
			return generateEmptyAgentInfoXml();
		}
		// 2.获取请求报文报文体参数内容 and  3.获取报文头信息
		String sid = null;
		String signMsg = null;
		try {
			Map<String, Object> messageBodyMap = (HashMap<String, Object>)nodeDataMap.get("eWealthContent");
			sid = (String) messageBodyMap.get("customerNo");
			Map<String, Object> messageHeaderMap = (HashMap<String, Object>)nodeDataMap.get("eWealthHeader");
			signMsg = (String) messageHeaderMap.get("signMsg");
		} catch (NullPointerException e) {
			e.printStackTrace();
			log.info("获取请求报文参数出现空指针错，将返回空报文给WebService请求端");
			//生成空报文
			return generateEmptyAgentInfoXml();
		}
		
		String secretKey = getKey(); // 密钥
		String signMsgMD5 = XmlUtils.md5EncodeRequestXmlBody(secretKey,xmlMessageContent);
		// 5.判断报文头信息中MD5值是否与WebService端自己生成的MD5相等
		if (!signMsgMD5.equals(signMsg)) { // 两者不等，则返回错误信息
			log.info("服务端MD5："+ signMsgMD5);
			log.info("报文MD5信息不对");
			//返回空报文
			return generateEmptyAgentInfoXml();
		} else { // 两者相等，则根据请求报文数据进行查询
			// (1)新建ResultInfo对象，接收QueryCustomerService返回的结果
			ResultInfo xmlAgentInfoMessage = queryCustomerService.queryAgentInfo(sid);
			// (2)声明Document对象，Element对象，用于组建整个报文
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("eWealthXml");
			// (3)根据xmlAgentInfoMessage中状态值的不同执行相应的措施
			if (xmlAgentInfoMessage.isSuccess()) { // 数据库查询返回数据
				// -------------------生成报文体字符串----------------------------------------------
				// 1.局部Document对象，Element对象仅用于将报文体信息转为XML格式字符串
				Document documentTemp = DocumentHelper.createDocument();
				Element rootElementTemp = documentTemp.addElement("");
				// 2.新建一个Map对象和一个List对象
				Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
				List<LinkedHashMap<String, String>> xmlBodyLeafNodeDataList = (List<LinkedHashMap<String, String>>)xmlAgentInfoMessage.getObj();
				//查询理财师照片URL
				Object ob = (xmlBodyLeafNodeDataList.get(0)).get("agentId");
				String agentIdStr = ob.toString();
				log.info("数据库查询返回理财师工号:" + agentIdStr);
				Long agentId = new Long(agentIdStr);
				Agent agentBaseInfo = new Agent();
				Map<String, String> agentMap = new HashMap<String, String>();
				try {
					agentBaseInfo = agentMapper.selectByPrimaryKey(agentId);
					if (agentBaseInfo.getAgentImage() != null && !("".equals(agentBaseInfo.getAgentImage())) ) {
						String fileSaveServerHttpAddress = commonService.getFileSaveServerHttpAddress();
						String fileSavePath = commonService.getFileSavePath("03");
						agentBaseInfo.setAgentImage(fileSaveServerHttpAddress+fileSavePath+agentBaseInfo.getAgentImage());
					}
					if (null == agentBaseInfo.getAgentImage()) {
						xmlBodyLeafNodeDataList.get(0).put("photoURL", "");
					} else {
						agentMap = JsonUtils.objectToMap(agentBaseInfo);
						xmlBodyLeafNodeDataList.get(0).put("photoURL", agentMap.get("agentImage"));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// 3.新建一个列表，存放报文体标签名字
				List<String> xmlContentLabel = new ArrayList<String>();
				xmlContentLabel.add("agentCode");
				xmlContentLabel.add("agentName");
				xmlContentLabel.add("storeName");
				xmlContentLabel.add("province");
				xmlContentLabel.add("city");
				xmlContentLabel.add("area");
				xmlContentLabel.add("street");
				xmlContentLabel.add("agentGender");
				xmlContentLabel.add("telephone");
				xmlContentLabel.add("workingYears");
				xmlContentLabel.add("photoURL");
				// 4.对列表中每个Map元素，添加返回值集合中不存在的报文标签
					// 当前元素i指向Map对象 不等于 NULL
				if (null != xmlBodyLeafNodeDataList.get(0)) {
					for (int j = 0; j < xmlContentLabel.size(); ++j) {
						// 报文体标签名字不在该Map中
						if (!xmlBodyLeafNodeDataList.get(0).containsKey(xmlContentLabel.get(j))) {
							// 该标签名与空值增加到该Map元素
							xmlBodyLeafNodeDataList.get(0).put(xmlContentLabel.get(j), "");
						}
					}
					xmlBodyLeafNodeDataList.get(0).remove("agentId");
				}
				// 5.将报文体信息键值对放入Map
				xmlBodyNodeDataMap.put("eWealthContent", xmlBodyLeafNodeDataList);
				// 6.将报文体信息转化为XML格式字符串
				XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
				xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
				int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
				String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
				// 7.对约定好的key与报文体内容字符串进行MD5加密
				String secretKeyEncode = getKey();
				String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
				// ----------------------生成报文头字符串--------------------------------------------
				// 8.声明一个Map对象和一个Map对象
				Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
				Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
				// 9.将报文头所有叶子节点信息键值对放入Map集合中
				xmlLeafNodeDataMap.put("businessCode", "0001");
				xmlLeafNodeDataMap.put("systemCode", "fms");
				xmlLeafNodeDataMap.put("password", "");
				xmlLeafNodeDataMap.put("resultCode", "00");
				xmlLeafNodeDataMap.put("resultDesc", "success");
				xmlLeafNodeDataMap.put("signMsg", encryptionString);
				xmlLeafNodeDataMap.put("requestCode", "1");
				// 10.将报文头节点键值对放入Map集合中
				xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
				// -----------------------生成整个报文字符串------------------------------
				// 11.将报文头Map对象转为XML字符串
				XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
				// 12.将报文体信息Map对象转为XML字符串
				XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
				// 13.去除字符串中换行符
				xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
			} 
			else	
			{// 数据库未返回任何数据
			 //生成空报文
				return generateEmptyAgentInfoXml();
			}
		}
		message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
		return message;
	}
	
	/**
	 * 该私有方法属于接口：根据客户编号获取理财师信息
	 * 用于生成理财师信息空报文
	 * @author LIWENTAO
	 * @return
	 */
	private String generateEmptyAgentInfoXml() {
		// 返回信息字符串
		String xmlstringNoEnter = "";
		String message = "";
		// -------------------生成空报文----------------------------------------------
		//声明Document对象，Element对象，用于组建整个报文
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");
		//局部Document对象，Element对象仅用于将报文体信息转为XML格式字符串
		Document documentTemp = DocumentHelper.createDocument();
		Element rootElementTemp = documentTemp.addElement("");
		//新建一个Map对象和一个List对象
		Map<String, Object> xmlContentDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlContentLeafMap = new LinkedHashMap<String, Object>();
		//将空值信息添加到报文体内
		xmlContentLeafMap.put("agentCode", "");
		xmlContentLeafMap.put("agentName", "");
		xmlContentLeafMap.put("storeName", "");
		xmlContentLeafMap.put("province", "");
		xmlContentLeafMap.put("city", "");
		xmlContentLeafMap.put("area", "");
		xmlContentLeafMap.put("street", "");
		xmlContentLeafMap.put("agentGender", "");
		xmlContentLeafMap.put("telephone", "");
		xmlContentLeafMap.put("workingYears", "");
		xmlContentLeafMap.put("photoURL", "");
		//将报文体信息键值对放入Map
		xmlContentDataMap.put("eWealthContent", xmlContentLeafMap);
		//将报文体信息转化为XML格式字符串
		XmlUtils.mapToXml(xmlContentDataMap, rootElementTemp);
		xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
		int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
		String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
		//对约定好的key与报文体内容字符串进行MD5加密
		String secretKeyEncode1 = getKey();
		String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode1, messageContent);
		// ----------------------生成报文头字符串--------------------------------------------
		//新分配两个Map对象用于报文头映射
		Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
		//将报文头所有节点信息键值对放入Map集合中
		xmlLeafNodeDataMap.put("businessCode", "0001");
		xmlLeafNodeDataMap.put("systemCode", "fms");
		xmlLeafNodeDataMap.put("password", "");
		xmlLeafNodeDataMap.put("resultCode", "01");
		xmlLeafNodeDataMap.put("resultDesc", "false");
		xmlLeafNodeDataMap.put("signMsg", encryptionString);
		xmlLeafNodeDataMap.put("requestCode", "1");
		//将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		// -----------------------生成整个报文字符串------------------------------
		// 将报文头Map对象转为XML字符串
		XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
		// 将报文Map对象转为XML格式字符串
		XmlUtils.mapToXml(xmlContentDataMap, rootElement);
		xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
		message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
		return message;
	}

	/**
	 * 根据用户手机号与邀请码获取交易信息与邀请码状态
	 * 
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getTradeInfoAndInviteCodeStatus(String inviCodeAndCustCodeMessage) {
		// 请求报文requestXml存入数据库
		if (commonService.saveWebserviceLog("getTradeInfoAndInviteCodeStatus", "交易信息与邀请码状态查询接口", "", inviCodeAndCustCodeMessage, "01", "成功接收请求报文")) {
			log.info("请求报文成功存入数据库");
		} else {
			log.info("请求报文未成功存入数据库");
		}
		/*************** 第一模块：验证请求报文合法性 **************************/
		String secretKey = getKey(); // 存储密钥信息
		// 请求报文中MD5值 不同于 WebService端自己生成的MD5值
		try {
			if (!XmlUtils.checkEwealthXml(secretKey, inviCodeAndCustCodeMessage)) {
				log.info("请求报文自带MD5值与服务端生成MD5值不同");
				log.info("将要返回空报文");
				return generateEmptyTradeInfoResponseXml();
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			log.info("解析请求报文时出错");
			log.info("将要返回空报文");
			return generateEmptyTradeInfoResponseXml();
		}

		/******************** 第二模块：解析出请求报文中请求参数 **********************/
		// 解析请求报文为Map对象
		Map<String, Object> XmlDataMap = new HashMap<String, Object>();
		try {
			XmlUtils.parseXmlToMap(inviCodeAndCustCodeMessage, XmlDataMap);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Map<String, Object> parasInMessBodyMap = (Map<String, Object>) XmlDataMap.get("eWealthContent");
		// 声明存储待返回报文字符串
		String xmlstringNoEnter = "";
		String message = "";

		/********************* 第三模块：请求报文请求参数对象传入下一层 *******************/
		// 新建ResultInfo对象，接收QueryCustomerService返回的数据与状态标志
		// ResultInfo inviteCodeAndTradeInfo =
		// queryCustomerService.queryInviteCodeAndTradeInfo(parasInMessBodyMap);
		ResultInfo inviteCodeAndTradeInfo = productOrderService
				.getProductOrderInfoByMobileNumAndInviteCode(parasInMessBodyMap);

		/********************* 第四模块：依据下一层返回类对象中状态标志进行相应报文封装 *********/
		// 声明Document对象，Element对象，用于组建整个报文
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");

		if (inviteCodeAndTradeInfo.isSuccess()) {
			// -----组报文体信息----------------------------------------
			// a.声明局部Document对象，Element对象，仅用于将报文体信息转为XML格式字符串
			Document documentTemp = DocumentHelper.createDocument();
			Element rootElementTemp = documentTemp.addElement("");
			// b.新建一个Map对象和一个List对象
			Map<String, Object> xmlBodyOuterLabelMap = new LinkedHashMap<String, Object>();
			Map<String, Object> xmlBodyInnerLabelMap = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, String> pdAmountOrderInfo = (LinkedHashMap<String, String>) inviteCodeAndTradeInfo
					.getObj();
			// c.新建一个列表，存放待组装报文体标签名字
			if ("01".equals(pdAmountOrderInfo.get("orderState"))) {
				xmlBodyInnerLabelMap.put("InviteCodeIsValid", "Y");
				xmlBodyInnerLabelMap.put("ProductCode", pdAmountOrderInfo.get("ProductCode"));
				xmlBodyInnerLabelMap.put("TradeAmount", pdAmountOrderInfo.get("TradeAmount"));
				xmlBodyInnerLabelMap.put("AgentId", pdAmountOrderInfo.get("AgentId"));
			} else {
				xmlBodyInnerLabelMap.put("InviteCodeIsValid", "N");
				xmlBodyInnerLabelMap.put("ProductCode", pdAmountOrderInfo.get("ProductCode"));
				xmlBodyInnerLabelMap.put("TradeAmount", pdAmountOrderInfo.get("TradeAmount"));
				xmlBodyInnerLabelMap.put("AgentId", pdAmountOrderInfo.get("AgentId"));
			}
			// e.将报文体信息键值对放入Map
			xmlBodyOuterLabelMap.put("eWealthContent", xmlBodyInnerLabelMap);
			// f.将报文体信息转化为XML格式字符串
			XmlUtils.mapToXml(xmlBodyOuterLabelMap, rootElementTemp);
			xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
			int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
			String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
			// g.使用约定好的key对报文体内容字符串进行MD5加密
			String secretKeyEncode = getKey();
			String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
			// -----组报文头信息----------------------------------------
			// a.声明一个Map对象和一个Map对象
			Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
			Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
			// b.将报文头所有叶子节点信息键值对放入Map集合中
			xmlLeafNodeDataMap.put("businessCode", "0001");
			xmlLeafNodeDataMap.put("systemCode", "fms");
			xmlLeafNodeDataMap.put("password", "");
			xmlLeafNodeDataMap.put("resultCode", "00");
			xmlLeafNodeDataMap.put("resultDesc", "success");
			xmlLeafNodeDataMap.put("signMsg", encryptionString);
			xmlLeafNodeDataMap.put("requestCode", "1");
			// c.将报文头节点键值对放入Map集合中
			xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
			// -------------------------组建整个报文--------------------------------------
			// a.将报文头Map对象转为XML字符串
			XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
			// b.将报文体信息Map对象转为XML字符串
			XmlUtils.mapToXml(xmlBodyOuterLabelMap, rootElement);
			// c.去除字符串中换行符
			xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
			// --------------------返回报文字符串-------------
			System.out.println("-----程序执行完:EWealthServiceImpl层-----");
			message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
			return message;
		}

		if (!inviteCodeAndTradeInfo.isSuccess()) {
			log.info("未查询到任何信息,将要返回空报文");
			return generateEmptyTradeInfoResponseXml();
		}
		return "EWealthServiceImpl";
	}
	
	/**
	 * 该私有方法属于接口：根据用户手机号与邀请码获取交易信息与邀请码状态
	 * 用于生成交易信息空报文
	 * @author LIWENTAO
	 * @return
	 */
	private String generateEmptyTradeInfoResponseXml() {
		// -------------------组报文体信息--------------
		// 声明存储待返回报文字符串
		String xmlstringNoEnter = "";
		String message = "";
		// 声明Document对象，Element对象，用于组建整个报文
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");
		// a.声明局部Document对象，Element对象，仅用于将报文体信息转为xml格式字符串
		Document documentTemp = DocumentHelper.createDocument();
		Element rootElementTemp = documentTemp.addElement("");
		// b.新建两个Map对象存放报文体信息
		Map<String, Object> xmlContentDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlContentLeafMap = new LinkedHashMap<String, Object>();
		// c.将空值信息添加到报文体内
		xmlContentLeafMap.put("InviteCodeIsValid", "N");
		xmlContentLeafMap.put("ProductCode", "");
		xmlContentLeafMap.put("AgentId", "");
		xmlContentLeafMap.put("TradeAmount", "");
		// d.将报文体信息键值对放入Map
		xmlContentDataMap.put("eWealthContent", xmlContentLeafMap);
		// e.将报文体信息转化为XML格式字符串
		XmlUtils.mapToXml(xmlContentDataMap, rootElementTemp);
		xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
		int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
		String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
		// f.使用约定好的key对报文体内容字符串进行MD5加密
		String secretKeyEncode1 = getKey();
		String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode1, messageContent);
		// ---------------------组报文头信息----------------------
		// a.新建两个Map对象存放报文头信息键值对
		Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
		// b.将报文头所有节点信息键值对放入Map集合中
		xmlLeafNodeDataMap.put("businessCode", "0001");
		xmlLeafNodeDataMap.put("systemCode", "fms");
		xmlLeafNodeDataMap.put("password", "");
		xmlLeafNodeDataMap.put("resultCode", "01");
		xmlLeafNodeDataMap.put("resultDesc", "false");
		xmlLeafNodeDataMap.put("signMsg", encryptionString);
		xmlLeafNodeDataMap.put("requestCode", "1");
		// c.将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		// ---------------------------------组建整个报文------------------------------
		// 将报文头Map对象转为XML字符串
		XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
		// 将整个报文转为XML格式字符串
		XmlUtils.mapToXml(xmlContentDataMap, rootElement);
		xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
		// --------------------返回报文字符串-------------
		message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
		return message;
	}
	
	/**
	 * 将邀请码失效
	 * 
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String invalidateInviteCode(String requestXml) {
		// 请求报文requestXml存入数据库
		if (commonService.saveWebserviceLog("invalidateInviteCode", "将邀请码失效接口", "", requestXml, "01", "成功接收请求报文")) {
			log.info("请求报文成功存入数据库");
		} else {
			log.info("请求报文未成功存入数据库");
		}
		Map<String, Object> xmlDataMap = new HashMap<String, Object>();
		/**************************************************
		 * 第一模块：验证报文
		 **************************************************/
		// 1 验证报文类型，报文参数类型为返回报文直接返回错误信息

		// 2 验证MD5信息
		// 截取XML格式字符串中报文体部分
		int messageContentStartPos = requestXml.indexOf("<eWealthContent>");
		int lableStart = requestXml.indexOf("</eWealthContent>");
		if ((-1 == messageContentStartPos) || (-1 == lableStart)) {
			log.info("请求报文体报文体节点不存在并返回空报文");
			return generateChangeInvteCodeStatusEmptyXml();
		}
		//截取请求报文报文体字符串
		int messageContentEndPos = lableStart + ("</eWealthContent>".length());
		String xmlMessageContent = requestXml.substring(messageContentStartPos, messageContentEndPos);
		//输出请求报文报文体
		log.info("请求报文报文体字符串为:" + xmlMessageContent);
		String secretKey = getKey(); // 存储密钥信息
		String signMsgGeneratedMD5 = XmlUtils.md5EncodeRequestXmlBody(secretKey,xmlMessageContent);
		log.info("服务端将请求报文与密钥加密生成MD5值为：" + signMsgGeneratedMD5);
		// 解析请求报文字符到Map对象
		try {
			XmlUtils.parseXmlToMap(requestXml, xmlDataMap);
		} catch (DocumentException e) {
			e.printStackTrace();
			log.info("解析请求报文失败并返回空报文");
			return generateChangeInvteCodeStatusEmptyXml();
		}
		String Md5InMessHeader = null;
		try {
			Map<String, Object> headerMap = (HashMap<String, Object>) xmlDataMap.get("eWealthHeader");
			Md5InMessHeader = (String) headerMap.get("signMsg");
		} catch (NullPointerException e) {
			e.printStackTrace();
			log.info("获取参数失败并返回空报文");
			return generateChangeInvteCodeStatusEmptyXml();
		}
		// 请求报文中MD5值 不同于 WebService端自己生成的MD5值
		if (!Md5InMessHeader.equals(signMsgGeneratedMD5)) {
			log.info("MD5密文不匹配并返回空报文");
			return generateChangeInvteCodeStatusEmptyXml();
		}

		/*************************************
		 * 第二模块：解析出请求报文中请求参数
		 *************************************/
		Map<String, Object> parasInMessBodyMap = (Map<String, Object>) xmlDataMap.get("eWealthContent");

		/**********************************
		 * 第三模块：根据传入参数查询数据
		 *********************************/
		// 新建ResultInfo对象，接收QueryCustomerService返回的数据与状态标志
		Map<String, Object> inviteCodeMap = new LinkedHashMap<String, Object>();
		try {
			inviteCodeMap.put("inviteCode", parasInMessBodyMap.get("InviteCode"));
		} catch (Exception e) {
			e.printStackTrace();
			log.info("参数解析失败并返回空报文");
			return generateChangeInvteCodeStatusEmptyXml();
		}
		
		ResultInfo inviteCodeAndTradeInfo = productOrderService.getProductOrderInfoByInviteCode(inviteCodeMap);

		/******************* 第四模块：判断查询结果 *******************/
		// xmlBodyNodeDataMap对象存储报文体标签名与所有子标签Map集合
		Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
		// xmlInnerLabelMap对象存储报文子标签名称与值集合
		Map<String, Object> xmlInnerLabelMap = new LinkedHashMap<String, Object>();
		/*--------------组报文头------------------------------------------------*/
		Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
		// 将报文头所有节点信息键值对放入Map集合中
		xmlLeafNodeDataMap.put("businessCode", "0001");
		xmlLeafNodeDataMap.put("systemCode", "fms");
		xmlLeafNodeDataMap.put("password", "");
		xmlLeafNodeDataMap.put("resultCode", "00");
		xmlLeafNodeDataMap.put("resultDesc", "success");
		xmlLeafNodeDataMap.put("requestCode", "1");
		// 将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		/*-------------判断数据库查询返回状态值------------------------------------*/
		// 判断ResultInfo对象中状态值
		if (inviteCodeAndTradeInfo.isSuccess()) {
			// statusInRequestXml存储请求报文中订单状态信息
			String statusInRequestXml = null;
			try {
				statusInRequestXml = (String) parasInMessBodyMap.get("status");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ResultInfo对象存储更新数据库状态值
			ResultInfo resultInfo = new ResultInfo();
			String orderStateInDatabase = ((LinkedHashMap<String, String>) inviteCodeAndTradeInfo.getObj())
					.get("orderState");
			if ("01".equals(orderStateInDatabase)) {
				// 新建存储表修改者信息的LOGINFO对象
				LoginInfo loginInfo = new LoginInfo();
				loginInfo.setUserId(new Long(1));
				loginInfo.setComId(new Long(47));
				loginInfo.setUserCode("fms");
				// 新建对应"产品额度预约信息表"的JavaBean对象
				PDAmountOrderInfo udpdatePdAmountOrderInfo = new PDAmountOrderInfo();
				Long pdAmountOrderInfoId = (Long) ((Map) inviteCodeAndTradeInfo.getObj())
						.get("pdAmountOrderInfoId");
				udpdatePdAmountOrderInfo.setPdAmountOrderInfoId(pdAmountOrderInfoId);
				udpdatePdAmountOrderInfo.setOrderStatus(statusInRequestXml);
				// 判断预约状态值
				resultInfo = productOrderService.updatePdOrderStatus(udpdatePdAmountOrderInfo, loginInfo);
			}

			// 更新成功
			if (resultInfo.isSuccess()) {
				xmlInnerLabelMap.put("result", "true");
			} else {
				xmlInnerLabelMap.put("result", "false");
			}
			/* 将报文体信息键值对放入Map */
			xmlBodyNodeDataMap.put("eWealthContent", xmlInnerLabelMap);
			/** 生成报文 ***/
			// document,rootElement对象用于生成整个报文字符串
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("eWealthXml");
			// documentTemp,rootElementTemp对象用于生成报文体字符串
			Document documentTemp = DocumentHelper.createDocument();
			Element rootElementTemp = documentTemp.addElement("");
			// 存储报文字符串
			String xmlstringNoEnter = "";
			String message = "";
			// 将报文体信息转化为XML格式字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
			xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
			int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
			String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
			// 使用约定好的key对报文体内容字符串进行MD5加密
			String secretKeyEncode = getKey();
			String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
			// -----将MD5密文放入报文头-------------
			((Map<String, Object>) xmlHeadNodeDataMap.get("eWealthHeader")).put("signMsg", encryptionString);
			// 将报文头Map对象转为XML字符串
			XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
			// 将报文体信息Map对象转为XML字符串
			XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
			// 去除字符串中换行符
			xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
			System.out.println("-----程序执行完:EWealthServiceImpl层-----");
			message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
			return message;
		} else {
			return generateChangeInvteCodeStatusEmptyXml();
		}
	}

	/**
	 * 该私有方法属于接口：将邀请码失效
	 * 用于生成将邀请码失效状态信息空报文
	 * @author LIWENTAO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String generateChangeInvteCodeStatusEmptyXml() {
		// xmlBodyNodeDataMap对象存储报文体标签名与所有子标签Map集合
		Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
		/*--------------组报文头------------------------------------------------*/
		Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
		// 将报文头所有节点信息键值对放入Map集合中
		xmlLeafNodeDataMap.put("businessCode", "0001");
		xmlLeafNodeDataMap.put("systemCode", "fms");
		xmlLeafNodeDataMap.put("password", "");
		xmlLeafNodeDataMap.put("resultCode", "00");
		xmlLeafNodeDataMap.put("resultDesc", "success");
		xmlLeafNodeDataMap.put("requestCode", "1");
		// 将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		// 将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		/** 组报文体 **/
		// xmlContentLeafMap集合对象存放报文体信息
		Map<String, Object> xmlContentLeafMap = new LinkedHashMap<String, Object>();
		// c.将空值信息添加到报文体内
		xmlContentLeafMap.put("result", "false");
		// d.将报文体信息键值对放入Map
		xmlBodyNodeDataMap.put("eWealthContent", xmlContentLeafMap);
		/* 生成报文 */
		// 1声明Document对象，Element对象，用于组建整个报文
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");
		// -----组报文体信息----------------------------------------
		// a.声明局部Document对象，Element对象，仅用于将报文体信息转为XML格式字符串
		Document documentTemp = DocumentHelper.createDocument();
		Element rootElementTemp = documentTemp.addElement("");
		// 声明存储待返回报文字符串
		String xmlstringNoEnter = "";
		String message = "";
		// f.将报文体信息转化为XML格式字符串
		XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
		xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
		int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
		String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
		// g.使用约定好的key对报文体内容字符串进行MD5加密
		String secretKeyEncode = getKey();
		String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
		// -----将MD5密文放入报文头-------------
		((Map<String, Object>) xmlHeadNodeDataMap.get("eWealthHeader")).put("signMsg", encryptionString);
		// a.将报文头Map对象转为XML字符串
		XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
		// b.将报文体信息Map对象转为XML字符串
		XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
		// c.去除字符串中换行符
		xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
		System.out.println("-----程序执行完:EWealthServiceImpl层-----");
		message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
		return message;
	}
	/**
	 * 查看某理财师信息是否已经存在于核心系统中
	 * 
	 * @author LIWENTAO
	 * @param requestXml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String agentIsExistOrNot(String requestXml) {
		// 请求报文requestXml存入数据库
		if (commonService.saveWebserviceLog("agentIsExistOrNot", "理财师信息是否存在查询接口", "", requestXml, "01", "成功接收请求报文")) {
			log.info("请求报文成功存入数据库");
		} else {
			log.info("请求报文未成功存入数据库");
		}
		/*************** 第一模块：验证请求报文合法性 **************************/
		String secretKey = getKey(); // 存储密钥信息
		// 请求报文中MD5值 不同于 WebService端自己生成的MD5值
		try {
			if (!XmlUtils.checkEwealthXml(secretKey, requestXml)) {
				return "请求报文自带MD5值与服务端生成MD5值不同";
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/******************** 第二模块：解析出请求报文中请求参数 **********************/
		// 解析请求报文为Map对象
		Map<String, Object> XmlDataMap = new HashMap<String, Object>();
		try {
			XmlUtils.parseXmlToMap(requestXml, XmlDataMap);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Map<String, Object> parasInMessBodyMap = (Map<String, Object>) XmlDataMap.get("eWealthContent");
		/********************* 第三模块：请求报文请求参数对象传入下一层 *******************/
		// 新建ResultInfo对象，接收QueryCustomerService返回的数据与状态标志
		// ResultInfo inviteCodeAndTradeInfo =
		// queryCustomerService.queryInviteCodeAndTradeInfo(parasInMessBodyMap);
		ResultInfo agentIsExistInfo = queryCustomerService.obtainAgentIsExistInfo(parasInMessBodyMap);
		/******************* 第四模块：判断查询结果 *******************/
		// xmlBodyNodeDataMap对象存储报文体标签名与所有子标签Map集合
		Map<String, Object> xmlBodyNodeDataMap = new LinkedHashMap<String, Object>();
		// xmlInnerLabelMap对象存储报文子标签名称与值集合
		Map<String, Object> xmlInnerLabelMap = new LinkedHashMap<String, Object>();
		/*--------------组报文头------------------------------------------------*/
		Map<String, Object> xmlHeadNodeDataMap = new LinkedHashMap<String, Object>();
		Map<String, Object> xmlLeafNodeDataMap = new LinkedHashMap<String, Object>();
		// 将报文头所有节点信息键值对放入Map集合中
		xmlLeafNodeDataMap.put("businessCode", "0001");
		xmlLeafNodeDataMap.put("systemCode", "fms");
		xmlLeafNodeDataMap.put("password", "");
		xmlLeafNodeDataMap.put("resultCode", "00");
		xmlLeafNodeDataMap.put("resultDesc", "success");
		xmlLeafNodeDataMap.put("requestCode", "1");
		// 将报文头节点键值对放入Map集合中
		xmlHeadNodeDataMap.put("eWealthHeader", xmlLeafNodeDataMap);
		// -------------------------------------------------
		if (agentIsExistInfo.isSuccess()) {
			// 组报文
			xmlInnerLabelMap.put("result", "true");

		} else {
			if (agentIsExistInfo.getMsg().equals("error")) {
				xmlInnerLabelMap.put("result", "error");
			} else {
				xmlInnerLabelMap.put("result", "false");
			}
		}
		/* 将报文体信息键值对放入Map */
		xmlBodyNodeDataMap.put("eWealthContent", xmlInnerLabelMap);
		/** 生成报文 ***/
		// 声明Document对象，Element对象，用于组建整个报文
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("eWealthXml");
		// -----组报文体信息----------------------------------------
		// 声明局部Document对象，Element对象，仅用于将报文体信息转为XML格式字符串
		Document documentTemp = DocumentHelper.createDocument();
		Element rootElementTemp = documentTemp.addElement("");
		// 声明存储待返回报文字符串
		String xmlstringNoEnter = "";
		String message = "";
		// 将报文体信息转化为XML格式字符串
		XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElementTemp);
		xmlstringNoEnter = documentTemp.asXML().replaceAll("[\n]", "");
		int stringHeaderSize = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><>".length();
		String messageContent = xmlstringNoEnter.substring(stringHeaderSize, xmlstringNoEnter.length() - 3);
		// g.使用约定好的key对报文体内容字符串进行MD5加密
		String secretKeyEncode = getKey();
		String encryptionString = XmlUtils.md5EncodeRequestXmlBody(secretKeyEncode, messageContent);
		// -----将MD5密文放入报文头-------------
		((Map<String, Object>) xmlHeadNodeDataMap.get("eWealthHeader")).put("signMsg", encryptionString);
		// a.将报文头Map对象转为XML字符串
		XmlUtils.mapToXml(xmlHeadNodeDataMap, rootElement);
		// b.将报文体信息Map对象转为XML字符串
		XmlUtils.mapToXml(xmlBodyNodeDataMap, rootElement);
		// c.去除字符串中换行符
		xmlstringNoEnter = document.asXML().replaceAll("[\n]", "");
		System.out.println("-----程序执行完:EWealthServiceImpl层-----");
		message = xmlstringNoEnter.substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".length());
		return message;
	}
}
