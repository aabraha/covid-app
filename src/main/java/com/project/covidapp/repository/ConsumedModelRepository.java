package com.project.covidapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.covidapp.dto.ConsumedModel;

public interface ConsumedModelRepository extends MongoRepository<ConsumedModel, Integer> {

}
