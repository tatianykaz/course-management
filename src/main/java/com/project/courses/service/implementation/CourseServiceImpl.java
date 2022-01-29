package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.Course;
import com.project.courses.repository.CourseRepository;
import com.project.courses.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<Course> getCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses;
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course getById(Long id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found."));
		return course;
	}

	@Override
	public Course updateCourseById(Long id, Course updatedCourse) {
		Course course = this.getById(id);
		
		if(updatedCourse.getName() != null)
			course.setName(updatedCourse.getName());
		course.setDescription(updatedCourse.getDescription());
		course.setResources(updatedCourse.getResources());
		course.setFee(updatedCourse.getFee());
		
		course = this.saveCourse(course);
		
		return course;
	}

	@Override
	public Boolean deleteCourseById(Long id) {
		Course course = this.getById(id);
		
		courseRepository.delete(course);
		
		if (course == null)
			return false;
		
		return true;
	}

}
