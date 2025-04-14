package com.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Candidate;
import com.model.User;
import com.service.CandidateService;
import com.service.UserService;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService canServ;

	@Autowired
	private UserService userServ;

	@PostMapping("/addcandidate")
	public String addCandidate(@RequestParam("candidate") String candidate,
							   Principal p, Model model, HttpSession session) {
		String email = p.getName();
		User user = userServ.getUserByEmail(email);

		// Only allow voting if user has not voted yet
		if ("Not Voted".equalsIgnoreCase(user.getStatus())) {
			try {
				// Fetch candidate and increment votes
				Candidate selectedCan = canServ.getCandidateByCandidate(candidate);
				selectedCan.setVotes(selectedCan.getVotes() + 1);
				canServ.addCandidate(selectedCan); // save candidate

				// Update user status
				user.setStatus("Voted");
				userServ.addUser(user); // save user

				session.setAttribute("vmsg", "Successfully Voted...");
			} catch (Exception e) {
				session.setAttribute("vmsg", "Something went wrong...");
				e.printStackTrace();
			}
		} else {
			session.setAttribute("vmsg", "You have already voted.");
		}

		return "redirect:/user";
	}
}
