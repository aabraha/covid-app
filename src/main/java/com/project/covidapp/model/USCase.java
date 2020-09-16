package com.project.covidapp.model;

import java.util.Date;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.covidapp.dto.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Document
public class USCase {
	@Id
	private String id;
	
	@Indexed(unique = true)
	private Date lastUpdated;
	
	private List<State> states;

}
