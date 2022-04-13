package grid;

import tiles.Tile;

public class Line {
	
	private Tile[] line;
	
	public Line() {
		line = new Tile[10];
	}
	
	public Line(int width) {
		line = new Tile[width];
	}
}