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
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.wpattern.mutrack.service.security.AuthenticationTokenProcessingFilter;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Provider
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebAppInitializer extends WebSecurityConfigurerAdapter implements WebApplicationInitializer, Feature {

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

	@Inject
	private UserDetailsService userService;

	@Inject
	private AuthenticationEntryPoint authEntryPoint;

	@Inject
	private AuthenticationTokenProcessingFilter authenticationFilter;

	@Inject
	private StandardPasswordEncoder passwordEncoder;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);

		auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.userDetailsService(this.userService);

		http.httpBasic().authenticationEntryPoint(this.authEntryPoint);
		http.addFilterBefore(this.authenticationFilter, BasicAuthenticationFilter.class);

		//http.csrf().disable();
		http.authorizeRequests().antMatchers("/service/public/auth").permitAll();
		http.authorizeRequests().antMatchers("/service/private/package").permitAll();
		http.authorizeRequests().antMatchers("/service/private/user/**").hasRole("user");
	}

}
