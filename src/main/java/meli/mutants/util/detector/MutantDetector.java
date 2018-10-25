package meli.mutants.util.detector;

import java.util.Arrays;
import java.util.List;

import meli.mutants.util.detector.exceptions.InvalidDNAException;

public class MutantDetector {

	static int SEQUENCE_LENGTH = 4;
	static int SEQUENCES_FOR_POSITIVE = 2;
	static List<Character> POSSIBLE_LETTERS = Arrays.asList('A', 'T', 'C', 'G');

	private String[] dna;
	private int foundSequences = 0;

	public MutantDetector(String[] dna) {
		this.dna = dna;
	}

	public boolean isMutant() {
		try {
			return doIsMutant();
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidDNAException("Invalid table size.");
		} catch (StringIndexOutOfBoundsException e) {
			throw new InvalidDNAException("Invalid row size.");
		}
	}

	public boolean doIsMutant() {

		for (int i = 0; i < dna.length; i++) {

			for (int j = 0; j < dna.length; j++) {

				Position pos = new Position(j, i);

				if (checkHorizontalSequence(pos) && foundSequences >= SEQUENCES_FOR_POSITIVE) {
					return true;
				}

				if (checkVerticalSequence(pos) && foundSequences >= SEQUENCES_FOR_POSITIVE) {
					return true;
				}

				if (checkDiagonalFowardSequence(pos) && foundSequences >= SEQUENCES_FOR_POSITIVE) {
					return true;
				}

				if (checkDiagonalBackwardSequence(pos) && foundSequences >= SEQUENCES_FOR_POSITIVE) {
					return true;
				}

			}

		}

		return false;
	}

	private boolean checkHorizontalSequence(Position initialPos) {

		if (!shouldCheckHorizontal(initialPos)) {
			return false;
		}

		for (int i = initialPos.getX(); i < SEQUENCE_LENGTH - 1 + initialPos.getX(); i++) {
			char currentChar = dna[initialPos.getY()].charAt(i);
			char nextChar = dna[initialPos.getY()].charAt(i + 1);

			if (!(POSSIBLE_LETTERS.contains(currentChar) && currentChar == nextChar)) {
				return false;
			}
		}

		foundSequences++;
		return true;
	}

	private boolean checkVerticalSequence(Position initialPos) {

		if (!shouldCheckVertical(initialPos)) {
			return false;
		}

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {

			char currentChar = dna[i].charAt(initialPos.getX());
			char nextChar = dna[i + 1].charAt(initialPos.getX());

			if (!(POSSIBLE_LETTERS.contains(currentChar) && currentChar == nextChar)) {
				return false;
			}
		}

		foundSequences++;
		return true;
	}

	private boolean checkDiagonalFowardSequence(Position initialPos) {

		if (!shouldCheckDiagonalFoward(initialPos)) {
			return false;
		}

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {

			int currentX = initialPos.getX() + (i - initialPos.getY());

			char currentChar = dna[i].charAt(currentX);
			char nextChar = dna[i + 1].charAt(currentX + 1);

			if (!(POSSIBLE_LETTERS.contains(currentChar) && currentChar == nextChar)) {
				return false;
			}
		}

		foundSequences++;
		return true;
	}

	private boolean checkDiagonalBackwardSequence(Position initialPos) {

		if (!shouldCheckDiagonalBackward(initialPos)) {
			return false;
		}

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {

			int currentX = initialPos.getX() - (i - initialPos.getY());

			char currentChar = dna[i].charAt(currentX);
			char nextChar = dna[i + 1].charAt(currentX - 1);

			if (!(POSSIBLE_LETTERS.contains(currentChar) && currentChar == nextChar)) {
				return false;
			}
		}

		foundSequences++;
		return true;
	}

	private boolean shouldCheckHorizontal(Position pos) {
		Position prevPos = new Position(pos.getX() - 1, pos.getY());
		return pos.getX() + SEQUENCE_LENGTH <= dna.length && !prevCharIsEquals(pos, prevPos);
	}

	private boolean shouldCheckVertical(Position pos) {
		Position prevPos = new Position(pos.getX(), pos.getY() - 1);
		return pos.getY() + SEQUENCE_LENGTH <= dna.length && !prevCharIsEquals(pos, prevPos);
	}

	private boolean shouldCheckDiagonalFoward(Position pos) {
		Position prevPos = new Position(pos.getX() - 1, pos.getY() - 1);

		return pos.getX() + SEQUENCE_LENGTH <= dna.length && pos.getY() + SEQUENCE_LENGTH <= dna.length
				&& !prevCharIsEquals(pos, prevPos);
	}

	private boolean shouldCheckDiagonalBackward(Position pos) {

		Position prevPos = new Position(pos.getX() + 1, pos.getY() - 1);

		return pos.getY() + SEQUENCE_LENGTH <= dna.length && pos.getX() + 1 - SEQUENCE_LENGTH >= 0
				&& !prevCharIsEquals(pos, prevPos);
	}

	private boolean prevCharIsEquals(Position currentPos, Position prevPos) {

		if (!prevPos.isValid(dna.length)) {
			return false;
		}

		return getChar(currentPos) == getChar(prevPos);

	}

	private char getChar(Position pos) {
		return dna[pos.getY()].charAt(pos.getX());
	}

}
