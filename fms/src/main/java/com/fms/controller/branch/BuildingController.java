package com.fms.controller.branch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fms.db.mapper.DefBuildingMapper;
import com.fms.db.model.DefBuilding;
import com.fms.service.branch.BuildingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Controller
@RequestMapping("/branch")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class BuildingController {
	
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private DefBuildingMapper defBuildingMapper;
	
	/**
	 * 获取楼盘url 
	 */
	@RequestMapping(value = "/listBuildUrl", method = RequestMethod.GET)
	public String listBuild(Model model) {
		return "fms/branch/listBuild";
	}
	
	/**
	 * 增加楼盘页面url
	 */
	@RequestMapping(value = "/addBuildUrl", method = RequestMethod.GET)
	public String addBuildUrl(Model model) {
		return "fms/branch/addBuild";
	}
	
	/**
	 * 获取更新楼盘页面url
	 */
	@RequestMapping(value = "/updateBuildUrl", method = RequestMethod.GET)
	public String updateBuildUrl(Model model) {
		return "fms/branch/updateBuild";
	}
	
	/**
	 * 获取楼盘明细页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailBuildUrl", method = RequestMethod.GET)
	public String detailBuildUrl()
	{
		return "fms/branch/detailBuild";
	}
	
	
	/**
	 * 楼盘录入页面查询楼盘录入信息列表
	 * @param queryParam
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryBuildListUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryBuildList(DataGridModel dgm,String queryParam) {
		DataGrid dataGrid = new DataGrid();
		try {
			Log.info("==请求数据===" + queryParam);
			if(queryParam==null||"".equals(queryParam)){
				return dataGrid;
			}
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
			Map paramMap = new HashMap();
			paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = buildingService.queryBuildList(dgm, paramMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 增加楼盘
	 */
	@RequestMapping(value = "/addBuild", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveBuildAdd(String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		return buildingService.savaBuildAdd(param,loginInfo);
	}
	
	/**
	 * 删除楼盘信息
	 * */
	@RequestMapping(value = "/delBuild",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo delBuild(@RequestParam("buildingId") List<Integer> buildid,ModelMap model){
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo) model.get(Constants.USER_INFO_SESSION);
		try
		{
			for (Integer id : buildid)
			{
				return buildingService.deleteBuild(new Long(id),loginInfo);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			resultInfo.setMsg(e.getMessage());
			resultInfo.setSuccess(false);
			return resultInfo;
		}
		resultInfo.setSuccess(false);
		resultInfo.setMsg("读取楼盘id失败！");
		return resultInfo;// 重定向
	}
	
	/**
	 * 楼盘信息下拉项，初始化事件
	 * */
	@RequestMapping(value = "/queryBuildListCode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryBuildListCode(){
		String codeMapJson = JsonUtils.objectToJsonStr(buildingService.getBuildListCode());
		return codeMapJson;
	}
	
	/**
	 * 楼盘更新页面初始化页面控件页面
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateBuildInitUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateBuildInit(@RequestParam("buildingId") int buildingId){
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.buildingService.getUpdateListInfo(new Long(buildingId));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		System.out.println(gson.toJson(map));
		return gson.toJson(map);
	}
	
	/**
	 * 楼盘更新页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveUpdateBuildUrl",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo saveUpdate(String param,ModelMap model){
		LoginInfo loginInfo = (LoginInfo) model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String buildingInfo = JsonUtils.getJsonValueByKey("buildingInfo", param);
		DefBuilding defBuildingSchema = gson.fromJson(buildingInfo, DefBuilding.class);
		HashMap tMap = new HashMap();
		tMap.put("defBuildingSchema", defBuildingSchema);
		resultInfo = buildingService.updateBuildSave(tMap,loginInfo);
		return resultInfo;
	}
}