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
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void rotateLeft() {
		// The coordinates of the tetromino O aren't modified when it's rotated.
	}

	@Override
	protected void rotateRight() {
		// The coordinates of the tetromino O aren't modified when it's rotated.
	}
}
