package com.bso.springjpa.Spring.jpa.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.bso.springjpa.Spring.jpa.filter.RequestValidationBeforeFilter;

@Configuration
public class ProjectSecurityConfig {

	@Value("${URLS.Authenticated.WithoutRoles}")
	private String authenticatedURLWithOutRoles;
	
	@Value("${URLS.Authenticated.WithBalanceRole}")
	private String authenticatedURLWithBalanceRoles;
	
	@Value("${URLS.Permitall}")
	private String permitedURL;
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
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
			.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class);
			
		return http.build();
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
}
