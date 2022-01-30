package com.project.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courses.model.Admin;
import com.project.courses.model.User;

public interface AdminRepository extends JpaRepository<Admin, Long>  {
	
	public Admin findByUser(User user);
}
