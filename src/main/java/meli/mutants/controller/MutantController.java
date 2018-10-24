package meli.mutants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meli.mutants.detector.MutantDetector;
import meli.mutants.domain.Person;
import meli.mutants.repository.PersonRepository;

@RestController
@EnableMongoRepositories(basePackages={"meli.mutants.repository"})
public class MutantController {
	
	@Autowired
	private PersonRepository personRepository;	
	
	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Person person) {
		
		person.setMutant(new MutantDetector().isMutant(person.getDna()));
		personRepository.save(person);
		
		HttpHeaders headers = new HttpHeaders();

		if(person.isMutant()) {
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
		}
 
    }

}

