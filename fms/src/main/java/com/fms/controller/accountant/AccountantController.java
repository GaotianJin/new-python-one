package com.fms.controller.accountant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accountant")
public class AccountantController {
	
	@RequestMapping("/accountantUrl")
	public String ReturnAccountant() {
		return "fms/accountant/listAccountant";
	}
}
