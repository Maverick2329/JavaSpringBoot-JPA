package com.bso.springjpa.Spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bso.springjpa.Spring.jpa.models.Course;

public interface ICourseRepository extends JpaRepository<Course, Long>{
	
}
