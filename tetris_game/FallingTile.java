package tetris_game;

import java.awt.Color;

public class FallingTile extends Tile {
	
//	[vertical axis, horizontal axis]
	private int[] coordinates = new int[2];
	private Color color;
	
	public FallingTile() {
		this.color = Color.BLACK;
	}
	
	public FallingTile(Color color, int posHorizontal, int posVertical) {
		super(color);
		this.color = color;
		this.coordinates[0] = posVertical;
		this.coordinates[1] = posHorizontal;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String toString() {
		return ""+color+"   "+coordinates[0] + coordinates[1];
	}
}
