package com.fms.controller.integratedQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sinosoft.core.interfaces.util.Constants;

/**
 * 综合查询模块
 * 
 */
@Controller
@RequestMapping("/integratedQuery")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class IntegratedQueryContoller {

	/**
	 * 收益分配综合查询
	 */
	@RequestMapping(value = "/incomeDistQueryListUrl", method = RequestMethod.GET)
	public String ListIncomeDistQuery(Model model) {
		return "fms/integrated/incomeDistQueryList";
	}

}
