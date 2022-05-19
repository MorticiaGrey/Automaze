package Maze;

import java.util.*;

public class MazeComponent {
	public int size_x;
	public int size_y;
	public List<Exit> gaps;
	public int[][] area;
	public String designation;

	public MazeComponent(int size_x, int size_y, Exit[] gaps, int[][] area) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.gaps = new ArrayList<Exit>(Arrays.asList(gaps));
		this.area = area;
		this.designation = "No Designation";
	}

	public MazeComponent(int size_x, int size_y, Exit[] gaps, int[][] area, String designation) {
		this.size_x = size_x;
		this.size_y = size_y;
		this.gaps = new ArrayList<Exit>(Arrays.asList(gaps));
		this.area = area;
		this.designation = designation;
	}

	@Override
	public String toString() {
		return designation;
	}
}