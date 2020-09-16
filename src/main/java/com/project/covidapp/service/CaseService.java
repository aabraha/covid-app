package com.project.covidapp.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.covidapp.dto.State;
import com.project.covidapp.model.USCase;
import com.project.covidapp.repository.USCaseRepository;

@Service
public class CaseService {
	
	@Autowired
	USCaseRepository usCaseRepository;
	@Autowired
	private ZonedDateTimeWriteConverter dateObj;
	@Autowired
	private MongoTemplate mongoTemplate;
		
	@GetMapping("/cases")
	public List<USCase> getAllCases(){
		List<USCase> lists = new ArrayList<>();
		lists = usCaseRepository.findAll();
		System.err.println("retrieved date format: " + lists.get(0).getLastUpdated());
		return usCaseRepository.findAll();
	}

	public Optional<USCase> getCaseById(String id) {
		
		return usCaseRepository.findById(id);
	}

	public void deleteCaseById(String id) {
		
		usCaseRepository.deleteById(id);
	}

	public List<USCase> getCasesByDateModified(ZonedDateTime dateModified) {
		
		return usCaseRepository.findByLastUpdated(dateObj.convert(dateModified));
	}

	public List<USCase> getCasesByDateModifiedAfter(ZonedDateTime afterDate) {
		
		return usCaseRepository.findByLastUpdatedGreaterThan(dateObj.convert(afterDate));
	}

	public List<USCase> getCasesByDateModifiedBefore(ZonedDateTime beforeDate) {
		
		return usCaseRepository.findByLastUpdatedLessThan(dateObj.convert(beforeDate));
	}

	public List<USCase> getCasesByDateModifiedBetween(ZonedDateTime from, ZonedDateTime to) {
		
		return usCaseRepository.findByLastUpdatedBetween(dateObj.convert(from), dateObj.convert(to));
	}

	public List<State> getCasesByState(String state) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("state").is(state));
		List<State> states = mongoTemplate.find(query, State.class);

		return states;
	}

}
