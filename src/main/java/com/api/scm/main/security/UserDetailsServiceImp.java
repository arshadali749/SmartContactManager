package com.api.scm.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.api.scm.main.entities.User;
import com.api.scm.main.repos.UserRepo;

public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.getUserByUserame(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		CustomUserDetails customeUserDetails = new CustomUserDetails(user);
		return customeUserDetails; 
	}

}
