package tetrominos;

import tiles.FallingTile;
import utils.*;
public class TetZ extends Tetromino{

	public TetZ() {
		super(ColorConstants.RED, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.RED, SpawningCoord.x-1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.RED, SpawningCoord.x+1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.RED, SpawningCoord.x, SpawningCoord.y+1));
	}

	@Override
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}

}
