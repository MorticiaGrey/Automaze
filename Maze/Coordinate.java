package Maze;

public class Coordinate {
	public int x;
	public int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public Coordinate add(Coordinate newCoord) {
		return new Coordinate(this.x + newCoord.x, this.y + newCoord.y);
	}

	public Exit toExit() {
		return new Exit(x, y, 0, 0);
	}
}