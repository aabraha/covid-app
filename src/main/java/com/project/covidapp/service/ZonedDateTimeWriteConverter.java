package com.project.covidapp.service;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

@Component
public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {

	@Override
	public Date convert(ZonedDateTime value) {
		
		return Date.from(value.toInstant());
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
