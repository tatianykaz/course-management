package com.project.courses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courses.model.Contact;
import com.project.courses.model.Student;

public interface ContactRepository extends JpaRepository<Contact, Long>  {

	public List<Contact> findByStudent(Student student);
}
