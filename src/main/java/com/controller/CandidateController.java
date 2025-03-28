package com.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping("/addcandidate") // Vote
	public String addCandidate(
			@RequestParam("candidate") String candidate,
			Principal principal,
			Model model,
			HttpSession session) {

		// Get logged-in user details
		String email = principal.getName();
		User user = userServ.getUserByEmail(email);

		// Check if the user has already voted
		if (user.getStatus() == null || user.getStatus().equals("Not Voted")) {
			try {
				// Retrieve selected candidate and increment votes
				Candidate selectedCan = canServ.getCandidateByCandidate(candidate);
				selectedCan.setVotes(selectedCan.getVotes() + 1);
				canServ.addCandidate(selectedCan); // Update candidate votes

				// Update user status to "Voted"
				user.setStatus("Voted");
				userServ.addUser(user); // Save updated user status

				session.setAttribute("vmsg", "Successfully Voted!");
			} catch (Exception e) {
				session.setAttribute("vmsg", "Something went wrong while voting.");
				e.printStackTrace(); // Consider using logging instead of printStackTrace()
				return "redirect:/user/";
			}
		} else {
			session.setAttribute("vmsg", "You have already voted!");
		}

		return "redirect:/user/";
	}
}
