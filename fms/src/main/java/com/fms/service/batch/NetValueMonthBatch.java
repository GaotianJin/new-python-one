package com.fms.service.batch;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.fms.service.product.ProductService;

@SuppressWarnings("serial")
@Service("NetValueMonthBatch")
public class NetValueMonthBatch implements Serializable, ApplicationContextAware {
   
	private ProductService productService;
	private static ApplicationContext context = null;
	private static final Logger logger = Logger.getLogger(NetValueMonthBatch.class);
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	// 运行批处理
	public void runNetValueMonthBatch() {
		System.out.println("--------执行净值待处理生成月批处理");
		//获取系统当前时间
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date()); 
		//自动生成开放日代办任务
		productService = (ProductService) context.getBean("ProductService");
		try {
			productService.openDateBatch();
			logger.info("自动生成开放日代办任务处理执行成功!");
		} catch (Exception e) {
			logger.info("自动生成开放日代办任务批处理执行失败："+e.getMessage());
		}
		//得到每月2号
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if(day==2){
			try {
				productService.netValueMonthBatch();
				logger.info("净值待处理月批处理执行成功!");
			} catch (Exception e) {
				logger.info("净值待处理月批处理执行失败："+e.getMessage());
			}
			
		}else{
			return;
		}
		
	}

}
