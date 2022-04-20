package tetrominos;

import tiles.FallingTile;
import utils.*;

public class TetI extends Tetromino {
	
	private Vector translationL;
	private Vector translationR;

	public TetI() {
		super(ColorConstants.SKY_BLUE, SpawningCoord.x, SpawningCoord.y);
		super.tiles.add(centerTile);
		super.tiles.add(new FallingTile(ColorConstants.SKY_BLUE, SpawningCoord.x-1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.SKY_BLUE, SpawningCoord.x+1, SpawningCoord.y));
		super.tiles.add(new FallingTile(ColorConstants.SKY_BLUE, SpawningCoord.x+2, SpawningCoord.y));
		translationL = new Vector(0,-1);
		translationR = new Vector(1,0);
	}


	@Override
	public void rotateLeft() {
		super.rotateLeft();
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+translationL.getX());
			newCoord.setY(newCoord.getY()+translationL.getY());
			ft.setCoordinates(newCoord);
		}
		
		// Translation vectors modification
		this.translationL.setX(-translationL.getY());
		this.translationL.setY(translationL.getX());
		
		this.translationR.setX(-translationR.getY());
		this.translationR.setY(translationR.getX());
	}

	@Override
	public void rotateRight() {
		super.rotateLeft();
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+translationR.getX());
			newCoord.setY(newCoord.getY()+translationR.getY());
			ft.setCoordinates(newCoord);
		}
		
		// Translation vectors modification
		this.translationL.setX(-translationL.getY());
		this.translationL.setY(translationL.getX());
		
		this.translationR.setX(-translationR.getY());
		this.translationR.setY(translationR.getX());
	}
}
