package com.project.covidapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.covidapp.model.USCase;

@Repository
public interface USCaseRepository extends MongoRepository<USCase, String> {

	List<USCase> findByLastUpdated(Date date);

	List<USCase> findByLastUpdatedGreaterThan(Date date);

	List<USCase> findByLastUpdatedLessThan(Date date);

	List<USCase> findByLastUpdatedBetween(Date from, Date to);

	List<USCase> findByStatesState(String state);

}
