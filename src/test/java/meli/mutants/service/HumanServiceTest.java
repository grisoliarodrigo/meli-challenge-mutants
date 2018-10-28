package meli.mutants.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.Assert;
import meli.mutants.domain.Human;
import meli.mutants.domain.Stats;
import meli.mutants.repository.HumanRepository;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class HumanServiceTest {
	
	@Mock HumanRepository humanRepository;
	@InjectMocks HumanService humanService = new HumanService();;
	
	String[] mutantDna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
	String[] notMutantDna = {"GGGGCC","ATATAT","GCGCGC","ATATAT","GCGCGC","ATATAT"};
	String[] invalidDna = {"A"};

	Human human;
 	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockHumanRepository();
		human = new Human();
	}
	
	@Test
	public void test_is_mutant() {
		human.setDna(mutantDna);
		humanService.save(human);
		Assert.assertTrue(human.isMutant());
	}
	
	@Test
	public void test_is_not_mutant() {
		human.setDna(notMutantDna);
		humanService.save(human);
		Assert.assertFalse(human.isMutant());
	}
	
	@Test(expected = InvalidDNAException.class)
	public void test_save_fails_with_invalid_dna() {
		human.setDna(invalidDna);
		humanService.save(human);
	}
	
	@Test
	public void test_stats_mutants_count() {
		Stats stats = humanService.getStats();
		Assert.assertEquals(new Long(50), stats.getCountMutantDna());
	}
	
	@Test
	public void test_stats_human_count() {
		Stats stats = humanService.getStats();
		Assert.assertEquals(new Long(100), stats.getCountHumanDna());
	}
	
	@Test
	public void test_stats_ratio() {
		Stats stats = humanService.getStats();
		Assert.assertEquals(0.5, stats.getRatio());
	}
	
	private void mockHumanRepository() {
		Mockito.when(humanRepository.count()).thenReturn((long) 100);
		Mockito.when(humanRepository.countByMutant(true)).thenReturn((long) 50);
	}
	
}


