package com.crimsonlogic.hostelmanagementsystem.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	//deployment dispatcher like web.xml		deployement config is done here

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppPersistentContext.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
