package com.project.covidapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.project.covidapp.dto.WorldCase;
import com.project.covidapp.model.USCase;
import com.project.covidapp.repository.ConsumedModelRepository;
import com.project.covidapp.repository.USCaseRepository;
import com.project.covidapp.service.ZonedDateTimeWriteConverter;

@EnableMongoRepositories(basePackageClasses = USCaseRepository.class)
@Configuration
public class MongodbConfig {
	
	@Autowired
	USCaseRepository usCaseRepository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private ZonedDateTimeWriteConverter dateObj;
	
	private String url = "https://cov19.cc/report.json";
	private WorldCase dto;
	private USCase usCase = new USCase();
	
		
	@Bean
	CommandLineRunner commonadLineRunner() {
	
		
		return strings->{

			 dto = restTemplate.getForObject(url, WorldCase.class);
			usCase.setLastUpdated(dateObj.convert(dto.getLast_updated()));
			usCase.setStates(dto.getRegions().getUnitedstates().getList());
				
			//usCaseRepository.insert(usCase);
		};
	}
	
	@Scheduled(fixedRateString="${fetchFrequencyInMili}" )
	public void publish() {
		//System.out.println("scheduled running");
		dto = restTemplate.getForObject(url, WorldCase.class);
		usCase.setLastUpdated(dateObj.convert(dto.getLast_updated()));
		usCase.setStates(dto.getRegions().getUnitedstates().getList());
		//System.out.println("before mongo insert");
		usCaseRepository.save(usCase);

	}
}
