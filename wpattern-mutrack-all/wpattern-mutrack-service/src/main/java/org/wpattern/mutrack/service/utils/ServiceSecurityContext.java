package org.wpattern.mutrack.service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import org.wpattern.mutrack.service.security.UnauthorizedEntryPoint;
import org.wpattern.mutrack.service.security.properties.SecurityProperties;

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
			.antMatchers("/service/public/**").permitAll()
			.antMatchers("/service/private/package/byuser").hasAnyAuthority("user")
			.antMatchers("/service/private/package/**").hasAnyAuthority("admin")
			.antMatchers("/service/private/user/**").hasAnyAuthority("admin")
			.anyRequest().authenticated();
		}
	}

}
