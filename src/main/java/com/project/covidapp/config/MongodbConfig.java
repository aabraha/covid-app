package com.project.covidapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.project.covidapp.dto.ConsumedModel;
import com.project.covidapp.repository.ConsumedModelRepository;

@EnableMongoRepositories(basePackageClasses = ConsumedModelRepository.class)
@Configuration
public class MongodbConfig {
	@Bean
		CommandLineRunner commonadLineRunner(
					ConsumedModelRepository consumedModelRepository,
					RestTemplate restTemplate) {
			
			String url = "https://www.cdc.gov/coronavirus/2019-ncov/cases-updates/new_cases_by_day.json";
			
			return strings->{
				ConsumedModel dto = restTemplate.getForObject(url, ConsumedModel.class);
				consumedModelRepository.save(dto);
			};
		}

}
