package meli.mutants.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import junit.framework.Assert;
import meli.mutants.domain.Person;
import meli.mutants.domain.Stats;
import meli.mutants.service.PersonService;

public class MutantControllerTest {

		
		@Mock PersonService personService;
		@InjectMocks MutantController personController = new MutantController();

		Person person;
	 	
		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);
			mockPersonRepository();
			person = new Person();
		}
		
		@Test
		public void test_is_mutant() {
			person.setMutant(true);
			ResponseEntity<?> response = personController.isMutant(person);
			Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK), response);
		}
		
		@Test
		public void test_is_not_mutant() {
			person.setMutant(false);
			ResponseEntity<?> response = personController.isMutant(person);
			Assert.assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), response);
		}
		
		@Test
		public void test_stats() {
			Assert.assertEquals(0.4, personController.getStats().getRatio());
		}
		
		private void mockPersonRepository() {
			Mockito.when(personService.getStats()).thenReturn(new Stats((long)40, (long)100));
		}
		
	}


