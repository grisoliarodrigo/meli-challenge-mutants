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
import meli.mutants.domain.Human;
import meli.mutants.domain.Stats;
import meli.mutants.service.HumanService;

public class MutantControllerTest {

		
		@Mock HumanService humanService;
		@InjectMocks MutantController humanController = new MutantController();

		Human human;
	 	
		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);
			mockHumanRepository();
			human = new Human();
		}
		
		@Test
		public void test_is_mutant() {
			human.setMutant(true);
			ResponseEntity<?> response = humanController.isMutant(human);
			Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK), response);
		}
		
		@Test
		public void test_is_not_mutant() {
			human.setMutant(false);
			ResponseEntity<?> response = humanController.isMutant(human);
			Assert.assertEquals(new ResponseEntity<>(HttpStatus.FORBIDDEN), response);
		}
		
		@Test
		public void test_stats() {
			Assert.assertEquals(0.4, humanController.getStats().getRatio());
		}
		
		private void mockHumanRepository() {
			Mockito.when(humanService.getStats()).thenReturn(new Stats((long)40, (long)100));
		}
		
	}


