package com.fms.controller.branch;

import java.io.PrintWriter;
/*****************************************************
 * HISTORY
 * 
 * 2013/04/11 yangzd 创建文件
 * 
 * YYYY/MM/DD ----- 增加新功能
 * 
 * Description 用户控制器
 *****************************************************/
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fms.db.model.Agent;
import com.fms.db.model.DefStore;
import com.fms.db.model.DefStoreLeaseInfo;
import com.fms.db.model.StoreBelongComTrace;
import com.fms.service.branch.StoreService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Controller
@RequestMapping("/branch")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class StoreController
{

	@Autowired
	private StoreService storeService;

	private static final Logger log = Logger.getLogger(StoreController.class);

	/**
	 * 获取网点维护页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listStoreUrl", method = RequestMethod.GET)
	public String listUrl()
	{
		return "fms/branch/listStore";
	}

	/**
	 * 获取网点新增页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addStoreUrl", method = RequestMethod.GET)
	public String addStoreUrl()
	{
		return "fms/branch/addStore";
	}

	/**
	 * 获取网点新增页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/uploadStoreImage", method = RequestMethod.GET)
	public ModelAndView uploadStoreImage(@RequestParam("param") String param){
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/branch/uploadStoreImage",reqParamMap);
	}
	
	/**
	 * 获取网点明细页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailStoreUrl", method = RequestMethod.GET)
	public String detailStoreUrl()
	{
		return "fms/branch/detailStore";
	}

	/**
	 * 获取网点更新页面URL
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateStoreUrl", method = RequestMethod.GET)
	public String updateStoreUrl()
	{
		return "fms/branch/updateStore";
	}

	/**
	 * 网点新增页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveAddStoreUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveAdd(String param,ModelMap model)
	{
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("param===" + param);
			//网点基本信息
			String storeInfo = JsonUtils.getJsonValueByKey("storeInfo", param);
			DefStore defStoreSchema = (DefStore) JsonUtils.jsonStrToObject(storeInfo, DefStore.class);
			//网点归属机构信息
			String storeBelongComInfo = JsonUtils.getJsonValueByKey("storeBelongComInfo", param);
			StoreBelongComTrace storeBelongComTrace = (StoreBelongComTrace) JsonUtils.jsonStrToObject(storeBelongComInfo, StoreBelongComTrace.class);
			//网点租金信息
			String storeLeaseInfoArray = JsonUtils.getJsonValueByKey("storeLeaseMoneyInfo", param);
			List<DefStoreLeaseInfo> storeLeaseInfoList = JsonUtils.jsonArrStrToList(storeLeaseInfoArray, DefStoreLeaseInfo.class);
			
			Map tMap = new HashMap();
			tMap.put("defStoreSchema", defStoreSchema);
			tMap.put("storeLeaseInfoList", storeLeaseInfoList);
			tMap.put("storeBelongComInfo", storeBelongComTrace);
			
			resultInfo = storeService.addStoreSave(tMap,loginInfo);
		}
		catch (CisCoreException e)
		{
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("新增保存失败,原因是" + e.getMessage());
		}
		return resultInfo;
	}

	/**
	 * 网点更新页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveUpdateStoreUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUpdate(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("param===" + param);
			String storeInfo = JsonUtils.getJsonValueByKey("storeInfo", param);

			DefStore defStoreSchema =(DefStore) JsonUtils.jsonStrToObject(storeInfo, DefStore.class);
			HashMap tMap = new HashMap();
			tMap.put("defStoreSchema", defStoreSchema);
			storeService.updateStoreSave(tMap,loginInfo);
			map.put("msg", "更新保存成功");
			map.put("succ", "true");
		}
		catch (CisCoreException e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "更新保存失败,原因是" + e.getMessage());
		}
		return map;
	}

	/**
	 * 网点更新页面初始化页面控件页面
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateStoreInitUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateStoreInit(@RequestParam("storeId") String storeId,ModelMap model)//
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.storeService.getUpdateListInfo(new Long(storeId));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(map);
	}

	/**
	 * 网点删除事件
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/deleteStoreUrl")
	@ResponseBody
	public Map<String, String> deleteStore(@RequestParam("id") List<Integer> uid,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			for (Integer id : uid)
			{
				storeService.deleteStoreSave(new Long(id),loginInfo);
			}
			map.put("msg", "删除保存成功");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "删除保存失败："+e.getMessage());
		}
		return map;// 重定向
	}

	/**
	 * 网点维护页面，列表信息查询（分页）
	 */
	@RequestMapping(value = "/queryStoreList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryStoreList(DataGridModel dgm,DefStore defStore)
	{
		return storeService.getPageList(dgm, defStore);
	}

	/**
	 * 网点信息下拉项，初始化事件
	 */
	@RequestMapping(value = "/queryStoreListCode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryStoreListCode()
	{
		String codeMapJson = JsonUtils.objectToJsonStr(storeService
				.getStoreListCode());
		return codeMapJson;
	}

	
	@RequestMapping(value = "/uploadStoreImage",method = RequestMethod.POST)
	@ResponseBody
	public void uploadStoreImage(@RequestParam("param") String param,HttpServletResponse response,
			@RequestParam MultipartFile[] storeImage,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取财富顾问基本信息
			String storeId = JsonUtils.getJsonValueByKey("storeId", param);
			resultInfo = storeService.uploadStoreImage(storeImage,param,loginInfo);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
			writer.write(JsonUtils.objectToJsonStr(resultInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文件出错");
		}
	}
	
	
	@RequestMapping(value = "/getAllStoreImage",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getAllStoreImage(String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取财富顾问基本信息
			String storeId = JsonUtils.getJsonValueByKey("storeId", param);
			String fileType = JsonUtils.getJsonValueByKey("fileType", param);
			resultInfo = storeService.getAllStoreImage(storeId, fileType);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取网点照片出错");
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/deleteStoreImage",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteStoreImage(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = storeService.deleteStoreImage(param,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取网点照片出错");
		}
		return resultInfo;
	}
}
