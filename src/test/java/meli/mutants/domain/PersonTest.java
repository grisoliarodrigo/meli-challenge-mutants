package meli.mutants.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.Assert;
import meli.mutants.repository.PersonRepository;
import meli.mutants.service.PersonService;

public class PersonTest {
	
	@Mock PersonRepository personRepository;
	@InjectMocks PersonService personService = new PersonService();;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsMutant() {
		Person person = new Person();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		person.setDna(dna);
		personService.save(person);
		Assert.assertTrue(person.isMutant());
	}
	
	@Test
	public void testIsNotMutant() {
		Person person = new Person();
		String[] dna = {"GGGGCC","ATATAT","GCGCGC","ATATAT","GCGCGC","ATATAT"};
		person.setDna(dna);
		personService.save(person);
		Assert.assertFalse(person.isMutant());
	}
	
}


