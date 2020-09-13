package com.project.covidapp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.covidapp.model.USCase;
import com.project.covidapp.repository.USCaseRepository;

@Service
public class CaseService {
	
	@Autowired
	USCaseRepository usCaseRepository;
		
	@GetMapping("/cases")
	public List<USCase> getAllCases(){
		
		return usCaseRepository.findAll();
	}

	public Optional<USCase> getCaseById(String id) {
		
		return usCaseRepository.findById(id);
	}

	public void deleteCaseById(String id) {
		
		usCaseRepository.deleteById(id);
	}

	public List<USCase> getCasesByDateModified(Date dateModified) {
		
		return usCaseRepository.findByLastUpdated(dateModified);
	}

}
