package com.project.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courses.model.Student;
import com.project.courses.model.User;

public interface StudentRepository  extends JpaRepository<Student, Long>  {
	
	public Student findByUser(User user);

}
