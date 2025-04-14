package com.service;


import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepo;

	// Using the static method to get the NoOpPasswordEncoder instance

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	// Adds a new user, sets role to "ROLE_NORMAL", and does not encode password (NoOpPasswordEncoder)
	public User addUser(User user) {
		user.setRole("ROLE_NORMAL");
		user.setPassword(user.getPassword());  // No encoding

		// Initialize status if not already set
		if (user.getStatus() == null || user.getStatus().trim().isEmpty()) {
			user.setStatus("Not Voted");
		}

		return userRepo.save(user);
	}


	// Retrieves a user by email
	public User getUserByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}
}
