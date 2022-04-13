package tetris_game;

import java.awt.Color;

import tetrominos.Tile;

public class NullTile extends Tile {

	private Color color = Color.BLACK;
	
	public boolean isNull() {
		return true;
	}
}
