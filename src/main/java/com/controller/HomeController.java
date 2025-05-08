package com.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model m)
	{
		m.addAttribute("title","HOME");
		return "home";
	}

	@GetMapping("/signin")
	public String signin(HttpSession session, Model model) {
		Object vmsg = session.getAttribute("vmsg");

		if (vmsg != null) {
			model.addAttribute("vmsg", vmsg);
			session.removeAttribute("vmsg"); // Clear it manually here
		}

		return "signin";
	}

	@GetMapping("/register")
	public String register(Model m)
	{
		m.addAttribute("title","REGISTER");
		return "register";
	}

	@GetMapping("/about")
	public String about(Model m)
	{
		m.addAttribute("title","ABOUT");
		return "about";
	}
}
