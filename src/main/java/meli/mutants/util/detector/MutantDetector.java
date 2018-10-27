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
	private int consecutive = 0;

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

		for (int i = 0; i < dna.length; i++) {

			validateRow(i);
			validateCharAt(i, 0);
			consecutive = 0;

			for (int j = 1; j < dna.length; j++) {
				validateCharAt(i, j);
				readPosition(i, j, getChar(i, j - 1));
			}

		}

	}

	private void readColumns() {
		
		int j = 0;
		int i = 1;
		
		while(keepChecking() && j < dna.length) {
			
			i = 1;
			consecutive = 0;
			
			while(keepChecking() && i < dna.length) {
				readPosition(i, j, getChar(i - 1, j));
				i++;
			}
			
			j++;
		}
		
	}

	private void readBackwardDiagonals() {
		// TODO Auto-generated method stub

	}

	private void readFowardDiagonals() {
		// TODO Auto-generated method stub

	}
	
	private void readPosition(int i, int j, char prevChar) {

		char currentChar = getChar(i, j);

		if (currentChar == prevChar) {
			consecutive++;
		} else {
			consecutive = 0;
		}

		if (consecutive == SEQUENCE_LENGTH - 1) {
			foundSequences++;
			consecutive = 0;
		}
	}
	
	private boolean keepChecking() {
		return foundSequences < SEQUENCES_FOR_POSITIVE;
	}

	private char getChar(int i, int j) {
		return dna[i].charAt(j);
	}

	private void validateCharAt(int i, int j) {

		char c = getChar(i, j);

		if (!POSSIBLE_LETTERS.contains(c)) {
			throw new InvalidDNAException("Invalid Character '" + c + "' at position (" + j + "," + i + ").");
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
