package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Candidate;
import com.repository.CandidateRepository;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository canRepo;

	public Candidate addCandidate(Candidate candidate) {
		return canRepo.save(candidate);
	}



	public int getNumOfVotes(String candidate) {
		return canRepo.getNumOfVotes(candidate);
	}

	public Candidate getCandidateByName(String candidate) {
		return canRepo.getCandidateByName(candidate);
	}

	public Candidate getCandidateByCandidate(String candidate) {
		return getCandidateByName(candidate);
	}
}
