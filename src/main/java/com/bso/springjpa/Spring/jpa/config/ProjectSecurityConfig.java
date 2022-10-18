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

@Configuration
public class ProjectSecurityConfig {

	@Value("${URLS.Authenticated}")
	private String authenticatedURL;
	
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
		
		String[] listAuthenticated = this.authenticatedURL.split(",");
		String[] listUrlPermited = this.permitedURL.split(",");
		
		http.authorizeHttpRequests()
			.antMatchers(listAuthenticated).authenticated()
			.antMatchers(listUrlPermited).permitAll()
			.and().formLogin()
			.and().httpBasic()
			.and().csrf().disable();
			
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
