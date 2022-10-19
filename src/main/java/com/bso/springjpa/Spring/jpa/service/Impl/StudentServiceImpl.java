package com.bso.springjpa.Spring.jpa.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bso.springjpa.Spring.jpa.models.Guardian;
import com.bso.springjpa.Spring.jpa.models.Student;
import com.bso.springjpa.Spring.jpa.repository.IStudentRepository;
import com.bso.springjpa.Spring.jpa.service.IStudentService;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IStudentRepository _studentRepository;
	
    @Autowired
    PasswordEncoder _passwordEncoder;
	
	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		Guardian guardian = Guardian.builder()
									.name(student.getGuardian().getName())
									.email(student.getGuardian().getEmail())
									.mobile(student.getGuardian().getMobile())
									.build();
		
		Student bldStudent = Student.builder()
									.firstName(student.getFirstName())
									.lastName(student.getLastName())
									.emailId(student.getEmailId())
									.password(_passwordEncoder.encode(student.getPassword()))
									.role(student.getRole())
									.guardian(guardian)
									.build();
		
		this._studentRepository.save(bldStudent);
		return bldStudent;
	}

	@Override
	public Student findStudentByEmail(String email) {
		// TODO Auto-generated method stub
		return this._studentRepository.findByEmailId(email);
	}

	@Override
	public Boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		try 
		{
			Guardian _guardian =  Guardian.builder()
									.name(student.getGuardian().getName())
									.email(student.getGuardian().getEmail())
									.mobile(student.getGuardian().getMobile())
									.build();
			
			Student studentUpdate =  Student.builder()
									.studentId(student.getStudentId())
					                .firstName(student.getFirstName())
					                .lastName(student.getLastName())
					                .emailId(student.getEmailId())
					                .guardian(_guardian)
					                .build();
			
			this._studentRepository.save(studentUpdate);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}		
	}

	@Override
	public Boolean removeStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> retriveAllStudent() {
		// TODO Auto-generated method stub
		//return this._studentRepository.findAll();
		return this._studentRepository.GET_ALL_STUDENTS();
	}
	
	public List<Student> retrieveAllStudentsAuthorities(){
		try {
			List<Student> studentsAuth = this._studentRepository.GET_ALL_STUDENTS_AUTHORITIES("Gabo@email.com");
			return studentsAuth;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
