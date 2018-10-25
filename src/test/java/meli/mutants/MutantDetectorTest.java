package meli.mutants;
import org.junit.Test;

import junit.framework.Assert;
import meli.mutants.util.detector.MutantDetector;
import meli.mutants.util.detector.Position;
import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class MutantDetectorTest {

	@Test
	public void testHorizontalSequenceAtBeginning() {
		String[] dna = {"AAAAQW", "QWERTY", "ASDFGH", "ASDFGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkHorizontalSequence(new Position(0,0), dna));
	}
	
	@Test
	public void testHorizontalSequenceAtInnerColumn() {
		String[] dna = {"QAAAAQW", "QWERTY", "ASDFGH", "ASDFGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkHorizontalSequence(new Position(1,0), dna));
	}
	
	@Test
	public void testHorizontalSequenceAtInnerRow() {
		String[] dna = {"QAABAQ", "QAAAAQ", "ASDFGH", "ASDFGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkHorizontalSequence(new Position(1,1), dna));
	}
	
	@Test
	public void testHorizontalSequenceOfThreeFails() {
		String[] dna = {"AEABAQ", "QAAAAQ", "ASDFGH", "ASDFGH", "ASDFGH", "ASDFGH"};
		Assert.assertFalse(new MutantDetector().checkHorizontalSequence(new Position(0,0), dna));
	}
	
	@Test
	public void testVerticalSequenceAtBeginning() {
		String[] dna = {"BBAAQW", "BWERTY", "BWERTY", "BWERTY", "AWERTY", "AWERTY"};
		Assert.assertTrue(new MutantDetector().checkVerticalSequence(new Position(0,0), dna));
	}
	
	@Test
	public void testVerticalSequenceAtInnerColumn() {
		String[] dna = {"EBAAQW", "BWERTY", "BWERTY", "BWERTY", "BWERTY", "AWERTY"};
		Assert.assertTrue(new MutantDetector().checkVerticalSequence(new Position(0,1), dna));
	}
	
	@Test
	public void testVerticalSequenceAtInnerRow() {
		String[] dna = {"EWAAQW", "BWERTY", "BWERTY", "BWERTY", "BFERTY", "AFERTY"};
		Assert.assertTrue(new MutantDetector().checkVerticalSequence(new Position(1,0), dna));
	}
	
	@Test
	public void testDiagonalFowardSequence() {
		String[] dna = {"TAAAQW", "QTERTY", "ASTFGH", "ASDTGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkDiagonalFowardSequence(new Position(0,0), dna));
	}
	
	@Test
	public void testDiagonalBackwardSequence() {
		String[] dna = {"TAACQW", "QTCRTY", "ACTFGH", "CSDTGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkDiagonalBackwardSequence(new Position(3,0), dna));
	}
	
	@Test
	public void testShouldCheckHorizontal() {
		Assert.assertTrue(new MutantDetector().shouldCheckHorizontal(new Position(2,0)));
	}
	
	@Test
	public void testShouldNotCheckHorizontal() {
		Assert.assertFalse(new MutantDetector().shouldCheckHorizontal(new Position(3,0)));
	}
	
	@Test
	public void testShouldCheckVertical() {
		Assert.assertTrue(new MutantDetector().shouldCheckVertical(new Position(0,2)));
	}
	
	@Test
	public void testShouldNotCheckVertical() {
		Assert.assertFalse(new MutantDetector().shouldCheckVertical(new Position(0,3)));
	}
	
	@Test
	public void testShouldCheckDiagonalFoward() {
		Assert.assertTrue(new MutantDetector().shouldCheckDiagonalFoward(new Position(2,2)));
	}
	
	@Test
	public void testShouldNotCheckDiagonalFoward() {
		Assert.assertFalse(new MutantDetector().shouldCheckDiagonalFoward(new Position(2,3)));
	}
	
	@Test
	public void testShouldCheckDiagonalBackward() {
		Assert.assertTrue(new MutantDetector().shouldCheckDiagonalBackward(new Position(3,2)));
	}
	
	@Test
	public void testShouldNotCheckDiagonalBackward() {
		Assert.assertFalse(new MutantDetector().shouldCheckDiagonalBackward(new Position(2,2)));
	}
	
	@Test
	public void testIsMutant() {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(new MutantDetector().isMutant(dna));
	}
	
	@Test
	public void testIsNotMutant() {
		String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGAAGG","CACCTA","TCACTG"};
		Assert.assertFalse(new MutantDetector().isMutant(dna));
	}
	
	@Test
	public void testIsMutantWithTwoSequences() {
		String[] dna = {"AAAAGA","CCGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(new MutantDetector().isMutant(dna));
	}
	
	@Test
	public void testIsMutantWithTwoDiagonals() {
		String[] dna = {"ATGTGA","CATTGC","TTATTT","TGAAGG","GCGTCA","TCACTG"};
		Assert.assertTrue(new MutantDetector().isMutant(dna));
	}
	
	@Test
	public void testIsNotMutantWithOneDiagonal() {
		String[] dna = {"ATGGGA","CATTGC","TTATTT","TGAAGG","GCGTCA","TCACTG"};
		Assert.assertFalse(new MutantDetector().isMutant(dna));
	}
	
	@Test
	public void testIsMutantWithTwoDiagonalsInside() {
		String[] dna = {"GTGTGA","CATTGC","TTATTT","TGAAGG","GCGTAA","TCACTG"};
		Assert.assertTrue(new MutantDetector().isMutant(dna));
	}
	
	@Test (expected = InvalidDNAException.class)
	public void testInvalidCharacterFails() {
		String[] dna = {"PAAAAA","PAAAAA","PAAAAA","PAAAAA","PAAAAA","PAAAAA"};
		new MutantDetector().isMutant(dna);
	}
	
}
