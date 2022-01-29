package com.project.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courses.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>  {

}
