package com.sinosoft.core.spring;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinosoft.core.interfaces.util.Constants;

public class SessionFilter extends HttpServlet implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI().toString();
		String contextPath = req.getContextPath();
		/*if (url != null && !"".equals(url) && url.indexOf("services")<0) {
			if (url.indexOf(".") == -1
					&& url.indexOf("company/queryCompanyListForLogin") == -1
					&& url.indexOf("welcome") == -1
					&& !url.equals(contextPath + "/")
					&& !url.equals(contextPath + "")) {
				HttpSession session = req.getSession();
				if (session.getAttribute(Constants.USER_INFO_SESSION) == null) {

					res.sendRedirect(contextPath + "/welcome/sessionReloadUrl");
					return;
				}
			}
			if (url.indexOf(".") == -1
					&& (url.equals(contextPath + "") || url.equals(contextPath
							+ "/"))) {
				res.sendRedirect(contextPath + "/index.jsp");
				return;
			}
		}
		filterChain.doFilter(request, response);*/
		
		if (url.endsWith("controller.jsp")) {
			filterChain.doFilter(request, response);
		}else{
			if (url != null && !"".equals(url) && url.indexOf("services")<0) {
				if (url.indexOf(".") == -1
						&& url.indexOf("company/queryCompanyListForLogin") == -1
						&& url.indexOf("welcome") == -1
						&& !url.equals(contextPath + "/")
						&& !url.equals(contextPath + "")) {
					HttpSession session = req.getSession();
					if (session.getAttribute(Constants.USER_INFO_SESSION) == null) {

						res.sendRedirect(contextPath + "/welcome/sessionReloadUrl");
						return;
					}
				}
				if (url.indexOf(".") == -1
						&& (url.equals(contextPath + "") || url.equals(contextPath
								+ "/"))) {
					res.sendRedirect(contextPath + "/index.jsp");
					return;
				}
			}
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
}
