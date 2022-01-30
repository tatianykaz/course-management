package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.Student;
import com.project.courses.model.User;
import com.project.courses.repository.StudentRepository;
import com.project.courses.service.StudentService;

public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getStudents() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getById(Long id) {
		Student student = studentRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Student not found."));
		return student;
	}

	@Override
	public Student getByUser(User user) {
		Student student = studentRepository.findByUser(user);
		return student;
	}

	@Override
	public Student updateStudentById(Long id, Student updatedStudent) {
		Student student = this.getById(id);
		
		if (updatedStudent.getName() != null)
			student.setName(updatedStudent.getName());
		
		student.setEmail(updatedStudent.getEmail());
		
		student = this.saveStudent(student);
		
		return student;
	}

	@Override
	public Boolean deleteStudentById(Long id) {
		Student student = this.getById(id);
		
		studentRepository.delete(student);
		
		if (student == null)
			return false;
		
		return true;
	}

}
