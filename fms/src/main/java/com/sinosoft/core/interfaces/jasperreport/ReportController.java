/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sinosoft.core.interfaces.jasperreport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Simple <code>Controller</code> implementation returning
 * data for report rendering. This implementation demonstrates
 * how JasperReports view can be integrated into your application
 * without placing a JasperReports dependency on your application code.
 * All data returned in the <code>ModelAndView</code> instances uses
 * standard Java classes.
 *
 * @author Rob Harrop
 */
@Controller
public class ReportController {

	@RequestMapping(value = "/jasperreport/list", method = RequestMethod.GET)
	public String list() {
		return "jasperreport/reportList";
	}

	@RequestMapping(value = "simpleReport.pdf", method = RequestMethod.GET)
	public ModelAndView handleSimpleReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("simpleReport", getModel());
	}

	@RequestMapping(value = "simpleReport.pdf", method = RequestMethod.POST)
	public ModelAndView handleSimpleReportPost(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String reportTitle = request.getParameter("reportTitle");
		Map model = getModel();
		model.put("ReportTitle", reportTitle);

		return new ModelAndView("simpleReportCompile", model);
	}

	// 自动根据后缀判断打印类型，比如*.pdf，自动对应到pdf打印
	@RequestMapping(value = "simpleReportMulti.pdf", method = RequestMethod.GET)
	public ModelAndView handleSimpleReportMulti(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String uri = request.getRequestURI();
		String format = uri.substring(uri.lastIndexOf(".") + 1);

		Map model = getModel();
		model.put("format", format);

		return new ModelAndView("simpleReportMulti", model);
	}

	@RequestMapping(value = "simpleReportHtml.html", method = RequestMethod.GET)
	public ModelAndView handleSimpleReportHtml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("simpleReportHtml", getModel());
	}

	@RequestMapping(value = "simpleReportCsv.csv", method = RequestMethod.GET)
	public ModelAndView handleSimpleReportCsv(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("simpleReportCsv", getModel());
	}

	@RequestMapping(value = "simpleReportExcel.xls", method = RequestMethod.GET)
	public ModelAndView handleSimpleReportExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("simpleReportExcel", getModel());
	}

	@RequestMapping(value = "simpleReportCompile.report", method = RequestMethod.GET)
	public ModelAndView handleSimpleReportCompile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("simpleReportCompile", getModel());
	}

	public ModelAndView handleExporterParameters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("htmlReport", getModel());
	}

	/**
	 * Here two instances of <code>List</code> are added to the
	 * model. The second instance, <code>SubReportData</code> is
	 * configured to be passed into the engine as a parameter
	 * which is subsequently passed into a sub-report by the
	 * master report file. Behind the scenes this <code>List</code> is
	 * transformed into an instance of <code>JRDataSource</code>.
	 */
	public ModelAndView handleSubReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = getModel();
		model.put("SubReportData", getProductData());
		return new ModelAndView("subReport", model);
	}

	private Map getModel() {
		Map model = new HashMap();
		model.put("ReportTitle", "Dear Lord!");
		model.put("dataSource", getData());


		return model;
	}

	private List getData() {
		List list = new ArrayList();

		for (int x = 0; x < 10; x++) {
			ReportDataBean bean = new ReportDataBean();
			bean.setId(x);
			bean.setName("Rob Harrop");
			bean.setStreet("My Street");

			if (x % 2 == 0) {
				bean.setCity("Manchester");
			}
			else {
				bean.setCity("London");
			}

			list.add(bean);
		}

		return list;
	}

	private List getProductData() {
		List list = new ArrayList();
		for (int x = 0; x < 10; x++) {
			SubReportDataBean bean = new SubReportDataBean();
			bean.setId(x);
			bean.setName("Foo Bar");
			bean.setPrice(1.9f);
			bean.setQuantity(1.0f);

			list.add(bean);
		}
		return list;
	}
}