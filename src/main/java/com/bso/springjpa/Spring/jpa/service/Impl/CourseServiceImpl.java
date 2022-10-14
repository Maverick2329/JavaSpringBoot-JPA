package com.bso.springjpa.Spring.jpa.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bso.springjpa.Spring.jpa.models.Course;
import com.bso.springjpa.Spring.jpa.models.Student;
import com.bso.springjpa.Spring.jpa.repository.ICourseRepository;
import com.bso.springjpa.Spring.jpa.repository.IStudentRepository;
import com.bso.springjpa.Spring.jpa.service.ICourseService;
import com.bso.springjpa.Spring.jpa.service.IStudentService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private IStudentRepository _studentRepository;
	
	@Autowired
	private ICourseRepository _courseRepository;
	
	@Override
	public Boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			
			
			Student _student = _studentRepository
					.findById(course.getStudent().getStudentId())
					.orElseThrow(() -> new RuntimeException("Student_id doesnot exist"));

			System.out.println(_student);
			
			Course _newCourse = Course.builder()
					.title(course.getTitle())
					.credits(course.getCredits())
					.student(_student)
					.build();
			
			this._courseRepository.save(_newCourse);
			
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

}
