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

	@Bean(name = "passwordEncoder")
	public StandardPasswordEncoder getStandardPasswordEncoder() {
		return new StandardPasswordEncoder(this.securityProperties.getSecret());
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		private static final String ALL = "/**";

		@Autowired
		private AuthenticationTokenFilter springSecurityFilterChain;

		@Autowired
		private UnauthorizedEntryPoint unauthorizedEntryPoint;

		@Autowired
		private PasswordEncoder passwordEncoder;

		@Autowired
		private UserDetailsService userService;

		@Override
		@Bean(name = "authenticationManager")
		public AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
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
			// Global Authority to OPTIONS (permit all).
			.antMatchers(HttpMethod.OPTIONS, ServiceNames.PRIVATE_ROOT_PATH + ALL).permitAll()
			// Package Authorities (Custom).
			.antMatchers(HttpMethod.GET, ServiceNames.PACKAGE_PATH + "/user").hasAnyAuthority(PermissionType.USER.role())
			.antMatchers(HttpMethod.GET, ServiceNames.PACKAGE_PATH).hasAnyAuthority(PermissionType.USER.role())
			.antMatchers(HttpMethod.POST, ServiceNames.PACKAGE_PATH).hasAnyAuthority(PermissionType.USER.role())
			.antMatchers(HttpMethod.PUT, ServiceNames.PACKAGE_PATH).hasAnyAuthority(PermissionType.USER.role())
			.antMatchers(HttpMethod.DELETE, ServiceNames.PACKAGE_PATH).hasAnyAuthority(PermissionType.USER.role())
			// Package Authorities.
			.antMatchers(ServiceNames.PACKAGE_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			// User Authorities.
			.antMatchers(ServiceNames.USER_PATH + ALL).hasAnyAuthority(PermissionType.ADMIN.role())
			.anyRequest().authenticated();
		}
	}

}
