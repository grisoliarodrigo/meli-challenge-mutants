package meli.mutants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import meli.mutants.domain.Human;
import meli.mutants.domain.Stats;
import meli.mutants.repository.HumanRepository;
import meli.mutants.util.detector.MutantDetector;

@Service
@EnableMongoRepositories(basePackages={"meli.mutants.repository"})
public class HumanService {
	
	@Autowired
	private HumanRepository humanRepository;
	
	public void save(Human human) {
		boolean isMutant = isMutant(human.getDna());
		human.setMutant(isMutant);
		humanRepository.save(human);
	}
	
	public Stats getStats() {
		Long mutantCount = humanRepository.countByMutant(true);
		Long humanCount = humanRepository.count();
		return new Stats(mutantCount, humanCount);
	}
	
	public boolean isMutant(String[] dna) {
		return new MutantDetector(dna).isMutant();
	}

}
