import processing.core.PApplet;

import Maze.*;

// Grid size 10
public class Sketch extends PApplet {
	Maze maze;

  public void settings() {
    size(400, 400);
		Maze.init();
		maze = new Maze(400);
		maze.clean();
  }

  public void setup() {
    background(0);
  }

	public void keyPressed() {
		if (key == 'n') {
			maze = new Maze(400);
			maze.clean();
		}
		if (key == 'x') {
			maze.export();
		}
	}

  public void draw() {
    fill(255);
		background(0);
		for (Coordinate i : maze.walls) {
			rect((float) i.x * maze.grid_quantum, (float) i.y * maze.grid_quantum, 10F, 10F);
		}
  }
}