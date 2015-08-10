package org.wpattern.mutrack.factory.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.wpattern.mutrack.service.security.AuthenticationTokenProcessingFilter;
import org.wpattern.mutrack.service.security.UnauthorizedEntryPoint;

@Component
@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(this.passwordEncoder);
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Autowired
		private AuthenticationTokenProcessingFilter springSecurityFilterChain;

		@Autowired
		private UnauthorizedEntryPoint unauthorizedEntryPoint;

		public ApiWebSecurityConfigurationAdapter() {
			System.out.println();
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
			.antMatchers("/service/public/auth").permitAll()
			.antMatchers("/service/private/package/byuser").hasRole("user")
			.antMatchers("/service/private/package/**").hasRole("admin")
			.antMatchers("/service/private/user/**").hasRole("admin")
			.anyRequest().authenticated();
		}
	}

}
