package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetL extends Tetromino {

	public TetL() {
		super(ColorConstants.ORANGE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.ORANGE, SpawningCoord.x+1, SpawningCoord.y+1));
		super.tiles.add(new FallingTile(ColorConstants.ORANGE, SpawningCoord.x+1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.ORANGE, SpawningCoord.x-1, SpawningCoord.y));
	}

	@Override
	protected Tetromino clone() {
		Tetromino clone = new TetL();
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			clone.tiles.get(i).setCoordinates(this.tiles.get(i).getCoordinates());
		}
		return clone;
	}

}
