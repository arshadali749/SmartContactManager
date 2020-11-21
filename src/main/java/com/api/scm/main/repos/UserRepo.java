package com.api.scm.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.scm.main.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
