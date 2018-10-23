package meli.mutants.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meli.mutants.detector.MutantDetector;
import meli.mutants.domain.Person;

@RestController
public class MutantController {
	
	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Person person) {
		
		HttpHeaders headers = new HttpHeaders();

		if(new MutantDetector().isMutant(person.getDna())) {
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
		}
 
    }
 

}

