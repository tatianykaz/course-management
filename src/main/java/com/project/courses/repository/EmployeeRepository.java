package com.project.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courses.model.Employee;
import com.project.courses.model.User;

public interface EmployeeRepository extends JpaRepository<Employee, Long>  {
	
	public Employee findByUser(User user);
}
