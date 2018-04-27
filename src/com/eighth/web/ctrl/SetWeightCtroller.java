package com.eighth.web.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.Askers;
import com.eighth.entity.Modules;
import com.eighth.service.SetWeightService;

@Controller
public class SetWeightCtroller {
	@Autowired
	private SetWeightService wservice;
	
	@RequestMapping("/showAllAsker")
	@ResponseBody
	public Object getAll(){
		List<Askers> list=wservice.getAllAsker();
		return list;
	}
	
	@RequestMapping("/updateAskers")
	@ResponseBody
	public  Object updateAsker(String askerId,String weight,String bakContent){
		Askers asker=new Askers();
		asker.setAskerId(askerId);
		asker.setWeight(weight);
		asker.setBakContent(bakContent);
		int row=wservice.updateAskers(asker);
		Map map=new HashMap();
		if(row==0){
			map.put("errorMsg", false);
		}else{
			map.put("errorMsg", true);
		}
		return map;
		
	}
}
