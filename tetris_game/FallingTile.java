package tetris_game;

import java.awt.Color;

import tetrominos.Tile;

public class FallingTile extends Tile {
	
//	[vertical axis, horizontal axis]
	private int[] coordinates = new int[2];
	private Color color;
	
	public FallingTile() {
		super(Color.BLACK);
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
}
