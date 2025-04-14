package com.service;

import java.util.List;

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

	public List<Candidate> getAllCandidates() {
		return canRepo.findAll();
	}

	public Candidate getCandidateById(int id) {
		return canRepo.findById(id).orElse(null);
	}

	public void deleteCandidate(int id) {
		canRepo.deleteById(id);
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
