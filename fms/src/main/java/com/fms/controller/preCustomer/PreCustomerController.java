package com.fms.controller.preCustomer;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;
import com.fms.controller.customer.CustomerController;
import com.fms.db.model.PRECustomerBaseInfo;
import com.fms.db.model.PRECustomerVistorInfo;
import com.fms.service.common.CodeQueryService;

import com.fms.service.branch.BuildingService;
import com.fms.service.preCustomer.PreCustomerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;


@Controller
@RequestMapping("/preCustomer")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class PreCustomerController {
	
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private PreCustomerService preCustomerService;
	@Autowired
	private CodeQueryService codeQueryService;
	
	private static final Logger log = Logger.getLogger(CustomerController.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	/**
	 * 静态方法：解决Controller层接收url中文参数时出现的乱码问题
	 * @author Administrator
	 */
	public static String EncodedString(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取准客户信息
	 * @return
	 */
	@RequestMapping(value = "/listPreCustomerUrl", method = RequestMethod.GET)
	public String list() {
		return "fms/preCustomer/listPreCustomer";
	}
	/**
	 * 获取活动量信息
	 * @return
	 */
	@RequestMapping(value = "/listPreCustActivityUrl", method = RequestMethod.GET)
	public String listActivity() {
		return "fms/preCustomer/listPreCustActivity";
	}
	/**
	 * 更新准客户信息
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/updatePreCustomerUrl",method = RequestMethod.GET)
	public ModelAndView updatePreCustomerUrl(@RequestParam("param") String param){
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/preCustomer/updatePreCustomer",reqParamMap);
	}
	/**
     * 获取"增加准客户信息页面"
     * @author LiWenTao
     * @param
     * @return String 准客户增加页面addPreCustomerBasicInfo.jsp的URL
     * 对应"增加"按钮事件
     */
    @RequestMapping(value = "/addPreCustomerBaicInfoUrl", method = RequestMethod.GET)
    public String PreCustomerBaicInfoUrl() {
        return "fms/preCustomer/addPreCustomerBasicInfo";
    }
	
    /**
     * 获取"准客户信息详情页面"
     * @author LiWenTao
     * @param value
     * @return string
     */
    @RequestMapping(value = "/detailPreCustomerUrl", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public ModelAndView detailPreCustomerUrl(@RequestParam("param")String param) {
    	log.info("==请求数据===" + param);
    	try {
			param =  java.net.URLDecoder.decode(param,"UTF-8");
			param =  java.net.URLDecoder.decode(param,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/preCustomer/showPreCustomerBasicInfo",reqParamMap);
    }
    
    /**
	 * 楼盘信息下拉项，初始化事件
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryBuildListCode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryBuildListCode(){
		// 获取楼盘信息列表
		List<Map> listBuidCode = buildingService.getBuildListCode();
		// 分配codeMap对象
		Map buildMap = null;
		for (int i = 0; i < listBuidCode.size(); ++i) {
			Map codeMap = new HashMap();
			buildMap = listBuidCode.get(i);
			Object objId = buildMap.get("id");
			codeMap.put("code", objId);
			Object buildObj = buildMap.get("name");
			codeMap.put("codeName", buildObj);
			// 将codeMap对象替换原来同样位置的对象
			listBuidCode.set(i, codeMap);
		}
		String codeMapJson = JsonUtils.objectToJsonStr(listBuidCode);
		return codeMapJson;
	}
	
	/**
	 * 根据codeType获取T_Def_Code表中的数据
	 */
	@RequestMapping(value = "/tdCodeQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String codeQuery(String codeType){
		List<Map<String, String>> codeMapList = codeQueryService.tdCodeQuery(codeType);
		String codeMapJson = JsonUtils.objectToJsonStr(codeMapList);
		return codeMapJson;
	}
	/**
	 * 根据准客户Id初始化信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getPreCustomerInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryProductCoreInfo(@RequestParam("param") String param){
		ResultInfo resultInfo = new ResultInfo();
		log.info("------开始获取修改的准客户信息:"+param);
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取准客户详细信息出错");
		return resultInfo;
		}
		resultInfo = preCustomerService.getPreCustomerInfo(param);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取准客户详细信息出错");
		}
		return resultInfo;
	}

	/**
	 * 保存准客户修改的基本信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/keepPreCustomerUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo keepPreCustomerInfoUrl(String param,ModelMap modelMap) {
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String updatePreCustBaseInfoId=JsonUtils.getJsonValueByKey("updatePreCustBaseInfoId", param);
		tMap.put("updatePreCustBaseInfoId", updatePreCustBaseInfoId);
		String preCustBaseInfo = JsonUtils.getJsonValueByKey("preCustBaseInfo", param);
		PRECustomerBaseInfo  preCustomerBaseInfo = gson.fromJson(preCustBaseInfo,PRECustomerBaseInfo.class);
		tMap.put("preCustomerBaseInfo", preCustomerBaseInfo);
		String preCustVistorInfo = JsonUtils.getJsonValueByKey("preCustVistorInfo",param);
		//PRECustomerVistorInfo preCustomerVistorInfo = gson.fromJson(preCustVistorInfo,PRECustomerVistorInfo.class);
		List<PRECustomerVistorInfo> preCustomerVistorInfos =  JsonUtils.jsonArrStrToList(preCustVistorInfo,PRECustomerVistorInfo.class);
		tMap.put("preCustomerVistorInfos", preCustomerVistorInfos);
	    resultInfo=preCustomerService.keepPreCustomerInfo(tMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 查询准客户信息列表
	 * @param dgm
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryPreCustomerListUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryPreCustomerListUrl(DataGridModel dgm, String param,ModelMap modelMap) {
		log.info("****************查询准客户信息*******************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		param = JsonUtils.decodeUrlParams(param, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
		dataGrid = preCustomerService.queryPreCustomerList(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
    /**
	 * "准客户基本信息和拜访信息"保存函数
	 * @author LiWenTao
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/saveAddPreCustomerBasicInfoUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAddPreCustomerBasicInfo(@RequestParam("param")String param,ModelMap model)
	{
    
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("用户点击提交请求时传入param为：" + param);
			param = java.net.URLDecoder.decode(param, "UTF-8");
			String preCustomerBasicInfoFormInfo = JsonUtils.getJsonValueByKey("preCustomerBasicInfo", param);
			PRECustomerBaseInfo  preCustomerBaseInfo = (PRECustomerBaseInfo) JsonUtils.jsonStrToObject(preCustomerBasicInfoFormInfo, PRECustomerBaseInfo.class);
			tMap.put("preCustomerBaseInfo", preCustomerBaseInfo);
			String preCustomerVisitInfo = JsonUtils.getJsonValueByKey("preCustomerVisitInfo",param);
			List<PRECustomerVistorInfo> listPreCustomerVistorInfo =  JsonUtils.jsonArrStrToList(preCustomerVisitInfo,PRECustomerVistorInfo.class);
			tMap.put("listPreCustomerVistorInfo", listPreCustomerVistorInfo);
			map = preCustomerService.addPreCustomerBasicInfoSave(tMap, loginInfo);
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
	 * 校验该操作人与客户所属的理该顾问是否是本人
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/updateVerifyPreCustomerUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateVerifyPreCustomer(String param, ModelMap modelMap) {
		log.info("****************点击修改,校验该操作人与客户所属的理该顾问是否是本人********************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		Map tMap = new HashMap();
		try {
		String agentId = JsonUtils.getJsonValueByKey("modifyAgentId", param);
		tMap.put("agentId", agentId);
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = preCustomerService.updateVerifyPreCustomer(tMap, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	

	/**
	 * 校验该操作人是否是财富顾问
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addVerifyPreAgentUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addVerifyPreAgentUrl(String param, ModelMap modelMap) {
		log.info("****************点击新增,校验该操作人是不是本人********************");
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try {
		// 将组装好的Map传给后台,进行数据保存
		resultInfo = preCustomerService.addVerifyPreAgent(loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	
	/**
	 * "准客户基本信息"删除函数
	 * @author LiWenTao
	 * @param  String 存储客户端页面传入数据
	 * @param  ModeMap
	 * @return Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/deletePreCustomerBasicInfoUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map deletePreCustomerBasicInfo(@RequestParam("list") String bp, ModelMap model)
	{
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		HashMap<String,Object> map     =  new HashMap<String,Object>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			paramMap = objectMapper.readValue(bp, HashMap[].class)[0];
			return this.preCustomerService.deletePreCustomerBasicInfo(paramMap,loginInfo);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "删除保存失败,原因是:"+e.getMessage());
			return map;
		}
	}
	/**
	 * 准客户活动量信息查询
	 * @param dgm
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getAllPreCustActivityUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getAllPreCustActivityInfo(DataGridModel dgm, String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			dataGrid = preCustomerService.getAllPreCustActivityInfo(dgm, paramMap, loginInfo);
		} catch (CisCoreException e) {	
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 导出准客户活动量信息明细
	 * @param queryParam
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "preCustActivityDetail.xls", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public ModelAndView preCustActivityDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			queryParam = StringUtils.encodeStr(queryParam);
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			//开始导出
			resultInfo = preCustomerService.preCustActivityDetail(paramMap,loginInfo);
			//生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("准客户活动量信息导出出现异常！");
		}
		return null;
	}
	
	/**
	 * 导出准客户活动量管理打分表
	 * @param queryParam
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "preCustActivityManagement.xls", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public ModelAndView getPreCustActivityManagement(@RequestParam String queryParam,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			queryParam = StringUtils.encodeStr(queryParam);
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			//开始导出
			resultInfo = this.preCustomerService.preCustActivityScoreManagement(paramMap,loginInfo);
			//生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("准客户活动量信息导出出现异常！");
		}
		return null;
	}	
	
	
	
}

	
	
	
