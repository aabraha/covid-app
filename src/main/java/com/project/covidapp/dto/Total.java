package com.project.covidapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Total {

	private int confirmed;
	private int deaths;
	private int tests;
	private int daily_confirmed;
	private int recovered; 
	private int daily_deaths;
	private int critical;
	
}
