package meli.mutants.util.detector;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	
	public boolean isValid(int tableLength) {
		return x >= 0 && y >= 0 &&
			   x <= tableLength - 1 && y <= tableLength - 1 ;
	}

	
}
