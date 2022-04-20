package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetJ extends Tetromino {
	
	public TetJ() {
		super(ColorConstants.BLUE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.BLUE, SpawningCoord.x-1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.BLUE, SpawningCoord.x-1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.BLUE, SpawningCoord.x+1, SpawningCoord.y));
	}

	@Override
	protected Tetromino clone() {
		Tetromino clone = new TetJ();
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			clone.tiles.get(i).setCoordinates(this.tiles.get(i).getCoordinates());
		}
		return clone;
	}

}
