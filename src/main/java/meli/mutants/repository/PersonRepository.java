package meli.mutants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import meli.mutants.domain.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
	
	

}
