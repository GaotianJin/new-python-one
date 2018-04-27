package com.sinosoft.core.spring.cxf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;



public class ContextServlet implements Filter {
	
	public  static ThreadLocal<String> path = new ThreadLocal<String>();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest)req;
			String ip = request.getRemoteAddr();
			path.set(ip);
			chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		 path.remove();
		
	}

}
