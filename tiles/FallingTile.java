package tiles;

import utils.Vector;

import java.awt.Color;

public class FallingTile extends Tile {
	
//	[horizontal axis, vertical axis]
	private Vector coordinates;
	private Color color;
	
	public FallingTile() {
		super(Color.BLACK);
		this.color = Color.BLACK;
	}
	
	public FallingTile(Color color, int posHorizontal, int posVertical) {
		super(color);
		this.color = color;
		coordinates = new Vector(posHorizontal, posVertical);
	}
	
	public Color getColor() {
		return this.color;
	}

	public Vector getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Vector coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
