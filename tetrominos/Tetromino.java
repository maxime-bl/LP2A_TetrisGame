package tetrominos;

import java.util.ArrayList;

import processing.core.*;
import utils.*;
import tiles.FallingTile;
import tiles.Tile;

public abstract class Tetromino {

	protected ArrayList<FallingTile> tiles;
	protected Color color;
	protected FallingTile centerTile;
	
	public Tetromino() {
		this.tiles = new ArrayList<FallingTile>();
		this.color = ColorConstants.BLACK;
		centerTile = new FallingTile();
	}
	
	public Tetromino(Color color, int posHorizontal, int posVertical) {
		this.tiles = new ArrayList<FallingTile>();
		this.color = color;
		this.centerTile = new FallingTile(color, posHorizontal, posVertical);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	protected abstract boolean hasCollided();
	protected abstract void rotateLeft();
	protected abstract void rotateRight();
	
	public void update() {
		
	}
	
	private void makeStatic() {
		
	}
	
	public void display(PApplet w) {
		for (FallingTile t : tiles) {
			int x = t.getCoordinates().getX();
			int y = t.getCoordinates().getY();
			
			w.push();
			w.translate(x*Tile.SIZE,(19-y)*Tile.SIZE);
			t.display(w);
			w.pop();
		}
	}
}
