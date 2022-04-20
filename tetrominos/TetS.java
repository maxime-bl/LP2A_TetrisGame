package tetrominos;

import tiles.FallingTile;
import utils.*;
public class TetS extends Tetromino {

	public TetS() {
		super(ColorConstants.GREEN, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.GREEN, SpawningCoord.x+1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.GREEN, SpawningCoord.x-1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.GREEN, SpawningCoord.x, SpawningCoord.y+1));
	}

	@Override
	protected Tetromino clone() {
		Tetromino clone = new TetS();
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			clone.tiles.get(i).setCoordinates(this.tiles.get(i).getCoordinates());
		}
		return clone;
	}
	
}
