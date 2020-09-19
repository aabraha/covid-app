package com.project.covidapp.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
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
	@Autowired
	private MongoOperations mongoOperations;

	@GetMapping("/cases")
	public List<USCase> getAllCases() {
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
		List<USCase> results = mongoTemplate.aggregate(aggregation, USCase.class, USCase.class).getMappedResults();
		
		return results;
	}
}
