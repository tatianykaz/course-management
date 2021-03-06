package com.project.courses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courses.model.Feedback;
import com.project.courses.model.Student;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
	public List<Feedback> findByStudent(Student student);

}
