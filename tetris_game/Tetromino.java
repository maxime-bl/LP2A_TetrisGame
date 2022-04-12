package tetris_game;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Tetromino {

	protected ArrayList<FallingTile> tiles;
	protected Color color;
	protected FallingTile centerTile;
	
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
