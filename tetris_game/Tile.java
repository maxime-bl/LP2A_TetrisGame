package tetris_game;

import java.awt.Color;

public class Tile {

	private Color color;

	public Tile() {
		this.color = Color.BLACK;
	}
	
	public Tile(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}	
	
	public void display() {
		
	}
	
	public boolean isNull() {
		return this.color == Color.BLACK;
	}
}
