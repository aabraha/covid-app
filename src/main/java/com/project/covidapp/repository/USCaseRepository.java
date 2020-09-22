package com.project.covidapp.repository;

import java.util.Date;
import java.util.List;

import javax.naming.ldap.PagedResultsControl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.project.covidapp.model.USCase;

@Repository
public interface USCaseRepository extends PagingAndSortingRepository<USCase, String> {

	List<USCase> findByLastUpdated(Date date);

	List<USCase> findByLastUpdatedGreaterThan(Date date);

	List<USCase> findByLastUpdatedLessThan(Date date);

	List<USCase> findByLastUpdatedBetween(Date from, Date to);

	List<USCase> findByStatesState(String state);
	
	Page<USCase> findAll(Pageable pageable);

}
