package com.fms.controller.product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.PDAmountOrderQueueInfo;
import com.fms.db.model.PDProduct;
import com.fms.service.product.ProductOrderService;
import com.sinosoft.core.application.util.CisCoreException;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.DateUtils;
import com.sinosoft.util.ExcelTool;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;

/**
 * 产品预约
 * 
 * @author
 * 
 */
@Controller
@RequestMapping("/productOrder")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class ProductOrderController {

	@Autowired
	private ProductOrderService productOrderService; 
	
	private static final Logger logger = Logger.getLogger(ProductOrderController.class);
	
	
	/** 获取产品额度预约页面 **/
	@RequestMapping(value = "/productAmountOrder", method = RequestMethod.GET)
	public String productAmountOrder(Model model) {
		return "fms/product/productAmountOrder";
	}
	
	/** 获取产品预约查询初始页面 **/
	@RequestMapping(value = "/productOrderQuery", method = RequestMethod.GET)
	public String productOrderQuery(Model model) {
		return "fms/product/productOrderQuery";
	}
	
	/** 获取产品预约审核初始页面 **/
	@RequestMapping(value = "/productOrderAudit", method = RequestMethod.GET)
	public String listProductDefUrl(Model model) {
		return "fms/product/productOrderAudit";
	}
	
	/** 获取产品队列预约初始页面 **/
	@RequestMapping(value = "/listRemainAmountDistributeUrl", method = RequestMethod.GET)
	public String listRemainAmountDistributeUrl(Model model) {
		return "fms/product/listRemainAmountDistribute";
	}
	
	/** 获取产品额度分配页面 **/
	@RequestMapping(value = "/addProductAmountOrder", method = RequestMethod.GET)
	public ModelAndView addProductAmountOrder(@RequestParam("param") String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		String operate = reqParamMap.get("operate");//detailCustOrder
		String isDistribute = reqParamMap.get("isDistribute").toString();
		if(operate!=null&&!"".equals(operate)&&"addCustOrder".equals(operate)
				&&isDistribute!=null&&!"".equals(isDistribute)&&"N".equals(isDistribute)){
			reqParamMap.put("comId", loginInfo.getComId().toString());
		}
		return new ModelAndView("fms/product/addProductAmountOrder",reqParamMap);
	}
	
	/** 获取产品剩余额度分配页面 **/
	@SuppressWarnings("unused")
	@RequestMapping(value = "/addProductRemainAmountOrder", method = RequestMethod.GET)
	public ModelAndView addProductRemainAmountOrder(@RequestParam("param") String param,ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		String operate = reqParamMap.get("operate");//detailCustOrder
		String queueIsDistribute = reqParamMap.get("queueIsDistribute").toString();
		return new ModelAndView("fms/product/addProductRemainAmountOrder",reqParamMap);
	}

	/**
	 * 获取“产品预约申请”信息:查询出各分公司对某款产品的申请信息
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getAllPdVerificationInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getAllPdVerificationInfo(DataGridModel dgm, String queryParam, ModelMap model) { //queryParam参数为转换为JSON对象的页面条件
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if (queryParam != null && !"".equals(queryParam)) {
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);// JSON格式的页面参数转换为Map对象
			}
			dataGrid = productOrderService.getAllPdVerificationInfo(dgm, paramMap, loginInfo); // 调用service层接口方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 产品预约额度审核
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/auditPdAmountOrderAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo auditPdAmountOrderAudit(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try{
//			PdAmountOrderInfo pdAmountOrderInfo = new PdAmountOrderInfo();
//			pdAmountOrderInfo = (PdAmountOrderInfo)JsonUtils.jsonStrToObject(param, pdAmountOrderInfo);
			List<PDAmountOrderInfo> productAmountOrderList = JsonUtils.jsonArrStrToList(param, PDAmountOrderInfo.class);
			for(PDAmountOrderInfo pdAmountOrderInfo:productAmountOrderList){
				resultInfo = productOrderService.submitPdOrderAudit(pdAmountOrderInfo, loginInfo);
			}
		}catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该预约产品提交审核失败："+e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cancelPdAmountOrderAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo cancelPdAmountOrderAudit(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try {
			//PdAmountOrderInfo pdAmountOrderInfo = new PdAmountOrderInfo();
			//pdAmountOrderInfo = (PdAmountOrderInfo)JsonUtils.jsonStrToObject(param, pdAmountOrderInfo);
			List<PDAmountOrderInfo> productAmountOrderList = JsonUtils.jsonArrStrToList(param, PDAmountOrderInfo.class);
			for(PDAmountOrderInfo pdAmountOrderInfo:productAmountOrderList){
				resultInfo = productOrderService.cancelPdAmountOrderAudit(pdAmountOrderInfo, loginInfo);
			}
		} catch(Exception e) {
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该预约产品撤销操作失败:" + e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	/**
	 * 预约审核数据查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryComPdAmountOrderInfoList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryComPdAmountOrderInfoList(DataGridModel dgm,String queryParam,ModelMap model) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if(queryParam!=null&&!"".equals(queryParam)){
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			}
			dataGrid = productOrderService.queryComPdAmountOrderInfoList(dgm,paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 产品预约查询页面获取值
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/getProductAndComInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getProductAndComInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = productOrderService.getProductAndComInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	
	@RequestMapping(value = "/saveProductAmountOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo saveProductAmountOrderInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			String pdAmountOrderInfoStr = JsonUtils.getJsonValueByKey("pdAmountOrderInfo", param);
			String foundDate = JsonUtils.getJsonValueByKey("foundDate", pdAmountOrderInfoStr);
			String custConfirmOrder = JsonUtils.getJsonValueByKey("custConfirmOrder", param);
			String isInviteCode = JsonUtils.getJsonValueByKey("isInviteCode", param);
			String productName = JsonUtils.getJsonValueByKey("productName", param);
			String isDistribute = JsonUtils.getJsonValueByKey("isDistribute", param);
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("isInviteCode", isInviteCode);
			paramMap.put("productName", productName);
			paramMap.put("isDistribute", isDistribute);
			PDAmountOrderInfo pdAmountOrderInfo = new PDAmountOrderInfo();
			PDAmountOrderQueueInfo pdAmountOrderQueueInfo = new PDAmountOrderQueueInfo();
			pdAmountOrderInfo = (PDAmountOrderInfo)JsonUtils.jsonStrToObject(pdAmountOrderInfoStr, pdAmountOrderInfo);
			pdAmountOrderQueueInfo = (PDAmountOrderQueueInfo)JsonUtils.jsonStrToObject(pdAmountOrderInfoStr, pdAmountOrderQueueInfo);
			//固收类产品期望开放日为空
			if (pdAmountOrderInfo.getExpectOpenDay()==null) {
				pdAmountOrderInfo.setExpectOpenDay(DateUtils.getDate(foundDate));
			}
			logger.info("====PdAmountOrderInfoId==="+pdAmountOrderInfo.getPdAmountOrderInfoId()+"===custConfirmOrder==="+custConfirmOrder);
			resultInfo = productOrderService.saveProductAmountOrderInfo(pdAmountOrderInfo,pdAmountOrderQueueInfo,paramMap,loginInfo);
		} catch(CisCoreException cisCoreException){
			cisCoreException.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(cisCoreException.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 获取预约信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getAllPdAmountOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getAllPdAmountOrderInfo(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			dataGrid = productOrderService.getAllPdAmountOrderInfo(dgm, paramMap, loginInfo);
		} catch (CisCoreException e) {	
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 获取队列预约信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getRemainAmountOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getRemainAmountOrderInfo(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			dataGrid = productOrderService.getRemainAmountOrderInfo(dgm, paramMap, loginInfo);
		} catch (CisCoreException e) {	
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getRemainTotalAmount", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getRemainTotalAmount(String queryParam,String operate,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			paramMap.put("operate", operate);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = productOrderService.getRemainTotalAmount(paramMap, loginInfo);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getPdOrderTotalAmount", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getPdOrderTotalAmount(String queryParam,String operate,ModelMap modelMap){
		ResultInfo resultInfo= new ResultInfo();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			paramMap.put("operate", operate);
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = productOrderService.getPdOrderTotalAmount(paramMap, loginInfo);
		} catch (CisCoreException e) {	
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	/**
	 * 预约信息撤销deletedeletepdAmountOrderInfoUrl
	 */
	/*@RequestMapping(value = "/deletepdAmountOrderInfoUrl",method = RequestMethod.POST)
	@ResponseBody//将Json转换成对象
	public ResultInfo deletepdAmountOrderInfoUrl(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		try {
			PDAmountOrderInfo pdAmountOrderInfo = new PDAmountOrderInfo();
			pdAmountOrderInfo = (PDAmountOrderInfo)JsonUtils.jsonStrToObject(param, pdAmountOrderInfo);
			resultInfo = productOrderService.deletepdAmountOrderInfo(pdAmountOrderInfo, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage()+"撤销预约失败！");
		}
		return resultInfo;
	}*/

	/**
	 * 客户预约查询
	 * @param pdAmountOrderInfoId
	 * @return
	 */
	@RequestMapping(value = "/getCustProductAmountOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getCustProductAmountOrderInfo(String pdAmountOrderInfoId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("产品预约流水号：===="+pdAmountOrderInfoId);
			resultInfo = productOrderService.getCustProductAmountOrderInfo(pdAmountOrderInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 队列客户预约查询
	 * @param pdAmountOrderQueueInfoId
	 * @return
	 */
	@RequestMapping(value = "/getCustRemainAmountOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getCustRemainAmountOrderInfo(String pdAmountOrderQueueInfoId){
		ResultInfo resultInfo = new ResultInfo();
		try {
			logger.info("产品队列预约流水号：===="+pdAmountOrderQueueInfoId);
			resultInfo = productOrderService.getCustRemainAmountOrderInfo(pdAmountOrderQueueInfoId);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	@RequestMapping(value = "/getInviteCode", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getInviteCode(){
		ResultInfo resultInfo = new ResultInfo();
		try {
			resultInfo = productOrderService.getInviteCode();
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	/**
	 * 导出预约信息
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/productOrderDetail.xls", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public ModelAndView donwloadProductOrderInfo(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
				// 输出日志，便于调试
				logger.info("Controller层类中donwloadProductOrderInfo()接受参数queryParam:" + queryParam);
				LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
				// 获取业务报表导出Excel模板
				String path = System.getProperty("fms.webroot");
				path = path+"/WEB-INF/classes/reportTemplate/productOrderDetail.xls";
				// 分配ResultInfo对象返回程序异常信息
				ResultInfo resultInfo = new ResultInfo();
				// 
				try {
					// 查询参数解码
					//queryParam = StringUtils.encodeStr(queryParam);
					// 查询参数转成Map集合
					Map paramMap = JsonUtils.jsonStrToMap(queryParam);
					// 调用Service层方法统计数据导出所需基本信息
					Map  datas = this.productOrderService.getProductOrderDetailInfo(paramMap,loginInfo);
					// 调用Excel模板生成Excel业务报表
					OutputStream out=null;
			        BufferedOutputStream bos=null;
			        BufferedInputStream bis=null;
			        InputStream in=null;
			        try{
				        in=ExcelTool.exportExcel(path, datas);
				        bis=new BufferedInputStream(in);
				        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
				        String fileName = "productOrderDetail"+now+".xls";
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
					resultInfo.setMsg("产品预约审核数据导出出现异常！");
				}
				return null;
		
	}
	
	
	
	/*public ModelAndView donwloadProductOrderInfo(@RequestParam String queryParam,HttpServletRequest request,HttpServletResponse response){
		ResultInfo resultInfo = new ResultInfo();
		try {
			queryParam = StringUtils.encodeStr(queryParam);
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			//开始导出
			resultInfo = productOrderService.getProductOrderDetailInfo(paramMap);
			//生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("预约信息导出出现异常！");
		}
		return null;
	}*/
	
	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/productOrderQueryDetail.xls", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public ModelAndView productOrderQueryInfo(@RequestParam String queryParam,HttpServletRequest request,
			HttpServletResponse response,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		try {
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			queryParam = StringUtils.encodeStr(queryParam);
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			paramMap.put("rolePrivilege", loginInfo.getRolePivilege());//获取用户权限
			paramMap.put("operComId", loginInfo.getComId().toString());//登陆用户所属管理机构ID
			paramMap.put("createUserId", loginInfo.getUserId().toString());//登陆用户ID
			//开始导出
			resultInfo = productOrderService.productOrderQueryInfo(paramMap);
			//生成Excel
			Map<String, Object> model = new HashMap<String, Object>();
			ReportCreateExcel reportCreateExcel = new ReportCreateExcel();
			model.put("reportList", resultInfo.getObj());
			return new ModelAndView(reportCreateExcel,model);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("预约信息导出出现异常！");
		}
		return null;
	}
	*/
	/**
	 * 		导出产品预约排队信息
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/productOrderQueueDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ModelAndView productOrderQueueDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
		// 输出日志，便于调试
		logger.info("Controller层类中productOrderQueueDetail()接受参数queryParam:" + queryParam);
		// 获取业务报表导出Excel模板
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/productOrderQueueDetail.xls";
		// 分配ResultInfo对象返回程序异常信息
		ResultInfo resultInfo = new ResultInfo();
		// 
		try {
			// 查询参数解码
			queryParam = StringUtils.encodeStr(queryParam);
			// 查询参数转成Map集合
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 调用Service层方法统计产品预约信息
			Map  datas = this.productOrderService.productOrderQueueInfo(paramMap);
			// 调用Excel模板生成Excel业务报表
			OutputStream out=null;
	        BufferedOutputStream bos=null;
	        BufferedInputStream bis=null;
	        InputStream in=null;
	        try{
		        in=ExcelTool.exportExcel(path, datas);
		        bis=new BufferedInputStream(in);
		        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
		        String fileName = "productOrderQueueDetail"+now+".xls";
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
			resultInfo.setMsg("导出产品预约排队报表出现异常！");
		}
		return null;
	}
	/**
	 * 根据codeType获取分公司表中的数据
	 */
	@RequestMapping(value = "/agencyNameQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String agencyNameQuery(){
		List<Map<String, String>> agencyMapList = productOrderService.comNameQuery();
		String agencyMapJson = JsonUtils.objectToJsonStr(agencyMapList);
		return agencyMapJson;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/isHadBuyWYBProduct", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo isHadBuyWYBProduct(String param){
		ResultInfo resultInfo = new ResultInfo();
		try {
			Map paramMap = JsonUtils.jsonStrToMap(param);
			//开始导出
			resultInfo = productOrderService.isHadBuyWYBProduct(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("判断客户是否购买过物业宝产品出现异常！");
		}
		return resultInfo;
	}
	
	/**
	 * 产品队列预约查询页面获取值
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/getQueueProductAndComInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getQueueProductAndComInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = productOrderService.getQueueProductAndComInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 队列产品预约额度分配
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/submitQueueDistribute", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo submitQueueDistribute(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try{
			String pdAmountOrderQueueInfoId = JsonUtils.getJsonValueByKey("pdAmountOrderQueueInfoId", param);
			String amount = JsonUtils.getJsonValueByKey("amount", param);
			String foundDate = JsonUtils.getJsonValueByKey("foundDate", param);
		    resultInfo = productOrderService.submitQueueDistribute(pdAmountOrderQueueInfoId,amount,foundDate,loginInfo);
		} catch(CisCoreException cisCoreException){
			cisCoreException.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(cisCoreException.getMessage());
		} catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该产品预约分配失败："+e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	

	/**
	 * 获取热门产品管理  
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hotListProductUrl", method = RequestMethod.GET)
	public String hotProductOrderUrl(Model model) {
		return "fms/product/hotProductOrder";
	}
	
	/**
	 * 热门产品信息管理
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/queryHotPdOrderInfoList", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryHotPdOrderInfoList(DataGridModel dgm,String queryParam,ModelMap model) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			if(queryParam!=null&&!"".equals(queryParam)){
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
			}
			dataGrid = productOrderService.queryHotPdOrderInfoList(dgm,paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 取消热门产状态
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelHotProductInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo cancelHotProductInfo(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try{
			String productId = JsonUtils.getJsonValueByKey("productId", param);
		    resultInfo = productOrderService.cancelHotProductInfo(productId, loginInfo);
		}catch(CisCoreException cisCoreException){
			cisCoreException.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(cisCoreException.getMessage());
		}catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("修改热门产品状态失败："+e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 产品预约查询页面根据客户姓名获取客户信息
	 * @author ZYM
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/getCustomerInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo getCustomerInfo(String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = productOrderService.getCustomerInfo(paramMap,loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}

	
	
	/** 
	 * 更新产品预约信息 
	 * cjj
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateProductRemainAmount", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateProductRemainAmount(@RequestParam("param") String param,ModelMap model) {
		ResultInfo resultInfo = new ResultInfo();
		logger.info("=============更新产品预约请求数据=============="+param);
		Map paramMap = new HashMap();
		try {
			LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
			paramMap = JsonUtils.jsonStrToMap(param);
			resultInfo = productOrderService.updateProductRemainAmount(paramMap,loginInfo);
		}catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;		
	}

	
	/**
	 * 		导出产品预约查询信息
	 * @author wanghao
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/productOrderQueryDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
	public ModelAndView productOrderQueryInfo(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		// 输出日志，便于调试
		logger.info("Controller层类中productOrderQueryDetail()接受参数queryParam:" + queryParam);
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		// 获取业务报表导出Excel模板
		String path = System.getProperty("fms.webroot");
		path = path+"/WEB-INF/classes/reportTemplate/productOrderQueryDetail.xls";
		// 分配ResultInfo对象返回程序异常信息
		ResultInfo resultInfo = new ResultInfo();
		// 
		try {
			// 查询参数解码
			queryParam = StringUtils.encodeStr(queryParam);
			// 查询参数转成Map集合
			Map paramMap = JsonUtils.jsonStrToMap(queryParam);
			// 调用Service层方法统计产品预约信息
			Map  datas = this.productOrderService.productOrderQueryInfo(paramMap,loginInfo);
			// 调用Excel模板生成Excel业务报表
			OutputStream out=null;
	        BufferedOutputStream bos=null;
	        BufferedInputStream bis=null;
	        InputStream in=null;
	        try{
		        in=ExcelTool.exportExcel(path, datas);
		        bis=new BufferedInputStream(in);
		        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
		        String fileName = "productOrderQueryDetail"+now+".xls";
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
			resultInfo.setMsg("导出产品预约查询报表出现异常！");
		}
		return null;
	}
	
	
	/**
	 * 获取浮动份额上传页面url
	 */
	@RequestMapping(value = "/importPDAmountOrderUrl", method = RequestMethod.GET)
	public String importPDAmountOrder(Model model) {
		return "fms/product/importPDAmountOrder";
	}
	

	@RequestMapping(value = "/importPDAmountOrderAuditFile", method = RequestMethod.POST)
	@ResponseBody
	public void importPDAmountOrderAuditFile(HttpServletResponse response,
			@RequestParam MultipartFile[] pdAmountOrderAuditFile,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			resultInfo = productOrderService.importPDAmountOrderAuditFile(pdAmountOrderAuditFile, loginInfo);
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
			writer.write(JsonUtils.objectToJsonStr(resultInfo));
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer;
			try {
				writer = response.getWriter();
				System.out.println("JsonUtils.objectToJsonStr(resultInfo)：==="+JsonUtils.objectToJsonStr(resultInfo));
				writer.write(JsonUtils.objectToJsonStr(resultInfo));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//return resultInfo;
	}
	
	/**
	 * 定金打款审核
	 * @param param
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/earnestAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo earnestAudit(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try{
			List<PDAmountOrderInfo> productAmountOrderList = JsonUtils.jsonArrStrToList(param, PDAmountOrderInfo.class);
			for(PDAmountOrderInfo pdAmountOrderInfo:productAmountOrderList){
				resultInfo = productOrderService.submitEarnestAudit(pdAmountOrderInfo, loginInfo);
			}
		}catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("该预约产品提交定金审核失败："+e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 新增热门产状态
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addHotProductInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo addHotProductInfo(String param, ModelMap model) {
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
		ResultInfo resultInfo = new ResultInfo();
		try{
			String productId = JsonUtils.getJsonValueByKey("productId", param);
		    resultInfo = productOrderService.addHotProductInfo(productId, loginInfo);
		}catch(CisCoreException cisCoreException){
			cisCoreException.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(cisCoreException.getMessage());
		}catch(Exception e){
			resultInfo.setSuccess(false);
			resultInfo.setMsg("修改热门产品状态失败："+e.getMessage());
			e.printStackTrace();
		}
		return resultInfo;
	}
	
	/**
	 * 给下拉框的
	 * @return
	 */
	@RequestMapping("/getProductAllForSelect")
	@ResponseBody
	public List<PDProduct> getProductAllForSelect(){
		return productOrderService.getProductAllForSelect();
	}
}
