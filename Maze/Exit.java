package Maze;

public class Exit extends Coordinate {
	int xOffset;
	int yOffset;

	public String direction;
	
	public Exit(int x, int y, int xOffset, int yOffset) {
		super(x, y);
		this.xOffset = xOffset;
		this.yOffset = yOffset;

		direction = "N";

		if (x == -1) {
			direction = "L";
		} else if (x == 1) {
			if (y == -1) {
				direction = "U";
			} else if (y == 3) {
				direction = "D";
			}
		} else if (x == 3) {
			direction = "R";
		}
	}

	public Coordinate getPlaceLoc() {
		return new Coordinate(x + xOffset, y + yOffset);
	}

	public Coordinate toCoordinate() {
		return new Coordinate(x, y);
	}

	// Gets coordinate one step beyond the exit location
	public Coordinate getBeyondCoord() {
		switch(direction) {
			case "U":
				return add(new Coordinate(0, -1)).toCoordinate();
			case "D":
				return add(new Coordinate(0, 1)).toCoordinate();
			case "L":
				return add(new Coordinate(-1, 0)).toCoordinate();
			case "R":
				return add(new Coordinate(1, 0)).toCoordinate();
			default:
				return add(new Coordinate(0, -1)).toCoordinate();
		}
	}

	public Coordinate getBeforeCoord() {
		switch(direction) {
			case "U":
				return add(new Coordinate(0, 1)).toCoordinate();
			case "D":
				return add(new Coordinate(0, -1)).toCoordinate();
			case "L":
				return add(new Coordinate(1, 0)).toCoordinate();
			case "R":
				return add(new Coordinate(-1, 0)).toCoordinate();
			default:
				return add(new Coordinate(0, 1)).toCoordinate();
		}
	}

	@Override
	public Exit add(Coordinate newCoord) {
		return new Exit(this.x + newCoord.x, this.y + newCoord.y, this.xOffset, this.yOffset);
	}
}