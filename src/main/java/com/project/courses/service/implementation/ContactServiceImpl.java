package com.project.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.courses.exceptions.ResourceNotFoundException;
import com.project.courses.model.Contact;
import com.project.courses.model.User;
import com.project.courses.repository.ContactRepository;
import com.project.courses.service.ContactService;
import com.project.courses.service.UserService;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Contact> getContacts() {
		List<Contact> contacts = contactRepository.findAll();
		return contacts;
	}

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public Contact getById(Long id) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact not found."));
		return contact;
	}

	@Override
	public List<Contact> getByUserId(Long userId) {
		User user = userService.getById(userId);
		List<Contact> contact = contactRepository.findByUser(user);
		return contact;
	}

	@Override
	public Contact updateContactById(Long id, Contact updatedContact) {
		Contact contact = this.getById(id);
		
		if (updatedContact.getName() != null)
			contact.setName(updatedContact.getName());
		contact.setMessage(updatedContact.getMessage());
		contact.setEmail(updatedContact.getEmail());
		contact.setPhone(updatedContact.getPhone());
		
		updatedContact = contactRepository.save(contact);
		
		return updatedContact;
	}

	@Override
	public Boolean deleteContactById(Long id) {
		Contact contact = this.getById(id);
		
		contactRepository.delete(contact);
		
		if (contact == null)
			return false;
		
		return true;
	}
}
