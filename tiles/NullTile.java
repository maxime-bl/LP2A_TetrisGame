package tiles;

import utils.*;

public class NullTile extends Tile {

	private Color color;
	
	public NullTile() {
		color = ColorConstants.BLACK;
	}
	
	public boolean isNull() {
		return true;
	}
	
	public Color getColor() {
		return this.color;
	}
}
