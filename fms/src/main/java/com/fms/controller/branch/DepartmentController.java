package com.fms.controller.branch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fms.db.model.DefDepartment;
import com.fms.service.branch.DepartmentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Controller
@RequestMapping("/branch")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 获取团队url 
	 */
	@RequestMapping(value = "/listDepartmentUrl", method = RequestMethod.GET)
	public String listDepartment(Model model) {
		return "fms/branch/listDepartment";
	}
	
	/**
	 * 增加团队页面url
	 */
	@RequestMapping(value = "/addDepartmentUrl", method = RequestMethod.GET)
	public String addDepartmentUrl(Model model) {
		return "fms/branch/addDepartment";
	}
	
	/**
	 * 获取团队明细页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailDepartmentUrl", method = RequestMethod.GET)
	public String detailDepartmentUrl()
	{
		return "fms/branch/detailDepartment";
	}
	
	/**
	 * 获取更新团队页面url
	 */
	@RequestMapping(value = "/updateDepartmentUrl", method = RequestMethod.GET)
	public String updateDepartmentUrl(Model model) {
		return "fms/branch/updateDepartment";
	}
	
	/**
	 * 团队录入页面查询团队录入信息列表
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/queryDepartmentList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryDepartmentList(DataGridModel dgm,DefDepartment defdep) {
		return departmentService.queryDepartmentList(dgm, defdep);
	}
	
	/**
	 * 增加团队
	 */
	@RequestMapping(value = "/addDepartment",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveDepartmentAdd(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		return departmentService.savaDepartmentAdd(param,loginInfo);
	}
	
	/**
	 * 团队下拉项，初始化事件
	 */
	@RequestMapping(value = "/queryDepartmentListCode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryDeprtmentListCode(){
		String codeMapJson = JsonUtils.objectToJsonStr(departmentService.getDepartmentListCode());
		return codeMapJson;
	}
	
	/**
	 * 团队更新页面初始化页面控件页面
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateDepartmentInitUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateDepartmentInit(@RequestParam("departmentId") int departmentId){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.departmentService.getUpdateListInfo(new Long(departmentId));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(map);
	}
	
	/**
	 * 团队更新页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveUpdateDepartmentUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveUpdate(String param,ModelMap model){
		//ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String departmentInfo = JsonUtils.getJsonValueByKey("departmentInfo", param);
			DefDepartment defDepartmentSchema = gson.fromJson(departmentInfo, DefDepartment.class);
			HashMap tMap = new HashMap();
			tMap.put("defDepartmentSchema", defDepartmentSchema);
			return departmentService.updateDepartmentSave(tMap,loginInfo);
	}
	
	
	/**
	 * 团队删除页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteDepartment(@RequestParam("departmentId") List<Integer> departmentId,ModelMap model){
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			for (Integer id : departmentId)
			{
				return departmentService.delDepartment(new Long(id),loginInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setSuccess(false);
		resultInfo.setMsg("删除团队信息失败！");
		return resultInfo;
	} 
	
//	/**
//	 * 团队编码校验
//	 * 
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping(value = "/verifyDepartmentCode",produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public ResultInfo verifyDepartmentCode(String param){
//		ResultInfo resultInfo = new ResultInfo();
//		try {
//			Log.info("==请求数据===" + param);
//			if(param==null||"".equals(param)){
//				return resultInfo;
//			}
//			String departmentCode = JsonUtils.getJsonValueByKey("departmentCode", param);
//			resultInfo = departmentService.verifyDepartmentCode(departmentCode);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resultInfo;
//	}
//	
//	/**
//	 * 团队关联校验
//	 * 
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping(value = "/verifyDepartmentId",produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public ResultInfo verifyDepartmentId(String param){
//		ResultInfo resultInfo = new ResultInfo();
//		try {
//			Log.info("==请求数据===" + param);
//			if(param==null||"".equals(param)){
//				return resultInfo;
//			}
//			String departmentId = JsonUtils.getJsonValueByKey("departmentId", param);
//			resultInfo = departmentService.verifyDepartmentId(departmentId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resultInfo;
//		
//	}
}
