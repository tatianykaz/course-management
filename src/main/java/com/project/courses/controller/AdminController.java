package com.project.courses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.courses.model.Admin;
import com.project.courses.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Admin>> getAdmins(){
		List<Admin> admins = adminService.getAdmins();
		return ResponseEntity.status(HttpStatus.OK).body(admins);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
		adminService.saveAdmin(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body(admin);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(adminService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin updatedAdmin){
		updatedAdmin = adminService.updateAdminById(id, updatedAdmin);
		return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteAdmin(@PathVariable Long id) {
		Boolean deleted = adminService.deleteAdminById(id);
		return ResponseEntity.status(HttpStatus.GONE).body(deleted);
		
	}	

}
