package com.fms.controller.branch;

/**
 * 2015/02/05 gzq 创建文件
 * Description 机构信息控制器
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fms.service.branch.ComService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinosoft.core.db.model.DefCom;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;

@Controller
@RequestMapping("/branch")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class ComController
{

	@Autowired
	private ComService comService;

	private static final Logger log = Logger.getLogger(ComController.class);

	/**
	 * 获取机构维护页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listComUrl", method = RequestMethod.GET)
	public String listUrl()
	{
		return "fms/branch/listCom";
	}

	/**
	 * 获取机构新增页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addComUrl", method = RequestMethod.GET)
	public String addComUrl()
	{
		return "fms/branch/addCom";
	}

	/**
	 * 获取机构明细页面URL
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detailComUrl", method = RequestMethod.GET)
	public String detailComUrl()
	{
		return "fms/branch/detailCom";
	}

	/**
	 * 获取机构更新页面URL
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateComUrl", method = RequestMethod.GET)
	public String updateComUrl()
	{
		return "fms/branch/updateCom";
	}

	/**
	 * 机构新增页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveAddComUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveAdd(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("param===" + param);
			String comInfo = JsonUtils.getJsonValueByKey("comInfo", param);
			comInfo = JsonUtils.decodeUrlParams(comInfo,"utf-8");
			DefCom defComSchema = (DefCom) JsonUtils.jsonStrToObject(comInfo, DefCom.class);

			HashMap tMap = new HashMap();
			tMap.put("defComSchema", defComSchema);

			comService.addComSave(tMap,loginInfo);
			map.put("msg", "新增保存成功");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "操作失败,原因是" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 机构更新页面，提交事件
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saveUpdateComUrl", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveUpdate(String param,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			log.info("param===" + param);
			String comInfo = JsonUtils.getJsonValueByKey("comInfo", param);
			comInfo = JsonUtils.decodeUrlParams(comInfo,"utf-8");
			DefCom defComSchema = (DefCom) JsonUtils.jsonStrToObject(comInfo, DefCom.class);
			HashMap tMap = new HashMap();
			tMap.put("defComSchema", defComSchema);
			comService.updateComSave(tMap,loginInfo);
			map.put("msg", "更新保存成功");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "更新保存失败,原因是" + e.getMessage());
		}
		return map;
	}

	/**
	 * 机构更新页面初始化页面控件页面
	 * 
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/updateComInitUrl", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateComInit(@RequestParam("comId") String comId)//
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.comService.getUpdateListInfo(new Long(comId));
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(map);
	}

	/**
	 * 机构删除事件
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/deleteComUrl")
	@ResponseBody
	public Map<String, String> deleteCom(@RequestParam("id") List<Integer> uid,ModelMap model)
	{
		Map<String, String> map = new HashMap<String, String>();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try
		{
			for (Integer id : uid)
			{
				comService.deleteComSave(new Long(id),loginInfo);
			}
			map.put("msg", "删除保存成功");
			map.put("succ", "true");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			map.put("succ", "false");
			map.put("msg", "删除保存失败:"+e.getMessage());
		}
		return map;// 重定向
	}
	
	/**
	 * 机构维护页面，列表信息查询（分页）
	 */
	@RequestMapping(value = "/queryComList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryComList(DataGridModel dgm, DefCom defCom)
	{
		return comService.getPageList(dgm, defCom);
	}

	/**
	 * 机构信息下拉项，初始化事件
	 */
	@RequestMapping(value = "/queryComListCode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryComListCode()
	{
		String codeMapJson = JsonUtils.objectToJsonStr(comService
				.getComListCode());
		return codeMapJson;
	}
}
