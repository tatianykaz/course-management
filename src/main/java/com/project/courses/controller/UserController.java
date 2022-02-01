package com.project.courses.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
	}
	
	@GetMapping("/photo/{id}")
	@ResponseBody
	public void getPhotoById(@PathVariable Long id, HttpServletResponse response){		
		ByteArrayInputStream photo = userService.getPhotoByUserId(id);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    try {
			StreamUtils.copy(photo, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping(value = "/photo", headers={"content-type=multipart/form-data"})
	public ResponseEntity<User> updateUserPhoto(@RequestPart("photo") MultipartFile photo){
		User user = userService.getAutheticatedUser();
		try {
			user = userService.updateUserPhoto(user, photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(user);			
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
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
