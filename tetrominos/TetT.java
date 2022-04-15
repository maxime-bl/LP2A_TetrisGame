package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetT extends Tetromino {

	public TetT() {
		super(ColorConstants.PURPLE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.PURPLE, SpawningCoord.x-1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.PURPLE, SpawningCoord.x, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.PURPLE, SpawningCoord.x+1, SpawningCoord.y));
	}
	
	@Override
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}
}
