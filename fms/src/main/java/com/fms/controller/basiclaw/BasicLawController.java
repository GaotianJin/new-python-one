package com.fms.controller.basiclaw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.fms.db.model.BasicLaw;
import com.fms.db.model.BasicLawAssess;
import com.fms.db.model.BasicLawIns;
import com.fms.db.model.BasicLawWealth;
import com.fms.db.model.BasicLawYB;
import com.fms.service.basiclaw.BasicLawService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Controller
@RequestMapping("/basicLaw")
//将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class BasicLawController 
{
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger log = Logger.getLogger(BasicLawController.class);
	
	@Autowired
	private BasicLawService basicLawService;
	
	/**************************************基本法版本参数相关*************************************************/
	
    /**
     * 获取基本法版本参数url 
     */
    @RequestMapping(value = "/listBasicLawVersionUrl", method = RequestMethod.GET)
    public String listBasicLawVersionUrl() {
        return "fms/basiclaw/listBasicLawVersion";
    }
    
    /**
     * 获取增加基本法版本参数页面url
     */
    @RequestMapping(value = "/addBasicLawVersionUrl", method = RequestMethod.GET)
    public String addBasicLawVersionUrl() {
        return "fms/basiclaw/addBasicLawVersion";
    }
    /**
     * 获取更新基本法版本参数页面url
     */
    @RequestMapping(value = "/updateBasicLawVersionUrl", method = RequestMethod.GET)
    public String updateBasicLawVersionUrl() {
        return "fms/basiclaw/updateBasicLawVersion";
    }
    /**
     * 获取明细基本法版本参数页面url
     */
    @RequestMapping(value = "/detailBasicLawVersionUrl", method = RequestMethod.GET)
    public String detailBasicLawVersionUrl() {
        return "fms/basiclaw/detailBasicLawVersion";
    }
    
    
	/**
	 * 基本法版本信息新增处理
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveAddBasicLawVersionUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAddBasicLawVersion(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("param===" + param);
			String basicLawVersionInfo = JsonUtils.getJsonValueByKey("basicLawVersionInfo", param);
			BasicLaw  basicLawInfo = (BasicLaw) JsonUtils.jsonStrToObject(basicLawVersionInfo, BasicLaw.class);
			tMap.put("basicLawInfo", basicLawInfo);
			this.basicLawService.addBasicLawVersionSave(tMap, loginInfo);
			map.put("msg", "新增保存成功!");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "新增保存失败,原因是:" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 基本法版本信息更新处理
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveUpdateBasicLawVersionUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUpdateBasicLawVersion(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("param===" + param);
			String basicLawVersionInfo = JsonUtils.getJsonValueByKey("basicLawVersionInfo", param);
			BasicLaw  basicLawInfo = (BasicLaw) JsonUtils.jsonStrToObject(basicLawVersionInfo, BasicLaw.class);
			tMap.put("basicLawInfo", basicLawInfo);
			this.basicLawService.updateBasicLawVersionSave(tMap, loginInfo);
			map.put("msg", "更新保存成功!");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "更新保存失败,原因是:" + e.getMessage());
		}
		return map;
	}
	
	/**
	 *基本法版本参数删除事件
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/saveDeleteBasicLawVersionUrl")
	@ResponseBody
	public ResultInfo saveDeleteBasicLawVersionUrl(@RequestParam("basicLawId") List<Integer> basicLawId,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo=new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			for (Integer id : basicLawId)
			{
				this.basicLawService.deleteBasicLawVersionSave(new Long(id),loginInfo);
			}
			resultInfo.setMsg("删除成功！");
			resultInfo.setSuccess(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			resultInfo.setMsg("删除保存失败,原因是:"+e.getMessage());
			resultInfo.setSuccess(false);
		}
		return resultInfo;// 重定向
	}
	
	/**
	 * 获取更新基本法版本信息url
	 * @return 
	 */
	@RequestMapping(value = "/queryBasicLawVersionInfoEntify", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryBasicLawVersionInfoEntify(@RequestParam("basicLawId") String basicLawId)
	{
		BasicLaw basicLaw = this.basicLawService.getBasicLawVersionInfoById(new Long(basicLawId));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("basicLaw", basicLaw);
		return JsonUtils.objectToJsonStr(map);
	}
	
	/**
	 *	基本法版本信息维护页面，列表信息查询（分页）
	 */
	@RequestMapping(value = "/queryBasicLawVersionList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryBasicLawVersionList (DataGridModel dgm,String queryParam ,ModelMap modelMap){
		Map<String,Object> tMap = null;
		try 
		{
			Map paramMap = new HashMap();
			if(queryParam!=null&&!"".equals(queryParam)){
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			tMap = this.basicLawService.getBasicLawVersionPageList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tMap;
	}
	
/***********************************************基本法产品参数相关*************************************************/

    /**
     * 获取基本法产品参数url 
     */
    @RequestMapping(value = "/listBasicLawProductUrl", method = RequestMethod.GET)
    public String listBasicLawProductUrl() {
        return "fms/basiclaw/listBasicLawProduct";
    }
    
    /**
     * 获取增加基本法产品参数页面url
     */
    @RequestMapping(value = "/addBasicLawProductUrl", method = RequestMethod.GET)
    public String addBasicLawProductUrl() {
        return "fms/basiclaw/addBasicLawProduct";
    }
    
    /**
     * 获取更新基本法产品参数页面url
     */
    @RequestMapping(value = "/updateBasicLawProductUrl", method = RequestMethod.GET)
    public String updateBasicLawProductUrl() {
        return "fms/basiclaw/updateBasicLawProduct";
    }
    /**
     * 获取更新基本法产品参数页面url
     */
    @RequestMapping(value = "/detailBasicLawProductUrl", method = RequestMethod.GET)
    public String detailBasicLawProductUrl() {
        return "fms/basiclaw/detailBasicLawProduct";
    }
    
    /**
	 * 获取更新基本法版本信息url
	 * @return 
	 */
	@RequestMapping(value = "/queryBasicLawProductVersionInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryBasicLawProductVersionInfo(@RequestParam("list") String bp)
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		try
		{
			paramMap = objectMapper.readValue(bp, HashMap[].class)[0];
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Map<String,Object> map = this.basicLawService.queryBasicLawProductVersionInfo(paramMap);
		return JsonUtils.objectToJsonStr(map);
	}
    
    /**
	 *	基本法产品参数信息维护页面，列表信息查询（分页）
	 */
	@RequestMapping(value = "/queryBasicLawProductList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryBasicLawProductList (DataGridModel dgm,String queryParam ,ModelMap modelMap){
		Map<String,Object> tMap = null;
		try 
		{
			Map paramMap = new HashMap();
			if(queryParam!=null&&!"".equals(queryParam)){
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			tMap = this.basicLawService.getBasicLawProductPageList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tMap;
	}
    
    
    /**
	 * 基本法产品参数新增页面，提交事件
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveAddBasicLawProductUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAddBasicLawProduct(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("基本法产品参数新增开始");
			log.info("param===" + param);
			String basicLawFormInfo = JsonUtils.getJsonValueByKey("basicLawFormInfo", param);
			BasicLaw  basicLawInfo = (BasicLaw) JsonUtils.jsonStrToObject(basicLawFormInfo, BasicLaw.class);
			String productType = JsonUtils.getJsonValueByKey("productType", basicLawFormInfo);
			String productSubType = JsonUtils.getJsonValueByKey("productSubType", basicLawFormInfo);
			String productId = JsonUtils.getJsonValueByKey("productId", basicLawFormInfo);
			// 校验该产品是否已经存在有效的基本法版本
			if (productType != null && productSubType != null) {
				// 产品为个人寿险时
				if (productType.equals("2") && productSubType.equals("01")) {
					Boolean verifyFlag = basicLawService.verifyBasicExist(productType, productSubType, "");
					if (!verifyFlag) {
						map.put("msg", "个人寿险已经存在已分配的基本法版本，如需重新添加，请将已分配的个人寿险版本信息删除!");
						map.put("succ", "false");
						return map;
					}
				} else {
					if (productId != null) {
						Boolean verifyFlag = basicLawService.verifyBasicExist(productType, productSubType, productId);
						if (!verifyFlag) {
							map.put("msg","该产品已经存在已分配的基本法版本，如需重新添加，请将已分配已的产品版本信息删除!");
							map.put("succ", "false");
							return map;
						}
					}
				}
			}
			tMap.put("productType", productType);
			tMap.put("productSubType", productSubType);
			tMap.put("productId", productId);
			tMap.put("basicLawInfo", basicLawInfo);
			//产品考核参数
			String basicLawAssessInfo = JsonUtils.getJsonValueByKey("basicLawAssessInfo",param);
			List<BasicLawAssess> listBasicLawAssess =  JsonUtils.jsonArrStrToList(basicLawAssessInfo,BasicLawAssess.class);
			tMap.put("listBasicLawAssess", listBasicLawAssess);
			
			//财富产品
			if("1".equals(productType))
			{
				//财富产品薪资参数-奖金比例
				String basicLawWealthInfo = JsonUtils.getJsonValueByKey("basicLawWealthInfo",param);
				List<BasicLawWealth> listBasicLawWealth =  JsonUtils.jsonArrStrToList(basicLawWealthInfo,BasicLawWealth.class);
				tMap.put("listBasicLawWealth", listBasicLawWealth);
				basicLawService.addBasicLawProductWealthSave(tMap, loginInfo);
			}
			
			//保险产品
			if("2".equals(productType))
			{
				//个人寿险薪资参数-奖金比例
				if("01".equals(productSubType))
				{
					String BasicLawInsInfo = JsonUtils.getJsonValueByKey("basicLawInsInfo",param);
					List<BasicLawIns> listBasicLawIns =  JsonUtils.jsonArrStrToList(BasicLawInsInfo,BasicLawIns.class);
					tMap.put("listBasicLawIns", listBasicLawIns);
					basicLawService.addBasicLawProductInsSave(tMap, loginInfo);
				}
				
				//车险薪资参数-奖金比例
				if("02".equals(productSubType))
				{
					basicLawService.addBasicLawProductVehicleSave(tMap, loginInfo);
				}
				
				//银行保险薪资参数-奖金比例
				if("03".equals(productSubType))
				{
					String basicLawYBInfo = JsonUtils.getJsonValueByKey("basicLawYBInfo",param);
					List<BasicLawYB> listBasicLawYB =  JsonUtils.jsonArrStrToList(basicLawYBInfo,BasicLawYB.class);
					tMap.put("listBasicLawYB", listBasicLawYB);
					basicLawService.addBasicLawProductYBSave(tMap, loginInfo);
				}
			}
			
			map.put("msg", "新增保存成功!");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "新增保存失败,原因是:" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 基本法产品参数新增页面，提交事件
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveUpdateBasicLawProductUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUpdateBasicLawProduct(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{	
			log.info("基本法产品参数更新开始");
			log.info("param===" + param);
			String basicLawFormInfo = JsonUtils.getJsonValueByKey("basicLawFormInfo", param);
			String oldBasicLawId = JsonUtils.getJsonValueByKey("oldBasicLawId", param);
			BasicLaw  basicLawInfo = (BasicLaw) JsonUtils.jsonStrToObject(basicLawFormInfo, BasicLaw.class);
			
			String productType = JsonUtils.getJsonValueByKey("productType", basicLawFormInfo);
			String productSubType = JsonUtils.getJsonValueByKey("productSubType", basicLawFormInfo);
			String productId = JsonUtils.getJsonValueByKey("productId", basicLawFormInfo);
			log.info("productId:::"+productId);
			log.info("basicLawInfo:::"+basicLawInfo);
			tMap.put("productId", productId);
			tMap.put("basicLawInfo", basicLawInfo);
			tMap.put("oldBasicLawId", oldBasicLawId);
			//产品考核参数
			String basicLawAssessInfo = JsonUtils.getJsonValueByKey("basicLawAssessInfo",param);
			List<BasicLawAssess> listBasicLawAssess =  JsonUtils.jsonArrStrToList(basicLawAssessInfo,BasicLawAssess.class);
			tMap.put("listBasicLawAssess", listBasicLawAssess);

			
			//财富产品
			if("1".equals(productType))
			{
				//财富产品薪资参数-奖金比例
				String basicLawWealthInfo = JsonUtils.getJsonValueByKey("basicLawWealthInfo",param);
				List<BasicLawWealth> listBasicLawWealth =  JsonUtils.jsonArrStrToList(basicLawWealthInfo,BasicLawWealth.class);
				tMap.put("listBasicLawWealth", listBasicLawWealth);
				basicLawService.updateBasicLawProductWealthSave(tMap, loginInfo);
			}
			
			//保险产品
			if("2".equals(productType))
			{
				//个人寿险薪资参数-奖金比例
				if("01".equals(productSubType))
				{
					String BasicLawInsInfo = JsonUtils.getJsonValueByKey("basicLawInsInfo",param);
					List<BasicLawIns> listBasicLawIns =  JsonUtils.jsonArrStrToList(BasicLawInsInfo,BasicLawIns.class);
					tMap.put("listBasicLawIns", listBasicLawIns);
					basicLawService.updateBasicLawProductInsSave(tMap, loginInfo);
				}
				//车险薪资参数-奖金比例
				if("02".equals(productSubType))
				{
					basicLawService.updateBasicLawProductVehicleSave(tMap, loginInfo);
				}
				//银行保险薪资参数-奖金比例
				if("03".equals(productSubType))
				{
					String basicLawYBInfo = JsonUtils.getJsonValueByKey("basicLawYBInfo",param);
					List<BasicLawYB> listBasicLawYB =  JsonUtils.jsonArrStrToList(basicLawYBInfo,BasicLawYB.class);
					tMap.put("listBasicLawYB", listBasicLawYB);
					basicLawService.updateBasicLawProductYBSave(tMap, loginInfo);
				}
			}
			
			map.put("msg", "更新保存成功!");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "更新保存失败,原因是:" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 获取更新基本法版本信息url
	 * @return 
	 */
	@RequestMapping(value = "/saveDeleteBasicLawProductUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map saveDeleteBasicLawProduct(@RequestParam("list") String bp,ModelMap model)
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> map = new HashMap<String,Object>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			paramMap = objectMapper.readValue(bp, HashMap[].class)[0];
			this.basicLawService.deleteBasicLawProductSave(paramMap,loginInfo);
			map.put("msg", "删除保存成功!");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "删除保存失败,原因是:"+e.getMessage());
		}
		return map;
	}
}
