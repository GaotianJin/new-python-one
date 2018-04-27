package com.fms.service.batch;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.fms.service.trade.TradeStatusService;
import com.sinosoft.util.ResultInfo;

@SuppressWarnings("serial")
@Service("ProductFoundEmailBatch")
public class ProductFoundEmailBatch implements Serializable, ApplicationContextAware {

	private TradeStatusService tradeStatusService;
	private static ApplicationContext context = null;
	private static final Logger logger = Logger.getLogger(ProductFoundEmailBatch.class);
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	// 运行批处理
	public void runProductFoundEmailBatch() {
		System.out.println("--------执行短信生成批处理");
		tradeStatusService = (TradeStatusService) context.getBean("TradeStatusService");
		ResultInfo resultInfo = tradeStatusService.productFoundEmailBatch();
		if (resultInfo.isSuccess()) {
			logger.info("产品成立邮件发送批处理执行成功!");
		} else {
			logger.info("产品成立邮件发送批处理执行失败：" + resultInfo.getMsg());

		}
	}

}
