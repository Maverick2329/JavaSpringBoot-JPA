package com.bso.springjpa.Spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.bso.springjpa.Spring.jpa.models.Student;

public interface IStudentRepository extends JpaRepository<Student, Long> {
	public Student findByEmailId(String emailId);
	public List<Student> findByGuardianNameNotNull();
	
	@Procedure(name = "GET_ALL_STUDENTS")
	public List<Student> GET_ALL_STUDENTS();
}

