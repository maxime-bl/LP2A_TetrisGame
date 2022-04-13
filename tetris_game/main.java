package tetris_game;

import java.awt.Color;

import tiles.FallingTile;
import tiles.Tile;

public class main {

	public static void main(String[] args) {
		Tile test = new FallingTile(Color.BLUE, 1, 1);
		System.out.println(test.isNull());
	}

}
