package meli.mutants.detector;

public class MutantDetector {

	static int LETTERS_FOR_SEQUENCE = 4;
	static int SEQUENCES_FOR_POSITIVE = 2;
	static int MATRIX_SIZE = 6;
	

	public boolean isMutant(String[] dna) {

		int foundSequences = 0;

		for (int i = 0; i < MATRIX_SIZE; i++) {
			
			for (int j = 0; j < MATRIX_SIZE; j++) {
				
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
	

	public boolean checkHorizontalSequence(Position pos, String[] dna) {
		
		if(!shouldChecklHorizontal(pos)) return false;
		
		String row = dna[pos.getY()];

		for (int i = pos.getX(); i < LETTERS_FOR_SEQUENCE - 1 + pos.getX(); i++) {
			char currentChar = row.charAt(i);
			char nextChar = row.charAt(i + 1);

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkVerticalSequence(Position pos, String[] dna) {
		
		if(!shouldChecklVertical(pos)) return false;

		for (int i = pos.getY(); i < LETTERS_FOR_SEQUENCE - 1 + pos.getY(); i++) {
			char currentChar = dna[i].charAt(pos.getX());
			char nextChar = dna[i + 1].charAt(pos.getX());

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkDiagonalFowardSequence(Position pos, String[] dna) {
		
		if(!shouldChecklDiagonalFoward(pos)) return false;

		for (int i = pos.getY(); i < LETTERS_FOR_SEQUENCE - 1 + pos.getY(); i++) {
			char currentChar = dna[i].charAt(pos.getX() + i);
			char nextChar = dna[i + 1].charAt(pos.getX() + i + 1);

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkDiagonalBackwardSequence(Position pos, String[] dna) {
		
		if(!shouldChecklDiagonalBackward(pos)) return false;

		for (int i = pos.getY(); i < LETTERS_FOR_SEQUENCE - 1 + pos.getY(); i++) {
			char currentChar = dna[i].charAt(pos.getX() - i);
			char nextChar = dna[i + 1].charAt(pos.getX() - i - 1);

			if (!(currentChar == nextChar)) {
				return false;
			}
		}

		return true;
	}
	
	public boolean shouldChecklHorizontal(Position pos) {
		return pos.getX() + LETTERS_FOR_SEQUENCE <= MATRIX_SIZE;
	}
	
	public boolean shouldChecklVertical(Position pos) {
		return pos.getY() + LETTERS_FOR_SEQUENCE <= MATRIX_SIZE;
	}
	
	public boolean shouldChecklDiagonalFoward(Position pos) {
		return shouldChecklHorizontal(pos) && shouldChecklVertical(pos);
	}
	
	public boolean shouldChecklDiagonalBackward(Position pos) {
		return shouldChecklVertical(pos) && 
				pos.getX() + 1 - LETTERS_FOR_SEQUENCE >= 0;
	}

}
