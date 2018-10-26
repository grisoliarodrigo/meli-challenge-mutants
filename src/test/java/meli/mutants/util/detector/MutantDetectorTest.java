package meli.mutants.util.detector;
import org.junit.Test;

import junit.framework.Assert;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class MutantDetectorTest {
	
	@Test
	public void testIsMutant() {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsNotMutant() {
		String[] dna = {"GGGGCC","ATATAT","GCGCGC","ATATAT","GCGCGC","ATATAT"};
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsMutantWithTwoSequences() {
		String[] dna = {"AAAAGA","CCGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsMutantWithTwoDiagonals() {
		String[] dna = {"ATGTGA","CATTGC","TTATTT","TGAAGG","GCGTCA","TCACTG"};
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsNotMutantWithOneDiagonal() {
		String[] dna = {"ATGGGA","CATTGC","TTATTT","TGAAGG","GCGTCA","TCACTG"};
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsMutantWithTwoDiagonalsInside() {
		String[] dna = {"GTGTGA","CATTGC","TTATTT","TGAAGG","GCGTAA","TCACTG"};
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsNotMutantOneHorizontalFiveLettersSequence() {
		String[] dna = {"ATATAT", "GGGGGC", "ATATAT", "GCGCGC", "ATATAT", "GCGCGC"};
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void oneVerticalFiveLettersSequenceIsNotMutant() {
		String[] dna = {"ATATAT", "ACGCGC", "ATATAT", "ACGCGC", "ATATAT", "GCGCGC"};
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void oneDiagonalFowardFiveLettersSequenceIsNotMutant() {
		String[] dna = {"ATATAT", "GAGCGC", "ATATAT", "GCGAGC", "ATATAT", "GCGCGC"};
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void oneDiagonaBackwardlFiveLettersSequenceIsNotMutant() {
		String[] dna = {"ATATAT", "GCGCTC", "ATATAT", "GCTCGC", "ATATAT", "GCGCGC"};
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}
	
	@Test
	public void testIsMutantWithOneSequenceOfFiveAndOneSequenceOfFour() {
		String[] dna = {"AAAAAT","GCGCGC","GTATAT","GCGCGC","GTATAT","CCGCGC"};
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}
	
	@Test(expected = InvalidDNAException.class)
	public void testInvalidTableSize() {
		String[] dna = {"AAA","GCG","GTA"};
		new MutantDetector(dna).isMutant();
	}
	
	@Test(expected = InvalidDNAException.class)
	public void testInvalidRowLength() {
		String rowOfFour = "AAAA";
		String[] dna = {"AAAAAT",rowOfFour,"GTATAT","GCGCGC","GTATAT","CCGCGC"};
		new MutantDetector(dna).isMutant();
	}
	
	@Test(expected = InvalidDNAException.class)
	public void testInvalidCharacter() {
		char x = 'X';
		String[] dna = {"AAAAAT","GCGCGG","GTATAT","GCGCGC","GTATAT",("CCGCG" + x)};
		new MutantDetector(dna).isMutant();
	}
	
}
