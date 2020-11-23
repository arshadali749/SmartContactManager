package com.api.scm.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.repos.ContactRepo;

@Service
public class ContactService {
	@Autowired
	private ContactRepo contactRepo;

	public Contact saveContact(Contact contact) {
		return contactRepo.save(contact);

	}
}
