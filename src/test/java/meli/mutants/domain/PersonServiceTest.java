package meli.mutants.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	
}


