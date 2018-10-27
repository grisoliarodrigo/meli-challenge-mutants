package meli.mutants.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meli.mutants.domain.Person;
import meli.mutants.domain.Stats;
import meli.mutants.service.PersonService;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

@RestController
public class MutantController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> isMutant(@RequestBody Person person) {
		
		try {
			personService.save(person);
		} catch(InvalidDNAException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		if(person.isMutant()) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
 
    }
	
	@RequestMapping(value = "/stats")
    public Stats getStats() {
	    return personService.getStats();
    }
	
	@RequestMapping(value = "/")
    public Map<String, String> APIstatus() {
		
		Map<String, String> response = new HashMap<String, String>();
		
		response.put("status", "OK");
		response.put("version", "0.0.2");
		
		return response;
    }

}