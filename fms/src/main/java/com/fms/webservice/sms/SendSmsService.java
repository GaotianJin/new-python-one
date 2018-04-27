package com.fms.webservice.sms;

import java.util.Map;

import org.apache.log4j.Logger;

import com.sinosoft.util.JsonUtils;
import com.sinosoft.util.ResultInfo;

public class SendSmsService {

	private static final Logger logger = Logger
			.getLogger(SendSmsService.class);
	
	@SuppressWarnings("rawtypes")
	public ResultInfo sendSms(String serviceUrl,String serviceFuncName,Map paramMap){
		ResultInfo resultInfo = new ResultInfo();
		try{
			//1.得到参数
			logger.info("发送短信参数：serviceUrl："+serviceUrl);
			logger.info("发送短信参数：account："+paramMap.get("userName").toString());
			logger.info("发送短信参数：password："+paramMap.get("passWord").toString());
			logger.info("发送短信参数：phone："+paramMap.get("sendMobile").toString());
			logger.info("发送短信参数：content："+paramMap.get("sendContent").toString());
			String account = paramMap.get("userName").toString();
			String password = paramMap.get("passWord").toString();
			String phone = paramMap.get("sendMobile").toString();
			String content = paramMap.get("sendContent").toString();
			String sign = "【巨鲸财富】";
			String subcode = "";
//			JSONHttpClient jsonHttpClient = new JSONHttpClient(serviceUrl);
//			jsonHttpClient.setRetryCount(1);
			//2.发送
			String sendhRes = "";//jsonHttpClient.sendSms(account, password, phone, content, sign, subcode);
			logger.info("提交单条普通短信响应：" + sendhRes);
			Map resultMap = JsonUtils.jsonStrToMap(sendhRes);
			String result =  (String)resultMap.get("result");
			if (!"0".equals(result)||result == null) {
				resultInfo.setSuccess(false);
				resultInfo.setObj(resultMap);
				resultInfo.setMsg(sendhRes);
			}else {
				if(resultMap.get("failPhones")==null||"".equals(resultMap.get("failPhones"))){
					resultInfo.setSuccess(true);
				}else {
					resultInfo.setSuccess(false);
					resultInfo.setObj(resultMap);
					resultInfo.setMsg(sendhRes);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setSuccess(false);
			resultInfo.setObj("0000");
			resultInfo.setMsg("短信发送出现异常");
		}
		return resultInfo;
	}
		
}
