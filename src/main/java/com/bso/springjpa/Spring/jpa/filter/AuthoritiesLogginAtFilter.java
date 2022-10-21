package com.bso.springjpa.Spring.jpa.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AuthoritiesLogginAtFilter implements Filter {

	private final Logger LOG = Logger.getLogger(AuthoritiesLogginAtFilter.class.getName());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		LOG.info("Authentication validation is in progress");
		chain.doFilter(request, response);
	}

}
