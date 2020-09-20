package com.project.covidapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class USCaseErrorResponse {
	
	private int status;
	private String message;
	private long timestamp;

}
