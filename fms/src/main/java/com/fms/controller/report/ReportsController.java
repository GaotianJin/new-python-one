package com.fms.controller.report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fms.controller.product.ProductController;
import com.fms.service.report.ReportsService;
import com.sinosoft.core.db.mapper.DefUserRoleRelaMapper;
import com.sinosoft.core.db.model.DefUserRoleRela;
import com.sinosoft.core.db.model.DefUserRoleRelaExample;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.core.spring.ReportCreateExcel;
import com.sinosoft.util.BeanUtils;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.ExcelTool;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/reports")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class ReportsController {
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ReportsService reportsService;
	@Autowired
	private DefUserRoleRelaMapper defUserRoleRelaMapper;
	
	/**
	 * 获取业务部报表初始页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listBusinessManageReportUrl", method = RequestMethod.GET)
	public String listBusinessMangeReportUrl(Model model) {
		return "fms/report/listBusinessManagementReport";
	}
	/**
	 * 获取赎回信息报表初始页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listRedemptionDetailUrl", method = RequestMethod.GET)
	public String listRedemptionDetailUrl(Model model) {
		return "fms/report/listRedemptionDetail";
	}
	/**
	 * 获取募集期产品报表初始页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listRaisingProductsUrl", method = RequestMethod.GET)
	public String listRaisingProductstUrl(Model model) {
		return "fms/report/listRaisingProducts";
	}
	/**
	 * 获取销售清单报表初始页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listSaleListUrl", method = RequestMethod.GET)
	public String listSaleListUrl(Model model) {
		return "fms/report/listSaleList";
	}
	
	/**
	 * 获取产品查询初始页面 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listProductQueryUrl", method = RequestMethod.GET)
	public String listProductQueryUrl(Model model) {
		return "fms/report/listProductQuery";
	}
	
	/**
	 * 固定产品详细查询页面
	 * @param param
	 * @return
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/fixedDetailUrl", method = RequestMethod.GET)
	public ModelAndView fixedDetailUrl(@RequestParam("param") String param,ModelMap model) {
    	//log.info("==请求数据===" + param);
		LoginInfo loginInfo = (LoginInfo)model.get(Constants.USER_INFO_SESSION);
	    Long userId = loginInfo.getUserId();
	    DefUserRoleRelaExample defUserRoleRelaExample = new DefUserRoleRelaExample();
	    defUserRoleRelaExample.createCriteria().andUserIdEqualTo(userId).andRcStateEqualTo("E");
	    List<DefUserRoleRela> defUserRoleRelas = defUserRoleRelaMapper.selectByExample(defUserRoleRelaExample);
	    Map<String, String> reqParamMap = new HashMap<String, String>();
		reqParamMap = (Map<String, String>)JsonUtils.jsonStrToMap(param);
		if(defUserRoleRelas.size()>1){
			for(int i=0;i<defUserRoleRelas.size();++i){
				DefUserRoleRela defUserRoleRela = defUserRoleRelas.get(i);
				String roleId = defUserRoleRela.getRoleId().toString();
				if("16".equals(roleId)||"1".equals(roleId)){
					reqParamMap.put("roleId", roleId);
					return new ModelAndView("fms/report/fixedDetail",reqParamMap);
				}
			}
			return new ModelAndView("fms/report/fixedDetail",reqParamMap);
		}else{
			DefUserRoleRela defUserRoleRela = defUserRoleRelas.get(0);
			String roleId = defUserRoleRela.getRoleId().toString();
			reqParamMap.put("roleId", roleId);
			return new ModelAndView("fms/report/fixedDetail",reqParamMap);
		}
	}
	
	/**
	 * 获取产品基本信息修改信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUpdateProductBasicInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getUpdateProductBasicInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品基本信息出错");
		return resultInfo;
		}
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		String productId = JsonUtils.getJsonValueByKey("productId", param);
		map.put("productId", productId);
		resultInfo = reportsService.queryProductBasicInfo(map, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品基本信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 获取产品基本信息修改信息
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getProductCoreInfo",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo getProductCoreInfo(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		Map map = new HashMap();
		try {
		if(param==null||"".equals(param)){
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品核心信息出错");
		return resultInfo;
		}
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		String productId = JsonUtils.getJsonValueByKey("productId", param);
		map.put("productId", productId);
		resultInfo = reportsService.getProductCoreInfo(map, loginInfo);
		} catch (Exception e) {
		e.printStackTrace();
		resultInfo.setSuccess(false);
		resultInfo.setMsg("获取产品核心信息出错");
		}
		return resultInfo;
	}
	
	/**
	 * 静态方法：解决Controller层接收url中文参数时出现的乱码问题
	 * @author Administrator
	 */
	public static String EncodedString(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/monthQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String monthQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> monthMapList = reportsService.monthQuery(loginInfo);
		String monthMapJson = JsonUtils.objectToJsonStr(monthMapList);
		return monthMapJson;
	}
	/**
	 * @return
	 */
	@RequestMapping(value = "/yearQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String yearQuery(ModelMap modelMap){
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		List<Map<String, String>> monthMapList = reportsService.yearQuery(loginInfo);
		String monthMapJson = JsonUtils.objectToJsonStr(monthMapList);
		return monthMapJson;
	}
	/**
	 * 查询业务部报表信息
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryBusinessManageList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryBusinessManageList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			logger.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = reportsService.queryBusinessManageList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = reportsService.queryBusinessManageList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询产品信息显示初始信息列表
	 * @param dgm
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryProductListUrl", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid queryProductListUrl(DataGridModel dgm, String param) {
		Map paramMap = new HashMap();
		DataGrid dataGrid = new DataGrid();
		try {
		if(param!=null&&!"".equals(param)){
		param = JsonUtils.decodeUrlParams(param, "utf-8");
		paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);}
		dataGrid = reportsService.queryProductList(dgm, paramMap);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return dataGrid;
	}

	
	/**
	 * 查询赎回详情信息
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryRedemptionDetailList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryRedemptionDetailList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			logger.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = reportsService.queryRedemptionDetailList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = reportsService.queryRedemptionDetailList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 查询募集期产品信息
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryRaisingProductsList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid queryRaisingProductsList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			logger.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = reportsService.queryRaisingProductsList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = reportsService.queryRaisingProductsList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 查询销售清单信息
	 * @param dgm
	 * @param queryParam
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/querySaleProductsList",produces = "application/json; charset=utf-8")
	@ResponseBody
	public DataGrid querySaleProductsList(DataGridModel dgm,String queryParam,ModelMap modelMap){
		DataGrid dataGrid = new DataGrid();
		try {
			logger.info("==请求数据===" + queryParam);
			Map paramMap = new HashMap();
			//获取用户登录信息			
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			if(queryParam==null||"".equals(queryParam)){
				dataGrid = reportsService.querySaleProductsList(dgm, paramMap, loginInfo);
			}else{
				queryParam = JsonUtils.decodeUrlParams(queryParam, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(queryParam, paramMap);
				dataGrid = reportsService.querySaleProductsList(dgm, paramMap, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	

/**
 * 		导出业管部报表信息
 * */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value="/businessManagementDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
