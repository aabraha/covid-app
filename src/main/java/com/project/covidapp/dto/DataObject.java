package com.project.covidapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataObject {
	
	private String type;
	private String x;
	private String xFormat;
	//private List<Label> empty;
	private List<List<String>> columns;

}
