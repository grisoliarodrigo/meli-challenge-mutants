package meli.mutants.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.Assert;
import meli.mutants.repository.PersonRepository;
import meli.mutants.service.PersonService;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class PersonServiceTest {
	
	@Mock PersonRepository personRepository;
	@InjectMocks PersonService personService = new PersonService();;
	
	String[] mutantDna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
	String[] notMutantDna = {"GGGGCC","ATATAT","GCGCGC","ATATAT","GCGCGC","ATATAT"};
	String[] invalidDna = {"Y"};

	Person person;
 	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockRepository();
		person = new Person();
	}
	
	@Test
	public void test_is_mutant() {
		person.setDna(mutantDna);
		personService.save(person);
		Assert.assertTrue(person.isMutant());
	}
	
	@Test
	public void test_is_not_mutant() {
		person.setDna(notMutantDna);
		personService.save(person);
		Assert.assertFalse(person.isMutant());
	}
	
	@Test(expected = InvalidDNAException.class)
	public void test_save_fails_with_invalid_dna() {
		person.setDna(invalidDna);
		personService.save(person);
	}
	
	@Test
	public void test_stats_mutants_count() {
		Stats stats = personService.getStats();
		Assert.assertEquals(new Long(50), stats.getMutantCount());
	}
	
	@Test
	public void test_stats_human_count() {
		Stats stats = personService.getStats();
		Assert.assertEquals(new Long(100), stats.getHumanCount());
	}
	
	@Test
	public void test_stats_ratio() {
		Stats stats = personService.getStats();
		Assert.assertEquals(0.5, stats.getRatio());
	}
	
	private void mockRepository() {
		Mockito.when(personRepository.count()).thenReturn((long) 100);
		Mockito.when(personRepository.countByMutant(true)).thenReturn((long) 50);
	}
	
}


