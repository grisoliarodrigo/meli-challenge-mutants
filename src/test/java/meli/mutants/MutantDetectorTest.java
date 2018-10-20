package meli.mutants;

import org.junit.Test;

import junit.framework.Assert;
import meli.mutants.services.MutantDetectorService;

public class MutantDetectorTest {

	@Test
	public void test() {
		String[] dna = {};
		Assert.assertFalse(new MutantDetectorService().isMutant(dna));
	}

}
