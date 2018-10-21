package meli.mutants;
import org.junit.Test;

import junit.framework.Assert;
import meli.mutants.detector.MutantDetector;
import meli.mutants.detector.Position;

public class MutantDetectorTest {

	@Test
	public void testHorizontalSequenceAtBeginning() {
		String[] dna = {"AAAAQW", "QWERTY", "ASDFGH", "ASDFGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkHorizontalSequence(new Position(0,0), dna));
	}
	
	@Test
	public void testHorizontalSequenceAtMiddleOfRow() {
		String[] dna = {"QAAAAQW", "QWERTY", "ASDFGH", "ASDFGH", "ASDFGH", "ASDFGH"};
		Assert.assertTrue(new MutantDetector().checkHorizontalSequence(new Position(1,0), dna));
	}
	
	@Test
	public void testHorizontalSequenceAtMiddleOfColumn() {
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
	public void testVerticalSequenceAtMiddleColumn() {
		String[] dna = {"EBAAQW", "BWERTY", "BWERTY", "BWERTY", "BWERTY", "AWERTY"};
		Assert.assertTrue(new MutantDetector().checkVerticalSequence(new Position(0,1), dna));
	}
	
	@Test
	public void testVerticalSequenceAtMiddleRow() {
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
		Assert.assertTrue(new MutantDetector().shouldChecklHorizontal(new Position(2,0)));
	}
	
	@Test
	public void testShouldNotCheckHorizontal() {
		Assert.assertFalse(new MutantDetector().shouldChecklHorizontal(new Position(3,0)));
	}
	
	@Test
	public void testShouldCheckVertical() {
		Assert.assertTrue(new MutantDetector().shouldChecklVertical(new Position(0,2)));
	}
	
	@Test
	public void testShouldNotCheckVertical() {
		Assert.assertFalse(new MutantDetector().shouldChecklVertical(new Position(0,3)));
	}
	
	@Test
	public void testShouldCheckDiagonalFoward() {
		Assert.assertTrue(new MutantDetector().shouldChecklDiagonalFoward(new Position(2,2)));
	}
	
	@Test
	public void testShouldNotCheckDiagonalFoward() {
		Assert.assertFalse(new MutantDetector().shouldChecklDiagonalFoward(new Position(2,3)));
	}
	
	@Test
	public void testShouldCheckDiagonalBackward() {
		Assert.assertTrue(new MutantDetector().shouldChecklDiagonalBackward(new Position(3,2)));
	}
	
	@Test
	public void testShouldNotCheckDiagonalBackward() {
		Assert.assertFalse(new MutantDetector().shouldChecklDiagonalBackward(new Position(2,2)));
	}
	

	
	

}
