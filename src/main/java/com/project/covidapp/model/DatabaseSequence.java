package com.project.covidapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString

@Document(collection="database_sequences")
public class DatabaseSequence {
	
	@Id
	private String id;
	
	private long seq;
	

}
