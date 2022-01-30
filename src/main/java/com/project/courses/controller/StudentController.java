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

import com.project.courses.model.Student;
import com.project.courses.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getStudents(){
		List<Student> students = studentService.getStudents();
		return ResponseEntity.status(HttpStatus.OK).body(students);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(student);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(studentService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent){
		updatedStudent = studentService.updateStudentById(id, updatedStudent);
		return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id) {
		Boolean deleted = studentService.deleteStudentById(id);
		return ResponseEntity.status(HttpStatus.GONE).body(deleted);
		
	}	

}
