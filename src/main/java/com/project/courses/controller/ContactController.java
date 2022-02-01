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

import com.project.courses.model.Contact;
import com.project.courses.model.User;
import com.project.courses.service.ContactService;
import com.project.courses.service.UserService;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getContacts(){
		List<Contact> contacts = contactService.getContacts();
		return ResponseEntity.status(HttpStatus.OK).body(contacts);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
		User user = userService.getAutheticatedUser();
		contactService.saveContact(contact, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(contact);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(contactService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact){
		updatedContact = contactService.updateContactById(id, updatedContact);
		return ResponseEntity.status(HttpStatus.OK).body(updatedContact);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deleteContact(@PathVariable Long id) {
		Boolean deleted = contactService.deleteContactById(id);
		return ResponseEntity.status(HttpStatus.GONE).body(deleted);
		
	}	

}
