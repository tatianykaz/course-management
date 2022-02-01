package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Student;
import com.project.courses.model.User;

public interface StudentService {

	List<Student> getStudents();
	
	Student saveStudent(Student student);
	
	Student getByUser(User user);

	Student getById(Long id);

	Student updateStudentById(Long id, Student updatedStudent);

	Boolean deleteStudentById(Long id);

	void createStudentUser(Student student);
}
