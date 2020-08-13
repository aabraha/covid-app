package com.project.covidapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class State {

	private String state;
	private String country;
	private int confirmed;
	private int deaths;
	private int recovered;
	private double Incidence_Rate;
	//private double Case-Fatality_Ratio;
	private Date last_update;
	private String country_code;
	private int daily_confirmed;
	private int daily_deaths;
	private int critical;
	private int tests;

}
