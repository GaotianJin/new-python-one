package com.fms.controller.referee;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fms.db.model.Referee;
import com.fms.service.referee.RefereeService;
import com.sinosoft.util.StringUtils;

@Controller
@RequestMapping("/refereeCtrl")
public class RefereeController {
	
	@Autowired
	private RefereeService rs;
	
	@RequestMapping(value = "/refereeUrl", method = RequestMethod.GET)
	public String referee(Model model) {
		return "fms/referee/refereeUser";
	}
	
	@RequestMapping("/getRefereeAll")
	@ResponseBody
	public Object getRefereeAll(Referee r) {
		System.out.println("r.getRefereeName="+r.getRefereeName());
		Referee re=new Referee(StringUtils.isNullByStr(r.getRefereeName()), r.getRefereeCode(), r.getState(), r.getPage(), r.getRows());
		Map map=rs.getDatagrid(re);
		return map;
	}
	
	@RequestMapping("/updateRefereeState")
	@ResponseBody
	public Object updateState(Referee r) {
		return rs.updateStateByRefereeId(r);
	}
	
	@RequestMapping("/getRefereeStateforList")
	@ResponseBody
	public Object getRefereeStateforList() {
		return rs.getRefereeStateforList();
	}
}