public ModelAndView businessManagementDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
	// 输出日志，便于调试
	logger.info("Controller层类中businessManagementDetail()接受参数queryParam:" + queryParam);
	// 获取业务报表导出Excel模板
	String path = System.getProperty("fms.webroot");
	path = path+"/WEB-INF/classes/reportTemplate/businessManagementDetail.xls";
	// 分配ResultInfo对象返回程序异常信息
	ResultInfo resultInfo = new ResultInfo();
	// 
	try {
		// 查询参数解码
		queryParam = StringUtils.encodeStr(queryParam);
		// 查询参数转成Map集合
		Map paramMap = JsonUtils.jsonStrToMap(queryParam);
		// 调用Service层方法统计产品预约信息
		Map  datas = this.reportsService.businessManagementDetail(paramMap);
		// 调用Excel模板生成Excel业务报表
		OutputStream out=null;
        BufferedOutputStream bos=null;
        BufferedInputStream bis=null;
        InputStream in=null;
        try{
	        in=ExcelTool.exportExcel(path, datas);
	        bis=new BufferedInputStream(in);
	        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
	        String fileName = "businessManagementDetail"+now+".xls";
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
		resultInfo.setMsg("导出业管部报表出现异常！");
	}
	return null;
}

/**
 * 		导出业管部报表信息
 * */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value="/redemptionDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
