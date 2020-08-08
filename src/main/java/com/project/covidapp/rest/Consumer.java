package com.project.covidapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.covidapp.dto.ConsumedModel;
import com.project.covidapp.repository.ConsumedModelRepository;

@RestController
@RequestMapping("/api")
public class Consumer {
	@Autowired
	ConsumedModelRepository consumedModelRepository;
		
	@RequestMapping("/data")
	public List<ConsumedModel> getData(){
		
		return consumedModelRepository.findAll();
	}

}
