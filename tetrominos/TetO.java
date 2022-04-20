package tetrominos;

import java.util.Iterator;

import scenes.gamescene.Grid;
import tiles.FallingTile;
import utils.*;

public class TetO extends Tetromino {

	public TetO() {
		super(ColorConstants.YELLOW, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.YELLOW, SpawningCoord.x+1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.YELLOW, SpawningCoord.x+1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.YELLOW, SpawningCoord.x, SpawningCoord.y+1));
	}

	@Override
	public void rotate(int direction, Grid grid) {
		
	}

	@Override
	public void rotateLeft() {
		// The coordinates of the tetromino O aren't modified when it's rotated.
	}

	@Override
	public void rotateRight() {
		// The coordinates of the tetromino O aren't modified when it's rotated.
	}

	@Override
	protected Tetromino clone() {
		Tetromino clone = new TetO();
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			clone.tiles.get(i).setCoordinates(this.tiles.get(i).getCoordinates());
		}
		return clone;
	}
}
