package com.project.covidapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.covidapp.dto.WorldCase;

public interface WorldCaseRepository extends PagingAndSortingRepository<WorldCase, Integer> {
	
	//List<WorldCase> findAll

}
