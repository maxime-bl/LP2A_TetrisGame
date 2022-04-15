package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetI extends Tetromino {

	public TetI() {
		super(ColorConstants.SKY_BLUE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.SKY_BLUE, SpawningCoord.x-1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.SKY_BLUE, SpawningCoord.x+1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.SKY_BLUE, SpawningCoord.x+2, SpawningCoord.y));
	}

	@Override
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void rotateRight() {
		// TODO Auto-generated method stub
		
	}
}
