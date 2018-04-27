package com.fms.controller.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.fms.db.model.Agent;
import com.fms.db.model.PDAmountOrderInfo;
import com.fms.db.model.SysSmsBatch;
import com.fms.db.model.SysSmsInfo;
import com.fms.service.sms.SmsService;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.interfaces.util.DataGridModel;
import com.sinosoft.util.DataGrid;
import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.LoginInfo;
import com.sinosoft.util.ResultInfo;

@Controller
@RequestMapping("/sms")
// 将Model中属性名为Constants.USER_INFO_SESSION的属性放到Session属性列表中，以便这个属性可以跨请求访问
@SessionAttributes(Constants.USER_INFO_SESSION)
public class SmsController {
	@Autowired
	private SmsService smsService;
	
	private static final Logger log = Logger.getLogger(SmsController.class);
	
	/** 获取短信审核初始页面 **/
	@RequestMapping(value = "/listMessageAuditUrl", method = RequestMethod.GET)
	public String listRemainAmountDistributeUrl(Model model) {
		return "fms/sms/listMessageAudit";
	}
	
	@RequestMapping(value = "/sendSmsDetail", method = RequestMethod.GET)
    public ModelAndView sendSms(Model model,@RequestParam("param") String param) {
    	Map<String, String> reqParamMap = new HashMap<String, String>();
    	reqParamMap = JsonUtils.jsonStrToMap(param);
        return new ModelAndView("fms/sms/sendSms",reqParamMap);
    }
	/**
	 * 获取短信类别
	 * @author ZYM
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getSmsAuditInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getSmsAuditInfo(DataGridModel dgm,String param) {
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = new HashMap();
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = smsService.getSmsAuditInfo(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 获取客户短信类别
	 * @author chengong
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getCustSmsAuditInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getCustSmsAuditInfo(DataGridModel dgm,String param) {
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = new HashMap();
			if(param!=null&&!"".equals(param)){
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				paramMap = (Map)JsonUtils.jsonStrToObject(param, paramMap);
			}
			dataGrid = smsService.getCustSmsAuditInfo(dgm, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 获取发送短信信息
	 * @author SLY
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getSmsDetailInfo", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid getSmsDetailInfo(DataGridModel dgm,@RequestParam("param")String param) {
		DataGrid dataGrid = new DataGrid();
		try {
			Map paramMap = new HashMap();
			if (param==null||"".equals(param)) {
				log.info("==获取短信详细信息时，短信批次号为空，获取短信详细信息失败===");
			}else {
				param = JsonUtils.decodeUrlParams(param, "utf-8");
				String queryCondition = JsonUtils.getJsonValueByKey("queryCondition", param);
				String sysSmsBatchId = JsonUtils.getJsonValueByKey("sysSmsBatchId", param);
				String smsType = JsonUtils.getJsonValueByKey("smsType", param);
				if(queryCondition==null||"".equals(queryCondition)){
					paramMap.put("sysSmsBatchId", sysSmsBatchId);
					paramMap.put("smsType", smsType);
				}else {
					paramMap = (Map)JsonUtils.jsonStrToObject(queryCondition, paramMap);
					paramMap.put("sysSmsBatchId", sysSmsBatchId);
					paramMap.put("smsType", smsType);
				}
			}
			dataGrid = smsService.getSmsDetailInfo(dgm,paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	/**
	 * 发送短信
	 * @param sendSmsListData
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo sendSms(String sendSmsListData,ModelMap modelMap) {
		ResultInfo resultInfo = new ResultInfo();
		try {
			//解码 由于%的缘故
			sendSmsListData = JsonUtils.decodeUrlParams(sendSmsListData,"utf-8");
			LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
			List<SysSmsInfo> sendSmsList = JsonUtils.jsonArrStrToList(sendSmsListData, SysSmsInfo.class);
			resultInfo = smsService.sendSms(sendSmsList, loginInfo);
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg(e.getMessage());
		}
		return resultInfo;
	}
	
	/**
	 * 删除短信
	 * @author SLY
	 * @param param
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/deleteSms",produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResultInfo deleteSms(String param,ModelMap modelMap){
		ResultInfo resultInfo = new ResultInfo();
		//获取用户登录信息			
		LoginInfo loginInfo = (LoginInfo) modelMap.get(Constants.USER_INFO_SESSION);
		try {
			log.info("==请求数据===" + param);
			if(param==null||"".equals(param)){
				return resultInfo;
			}
			List<SysSmsBatch> sysSmsBatchList = JsonUtils.jsonArrStrToList(param, SysSmsBatch.class);
			for(SysSmsBatch sysSmsBatch:sysSmsBatchList){
				String sysSmsBatchId = sysSmsBatch.getSysSmsBatchId().toString();
				resultInfo = smsService.deleteSms(sysSmsBatchId, loginInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setMsg("删除短信信息出错");
		}
		return resultInfo;
	}
	/**
	 * 获取客户短信审核初始页面
	 * author 陈功
	 *createTime 2017/3/14
	 **/
	@RequestMapping(value="/listCustMessageAuditUrl",method=RequestMethod.GET)
	public String listCustMessageAudit(Model model){
		return "fms/sms/listCustMessageAudit"; 
	}
}
