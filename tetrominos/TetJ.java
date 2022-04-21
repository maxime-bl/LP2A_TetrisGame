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
		Vector newCoord;
		clone.centerTile = this.centerTile;
		clone.actualRot = this.actualRot;
		for (int i = 0; i < 4; i++) {
			newCoord = new Vector(this.tiles.get(i).getCoordinates().getX(), this.tiles.get(i).getCoordinates().getY());
			clone.tiles.get(i).setCoordinates(newCoord);
		}
		return clone;
	}

}
