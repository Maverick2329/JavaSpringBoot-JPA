package com.bso.springjpa.Spring.jpa.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bso.springjpa.Spring.jpa.models.Authority;
import com.bso.springjpa.Spring.jpa.models.Student;
import com.bso.springjpa.Spring.jpa.repository.IStudentRepository;

@Service
@Transactional
public class StudentDetailsConfig implements AuthenticationProvider{
	
	@Autowired
	private IStudentRepository _studentRepository;
	@Autowired
	private PasswordEncoder _passwordEncoder;

	
	@Override
	public org.springframework.security.core.Authentication authenticate(
			org.springframework.security.core.Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String userName = authentication.getName(); 
		String password = authentication.getCredentials().toString();
		Student student = _studentRepository.findByEmailId(userName);
		
		if(student != null) {
			if(_passwordEncoder.matches(password, student.getPassword())) {
				
				return new UsernamePasswordAuthenticationToken(userName, password, getGrantedAuthorities(student.getAuthorities()));
			}
			else {
				throw new BadCredentialsException("No User registered with those credential");
			}
		}		
		throw new BadCredentialsException("No User registered with those credential");
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
		System.out.println(authorities);
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Authority authority: authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		return grantedAuthorities;
	}
	

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}


}
