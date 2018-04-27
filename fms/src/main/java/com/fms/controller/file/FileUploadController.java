package com.fms.controller.file;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.fms.db.model.DefFileInfo;
import com.fms.service.file.FileUploadService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/fileUpload")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class FileUploadController {
	
	private static final Logger log = Logger.getLogger(FileUploadController.class);
	
	@Autowired
	private FileUploadService fileUploadService;
	/**
	 * 文件上传url
	 */
	@RequestMapping(value = "/fileUploadUrl", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/file/uploadFile",reqParamMap);
	}
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public void fileUpload(@RequestParam("param") String param,HttpServletResponse response,
			@RequestParam MultipartFile[] uploadFileName,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			param = StringUtils.encodeStr(param);
			System.out.println("文件相关参数param：==="+param);	
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = fileUploadService.uploadFile(uploadFileName,param,loginInfo);
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo deleteFile(String param,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			param = param.replaceAll("\\\\", "/");
			//param = param.replaceAll("/", "");
			log.info("==请求数据处理后===" + param);
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			
			//String fileList = JsonUtils.getJsonValueByKey("fileList", param);
			List<DefFileInfo> defFileInfoList = JsonUtils.jsonArrStrToList(param, DefFileInfo.class);
			resultInfo = fileUploadService.deleteFile(defFileInfoList, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("保存客户信息出错");
		}
		return resultInfo;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getFileList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getFileListByBusinessNoAndType(DataGridModel dgm,String queryParam,ModelMap modelMap) {
		DataGrid dataGrid = new DataGrid();
		try {
			log.info("==请求数据===" + queryParam);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			Map paramMap = new HashMap();
			if(queryParam!=null&&!"".equals(queryParam)){
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = JsonUtils.jsonStrToMap(queryParam);
			}
			dataGrid = fileUploadService.getFileListByBusinessNoAndType(dgm, paramMap, loginInfo);
		} catch (Exception e) {
		}
		return dataGrid;
	}
	
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	//@ResponseBody
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("param") String param,ModelMap modelMap){
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			param = StringUtils.encodeStr(param);
			System.out.println("文件相关参数param：==="+param);	
			DefFileInfo defFileInfo = (DefFileInfo)JsonUtils.jsonStrToObject(param, DefFileInfo.class);
			fileUploadService.download(request, response, defFileInfo,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件上传url
	 */
	@RequestMapping(value = "/investCustFileUploadUrl", method = RequestMethod.GET)
	public ModelAndView investCustFileUploadUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/file/investCustUploadFile",reqParamMap);
	}
	
	/**
	 * 文件上传url
	 */
	@RequestMapping(value = "/custUpgradeAuditUrl", method = RequestMethod.GET)
	public ModelAndView custUpgradeAuditUrl(@RequestParam("param") String param) {
		log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/file/custUpgradeAudit",reqParamMap);
	}
}

