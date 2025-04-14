package com.service;

import java.util.List;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepo;

	// Using the static method to get the NoOpPasswordEncoder instance
	private final NoOpPasswordEncoder passwordEncoder = (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();

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

	// Retrieves all users
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	// Retrieves a user by ID
	public User getUserById(int id) {
		return userRepo.findById(id).orElse(null);
	}

	// Deletes a user by ID
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

	// Retrieves a user by email
	public User getUserByEmail(String email) {
		return userRepo.getUserByEmail(email);
	}
}
