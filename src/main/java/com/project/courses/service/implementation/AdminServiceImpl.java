package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.Admin;
import com.project.courses.model.User;
import com.project.courses.repository.AdminRepository;
import com.project.courses.service.AdminService;
import com.project.courses.service.UserService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Admin> getAdmins() {
		List<Admin> admins = adminRepository.findAll();
		return admins;
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		this.createAdminUser(admin);
		return adminRepository.save(admin);
	}
	
	@Override
	public void createAdminUser(Admin admin) {
		if (admin.getUser() == null) 
			admin.setUser(userService.createUser(admin.getName(), "ADMIN"));
	}

	@Override
	public Admin getById(Long id) {
		Admin admin = adminRepository.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("Admin not found."));
		return admin;
	}

	@Override
	public Admin getByUserId(Long userId) {
		User user = userService.getById(userId);
		Admin admin = adminRepository.findByUser(user);
		return admin;
	}

	@Override
	public Admin updateAdminById(Long id, Admin updatedAdmin) {
		Admin admin = this.getById(id);
		
		if (updatedAdmin.getName() != null)
			admin.setName(updatedAdmin.getName());
		
		admin.setEmail(updatedAdmin.getEmail());
		admin.setAddress(updatedAdmin.getAddress());
		admin.setPhone(updatedAdmin.getPhone());
		admin.setPosition(updatedAdmin.getPosition());
		
		admin = this.saveAdmin(admin);
		
		return admin;
	}

	@Override
	public Boolean deleteAdminById(Long id) {
		Admin admin = this.getById(id);
		
		try {
			adminRepository.delete(admin);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
