package meli.mutants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import meli.mutants.domain.Human;

@Repository
public interface HumanRepository extends MongoRepository<Human, String> {
	
	public Long countByMutant(boolean isMutant); 

}
