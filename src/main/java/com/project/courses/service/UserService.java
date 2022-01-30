package com.project.courses.service;

import java.util.List;

import com.project.courses.model.User;

public interface UserService {

	List<User> getUsers();
	
	User saveUser(User user);

	User getById(Long id);

	User updateUserById(Long id, User updatedUser);

	Boolean deleteUserById(Long id);

	User updateUserPhotoById(Long id, byte[] photo);

	byte[] getPhotoById(Long id);
	
	String encodeUserPassword(String password);
	
	User createUser(String fullname, String role);

}
