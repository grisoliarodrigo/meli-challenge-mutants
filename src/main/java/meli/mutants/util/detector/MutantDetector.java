package meli.mutants.util.detector;

import java.util.Arrays;
import java.util.List;

import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class MutantDetector {

	static int SEQUENCE_LENGTH = 4;
	static int SEQUENCES_FOR_POSITIVE = 2;
	static List<Character> POSSIBLE_LETTERS = Arrays.asList('A', 'T', 'C', 'G');

	private String[] dna;
	private int foundSequences;
	
	public MutantDetector(String[] dna) {
		this.dna = dna;
	}

	public boolean isMutant() {

		foundSequences = 0;
		validateTableSize();
		
		readRows();
		readColumns();
		readFowardDiagonals();
		readBackwardDiagonals();

		return foundSequences >= SEQUENCES_FOR_POSITIVE;
	}

	
	private void readRows() {
		
		for(int i = 0; i < dna.length; i++) {
			
			validateRow(i);
			validateCharAt(i, 0);
			int consecutive = 0;
			
			for(int j = 1; j < dna.length; j++) {
				
				char prevChar = getChar(i, j - 1);
				char currentChar = getChar(i, j);
				validateCharAt(i, j);
				
				if(currentChar == prevChar) {
					consecutive ++;
				} else {
					consecutive = 0;
				}
				
				if(consecutive == SEQUENCE_LENGTH - 1) {
					foundSequences ++;
					consecutive = 0;
				}
 				
			}
			
		}
		
	}

	private void readColumns() {
		
	}
	
	private void readBackwardDiagonals() {
		// TODO Auto-generated method stub
		
	}

	private void readFowardDiagonals() {
		// TODO Auto-generated method stub
		
	}

	
	private char getChar(int i, int j) {
		return dna[i].charAt(j);
	}

	private void validateCharAt(int i, int j) {

		char c = getChar(i, j);

		if (!POSSIBLE_LETTERS.contains(c)) {
			throw new InvalidDNAException(
					"Invalid Character '" + c + "' at position (" + j + "," + i + ").");
		}
	}

	private void validateRow(int i) {
		if (dna[i].length() != dna.length) {
			throw new InvalidDNAException("Row " + i + " does not match dna Array lenght. Table sould be NxN.");
		}
	}

	private void validateTableSize() {
		if (dna.length < SEQUENCE_LENGTH) {
			throw new InvalidDNAException("Table sould be at least " + SEQUENCE_LENGTH + "x" + SEQUENCE_LENGTH + ".");
		}
	}

}
