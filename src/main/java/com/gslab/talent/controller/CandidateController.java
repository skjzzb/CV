package com.gslab.talent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.gslab.talent.constant.Constant;
import com.gslab.talent.model.Candidate;
import com.gslab.talent.service.CandidateService;



@RestController
@RequestMapping(value = "/v1")
public class CandidateController {

	@Autowired
	private CandidateService ServiceObj;
	
	@GetMapping(value = Constant.GET_LIST_OF_CANDIDATES, headers = Constant.ACCEPT_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Candidate> getAllCandidates() {
		return ServiceObj.getAllCandidate();
	}

	@GetMapping(value = Constant.GET_CANDIDATE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Candidate> getCandidateById(@PathVariable(Constant.CANDIDATE_ID) long id) {
		Candidate candidateObj = ServiceObj.findById(id);
		if (candidateObj == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(candidateObj, HttpStatus.OK);
	}


	@PostMapping(value = Constant.ADD_CANDIDATE, headers = Constant.ACCEPT_JSON)
	public ResponseEntity<Void> createCandidate(@RequestBody Candidate candidateObj, UriComponentsBuilder ucBuilder) {
		System.out.println(candidateObj);
		ServiceObj.createCandidate(candidateObj);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(Constant.GET_CANDIDATE_BY_ID).buildAndExpand(candidateObj.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PostMapping(value = Constant.UPDATE_CANDIDATE ,headers = Constant.ACCEPT_JSON)
	public ResponseEntity<Void> updateCandidateInfo(@RequestBody Candidate candidateObj )
	{
		ServiceObj.update(candidateObj);
		return new ResponseEntity<Void>(HttpStatus.FOUND);
	}
	
	@DeleteMapping(Constant.DELETE_CANDIDATE_BY_ID)
	public ResponseEntity<Void> deleteCandidateById(@PathVariable long id, UriComponentsBuilder ucBuilder) {
		ServiceObj.deleteCandidateById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path(Constant.DELETE_CANDIDATE_BY_ID).buildAndExpand(id).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
	

}
