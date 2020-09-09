package com.project.covidapp.config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.security.cert.X509Certificate;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.project.covidapp.dto.WorldCase;
import com.project.covidapp.model.USCase;
import com.project.covidapp.repository.USCaseRepository;
import com.project.covidapp.repository.ConsumedModelRepository;

@EnableMongoRepositories(basePackageClasses = USCaseRepository.class)
@Configuration
public class MongodbConfig {
	@Bean
		CommandLineRunner commonadLineRunner(
					ConsumedModelRepository consumedModelRepository,
					USCaseRepository usCaseRepository,
					RestTemplate restTemplate) {
		
			String url = "https://cov19.cc/report.json";
			return strings->{

				WorldCase dto = restTemplate.getForObject(url, WorldCase.class);
				USCase theCase = new USCase();
				theCase.setLast_updated(dto.getLast_updated());
				theCase.setStates(dto.getRegions().getUnitedstates().getList());
				
				System.err.println(theCase);
				System.err.println("hello:" + url);
				System.err.println(dto.getRegions().getUnitedstates().getList());
				//check last_updated is already inserted
				
				usCaseRepository.insert(theCase);
				//consumedModelRepository.save(dto);

			};
		}
	
	



}
