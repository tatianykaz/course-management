package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Course;

public interface CourseService {
	
	List<Course> getCourses();
	
	Course saveCourse(Course course);

	Course getById(Long id);

	Course updateCourseById(Long id, Course updatedCourse);

	Boolean deleteCourseById(Long id);
}
