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
		readUpFowardDiagonals();
		readDownFowardDiagonals();

		return foundSequences >= SEQUENCES_FOR_POSITIVE;
	}

	private void readRows() {

		for (int i = 0; i < dna.length; i++) {

			validateRow(i);
			consecutive = 0;

			for (int j = 0; j < dna.length - 1; j++) {
				validateCharAt(i, j + 1);
				readPosition(i, j, getCharAt(i, j + 1));
			}

		}

	}

	private void readColumns() {

		int j = 0;

		while (keepChecking() && j < dna.length) {

			int i = 0;
			consecutive = 0;

			while (keepChecking() && i < dna.length - 1) {
				readPosition(i, j, getCharAt(i + 1, j));
				i++;
			}

			j++;
		}

	}

	private void readUpFowardDiagonals() {
		
		int row = SEQUENCE_LENGTH - 1;
		
		while(keepChecking() && row < dna.length) {
			
			int i = row;
			int j = 0;
			
			while(keepChecking() && i > 0) {
				readPosition(i, j, getCharAt(i -1, j + 1));
				i--;
				j++;
			}
			
			row++;
			
		}
		
		
		int column = 1;
		
		while(keepChecking() && column < (dna.length - SEQUENCE_LENGTH + 1)) {
			
			int i = dna.length - 1;
			int j = column;
			
			while(keepChecking() && j < dna.length - 1) {
				readPosition(i, j, getCharAt(i - 1, j + 1));
				i--; 
				j++;
			}
			
			column++;
			
		}

	}

	private void readDownFowardDiagonals() {
		
		int row = dna.length - SEQUENCE_LENGTH;

		while(keepChecking() && row >= 0) {
			
			int i = row;
			int j = 0;

			while(keepChecking() && i < dna.length - 1) {
				readPosition(i, j, getCharAt(i + 1, j + 1));
				i++;
				j++;
			}
			
			row--;
		}
		
		
		int column = 1;
		
		while(keepChecking() && column < (dna.length - SEQUENCE_LENGTH + 1)) {
			
			int i = 0; int j = column;
			
			while(keepChecking() && j < dna.length - 1) {
				readPosition(i, j, getCharAt(i + 1, j + 1));
				i++; 
				j++;
			}
			
			column++;
			
		}
	}

	private void readPosition(int i, int j, char nextChar) {

		char currentChar = getCharAt(i, j);

		if (currentChar == nextChar) {
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

	private char getCharAt(int i, int j) {
		return dna[i].charAt(j);
	}

	private void validateCharAt(int i, int j) {

		char c = getCharAt(i, j);

		if (!POSSIBLE_LETTERS.contains(c)) {
			throw new InvalidDNAException("Invalid Character '" + c + "' at position (" + j + "," + i + ").");
		}
	}

	private void validateRow(int i) {
		if (dna[i].length() != dna.length) {
			throw new InvalidDNAException("Row " + i + " does not match dna array lenght. Table sould be NxN.");
		}
	}

	private void validateTableSize() {
		if (dna.length < SEQUENCE_LENGTH) {
			throw new InvalidDNAException("Table sould be at least " + SEQUENCE_LENGTH + "x" + SEQUENCE_LENGTH + ".");
		}
	}

}
