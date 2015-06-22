package org.wpattern.mutrack.service.utils;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.wpattern.mutrack.service.security.AuthenticationTokenProcessingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(order = 1, prePostEnabled = true, securedEnabled = true)
public class ServiceSecurityContext extends WebSecurityConfigurerAdapter {

	@Inject
	private UserDetailsService userService;

	@Inject
	private AuthenticationTokenProcessingFilter authenticationFilter;

	@Inject
	private AuthenticationEntryPoint authEntryPoint;

	@Inject
	private StandardPasswordEncoder passwordEncoder;

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder("ThisIsASecretSoChangeMe");
	}

	@Bean(name = "authService")
	public UserDetailsService getUserDetailsService() {
		return this.userService;
	}

	@Bean(name = "springSecurityFilterChain")
	public AuthenticationTokenProcessingFilter getSpringSecurityFilter() {
		return this.authenticationFilter;
	}

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
