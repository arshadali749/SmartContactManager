package com.api.scm.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.scm.main.entities.User;
import com.api.scm.main.repos.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public User getUserByUserName(String username) {
		return userRepo.getUserByUserame(username);
	}

}
