package com.eighth.test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eighth.entity.Askers;
import com.eighth.service.SetWeightService;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		SetWeightService ser=(SetWeightService) context.getBean("setweight");
		int num=0;
		List<Askers> list=ser.getAllAsker();
		List<Askers> list1=new ArrayList<Askers>();
		for (Askers askers : list) {
			for (int i = 0; i < Integer.parseInt(askers.getWeight()); i++) {
				Askers as=new Askers();
				as.setAskerId(askers.getAskerId());
				as.setWeight(askers.getWeight());
				list1.add(as);
			}
			num+=Integer.parseInt(askers.getWeight());
		}
		int sj=(int) (Math.random()*num);
		System.out.println(list1.get(sj).getAskerId());*/
		
		
	}
}
