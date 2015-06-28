package org.wpattern.mutrack.factory.utils;

import javax.inject.Inject;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Provider
@Component
public class WebAppInitializer implements WebApplicationInitializer, Feature {

	@Inject
	private FactoryProperties properties;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Spring and RESTEasy listeners.
		servletContext.addListener(new ResteasyBootstrap());
		servletContext.addListener(new SpringContextLoaderListener());

		// RESEasy servlet.
		ServletRegistration.Dynamic restDispatcher = servletContext.addServlet("Resteasy", new HttpServletDispatcher());
		restDispatcher.setLoadOnStartup(1);
		restDispatcher.addMapping(ServiceNames.ROOT_PATH + "/*");

		// Spring Security filter.
		FilterRegistration.Dynamic securityDispatcher = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
		securityDispatcher.addMappingForUrlPatterns(null, false, "/*");
	}

	@Override
	public boolean configure(FeatureContext context) {
		// CORS configuration.
		CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().addAll(this.properties.getCorsAllowedOrigins());
		context.register(corsFilter);

		return true;
	}

}
