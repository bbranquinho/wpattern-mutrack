package org.wpattern.mutrack.factory.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.springframework.web.WebApplicationInitializer;
import org.wpattern.mutrack.utils.services.ServiceNames;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new ResteasyBootstrap());
		servletContext.addListener(new SpringContextLoaderListener());

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("Resteasy", new HttpServletDispatcher());
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(ServiceNames.ROOT_PATH + "/*");

		// TODO: augusto.branquinho Not tested and developed until now. Will be used when start the development of the AngularJS application.
		//		FilterRegistration.Dynamic corsFilder = servletContext.addFilter("CORS", new CORSFilter());
		//		corsFilder.addMappingForUrlPatterns(null, false, "/*");
	}

}
