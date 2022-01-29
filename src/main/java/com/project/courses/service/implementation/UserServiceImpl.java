package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.User;
import com.project.courses.repository.UserRepository;
import com.project.courses.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getById(Long id) throws ResourceNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found."));
		return user;
	}

	@Override
	public User updateUserById(Long id, User updatedUser) throws ResourceNotFoundException{
		User user = this.getById(id);
		
		if (updatedUser.getName() != null)
			user.setName(updatedUser.getName());
		if (updatedUser.getPassword() != null)
			user.setPassword(updatedUser.getPassword());
		user.setAddress(updatedUser.getAddress());
		user.setEmail(updatedUser.getEmail());
		user.setPhone(updatedUser.getPhone());
		user.setPhoto(updatedUser.getPhoto());
		user.setRoles(updatedUser.getRoles());
		
		updatedUser = userRepository.save(user);
		
		return updatedUser;
	}

	@Override
	public Boolean deleteUserById(Long id) {
		
		User user = this.getById(id);
		
		userRepository.delete(user);
		
		if (user == null)
			return false;
		
		return true;
	}

	@Override
	public User updateUserPhotoById(Long id, byte[] photo) {
		User user = this.getById(id);
		
		if (photo != null)
			user.setPhoto(photo);
		
		user = userRepository.save(user);
		
		return user;
	}

	@Override
	public byte[] getPhotoById(Long id) {
		User user = this.getById(id);  
		if (user.getPhoto() == null)
			throw new ResourceNotFoundException("Photo not found.");
		return user.getPhoto();		
	}
}
