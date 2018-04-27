package com.sinosoft.core.interfaces.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSONArray;
import com.sinosoft.core.db.model.BDCompany;
//import com.icbcaxa.common.application.CompanyService;
//import com.icbcaxa.common.domain.model.BdCompany;
import com.sinosoft.core.interfaces.util.Constants;
import com.sinosoft.core.service.BDCompanyService;
import com.sinosoft.util.LoginInfo;

@Controller
@RequestMapping("/company")
@SessionAttributes(Constants.USER_INFO_SESSION)
public class CompanyController {
	@Autowired
	private BDCompanyService companyService;

	@RequestMapping(value = "/queryCompanyList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAdapterComboxList(ModelMap model) {
		JSONArray jsonArray;
		List reslist = new ArrayList();
		try {
			LoginInfo loginInfo = (LoginInfo) model
					.get(Constants.USER_INFO_SESSION);
			List<BDCompany> listBdCompany;
			if (loginInfo != null) {
				listBdCompany = companyService.getCompanyChildrenID(loginInfo.getComId());
			} else {
				listBdCompany = companyService.getAllCompany();
			}

			if (listBdCompany != null && listBdCompany.size() > 0) {
				for (int i = 0; i < listBdCompany.size(); i++) {
					BDCompany bdCompany = listBdCompany.get(i);
					Map map = new HashMap();
					map.put("id", bdCompany.getDbid());
					map.put("name", bdCompany.getCompanyCode() + "-"
							+ bdCompany.getCompanyName());
					reslist.add(map);
				}
			}
			jsonArray = new JSONArray();
			jsonArray.addAll(reslist);
			System.out.println(jsonArray.toJSONString());
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/queryCompanyListForLogin", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryCompanyListForLogin() {
		JSONArray jsonArray;
		List reslist = new ArrayList();
		try {
			List<BDCompany> listBdCompany = companyService.getAllCompany();
			if (listBdCompany != null && listBdCompany.size() > 0) {
				for (int i = 0; i < listBdCompany.size(); i++) {
					BDCompany bdCompany = listBdCompany.get(i);
					Map map = new HashMap();
					map.put("id", bdCompany.getDbid());
					map.put("name", bdCompany.getCompanyCode() + "-"
							+ bdCompany.getCompanyName());
					reslist.add(map);
				}
			}
			jsonArray = new JSONArray();
			jsonArray.addAll(reslist);
			System.out.println(jsonArray.toJSONString());
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/queryCompanyList2", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryAdapter2ComboxList(ModelMap model) {
		JSONArray jsonArray;
		List reslist = new ArrayList();
		try {
			LoginInfo loginInfo = (LoginInfo) model
					.get(Constants.USER_INFO_SESSION);
			List<BDCompany> listBdCompany;
			if (loginInfo != null) {
				listBdCompany = companyService.getCompanyChildrenID(loginInfo.getComId());
			} else {
				listBdCompany = companyService.getAllCompany();
			}

			if (listBdCompany != null && listBdCompany.size() > 0) {
				for (int i = 0; i < listBdCompany.size(); i++) {
					BDCompany bdCompany = listBdCompany.get(i);
					Map map = new HashMap();
					map.put("id", bdCompany.getCompanyCode());
					map.put("name", bdCompany.getCompanyCode() + "-"
							+ bdCompany.getCompanyName());
					reslist.add(map);
				}
			}
			jsonArray = new JSONArray();
			jsonArray.addAll(reslist);
			System.out.println(jsonArray.toJSONString());
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
