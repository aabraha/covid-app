package com.project.covidapp.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.project.covidapp.model.USCase;
import com.project.covidapp.repository.USCaseRepository;
import com.project.covidapp.rest.USCaseNotFoundException;

@Service
public class CaseService {

	@Autowired
	USCaseRepository usCaseRepository;
	@Autowired
	private ZonedDateTimeWriteConverter dateObj;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	//working on
	public Page<USCase> getAllCases(Pageable pageable){
		Page<USCase> pages =  usCaseRepository.findAll(pageable);
		if(pages.isEmpty()) {
			throw new USCaseNotFoundException("There is no any record found");
		}

		return pages;
	}

	public Optional<USCase> getCaseById(String id) {

		Optional<USCase> theCase = usCaseRepository.findById(id);
		if(!theCase.isPresent()) {
			throw new USCaseNotFoundException("There is no case with ID: " + id);
		}
		
		return theCase;
	}

	public List<USCase> getCasesByDateModified(ZonedDateTime dateModified) {

		List<USCase> lists = new ArrayList<>();
		lists = usCaseRepository.findByLastUpdated(dateObj.convert(dateModified));
		if(lists.size() == 0) {
			throw new USCaseNotFoundException("There is no any record found on: " +
					dateModified);
		}
		
		return lists;
	}

	public List<USCase> getCasesByDateModifiedAfter(ZonedDateTime afterDate) {

		List<USCase> lists = new ArrayList<>();
		lists = usCaseRepository.findByLastUpdatedGreaterThan(
				dateObj.convert(afterDate));
		if(lists.size() == 0) {
			throw new USCaseNotFoundException("There is no any record found after: " +
					afterDate);
		}
		
		return lists; 
	}

	public List<USCase> getCasesByDateModifiedBefore(ZonedDateTime beforeDate) {

		List<USCase> lists = new ArrayList<>();
		lists =  usCaseRepository.findByLastUpdatedLessThan(
				dateObj.convert(beforeDate));
		if(lists.size() == 0) {
			throw new USCaseNotFoundException("There is no any record found before: " +
					beforeDate);
		}
		
		return lists; 
	}

	public List<USCase> getCasesByDateModifiedBetween(ZonedDateTime from, 
			ZonedDateTime to) {

		List<USCase> lists = new ArrayList<>();
		lists =  usCaseRepository.findByLastUpdatedBetween(
				dateObj.convert(from), dateObj.convert(to));
		if(lists.size() == 0) {
			throw new USCaseNotFoundException("There is no any record found between: " +
					from + " and " + to);
		}
		
		return lists; 
	}

	public List<USCase> getCasesByState(String state) {

		AggregationOperation match = Aggregation.match(Criteria.where("states.state").is(state));
		AggregationOperation unwind = Aggregation.unwind("states");
		AggregationOperation group = Aggregation.group("id")
				.push("lastUpdated").as("lastUpdated")
				.push("states").as("states");

		List<AggregationOperation> operations = new ArrayList<>();
		operations.add(match);
		operations.add(unwind);
		operations.add(match);
		operations.add(group);
		Aggregation aggregation = Aggregation.newAggregation(operations);
		List<USCase> results = mongoTemplate.aggregate(aggregation, 
				USCase.class, USCase.class).getMappedResults();
		
		if(results.size() == 0) {
			throw new USCaseNotFoundException("There is no state named : " + state);
		}
		
		return results;
	}
	
	public String deleteCaseById(String id) {

		// First check availability of a case with that id
		Optional<USCase> theCase = usCaseRepository.findById(id);
		if(!theCase.isPresent()) {
			throw new USCaseNotFoundException("There is no case with ID: " + id);
		}
		
		usCaseRepository.deleteById(id);
		
		return "Case deleted with ID: " + id;
	}
}
