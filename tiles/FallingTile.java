package tiles;

import utils.*;

public class FallingTile extends Tile {
	
//	[horizontal axis, vertical axis]
	private Vector coordinates;
	private Color color;
	
	public FallingTile() {
		super(ColorConstants.BLACK);
		this.color = ColorConstants.BLACK;
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
