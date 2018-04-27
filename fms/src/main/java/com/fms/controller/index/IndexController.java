package com.fms.controller.index;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.fms.db.model.CompanyRosterInfo;
import com.fms.service.index.IndexService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.db.mapper.DefUserRoleRelaMapper;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.db.model.DefUserRoleRelaExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sun.tools.xjc.model.Model;

@Controller
@RequestMapping("/index")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class IndexController {

	@Autowired
	private IndexService indexService;
	@Autowired
	private DefUserRoleRelaMapper defUserRoleRelaMapper;
	private static final Logger log = Logger.getLogger(IndexController.class);
	
	/**
	 * 获取公司政策列表
	 */
	@RequestMapping(value = "/listCompanyPolicyUrl", method = RequestMethod.GET)
	public ModelAndView listCompanyPolicyUrl(ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
	    Long userId = loginInfo.getUserId();
	    DefUserRoleRelaExample defUserRoleRelaExample = new DefUserRoleRelaExample();
	    defUserRoleRelaExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
	    List<DefUserRoleRela> defUserRoleRelas = defUserRoleRelaMapper.selectByExample(defUserRoleRelaExample);
	    Map<String, String> reqParamMap = new HashMap<String, String>();
	    for(int i=0;i<defUserRoleRelas.size();++i){
			DefUserRoleRela defUserRoleRela = defUserRoleRelas.get(i);
			String roleId = defUserRoleRela.getRoleId().toString();
			if("1".equals(roleId)){
				reqParamMap.put("roleId", roleId);
				return new ModelAndView("fms/index/listCompanyPolicy",reqParamMap);
			}
		}
	    return new ModelAndView("fms/index/listCompanyPolicy",reqParamMap);
	}
	
	/**
	 * 行业新闻初始页面
	 * @return
	 */
	@RequestMapping(value = "/listProfessionNewsUrl", method = RequestMethod.GET)
	public ModelAndView listProfessionNewsUrl(ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
	    Long userId = loginInfo.getUserId();
	    DefUserRoleRelaExample defUserRoleRelaExample = new DefUserRoleRelaExample();
	    defUserRoleRelaExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
	    List<DefUserRoleRela> defUserRoleRelas = defUserRoleRelaMapper.selectByExample(defUserRoleRelaExample);
	    Map<String, String> reqParamMap = new HashMap<String, String>();
	    for(int i=0;i<defUserRoleRelas.size();++i){
			DefUserRoleRela defUserRoleRela = defUserRoleRelas.get(i);
			String roleId = defUserRoleRela.getRoleId().toString();
			if("1".equals(roleId)){
				reqParamMap.put("roleId", roleId);
				return new ModelAndView("fms/index/listProfessionNews",reqParamMap);
			}
		}
	    return new ModelAndView("fms/index/listProfessionNews",reqParamMap);
	}
	/**
	 * 获取公司政策详情页面url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listCompanyPolicyDetailUrl", method = RequestMethod.GET)
	public ModelAndView listCompanyPolicyDetailUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/listCompanyPolicyDetail",reqParamMap);
	}
	
	/**
	 * 行业新闻详细页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/listProfessionNewsDetailUrl", method = RequestMethod.GET)
	public ModelAndView listProfessionNewsDetailUrl(@RequestParam("param") String param){
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/listProfessionNewsDetail",reqParamMap);
	}
	
	/**
	 * 上传公司政策页面
	 */
	@RequestMapping(value = "/addUploadFileUrl", method = RequestMethod.GET)
	public String addUploadFileUrl() {
		return "fms/index/addUploadFileInfo";
	}
	
	/**
	 * 上传行业新闻页面
	 */
	@RequestMapping(value = "/proNewsUploadFileUrl", method = RequestMethod.GET)
	public String proNewsUploadFileUrl(){
		return "fms/index/professionNewsUploadFile";
	}
	/**
	 * 获取公司要闻页面url
	 */
	@RequestMapping(value = "/listCompanyNewsUrl", method = RequestMethod.GET)
	public ModelAndView listCompanyNewsUrl(ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
	    Long userId = loginInfo.getUserId();
	    DefUserRoleRelaExample defUserRoleRelaExample = new DefUserRoleRelaExample();
	    defUserRoleRelaExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
	    List<DefUserRoleRela> defUserRoleRelas = defUserRoleRelaMapper.selectByExample(defUserRoleRelaExample);
	    Map<String, String> reqParamMap = new HashMap<String, String>();
	    for(int i=0;i<defUserRoleRelas.size();++i){
			DefUserRoleRela defUserRoleRela = defUserRoleRelas.get(i);
			String roleId = defUserRoleRela.getRoleId().toString();
			if("1".equals(roleId)){
				reqParamMap.put("roleId", roleId);
				return new ModelAndView("fms/index/listCompanyNews",reqParamMap);
			}
		}
	    return new ModelAndView("fms/index/listCompanyNews",reqParamMap);
	}
	
	/**
	 * 上传公司要闻页面
	 */
	@RequestMapping(value = "/addComNewsFileUrl", method = RequestMethod.GET)
	public String addComNewsFileUrl() {
		return "fms/index/addComNewsFile";
	}
	/**
	 * 获取公司要闻详情页面url
	 *
	 */
	@RequestMapping(value = "/companyNewsDetailUrl", method = RequestMethod.GET)
	public ModelAndView companyNewsDetailUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/listCompanyNewsDetail",reqParamMap);
	}
	/**
	 * 获取通讯录页面url
	 */
	@RequestMapping(value = "/listCompanyRosterUrl", method = RequestMethod.GET)
	public ModelAndView listCompanyRosterUrl(ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
	    Long userId = loginInfo.getUserId();
	    DefUserRoleRelaExample defUserRoleRelaExample = new DefUserRoleRelaExample();
	    defUserRoleRelaExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
	    List<DefUserRoleRela> defUserRoleRelas = defUserRoleRelaMapper.selectByExample(defUserRoleRelaExample);
	    Map<String, String> reqParamMap = new HashMap<String, String>();
	    for(int i=0;i<defUserRoleRelas.size();++i){
			DefUserRoleRela defUserRoleRela = defUserRoleRelas.get(i);
			String roleId = defUserRoleRela.getRoleId().toString();
			if("1".equals(roleId)){
				reqParamMap.put("roleId", roleId);
				return new ModelAndView("fms/index/listCompanyRoster",reqParamMap);
			}
		}
	    return new ModelAndView("fms/index/listCompanyRoster",reqParamMap);
	}
	
	/**
	 * 获取通讯录导入页面url
	 */
	@RequestMapping(value = "/importCompanyRosterUrl", method = RequestMethod.GET)
	public String importCompanyRosterUrl() {
		return "fms/index/importCompanyRoster";
	}
	
	
	/**
	 * 获取自我介绍页面url
	 */
	@RequestMapping(value = "/listSelfIntroductionUrl", method = RequestMethod.GET)
	public String listSelfIntroductionUrl() {
		return "fms/index/listSelfIntroduction";
	}
	
	
	
	
	
	/**
	 * 公司要闻上传文件跳转页面
	 * @return
	 */
	@RequestMapping(value = "/editorCompanyNewsUrl", method = RequestMethod.GET)
	public ModelAndView editorCompanyNewsUrl() {
		return new ModelAndView("fms/index/editorCompanyNews");
	}
	
	/**
	 * 首页文件上传页面跳转
	 * @return
	 */
	@RequestMapping(value = "/uploadFilesUrl", method = RequestMethod.GET)
	public ModelAndView uploadFilesUrl() {
		return new ModelAndView("fms/index/uploadIndexFiles");
	}
	/**
	 * 查询公司政策列表信息
	 */
	/**
	 * 公司政策列表查询
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryComPolicyList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryComPolicyList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = indexService.queryComPolicyList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = indexService.queryComPolicyList(dgm, paramMap, loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 行业新闻列表查询
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/queryProfessionNewsList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryProfessionNewsList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try{
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			LoginInfo loginInfo = (LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam == null||"".equals(queryParam)){
				dataGrid = indexService.queryProfessionNewsList(dgm, paramMap ,loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = indexService.queryProfessionNewsList(dgm, paramMap ,loginInfo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 保存公司政策附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked" })
	@RequestMapping(value = "/addUploadFileInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addUploadFileInfo(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		String title=JsonUtils.getJsonValueByKey("title", param);
		Map tMap=new HashMap();
		try {	tMap.put("editorInfoSucFlag", editorInfoSucFlag);
				tMap.put("title", title);
				tMap.put("htmlContext", content);
				resultInfo = indexService.addUploadFileInfo(tMap,loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
   
	/**
	 * 保存行业新闻编辑问题
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/professionNewsUploadFileInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo professionNewsUploadFileInfo(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		String title=JsonUtils.getJsonValueByKey("title", param);
		Map tMap=new HashMap();
		try{
			tMap.put("editorInfoSucFlag", editorInfoSucFlag);
			tMap.put("title", title);
			tMap.put("htmlContext", content);
			resultInfo = indexService.professionNewsUploadFileInfo(tMap,loginInfo);
		}catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取公司政策附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/getCompanyPolicyContextUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getCompanyPolicyContextUrl(String param,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String companyPolicyInfoId=JsonUtils.getJsonValueByKey("companyPolicyInfoId", param);
		Map tMap=new HashMap();
		try {
			if (companyPolicyInfoId!=null) {
				tMap.put("companyPolicyInfoId", companyPolicyInfoId);
				resultInfo = indexService.getCompanyPolicyContext(tMap);
			}
			else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取公司政策信息失败！");
			}
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存公司政策附文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 获取行业新闻附文本编辑问题
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/getProfessionNewsContextUrl",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getProfessionNewsContextUrl(String param,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String professionNewsInfoId=JsonUtils.getJsonValueByKey("professionNewsInfoId", param);
		Map tMap=new HashMap();
		try{
			if(professionNewsInfoId!=null){
				tMap.put("professionNewsInfoId", professionNewsInfoId);
				resultInfo = indexService.getProfessionNewsContext(tMap);
			}else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取行业新闻信息失败！");
			}
		}catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存行业新闻文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 通讯录查询列表
	 * @param dgm
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryListComRoster", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryListComRoster(DataGridModel dgm, String param,ModelMap modelMap) {
		Map paramMap = new HashMap();
		//获取用户登录信息			
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		DataGrid dataGrid = new DataGrid();
		try {
			if(param==null||"".equals(param)||param.equals("{}")){
				List returnList = new ArrayList(); 
				dataGrid.setRows(returnList);
				dataGrid.setTotal(0);
			}else{
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
				dataGrid = indexService.queryListComRoster(dgm, paramMap, loginInfo);
			}
		}catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 获取通讯录新增页面
	 */
	@RequestMapping(value = "/addCompanyRosterUrl", method = RequestMethod.GET)
	public ModelAndView addCompanyRosterUrl(@RequestParam("param") String param) {
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/addCompanyRoster",reqParamMap);
	}
	
	/**
	 * 新增通讯录信息操作
	 */
	@SuppressWarnings({ "rawtypes", "unused"})
	@RequestMapping(value = "/addCompanyRoster", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addCompanyRoster(String companyRosterInfoData,String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		HashMap tMap = new HashMap();
		try {
			CompanyRosterInfo comRoster=(CompanyRosterInfo)JsonUtils.jsonStrToObject(companyRosterInfoData, CompanyRosterInfo.class);
			//取出基本信息
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			resultInfo = indexService.addCompanyRoster(comRoster,operate,loginInfo);
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
	 * 获取通讯录信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getCompanyRosterInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getCompanyRosterInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取通讯录信息出错");
		return resultInfo;
		}
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		resultInfo = indexService.getCompanyRosterInfo(param, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取通讯录信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 删除通讯录信息
	 * @param companyRosterInfoId
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteCompanyRoster")
	@ResponseBody
	public ResultInfo deleteCompanyRoster(@RequestParam("companyRosterInfoId") List<Integer> companyRosterInfoId,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			for (Integer id : companyRosterInfoId) {
			resultInfo=	indexService.deleteCompanyRoster(new Long(id),loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}
	
	/**
	 * 批量导入通讯录
	 * @param response
	 * @param rosterFileName
	 * @param modelMap
	 */
	@RequestMapping(value = "/importRosterDisFile", method = RequestMethod.POST)
	@ResponseBody
	public void importRosterDisFile(HttpServletResponse response,
			@RequestParam MultipartFile[] rosterFileName,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = indexService.importRosterDisFile(rosterFileName, loginInfo);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
			writer.write(JsonUtils.objectToJsonStr(resultInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("通讯录导入文件出错");
		}
	}
	
	/**
	 * 查询公司要闻列表信息
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryCompanyNewsList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryCompanyNewsList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = indexService.queryCompanyNewsList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = indexService.queryCompanyNewsList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 保存公司要闻附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked" })
	@RequestMapping(value = "/addComNewsInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addComNewsInfo(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		String title=JsonUtils.getJsonValueByKey("title", param);
		Map tMap=new HashMap();
		try {	tMap.put("editorInfoSucFlag", editorInfoSucFlag);
				tMap.put("title", title);
				tMap.put("htmlContext", content);
				resultInfo = indexService.addComNewsInfo(tMap,loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	
	/**
	 * 获取公司要闻附文本编辑问题
	 * @param companyNewsInfoId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/getCompanyNewsContextUrl", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getCompanyNewsContextUrl(String param,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String companyNewsInfoId=JsonUtils.getJsonValueByKey("companyNewsInfoId", param);
		Map tMap=new HashMap();
		try {
			if (companyNewsInfoId!=null) {
				tMap.put("companyNewsInfoId", companyNewsInfoId);
				resultInfo = indexService.getCompanyNewsContext(tMap);
			}
			else{
				resultInfo.setSuccess(false);
				resultInfo.setMsg("获取公司要闻信息失败！");
			}
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存公司要闻附文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 编辑公司要闻页面
	 */
	@RequestMapping(value = "/companyNewsEditorUrl", method = RequestMethod.GET)
	public String companyNewsEditorUrl() {
		return "fms/index/companyNewsEditor";
	}
	
	/**
	 * 删除公司要闻信息
	 * @param companyRosterInfoId
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteCompanyNews")
	@ResponseBody
	public ResultInfo deleteCompanyNews(@RequestParam("companyNewsInfoId") List<Integer> companyNewsInfoId,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			for (Integer id : companyNewsInfoId) {
			resultInfo=	indexService.deleteCompanyNews(new Long(id),loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}
	
	/**
	 * 更新公司要闻附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/updateComNewsInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateComNewsInfo(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		String title=JsonUtils.getJsonValueByKey("title", param);
		String companyNewsInfoId=JsonUtils.getJsonValueByKey("companyNewsInfoId", param);
		Map tMap=new HashMap();
		try {	tMap.put("editorInfoSucFlag", editorInfoSucFlag);
				tMap.put("title", title);
				tMap.put("htmlContext", content);
				tMap.put("companyNewsInfoId", companyNewsInfoId);
				resultInfo = indexService.updateComNewsInfo(tMap,loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 更新公司要闻页面url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateComNewsFileUrl", method = RequestMethod.GET)
	public ModelAndView updateComNewsFileUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/updateComNewsFile",reqParamMap);
	}
	
	/**
	 * 编辑公司政策页面
	 */
	@RequestMapping(value = "/companyPolicyEditorUrl", method = RequestMethod.GET)
	public String companyPolicyEditorUrl() {
		return "fms/index/companyPolicyEditor";
	}
	
	/**
	 * 删除公司政策信息
	 * @param companyRosterInfoId
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteCompanyPolicy")
	@ResponseBody
	public ResultInfo deleteCompanyPolicy(@RequestParam("companyPolicyInfoId") List<Integer> companyPolicyInfoId,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			for (Integer id : companyPolicyInfoId) {
			resultInfo=	indexService.deleteCompanyPolicy(new Long(id),loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}
	
	/**
	 * 编辑行业新闻页面
	 */
	@RequestMapping(value = "/proNewsEditorUrl", method = RequestMethod.GET)
	public String proNewsEditorUrl() {
		return "fms/index/proNewsEditor";
	}
	
	/**
	 * 删除行业新闻信息
	 * @param companyRosterInfoId
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/deleteProNews")
	@ResponseBody
	public ResultInfo deleteProNews(@RequestParam("professionNewsInfoId") List<Integer> professionNewsInfoId,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> map = new HashMap<String, String>();
		ResultInfo resultInfo = new ResultInfo();
		try {
			for (Integer id : professionNewsInfoId) {
			resultInfo=	indexService.deleteProNews(new Long(id),loginInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除失败!");
		}
		return resultInfo;// 重定向
	}
	
	/**
	 * 更新公司政策页面url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateComPolicyFileUrl", method = RequestMethod.GET)
	public ModelAndView updateComPolicyFileUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/updateComPolicyFile",reqParamMap);
	}
	
	/**
	 * 更新行业新闻页面url
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateProNewsFileUrl", method = RequestMethod.GET)
	public ModelAndView updateProNewsFileUrl(@RequestParam("param") String param)
	{
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/index/updateProNewsFile",reqParamMap);
	}
	
	/**
	 * 更新公司政策附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/updateComPolicyInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateComPolicyInfo(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		String title=JsonUtils.getJsonValueByKey("title", param);
		String companyPolicyInfoId=JsonUtils.getJsonValueByKey("companyPolicyInfoId", param);
		Map tMap=new HashMap();
		try {	tMap.put("editorInfoSucFlag", editorInfoSucFlag);
				tMap.put("title", title);
				tMap.put("htmlContext", content);
				tMap.put("companyPolicyInfoId", companyPolicyInfoId);
				resultInfo = indexService.updateComPolicyInfo(tMap,loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 更新行业新闻附文本编辑问题
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/updateProNewsInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateProNewsInfo(String param,@RequestParam("content") String content,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String editorInfoSucFlag=JsonUtils.getJsonValueByKey("editorInfoSucFlag", param);
		String title=JsonUtils.getJsonValueByKey("title", param);
		String professionNewsInfoId=JsonUtils.getJsonValueByKey("professionNewsInfoId", param);
		Map tMap=new HashMap();
		try {	tMap.put("editorInfoSucFlag", editorInfoSucFlag);
				tMap.put("title", title);
				tMap.put("htmlContext", content);
				tMap.put("professionNewsInfoId", professionNewsInfoId);
				resultInfo = indexService.updateProNewsInfo(tMap,loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("上传文本信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	
	/**
	 * 查询自我介绍信息
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/querySelfIntroductionText", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo querySelfIntroductionText(String param,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String userId=JsonUtils.getJsonValueByKey("userId", param);
		Map tMap=new HashMap();
		try {	tMap.put("userId", userId);
				resultInfo = indexService.querySelfIntroductionText(tMap, loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取自我介绍信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 更新自我介绍信息
	 * @param productId
	 * @return
	 */
	@SuppressWarnings({  "rawtypes", "unchecked", "unused" })
	@RequestMapping(value = "/updateSelfIntroductionText", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateSelfIntroductionText(String param,ModelMap modelMap){
		LoginInfo loginInfo =(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		String userId=JsonUtils.getJsonValueByKey("userId", param);
		String selfIntroduction=JsonUtils.getJsonValueByKey("selfIntroduction", param);
		Map tMap=new HashMap();
		try {	tMap.put("userId", userId);
				tMap.put("selfIntroduction", selfIntroduction);
				resultInfo = indexService.updateSelfIntroductionText(tMap, loginInfo);
		} catch (CisCoreException e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("更新自我介绍信息失败");
			e.printStackTrace();
		}
		return resultInfo;
	}
}
