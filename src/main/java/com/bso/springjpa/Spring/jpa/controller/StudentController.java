package com.bso.springjpa.Spring.jpa.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bso.springjpa.Spring.jpa.models.Student;
import com.bso.springjpa.Spring.jpa.service.IStudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private IStudentService _studentService;
	
	@PostMapping("/register")
	public ResponseEntity<Student> createNewStudent(@Valid @RequestBody Student student){
		Student studentAdded = this._studentService.addStudent(student);
		return new ResponseEntity<>(studentAdded, HttpStatus.CREATED);
	}
	
	@GetMapping("/byEmail")
	public ResponseEntity<Student> getStudentByEmail(@RequestParam(name="email", required = true) String email){
		Student getStudentByEmail=this._studentService.findStudentByEmail(email);
		return new ResponseEntity<>(getStudentByEmail, HttpStatus.OK);
	}
	
	@GetMapping("/AllStudents")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> getAllStudent = this._studentService.retriveAllStudent();
		return new ResponseEntity<>(getAllStudent, HttpStatus.OK);
	}
	
	@PutMapping("/updateStudent")
	public ResponseEntity<Boolean> updateStudent(@Valid @RequestBody Student student){
		Boolean success = this._studentService.updateStudent(student);
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>>RetieveAllStudents(){
		return new ResponseEntity<List<Student>>(this._studentService.retriveAllStudent(),HttpStatus.OK);
	}
	
	@GetMapping("/authorities")
	public ResponseEntity<List<Student>>RetieveAllStudentsAuthorities(){
		return new ResponseEntity<List<Student>>(this._studentService.retrieveAllStudentsAuthorities(),HttpStatus.OK);
	}
	
}





/*spring.datasource.url=jdbc:mysql://127.0.0.1:3305/springjpa-project
spring.datasource.username=root
spring.jpa.show-sql=true
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update*/

