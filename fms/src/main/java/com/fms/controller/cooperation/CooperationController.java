package com.fms.controller.cooperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fms.controller.customer.CustomerController;
import com.fms.db.model.AgencyCom;
import com.fms.db.model.AgencyContacts;
import com.fms.db.model.AgencyProtocol;
import com.fms.db.model.AgencySubProtocol;
import com.fms.service.cooperation.CooperationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Controller
@RequestMapping("/cooperation")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class CooperationController {
	
	@Autowired 
	CooperationService cooperationService;
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	
	/**
	 * 获取合作机构信息维护初始页面
	 */
	@RequestMapping(value = "/listAgencyComUrl", method = RequestMethod.GET)
	public String listAgencyCom(Model model) {
		return "fms/cooperation/listAgencyCom";
	}

	/** 获取合作机构信息初始信息列表 **/
	@RequestMapping(value = "/queryListAgencyCom", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> queryListAgencyCom(DataGridModel dgm, AgencyCom agencyCom) {
		return cooperationService.getAgencyComPageList(dgm, agencyCom);
	}
	
	/**
	 * 获取合作机构新增初始页面
	 */
	@RequestMapping(value = "/addAgencyComUrl", method = RequestMethod.GET)
	public ModelAndView addAgencyComUrl(@RequestParam("param") String param) {
    	logger.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/cooperation/addAgencyCom",reqParamMap);
	}
	
	/**
	 * 获取合作机构明细初始页面
	 */
	@RequestMapping(value = "/detailAgencyComUrl", method = RequestMethod.GET)
	public String detailAgencyComUrl(Model model) {
		return "fms/cooperation/detailAgencyCom";
	}
	
	/**
	 * 新增合作机构信息操作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addAgencyCom", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addAgencyCom(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String agencyComBaseInfo = JsonUtils.getJsonValueByKey("agencyComBaseInfo", param);
			agencyComBaseInfo = JsonUtils.decodeUrlParams(agencyComBaseInfo,"utf-8");
			AgencyCom agencyCom=gson.fromJson(agencyComBaseInfo, AgencyCom.class);
			//取出基本信息
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			tMap.put("agencyCom", agencyCom);
			String agencyComRelationTableInfo = JsonUtils.getJsonValueByKey("agencyComRelationTableInfo", param);
			List<AgencyContacts> agencyContactsInfolist = gson.fromJson(agencyComRelationTableInfo,new TypeToken<List<AgencyContacts>>(){}.getType());
			//取出联系人信息
			tMap.put("agencyContactsInfolist", agencyContactsInfolist);
			//将组装好的Map传给后台,进行数据保存
			resultInfo = cooperationService.addAgencyComInfo(tMap,operate,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "操作失败,原因是" + e.getMessage());
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	
	/**
	 * 获取合作机构信息修改页面
	 */
	@RequestMapping(value = "/updateAgencyComUrl", method = RequestMethod.GET)
	public String updateAgencyComUrl(Model model) {
		return "fms/cooperation/updateAgencyCom";
	}
	/**
	 * 获取合作机构修改页面的信息
	 */
	@RequestMapping(value = "/queryAgencyComInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryAgencyInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取合作机构详细信息出错");
				return resultInfo;
			}
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = cooperationService.queryAgencyComInfo(param, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取合作机构详细信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 修改合作机构信息操作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateAgencyCom", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateAgencyCom(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String agencyComBaseInfo = JsonUtils.getJsonValueByKey("agencyComBaseInfo", param);
			AgencyCom agencyCom=gson.fromJson(agencyComBaseInfo, AgencyCom.class);
			//取出基本信息
			tMap.put("agencyCom", agencyCom);
			String agencyComRelationTableInfo = JsonUtils.getJsonValueByKey("agencyComRelationTableInfo", param);
			List<AgencyContacts> agencyContactsInfolist = gson.fromJson(agencyComRelationTableInfo,new TypeToken<List<AgencyContacts>>(){}.getType());
			//取出联系人信息
			tMap.put("agencyContactsInfolist", agencyContactsInfolist);
			//取出修改的合作机构Id
			String modifyAgencyComId = JsonUtils.getJsonValueByKey("modifyAgencyComId", param);
			tMap.put("modifyAgencyComId", modifyAgencyComId);
			
			//将组装好的Map传给后台,进行数据保存
			cooperationService.updateAgencyComInfo(tMap, loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "操作失败,原因是" + e.getMessage());
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}
	
	
	/**
	 * 合作机构信息删除
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteAgencyCom")
	@ResponseBody
	public ResultInfo deleteAgencyCom(@RequestParam("agencyComId") List<Integer> uid,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			for (Integer agencyComId : uid) {
			resultInfo=	cooperationService.deleteAgencyCom(new Long(agencyComId),loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}
	
	/****************************合作机构协议信息维护**********************************************************/
	
	
	/**
	 * 获取合作协议维护初始页面
	 */
	@RequestMapping(value = "/listAgencyProtoclUrl", method = RequestMethod.GET)
	public String listAgencyProtocol(Model model) {
		return "fms/cooperation/listAgencyProtocol";
	}
	
	/**
	 * 获取合作协议新增初始页面
	 */
	@RequestMapping(value = "/addAgencyProtocolUrl", method = RequestMethod.GET)
	public ModelAndView addAgencyProtocol(@RequestParam("param") String param) {
		logger.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/cooperation/addAgencyProtocol",reqParamMap);
		//return "fms/cooperation/addAgencyProtocol";
	}
	
	/**
	 * 获取合作协议明细初始页面
	 */
	@RequestMapping(value = "/detailAgencyProtocolUrl", method = RequestMethod.GET)
	public String detailAgencyProtocol(Model model) {
		return "fms/cooperation/detailAgencyProtocol";
	}

	/**
	 * 新增合作机构协议信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addAgencyComProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addAgencyComProtocol(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
			//取出协议类型，子协议和框架协议/普通协议新增的表不一样
			String getbasicProtocolInfo=JsonUtils.getJsonValueByKey("basicProtocolInfo", param);
			//String protocolType=JsonUtils.getJsonValueByKey("protocolType", getbasicProtocolInfo);
			String protocolId = JsonUtils.getJsonValueByKey("addAgencyProtocolId", param);
			//tMap.put("protocolType", protocolType);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String basicProtocolInfo = JsonUtils.getJsonValueByKey("basicProtocolInfo", param);
			//取出协议起始日期
			String subprotocolStartDate=JsonUtils.getJsonValueByKey("protocolStartDay", getbasicProtocolInfo);
			//取出协议结束日期
			String subprotocolEndDate=JsonUtils.getJsonValueByKey("protocolEndDay", getbasicProtocolInfo);
			
				AgencyProtocol agencyProtocol=gson.fromJson(basicProtocolInfo, AgencyProtocol.class);
				agencyProtocol.setProtocolStartDate(DateUtils.getDate(subprotocolStartDate));
				agencyProtocol.setProtocolEndDate(DateUtils.getDate(subprotocolEndDate));
				
				tMap.put("agencyProtocol", agencyProtocol);
			
			//上传的文件信息
			String basicUploadFileInfo = JsonUtils.getJsonValueByKey("basicUploadFileInfo", param);
			tMap.put("basicUploadFileInfo", basicUploadFileInfo);
			tMap.put("protocolId", protocolId);
			
			//将组装好的Map传给后台,进行数据保存
			String agencyProtocolId= cooperationService.addAgencyComProtocolInfo(tMap,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("操作成功！");
			resultInfo.setObj(agencyProtocolId);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "操作失败,原因是" + e.getMessage());
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());

		}
		return resultInfo;
	}
	
	/** 获取合作机构信息初始信息列表 **/
	@RequestMapping(value = "/queryListAgencyComProtocol", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListAgencyComProtocol(DataGridModel dgm, String queryParam,ModelMap modelMap) {
		DataGrid dataGrid = new DataGrid();
		try {
			logger.info("==请求数据===" + queryParam);
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			Map<String, String> paramMap = new HashMap<String, String>();
			if(queryParam==null||"".equals(queryParam)){
				
			}else{
				paramMap = JsonUtils.jsonStrToMap(queryParam);
			}
			dataGrid = cooperationService.getAgencyComProtocolPageList(dgm, paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 获取合作机构信息修改页面
	 */
	@RequestMapping(value = "/updateAgencyComProtocolUrl", method = RequestMethod.GET)
	public String updateAgencyComProtocolUrl(Model model) {
		return "fms/cooperation/updateAgencyProtocol";
	}
	
	/**
	 * 修改合作机构协议信息操作
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/queryAgencyComProtocolInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo queryAgencyComProtocolInfo(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
			String modifyAgencyComId = JsonUtils.getJsonValueByKey("modifyAgencyComId", param);
			String modifyProtocolType = JsonUtils.getJsonValueByKey("modifyProtocolType", param);
			String modifyProtocolId=JsonUtils.getJsonValueByKey("modifyProtocolId", param);
			//取出基本信息
			tMap.put("modifyAgencyComId", modifyAgencyComId);
			tMap.put("modifyProtocolType", modifyProtocolType);
			tMap.put("modifyProtocolId", modifyProtocolId);
			//将组装好的Map传给后台,进行数据保存
			resultInfo=	cooperationService.queryAgencyComProtocolInfo(tMap, loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "操作失败,原因是" + e.getMessage());

		}
		return resultInfo;
	}
	
	
	/**
	 * 合作机构协议信息修改
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateAgencyComProtocol", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateAgencyComProtocol(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Map<String, String> map = new HashMap<String, String>();
		HashMap tMap = new HashMap();
		try {
		
			String getbasicProtocolInfo=JsonUtils.getJsonValueByKey("basicProtocolInfo", param);
			String agencyComId=JsonUtils.getJsonValueByKey("modifyAgencyComId", param);
			String protocolType=JsonUtils.getJsonValueByKey("modifyProtocolType", param);
			String protocolId=JsonUtils.getJsonValueByKey("modifyProtocolId", param);
			tMap.put("protocolType", protocolType);
			tMap.put("protocolId", protocolId);
			tMap.put("agencyComId", agencyComId);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String basicProtocolInfo = JsonUtils.getJsonValueByKey("basicProtocolInfo", param);
			//取出协议起始日期
			String subprotocolStartDate=JsonUtils.getJsonValueByKey("protocolStartDate", getbasicProtocolInfo);
			//取出协议结束日期
			String subprotocolEndDate=JsonUtils.getJsonValueByKey("protocolEndDate", getbasicProtocolInfo);
			
			//子协议
			if(protocolType.equals("3")||protocolType=="3"){
				AgencySubProtocol agencySubProtocol=new  AgencySubProtocol();
				//取出所属框架协议Id
				String agencyProtocolId=JsonUtils.getJsonValueByKey("agencyProtocolId", getbasicProtocolInfo);
				//取出子协议编码
				String subprotocolCode=JsonUtils.getJsonValueByKey("protocolCode", getbasicProtocolInfo);
				//取出子协议编码名称
				String subprotocolName=JsonUtils.getJsonValueByKey("protocolName", getbasicProtocolInfo);
				//取出子协议类型
				String subprotocolType=JsonUtils.getJsonValueByKey("protocolType", getbasicProtocolInfo);
				//取出子协议状态
				String subprotocolState=JsonUtils.getJsonValueByKey("protocolState", getbasicProtocolInfo);
				agencySubProtocol.setAgencyProtocolId(new Long(agencyProtocolId));
				agencySubProtocol.setSubprotocolCode(subprotocolCode);
				agencySubProtocol.setSubprotocolName(subprotocolName);
				agencySubProtocol.setSubprotocolType(subprotocolType);
				agencySubProtocol.setSubprotocolState(subprotocolState);
				agencySubProtocol.setSubProtocolStartDate( DateUtils.getDate(subprotocolStartDate));
				agencySubProtocol.setSubProtocolEndDate( DateUtils.getDate(subprotocolEndDate));
				tMap.put("agencySubProtocolInfo", agencySubProtocol);
			}
			else{
				AgencyProtocol agencyProtocol=gson.fromJson(basicProtocolInfo, AgencyProtocol.class);
				agencyProtocol.setProtocolStartDate(DateUtils.getDate(subprotocolStartDate));
				agencyProtocol.setProtocolEndDate(DateUtils.getDate(subprotocolEndDate));
				
				tMap.put("agencyProtocol", agencyProtocol);
			}
			//上传的文件信息?????????????
	
			//将组装好的Map传给后台,进行数据保存
			cooperationService.updateAgencyComProtocolInfo(tMap,loginInfo);
			resultInfo.setSuccess(true);
			resultInfo.setMsg("操作成功！");
			resultInfo.setObj(protocolId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "操作失败,原因是" + e.getMessage());
			resultInfo.setSuccess(false);
			resultInfo.setMsg("操作失败,原因是" + e.getMessage());

		}
		return resultInfo;
	}
	
	/**
	 * 删除合作机构协议信息
	 */
	@RequestMapping(value = "/deleteAgencyComProtocol",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo deleteAgencyComProtocol(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		Map<String, String> tmap = new HashMap<String, String>();
		try{
		String getrowsdeleteInfo=JsonUtils.getJsonValueByKey("rows", param);
		List<Map<String, String>> agencyProtocolList = JsonUtils.jsonArrStrToListMap(getrowsdeleteInfo);
		String protocolId=agencyProtocolList.get(0).get("protocolId").toString();
		String potocolType=agencyProtocolList.get(0).get("protocolTypeCode").toString();
		tmap.put("potocolType", potocolType);
		tmap.put("protocolId", protocolId);
		
		resultInfo=cooperationService.deleteAgencyComProtocol(tmap,loginInfo);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取删除信息失败："+e.getMessage());
		}
		
		return resultInfo;
	}
	

}