public ModelAndView redemptionDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
	// 输出日志，便于调试
	logger.info("Controller层类中redemptionDetail()接受参数queryParam:" + queryParam);
	// 获取业务报表导出Excel模板
	String path = System.getProperty("fms.webroot");
	path = path+"/WEB-INF/classes/reportTemplate/redemptionDetail.xls";
	// 分配ResultInfo对象返回程序异常信息
	ResultInfo resultInfo = new ResultInfo();
	// 
	try {
		// 查询参数解码
		queryParam = StringUtils.encodeStr(queryParam);
		// 查询参数转成Map集合
		Map paramMap = JsonUtils.jsonStrToMap(queryParam);
		// 调用Service层方法统计产品预约信息
		Map  datas = this.reportsService.redemptionDetail(paramMap);
		// 调用Excel模板生成Excel业务报表
		OutputStream out=null;
        BufferedOutputStream bos=null;
        BufferedInputStream bis=null;
        InputStream in=null;
        try{
	        in=ExcelTool.exportExcel(path, datas);
	        bis=new BufferedInputStream(in);
	        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
	        String fileName = "redemptionDetail"+now+".xls";
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
		resultInfo.setMsg("导出赎回详情报表出现异常！");
	}
	return null;
}
/**
 * 		导出募集期产品报表信息
 * */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value="/raisingProductsDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
public ModelAndView raisingProductsDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
	// 输出日志，便于调试
	logger.info("Controller层类中raisingProductsDetail()接受参数queryParam:" + queryParam);
	// 获取业务报表导出Excel模板
	String path = System.getProperty("fms.webroot");
	path = path+"/WEB-INF/classes/reportTemplate/raisingProductsDetail.xls";
	// 分配ResultInfo对象返回程序异常信息
	ResultInfo resultInfo = new ResultInfo();
	// 
	try {
		// 查询参数解码
		queryParam = StringUtils.encodeStr(queryParam);
		// 查询参数转成Map集合
		Map paramMap = JsonUtils.jsonStrToMap(queryParam);
		// 调用Service层方法统计产品预约信息
		Map  datas = this.reportsService.raisingProductsDetail(paramMap);
		// 调用Excel模板生成Excel业务报表
		OutputStream out=null;
        BufferedOutputStream bos=null;
        BufferedInputStream bis=null;
        InputStream in=null;
        try{
	        in=ExcelTool.exportExcel(path, datas);
	        bis=new BufferedInputStream(in);
	        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
	        String fileName = "raisingProductsDetail"+now+".xls";
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
		resultInfo.setMsg("导出募集期产品报表出现异常！");
	}
	return null;
}


/**
 * 		该方法接受导出业务报表请求
 * */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping(value="/saleProductsDetail.xls", method=RequestMethod.GET, produces="application/json;charset=utf-8")
public ModelAndView saleProductsDetail(@RequestParam String queryParam, HttpServletRequest request, HttpServletResponse response) {
	// 输出日志，便于调试
	logger.info("Controller层类中saleProductsDetail()接受参数queryParam:" + queryParam);
	// 获取业务报表导出Excel模板
	String path = System.getProperty("fms.webroot");
	path = path+"/WEB-INF/classes/reportTemplate/saleProductsDetail.xls";
	// 分配ResultInfo对象返回程序异常信息
	ResultInfo resultInfo = new ResultInfo();
	// 
	try {
		// 查询参数解码
		queryParam = StringUtils.encodeStr(queryParam);
		// 查询参数转成Map集合
		Map paramMap = JsonUtils.jsonStrToMap(queryParam);
		// 调用Service层方法统计产品预约信息
		Map  datas = this.reportsService.saleProductsDetail(paramMap);
		// 调用Excel模板生成Excel业务报表
		OutputStream out=null;
        BufferedOutputStream bos=null;
        BufferedInputStream bis=null;
        InputStream in=null;
        try{
	        in=ExcelTool.exportExcel(path, datas);
	        bis=new BufferedInputStream(in);
	        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date())); 
	        String fileName = "saleProductsDetail"+now+".xls";
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
		resultInfo.setMsg("导出销售清单报表出现异常！");
	}
	return null;
}
}