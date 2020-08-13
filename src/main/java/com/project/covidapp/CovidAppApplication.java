package com.project.covidapp;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.project.covidapp.dto.ConsumedModel;

@SpringBootApplication
public class CovidAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidAppApplication.class, args);
		
		// auto consume data
		/*
		 * RestTemplate restTemplate = new RestTemplate(); String url =
		 * "https://www.cdc.gov/coronavirus/2019-ncov/cases-updates/new_cases_by_day.json";
		 * ConsumedModel dto = restTemplate.getForObject(url, ConsumedModel.class);
		 * 
		 * //parse the data
		 * System.err.println("\n============the whole data object==========");
		 * System.out.println("the whole data: " + dto);
		 * 
		 * System.out.println("\n============the columns field============");
		 * System.out.println("the columns:" + dto.getData().getColumns());
		 * 
		 * System.out.println("\n=============the columns field list==============");
		 * System.out.println("the X part:\n" + dto.getData().getColumns().get(0));
		 * System.out.println("the Data1 part:\n" + dto.getData().getColumns().get(1));
		 * System.out.println("the Data2 part:\n" + dto.getData().getColumns().get(2));
		 * 
		 * System.out.println("\n============the columns field inner list============");
		 * System.out.println("the X part:\n" +
		 * dto.getData().getColumns().get(0).get(0));
		 * System.out.println("the Data1 part:\n" +
		 * dto.getData().getColumns().get(1).get(0));
		 * System.out.println("the Data2 part:\n" +
		 * dto.getData().getColumns().get(2).get(0));
		 */
	}
	
	//RestTemplate Bean
	// bypass the ssl validation
	@Bean
	public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
	        @Override
	        public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
	            return true;
	        }
	    };
	    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	}

}
