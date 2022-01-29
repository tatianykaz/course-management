package com.project.courses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.courses.model.Course;
import com.project.courses.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Course>> getCourses(){
		List<Course> courses = courseService.getCourses();
		return ResponseEntity.status(HttpStatus.OK).body(courses);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Course> addCourse(@RequestBody Course course){
		courseService.saveCourse(course);
		return ResponseEntity.status(HttpStatus.CREATED).body(course);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(courseService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse){
		updatedCourse = courseService.updateCourseById(id, updatedCourse);
		return ResponseEntity.status(HttpStatus.OK).body(updatedCourse);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteCourse(@PathVariable Long id) {
		Boolean deleted = courseService.deleteCourseById(id);
		return ResponseEntity.status(HttpStatus.GONE).body(deleted);
		
	}	

}
