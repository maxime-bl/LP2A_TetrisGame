package tetris_game;

import java.awt.Color;

public class main {

	public static void main(String[] args) {
		Tile test = new FallingTile(Color.BLACK, 1, 1);
		System.out.println(test.isNull());
	}

}
