package com.project.covidapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.covidapp.dto.WorldCase;

public interface ConsumedModelRepository extends MongoRepository<WorldCase, Integer> {

}
