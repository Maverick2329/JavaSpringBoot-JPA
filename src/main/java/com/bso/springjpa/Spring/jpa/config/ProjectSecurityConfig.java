package com.bso.springjpa.Spring.jpa.config;

import org.springframework.security.core.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.bso.springjpa.Spring.jpa.filter.AuthoritiesLogginAtFilter;
import com.bso.springjpa.Spring.jpa.filter.AuthoritiesLoggingAfterFilter;
import com.bso.springjpa.Spring.jpa.filter.RequestValidationBeforeFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${URLS.Authenticated.WithoutRoles}")
	private String authenticatedURLWithOutRoles;
	
	@Value("${URLS.Authenticated.WithBalanceRole}")
	private String authenticatedURLWithBalanceRoles;
	
	@Value("${URLS.Permitall}")
	private String permitedURL;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		/**
		 * Configuration to deny all the requests
		 */
		
		//http.authorizeRequests()
			//.anyRequest()
			//.denyAll()
			//.and().formLogin()
			//.and().httpBasic();
		//return http.build();
		
		/*
		 * Permit  all request
		 */
		
		//http.authorizeRequests()
		//.anyRequest()
		//.denyAll()
		//.and().formLogin()
		//.and().httpBasic();
	//return http.build();
		
		//.antMatchers("/api/**").authenticated()
		//.antMatchers("/api/student/register").permitAll()
		
		String[] listAuthenticated = this.authenticatedURLWithOutRoles.split(",");
		String[] listAuthenticatedWithBalanceRole = this.authenticatedURLWithBalanceRoles.split(",");
		String[] listUrlPermited = this.permitedURL.split(",");
		
		http.authorizeRequests()
		    .antMatchers(listAuthenticated).authenticated()
			.antMatchers(listAuthenticatedWithBalanceRole).hasAuthority("VIEWBALANCE")
			.antMatchers(listUrlPermited).permitAll()
			.and().formLogin()
			.and().httpBasic()
			.and().csrf().disable()
			//.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
			//.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
			//.addFilterAt(new AuthoritiesLogginAtFilter(), BasicAuthenticationFilter.class)
			;
			
		//return http.build();
	}
	
	/*
	 * NoOpPasswordEncoder is not safety
	 */
	/*@Bean
	public PasswordEncoder passwordEndcoder() {
		return NoOpPasswordEncoder.getInstance();
	}*/
	
	@Bean
	public PasswordEncoder passwordEndcoder() {
		return new BCryptPasswordEncoder(5);
	}
	
	//@Bean(BeanIds.AUTHENTICATION_MANAGER)
	//public AuthenticationManager authenticationManagerBean() throws Exception {
	//	return super.authenticationManagerBean();
	//}
	
	
}

class DummyAuthenticationManager implements AuthenticationManager {
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException{
		return authentication;
	}
}
