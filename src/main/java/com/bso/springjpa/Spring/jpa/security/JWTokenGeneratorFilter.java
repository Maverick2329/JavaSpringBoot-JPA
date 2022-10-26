package com.bso.springjpa.Spring.jpa.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bso.springjpa.Spring.jpa.constants.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTokenGeneratorFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder()
							 .setIssuer("MDF")
							 .setSubject("token")
							 .claim("username", authentication.getName())
							 .claim("authorities", populateAuthorities(authentication.getAuthorities()))
							 .setIssuedAt(new Date())
							 .setExpiration(new Date((new Date()).getTime()+6000000))
							 .signWith(key)
							 .compact();
			
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
			filterChain.doFilter(request, response);
		}
	}
	
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().equals("/api/auth/signin");
	}
	
	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>(); 
		for(GrantedAuthority authority:collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}
}




