package com.fms.controller.product;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
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

import com.fms.service.product.IncomeDisService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

/**
 * 收益分配
 * 
 * @author
 * 
 */
@Controller
@RequestMapping("/incomeDis")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class IncomeDisController {

	@Autowired
	private IncomeDisService incomeDisService;

	private static final Logger logger = Logger
			.getLogger(IncomeDisController.class);

	/**
	 * 收益分配计算页面
	 */
	@RequestMapping(value = "/incomeDistributionListUrl", method = RequestMethod.GET)
	public String ListIncomeDistribution(Model model) {
		return "fms/product/listIncomeDistribution";
	}

	/**
	 * 收益分配导入页面
	 */
	@RequestMapping(value = "/incomeDistributionImportUrl", method = RequestMethod.GET)
	public String incomeDistributionImport(Model model) {
		return "fms/product/listIncomeDisImport";
	}

	/**
	 * 收益分配明细页面
	 */
	@RequestMapping(value = "/incomeDistributionDetailUrl", method = RequestMethod.GET)
	public String incomeDistributionDetail(Model model) {
		return "fms/product/listIncomeDisDetail";
	}

	
	@RequestMapping(value = "/incomeDisBatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo incomeDisBatch() {
		ResultInfo resultInfo = new ResultInfo();
		try {
			resultInfo = incomeDisService.incomeDisBatch();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	
	
	/**
	 * 查询产品收益分配信息
	 * 
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryProductIncomeDistibuteList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryProductIncomeDistibuteList(DataGridModel dgm,
			String queryParam, ModelMap modelMap) {
		DataGrid dataGrid = new DataGrid();
		try {
			if (queryParam != null || !"".equals(queryParam)) {
				logger.info("==请求数据===" + queryParam);
			}
			// 获取用户登录信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			Map paramMap = new HashMap();
			paramMap = (Map) JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = incomeDisService.queryProductIncomeDistibuteList(dgm,
					paramMap, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	/**
	 * 查询产品所有交易收益分配信息
	 * 
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryProductIncomeDistibuteDetailList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryProductIncomeDistibuteDetailList(DataGridModel dgm,
			String queryParam, ModelMap modelMap) {
		DataGrid dataGrid = new DataGrid();
		try {
			if (queryParam != null || !"".equals(queryParam)) {
				logger.info("==请求数据===" + queryParam);
			}
			// 获取用户登录信息
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			Map paramMap = new HashMap();
			paramMap = (Map) JsonUtils.jsonStrToObject(queryParam, paramMap);
			dataGrid = incomeDisService.queryProductIncomeDistibuteDetailList(
					dgm, paramMap, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}

	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/productIncomeDisDetail.xls", method = RequestMethod.GET)
	public ModelAndView donwloadProductIncomeDis(HttpServletRequest request,
            HttpServletResponse response,@RequestParam String pdIncomeDistributeId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map paramMap = new HashMap();
			if(pdIncomeDistributeId!=null&&!"".equals(pdIncomeDistributeId)){
				logger.info("==请求数据===" + pdIncomeDistributeId);
				paramMap.put("pdIncomeDistributeId", pdIncomeDistributeId);
			}
			//开始结算
			resultInfo = incomeDisService.getProductIncomeDistributeDetailInfo(paramMap);
			//结算完成，生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("产品收益分配导出报表出现异常！");
		}
		return null;
	}
	
	
	@RequestMapping(value = "/importIncomeDisFile", method = RequestMethod.POST)
	@ResponseBody
	public void importIncomeDisFile(HttpServletResponse response,
			@RequestParam MultipartFile[] incomeFileName,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = incomeDisService.importIncomeDisFile(incomeFileName, loginInfo);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
			writer.write(JsonUtils.objectToJsonStr(resultInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("收益分配导入文件出错");
		}
	}
	/**
	 * 手动生成收益分配短信
	 * @return
	 */
	@RequestMapping(value = "/incomeDisSmsBatch", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo incomeDisSmsBatch() {
		ResultInfo resultInfo = new ResultInfo();
		try {
			resultInfo = incomeDisService.incomeDisSmsBatch();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
}
