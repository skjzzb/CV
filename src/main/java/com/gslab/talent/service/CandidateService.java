package com.gslab.talent.service;

import java.util.List;

import com.gslab.talent.model.Candidate;

public interface CandidateService {

	public void createCandidate(Candidate candidateObj);
	public  List<Candidate> getAllCandidate();
	public Candidate findById(long id);
	public  Candidate update(Candidate candidateObj);
	public void deleteCandidateById(long id);
	
}
