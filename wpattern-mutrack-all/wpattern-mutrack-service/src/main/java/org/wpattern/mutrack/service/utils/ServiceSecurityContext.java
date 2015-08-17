package org.wpattern.mutrack.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.AuthenticationTokenFilter;
import org.wpattern.mutrack.service.security.SecurityProperties;
import org.wpattern.mutrack.service.security.UnauthorizedEntryPoint;
import org.wpattern.mutrack.utils.entities.types.PermissionType;
import org.wpattern.mutrack.utils.services.ServiceNames;

@Component
@Configuration
@EnableWebSecurity
public class ServiceSecurityContext {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(this.securityProperties.getSecret());
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		private static final String ALL = "/**";

		@Autowired
		private AuthenticationTokenFilter springSecurityFilterChain;

		@Autowired
		private UnauthorizedEntryPoint unauthorizedEntryPoint;

		@Override
		@Bean(name = "authenticationManager")
		public AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.addFilterBefore(this.springSecurityFilterChain, UsernamePasswordAuthenticationFilter.class)
			.csrf().disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(this.unauthorizedEntryPoint)
			.and()
			.authorizeRequests()
			// Public Authorities (permit all).
			.antMatchers(ServiceNames.PUBLIC_ROOT_PATH + ALL).permitAll()
			// Package Authorities.
			.antMatchers(HttpMethod.GET, ServiceNames.PACKAGE_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.antMatchers(HttpMethod.POST, ServiceNames.PACKAGE_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.antMatchers(HttpMethod.PUT, ServiceNames.PACKAGE_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.antMatchers(HttpMethod.DELETE, ServiceNames.PACKAGE_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			// Package Authorities (Custom).
			.antMatchers(HttpMethod.GET, ServiceNames.PACKAGE_USER_CUSTOM_PATH).hasAnyAuthority(PermissionType.USER.role())
			// User Authorities.
			.antMatchers(HttpMethod.GET, ServiceNames.USER_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.antMatchers(HttpMethod.POST, ServiceNames.USER_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.antMatchers(HttpMethod.PUT, ServiceNames.USER_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.antMatchers(HttpMethod.DELETE, ServiceNames.USER_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			// Global Authority to OPTIONS.
			.antMatchers(HttpMethod.OPTIONS, ServiceNames.PRIVATE_ROOT_PATH + ALL).permitAll()
			.anyRequest().authenticated();
		}
	}

}
