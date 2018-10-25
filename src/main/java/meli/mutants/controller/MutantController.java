package meli.mutants.controller;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> isMutant(@RequestBody Person person) {
		
		person.setMutant(new MutantDetector().isMutant(person.getDna()));
		personRepository.save(person);
		
		HttpHeaders headers = new HttpHeaders();

		if(person.isMutant()) {
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
		}
 
    }
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
    public Map<String, Object> getStats() {
		
		Long totalCount = personRepository.count();
		Long mutantsCount = personRepository.countByMutant(true);
		Long humansCount = totalCount - mutantsCount;
		Double ratio =  (double)mutantsCount / (double)totalCount;
		
		HashMap<String, Object> stats = new HashMap<String, Object>();
	    stats.put("count_mutant_dna", mutantsCount);
	    stats.put("count_human_dna", humansCount);
	    stats.put("ratio", ratio);
	    
	    return stats;
 
    }

}

