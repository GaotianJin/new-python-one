package com.eighth.web.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.service.AutoOnOffService;

@Controller
public class AutoOnOffCtroller {
	
	
	@Autowired
	private AutoOnOffService ser;
	
	@RequestMapping("/getAll")
	@ResponseBody
	public Object getAll(){
		return ser.selectIsOpen();
	}
	
	@RequestMapping("/modOn")
	@ResponseBody
	public Object modOn(Boolean isOn){
		if(isOn){
			return ser.updateIsOpen(1);
		}
		return ser.updateIsOpen(0);
	}
}
