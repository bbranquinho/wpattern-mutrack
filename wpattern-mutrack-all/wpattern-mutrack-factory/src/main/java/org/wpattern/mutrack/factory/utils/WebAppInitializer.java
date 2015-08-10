package org.wpattern.mutrack.factory.utils;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.wpattern.mutrack.utils.services.ServiceNames;

public class WebAppInitializer  implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Spring and RESTEasy listeners.
		servletContext.addListener(new ResteasyBootstrap());
		servletContext.addListener(new SpringContextLoaderListener());

		// RESEasy servlet.
		ServletRegistration.Dynamic restDispatcher = servletContext.addServlet("resteasy", new HttpServletDispatcher());
		restDispatcher.setLoadOnStartup(1);
		restDispatcher.addMapping(ServiceNames.ROOT_PATH + "/*");

		// Spring Security filter.
		FilterRegistration.Dynamic securityDispatcher = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
		securityDispatcher.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
		//securityDispatcher.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), false, "springSecurityFilterChain");
	}

}
