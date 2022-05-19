package Maze;

import java.util.*;
import Maze.*;
import java.util.Random;

// 0 = obstacle, anything else will be registered as a path. The number at point before player
// will be multiplied by the player speed,
// which is so I can avoid an if statement because they're slow
public class Maze {
	public int grid_quantum = 10;
	private int[][] codex;
	public List<Coordinate> walls;
	public List<Coordinate> takenSquares;
	private int iterations;

	public List<Coordinate> specialSquares;

	private int additions = 1;
	private int iterations2 = 1;

	private List<ExitsComponent> exitsComponents;

	public Maze(int screen_size) {
		int size = screen_size / grid_quantum;
		codex = new int[size][size];
		walls = new ArrayList<>();
		takenSquares = new ArrayList<>();
		iterations = 0;
		exitsComponents = new ArrayList<>();
		for (int i = 0; i < codex.length; i++) {
			for (int j = 0; j < codex.length; j++) {
				codex[i][j] = 1;
			}
		}

		specialSquares = new ArrayList<>();
		
		// Set walls here
		MazeComponent currComponent = getComp(true);
		Coordinate currCompLoc = new Coordinate(0, 0);
		addComponent(currComponent, currCompLoc);
		recursiveComponentAdder(currComponent, currCompLoc, validOpenings(currComponent, currCompLoc));

		updateWalls();
	}

	public void updateWalls() {
		walls = new ArrayList<>();
		for (int i = 0; i < codex.length; i++) {
			for (int j = 0; j < codex.length; j++) {
				if (codex[i][j] == 0) {
					walls.add(new Coordinate(i, j));
				}
			}
		}
	}

	// I have to make this because java is freaking out over literally nothing so disregard this
	public static void init() {
		MazeComponents.init();
	}

	private boolean finished = false;
	private void recursiveComponentAdder(MazeComponent component, Coordinate compLoc, List<Exit> validOpeningsList) {
		if (finished) {
			return;
		}
		for (Exit i : validOpeningsList) {
			if (isCoordinateTaken(i.getPlaceLoc())) {
				continue;
			}
			
			iterations2 = 0;
			MazeComponent newComp;

			boolean next = false;
			do {
				iterations2++;
				if (iterations2 > 300) {
					newComp = MazeComponents.nullComp;
					next = true;
					break;
				}
				newComp = getComp(false);
				if (!addComponent(newComp, i.getPlaceLoc())) {
					continue;
				}
				if (isComponentValid(newComp, component, i)) {
					additions++;
				}
			} while(!isComponentValid(newComp, component, i));

			if (next) {
				return; //continue;
			}

			specialSquares.add(i.getPlaceLoc());
			
			iterations++;
			/*if (iterations > 100) {  // Uncomment this to limit the number of components added
				System.out.println("Iterations: " + iterations + "    Too Many Iterations");
				System.out.println("Additions: " + additions);
				finished = true;
				return;
			}*/

			exitsComponents.add(new ExitsComponent(newComp, i.getPlaceLoc(), newComp.gaps));
			recursiveComponentAdder(newComp, i.getPlaceLoc(), validOpenings(newComp, i.getPlaceLoc()));
			if (finished) {
				return;
			}
		}
	}

  // This function checks if the component provided will be a dead end, exit is where it was added
	private boolean isComponentValid(MazeComponent newComp, MazeComponent oldComp, Exit loc) {
		if (loc.x < 40 && loc.y < 40) {
			return (codex[loc.x][loc.y] != 0);
		} else {
			return false;
		}
	}

	private boolean addComponent(MazeComponent component, Coordinate coord) {
		if (coord.x + component.size_x > 39 || coord.y + component.size_y > 39) {
			return false;
		}
		
		int amount = component.area.length;
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < amount; j++) {
				codex[coord.x + j][coord.y + i] = component.area[i][j];
				takenSquares.add(new Coordinate(coord.x + j, coord.y + i));
			}
		}
		return true;
	}

	public void clear(Coordinate coord) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (coord.x + i > 39 || coord.y + j > 39) {
					return;
				}
				codex[coord.x + i][coord.y + j] = 1;
			}
		}
	}

	private int randNum(int upper) {
		return new Random().nextInt(upper);
	}

	private MazeComponent getComp(boolean first) {
		int num;
		if (first) {
			do {
				num = randNum(MazeComponents.components.size());
			} while (MazeComponents.components.get(num).designation.equals("Left/Up Corner"));
		} else {
			num = randNum(MazeComponents.components.size());
		}
		return MazeComponents.components.get(num);
	}

	private List<Exit> validOpenings(MazeComponent component, Coordinate coords) {
		List<Exit> list = new ArrayList<>();
		Coordinate i;
		for (Exit e : component.gaps) {
			i = e.getPlaceLoc();
			if (coords.x + i.x <= 400 && coords.x + i.x >= 0) {
				if (coords.y + i.y <= 400 && coords.y + i.y >= 0) {
					if (!isCoordinateTaken(coords.x + i.x, coords.y + i.y)) {
						list.add(e.add(coords));
					}
				}
			}
		}
		return list;
	}

	private boolean isCoordinateTaken(int x, int y) {
		for (Coordinate i : takenSquares) {
			if (i.x == x && i.y == y) {
				return true;
			}
		}
		return false;
	}

	private boolean isCoordinateTaken(Coordinate c) {
		return isCoordinateTaken(c.x, c.y);
	}

	public void clean() {
		for (ExitsComponent i : exitsComponents) {
			for (Exit j : i.exits) {
				Coordinate c = j.add(i.loc);
				Coordinate v = j.getBeforeCoord().add(i.loc);
				if (c.x >= 0 && c.x < 40 && c.y >= 0 && c.y < 40) {
					// Extend ends of maze to cover outside pixels that aren't multiple of three
					if (c.x == 39) {
						codex[39][c.y + 1] = 0;
						codex[39][c.y - 1] = 0;
					} else if (c.y == 39) {
						codex[c.x + 1][39] = 0;
						codex[c.x - 1][39] = 0;
					}
					// Removes exits that lead nowhere
					if (codex[c.x][c.y] == 0) {
;						codex[v.x][v.y] = 0;
					}
				}
			}
		}
		updateWalls();
	}

	public void export() {
		System.out.print("{");
		for (int[] i : codex) {
				for (int j : i) {
					System.out.print(j + ", ");
				}
				System.out.print("\n");
		}
		System.out.print("}");
	}
}