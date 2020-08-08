package com.project.covidapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.project.covidapp.dto.ConsumedModel;

@SpringBootApplication
public class CovidAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidAppApplication.class, args);
		
		// auto consume data
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://www.cdc.gov/coronavirus/2019-ncov/cases-updates/new_cases_by_day.json";
		ConsumedModel dto = restTemplate.getForObject(url, ConsumedModel.class);
		
		//parse the data
		System.err.println("\n============the whole data object==========");
		System.out.println("the whole data: " + dto);
		
		System.out.println("\n============the columns field============");
		System.out.println("the columns:" + dto.getData().getColumns());
		
		System.out.println("\n=============the columns field list==============");
		System.out.println("the X part:\n" + dto.getData().getColumns().get(0));
		System.out.println("the Data1 part:\n" + dto.getData().getColumns().get(1));
		System.out.println("the Data2 part:\n" + dto.getData().getColumns().get(2));
		
		System.out.println("\n============the columns field inner list============");
		System.out.println("the X part:\n" + dto.getData().getColumns().get(0).get(0));
		System.out.println("the Data1 part:\n" + dto.getData().getColumns().get(1).get(0));
		System.out.println("the Data2 part:\n" + dto.getData().getColumns().get(2).get(0));

	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
