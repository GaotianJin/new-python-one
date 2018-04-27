package com.fms.controller.product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fms.db.model.CustBaseInfo;
import com.fms.db.model.PDContract;
import com.fms.db.model.PDFactor;
import com.fms.db.model.PDProduct;
import com.fms.db.model.PDRisk;
import com.fms.db.model.PDRiskFeeRate;
import com.fms.db.model.PDRiskIntroduceInfo;
import com.fms.db.model.PDRiskSalesInfo;
import com.fms.db.model.PDWealth;
import com.fms.db.model.PDWealthChargeRate;
import com.fms.db.model.PDWealthFeeRate;
import com.fms.db.model.PDWealthIntroduceInfo;
import com.fms.db.model.PDWealthNetValue;
import com.fms.db.model.PDWealthSalesInfo;
import com.fms.db.model.PDWealthStockDis;
import com.fms.db.model.PDwealthIncomeDis;
import com.fms.db.model.PdAmountDisInfo;
import com.fms.db.model.SysSmsBatch;
import com.fms.service.product.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.ExcelTool;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.NumberUtils;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;


@Controller
@RequestMapping("/product")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class ProductController {
	@SuppressWarnings("unused")
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger log = Logger.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
/*	@Autowired
	private  EWealthClientService  eWealthClientService;*/
	
	/**
	 * 获取产品设置初始页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listProductDefUrl", method = RequestMethod.GET)
	public String listProductDefUrl(Model model) {
		return "fms/product/listProductDef";
	}
	/**
	 * 获取产品额度分配初始页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productAmountDistribute", method = RequestMethod.GET)
	public String productAmountDestribute(Model model) {
		return "fms/product/productAmountDistribute";
	}
	
	/**
	 * 产品额度分配提交页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/addProductAmountDis", method = RequestMethod.GET)
	public ModelAndView addProductAmountDis(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/addProductAmountDis",reqParamMap);
	}
	
	/**
	 * 根据codeType查找数据
	 * @param codeType
	 * @return
	 */
	@RequestMapping(value = "/queryProductFactor", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<String, String>> queryProductFactor(String codeType){
		List<Map<String, String>> codeMapList = productService.queryProductFactor(codeType);
		return codeMapList;
	}

	/**
	 * 查询产品信息显示初始信息列表
	 * @param dgm
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryListProductUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListProductUrl(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		param = JsonUtils.decodeUrlParams(param, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
		dataGrid = productService.getPageList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}

	
	
	/**
	 * 查询浮动类产品信息列表
	 * @param dgm
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchFloatProductNetValueUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid searchFloatProductNetValue(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		param = JsonUtils.decodeUrlParams(param, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
		dataGrid = productService.searchFloatProductNetValue(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 查询浮动类产品净值列表
	 * @param dgm
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/searchNetValueUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid searchNetValue(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		//param = JsonUtils.decodeUrlParams(param, "utf-8");
		//paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
		paramMap.put("productId", param);	
		}
		dataGrid = productService.searchNetValue(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 获取新增产品信息列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addProductDefUrl", method = RequestMethod.GET)
	public String addProductDefUrl(Model model) {
		return "fms/product/addProductDef";
	}

	/**
	 * 获取产品净值查询信息列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listProductNetValueUrl", method = RequestMethod.GET)
	public String listProductNetValueUrl(Model model) {
		return "fms/product/listProductNetValue";
	}
	
	/**
	 * 获取产品净值信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getProductNetValue", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getProductNetValue(String param, ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		Map paramMap = new HashMap();
		try {
			paramMap.put("productId", param);
		// 将组装好的Map传给后台,进行数据保存
		resultInfo= productService.getProductNetValue(paramMap, loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("操作成功！");
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取新增产品副文本编辑页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/addProductEditorUrl", method = RequestMethod.GET)
	public ModelAndView addProductEditorUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/addProductEditor",reqParamMap);
	}
	
	/**
	 * 新增产品基本信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/addProductBasicInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addProductBasicInfoUrl(String param, ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		Map tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String productBaseInfo = JsonUtils.getJsonValueByKey("productBaseInfo", param);
		String existProductId = JsonUtils.getJsonValueByKey("productId", param);
		PDProduct pDProductSchema = new PDProduct();
		pDProductSchema = (PDProduct) JsonUtils.jsonStrToObject(productBaseInfo, pDProductSchema);
		tMap.put("pDProductSchema", pDProductSchema);
		tMap.put("productId", existProductId);
		// 将组装好的Map传给后台,进行数据保存
		String productId = productService.addPrdocutBasicInfo(tMap, loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("操作成功！");
		resultInfo.setObj(productId);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 获取产品核心信息页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/addProductCoreInfoUrl", method = RequestMethod.GET)
	public ModelAndView addProductCoreInfoUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/addProductCoreDef",reqParamMap);
	}
	
	/**
	 * 保存产品核心信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/submitProductCoreInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitProductCoreInfoUrl(@RequestParam Map paramMap, ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		Map tMap = new HashMap();
		try {
			String param = paramMap.containsKey("param")?(String)paramMap.get("param"):"";
			String existProductId = JsonUtils.getJsonValueByKey("existProductId", param);
			String productType = JsonUtils.getJsonValueByKey("existProductType", param);
			String productSubType = JsonUtils.getJsonValueByKey("existProductSubType", param);
			String existSucFlag = JsonUtils.getJsonValueByKey("existSucFlag", param);
			tMap.put("productId", existProductId);
			tMap.put("productType", productType);
			tMap.put("productSubType", productSubType);
			tMap.put("sucFlag", existSucFlag);
			/** 产品类型为1-财富产品 2-保险产品 **/
			if (productType != null && productSubType != null) {
				if (productType.equals("1")) {
					/*String wealthProductInfo = JsonUtils.getJsonValueByKey("wealthProductInfo", param);
					wealthProductInfo = JsonUtils.decodeUrlParams(wealthProductInfo, "utf-8");
					PDWealth pDWealthSchema = (PDWealth) JsonUtils.jsonStrToObject(wealthProductInfo, PDWealth.class);*/
					PDWealth pDWealthSchema = (PDWealth)JsonUtils.mapToObject(paramMap, PDWealth.class);
					if (pDWealthSchema.getFinancingScale()!=null) {
						pDWealthSchema.setFinancingScale((new BigDecimal(NumberUtils.multiply(pDWealthSchema.getFinancingScale().toString(), "10000"))));
					}
			/*		if (pDWealthSchema.getHistoryEarnrate()!=null&&pDWealthSchema.getHistoryEarnrate().contains("#")) {
						pDWealthSchema.setHistoryEarnrate(pDWealthSchema.getHistoryEarnrate().replace("#", "%"));
					}*/
					tMap.put("pDWealthSchema", pDWealthSchema);// 放入财富基本信息
					if (productSubType.equals("02")) {
						String wealthRateTableInfoStr = paramMap.containsKey("wealthRateTableInfo")?(String)paramMap.get("wealthRateTableInfo"):"";
						String wealthRateTableInfo = JsonUtils.decodeUrlParams(wealthRateTableInfoStr,"utf-8");
						List<PDWealthFeeRate> WealthFeeRateInfolist=com.sinosoft.util.JsonUtils.jsonArrStrToList(wealthRateTableInfo, PDWealthFeeRate.class);
						tMap.put("WealthFeeRateInfolist", WealthFeeRateInfolist);
						// 放入固定类费用比例信息
						PDWealthChargeRate pDWealthChargeRateSchema = (PDWealthChargeRate)JsonUtils.mapToObject(paramMap, PDWealthChargeRate.class);
						tMap.put("pDWealthChargeRate", pDWealthChargeRateSchema);
						// 放入固定类分类信息
						String wealthIncomeDisInfoData = paramMap.containsKey("wealthIncomeDisInfoData")?(String)paramMap.get("wealthIncomeDisInfoData"):"";
						List<PDwealthIncomeDis> pdwealthIncomeDisList = JsonUtils.jsonArrStrToList(wealthIncomeDisInfoData, PDwealthIncomeDis.class);
						tMap.put("pdwealthIncomeDisList", pdwealthIncomeDisList);// 放入固定收益类收益分配信息
				}else {
					if (productSubType.equals("01")) {
						String wealthStockDisInfoData = paramMap.containsKey("wealthStockDisInfoData")?(String)paramMap.get("wealthStockDisInfoData"):"";
						List<PDWealthStockDis> pdwealthStockDisList = JsonUtils.jsonArrStrToList(wealthStockDisInfoData, PDWealthStockDis.class);
						tMap.put("pdwealthStockDisList", pdwealthStockDisList);// 放入固定收益类收益分配信息
						}
					PDWealthChargeRate pDWealthChargeRateSchema = (PDWealthChargeRate)JsonUtils.mapToObject(paramMap, PDWealthChargeRate.class);
					tMap.put("pDWealthChargeRate", pDWealthChargeRateSchema);// 放入浮动、股权类例信息
				}
		}
		// 获取录入信息 PDFactor
			String factorInfoTableInfoStr = paramMap.containsKey("factorInfoTableInfo")?(String)paramMap.get("factorInfoTableInfo"):"";
			String factorInfoTableInfo = JsonUtils.decodeUrlParams(factorInfoTableInfoStr,"utf-8");
			List<PDFactor> factorInfolist = JsonUtils.jsonArrStrToList(factorInfoTableInfo, PDFactor.class);
			tMap.put("factorInfolist", factorInfolist);// 放入录入信息
		}
		// 将组装好的Map传给后台,进行数据保存
		String sucFlag = productService.addPrdocutCoreInfo(tMap, loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("操作成功！");
		resultInfo.setObj(sucFlag);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
			}
		return resultInfo;
	}
	
	/**
	 * 获取网销产品信息页面-
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/addProductInternetInfoUrl", method = RequestMethod.GET)
	public ModelAndView addProductInternetInfoUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/addProductInternetDef",reqParamMap);
	}
	
	/**
	 * 保存互联网产品信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/submitProductInternetInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitProductInternetInfoUrl(String param, ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		Map tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String existProductId = JsonUtils.getJsonValueByKey("productId", param);
		String productType = JsonUtils.getJsonValueByKey("productType", param);
		String productSubType = JsonUtils.getJsonValueByKey("productSubType", param);
		String existProductInternetInfoId = JsonUtils.getJsonValueByKey("productInternetInfoId", param);
		String existSucFlag = JsonUtils.getJsonValueByKey("internetInfoSucFlag", param);
		PDWealthIntroduceInfo PDWealthIntroduceInfo = new PDWealthIntroduceInfo();// 财富产品说明信息
		PDWealthIntroduceInfo pdWealthIntroductSEOInfo = new PDWealthIntroduceInfo();// 财富产品说明信息SEO说明信息
		PDWealthSalesInfo pdWealthSalesInfo = new PDWealthSalesInfo();// 财富产品营销信息
		PDRiskIntroduceInfo PDRiskIntroduceInfo = new PDRiskIntroduceInfo();// 保险产品说明信息
		PDRiskIntroduceInfo pdRiskIntroductSEOInfo = new PDRiskIntroduceInfo();// 保险产品SEO说明信息
		PDRiskSalesInfo pdRiskSalesInfo = new PDRiskSalesInfo();// 保险产品营销信息
		
		if (existProductId != null && productType != null && productSubType != null) {
		if (productType.equals("1")) {
		if (productSubType.equals("02")) {
		String pdIntenetGDWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetGDWealthDesc", param);
		pdIntenetGDWealthDesc = JsonUtils.decodeUrlParams(pdIntenetGDWealthDesc, "utf-8");
		PDWealthIntroduceInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetGDWealthDesc,PDWealthIntroduceInfo);// 固定类产品营销信息
		tMap.put("PDWealthIntroduceInfo", PDWealthIntroduceInfo);
		
		String pdIntenetGDSEOWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetGDSEOWealthDesc", param);
		pdIntenetGDSEOWealthDesc = JsonUtils.decodeUrlParams(pdIntenetGDSEOWealthDesc, "utf-8");
		pdWealthIntroductSEOInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetGDSEOWealthDesc,PDWealthIntroduceInfo);// 固定类产品营销信息
		tMap.put("pdWealthIntroductSEOInfo", pdWealthIntroductSEOInfo);
		
		} else {
		String pdIntenetFDWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetFDWealthDesc", param);
		pdIntenetFDWealthDesc = JsonUtils.decodeUrlParams(pdIntenetFDWealthDesc, "utf-8");
		PDWealthIntroduceInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDWealthDesc,PDWealthIntroduceInfo);
		tMap.put("PDWealthIntroduceInfo", PDWealthIntroduceInfo);
		
		String pdIntenetFDSEOWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetFDSEOWealthDesc", param);
		pdIntenetFDSEOWealthDesc = JsonUtils.decodeUrlParams(pdIntenetFDSEOWealthDesc, "utf-8");
		pdWealthIntroductSEOInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDSEOWealthDesc,PDWealthIntroduceInfo);// 固定类产品营销信息
		tMap.put("pdWealthIntroductSEOInfo", pdWealthIntroductSEOInfo);

		}
		String ProductSaleSDesc = JsonUtils.getJsonValueByKey("ProductSaleSDesc", param);
		ProductSaleSDesc = JsonUtils.decodeUrlParams(ProductSaleSDesc, "utf-8");
		pdWealthSalesInfo = (PDWealthSalesInfo) JsonUtils.jsonStrToObject(ProductSaleSDesc,pdWealthSalesInfo);// 固定类产品营销信息
		tMap.put("pdWealthSalesInfo", pdWealthSalesInfo);
		}
		else 
		{
		String pdIntenetFDRiskDesc = JsonUtils.getJsonValueByKey("pdIntenetFDRiskDesc", param);
		pdIntenetFDRiskDesc = JsonUtils.decodeUrlParams(pdIntenetFDRiskDesc, "utf-8");
		PDRiskIntroduceInfo = (PDRiskIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDRiskDesc,PDRiskIntroduceInfo);
		tMap.put("PDRiskIntroduceInfo", PDRiskIntroduceInfo);
		
		String ProductSaleSDesc = JsonUtils.getJsonValueByKey("ProductSaleSDesc", param);
		ProductSaleSDesc = JsonUtils.decodeUrlParams(ProductSaleSDesc, "utf-8");
		pdRiskSalesInfo = (PDRiskSalesInfo) JsonUtils.jsonStrToObject(ProductSaleSDesc, pdRiskSalesInfo);// 保险类产品营销信息
		tMap.put("pdRiskSalesInfo", pdRiskSalesInfo);
		
		String pdIntenetFDSEORiskDesc = JsonUtils.getJsonValueByKey("pdIntenetFDSEORiskDesc", param);
		pdIntenetFDSEORiskDesc = JsonUtils.decodeUrlParams(pdIntenetFDSEORiskDesc, "utf-8");
		pdRiskIntroductSEOInfo = (PDRiskIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDSEORiskDesc, PDRiskIntroduceInfo);// 保险类SEO信息
		tMap.put("pdRiskIntroductSEOInfo", pdRiskIntroductSEOInfo);
		
		}
		} else {
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品类型相关信息失败");
		return resultInfo;
		}
		tMap.put("productType", productType);
		tMap.put("productSubType", productSubType);
		tMap.put("productId", existProductId);
		tMap.put("sucFlag", existSucFlag);
		// 将组装好的Map传给后台,进行数据保存
		String internetInfoSucFlag = productService.addPrdocutInternetInfo(tMap, loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("操作成功！");
		resultInfo.setObj(internetInfoSucFlag);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 新增产品提交审核动作
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productSubmitAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo productSubmitAudit(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo) model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try {
		productService.submitAudit(param, loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品提交审核成功！");	
		
/*		if (resultInfo.isSuccess()) {
			resultInfo.setSuccess(true);
			resultInfo.setMsg("产品提交审核成功！");	
		}
		else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("产品提交审核shiba！");	
			return resultInfo;	
		}
		*/
		}/*catch(IndexOutOfBoundsException i){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("产品重复提交!" );
			i.printStackTrace();
		}*/
		catch (Exception e) {
		resultInfo.setSuccess(false);
		resultInfo.setMsg("产品提交审核失败：" + e.getMessage());
		e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取产品基本信息修改页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/updateProductDefUrl", method = RequestMethod.GET)
	public ModelAndView updateProductDefUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/updateProductDef",reqParamMap);
	}
	
	/**
	 * 获取产品基本信息修改信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getUpdateProductBasicInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getUpdateProductBasicInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取修改的产品信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品详细信息出错");
		return resultInfo;
		}
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		resultInfo = productService.queryProductBasicInfo(param, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品详细信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 保存产品修改的基本信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/updateProductBasicInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateProductBasicInfoUrl(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		/**获取需要修改的产品相关信息 modifyProduct：产品Id modifyProductType:产品类型Code modifyProductSub：产品子类Code**/
		String modifyProductId=JsonUtils.getJsonValueByKey("modifyBasicProductId", param);
		String modifyProductType=JsonUtils.getJsonValueByKey("modifyBasicProductType", param);
		String modifyProductSubType=JsonUtils.getJsonValueByKey("modifyBasicProductSubType", param);
		String modifyProductStatus=JsonUtils.getJsonValueByKey("modifyBasicProductStatus", param);
		tMap.put("modifyProductId", modifyProductId);
		tMap.put("modifyProductType", modifyProductType);
		tMap.put("modifyProductSubType", modifyProductSubType);
		tMap.put("modifyProductStatus", modifyProductStatus);
		/***获取已经修改的产品基本信息****/
		String productBaseInfo = JsonUtils.getJsonValueByKey("productBaseInfo", param);
		PDProduct pDProductSchema = gson.fromJson(productBaseInfo,PDProduct.class);
		tMap.put("pDProductSchema", pDProductSchema);
	    String productId=productService.updatePrdocutBasicInfo(tMap,loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("操作成功！");
		resultInfo.setObj(productId);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 获取网销产品信息修改页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/updateProductInternetInfoUrl", method = RequestMethod.GET)
	public ModelAndView updateProductInternetInfoUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/updateProductInternetDef",reqParamMap);
	}
	
	/**
	 * 获取网销产品信息页面的修改信息初始化
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/getUpdateProductInternetInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getUpdateProductInternetInfo(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String productId=JsonUtils.getJsonValueByKey("modifyInternetProductId", param);
		String productTypeValue=JsonUtils.getJsonValueByKey("modifyInternetProductType", param);
		String productSubTypeValue=JsonUtils.getJsonValueByKey("modifyInternetProductSubType", param);
		tMap.put("modifyProductId", productId);
		tMap.put("modifyProductType", productTypeValue);
		tMap.put("modifyProductSubType", productSubTypeValue);
		resultInfo=productService.queryProductInternetInfo(tMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 修改互联网产品信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/saveUpdateProductInternetInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveUpdateProductInternetInfoUrl(String param, ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		Map tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String existProductId = JsonUtils.getJsonValueByKey("modifyInternetProductId", param);
		String productType = JsonUtils.getJsonValueByKey("modifyInternetProductType", param);
		String productSubType = JsonUtils.getJsonValueByKey("modifyInternetProductSubType", param);
		PDWealthIntroduceInfo PDWealthIntroduceInfo = new PDWealthIntroduceInfo();// 财富产品说明信息
		PDWealthIntroduceInfo pdWealthSEOIntroductInfo = new PDWealthIntroduceInfo();// 财富产品说明信息
		PDWealthSalesInfo pdWealthSalesInfo = new PDWealthSalesInfo();// 财富产品营销信息
		
		PDRiskIntroduceInfo PDRiskIntroduceInfo = new PDRiskIntroduceInfo();// 保险产品说明信息
		PDRiskIntroduceInfo pdRiskSEOIntroductInfo = new PDRiskIntroduceInfo();// 保险产品SEO说明信息
		PDRiskSalesInfo pdRiskSalesInfo = new PDRiskSalesInfo();// 保险产品营销信息
		if (existProductId != null && productType != null && productSubType != null) {
		if (productType.equals("1")) {
		if (productSubType.equals("02")) {
		String pdIntenetGDWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetGDWealthDesc", param);
		pdIntenetGDWealthDesc = JsonUtils.decodeUrlParams(pdIntenetGDWealthDesc,"utf-8");
		PDWealthIntroduceInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetGDWealthDesc,PDWealthIntroduceInfo);// 固定类产品营销信息
		tMap.put("PDWealthIntroduceInfo", PDWealthIntroduceInfo);
		
		String pdIntenetGDSEOWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetGDSEOWealthDesc", param);
//		pdIntenetGDSEOWealthDesc = JsonUtils.decodeUrlParams(pdIntenetGDSEOWealthDesc,"utf-8");
		pdWealthSEOIntroductInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetGDSEOWealthDesc,PDWealthIntroduceInfo);// 固定类产品营销信息
		tMap.put("pdWealthSEOIntroductInfo", pdWealthSEOIntroductInfo);
		
		} else {
			
		String pdIntenetFDWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetFDWealthDesc", param);
		pdIntenetFDWealthDesc = JsonUtils.decodeUrlParams(pdIntenetFDWealthDesc,"utf-8");
		PDWealthIntroduceInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDWealthDesc,PDWealthIntroduceInfo);
		tMap.put("PDWealthIntroduceInfo", PDWealthIntroduceInfo);
		
		String pdIntenetFDSEOWealthDesc = JsonUtils.getJsonValueByKey("pdIntenetFDSEOWealthDesc", param);
//		pdIntenetFDSEOWealthDesc = JsonUtils.decodeUrlParams(pdIntenetFDSEOWealthDesc,"utf-8");
		pdWealthSEOIntroductInfo = (PDWealthIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDSEOWealthDesc,PDWealthIntroduceInfo);
		tMap.put("pdWealthSEOIntroductInfo", pdWealthSEOIntroductInfo);
		}
		String ProductSaleSDesc = JsonUtils.getJsonValueByKey("ProductSaleSDesc", param);
		ProductSaleSDesc = JsonUtils.decodeUrlParams(ProductSaleSDesc,"utf-8");
		pdWealthSalesInfo = (PDWealthSalesInfo) JsonUtils.jsonStrToObject(ProductSaleSDesc,pdWealthSalesInfo);// 固定类产品营销信息
		tMap.put("pdWealthSalesInfo", pdWealthSalesInfo);
		}
		else 
		{
		String pdIntenetFDRiskDesc = JsonUtils.getJsonValueByKey("pdIntenetFDRiskDesc", param);
		pdIntenetFDRiskDesc = JsonUtils.decodeUrlParams(pdIntenetFDRiskDesc,"utf-8");
		PDRiskIntroduceInfo = (PDRiskIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDRiskDesc,PDRiskIntroduceInfo);
		tMap.put("PDRiskIntroduceInfo", PDRiskIntroduceInfo);
		
		String pdIntenetFDSEORiskDesc = JsonUtils.getJsonValueByKey("pdIntenetFDSEORiskDesc", param);
		pdIntenetFDSEORiskDesc = JsonUtils.decodeUrlParams(pdIntenetFDSEORiskDesc,"utf-8");
		pdRiskSEOIntroductInfo = (PDRiskIntroduceInfo) JsonUtils.jsonStrToObject(pdIntenetFDSEORiskDesc,PDRiskIntroduceInfo);
		tMap.put("pdRiskSEOIntroductInfo", pdRiskSEOIntroductInfo);
		
		String ProductSaleSDesc = JsonUtils.getJsonValueByKey("ProductSaleSDesc", param);
		ProductSaleSDesc = JsonUtils.decodeUrlParams(ProductSaleSDesc,"utf-8");
		pdRiskSalesInfo = (PDRiskSalesInfo) JsonUtils.jsonStrToObject(ProductSaleSDesc, pdRiskSalesInfo);// 固定类产品营销信息
		tMap.put("pdRiskSalesInfo", pdRiskSalesInfo);
		}
		} else {
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品类型相关信息失败");
		return resultInfo;
		}
		tMap.put("productType", productType);
		tMap.put("productSubType", productSubType);
		tMap.put("productId", existProductId);
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = productService.SaveUpdatePrdocutInternetInfo(tMap, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}

	
	/**
	 * 获取产品核心信息修改页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/updateProductCoreInfoUrl", method = RequestMethod.GET)
	public ModelAndView updateProductCoreInfoUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/updateProductCoreDef",reqParamMap);
	}
	
	
	/**
	 * 获取产品核心信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/queryProductCoreInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryProductCoreInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取修改的产品信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品详细信息出错");
		return resultInfo;
		}
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		resultInfo = productService.queryProductCoreInfo(param, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品详细信息出错");
		}
		return resultInfo;
	}
	
	
	/**
	 * 修改产品信息操作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/submitUpdateProductCoreInfoUrl1", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateProductCoreInfoUrl1(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//解码 由于%的缘故
		param = JsonUtils.decodeUrlParams(param,"utf-8");
		/**获取需要修改的产品相关信息 modifyProduct：产品Id modifyProductType:产品类型Code modifyProductSub：产品子类Code**/
		String modifyProductId=JsonUtils.getJsonValueByKey("modifyProductId", param);
		String modifyProductType=JsonUtils.getJsonValueByKey("modifyProductType", param);
		String modifyProductSubType=JsonUtils.getJsonValueByKey("modifyProductSubType", param);
		String modifyProductStatus=JsonUtils.getJsonValueByKey("modifyProductStatus", param);
		tMap.put("modifyProductId", modifyProductId);
		tMap.put("modifyProductType", modifyProductType);
		tMap.put("modifyProductSubType", modifyProductSubType);
		tMap.put("modifyProductStatus", modifyProductStatus);
		/***获取已经修改并的产品信息****/
		// 产品类型为1-财富产品 2-保险产品
		if (modifyProductType.equals("1")) {
		// 财富产品信息
		String wealthProductInfo = JsonUtils.getJsonValueByKey("wealthProductInfo", param);
		wealthProductInfo = JsonUtils.decodeUrlParams(wealthProductInfo,"utf-8");
		PDWealth pDWealthSchema = (PDWealth)JsonUtils.jsonStrToObject(wealthProductInfo, PDWealth.class);
		if (pDWealthSchema.getFinancingScale()!=null) {
		pDWealthSchema.setFinancingScale((new BigDecimal(NumberUtils.multiply(pDWealthSchema.getFinancingScale().toString(), "10000"))));
		}
/*		
		if (pDWealthSchema.getHistoryEarnrate()!=null&&pDWealthSchema.getHistoryEarnrate().contains("#")) {
			pDWealthSchema.setHistoryEarnrate(pDWealthSchema.getHistoryEarnrate().replace("#", "%"));
		}*/
		
		tMap.put("pDWealthSchema", pDWealthSchema);// 放入财富基本信息
		// 固定类产品
		if (modifyProductSubType.equals("02")) {
		// 获取固定类产品费用比例表
		String wealthRateTableInfo = JsonUtils.getJsonValueByKey("wealthRateTableInfo", param);
		List<PDWealthFeeRate> WealthFeeRateInfolist=com.sinosoft.util.JsonUtils.jsonArrStrToList(wealthRateTableInfo, PDWealthFeeRate.class);
		tMap.put("WealthFeeRateInfolist", WealthFeeRateInfolist);// 放入固定类费用比例信息
		// 固定收益分类信息 gdiffrentInfo
		String gdiffrentInfo = JsonUtils.getJsonValueByKey("gdiffrentInfo", param);
		PDWealthChargeRate pDWealthChargeRateSchema = gson.fromJson(gdiffrentInfo,PDWealthChargeRate.class);
		tMap.put("pDWealthChargeRate", pDWealthChargeRateSchema);// 放入固定类分类信息
		// 固定收益类收益分配信息
		String wealthIncomeDisInfoData = JsonUtils.getJsonValueByKey("wealthIncomeDisInfoData", param);
		List<PDwealthIncomeDis> pdwealthIncomeDisList = JsonUtils.jsonArrStrToList(wealthIncomeDisInfoData, PDwealthIncomeDis.class);
		tMap.put("pdwealthIncomeDisList", pdwealthIncomeDisList);// 放入固定收益类收益分配信息
		}
		else {
			if (modifyProductSubType.equals("01")) {
				// 股权收益类收益分配信息
				String wealthStockDisInfoData = JsonUtils.getJsonValueByKey("wealthStockDisInfoData", param);
				List<PDWealthStockDis> pdwealthStockDisList = JsonUtils.jsonArrStrToList(wealthStockDisInfoData, PDWealthStockDis.class);
				tMap.put("pdwealthStockDisList", pdwealthStockDisList);// 放入固定收益类收益分配信息
			}
		// 获取浮动股权类分类信息fdiffrentInfo
		String fdiffrentInfo = JsonUtils.getJsonValueByKey("fdiffrentInfo", param);
		PDWealthChargeRate pDWealthChargeRateSchema = gson.fromJson(fdiffrentInfo,PDWealthChargeRate.class);
		tMap.put("pDWealthChargeRate", pDWealthChargeRateSchema);// 放入浮动、股权类例信息
		}} 
		else 
		{
		// 保险产品
		String insuranceInfo = JsonUtils.getJsonValueByKey("insuranceInfo", param);
		PDRisk pDRiskShcema = gson.fromJson(insuranceInfo,PDRisk.class);
		tMap.put("pDRiskShcema", pDRiskShcema);// 保存保险基本信息
		// 获取保险产品费用比例表
		String insuraceRateTableInfo = JsonUtils.getJsonValueByKey("insuraceRateTableInfo", param);
		List<PDRiskFeeRate> insuraceRateInfolist=com.sinosoft.util.JsonUtils.jsonArrStrToList(insuraceRateTableInfo, PDRiskFeeRate.class);
		tMap.put("insuraceRateInfolist", insuraceRateInfolist);// 放入保险类费用比例信息
		}
		// 获取录入信息 PDFactor
		String factorInfoTableInfo = JsonUtils.getJsonValueByKey("factorInfoTableInfo", param);
		List<PDFactor> factorInfolist = gson.fromJson(factorInfoTableInfo, new TypeToken<List<PDFactor>>() {}.getType());
		tMap.put("factorInfolist", factorInfolist);// 放入录入信息
		// 将组装好的Map传给后台,进行数据保存
		resultInfo =productService.updatePrdocutCoreInfo(tMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/submitUpdateProductCoreInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateProductCoreInfoUrl(@RequestParam Map paramMap,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
			String param = paramMap.containsKey("param")?(String)paramMap.get("param"):"";
			/**获取需要修改的产品相关信息 modifyProduct：产品Id modifyProductType:产品类型Code modifyProductSub：产品子类Code**/
			String modifyProductId=JsonUtils.getJsonValueByKey("modifyProductId", param);
			String modifyProductType=JsonUtils.getJsonValueByKey("modifyProductType", param);
			String modifyProductSubType=JsonUtils.getJsonValueByKey("modifyProductSubType", param);
			String modifyProductStatus=JsonUtils.getJsonValueByKey("modifyProductStatus", param);
			tMap.put("modifyProductId", modifyProductId);
			tMap.put("modifyProductType", modifyProductType);
			tMap.put("modifyProductSubType", modifyProductSubType);
			tMap.put("modifyProductStatus", modifyProductStatus);
			/***获取已经修改并的产品信息****/
			// 产品类型为1-财富产品 2-保险产品
			if (modifyProductType.equals("1")) {
				PDWealth pDWealthSchema = (PDWealth)JsonUtils.mapToObject(paramMap,  PDWealth.class);
				if (pDWealthSchema.getFinancingScale()!=null) {
					pDWealthSchema.setFinancingScale((new BigDecimal(NumberUtils.multiply(pDWealthSchema.getFinancingScale().toString(), "10000"))));
				}
				tMap.put("pDWealthSchema", pDWealthSchema);// 放入财富基本信息
				// 固定类产品
				if (modifyProductSubType.equals("02")) {
					// 获取固定类产品费用比例表 
					String wealthRateTableInfoStr = paramMap.containsKey("wealthRateTableInfo")?(String)paramMap.get("wealthRateTableInfo"):"";
					String wealthRateTableInfo = JsonUtils.decodeUrlParams(wealthRateTableInfoStr,"utf-8");
					List<PDWealthFeeRate> WealthFeeRateInfolist=com.sinosoft.util.JsonUtils.jsonArrStrToList(wealthRateTableInfo, PDWealthFeeRate.class);
					tMap.put("WealthFeeRateInfolist", WealthFeeRateInfolist);// 放入固定类费用比例信息
					// 固定收益分类信息 gdiffrentInfo
					PDWealthChargeRate pDWealthChargeRateSchema = (PDWealthChargeRate)JsonUtils.mapToObject(paramMap, PDWealthChargeRate.class);
					tMap.put("pDWealthChargeRate", pDWealthChargeRateSchema);// 放入固定类分类信息
					// 固定收益类收益分配信息
					String wealthIncomeDisInfoData = paramMap.containsKey("wealthIncomeDisInfoData")?(String)paramMap.get("wealthIncomeDisInfoData"):"";
					List<PDwealthIncomeDis> pdwealthIncomeDisList = JsonUtils.jsonArrStrToList(wealthIncomeDisInfoData, PDwealthIncomeDis.class);
					tMap.put("pdwealthIncomeDisList", pdwealthIncomeDisList);// 放入固定收益类收益分配信息
				}else {
					if (modifyProductSubType.equals("01")) {
						// 股权收益类收益分配信息
						String wealthStockDisInfoData = paramMap.containsKey("wealthStockDisInfoData")?(String)paramMap.get("wealthStockDisInfoData"):"";
						List<PDWealthStockDis> pdwealthStockDisList = JsonUtils.jsonArrStrToList(wealthStockDisInfoData, PDWealthStockDis.class);
						tMap.put("pdwealthStockDisList", pdwealthStockDisList);// 放入固定收益类收益分配信息
					}
					// 获取浮动股权类分类信息fdiffrentInfo
					PDWealthChargeRate pDWealthChargeRateSchema = (PDWealthChargeRate)JsonUtils.mapToObject(paramMap, PDWealthChargeRate.class);
					tMap.put("pDWealthChargeRate", pDWealthChargeRateSchema);// 放入浮动、股权类例信息
				}
			} 
			// 获取录入信息 PDFactor
			String factorInfoTableInfoStr = paramMap.containsKey("factorInfoTableInfo")?(String)paramMap.get("factorInfoTableInfo"):"";
			String factorInfoTableInfo = JsonUtils.decodeUrlParams(factorInfoTableInfoStr,"utf-8");
			List<PDFactor> factorInfolist = JsonUtils.jsonArrStrToList(factorInfoTableInfo, PDFactor.class);
			tMap.put("factorInfolist", factorInfolist);// 放入录入信息
			// 将组装好的Map传给后台,进行数据保存
			resultInfo =productService.updatePrdocutCoreInfo(tMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 获取产品基本信息明细页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailProductDefUrl", method = RequestMethod.GET)
	public ModelAndView detailProductDefUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/detailProductDef",reqParamMap);
	}
	/**
	 * 获取浮动类产品净值走势页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailProductNetValueUrl", method = RequestMethod.GET)
	public ModelAndView detailProductNetValueUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/detailProductNetValue",reqParamMap);
	}
	
	/**
	 * 获取产品网销信息明细页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailProductInternetInfoUrl", method = RequestMethod.GET)
	public ModelAndView detailProductInternetInfoUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/detailProductInternetDef",reqParamMap);
	}
	
	/**
	 * 获取产品核心信息明细页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailProductCoreInfoUrl", method = RequestMethod.GET)
	public ModelAndView detailProductCoreInfoUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/detailProductCoreDef",reqParamMap);
	}
	
	
	/**
	 * 获取产品核附文本编辑信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailProductEditorUrl", method = RequestMethod.GET)
	public ModelAndView detailProductEditorUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/detailProductEditor",reqParamMap);
	}
	
	
	/*********************************** 产品发布 *************************************/
	/**
	 * 跳转产品发布初始页面
	 */
	@RequestMapping(value = "/listProductReleaseUrl", method = RequestMethod.GET)
	public String listTradeInputUrl(Model model) {
		return "fms/product/listProductRelease";
	}
	
	/**
	 * 产品发布初始列表查询 queryListReleaseProductUrl
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryListReleaseProductUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListReleaseProductUrl(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		paramMap = (Map) JsonUtils.jsonStrToObject(param, paramMap);
		dataGrid = productService.getReleasePageList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	 }
	
	/**
	 * 产品修改申请动作
	 */
	@RequestMapping(value = "/productModifyApply", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo productModifyApply(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo=new ResultInfo();
		System.out.println("param:"+param);
		try{
		productService.modifyApply(param,loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("产品修改申请成功！");
		}
		catch(Exception e){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("产品修改申请失败："+e.getMessage());
		e.printStackTrace();
		}
		return resultInfo;
	}
	
	
	/**
	 * 产品信息同步至互联网
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/productInternetRelease", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo productInternetRelease(String param,HttpServletRequest request,ModelMap model) {
		Map paramMap=new HashMap();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo=new ResultInfo();
		if (param!=null) {
			try{
				paramMap.put("productId", param);
				resultInfo=productService.internetRelease(paramMap,loginInfo);//产品同步互联网
				if (resultInfo.isSuccess()) {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("产品信息同步互联网端成功！");
				return resultInfo;
				}
				else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品发布接口同步失败！原因是："+resultInfo.getMsg());
				return 	resultInfo;
				}
				}
				catch(Exception e){
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品信息同步互联网端失败，接口信息出现异常，原因是："+e.getMessage());
				return resultInfo;}
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品流水号失败，无法将产品信息同步至互联网端");
		return resultInfo;
		}
	}
	
	
	
	/**
	 * 产品发布动作
	 */
	@RequestMapping(value = "/productRelease", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo productRelease(String param,ModelMap model) {
		String productId=null;
		String productSatus=null;
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo=new ResultInfo();
		try {
			productId = JsonUtils.getJsonValueByKey("productId", param);
			productSatus = JsonUtils.getJsonValueByKey("productStatus", param);
			if (productId != null && productSatus != null) {
				// 先判断该产品是否是重复发布，如果重新发布就不需要
				if (!productSatus.equals("2")) {
					resultInfo = productService.productRelease(productId, loginInfo);// 产品发布动作
					if (resultInfo.isSuccess()) {
						log.info("产品发布成功！");
						resultInfo.setSuccess(true);
						resultInfo.setMsg("产品发布成功！");
	                    return resultInfo;
					} else {
						resultInfo.setSuccess(false);
						resultInfo.setMsg("产品发布失败，原因是：" + resultInfo.getMsg());
						return resultInfo;
					}
				}
				else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品已发布，不能重新发布");
				return resultInfo;	
				}
			} else {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品流水号信息失败，无法发布产品");
				return resultInfo;
			}
		} catch (Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("产品发布失败：" + e.getMessage());
			e.printStackTrace();
			return resultInfo;
		}
		
	}
	
	

	/************************************** 产品净值维护 **************************************/
	/***
	 * 跳转净值信息维护初始页面
	 */
	@RequestMapping(value = "/listNetWorthFindUrl", method = RequestMethod.GET)
	public String listTradeCheckUrl(Model model) {
		return "fms/product/listNetWorthFind";
	}

	/**
	 * 产品净值信息初始页面查询动作
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryListNetValueUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListNetValueUrl(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		param = JsonUtils.decodeUrlParams(param, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
		}
		dataGrid = productService.getNetValuePageList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/***
	 * 跳转净值信息维护初始页面
	 */
	@RequestMapping(value = "/listNetValueUrl", method = RequestMethod.GET)
	public String listNetValueUrl(Model model) {
		return "fms/product/listNetValue";
	}

	/**
	 * 产品净值信息初始页面查询动作
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryNetValueHistoryUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryNetValueHistoryUrl(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		param = JsonUtils.decodeUrlParams(param, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
		}
		dataGrid = productService.getNetValueList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 删除初始化页面列表的信息
	 * */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteNetValueUrl")
	@ResponseBody
	public ResultInfo deleteNetValueInfo(@RequestParam("id") List<Integer> uid,ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
		for (Integer id : uid) {
		productService.deleteNetValueInfo(new Long(id),loginInfo);
		}
		resultInfo.setSuccess(true);
		resultInfo.setMsg("删除成功!");
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}

	/***
	 * 单独的净值信息查询页面
	 */
	@RequestMapping(value = "/findNetWorthUrl", method = RequestMethod.GET)
	public String findNetWorthUrl() {
		return "fms/product/findNetValue";
	}
	
	/***
	 * 跳转新增净值初始页面
	 */
	@RequestMapping(value = "/addNetWorthUrl", method = RequestMethod.GET)
	public String addNetWorthUrl() {
		return "fms/product/addNetWorth";
	}
	
	/**
	 * 新增净值
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addNetValueAndFileInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addNetValueInfoUrl(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
	    LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
	    /***接口测试**/
		HashMap tMap = new HashMap();
		String existPdwealthdNetValueId = JsonUtils.getJsonValueByKey("pdwealthNetValueId", param);
		String netValueBaseInfo = JsonUtils.getJsonValueByKey("netValueBaseInfo", param);
		// 拿出净值部分的信息并存入表格
		String productId = JsonUtils.getJsonValueByKey("productId",netValueBaseInfo);
		String publicDate = JsonUtils.getJsonValueByKey("openDayDate",netValueBaseInfo);
		String netType = JsonUtils.getJsonValueByKey("netValueType",netValueBaseInfo);
		String netValue = JsonUtils.getJsonValueByKey("netValue",netValueBaseInfo);
		String isOrder = JsonUtils.getJsonValueByKey("isOrder",netValueBaseInfo);
		String specialType = JsonUtils.getJsonValueByKey("specialType",netValueBaseInfo);
		String earnRate = JsonUtils.getJsonValueByKey("earnRate",netValueBaseInfo);
		PDWealthNetValue pDWealthNetValueSchema = new PDWealthNetValue();
		pDWealthNetValueSchema.setNetType(netType);
		pDWealthNetValueSchema.setIsOrder(isOrder);
		pDWealthNetValueSchema.setNetValue(new BigDecimal(netValue));
		pDWealthNetValueSchema.setSpecialType(specialType);
		pDWealthNetValueSchema.setEarnRate(earnRate);
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
		pDWealthNetValueSchema.setPublicDate(fmt.parse(publicDate));
		} catch (ParseException e1) {
		e1.printStackTrace();
		}
		/************判断产品流水号是否存在**************/
		if (productId.equals("")||productId.equals(null)||productId.equals("null")) {
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品流水号失败");
		return resultInfo;
		}
		/************新增净值和重复提交净值*************/
		try {
		if(existPdwealthdNetValueId.equals("null")){
		tMap.put("existPdwealthdNetValueId", "isnull");
		}
		else 
		{
		tMap.put("existPdwealthdNetValueId", existPdwealthdNetValueId);
		}
		tMap.put("pDWealthNetValueSchema", pDWealthNetValueSchema);// 保存基本信息
		tMap.put("productId", productId);
		// 将组装好的Map传给后台,进行数据保存
		String pdwealthNetValueId=	productService.addNetValueInfo(tMap,loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setObj(pdwealthNetValueId);
		resultInfo.setMsg("净值新增成功！");
		} catch (Exception e){
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		return  resultInfo;
		}
		return resultInfo;
	}
	

	/**
	 * 净值信息同步至互联网
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/netValueRelease", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo netValueRelease(String param,HttpServletRequest request,ModelMap model) {
		Map paramMap=new HashMap();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo=new ResultInfo();
		if (param!=null) {
			try{
				paramMap.put("productId", param);
				resultInfo=productService.internetNetValue(paramMap,loginInfo);//产品净值信息同步互联网
				if (resultInfo.isSuccess()) {
				resultInfo.setSuccess(true);
				resultInfo.setMsg("产品净值信息同步互联网端成功！");
				return resultInfo;
				}
				else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品净值信息同步失败！原因是："+resultInfo.getMsg());
				return 	resultInfo;
				}
				}
				catch(Exception e){
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("产品净值信息同步互联网端失败，接口信息出现异常，原因是："+e.getMessage());
				return resultInfo;}
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品流水号失败，无法将产品净值信息同步至互联网端");
		return resultInfo;
		}
	}
	
	

	/**
	 * 跳转净值信息修改页面
	 */
	@RequestMapping(value = "/updateNetWorthUrl", method = RequestMethod.GET)
	public String updateNetWorthUrl() {
		return "fms/product/updateNetWorth";
	}

	/**
	 * 净值信息初始化页面控件页面
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateNetValueInitUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map updateNetValueInit(String param) {
		Map map = new HashMap();
		if (param != null && !param.equals("")) {
		map = productService.getUpdateListInfo(new Long(param));
		}
		return map;
	}

	/**
	 * 净值信息修改动作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/saveUpdateNetValueUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveUpdateNetValue(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		String modifyProductId=JsonUtils.getJsonValueByKey("netValueBaseInfo", param);
		try {
		String netValueInfoForm = JsonUtils.getJsonValueByKey("netValueBaseInfo", param);
		String publicDate = JsonUtils.getJsonValueByKey("publicDate",netValueInfoForm);
		String netType = JsonUtils.getJsonValueByKey("netType",netValueInfoForm).substring(0, 2);
		String netValue = JsonUtils.getJsonValueByKey("netValue",netValueInfoForm);
		String isOrder = JsonUtils.getJsonValueByKey("isOrder",netValueInfoForm);
		String specialType = JsonUtils.getJsonValueByKey("specialType",netValueInfoForm);
		String earnRate = JsonUtils.getJsonValueByKey("earnRate",netValueInfoForm);
		String modifyRecordNetWorthId = JsonUtils.getJsonValueByKey("modifyRecordNetWorthId", param);
		HashMap tMap = new HashMap();
		tMap.put("modifyRecordNetWorthId", modifyRecordNetWorthId);
		tMap.put("publicDate", publicDate);
		tMap.put("netType", netType);
		tMap.put("netValue", netValue);
		tMap.put("isOrder", isOrder);
		tMap.put("specialType", specialType);
		tMap.put("earnRate", earnRate);
		productService.updatNetValueSave(tMap,loginInfo);
		resultInfo.setSuccess(true);
		resultInfo.setMsg("修改保存成功！");
		} catch (CisCoreException e) {
		resultInfo.setSuccess(false);
		resultInfo.setMsg("修改保存失败！原因是：" + e.getMessage());
		e.printStackTrace();
		}
	/*	*//*****************将净值信息同步至互联网前端*************//*
		try 
		{
		Map  pramMap=new HashMap();
		pramMap.put("productId", modifyProductId);
		resultInfo=productService.internetNetValue(pramMap,loginInfo);//产品同步互联网
		if (resultInfo.isSuccess()) {
		resultInfo.setSuccess(true);
		resultInfo.setMsg("净值修改成功，并同步至互联网！");
		}
		else{
		resultInfo.setSuccess(true);
		resultInfo.setMsg("净值修改成功，但同步至互联网失败！");
		return 	resultInfo;
		}
		} 
		catch (Exception e) 
		{
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("产品净值修改成功，但同步至前端互联网失败");
		return  resultInfo;
	    }*/
		return resultInfo;
	}

	/**
	 * 附件列表初始查询
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryAttachInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryAttachInfoUrl(DataGridModel dgm, String queryParam) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		paramMap = (Map) JsonUtils.jsonStrToObject(queryParam, paramMap);
		dataGrid = productService.getNetValueFileInfo(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}


	/************************************** 开放日维护 **************************************/

	/**
	 * 开放日信息维护初始页面
	 */
	@RequestMapping(value = "/listOpenDayFindUrl", method = RequestMethod.GET)
	public String listTradeAuditUrl(Model model) {
		return "fms/product/listOpenDayFind";
	}

	/**
	 * 新增开放放日跳转页面
	 */
	@RequestMapping(value = "/addOpenDayFindUrl", method = RequestMethod.GET)
	public String addOpenDayFindUrl(Model model) {
		return "fms/product/addOpenDayFind";
	}
	
	/**
	 * 新增开放放日维护跳转页面
	 */
	@RequestMapping(value = "/addOpenDaysFindUrl", method = RequestMethod.GET)
	public String addOpenDaysFindUrl(Model model) {
		return "fms/product/addOpenDaysFind";
	}
	/**
	 * 单独查看开放日信息
	 */
	@RequestMapping(value = "/findOpenDayUrl", method = RequestMethod.GET)
	public String findOpenDayUrl(Model model) {
		return "fms/product/findOpenDay";
	}
	
	

	/**
	 * 新增开放日动作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addOpenDayInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addOpenDayInfo(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		HashMap tMap = new HashMap();
		String productId="";
		try {
			String netOpenBaseInfo = JsonUtils.getJsonValueByKey("netOpenBaseInfo", param);
			String existpdwealthOpenDayId=JsonUtils.getJsonValueByKey("pdwealthOpenDayId", param);
			if(existpdwealthOpenDayId.equals("null")){
			tMap.put("existpdwealthOpenDayId", "isnull");
			}
			else{
			tMap.put("existpdwealthOpenDayId", existpdwealthOpenDayId);
			}
			// 拿出开放日部分的信息并存入表格
			productId = JsonUtils.getJsonValueByKey("productId",netOpenBaseInfo);
			if (productId.equals("")||productId==null) {
			resultInfo.setSuccess(false);	
			resultInfo.setMsg("获取产品流水号失败，无法保存开放日信息");
			return resultInfo;
			}
			String openDayDate = JsonUtils.getJsonValueByKey("openDate",netOpenBaseInfo);
			String investStartDate = JsonUtils.getJsonValueByKey("investStartDate",netOpenBaseInfo);
			String investEndDate = JsonUtils.getJsonValueByKey("investEndDate",netOpenBaseInfo);
			String financingScale = JsonUtils.getJsonValueByKey("financingScale",netOpenBaseInfo);
			tMap.put("productId", productId);
			tMap.put("openDate", openDayDate);
			tMap.put("investStartDate", investStartDate);
			tMap.put("investEndDate", investEndDate);
			tMap.put("financingScale", financingScale);
	        String returnWealthOpenDayId= productService.addopenDayInfo(tMap,loginInfo);
	        log.info("返回的开放日流水号为："+returnWealthOpenDayId);
			resultInfo.setSuccess(true);
			resultInfo.setObj(returnWealthOpenDayId);
			resultInfo.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());

		}
		
		return resultInfo;
	}

	/**
	 * 批量维护开放日动作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addOpenDaysInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addOpenDaysInfo(String param, ModelMap modelMap) {
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		HashMap tMap = new HashMap();
		String productId = "";
		try {
			String netOpenBaseInfo = JsonUtils.getJsonValueByKey("netOpenBaseInfo", param);
			String existpdwealthOpenDayId = JsonUtils.getJsonValueByKey("pdwealthOpenDayId", param);
			String isOrder = JsonUtils.getJsonValueByKey("isOrder", param);
			String openDayRules = JsonUtils.getJsonValueByKey("openDayRules", param);
			if (existpdwealthOpenDayId.equals("null")) {
				tMap.put("existpdwealthOpenDayId", "isnull");
			} else {
				tMap.put("existpdwealthOpenDayId", existpdwealthOpenDayId);
			}
			// 拿出开放日部分的信息并存入表格
			productId = JsonUtils.getJsonValueByKey("productId", netOpenBaseInfo);
			if (productId.equals("") || productId == null) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品流水号失败，无法保存开放日信息");
				return resultInfo;
			}
			String openDayDate = JsonUtils.getJsonValueByKey("openDate", netOpenBaseInfo);
			String defendStartDate = JsonUtils.getJsonValueByKey("defendStartDate", netOpenBaseInfo);
			String defendEndDate = JsonUtils.getJsonValueByKey("defendEndDate", netOpenBaseInfo);
			tMap.put("productId", productId);
			tMap.put("openDate", openDayDate);
			tMap.put("defendStartDate", defendStartDate);
			tMap.put("defendEndDate", defendEndDate);
			tMap.put("isOrder", isOrder);
			tMap.put("openDayRules", openDayRules);
			resultInfo = productService.addOpenDaysInfo(tMap, loginInfo);
			log.info(resultInfo.getObj());
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 开放日同步至互联网
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/openDayRelease", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo openDayRelease(String param,ModelMap model) {
		Map paramMap=new HashMap();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo=new ResultInfo();
		if (param!=null) {
			/*****************将开放日信息同步至互联网前端*************/
			try 
			{
			Map  pramMap=new HashMap();
			pramMap.put("productId", param);
			resultInfo=productService.internetOpenDay(pramMap,loginInfo);//开放日同步互联网
			if (resultInfo.isSuccess()) {
			resultInfo.setSuccess(true);
			resultInfo.setMsg("开放日已同步至互联网！");
			}
			else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg(resultInfo.getMsg());
			return 	resultInfo;
			}
			} 
			catch (Exception e) 
			{
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("开放日同步至互联网失败，原因是："+e.getMessage());
			return  resultInfo;
		    }
		}
		else{
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品流水号失败，无法将产品开放日信息同步至互联网端");
		return resultInfo;
		}
		return resultInfo;
	}
	
	
	
	/**
	 * 开放日信息列表queryListOpenDayUrl
	 */

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryListOpenDayUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListOpenDayUrl(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			if(param!=null&&!"".equals(param)){
			param = JsonUtils.decodeUrlParams(param, "utf-8");
			paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = productService.getOpenDayPageList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 开放日信息删除deleteOpenDayInfoUrl
	 */
	/**
	 * 删除初始化页面列表的信息
	 * */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteOpenDayInfoUrl")
	@ResponseBody
	public ResultInfo deleteOpenDayInfoUrl(@RequestParam("id") List<Integer> uid,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			for (Integer id : uid) {
			productService.deleteOpenDayInfo(new Long(id),loginInfo);
			}
			resultInfo.setSuccess(true);
			resultInfo.setMsg("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}

	/**
	 * 跳转净值信息修改页面
	 */
	@RequestMapping(value = "/updateOpenDayUrl", method = RequestMethod.GET)
	public String updateOpenDayUrl() {
		  return "fms/product/updateOpenDay";
	}

	/**
	 * 开放日信息初始化页面控件页面
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateOpenDayInitUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map updateOpenDayInitUrl(String param) {
		Map map = new HashMap();
		if (param != null && !param.equals("")) {
			map = productService.getUpdateOpenDayListInfo(new Long(param));
		}
		return map;
	}
	
	
	/**
	 * 开放日信息修改动作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/saveUpdateOpenDayUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveUpdateOpenDay(String param,ModelMap modelMap) {
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			String netOpenDayForm = JsonUtils.getJsonValueByKey("openDayBaseInfo", param);
			String openDay = JsonUtils.getJsonValueByKey("openDate",netOpenDayForm);
			String modifywealthOpenDateId = JsonUtils.getJsonValueByKey("modifywealthOpenDateId", param);
			String investStartDate = JsonUtils.getJsonValueByKey("investStartDate", netOpenDayForm);
			String investEndDate = JsonUtils.getJsonValueByKey("investEndDate", netOpenDayForm);
			String financingScale = JsonUtils.getJsonValueByKey("financingScale",netOpenDayForm);
			HashMap tMap = new HashMap();
			tMap.put("modifywealthOpenDateId", modifywealthOpenDateId);
			tMap.put("openDay", openDay);
			tMap.put("investStartDate", investStartDate);
			tMap.put("investEndDate", investEndDate);
			tMap.put("financingScale", financingScale);
			productService.updateOpenDaySave(tMap,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("修改保存成功！");
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("修改保存失败！原因是：" + e.getMessage());
			e.printStackTrace();

		}
		return resultInfo;
	}
	
	
	/**
	 * 产品额度分配获取所有分公司信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/getAllComInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getAllComInfo(String param,ModelMap modelMap) {
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			resultInfo = productService.getAllComInfo(paramMap,loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到分公司信息！原因是：" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 产品额度分配保存所有分公司额度分配信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({"unchecked", "unused" })
	@RequestMapping(value = "/saveProductAmountDisInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveProductAmountDisInfo(String param,ModelMap modelMap) {
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			String pdAmountDisInfo = JsonUtils.getJsonValueByKey("pdAmountDisInfo", param);
			String productInfo = JsonUtils.getJsonValueByKey("productInfo", param);
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			List<PdAmountDisInfo> pdAmountDisInfoList = JsonUtils.jsonArrStrToList(pdAmountDisInfo, PdAmountDisInfo.class);
			resultInfo = productService.saveProductAmountDisInfo(pdAmountDisInfoList,productInfo,loginInfo,operate);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到分公司信息！原因是：" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/getProductInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getProductInfo(String productId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			resultInfo = productService.getProductInfo(productId);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到产品信息！原因是：" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取额度分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getPdAmountDisInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getPdAmountDisInfo(String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = productService.getPdAmountDisInfo(paramMap);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到产品信息！原因是：" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取额度分配信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getAllPdAmountDisInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getAllPdAmountDisInfo(DataGridModel dgm,String queryParam){
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			dataGrid = productService.getAllPdAmountDisInfo(dgm,paramMap);
		} catch (CisCoreException e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 跳转至产品报告查询页面
	 */
	@RequestMapping(value = "/listProductReportUrl", method = RequestMethod.GET)
	public String listProductReportUrl() {
		return "fms/product/listProductReport";
	}
	
   /**
    * 查询产品报告信息初始页面
    * @param dgm
    * @param queryParam
    * @return
    */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryListProductReportUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListProductReportUrl(DataGridModel dgm,String param){
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(param);
			dataGrid = productService.getAllProductReportInfo(dgm,paramMap);
		} catch (CisCoreException e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 跳转至产品报告新增页面
	 * @return
	 */
	@RequestMapping(value = "/addProductReportUrl", method = RequestMethod.GET)
	public String addProductReportUrl() {
		return "fms/product/productReportUpload";
	}
	
	
	
	/**
	 * 获取t_pd_product表中的数据所有已发布的产品
	 * @return
	 */
	@RequestMapping(value = "/productQueryAllRelease", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String productQueryAllRelease(){
		List<Map<String, String>> codeMapList = productService.tproductQueryAllRelease();
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	/**
	 * 删除产品报告
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/deleteProductReport", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteProductReport(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		try {
			if (param!=null) {
				param=JsonUtils.getJsonValueByKey("fileInfoId", param);
				resultInfo = productService.deleteProductReport(param,loginInfo);
			}
			else{
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取附件流水号失败，无法删除");
			return resultInfo;
			}
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到产品信息！原因是：" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
   @RequestMapping("/uploadimageUrl")
   public void imageUpload(@RequestParam("upfile") MultipartFile upfile) throws IOException{
		log.info("进入富文本图片上传");
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		String  path=request.getSession().getServletContext().getRealPath("ueditor/upload");
		String fileName =upfile.getOriginalFilename();
		int num =1;
		System.out.println(path);
		String savePath=path+"/"+fileName+"/"+num;
		File targetFile=new File(savePath,fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		String json = "{ \"state\": \"SUCCESS\",\"url\": \"/ueditor/upload/"+fileName.toString()+"/"+num+"/"+upfile.getOriginalFilename()+"\",\"title\": \""+
	    upfile.getOriginalFilename()+"\",\"original\": \""+upfile.getOriginalFilename()+"\"}";
	    System.out.println(json);
	    HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
	    response.getWriter().write(json);
	}
   

	/**
	 * 保存产品附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked" })
	@RequestMapping(value = "/addProductUeditorInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addProductEditorInfoUrl(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String productId=JsonUtils.getJsonValueByKey("productId", param);
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		Map tMap=new HashMap();
		try {
			if (productId!=null) {
				tMap.put("productId", productId);
				tMap.put("htmlContext", content);
				tMap.put("editorInfoSucFlag", editorInfoSucFlag);
				resultInfo = productService.addProductEditorInfo(tMap,loginInfo);
			}
			else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品信息失败，无法保存产品附文本信息");
			}
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存产品附文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
   

	/**
	 * 获取产品核心信息明细页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateProductEditorUrl", method = RequestMethod.GET)
	public ModelAndView updateProductEditorUrl(@RequestParam("param") String param)
	{
	    Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/updateProductEditor",reqParamMap);
	}
	
	/**
	 * 获取产品附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/getProductEditorContextUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getProductEditorContextUrl(String param,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String productId=JsonUtils.getJsonValueByKey("productId", param);
		Map tMap=new HashMap();
		try {
			if (productId!=null) {
				tMap.put("productId", productId);
				resultInfo = productService.getProductEditorContext(tMap);
			}
			else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品信息失败，无法保存产品附文本信息");
			}
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存产品附文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
   
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProductAmountDisAndOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getProductAmountDisAndOrderInfo(String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = productService.getProductAmountDisAndOrderInfo(paramMap);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("未获取到产品信息！原因是：" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 跳转至产品附文本编辑页面
	 */
	@RequestMapping(value = "/listProductEditorUrl", method = RequestMethod.GET)
	public String listProductEditorUrl() {
		return "fms/product/listProductEditor";
	}
	
	/**
	 * 		导出业管部报表信息
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/floatProductNetValue.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ModelAndView businessManagementDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
		// 获取业务报表导出Excel模板
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/floatProductNetValue.xls";
		// 分配ResultInfo对象返回程序异常信息
		ResultInfo resultInfo = new ResultInfo();
		// 
		try {
			// 查询参数解码
			queryParam = StringUtils.encodeStr(queryParam);
			// 查询参数转成Map集合
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 调用Service层方法统计产品预约信息
			Map  datas = productService.getProductNetValues(paramMap);
			// 调用Excel模板生成Excel业务报表
			OutputStream out=null;
	        BufferedOutputStream bos=null;
	        BufferedInputStream bis=null;
	        InputStream in=null;
	        try{
		        in=ExcelTool.exportExcel(path, datas);
		        bis=new BufferedInputStream(in);
		        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
		        String fileName = "floatProductNetValue"+now+".xls";
		      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
		        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		        byte[] data=new byte[1024];
		        int bytes=0;
		        out=response.getOutputStream();
		        bos=new BufferedOutputStream(out);
		        while((bytes=bis.read(data, 0, data.length))!=-1){
		            bos.write(data,0,bytes);                                        //写出文件流                                     
		        }
		        bos.flush();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try {
	                bos.close();
	                out.close();
	                bis.close(); 
	                in.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出产品净值报表出现异常！");
		}
		return null;
	}
	/**
	 * 跳转至产品投后清单页面
	 */
	@RequestMapping(value = "/listFixedProductIncomeUrl", method = RequestMethod.GET)
	public String listFixedProductIncomeUrl() {
		return "fms/product/listFixedProductIncome";
	}
	
	  /**
	    * 查询产品投后清单
	    * @param dgm
	    * @param queryParam
	    * @return
	    */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/getFixedProductIncomeUrl", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid getFixedProductIncomeUrl(DataGridModel dgm,String param){
			DataGrid dataGrid = new DataGrid();
			try {
				Map paramMap = JsonUtils.jsonStrToMap(param);
				dataGrid = productService.getAlltFixedProductIncomeInfo(dgm,paramMap);
			} catch (CisCoreException e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 	下载固收投后清单详情
		 * */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value="/fixedProductDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
		public ModelAndView fixedProductDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
			// 输出日志，便于调试
			log.info("Controller层类中fixedProductDetail()接受参数queryParam:" + queryParam);
			// 获取业务报表导出Excel模板
			String path = System.getProperty("fms.webroot");
			path = path+"/WEB-INF/classes/reportTemplate/fixedProductDetail.xls";
			// 分配ResultInfo对象返回程序异常信息
			ResultInfo resultInfo = new ResultInfo();
			// 
			try {
				// 查询参数解码
				queryParam = StringUtils.encodeStr(queryParam);
				// 查询参数转成Map集合
				Map paramMap = JsonUtils.jsonStrToMap(queryParam);
				// 调用Service层方法统计产品预约信息
				Map  datas = productService.fixedProductDetail(paramMap);
				// 调用Excel模板生成Excel业务报表
				OutputStream out=null;
		        BufferedOutputStream bos=null;
		        BufferedInputStream bis=null;
		        InputStream in=null;
		        try{
			        in=ExcelTool.exportExcel(path, datas);
			        bis=new BufferedInputStream(in);
			        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
			        String fileName = "fixedProductDetail"+now+".xls";
			      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
			        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			        byte[] data=new byte[1024];
			        int bytes=0;
			        out=response.getOutputStream();
			        bos=new BufferedOutputStream(out);
			        while((bytes=bis.read(data, 0, data.length))!=-1){
			            bos.write(data,0,bytes);                                        //写出文件流                                     
			        }
			        bos.flush();
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally{
		            try {
		                bos.close();
		                out.close();
		                bis.close(); 
		                in.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("下载固收产品投后清单详情出现异常！");
			}
			return null;
		}
		/**
		 * 固收产品投后清单导出
		 * @param queryParam
		 * @param request
		 * @param response
		 * @param modelMap
		 * @return
		 */
		@SuppressWarnings("unchecked")
		@RequestMapping(value="/fixedProductIncomeDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
		public ModelAndView exportFixedProIncomeInfo(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
			// 输出日志，便于调试
			log.info("Controller层类中exportFixedProIncomeInfo()接受参数queryParam:" + queryParam);
			// 获取业务报表导出Excel模板
			String path = System.getProperty("fms.webroot");
			path = path+"/WEB-INF/classes/reportTemplate/fixedProductIncomeInfo.xls";
			ResultInfo resultInfo = new ResultInfo();
			try {
				// 查询参数转成Map集合
				Map paramMap = JsonUtils.jsonStrToMap(queryParam);
				//获取固收产品信息Data
				Map fixedProData = this.productService.exportFixedProIncomeInfo(paramMap);
				// 调用Excel模板生成Excel业务报表
				OutputStream out=null;
		        BufferedOutputStream bos=null;
		        BufferedInputStream bis=null;
		        InputStream in=null;
		        try {
		        	in=ExcelTool.exportExcel(path, fixedProData);
			        bis=new BufferedInputStream(in);
			        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
			        String fileName = "fixedProductIncomeInfo"+now+".xls";
			        //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
			        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			        byte[] data=new byte[1024];
			        int bytes=0;
			        out=response.getOutputStream();
			        bos=new BufferedOutputStream(out);
			        while((bytes=bis.read(data, 0, data.length))!=-1){
			            bos.write(data,0,bytes);                                        //写出文件流                                     
			        }
			        bos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
		                bos.close();
		                out.close();
		                bis.close(); 
		                in.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("导出固收产品投后清单信息出现异常！");
			}
			return null;
		}
		
		/**
		 * 固收产品头后清单详细信息页面
		 * @param param
		 * @return
		 */
		@RequestMapping(value = "/detailFixedProIncomeUrl", method = RequestMethod.GET)
		public ModelAndView detailFixedProIncomeUrl(@RequestParam("param") String param)
		{
			Map<String, String> reqParamMap = new HashMap<String, String>();
			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
			return new ModelAndView("fms/product/detailFixedProIncome",reqParamMap);
		}
		
		/**
		 * 查询固收产品投后详单
		 * @param dgm
		 * @param queryParams
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryDetailFixedProIncomeList", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryDetailFixedProIncomeList(DataGridModel dgm,@RequestParam("param")String param){
			DataGrid dataGrid = new DataGrid();
			try {
				Map paramMap = JsonUtils.jsonStrToMap(param);
				dataGrid = productService.queryDetailFixedProIncomeList(dgm,paramMap);
			} catch (CisCoreException e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 净值代办任务页面
		 */
		@RequestMapping(value = "/listNetValueTaskUrl", method = RequestMethod.GET)
		public String listNetValueTaskUrl() {
			  return "fms/product/listNetValueTask";
		}
		
		/**
		 * 查询净值待办任务列表 
		 * @author ZYM
		 * @param dgm
		 * @param param
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/searchNetValueTaskUrl", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid searchNetValueTask(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			try {
			if(param!=null&&!"".equals(param))
				{
					param = JsonUtils.decodeUrlParams(param, "utf-8");
					paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
				}
			dataGrid = productService.searchNetValueTask(dgm, paramMap);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return dataGrid;
		}

		
		/***
		 * 跳转新增净值初始页面
		 * @author wanghao
		 */
		@RequestMapping(value = "/addNetValueTaskUrl", method = RequestMethod.GET)
		public String addNetValueTaskUrl() {
			return "fms/product/addNetValueTask";
		}
		
		/**
		 * 新增净值(净值披露频率任务代办)
		 * @author wanghao
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value = "/addNetValueTaskAndFileInfoUrl", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo addNetValueTaskInfoUrl(String param,ModelMap model) {
			ResultInfo resultInfo = new ResultInfo();
		    LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		    /***接口测试**/
			HashMap tMap = new HashMap();
			String existPdwealthdNetValueId = JsonUtils.getJsonValueByKey("pdwealthNetValueId", param);
			String netValueBaseInfo = JsonUtils.getJsonValueByKey("netValueBaseInfo", param);
			String netValueTaskId = JsonUtils.getJsonValueByKey("netValueTaskId", param);
			// 拿出净值部分的信息并存入表格
			String productId = JsonUtils.getJsonValueByKey("productId",param);
			String publicDate = JsonUtils.getJsonValueByKey("openDayDate",netValueBaseInfo);
			String netType = JsonUtils.getJsonValueByKey("netValueType",netValueBaseInfo);
			String netValue = JsonUtils.getJsonValueByKey("netValue",netValueBaseInfo);
			String isOrder = JsonUtils.getJsonValueByKey("isOrder",netValueBaseInfo);
			String specialType = JsonUtils.getJsonValueByKey("specialType",netValueBaseInfo);
			String earnRate = JsonUtils.getJsonValueByKey("earnRate", netValueBaseInfo);
			PDWealthNetValue pDWealthNetValueSchema = new PDWealthNetValue();
			pDWealthNetValueSchema.setNetType(netType);
			pDWealthNetValueSchema.setIsOrder(isOrder);
			pDWealthNetValueSchema.setNetValue(new BigDecimal(netValue));
			pDWealthNetValueSchema.setSpecialType(specialType);
			pDWealthNetValueSchema.setEarnRate(earnRate);
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			try {
			pDWealthNetValueSchema.setPublicDate(fmt.parse(publicDate));
			} catch (ParseException e1) {
			e1.printStackTrace();
			}
			/************判断产品流水号是否存在**************/
			if (productId.equals("")||productId.equals(null)||productId.equals("null")) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品流水号失败");
			return resultInfo;
			}
			/************新增净值和重复提交净值*************/
			try {
			if(existPdwealthdNetValueId.equals("null")){
			tMap.put("existPdwealthdNetValueId", "isnull");
			}
			else 
			{
			tMap.put("existPdwealthdNetValueId", existPdwealthdNetValueId);
			}
			tMap.put("pDWealthNetValueSchema", pDWealthNetValueSchema);// 保存基本信息
			tMap.put("productId", productId);
			tMap.put("netValueTaskId",netValueTaskId);
			// 将组装好的Map传给后台,进行数据保存
			String pdwealthNetValueId=	productService.addNetValueTaskInfo(tMap,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setObj(pdwealthNetValueId);
			resultInfo.setMsg("净值新增成功！");
			} catch (Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
			return  resultInfo;
			}
			return resultInfo;
		}
		

		/**
		 * 净值待办标记处理
		 * louao
		 * @return
		 */
		@RequestMapping(value = "/setNetValueTaskState", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo setNetValueTaskState(String param,ModelMap modelMap){
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo=new ResultInfo();
			String pdWealthNetValueTaskId = JsonUtils.getJsonValueByKey("netValueTaskId", param);
		    // 将pdWealthNetValueTaskId传到后台
		    resultInfo=productService.setNetValueTaskState(pdWealthNetValueTaskId,loginInfo);
			return resultInfo;
		}
		
		/**
		 * 创建期间分配到账短信
		 * @author ZYM
		 * @return
		 */
		@RequestMapping(value = "/createIncomeToAcctSms", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo createIncomeToAcctSms(String param,ModelMap modelMap){
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo=new ResultInfo();
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String distributeDate = JsonUtils.getJsonValueByKey("distributeDate", param);
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			paramMap.put("distributeDate", distributeDate);
		    // 将productId传到后台
		    resultInfo=productService.createIncomeToAcctSms(paramMap,loginInfo);
			return resultInfo;
		}
		
		/**
		 * 创建到期清算到账短信
		 * @author ZYM
		 * @return
		 */
		@RequestMapping(value = "/createAllIncomeToAcctSms", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo createAllIncomeToAcctSms(String param,ModelMap modelMap){
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo=new ResultInfo();
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String distributeDate = JsonUtils.getJsonValueByKey("distributeDate", param);
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			paramMap.put("distributeDate", distributeDate);
		    // 将productId传到后台
		    resultInfo=productService.createAllIncomeToAcctSms(paramMap,loginInfo);
			return resultInfo;
		}
		
		/**
		 * 获取固定产品查询页面
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/stockProductListUrl", method = RequestMethod.GET)
		public String stockProductListUrl(Model model) {
			return "fms/product/stockProductList";
		}
		
		/**
		 * 获取固定产品查询页面
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/fixedProductListUrl", method = RequestMethod.GET)
		public String fixedProductListUrl(Model model) {
			return "fms/product/fixedProductList";
		}
		
		/***
		 * 浮动产品查询页面
		 * @author cjj
		 */
		@RequestMapping(value = "/floatProductlistUrl", method = RequestMethod.GET)
		public String floatProductlistUrl() {
			return "fms/product/listFloatProductInfo";
		}
		
		/***
		 * 海外产品查询页面
		 * @author cjj
		 */
		@RequestMapping(value = "/overseasProductlistUrl", method = RequestMethod.GET)
		public String overseasProductlistUrl() {
			return "fms/product/listOverseasProductInfo";
		}
		
		
		/**
		 * 查询股权产品列表
		 * @param dgm
		 * @param param
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryStockProductList", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryStockProductList(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			try {
			if(param!=null&&!"".equals(param)){
				paramMap = JsonUtils.jsonStrToMap(param);
												}
			dataGrid = productService.queryStockProductList(dgm, paramMap);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 查询固定产品列表
		 * @param dgm
		 * @param param
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryFixedProductList", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryFixedProductList(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			try {
			if(param!=null&&!"".equals(param)){
				paramMap = JsonUtils.jsonStrToMap(param);
												}
			dataGrid = productService.queryFixedProductList(dgm, paramMap);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return dataGrid;
		}

		/**
		 * 查询浮动产品信息显示初始信息列表
		 * cjj
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryListFloatProductUrl", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryListFloatProductUrl(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			log.info("=================查询参数："+param);;
			try {
				if(param!=null&&!"".equals(param)){
					param = JsonUtils.decodeUrlParams(param, "utf-8");
					paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
					dataGrid = productService.getFloatProductPageList(dgm, paramMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		
		/**
		 * 查询海外产品信息显示初始信息列表
		 * cjj
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryListOverseasProductUrl", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryListOverseasProductUrl(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			log.info("=================查询参数："+param);;
			try {
				if(param!=null&&!"".equals(param)){
					param = JsonUtils.decodeUrlParams(param, "utf-8");
					paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
					dataGrid = productService.getOverseasProductPageList(dgm, paramMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 获取产品查询(浮动海外)产品净值走势页面
		 * cjj
		 * @return
		 */
		@RequestMapping(value = "/pdSearchProductNetValueUrl", method = RequestMethod.GET)
		public ModelAndView pdSearchProductNetValueUrl(@RequestParam("param") String param)
		{
			Map<String, String> reqParamMap = new HashMap<String, String>();
			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
			return new ModelAndView("fms/product/pdSearchProductNetValue",reqParamMap);
		}
		
		/**
		 * 产品查询(浮动海外)初始化走势图获取产品所有净值信息
		 * CJJ
		 */
		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
		@RequestMapping(value = "/getPdSearchNetValueList",method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo getPdSearchNetValueList(String param ,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		Map paramMap = new HashMap();
		try {
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String productSubType = JsonUtils.getJsonValueByKey("productSubType", param);
			String type = JsonUtils.getJsonValueByKey("type", param);
			paramMap.put("productId", productId);
			paramMap.put("productSubType", productSubType);
			paramMap.put("type", type);
			// 将组装好的Map传给后台,进行数据保存
			resultInfo = productService.getPdSearchNetValue(paramMap, loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
		/**
		 * 获取浮动产品查询产品净值走势页面
		 * 
		 * @return
		 */
//		@RequestMapping(value = "/pdSearchInvestProductNetValueUrl", method = RequestMethod.GET)
//		public ModelAndView pdSearchInvestProductNetValueUrl(@RequestParam("param") String param)
//		{
//			Map<String, String> reqParamMap = new HashMap<String, String>();
//			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
//			return new ModelAndView("fms/product/pdInvestSearchProductNetValue",reqParamMap);
//		}
		/**
		 * 获取产品查询产品详情页面
		 * 
		 * @return
		 */
		@RequestMapping(value = "/pdSearchInvestMentProductNetValueUrl", method = RequestMethod.GET)
		public ModelAndView pdSearchInvestMentProductNetValueUrl(@RequestParam("param") String param)
		{
			Map<String, String> reqParamMap = new HashMap<String, String>();
			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
			return new ModelAndView("fms/product/searchProductInvestMentDetail",reqParamMap);
		}
		/**
		 * 产品查询(浮动海外)初始化走势图获取产品所有净值信息
		 * CJJ
		 */
//		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
//		@RequestMapping(value = "/getPdInvestSearchNetValue",method = RequestMethod.POST)
//		@ResponseBody
//		public ResultInfo getPdInvestSearchNetValue(String param ,ModelMap modelMap) {
//		ResultInfo resultInfo = new ResultInfo();
//		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
//		Map<String, String> map = new HashMap<String, String>();
//		Map paramMap = new HashMap();
//		try {
//			String productId = JsonUtils.getJsonValueByKey("productId", param);
//			String productSubType = JsonUtils.getJsonValueByKey("productSubType", param);
//			String customerNo=JsonUtils.getJsonValueByKey("customerNo", param);
//			paramMap.put("productId", productId);
//			paramMap.put("productSubType", productSubType);
//			paramMap.put("customerNo", customerNo);
//			// 将组装好的Map传给后台,进行数据保存
//			resultInfo = productService.getInvestSearchNetValue(paramMap, loginInfo);
//			resultInfo.setSuccess(true);
//			resultInfo.setMsg("操作成功！");
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultInfo.setSuccess(false);
//			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
//		}
//		return resultInfo;
//	}
		
	/**
	 * 产品附件上传页面跳转
	 * cjj
	 * @return
	 */
	@RequestMapping(value = "/pdAppendixUploadUrl", method = RequestMethod.GET)
	public String pdAppendixUploadUrl(Model model) {
		return "fms/product/listPdAppendixUpload";
	}
	
	/**
	 * 产品查询菜单查询浮动、海外类产品净值列表
	 * cjj
	 * @param dgm
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/pdsearchNetValueUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid pdsearchNetValueUrl(String param, DataGridModel dgm) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if( param != null && !"".equals(param)){
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String productSubType = JsonUtils.getJsonValueByKey("productSubType", param);
			paramMap.put("productId", productId);
			paramMap.put("productSubType", productSubType);
		}
		dataGrid = productService.pdSearchNetValue(paramMap,dgm);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 获取产品查询详情页面跳转
	 * cjj
	 */
	@RequestMapping(value = "/searchProductPageUrl", method = RequestMethod.GET)
	public ModelAndView searchProductPageUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/product/searchProductDetail",reqParamMap);
	}
	
	/**
	 *产品查询，获取产品详细信息页面
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/searchProductDetailUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo searchProductDetailUrl(String param, ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			if (param == null || "".equals(param)) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品信息出错");
				return resultInfo;
			}
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String productSubType = JsonUtils.getJsonValueByKey("productSubType", param);
			paramMap.put("productId", productId);
			paramMap.put("productSubType", productSubType);
			resultInfo = productService.searchProductDetail(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取产品详细信息出错");
		}
		return resultInfo;
	}
		/**
		 * 合同管理页面跳转
		 * cjj 2017/5/3
		 * @return
		 */  
		@RequestMapping(value = "/pdContractInfoUrl", method = RequestMethod.GET)
		public String listPdContractInfoUrl(Model model) {
			return "fms/product/listPdContractInfo";
		}
		/**
		 * 新增合同页面跳转
		 * cjj 2017/5/3
		 * @return
		 */  
		@RequestMapping(value = "/addPdContractInfoUrl", method = RequestMethod.GET)
		public String addPdContractInfoUrl(Model model) {
			return "fms/product/addPdContractInfo";
		}
		
		/**
		 * 保存新增合同信息
		 * cjj 2017/5/3
		 * @return
		 */  
		@RequestMapping(value = "/addPdContractInfo", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo savePdContractInfo(String param, ModelMap modelMap) {
			ResultInfo resultInfo = new ResultInfo();
			try {
				log.info("==请求数据===" + param);
				LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
				PDContract contractInfo = new PDContract();
				contractInfo = (PDContract) JsonUtils.jsonStrToObject(param, contractInfo);
				resultInfo = productService.saveContractInfo(contractInfo, loginInfo);
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("保存客户信息出错");
			}
			return resultInfo;
		}
		
		/**
		 * 修改合同页面跳转
		 * ZYM 2017/5/5
		 * @return
		 */  
		@RequestMapping(value = "/updatePdContractInfoUrl", method = RequestMethod.GET)
		public ModelAndView updatePdContractInfoUrl(@RequestParam("param") String param) {
			log.info("==请求数据===" + param);
			Map<String, String> reqParamMap = new HashMap<String, String>();
			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
			return new ModelAndView("fms/product/updatePdContractInfo",reqParamMap);
		}
		
		/**
		 * 获取产品合同基本信息修改信息
		 * ZYM 2017/5/5
		 * @param param
		 * @param modelMap
		 * @return
		 */
		@RequestMapping(value = "/getUpdatePDContractInfo",produces = "application/json; charset=utf-8")
		@ResponseBody
		public ResultInfo getUpdatePDContractInfo(String param,ModelMap modelMap){
			ResultInfo resultInfo = new ResultInfo();
			log.info("------开始获取修改的产品合同信息:"+param);
			try {
				if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品合同详细信息出错");
				return resultInfo;
				}
				LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
				resultInfo = productService.getUpdatePDContractInfo(param, loginInfo);
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品合同信息出错");
			}
			return resultInfo;
		}
		
		/**
		 * 修改合同信息
		 * ZYM 2017/5/5
		 * @param modelMap
		 * @return
		 */
		@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
		@RequestMapping(value = "/updatePdContractInfo", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo updatePdContractInfo(String param,ModelMap modelMap) {
			LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo = new ResultInfo();
			Map<String, String> map = new HashMap<String, String>();
			HashMap tMap = new HashMap();
			try {
				String productId=JsonUtils.getJsonValueByKey("productId", param);
				
				tMap.put("productId", productId);
				/***获取已经修改的产品基本信息****/
				String pdContractInfo = JsonUtils.getJsonValueByKey("pdContractInfo", param);
				PDContract pdContract = new PDContract();
				pdContract = (PDContract) JsonUtils.jsonStrToObject(pdContractInfo, pdContract);
				tMap.put("pdContractInfo", pdContract);
				resultInfo = productService.updatePdContractInfo(tMap,loginInfo);
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("操作失败,原因是" + e.getMessage());
			}
			return resultInfo;
		}
		
		/**
		 * 查询合同产品信息显示初始信息列表
		 * @param dgm
		 * @param param
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryListContractInfo", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryListContractInfo(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			try {
			if( param!=null && !"".equals(param)){
			param = JsonUtils.decodeUrlParams(param, "utf-8");
			paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
			dataGrid = productService.getContractInfoPageList(dgm, paramMap);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 获取产品合同基本信息明细页面
		 * @return
		 */
		@RequestMapping(value = "/detailPdContractUrl", method = RequestMethod.GET)
		public ModelAndView detailPdContractUrl(@RequestParam("param") String param)
		{
			Map<String, String> reqParamMap = new HashMap<String, String>();
			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
			return new ModelAndView("fms/product/detailPdContractInfo",reqParamMap);
		}
		
		/**
		 * 产品合同详细信息
		 * @param dgm
		 * @param param
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryDetailContractInfo", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryDetailContractInfo(DataGridModel dgm, String param) {
			Map paramMap = new HashMap();
			DataGrid dataGrid = new DataGrid();
			try {
			if( param!=null && !"".equals(param)){
			param = JsonUtils.decodeUrlParams(param, "utf-8");
			paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
			dataGrid = productService.getDetailContractInfoPageList(dgm, paramMap);
			} catch (Exception e) {
			e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 删除产品合同
		 * @author ZYM
		 * @param param
		 * @param modelMap
		 * @return
		 */
		@RequestMapping(value = "/deleteContract",produces = "application/json; charset=utf-8")
		@ResponseBody
		public ResultInfo deleteContract(String param,ModelMap modelMap){
			ResultInfo resultInfo = new ResultInfo();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			try {
				log.info("==请求数据===" + param);
				if(param==null||"".equals(param)){
					return resultInfo;
				}
				List<PDContract> productContractList = JsonUtils.jsonArrStrToList(param, PDContract.class);
				for(PDContract pdContract:productContractList){
					String contractId = pdContract.getContractId().toString();
					resultInfo = productService.deleteContract(contractId, loginInfo);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("删除短信信息出错");
			}
			return resultInfo;
		}
		
		/**
		 * 导出产品合同号详情信息
		 * */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value="/contractInfoDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
		public ModelAndView contractInfoDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {

			// 输出日志，便于调试
			log.info("接受参数queryParam:" + queryParam);
			// 获取业务报表导出Excel模板
			String path = System.getProperty("fms.webroot");
			path = path+"/WEB-INF/classes/reportTemplate/contractInfoDetaill.xls";
			// 分配ResultInfo对象返回程序异常信息
			ResultInfo resultInfo = new ResultInfo();
			try {
				// 查询参数解码
				queryParam = StringUtils.encodeStr(queryParam);
				// 查询参数转成Map集合
				Map paramMap = JsonUtils.jsonStrToMap(queryParam);
				// 调用Service层方法统计客户基本信息
				Map  datas =  this.productService.getContractInfoDetail(paramMap);
				// 调用Excel模板生成Excel业务报表
				OutputStream out=null;
		        BufferedOutputStream bos=null;
		        BufferedInputStream bis=null;
		        InputStream in=null;
		        try{
			        in=ExcelTool.exportExcel(path, datas);
			        bis=new BufferedInputStream(in);
			        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
			        String fileName = "contractInfoDetaill"+now+".xls";
			      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
			        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			        byte[] data=new byte[1024];
			        int bytes=0;
			        out=response.getOutputStream();
			        bos=new BufferedOutputStream(out);
			        while((bytes=bis.read(data, 0, data.length))!=-1){
			            bos.write(data,0,bytes);                                        //写出文件流                                     
			        }
			        bos.flush();
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally{
		            try {
		                bos.close();
		                out.close();
		                bis.close(); 
		                in.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("导出产品合同信息失败！");
			}
			return null;
		}
	
	/**
	 * 产品底层材料附件上传页面跳转
	 * wh
	 * @return
	 */
	@RequestMapping(value = "/pdFloorMaterialUrl", method = RequestMethod.GET)
	public String pdFloorMaterialUrl(Model model) {
		return "fms/product/listPdFloorMaterial";
	}
	
	///////////////////////////////股权产品投后清单//////////////////////////////
	/**
	 * 跳转至产品投后清单页面
	 */
	@RequestMapping(value = "/listStockProductIncomeUrl", method = RequestMethod.GET)
	public String listStockProductIncomeUrl() {
		return "fms/product/listStockProductIncome";
	}
	
	  /**
	    * 查询股权产品投后清单
	    * @param dgm
	    * @param queryParam
	    * @return
	    */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/getStockProductIncomeUrl", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid getStockProductIncomeUrl(DataGridModel dgm,String param){
			DataGrid dataGrid = new DataGrid();
			try {
				Map paramMap = JsonUtils.jsonStrToMap(param);
				dataGrid = productService.getAlltStockProductIncomeInfo(dgm,paramMap);
			} catch (CisCoreException e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 查询股权产品投后详单
		 * @param dgm
		 * @param queryParams
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		@RequestMapping(value = "/queryDetailStockProIncomeList", method = RequestMethod.POST)
		@ResponseBody
		public DataGrid queryDetailStockProIncomeList(DataGridModel dgm,@RequestParam("param")String param){
			DataGrid dataGrid = new DataGrid();
			try {
				Map paramMap = JsonUtils.jsonStrToMap(param);
				dataGrid = productService.queryDetailStockProIncomeList(dgm,paramMap);
			} catch (CisCoreException e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 股权产品投后清单导出
		 * @param queryParam
		 * @param request
		 * @param response
		 * @param modelMap
		 * @return
		 */
		@SuppressWarnings("unchecked")
		@RequestMapping(value="/stockProductIncomeDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
		public ModelAndView exportStockProIncomeInfo(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
			// 输出日志，便于调试
			log.info("Controller层类中exportStockProIncomeInfo()接受参数queryParam:" + queryParam);
			// 获取业务报表导出Excel模板
			String path = System.getProperty("fms.webroot");
			path = path+"/WEB-INF/classes/reportTemplate/stockProductIncomeInfo.xls";
			ResultInfo resultInfo = new ResultInfo();
			try {
				// 查询参数转成Map集合
				Map paramMap = JsonUtils.jsonStrToMap(queryParam);
				//获取固收产品信息Data
				Map stockProData = this.productService.exportStockProIncomeInfo(paramMap);
				// 调用Excel模板生成Excel业务报表
				OutputStream out=null;
		        BufferedOutputStream bos=null;
		        BufferedInputStream bis=null;
		        InputStream in=null;
		        try {
		        	in=ExcelTool.exportExcel(path, stockProData);
			        bis=new BufferedInputStream(in);
			        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
			        String fileName = "stockProductIncomeInfo"+now+".xls";
			        //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
			        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			        byte[] data=new byte[1024];
			        int bytes=0;
			        out=response.getOutputStream();
			        bos=new BufferedOutputStream(out);
			        while((bytes=bis.read(data, 0, data.length))!=-1){
			            bos.write(data,0,bytes);                                        //写出文件流                                     
			        }
			        bos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
		                bos.close();
		                out.close();
		                bis.close(); 
		                in.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("导出固收产品投后清单信息出现异常！");
			}
			return null;
		}
		
		/**
		 * 	下载股权投后清单详情
		 * */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value="/stockProductDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
		public ModelAndView stockProductDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
			// 输出日志，便于调试
			log.info("Controller层类中stockProductDetail()接受参数queryParam:" + queryParam);
			// 获取业务报表导出Excel模板
			String path = System.getProperty("fms.webroot");
			path = path+"/WEB-INF/classes/reportTemplate/stockProductDetail.xls";
			// 分配ResultInfo对象返回程序异常信息
			ResultInfo resultInfo = new ResultInfo();
			// 
			try {
				// 查询参数解码
				queryParam = StringUtils.encodeStr(queryParam);
				// 查询参数转成Map集合
				Map paramMap = JsonUtils.jsonStrToMap(queryParam);
				// 调用Service层方法统计产品预约信息
				Map  datas = productService.stockProductDetail(paramMap);
				// 调用Excel模板生成Excel业务报表
				OutputStream out=null;
		        BufferedOutputStream bos=null;
		        BufferedInputStream bis=null;
		        InputStream in=null;
		        try{
			        in=ExcelTool.exportExcel(path, datas);
			        bis=new BufferedInputStream(in);
			        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
			        String fileName = "stockProductDetail"+now+".xls";
			      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
			        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			        byte[] data=new byte[1024];
			        int bytes=0;
			        out=response.getOutputStream();
			        bos=new BufferedOutputStream(out);
			        while((bytes=bis.read(data, 0, data.length))!=-1){
			            bos.write(data,0,bytes);                                        //写出文件流                                     
			        }
			        bos.flush();
		        }catch(Exception e){
		            e.printStackTrace();
		        }finally{
		            try {
		                bos.close();
		                out.close();
		                bis.close(); 
		                in.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("下载固收产品投后清单详情出现异常！");
			}
			return null;
		}
		
		/**
		 * 固收产品头后清单详细信息页面
		 * @param param
		 * @return
		 */
		@RequestMapping(value = "/detailStockProIncomeUrl", method = RequestMethod.GET)
		public ModelAndView detailStockProIncomeUrl(@RequestParam("param") String param)
		{
			Map<String, String> reqParamMap = new HashMap<String, String>();
			reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
			return new ModelAndView("fms/product/detailStockProIncome",reqParamMap);
		}
		
		
		/**
		 * 产品查询中客户投资记录
		 * @param searchParam
		 * @param modelMap
		 * @return
		 */
		@SuppressWarnings({ "rawtypes" })
		@RequestMapping(value = "/queryCustInvestList",produces = "application/json; charset=utf-8")
		@ResponseBody
		public DataGrid queryCustInvestList(DataGridModel dgm,String productId,ModelMap modelMap){
			DataGrid dataGrid = new DataGrid();
			try {
				log.info("==请求数据===" + productId);
				Map paramMap = new HashMap();
				paramMap.put("productId", productId);
				//获取用户登录信息			
				LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
				
				dataGrid = productService.queryCustInvestList(dgm, paramMap, loginInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
		
		/**
		 * 客户投资记录导出
		 * @param queryParam
		 * @param request
		 * @param response
		 * @param modelMap
		 * @return
		 */
		@SuppressWarnings("unchecked")
		@RequestMapping(value="/detialCustInvestInfo.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
		public ModelAndView detialCustInvestInfo(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
			// 输出日志，便于调试
			log.info("Controller层类中exportCustInvestInfo()接受参数queryParam:" + queryParam);
			// 获取业务报表导出Excel模板
			String path = System.getProperty("fms.webroot");
			path = path+"/WEB-INF/classes/reportTemplate/detialCustInvestInfo.xls";
			ResultInfo resultInfo = new ResultInfo();
			try {
				// 查询参数转成Map集合
				Map paramMap = JsonUtils.jsonStrToMap(queryParam);
				//获取固收产品信息Data
				Map custInvestData = this.productService.exportDetialCustInvestInfo(paramMap);
				// 调用Excel模板生成Excel业务报表
				OutputStream out=null;
		        BufferedOutputStream bos=null;
		        BufferedInputStream bis=null;
		        InputStream in=null;
		        try {
		        	in=ExcelTool.exportExcel(path, custInvestData);
			        bis=new BufferedInputStream(in);
			        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
			        String fileName = "detialCustInvestInfo"+now+".xls";
			        //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
			        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			        byte[] data=new byte[1024];
			        int bytes=0;
			        out=response.getOutputStream();
			        bos=new BufferedOutputStream(out);
			        while((bytes=bis.read(data, 0, data.length))!=-1){
			            bos.write(data,0,bytes);                                        //写出文件流                                     
			        }
			        bos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
		                bos.close();
		                out.close();
		                bis.close(); 
		                in.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultInfo.setSuccess(false);
				resultInfo.setMsg("导出客户认购记录信息出现异常！");
			}
			return null;
		}
		
		/**
		 * 产品删除
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@RequestMapping(value = "/productDelete", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo productDelete(String productId,ModelMap model) {
			Map paramMap=new HashMap();
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo=new ResultInfo();
				try{
					resultInfo=productService.productDelete(productId,loginInfo);//产品同步互联网
					}
					catch(Exception e){
						e.printStackTrace();
						resultInfo.setSuccess(false);
						resultInfo.setMsg("产品删除失败，原因是："+e.getMessage());
						return resultInfo;
										}
				return resultInfo;
			}
		
		/**
		 * 创股权产品分配短信
		 * @author ZYM
		 * @return
		 */
		@RequestMapping(value = "/createStockDisSms", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo createStockDisSms(String param,ModelMap modelMap){
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo=new ResultInfo();
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String distributeDate = JsonUtils.getJsonValueByKey("distributeDate", param);
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			paramMap.put("distributeDate", distributeDate);
		    // 将productId传到后台
		    resultInfo=productService.createStockDisSms(paramMap,loginInfo);
		    resultInfo.setSuccess(true);
		    resultInfo.setMsg("创建成功！");
			return resultInfo;
		}
		
		
		/**
		 * 创股权产品分配短信
		 * @author ZYM
		 * @return
		 */
		@RequestMapping(value = "/createStockToAccSms", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo createStockToAccSms(String param,ModelMap modelMap){
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			ResultInfo resultInfo=new ResultInfo();
			String productId = JsonUtils.getJsonValueByKey("productId", param);
			String distributeDate = JsonUtils.getJsonValueByKey("distributeDate", param);
			Map paramMap = new HashMap();
			paramMap.put("productId", productId);
			paramMap.put("distributeDate", distributeDate);
		    // 将productId传到后台
		    resultInfo=productService.createStockToAccSms(paramMap,loginInfo);
		    resultInfo.setSuccess(true);
		    resultInfo.setMsg("创建成功！");
			return resultInfo;
		}
		
		/**
		 * 删除预约额度分配
		 * @param productId
		 * @return
		 */
		@RequestMapping(value = "/deleteProductAmountDisInfo", method = RequestMethod.POST)
		@ResponseBody
		public ResultInfo deleteProductAmountDisInfo(String param,ModelMap modelMap){
			ResultInfo resultInfo = new ResultInfo();
			LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
			try {
				if (param!=null) {
					Map paramMap = new HashMap();
					String productId = JsonUtils.getJsonValueByKey("productId", param);
					String expectOpenDay = JsonUtils.getJsonValueByKey("expectOpenDay", param);
					paramMap.put("productId", productId);
					paramMap.put("expectOpenDay", expectOpenDay);
					resultInfo = productService.deleteProductAmountDisInfo(paramMap,loginInfo);
				}
				else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取产品流水号失败，无法删除");
				return resultInfo;
				}
			} catch (Exception e) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("未获取到产品信息！原因是：" + e.getMessage());
				e.printStackTrace();
			}
			return resultInfo;
		}
		
		/**
		 * 浮动海外产品查询中客户投资记录
		 * @param searchParam
		 * @param modelMap
		 * @return
		 */
		@SuppressWarnings({ "rawtypes" })
		@RequestMapping(value = "/queryCustFloatInvestList",produces = "application/json; charset=utf-8")
		@ResponseBody
		public DataGrid queryCustFloatInvestList(DataGridModel dgm,String productId,ModelMap modelMap){
			DataGrid dataGrid = new DataGrid();
			try {
				log.info("==请求数据===" + productId);
				Map paramMap = new HashMap();
				paramMap.put("productId", productId);
				//获取用户登录信息			
				LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
				
				dataGrid = productService.queryCustFloatInvestList(dgm, paramMap, loginInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dataGrid;
		}
}
