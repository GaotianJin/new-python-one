package com.fms.service.batch;


import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import com.fms.service.product.ProductService;
import com.sinosoft.util.ResultInfo;

@SuppressWarnings("serial")
@Service("NetValueWeekBatch")
public class NetValueWeekBatch implements Serializable, ApplicationContextAware{

	private ProductService productService;
	private static ApplicationContext context = null;
	private static final Logger logger = Logger.getLogger(NetValueWeekBatch.class);
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	// 每周周二进行净值披露批处理
	public void runNetValueWeekBatch() {
		logger.info("--------执行每周周二净值披露批处理");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date()); 
//		int week = cal.get(Calendar.DAY_OF_WEEK)-1;
//		if(week == 2) {
			productService = (ProductService) context.getBean("ProductService");
			ResultInfo resultInfo;
			try {
				resultInfo = productService.createNetValueWeekBatch();
				if (resultInfo.isSuccess()) {
					logger.info("执行周二净值披露批处理成功!");
				} else {
					logger.info("执行周二净值披露批处理失败：" + resultInfo.getMsg());

				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
//		}
		
	}
	
	
}
