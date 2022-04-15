package tetrominos;

import java.util.ArrayList;

import utils.*;
import tiles.FallingTile;

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
	
	public void draw() {
		
	}
}
