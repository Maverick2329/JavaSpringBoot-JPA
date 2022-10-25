package com.bso.springjpa.Spring.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bso.springjpa.Spring.jpa.models.Student;
import com.bso.springjpa.Spring.jpa.payload.LoginRequest;
import com.bso.springjpa.Spring.jpa.repository.IStudentRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private IStudentRepository _studentRepository;
		
	@PostMapping("/signin")
	public ResponseEntity<Student> authenticateUser(Authentication authenticacion){
		
		Student _student = _studentRepository.findByEmailId(authenticacion.getName());
		if(_student != null) {
			return new ResponseEntity<>(_student, HttpStatus.OK);
		}
		else 
		{
			return null;
		}
	}
}
