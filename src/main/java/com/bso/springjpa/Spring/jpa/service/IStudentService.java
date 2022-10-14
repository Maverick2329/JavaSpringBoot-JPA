package com.bso.springjpa.Spring.jpa.service;

import java.util.List;

import com.bso.springjpa.Spring.jpa.models.Student;

public interface IStudentService {
	
	Student addStudent(Student student);
	
	Student findStudentByEmail(String email);
	
	List<Student> retriveAllStudent();
	
	Boolean updateStudent(Student student);
	
	Boolean removeStudent(Student student);
	
	
}
