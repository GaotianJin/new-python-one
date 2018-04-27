package com.fms.controller.sales;



import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.fms.service.sales.SalesService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Controller
@RequestMapping("/sales")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class SalesController {
	private static final Logger log = Logger.getLogger(SalesController.class);
	
	@Autowired
	private SalesService salesService;
	
	/*跳转页面*/
	@RequestMapping(value="/searchAgent",method=RequestMethod.GET)
	public String searchAgent(){
		return "fms/sales/searchAgent";
	}
	/*******************************薪资管理***********************************************
	
	/**
	 * 获取薪资管理页面url
	 */
	@RequestMapping(value = "/salaryCalculationListUrl", method = RequestMethod.GET)
	public String list(Model model) {
		return "fms/sales/salaryCalculationList";
	}

	/**
	 * 获取已经结算的薪资信息列表
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/querySalaryCalList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid querySalaryCalList(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = salesService.getSalaryCalList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 薪资结算
	 */
	@RequestMapping(value = "/salaryCal", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo salaryCal(String param,ModelMap modelMap){
		ResultInfo resultInfo=new ResultInfo();
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		String calYear=JsonUtils.getJsonValueByKey("calYear", param);
		String calMonth=JsonUtils.getJsonValueByKey("calMonth", param);
		if(calYear!=null&&!calYear.equals("")&&calMonth!=null&&!calMonth.equals("")){
			try {
				resultInfo=salesService.salaryCal(calYear, calMonth, loginInfo);
			} catch (Exception e) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("薪资结算失败！");
				return resultInfo;
			}
		
		}else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("薪资计算年/计算月不能为空");
			return resultInfo;
			
		}
		
		return resultInfo;
	}
	
	/**
	 * 薪资结转
	 */
	@RequestMapping(value = "/salaryForward", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo salaryForward(String param,ModelMap modelMap){
		ResultInfo resultInfo=new ResultInfo();
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		String calYear=JsonUtils.getJsonValueByKey("calYear", param);
		String calMonth=JsonUtils.getJsonValueByKey("calMonth", param);
		if(calYear!=null&&calMonth!=null){
			try {
				resultInfo=salesService.salaryForward(calYear, calMonth, loginInfo);
			} catch (Exception e) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("薪资结转失败！");
				return resultInfo;
			}
		
		}
		
		return resultInfo;
	}
	
	/*******************************薪资查询***********************************************
	
	/**
	 * 获取 薪资查询页面url
	 */
	@RequestMapping(value = "/salaryFindListUrl", method = RequestMethod.GET)
	public String listsaleSearch(Model model) {
		return "fms/sales/salaryFindList";
	}
	/**
	 * 获取的薪资查询页面信息列表
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryAgentWageList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryAgentWageList(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = salesService.getAgentWageList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/salaryCalExport.xls", method = RequestMethod.GET)
	public ModelAndView salaryCalExport(HttpServletRequest request,
            HttpServletResponse response,@RequestParam String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==薪资查询报表导出请求数据===" + param);
			Map paramMap = new HashMap();
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");  
				String salaryCalParam = JsonUtils.getJsonValueByKey("salaryParam", param);
				paramMap = (Map)JsonUtils.jsonStrToObject(salaryCalParam, paramMap);
			}
			//开始薪资结算
			resultInfo =salesService.salaryCalExport(paramMap);
			//结算完成，生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("薪资报表导出异常！");
		}
		return null;
	}
	
	
	
	/*******************************考核管理***********************************************
	
	/**
	 * 获取用户页面url 考核管理页面
	 */
	@RequestMapping(value = "/assessmentCaluationUrl", method = RequestMethod.GET)
	public String assessmentCaluationList(Model model) {
		return "fms/sales/assessmentCaluationList";
	}
	/**
	 * 考核预警
	 */
	
	@RequestMapping(value = "/assessCal", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo assessCal(String param,ModelMap modelMap){
		ResultInfo resultInfo=new ResultInfo();
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		String assessCalYear=JsonUtils.getJsonValueByKey("assessCalYear", param);
		String assessCalMonth=JsonUtils.getJsonValueByKey("assessCalMonth", param);
		String assessUnit=JsonUtils.getJsonValueByKey("assessUnit", param);
		
		if(assessCalYear!=null&&assessCalMonth!=null&&assessUnit!=null){
			try {
				resultInfo=salesService.assessCal(assessCalYear, assessCalMonth, assessUnit,loginInfo);
			} catch (Exception e) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("考核预警结算失败！");
				return resultInfo;
			}
		
		}else {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("考核起止日期/结算日期不能为空");
			return resultInfo;
			
		}
		
		return resultInfo;
	}
	
	
	/**
	 * 获取已经预警的考核信息列表
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryAssessCalList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryAssessCalList(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = salesService.getAssessCalList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	
	/**
	 * 考核结转
	 */
	@RequestMapping(value = "/assessForward", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo assessForward(String param,ModelMap modelMap){
		ResultInfo resultInfo=new ResultInfo();
		LoginInfo loginInfo=(LoginInfo)modelMap.get(Constants.USER_INFO_SESSION);
		String assessCalYear=JsonUtils.getJsonValueByKey("assessYear", param);
		String assessCalMonth=JsonUtils.getJsonValueByKey("assessMonth", param);
		String assessUnit=JsonUtils.getJsonValueByKey("assessUnit", param);
		if(assessCalYear!=null&&assessCalMonth!=null&&assessUnit!=null){
			try {
				resultInfo=salesService.assessForward(assessCalYear, assessCalMonth,assessUnit,loginInfo);
			} catch (Exception e) {
				resultInfo.setSuccess(false);
				resultInfo.setMsg("考核结转失败！");
				return resultInfo;
			}
		
		}
		
		return resultInfo;
	}
	
	
	
	/*******************************考核查询***********************************************
	/**
	 * 获取用户页面url 考核查询页面
	 */
	@RequestMapping(value = "/assessmentFindUrl", method = RequestMethod.GET)
	public String assessmentFindList(Model model) {
		return "fms/sales/assessmentFindList";
	}
	
	
	
	
	/**
	 * 获取的薪资查询页面信息列表
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryAgentAssessList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryAgentAssessList(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = salesService.getAgentAssessList(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 考核查询导出报表
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/assessCalExport.xls", method = RequestMethod.GET)
	public ModelAndView assessCalExport(HttpServletRequest request,
            HttpServletResponse response,@RequestParam String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			log.info("==考核查询报表导出请求数据===" + param);
			Map paramMap = new HashMap();
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				String assessParam = JsonUtils.getJsonValueByKey("assessParam", param);
				paramMap = (Map)JsonUtils.jsonStrToObject(assessParam, paramMap);
			}
			//开始薪考核结算
			resultInfo =salesService.assessCalExport(paramMap);
			//考核完成，生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("考核报表导出异常！");
		}
		return null;
	}
	
	/**
	 * 调整凭证
	 */
	@RequestMapping(value = "/printDown", method = RequestMethod.GET)
	public String listApplyOfPrint(Model model) {
		return "fms/sales/printDown";
	}
	
    /**
     * 薪资信息维护初始页面
     */
	@RequestMapping(value = "/listSalaryUrl", method = RequestMethod.GET)
    public String listSalaryUrl(Model model) {
        return "fms/sales/listSalary";
    }
	
	/**
	 * 基本工资导入页面
	 */
	@RequestMapping(value = "/importSalaryUrl", method = RequestMethod.GET)
    public ModelAndView importSalaryUrl() {
		return new ModelAndView("fms/sales/importSalary");
    }
	
	/**
	 * 基本工资导入
	 * @param response
	 * @param salaryFileName
	 * @param param
	 * @param modelMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/importSalaryFile", method = RequestMethod.POST)
	@ResponseBody
	public void importSalaryFile(HttpServletResponse response,@RequestParam MultipartFile[] salaryFileName,@RequestParam String param,ModelMap modelMap) throws Exception{
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			String salaryFileType = JsonUtils.getJsonValueByKey("salaryFileType", param);
			String salaryFileDate = JsonUtils.getJsonValueByKey("salaryFileDate", param);
			resultInfo = salesService.importSalaryFile(salaryFileName, salaryFileType, salaryFileDate, loginInfo);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
			writer.write(JsonUtils.objectToJsonStr(resultInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("导入文件出错");
		}
	}
	
	/**
	 * 查询工资主表
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/querySalaryList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid querySalaryList(DataGridModel dgm, String queryParam) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			String param = JsonUtils.getJsonValueByKey("month", queryParam);
			if("-".equals(param)){
				paramMap = (Map)JsonUtils.jsonStrToObject(null, paramMap);
				dataGrid = salesService.querySalaryList(dgm, paramMap);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = salesService.querySalaryList(dgm, paramMap);
			}
		}else{
			dataGrid = salesService.querySalaryList(dgm, paramMap);
		}
		}catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 基本工资明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/baseSalaryDetailUrl", method = RequestMethod.GET)
    public ModelAndView baseSalaryDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailBaseSalary",reqParamMap);
    }
	
	/**
	 * 查询基本工资表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryBaseSalaryList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryBaseSalaryList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.queryBaseSalaryList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 销售佣金明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/saleCommissionIdDetailUrl", method = RequestMethod.GET)
    public ModelAndView saleCommissionIdDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailSaleCommissionId",reqParamMap);
    }
	
	/**
	 * 国金交易佣金明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/guojinCommissionIdDetailUrl", method = RequestMethod.GET)
    public ModelAndView guojinCommissionIdDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailGuojinCommissionId",reqParamMap);
    }
	
	/**
	 * 海外投资明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/overseasCommissionIdDetailUrl", method = RequestMethod.GET)
    public ModelAndView overseasCommissionIdDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailOverseasCommissionId",reqParamMap);
    }
	
	/**
	 * 补发利益明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/reissueIdDetailUrl", method = RequestMethod.GET)
    public ModelAndView reissueIdDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailReissueId",reqParamMap);
    }
	
	/**
	 * 暂扣利益明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/withholdIdDetailUrl", method = RequestMethod.GET)
    public ModelAndView withholdIdDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailWithholdId",reqParamMap);
    }
	
	/**
	 * 项目端佣金明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/projectCommissionIdUrl", method = RequestMethod.GET)
    public ModelAndView projectCommissionIdUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailProjectCommissionId",reqParamMap);
    }
	
	/**
	 * 查询国金交易佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryGuojinCommissionIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryGuojinCommissionIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.queryGuojinCommissionIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询海外投资表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryOverseasCommissionIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryOverseasCommissionIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.queryOverseasCommissionIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询项目端佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryProjectCommissionIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryProjectCommissionIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.queryProjectCommissionIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询补发利益表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryReissueIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryReissueIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.queryReissueIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询销售佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/querySaleCommissionIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid querySaleCommissionIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.querySaleCommissionIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询暂扣利益表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryWithholdIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryWithholdIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.queryWithholdIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	/************************************综合查询*************************************/
	/**
	 * 获取工资查询页面url
	 */
	@RequestMapping(value = "/querySalaryUrl", method = RequestMethod.GET)
	public String querySalaryUrl(Model model) {
		return "fms/sales/querySalaryDetail";
	}
	
	
	/**
	 * 查询基本工资表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryBaseSalaryDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryBaseSalaryDetail(DataGridModel dgm,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		if(queryParam == null||"".equals(queryParam)||"{}".equals(queryParam)){
			List returnList = new ArrayList();
			dataGrid.setRows(returnList);
			dataGrid.setTotal(0);
			return dataGrid;
		}
		queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		dataGrid = salesService.queryBaseSalaryDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 查询销售佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/querySaleCommissionDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid querySaleCommissionDetail(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.querySaleCommissionDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 查询国金佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryGuojinCommissionDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryGuojinCommissionDetail(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.queryGuojinCommissionDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 查询海外佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryOverseasCommissionDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryOverseasCommissionDetail(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.queryOverseasCommissionDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询补发利益表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryReissueDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryReissueDetail(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.queryReissueDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询暂扣利益表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryWithholdDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryWithholdDetail(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.queryWithholdDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	
	/**
	 * 查询海外佣金表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryProjectCommissionDetail",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryProjectCommissionDetail(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.queryProjectCommissionDetail(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 佣金导入页面
	 */
	@RequestMapping(value = "/importCommissionUrl", method = RequestMethod.GET)
    public ModelAndView importCommissionUrl() {
		return new ModelAndView("fms/sales/importCommission");
    }
	
	/**
     * 佣金信息维护初始页面
     */
	@RequestMapping(value = "/listCommissionUrl", method = RequestMethod.GET)
    public String listCommissionUrl(Model model) {
        return "fms/sales/listCommission";
    }
	
	/**
	 * 查询综合工资表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryGeneralSalary",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryGeneralSalary(DataGridModel dgm,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		if(queryParam == null||"".equals(queryParam)||"{}".equals(queryParam)){
			List returnList = new ArrayList();
			dataGrid.setRows(returnList);
			dataGrid.setTotal(0);
			return dataGrid;
		}
		queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		String salayDetail_year = JsonUtils.getJsonValueByKey("salayDetail_year", queryParam);
		String salayDetail_month = JsonUtils.getJsonValueByKey("salayDetail_month", queryParam);
		String month = salayDetail_year+"-"+salayDetail_month;
		String salayDetail_endyear = JsonUtils.getJsonValueByKey("salayDetail_endyear", queryParam);
		String salayDetail_endmonth = JsonUtils.getJsonValueByKey("salayDetail_endmonth", queryParam);
		String endMonth = salayDetail_endyear+"-"+salayDetail_endmonth;
		if(!"-".equals(endMonth)){
			paramMap.put("endMonth", endMonth);
		}
		paramMap.put("month", month);
		dataGrid = salesService.queryGeneralSalary(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 综合基本工资跳转页面
	 */
	@RequestMapping(value = "/generalSalaryDetailUrl", method = RequestMethod.GET)
    public ModelAndView generalSalaryDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/generalBaseSalary",reqParamMap);
    }
	
	/**
	 * 查询综合基本工资表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/queryGeneralBaseSalaryList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryGeneralBaseSalaryList(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.queryGeneralBaseSalaryList(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 综合佣金跳转页面
	 */
	@RequestMapping(value = "/generalCommissionDetailUrl", method = RequestMethod.GET)
    public ModelAndView generalCommissionDetailUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/generalCommission",reqParamMap);
    }
	
	/**
	 * 佣金明细跳转页面
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/detailsCommissionOverviewUrl", method = RequestMethod.GET)
    public ModelAndView detailsCommissionOverviewUrl(@RequestParam("param") String param) {
    	log.info("==请求数据===" + param);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		return new ModelAndView("fms/sales/detailsCommissionOverviewId",reqParamMap);
    }
	
	/**
	 * 查询佣金明细表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/querydetailsCommissionIdList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid querydetailsCommissionIdList(DataGridModel dgm,@RequestParam("param") String param,String queryParam,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(queryParam!=null&&!"".equals(queryParam)){
			queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
		}
		paramMap.put("month", param);
		dataGrid = salesService.querydetailsCommissionIdList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 查询佣金总额表
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@RequestMapping(value = "/querydetailsCommission",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid querydetailsCommission(DataGridModel dgm,@RequestParam("param") String param,ModelMap modelMap){
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
		paramMap.put("month", param);
		dataGrid = salesService.querydetailsCommission(dgm, paramMap,loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}
}
