package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.Employee;
import com.project.courses.model.User;
import com.project.courses.repository.EmployeeRepository;
import com.project.courses.repository.EmployeeRepository;
import com.project.courses.service.EmployeeService;
import com.project.courses.service.UserService;

public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getById(Long id) {
		Employee employee = employeeRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
		return employee;
	}

	@Override
	public Employee getByUserId(Long userId) {
		User user = userService.getById(userId);
		Employee employee = employeeRepository.findByUser(user);
		return employee;
	}

	@Override
	public Employee updateEmployeeById(Long id, Employee updatedEmployee) {
		Employee employee = this.getById(id);
		
		if (updatedEmployee.getName() != null)
			employee.setName(updatedEmployee.getName());
		
		employee.setEmail(updatedEmployee.getEmail());
		
		employee = this.saveEmployee(employee);
		
		return employee;
	}

	@Override
	public Boolean deleteEmployeeById(Long id) {
		Employee employee = this.getById(id);
		
		employeeRepository.delete(employee);
		
		if (employee == null)
			return false;
		
		return true;
	}

}
