package com.project.courses.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.courses.model.Role;
import com.project.courses.repository.RoleRepository;
import com.project.courses.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role getByRoleName(String role) {
		return roleRepository.getByRoleName(role);
	}

}
