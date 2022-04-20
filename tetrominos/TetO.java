package tetrominos;

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
	public void rotateLeft() {
		// The coordinates of the tetromino O aren't modified when it's rotated.
	}

	@Override
	public void rotateRight() {
		// The coordinates of the tetromino O aren't modified when it's rotated.
	}
}
