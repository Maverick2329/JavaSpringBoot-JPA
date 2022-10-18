package com.bso.springjpa.Spring.jpa.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class StudentDetailsConfig implements AuthenticationProvider{
	
	@Autowired
	private IStudentRepository _studentRepository;
	@Autowired
	private PasswordEncoder _passwordEncoder;

	
	//@Override
	//public Authentication loadUserByUsername(String _username) throws UsernameNotFoundException 
	//{
		// TODO Auto-generated method stub
	//	String userName, password = null;
	//	List<GrantedAuthority> authorities = null;
	//	Student student = _studentRepository.findByEmailId(_username);
	//	if(student == null) {
	//		throw new UsernameNotFoundException("User details was not founded");
	//	}
	//	else {
	//		if(_passwordEncoder.matches(password, student.getPassword())) {
	//			authorities = new ArrayList<>();
	//			authorities.add(new SimpleGrantedAuthority(student.getRole()));
	//		}
	//		/*userName = student.getEmailId();
	//		password = student.getPassword();*/
	//	}		
	//	return new User(userName, password, authorities);
	//}

	@Override
	public org.springframework.security.core.Authentication authenticate(
			org.springframework.security.core.Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String userName = authentication.getName(); 
		String password = authentication.getCredentials().toString();
		Student student = _studentRepository.findByEmailId(userName);
		
		if(student != null) {
			if(_passwordEncoder.matches(password, student.getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(student.getRole()));
				return new UsernamePasswordAuthenticationToken(userName, password, getGrantedAuthorities(student.getAuthorities()));
			}
			else {
				throw new BadCredentialsException("No User registered with those credential");
			}
		}		
		throw new BadCredentialsException("No User registered with those credential");
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
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
