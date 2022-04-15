package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetL extends Tetromino {

	public TetL() {
		super(ColorConstants.ORANGE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.YELLOW, SpawningCoord.x+1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.YELLOW, SpawningCoord.x+1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.YELLOW, SpawningCoord.x-1, SpawningCoord.y));
	}

	@Override
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}
}
