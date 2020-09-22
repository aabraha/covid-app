package com.project.covidapp.rest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.covidapp.dto.State;
import com.project.covidapp.model.USCase;
import com.project.covidapp.service.CaseService;

@RestController
@RequestMapping("/api")
public class CaseController {
	@Autowired
	private CaseService caseService;
		
	// retrieve all cases
	@GetMapping("/cases")
	public List<USCase> getAllCases(){
		
		return caseService.getAllCases();
	}
	
	@GetMapping("/cases/{id}")
	public Optional<USCase> getCaseById(@PathVariable String id) {
		
		return caseService.getCaseById(id);
	}
		
	// 2015-09-26T01:30:00.000Z zonedDateTime
	@GetMapping(value="/cases/search", params= {"dateModified"}) 
	 public List<USCase> getCasesByDateModefied(@RequestParam("dateModified")
	  	  @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) 
	  		ZonedDateTime dateModified){ 

		  return caseService.getCasesByDateModified(dateModified); 
	  }
	 
	
	 @GetMapping(value="/cases/search", params= {"afterDate"}) 
	 public List<USCase> getCasesByDateModefiedAfter(@RequestParam("afterDate")
				  	  	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) 
				  		ZonedDateTime afterDate){ 
		  
		  return caseService.getCasesByDateModifiedAfter(afterDate); 
	  }
	 
	 @GetMapping(value="/cases/search", params= {"beforeDate"}) 
	 public List<USCase> getCasesByDateModefiedBefore(@RequestParam("beforeDate")
				  	  	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) 
				  		ZonedDateTime beforeDate){ 

		 return caseService.getCasesByDateModifiedBefore(beforeDate); 
	  }
	 
	 @GetMapping(value="/cases/search", params= {"from", "to"})
	 public List<USCase> getCasesByDateModefiedBetween(@RequestParam("from")
		@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) ZonedDateTime from,
		@RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) ZonedDateTime to){
		
		return caseService.getCasesByDateModifiedBetween(from, to);
	}
	
	 @GetMapping(value="/cases/search", params= {"state"})
	 public List<USCase> getCasesByState(@RequestParam("state") String state){
		 
		 return caseService.getCasesByState(state);
	 }
	 
	@DeleteMapping("/cases/{id}")
	public String deleteCaseById(@PathVariable String id) {
		
		caseService.deleteCaseById(id);
		return "deleted entry with id: " + id;
	}
	

}
