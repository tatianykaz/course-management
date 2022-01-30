package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Employee;

public interface EmployeeService {
	
	List<Employee> getEmployees();
	
	Employee saveEmployee(Employee employee);

	Employee getById(Long id);
	
	Employee getByUserId(Long id);

	Employee updateEmployeeById(Long id, Employee updatedEmployee);

	Boolean deleteEmployeeById(Long id);

}
