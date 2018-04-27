package com.fms.service.batch;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.fms.service.product.IncomeDisService;
import com.sinosoft.util.ResultInfo;

@SuppressWarnings("serial")
@Service("IncomeDisEmailBatch")
public class IncomeDisEmailBatch implements Serializable, ApplicationContextAware {

	private IncomeDisService incomeDisService;
	private static ApplicationContext context = null;
	private static final Logger logger = Logger.getLogger(IncomeDisEmailBatch.class);
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	// 运行批处理
	public void runIncomeDisEmailBatch() {
		System.out.println("--------执行短信生成批处理");
		incomeDisService = (IncomeDisService) context.getBean("IncomeDisService");
		ResultInfo resultInfo = incomeDisService.incomeDisEmailBatch();
		if (resultInfo.isSuccess()) {
			logger.info("收益分配批处理执行成功!");
		} else {
			logger.info("收益分配批处理执行失败：" + resultInfo.getMsg());

		}
	}

}
