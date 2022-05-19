package Maze;

import java.util.*;

public class MazeComponents {
	public static List<MazeComponent> components = new ArrayList<>();
	public static MazeComponent nullComp = new MazeComponent(3, 3, new Exit[]{}, new int[][]{
		{0, 0, 0},
		{0, 0, 0},
		{0, 0, 0} 
	}, "Null");

	public static void init() {
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(1, -1, -1, -2), new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 1, 0},
			{0, 1, 0},
			{0, 1, 0} 
		}, "Vertical Straight"));
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(3, 1, 0, -1)}, new int[][]{
			{0, 0, 0},
			{1, 1, 1},
			{0, 0, 0} 
		}, "Horizontal Straight"));
		
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(3, 1, 0, -1), new Exit(1, -1, -1, -2), new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 1, 0},
			{1, 1, 1},
			{0, 1, 0} 
		}, "Four Way Crossroad"));
		
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(1, -1, -1, -2), new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 1, 0},
			{1, 1, 0},
			{0, 1, 0} 
		}, "Left/Up/Down Crossroad"));
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(3, 1, 0, -1),	new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 0, 0},
			{1, 1, 1},
			{0, 1, 0} 
		}, "Left/Right/Down Crossroad"));
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(3, 1, 0, -1), new Exit(1, -1, -1, -2), new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 1, 0},
			{0, 1, 1},
			{0, 1, 0} 
		}, "Right/Up/Down Crossroad"));
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(3, 1, 0, -1), new Exit(1, -1, -1, -2)}, new int[][]{
			{0, 1, 0},
			{1, 1, 1},
			{0, 0, 0} 
		}, "Left/Right/Up Crossroad"));
		
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 0, 0},
			{1, 1, 0},
			{0, 1, 0}
		}, "Left/Down Corner"));
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(3, 1, 0, -1), new Exit(1, 3, -1, 0)}, new int[][]{
			{0, 0, 0},
			{0, 1, 1},
			{0, 1, 0} 
		}, "Right/Down Corner"));
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(-1, 1, -2, -1), new Exit(1, -1, -1, -2)}, new int[][]{
			{0, 1, 0},
			{1, 1, 0},
			{0, 0, 0} 
		}, "Left/Up Corner")); // Spawned unconnected
		components.add(new MazeComponent(3, 3, new Exit[]{new Exit(1, -1, -1, -2), new Exit(3, 1, 0, -1)}, new int[][]{
			{0, 1, 0},
			{0, 1, 1},
			{0, 0, 0} 
		}, "Right/Up Corner"));
	}
}