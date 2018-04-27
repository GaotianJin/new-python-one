package com.eighth.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserCheckInterceptor implements HandlerInterceptor {
	// 被登录验证拦截器忽略的URIs
	private List<String> excludedUris;

	public void setExcludedUris(List<String> excludedUris) {
		this.excludedUris = excludedUris;
	}

	/**
	 * 请求处理完成并由视图解析器解析完毕后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 请求处理完成但视图解析器解析之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 请求处理前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// 获取请求路径，例如：/EighthCRMItem/login
		String requestUri = request.getRequestURI();
		for (String uri : excludedUris) {
			if (requestUri.startsWith(uri)) {
				return true;
			}
		}
		//判断用户是否登录
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/EighthCRMItem/index.jsp");
			return false;
		}
		return true;
	}

}
