package meli.mutants.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import meli.mutants.domain.Person;
import meli.mutants.domain.Stats;
import meli.mutants.service.PersonService;

@RestController
public class MutantController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> isMutant(@RequestBody Person person) {
		
		personService.save(person);
		
		HttpHeaders headers = new HttpHeaders();

		if(person.isMutant()) {
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
		}
 
    }
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
    public Map<String, Object> getStats() {
		
		Stats stats = personService.getStats();
		
		HashMap<String, Object> statsMap = new HashMap<String, Object>();
	    statsMap.put("count_mutant_dna", stats.getMutantCount());
	    statsMap.put("count_human_dna", stats.getHumanCount());
	    statsMap.put("ratio", stats.getRatio());
	    
	    return statsMap;
 
    }

}