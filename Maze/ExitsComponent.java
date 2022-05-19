package Maze;

import java.util.*;

public class ExitsComponent {
	public MazeComponent comp;
	public Coordinate loc;
	public List<Exit> exits;

	public ExitsComponent(MazeComponent comp, Coordinate loc, List<Exit> exits) {
		this.comp = comp;
		this.loc = loc;
		this.exits = exits;
	}
}