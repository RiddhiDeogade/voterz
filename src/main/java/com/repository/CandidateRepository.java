package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	@Query("SELECT c.votes FROM Candidate c WHERE c.candidate = :candidate")
	int getNumOfVotes(@Param("candidate") String candidate);

	@Query("SELECT c FROM Candidate c WHERE c.candidate = :candidate")
	Candidate getCandidateByName(@Param("candidate") String candidate);
}
