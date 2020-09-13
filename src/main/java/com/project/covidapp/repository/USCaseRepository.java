package com.project.covidapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.covidapp.model.USCase;

@Repository
public interface USCaseRepository extends MongoRepository<USCase, String> {

	List<USCase> findByLastUpdated(Date dateModified);

}
