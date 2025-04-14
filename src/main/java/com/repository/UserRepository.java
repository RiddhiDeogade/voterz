package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

	// Custom query to fetch User by email
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User getUserByEmail(String email);
}
