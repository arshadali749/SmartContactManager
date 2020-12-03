package com.api.scm.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;
import com.api.scm.main.repos.ContactRepo;

@Service
public class ContactService {
	@Autowired
	private ContactRepo contactRepo;

	public Contact saveContact(Contact contact) {
		return contactRepo.save(contact);

	}

	public Page<Contact> getContactsList(User user, Pageable pageable) {
		return contactRepo.findByUser(user, pageable);
	}

	public Optional<Contact> getContactById(int id) {
		return contactRepo.findById(id);
	}
}
