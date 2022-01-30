package com.project.courses.service;

import java.util.List;

import com.project.courses.model.Contact;
import com.project.courses.model.User;

public interface ContactService {
	
	List<Contact> getContacts();
	
	Contact saveContact(Contact contact);
	
	Contact saveContact(Contact contact, User user);

	Contact getById(Long id);
	
	List<Contact> getByStudentId(Long studentId);

	Contact updateContactById(Long id, Contact updatedContact);

	Boolean deleteContactById(Long id);

}
