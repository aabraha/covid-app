package com.project.covidapp.repository;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;

import com.project.covidapp.dto.WorldCase;

public interface ConsumedModelRepository extends MongoRepository<WorldCase, Integer> {
	
	/*
	 * Pageable pageable = PageRequest.of(0, 10);
	 * 
	 * Query casesDynamicQuery = new Query().with(pageable); // Add criteria's
	 * according to your wish to patientsDynamicQuery List<WorldCase> filteredCases
	 * = mongoTemplate.find(casesDynamicQuery, WorldCase.class, "last_updated");
	 * Page<WorldCase> casePage = PageableExecutionUtils.getPage( filteredCases,
	 * pageable, () -> mongoTemplate.count(casesDynamicQuery, WorldCase.class));
	 */

}
