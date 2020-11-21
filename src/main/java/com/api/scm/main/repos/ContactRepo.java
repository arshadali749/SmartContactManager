package com.api.scm.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.scm.main.entities.Contact;
@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {

}
