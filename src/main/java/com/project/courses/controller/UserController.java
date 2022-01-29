package com.project.courses.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.courses.model.User;
import com.project.courses.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping("/new")
	public ResponseEntity<User> addUser(@RequestBody User user){
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@PutMapping(value = "/photo/{id}", headers={"content-type=multipart/form-data"})
	public ResponseEntity<User> updateUserPhoto(@PathVariable Long id, @RequestPart("photo") MultipartFile photo){
		User user = new User();
		try {
			user = userService.updateUserPhotoById(id, photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);			
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
		updatedUser = userService.updateUserById(id, updatedUser);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
		Boolean deleted = userService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.GONE).body(deleted);
		
	}	
}
