package com.fms.controller.custbaseinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fms.db.model.CustBaseInfo;
import com.fms.service.CustBaseInfo.CustBaseInfoService;

@Controller
@RequestMapping("/custBaseInfoCtrl")
public class CustBaseInfoController {
	
	@Autowired
	private CustBaseInfoService cbs;
	
	@RequestMapping("/getCustAllForSelect")
	@ResponseBody
	public List<CustBaseInfo> getCustAllForSelect(){
		return cbs.getCustAllForSelect();
		
	}
}
