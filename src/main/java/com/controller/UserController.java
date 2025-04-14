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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserService userServ;

	// Display Registration Page
	@GetMapping("/user-register")
	public String showRegisterForm(HttpSession session, Model model) {
		Object msg = session.getAttribute("msg");
		Object fail = session.getAttribute("fail");

		if (msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg");
		}

		if (fail != null) {
			model.addAttribute("fail", fail);
			session.removeAttribute("fail");
		}

		return "register";
	}

	// Register User
	@PostMapping("/createuser")
	public String createUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
		String email = user.getEmail();

		if (userServ.getUserByEmail(email) != null) {
			redirectAttributes.addFlashAttribute("fail", "Registration Failed! Email already in use.");
			return "redirect:/user-register";
		}

		userServ.addUser(user);
		redirectAttributes.addFlashAttribute("msg", "Registration successful!");
		return "redirect:/user-register";	}

	//  User Dashboard
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


	//********
//	@GetMapping("/user")
//	public String dashboard(Model m, Principal p)
//	{
//		String email = p.getName(); //
//
//		User user  = userServ.getUserByEmail(email);
//
//		m.addAttribute("user",user);
//		m.addAttribute("title","DASHBOARD");
//
//		return "user/dashboard";
//
//
//	}

}
