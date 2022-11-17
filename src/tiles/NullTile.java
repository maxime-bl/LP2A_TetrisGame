package tiles;

import processing.core.*;
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
	
	public void display(PApplet w) {
		w.push();
		
		w.noStroke();
				
		w.fill(255);
		w.rect(0, 0, Tile.SIZE, Tile.SIZE);
		
		w.fill(0);
		w.rect((float)0.01*Tile.SIZE, (float)0.01*Tile.SIZE, (float)0.98*Tile.SIZE, (float)0.98*Tile.SIZE);
		
		w.pop();
	}
}
