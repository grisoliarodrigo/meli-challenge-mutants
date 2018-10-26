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

		for (int i = 0; i < dna.length; i++) {

			validateRowSize(i);

			for (int j = 0; j < dna.length; j++) {

				Position pos = new Position(j, i);
				validateCharAt(pos);

				checkHorizontalSequence(pos);
				checkVerticalSequence(pos);
				checkDiagonalFowardSequence(pos);
				checkDiagonalBackwardSequence(pos);

			}

		}

		return foundSequences >= SEQUENCES_FOR_POSITIVE;
	}

	private void checkHorizontalSequence(Position initialPos) {

		if (!shouldCheckHorizontal(initialPos)) {
			return;
		}

		for (int i = initialPos.getX(); i < SEQUENCE_LENGTH - 1 + initialPos.getX(); i++) {
			char currentChar = getChar(i, initialPos.getY());
			char nextChar = getChar(i + 1, initialPos.getY());

			if (currentChar != nextChar) {
				return;
			}
		}

		foundSequences++;
	}

	private void checkVerticalSequence(Position initialPos) {

		if (!shouldCheckVertical(initialPos)) {
			return;
		}

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {

			validateRowSize(i + 1);
			char currentChar = getChar(initialPos.getX(), i);
			char nextChar = getChar(initialPos.getX(), i + 1);

			if (currentChar != nextChar) {
				return;
			}
		}

		foundSequences++;
	}

	private void checkDiagonalFowardSequence(Position initialPos) {

		if (!shouldCheckDiagonalFoward(initialPos)) {
			return;
		}

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {

			validateRowSize(i + 1);
			int currentX = initialPos.getX() + (i - initialPos.getY());
			char currentChar = getChar(currentX, i);
			char nextChar = getChar(currentX + 1, i + 1);

			if (currentChar != nextChar) {
				return;
			}
		}

		foundSequences++;
	}

	private void checkDiagonalBackwardSequence(Position initialPos) {

		if (!shouldCheckDiagonalBackward(initialPos)) {
			return;
		}

		for (int i = initialPos.getY(); i < SEQUENCE_LENGTH - 1 + initialPos.getY(); i++) {
			validateRowSize(i + 1);
			int currentX = initialPos.getX() - (i - initialPos.getY());

			char currentChar = getChar(currentX, i);
			char nextChar = getChar(currentX - 1, i + 1);

			if (currentChar != nextChar) {
				return;
			}
		}

		foundSequences++;
	}

	private boolean shouldCheckHorizontal(Position pos) {
		Position prevPos = new Position(pos.getX() - 1, pos.getY());
		boolean isInnerSequence = pos.getX() + SEQUENCE_LENGTH <= dna.length;
		return shouldCheckPosition(pos, prevPos, isInnerSequence);
	}

	private boolean shouldCheckVertical(Position pos) {
		Position prevPos = new Position(pos.getX(), pos.getY() - 1);
		boolean isInnerSequence = pos.getY() + SEQUENCE_LENGTH <= dna.length;
		return shouldCheckPosition(pos, prevPos, isInnerSequence);
	}

	private boolean shouldCheckDiagonalFoward(Position pos) {
		Position prevPos = new Position(pos.getX() - 1, pos.getY() - 1);

		boolean isInnerSequence = pos.getX() + SEQUENCE_LENGTH <= dna.length
				&& pos.getY() + SEQUENCE_LENGTH <= dna.length;

		return shouldCheckPosition(pos, prevPos, isInnerSequence);
	}

	private boolean shouldCheckDiagonalBackward(Position pos) {
		Position prevPos = new Position(pos.getX() + 1, pos.getY() - 1);

		boolean isInnerSequence = pos.getY() + SEQUENCE_LENGTH <= dna.length && pos.getX() + 1 - SEQUENCE_LENGTH >= 0;

		return shouldCheckPosition(pos, prevPos, isInnerSequence);
	}

	private boolean prevCharIsEquals(Position currentPos, Position prevPos) {

		if (!prevPos.isValid(dna.length)) {
			return false;
		}

		return getChar(currentPos) == getChar(prevPos);

	}

	private boolean shouldCheckPosition(Position pos, Position prevPos, boolean isInnerSequence) {
		return foundSequences < SEQUENCES_FOR_POSITIVE && !prevCharIsEquals(pos, prevPos) && isInnerSequence;
	}

	private char getChar(int x, int y) {
		return getChar(new Position(x, y));
	}

	private char getChar(Position pos) {
		return dna[pos.getY()].charAt(pos.getX());
	}

	private void validateCharAt(Position pos) {

		char c = getChar(pos);

		if (!POSSIBLE_LETTERS.contains(c)) {
			throw new InvalidDNAException(
					"Invalid Character '" + c + "' at position (" + pos.getX() + "," + pos.getY() + ")");
		}
	}

	private void validateRowSize(int i) {
		if (dna[i].length() != dna.length) {
			throw new InvalidDNAException("Row " + i + " does not match dna Array lenght. Table sould be NxN");
		}
	}

	private void validateTableSize() {
		if (dna.length < SEQUENCE_LENGTH) {
			throw new InvalidDNAException("Table sould be at least " + SEQUENCE_LENGTH + "x" + SEQUENCE_LENGTH);
		}
	}

}
