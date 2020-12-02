package com.api.scm.main.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.scm.main.entities.Contact;
import com.api.scm.main.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {
	public Page<Contact> findByUser(User user,Pageable pageable );
}
