package com.project.covidapp.dto;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorldCase {

	private ZonedDateTime last_updated;
	private Category regions;
}
