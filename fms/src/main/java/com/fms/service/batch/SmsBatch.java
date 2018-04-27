package com.fms.service.batch;


import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.fms.service.sms.SmsService;
import com.sinosoft.util.ResultInfo;

@SuppressWarnings("serial")
@Service("SmsBatch")
public class SmsBatch implements Serializable, ApplicationContextAware{

	private SmsService smsService;
	private static ApplicationContext context = null;
	private static final Logger logger = Logger.getLogger(SmsBatch.class);
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SmsBatch.context = context;
	}

	// 当日生日客户短信测试批处理
	public void runBirthToCustSmsBatch() {
		System.out.println("--------执行短信生成批处理");
		smsService = (SmsService) context.getBean("SmsService");
		ResultInfo resultInfo = smsService.createBirthToCustSms();
		if (resultInfo.isSuccess()) {
			logger.info("短信批处理执行成功!");
		} else {
			logger.info("短信批处理执行失败：" + resultInfo.getMsg());

		}

	}
	
	// 明日生日客户所属理财经理生日提醒短信测试批处理
		public void runBirthToAgentSmsBatch() {
			System.out.println("--------执行短信生成批处理");
			smsService = (SmsService) context.getBean("SmsService");
			ResultInfo resultInfo = smsService.createBirthToAgentSms();
			if (resultInfo.isSuccess()) {
				logger.info("短信批处理执行成功!");
			} else {
				logger.info("短信批处理执行失败：" + resultInfo.getMsg());

			}

		}
/*
		//春节祝福短信测试批处理
		public void runSpringFestivalSmsBatch() {
			System.out.println("--------执行短信生成批处理");
			smsService = (SmsService) context.getBean("SmsService");
			ResultInfo resultInfo = smsService.createSpringFestival();
			if (resultInfo.isSuccess()) {
				logger.info("短信批处理执行成功!");
			} else {
				logger.info("短信批处理执行失败：" + resultInfo.getMsg());

			}

		}*/

}
