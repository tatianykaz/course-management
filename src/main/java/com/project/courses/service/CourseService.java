package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Course;
import com.project.courses.model.User;

public interface CourseService {
	
	List<Course> getCourses();
	
	Course saveCourse(Course course);

	Course getById(Long id);

	Course updateCourseById(Long id, Course updatedCourse);

	Boolean deleteCourseById(Long id);

	Boolean enrollStudent(Long idCourse, User user);
}
