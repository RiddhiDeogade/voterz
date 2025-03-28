package com.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.User;
import com.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userServ;

	// Register User
	@PostMapping("/createuser")
	public String createUser(@ModelAttribute User user, HttpSession session) {
		String email = user.getEmail();

		if (userServ.getUserByEmail(email) != null) {
			session.setAttribute("fail", "Registration Failed! Email already in use.");
			return "redirect:/register";
		}

		userServ.addUser(user);
		session.setAttribute("msg", "Registration successful!");
		return "redirect:/register";
	}

	// User Dashboard
	@GetMapping("/user")
	public String dashboard(Model model, Principal principal, HttpSession session) {
		if (principal == null) {
			session.setAttribute("fail", "Please log in first.");
			return "redirect:/signin";
		}

		String email = principal.getName();
		User user = userServ.getUserByEmail(email);

		model.addAttribute("user", user);
		model.addAttribute("title", "DASHBOARD");

		return "user/dashboard";
	}
}
