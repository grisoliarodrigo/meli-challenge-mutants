package meli.mutants.util.detector;

import org.junit.Test;

import junit.framework.Assert;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class MutantDetectorTest {

	@Test
	public void test_is_mutant() {
		String[] dna = { "TAAAAT", "GGCCCC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_not_mutant() {
		String[] dna = { "GGGGCC", "AAATAT", "GCGCGC", "ATATAT", "GCGCGC", "ATATAT" };
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_one_horizontal_and_one_vertical_sequence() {
		String[] dna = { "AAAAGA", "CCGTGC", "TTATGT", "AGAAGG", "GCCCTA", "TCACTG" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_diagonals() {
		String[] dna = { "ATGTGA", "CATTGC", "TTATTT", "TGAAGG", "GCGTCA", "TCACTG" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_not_mutant_with_one_diagonal() {
		String[] dna = { "ATGGGA", "CATTGC", "TTATTT", "TGAAGG", "GCGTCA", "TCACTG" };
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_diagonals_inside() {
		String[] dna = { "GTGTGA", "CATTGC", "TTATTT", "TGAAGG", "GCGTAA", "TCACTG" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_not_mutant_with_one_horizontal_five_letter_sequence() {
		String[] dna = { "ATATAT", "GGGGGC", "ATATAT", "GCGCGC", "ATATAT", "GCGCGC" };
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_not_mutant_with_one_vertical_five_letter_sequence() {
		String[] dna = { "ATATAT", "ACGCGC", "ATATAT", "ACGCGC", "ATATAT", "GCGCGC" };
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_not_mutant_with_one_foward_diagonal_five_letter_sequence() {
		String[] dna = { "ATATAT", "GAGCGC", "ATATAT", "GCGAGC", "ATATAT", "GCGCGC" };
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_not_mutant_with_one_backward_diagonal_five_letter_sequence() {
		String[] dna = { "ATATAT", "GCGCTC", "ATATAT", "GCTCGC", "ATATAT", "GCGCGC" };
		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_one_sequence_of_five_and_one_sequence_of_four() {
		String[] dna = { "AAAAAT", "GCGCGC", "GTATAT", "GCGCGC", "GTATAT", "CCGCGC" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test(expected = InvalidDNAException.class)
	public void test_invalid_table_size() {
		String[] dna = { "AAA", "GCG", "GTA" };
		new MutantDetector(dna).isMutant();
	}

	@Test(expected = InvalidDNAException.class)
	public void test_invalid_row_length() {
		String rowOfFour = "AAAA";
		String[] dna = { "AAAAAT", rowOfFour, "GTATAT", "GCGCGC", "GTATAT", "CCGCGC" };
		new MutantDetector(dna).isMutant();
	}

	@Test(expected = InvalidDNAException.class)
	public void test_invalid_character() {
		char x = 'X';
		String[] dna = { "AAAAAT", "GCGCGG", "GTATAT", "GCGCGC", "GTATAT", ("CCGCG" + x) };
		new MutantDetector(dna).isMutant();
	}

	@Test
	public void test_is_not_mutant_with_nine_row_table() {
		String[] dna = { "ATATATATA", "CGCGCGCGC", "ATATATATA", "CGCGCGCGC", "ATATATATA", "CGCGCGCGC", "ATATATATA",
				"CGCGCGCGC", "ATATATATA" };

		Assert.assertFalse(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_consecutive_horizontal() {
		String[] dna = { "ATATATATA", "CGGGGGGGG", "ATATATATA", "CGCGCGCGC", "ATATATATA", "CGCGCGCGC", "ATATATATA",
				"CGCGCGCGC", "ATATATATA" };

		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_consecutive_vertical() {
		String[] dna = { "ATATATATA", "CGCGCGCGC", "ATATATAGA", "CGCGCGCGC", "ATATATAGA", "CGCGCGCGC", "ATATATAGA",
				"CGCGCGCGC", "ATATATAGA" };

		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_consecutive_diagonals() {
		String[] dna = { "ATATATATA", "CGCGCGCAC", "ATATATATA", "CGCGCACGC", "ATATATATA", "CGCACGCGT", "ATATATATA",
				"CACGCGTGC", "ATATATATA" };

		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_backward_diagonals() {
		String[] dna = { "GCGAGC", "ATATAT", "GAGCTC", "ATATAT", "GCTCGC", "ATATAT" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}

	@Test
	public void test_is_mutant_with_two_foward_diagonals() {
		String[] dna = { "GCGCGC", "ATAGAT", "GAGCGC", "ATATAG", "GCGAGC", "ATATAT" };
		Assert.assertTrue(new MutantDetector(dna).isMutant());
	}
	
}
