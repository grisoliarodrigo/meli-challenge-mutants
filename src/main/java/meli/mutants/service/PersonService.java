package meli.mutants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import meli.mutants.domain.Person;
import meli.mutants.domain.Stats;
import meli.mutants.repository.PersonRepository;
import meli.mutants.util.detector.MutantDetector;

@Service
@EnableMongoRepositories(basePackages={"meli.mutants.repository"})
public class PersonService {
	
	@Autowired
	public PersonRepository personRepository;
	
	public void save(Person person) {
		boolean isMutant = isMutant(person.getDna());
		person.setMutant(isMutant);
		personRepository.save(person);
	}
	
	public Stats getStats() {
		Long mutantCount = personRepository.countByMutant(true);
		Long humanCount = personRepository.count();
		return new Stats(mutantCount, humanCount);
	}
	
	public boolean isMutant(String[] dna) {
		return new MutantDetector(dna).isMutant();
	}

}
