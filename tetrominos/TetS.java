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
	
}
