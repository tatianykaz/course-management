package com.project.courses.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private byte[] resources;
	
	private String description;
	
	private BigDecimal fee;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
            )
    private Set<Student> students = new HashSet<>();
	
	public void addStudent(Student student) {
		this.getStudents().add(student);
	}

}
