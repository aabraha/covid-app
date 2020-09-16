package com.project.covidapp.service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

@Component
public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {

	@Override
	public ZonedDateTime convert(Date value) {
		
		return value.toInstant().atZone(ZoneOffset.UTC);
	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

}
