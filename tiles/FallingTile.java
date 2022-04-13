package tiles;

import java.awt.Color;

public class FallingTile extends Tile {
	
//	[horizontal axis, vertical axis]
	private int[] coordinates = new int[2];
	private Color color;
	
	public FallingTile() {
		super(Color.BLACK);
		this.color = Color.BLACK;
	}
	
	public FallingTile(Color color, int posHorizontal, int posVertical) {
		super(color);
		this.color = color;
		this.coordinates[1] = posVertical;
		this.coordinates[0] = posHorizontal;
	}
	
	public Color getColor() {
		return this.color;
	}
}
