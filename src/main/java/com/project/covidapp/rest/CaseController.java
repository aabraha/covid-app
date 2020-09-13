package com.project.covidapp.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.covidapp.model.USCase;
import com.project.covidapp.service.CaseService;

@RestController
@RequestMapping("/api")
public class CaseController {
	@Autowired
	private CaseService caseService;
			
	@GetMapping("/cases")
	public List<USCase> getAllCases(){
		
		return caseService.getAllCases();
	}
	
	@GetMapping("/cases/{id}")
	public Optional<USCase> getCaseById(@PathVariable String id) {
		
		return caseService.getCaseById(id);
	}
	
	@GetMapping("/cases/{dateModified}")
	public List<USCase> getCasesByDateModefied(@PathVariable Date dateModified){
		
		return caseService.getCasesByDateModified(dateModified);
	}
	
	@DeleteMapping("/cases/{id}")
	public String deleteCaseById(@PathVariable String id) {
		
		caseService.deleteCaseById(id);
		return "deleted entry with id: " + id;
	}
	

}
