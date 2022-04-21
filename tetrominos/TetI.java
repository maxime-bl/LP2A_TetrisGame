package tetrominos;

import scenes.gamescene.Grid;
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
	public void rotateLeft() {
		super.rotateLeft();
		
		//choosing the final rotation position
		int wantedRot = this.actualRot - 1;
		if (wantedRot == -1) {
			wantedRot = 3;
		} else if (wantedRot == 4) {
			wantedRot = 0;
		}
		String ope = "" + super.actualRot + ">>" + wantedRot;
		Vector trans = RotationVectorI.get(ope);
		
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+trans.getX());
			newCoord.setY(newCoord.getY()+trans.getY());
			ft.setCoordinates(newCoord);
		}
	}

	@Override
	public void rotateRight() {
		super.rotateRight();
		
		//choosing the final rotation position
		int wantedRot = this.actualRot + 1;
		if (wantedRot == -1) {
			wantedRot = 3;
		} else if (wantedRot == 4) {
			wantedRot = 0;
		}
		String ope = "" + super.actualRot + ">>" + wantedRot;
		Vector trans = RotationVectorI.get(ope);
		
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+trans.getX());
			newCoord.setY(newCoord.getY()+trans.getY());
			ft.setCoordinates(newCoord);
		}
	}
	
	/*
	 * Method used to rotate a tetromino
	 * @param direction: int --> -1: anti-clockwise, 1: clockwise
	 * @param grid: the actual game grid
	 */
	@Override
	public void rotate(int direction, Grid grid) {
		//choosing the final rotation position
		int wantedRot = this.actualRot + direction;
		if (wantedRot == -1) {
			wantedRot = 3;
		} else if (wantedRot == 4) {
			wantedRot = 0;
		}
		
		boolean isPossible;
		int test = 0;
		String ope;
		Tetromino preview;
		Vector coord, trans;
		do {
			test++;
			ope = "" + actualRot + ">>" + wantedRot + "_";
			preview = this.clone();
			isPossible = true;
			ope += test;
			System.out.println(ope);
			if (direction < 0) {
				preview.rotateLeft();
			} else if (direction > 0) {
				preview.rotateRight();
			}
			//Check if the falling tiles are in collision with other 
			//tiles or are outside the grid
			for (FallingTile fallingTile : preview.tiles) {
				coord = fallingTile.getCoordinates();
				
				//Get the translation vector corresponding to the test
				trans = WallKickDataI.get(ope);
				
				//Apply the translation to the preview
				coord.setX(coord.getX()+trans.getX());
				coord.setY(coord.getY()+trans.getY());
				
				//Check the result
				if (coord.getX() < 0 || coord.getX() >= grid.width
						|| coord.getY() < 0 || coord.getY() >= grid.height) {
					isPossible = false;
				} else if (grid.getTile(coord).isNull() == false) {
					isPossible = false;
				}
			}		
		} while (isPossible == false && test < 5);
		System.out.println(test);
		//Apply the result of the test
		if (isPossible) {
			if (direction < 0) {
				this.rotateLeft();
			} else if (direction > 0) {
				this.rotateRight();
			}
			ope = "" + actualRot + ">>" + wantedRot + "_" + test;
			trans = WallKickDataI.get(ope);
			for (FallingTile fallingTile : tiles) {
				coord = fallingTile.getCoordinates();
				coord.setX(coord.getX()+trans.getX());
				coord.setY(coord.getY()+trans.getY());
				fallingTile.setCoordinates(coord);
			}
			super.actualRot = wantedRot;
		}
	}

	@Override
	protected TetI clone() {
		TetI clone = new TetI();
		Vector newCoord;
		clone.centerTile = this.centerTile;
		clone.actualRot = super.actualRot;
		for (int i = 0; i < 4; i++) {
			newCoord = new Vector(this.tiles.get(i).getCoordinates().getX(), this.tiles.get(i).getCoordinates().getY());
			clone.tiles.get(i).setCoordinates(newCoord);
		}
		return clone;
	}
	
	
}
