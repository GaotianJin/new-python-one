package com.sinosoft.core.spring;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ApplicationListener extends ContextLoaderListener{

	public void contextInitialized(ServletContextEvent sce) {  
	    // TODO Auto-generated method stub  
	    String webAppRootKey = sce.getServletContext().getRealPath("/");  
	    System.out.println("===webAppRootKey===="+webAppRootKey);
	    System.setProperty("fms.webroot" , webAppRootKey);  
	}  
 
	
}
