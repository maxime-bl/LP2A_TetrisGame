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
	protected Tetromino clone() {
		Tetromino clone = new TetT();
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			clone.tiles.get(i).setCoordinates(this.tiles.get(i).getCoordinates());
		}
		return clone;
	}
	
}
