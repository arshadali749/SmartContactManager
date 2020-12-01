package com.api.scm.main.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {
	public List<Contact> findByUser(User user);
}
