package com.project.covidapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.covidapp.dto.ConsumedModel;

@RestController
@RequestMapping("/api")
public class Consumer {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/data")
	public ConsumedModel getData(){
		restTemplate = new RestTemplate();
		String url = "https://www.cdc.gov/coronavirus/2019-ncov/cases-updates/new_cases_by_day.json";
		ConsumedModel dto = restTemplate.getForObject(url, ConsumedModel.class);
		System.err.println(dto.getDataObj().getColumns());
		return dto;
	}

}
