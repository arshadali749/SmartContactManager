package com.api.scm.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.scm.main.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.email=:email")
	public User getUserByUserame(@Param("email") String email);
}
