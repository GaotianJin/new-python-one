package com.fms.controller.sales;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.leveldb.replicated.dto.Login;
import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fms.db.model.Agent;
import com.fms.db.model.AgentAccInfo;
import com.fms.db.model.AgentCertificationInfo;
import com.fms.db.model.AgentDepartment;
import com.fms.db.model.AgentFamilyInfo;
import com.fms.db.model.AgentNurserInfo;
import com.fms.db.model.AgentOtherInfo;
import com.fms.db.model.AgentPositionTrace;
import com.fms.db.model.AgentWorkInfo;
import com.fms.service.sales.SalesService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.ExcelTool;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;


@Controller
@RequestMapping("/sales")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class AgentController {
	
	private static final Logger log = Logger.getLogger(AgentController.class);
	@Autowired
	private SalesService salesService;
	
    @RequestMapping(value = "/listAgentUrl", method = RequestMethod.GET)
    public String listAgent(Model model) {
        return "fms/sales/listAgent";
    }
    
    /**
     * 获取增加顾问页面url
     */
    @RequestMapping(value = "/addAgentUrl", method = RequestMethod.GET)
    public ModelAndView addAgentUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/addAgent",reqParamMap);
        //return "fms/sales/addAgent";
    }
    
    /**
     * 获取增加顾问页面url
     */
    @RequestMapping(value = "/uploadAgentPhoto", method = RequestMethod.GET)
    public ModelAndView uploadAgentPhoto(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/uploadAgentPhoto",reqParamMap);
    }
    
    
    /**
     * 获取修改顾问页面url
     */
    @RequestMapping(value = "/updateAgentUrl", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String updateAgentUrl(Model model) {
        return "fms/sales/addAgent";
    }
    
   
    
    /**
     * 获取修改顾问页面url
     */
    @RequestMapping(value = "/selectAgentUrl", method = RequestMethod.GET)
    public String searchAgentUrl(Model model) {
        return "fms/sales/selectAgent";
    }
    

    /**
	 * @param searchParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryAgentList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryAgentList(DataGridModel dgm,String queryParam,ModelMap modelMap){
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
			dataGrid = salesService.queryAgentList(dgm, paramMap, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	@RequestMapping(value = "/verifyUserCode",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo verifyUserCode(String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			String userId = JsonUtils.getJsonValueByKey("userId", param);
			String userCode = JsonUtils.getJsonValueByKey("userCode", param);
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			resultInfo = salesService.verifyUserCode(userId,userCode,operate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/verifyAgentCode",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo verifyAgentCode(String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			String agentCode = JsonUtils.getJsonValueByKey("agentCode", param);
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			resultInfo = salesService.verifyAgentCode(agentId,agentCode,operate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	
	@SuppressWarnings({"unchecked" })
	@RequestMapping(value = "/submitAgent",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo submitAgent(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//1.获取财富顾问基本信息
			String agentBaseInfoStr = JsonUtils.getJsonValueByKey("agentBaseInfo", param);
			agentBaseInfoStr = JsonUtils.decodeUrlParams(agentBaseInfoStr,"utf-8");
			Agent agent = (Agent)JsonUtils.jsonStrToObject(agentBaseInfoStr, Agent.class);
			//2.获取财富顾问家庭成员信息
			String agentFamilyInfoStr = JsonUtils.getJsonValueByKey("agentFamilyInfoList", param);
			agentFamilyInfoStr = JsonUtils.decodeUrlParams(agentFamilyInfoStr,"utf-8");
			List<AgentFamilyInfo> agentFamilyInfoList = JsonUtils.jsonArrStrToList(agentFamilyInfoStr, AgentFamilyInfo.class);
			//3.获取财富顾问资格证书信息
			String agentCertificationInfoStr = JsonUtils.getJsonValueByKey("agentCertificationInfoList", param);
			List<AgentCertificationInfo> agentCertificationInfoList = JsonUtils.jsonArrStrToList(agentCertificationInfoStr, AgentCertificationInfo.class);
			//4.获取财富顾问职级信息
			String agentPositionInfoStr = JsonUtils.getJsonValueByKey("agentPositionInfoList", param);
			List<AgentPositionTrace> agentPositionList = JsonUtils.jsonArrStrToList(agentPositionInfoStr, AgentPositionTrace.class);
			//4.1获取理财经培育信息
			String agentNurserInfoStr = JsonUtils.getJsonValueByKey("agentNurserInfoList", param);
			List<AgentNurserInfo> agentNurserList = JsonUtils.jsonArrStrToList(agentNurserInfoStr, AgentNurserInfo.class);
			//4.2获取理财经理从业经历信息
			String agentWorkInfoStr = JsonUtils.getJsonValueByKey("agentWorkInfoList", param);
			List<AgentWorkInfo> agentWorkList = JsonUtils.jsonArrStrToList(agentWorkInfoStr, AgentWorkInfo.class);
			//5.获取财富顾问所属网点信息
			/*String agentStoreInfoStr = JsonUtils.getJsonValueByKey("agentStoreInfoList", param);
			List<Map<String, String>> agentStoreList = JsonUtils.jsonArrStrToListMap(agentStoreInfoStr);*/
			//6.获取财富顾问所属团队信息
			String agentDepartmentInfoStr = JsonUtils.getJsonValueByKey("agentDepartInfoList", param);
/*			List<AgentDepartment> agentDepartmentList = JsonUtils.jsonArrStrToList(agentDepartmentInfoStr, AgentDepartment.class);
*/			List<Map<String, String>> agentDepartmentList = JsonUtils.jsonArrStrToListMap(agentDepartmentInfoStr);
			//9.获取员工其他信息
			String agentOtherInfoStr = JsonUtils.getJsonValueByKey("agentOtherInfoList", param);
			List<AgentOtherInfo> agentOtherInfoList = JsonUtils.jsonArrStrToList(agentOtherInfoStr, AgentOtherInfo.class);
			//10.获取员工工资卡信息
	        String agentSalaryAccInfoStr = JsonUtils.getJsonValueByKey("agentSalaryAccInfoList", param);
	        List<AgentAccInfo> agentAccInfoList = JsonUtils.jsonArrStrToList(agentSalaryAccInfoStr, AgentAccInfo.class);
			//7.获取操作信息
			String operate = JsonUtils.getJsonValueByKey("operate", param);
			//8.需要创建用户，准备用户信息
			String userCode = JsonUtils.getJsonValueByKey("userCode", agentBaseInfoStr);
			
			//新增财富顾问
			resultInfo = salesService.submitAgent(agent, agentFamilyInfoList, agentCertificationInfoList, 
					agentPositionList, agentNurserList,agentWorkList,agentDepartmentList,agentOtherInfoList,agentAccInfoList, loginInfo, userCode, operate);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("新增财富顾问信息出错");
		}
		return resultInfo;
	}
	
	@RequestMapping(value = "/queryAgentInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo queryAgentInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取财富顾问基本信息
			String agentBaseInfoStr = JsonUtils.getJsonValueByKey("agentBaseInfo", param);
			Agent agent = (Agent)JsonUtils.jsonStrToObject(agentBaseInfoStr, Agent.class);
			
			resultInfo = salesService.queryAgentInfo(agent, loginInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("获取财富顾问详细信息出错");
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/deleteAgent",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo deleteAgent(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取财富顾问基本信息
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			resultInfo = salesService.deleteAgent(agent, loginInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除财富顾问信息出错");
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/uploadAgentImage",method = RequestMethod.POST)
	@ResponseBody
	public void uploadAgentImage(@RequestParam("param") String param,HttpServletResponse response,
			@RequestParam MultipartFile[] agentImage,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			//获取财富顾问基本信息
			String agentId = JsonUtils.getJsonValueByKey("agentId", param);
			Agent agent = new Agent();
			if (agentId!=null&&!"".equals(agentId)) {
				agent.setAgentId(new Long(agentId));
			}
			resultInfo = salesService.uploadAgentImage(agentImage,param,loginInfo);
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
	
	/**
	 * 		导出员工基本信息
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/exportAgentInfo.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ModelAndView exportAgentInfo(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
		// 输出日志，便于调试
		log.info("Controller层类中exportAgentInfo()接受参数queryParam:" + queryParam);
		// 获取业务报表导出Excel模板
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/agentsInfo.xls";
		// 分配ResultInfo对象返回程序异常信息
		ResultInfo resultInfo = new ResultInfo();
		try {
			// 查询参数转成Map集合
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 调用Service层方法统计产品预约信息
			Map  datas = this.salesService.exportAgentInfo(paramMap);
			// 调用Excel模板生成Excel业务报表
			OutputStream out=null;
	        BufferedOutputStream bos=null;
	        BufferedInputStream bis=null;
	        InputStream in=null;
	        try{
		        in=ExcelTool.exportExcel(path, datas);
		        bis=new BufferedInputStream(in);
		        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
		        String fileName = "employeeInfo"+now+".xls";
		      //设置头文件  可参照 http://blog.csdn.net/fanyuna/article/details/5568089
		        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		        byte[] data=new byte[1024];
		        int bytes=0;
		        out=response.getOutputStream();
		        bos=new BufferedOutputStream(out);
		        while((bytes=bis.read(data, 0, data.length))!=-1){
		            bos.write(data,0,bytes);                                        //写出文件流                                     
		        }
		        bos.flush();
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            try {
	                bos.close();
	                out.close();
	                bis.close(); 
	                in.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导出员工基本信息出现异常！");
		}
		return null;
	}
	
}
