package org.wpattern.mutrack.factory.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Provider
@Component
public class WebAppInitializer extends Application implements WebApplicationInitializer, Feature {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new ResteasyBootstrap());
		servletContext.addListener(new SpringContextLoaderListener());

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("Resteasy", new HttpServletDispatcher());
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(ServiceNames.ROOT_PATH + "/*");
	}

	@Override
	public boolean configure(FeatureContext context) {
		CorsFilter corsFilter = new CorsFilter();
		corsFilter.getAllowedOrigins().add("*");
		context.register(corsFilter);

		return true;
	}

}
