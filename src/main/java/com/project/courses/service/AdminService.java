package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Admin;

public interface AdminService {
	
	List<Admin> getAdmins();
	
	Admin saveAdmin(Admin admin);

	Admin getById(Long id);
	
	Admin getByUserId(Long id);

	Admin updateAdminById(Long id, Admin updatedAdmin);

	Boolean deleteAdminById(Long id);

	void createAdminUser(Admin admin);

}
