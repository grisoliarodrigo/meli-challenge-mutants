package meli.mutants.util.detector;

import java.util.Arrays;
import java.util.List;

import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class MutantDetector {

	static int SEQUENCE_LENGTH = 4;
	static int SEQUENCES_FOR_POSITIVE = 2;
	static int DNA_SIZE = 6;
	static List<Character> POSSIBLE_LETTERS = Arrays.asList('A','T','C','G');

	
	public boolean isMutant(String[] dna) {

		int foundSequences = 0;
		
		for (int i = 0; i < DNA_SIZE; i++) {
			
			for (int j = 0; j < DNA_SIZE; j++) {
				
				validateChar(dna[i].charAt(j));				
				Position pos = new Position(j, i);
				
				if(checkHorizontalSequence(pos, dna)) {
					foundSequences++;
					if(foundSequences >= SEQUENCES_FOR_POSITIVE) return true;
				}
				
				if(checkVerticalSequence(pos, dna)) {
					foundSequences++;
					if(foundSequences >= SEQUENCES_FOR_POSITIVE) return true;
				}
				
				if(checkDiagonalFowardSequence(pos, dna)) {
					foundSequences++;
					if(foundSequences >= SEQUENCES_FOR_POSITIVE) return true;
				}
				
				if(checkDiagonalBackwardSequence(pos, dna)) {
					foundSequences++;
					if(foundSequences >= SEQUENCES_FOR_POSITIVE) return true;
				}
				
			}
			
		}

		return false;
	}
	

	public boolean checkHorizontalSequence(Position initialPos, String[] dna) {
		
		if(!shouldCheckHorizontal(initialPos)) return false;
		
		String row = dna[initialPos.getY()];

		for (int i = initialPos.getX(); i < SEQUENCE_LENGTH - 1 + initialPos.getX(); i++) {
			char currentChar = row.charAt(i);
			char nextChar = row.charAt(i + 1);

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkVerticalSequence(Position initialPos, String[] dna) {
		
		if(!shouldCheckVertical(initialPos)) return false;

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {
			char currentChar = dna[i].charAt(initialPos.getX());
			char nextChar = dna[i + 1].charAt(initialPos.getX());

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkDiagonalFowardSequence(Position initialPos, String[] dna) {
		
		if(!shouldCheckDiagonalFoward(initialPos)) return false;

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {
			
			int currentX = initialPos.getX() + (i - initialPos.getY());
			
			char currentChar = dna[i].charAt(currentX);
			char nextChar = dna[i + 1].charAt(currentX + 1);

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkDiagonalBackwardSequence(Position initialPos, String[] dna) {
		
		if(!shouldCheckDiagonalBackward(initialPos)) return false;

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {
			
			int currentX = initialPos.getX() - (i - initialPos.getY());
			
			char currentChar = dna[i].charAt(currentX);
			char nextChar = dna[i + 1].charAt(currentX-1);

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}
	
	public boolean shouldCheckHorizontal(Position pos) {
		return pos.getX() + SEQUENCE_LENGTH <= DNA_SIZE;
	}
	
	public boolean shouldCheckVertical(Position pos) {
		return pos.getY() + SEQUENCE_LENGTH <= DNA_SIZE;
	}
	
	public boolean shouldCheckDiagonalFoward(Position pos) {
		return shouldCheckHorizontal(pos) && shouldCheckVertical(pos);
	}
	
	public boolean shouldCheckDiagonalBackward(Position pos) {
		return shouldCheckVertical(pos) && pos.getX() + 1 - SEQUENCE_LENGTH >= 0;
	}
	
	private void validateChar(char c) {
		if(!POSSIBLE_LETTERS.contains(c)) {
			throw new InvalidDNAException("Invalid Character");
		}
	}

}
