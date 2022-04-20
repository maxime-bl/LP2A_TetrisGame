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
	protected Tetromino clone() {
		Tetromino clone = new TetZ();
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			clone.tiles.get(i).setCoordinates(this.tiles.get(i).getCoordinates());
		}
		return clone;
	}

}
