package com.project.covidapp.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.covidapp.dto.WorldCase;
import com.project.covidapp.model.USCase;
import com.project.covidapp.repository.ConsumedModelRepository;
import com.project.covidapp.repository.USCaseRepository;

@RestController
@RequestMapping("/api")
public class CaseController {
	@Autowired
	ConsumedModelRepository consumedModelRepository;
	
	@Autowired
	USCaseRepository usCaseRepository;
		
	@GetMapping("/cases")
	public List<USCase> getData(){
		
		return usCaseRepository.findAll();
	}
	
	@GetMapping("/cases/{id}")
	public Optional<WorldCase> getById(@PathVariable int id) {
		
		return consumedModelRepository.findById(id);
	}
	
	@DeleteMapping("/cases/{id}")
	public String delete(@PathVariable String id) {
		
		usCaseRepository.deleteById(id);
		return "deleted entry with id: " + id;
	}

}
