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
		super.rotateLeft();
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setY(newCoord.getY()-1);
			ft.setCoordinates(newCoord);
		}
	}

	@Override
	protected void rotateRight() {
		// Maximum coordinate over X or Y is used as a referential to do the rotation
		int max = 0;
		for (FallingTile ft: tiles) {
			if (max < Math.max(ft.getCoordinates().getX(), ft.getCoordinates().getY())) {
				max = Math.max(ft.getCoordinates().getX(), ft.getCoordinates().getY());
			}
		}
		for (FallingTile ft: tiles) {
			Vector ftCoord = ft.getCoordinates();
			Vector newCoord = new Vector(ftCoord.getY(),max -(max-ftCoord.getX()));
			ft.setCoordinates(newCoord);
		}
	}
}
