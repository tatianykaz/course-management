package com.project.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.courses.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("SELECT r FROM Role r WHERE r.role = :role")
	public Role getByRoleName(String role);

	
}
