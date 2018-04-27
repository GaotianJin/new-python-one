package com.fms.controller.manager;

import java.util.HashMap;
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

import com.fms.db.model.Manager;
import com.fms.service.manager.ManagerService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Controller
@RequestMapping("/manager")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class ManagerController {
	private static final Logger log = Logger.getLogger(ManagerController.class);
	@Autowired
	private ManagerService managerService;
	
    @RequestMapping(value = "/listManagerUrl", method = RequestMethod.GET)
    public String listAgent(Model model) {
        return "fms/manager/listManager";
    }
    
    /**
     * 获取增加内勤人员页面url
     */
    @RequestMapping(value = "/addManagerUrl", method = RequestMethod.GET)
    public ModelAndView addManagerUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/manager/addManager",reqParamMap);
    }
    
    /**
  	 * @param searchParam
  	 * @param modelMap
  	 * @return
  	 */
  	@SuppressWarnings({ "rawtypes" })
  	@RequestMapping(value = "/queryManagerList",produces = "application/json; charset=utf-8")
  	@ResponseBody
  	public DataGrid queryManagerList(DataGridModel dgm,String queryParam,ModelMap modelMap){
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
  			dataGrid = managerService.queryManagerList(dgm, paramMap, loginInfo);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return dataGrid;
  	}
  	
    @RequestMapping(value = "/queryManagerInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryManagerInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			Map paramMap = new HashMap();
			paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			String managerId = paramMap.get("managerId").toString();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = managerService.queryManagerInfo(managerId, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取内勤人员详细信息出错");
		}
		return resultInfo;
	}
    
	@RequestMapping(value = "/submitManager",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo submitManager(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			String managerBaseInfoStr = JsonUtils.getJsonValueByKey("managerInfoJsonStr", param);
			String managerId = JsonUtils.getJsonValueByKey("managerId", param);
			managerBaseInfoStr = JsonUtils.decodeUrlParams(managerBaseInfoStr,"utf-8");
			Manager manager = (Manager)JsonUtils.jsonStrToObject(managerBaseInfoStr, Manager.class);
			//获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			//新增财富顾问
			resultInfo = managerService.submitManager(manager, loginInfo,managerId, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("新增内勤人员信息出错");
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/deleteManager",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo deleteManager(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			String managerId = JsonUtils.getJsonValueByKey("managerId", param);
			resultInfo = managerService.deleteManager(managerId, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("新增内勤人员信息出错");
		}
		return resultInfo;
	}
}
