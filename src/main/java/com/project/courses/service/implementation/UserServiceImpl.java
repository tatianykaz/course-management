package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.courses.auth.MyUserDetails;
import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.User;
import com.project.courses.repository.UserRepository;
import com.project.courses.service.RoleService;
import com.project.courses.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;

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
		
		if (updatedUser.getPassword() != null)
			user.setPassword(this.encodeUserPassword(updatedUser.getPassword()));
		
		updatedUser = userRepository.save(user);
		
		return updatedUser;
	}

	@Override
	public Boolean deleteUserById(Long id) {
		
		User user = this.getById(id);
		user.getRoles().clear();
		
		try {
			userRepository.save(user);
			userRepository.delete(user);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
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

	@Override
	public String encodeUserPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public User createUser(String fullname, String role) {
		User user = new User();
		user.setLogin(fullname.split(" ")[0].toLowerCase());
		user.setPassword(this.encodeUserPassword("abc123"));
		user.addRole(roleService.getByRoleName(role));
		return user;		
	}

	@Override
	public User getAutheticatedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((MyUserDetails)principal).getUser();
	}
}
