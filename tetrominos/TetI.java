package tetrominos;

import java.util.HashMap;

import scenes.gamescene.Grid;
import tiles.FallingTile;
import utils.*;

public class TetI extends Tetromino {
	
	//Standard rotation vector contants
	private static final HashMap<String, Vector> rotationVectors = new HashMap<String, Vector>();
		
	static {
		rotationVectors.put("0>>1", new Vector(1, 0));
		rotationVectors.put("1>>2", new Vector(0, -1));
		rotationVectors.put("2>>3", new Vector(-1, 0));
		rotationVectors.put("3>>0", new Vector(0, 1));
		rotationVectors.put("1>>0", new Vector(-1, 0));
		rotationVectors.put("2>>1", new Vector(0, 1));
		rotationVectors.put("3>>2", new Vector(1, 0));
		rotationVectors.put("0>>3", new Vector(0, -1));
	}
		
	//Wall Kick translation vectors
	private final static HashMap<String, Vector> wallKickDataI;
		
	static {
		wallKickDataI = new HashMap<String, Vector>();
		wallKickDataI.put("0>>1_1", new Vector(0, 0));
		wallKickDataI.put("0>>1_2", new Vector(-2, 0));
		wallKickDataI.put("0>>1_3", new Vector(1, 0));
		wallKickDataI.put("0>>1_4", new Vector(-2, -1));
		wallKickDataI.put("0>>1_5", new Vector(1, 2));
		
		wallKickDataI.put("1>>0_1", new Vector(0, 0));
		wallKickDataI.put("1>>0_2", new Vector(2, 0));
		wallKickDataI.put("1>>0_3", new Vector(-1, 0));
		wallKickDataI.put("1>>0_4", new Vector(2, 1));
		wallKickDataI.put("1>>0_5", new Vector(-1, -2));
		
		wallKickDataI.put("1>>2_1", new Vector(0, 0));
		wallKickDataI.put("1>>2_2", new Vector(-1, 0));
		wallKickDataI.put("1>>2_3", new Vector(2, 0));
		wallKickDataI.put("1>>2_4", new Vector(-1, 2));
		wallKickDataI.put("1>>2_5", new Vector(2, -1));
		
		wallKickDataI.put("2>>1_1", new Vector(0, 0));
		wallKickDataI.put("2>>1_2", new Vector(1, 0));
		wallKickDataI.put("2>>1_3", new Vector(-2, 0));
		wallKickDataI.put("2>>1_4", new Vector(1, -2));
		wallKickDataI.put("2>>1_5", new Vector(-2, 1));
		
		wallKickDataI.put("2>>3_1", new Vector(0, 0));
		wallKickDataI.put("2>>3_2", new Vector(2, 0));
		wallKickDataI.put("2>>3_3", new Vector(-1, 0));
		wallKickDataI.put("2>>3_4", new Vector(2, 1));
		wallKickDataI.put("2>>3_5", new Vector(-1, -2));
		
		wallKickDataI.put("3>>2_1", new Vector(0, 0));
		wallKickDataI.put("3>>2_2", new Vector(-2, 0));
		wallKickDataI.put("3>>2_3", new Vector(1, 0));
		wallKickDataI.put("3>>2_4", new Vector(-2, -1));
		wallKickDataI.put("3>>2_5", new Vector(1, 2));
		
		wallKickDataI.put("3>>0_1", new Vector(0, 0));
		wallKickDataI.put("3>>0_2", new Vector(1, 0));
		wallKickDataI.put("3>>0_3", new Vector(-2, 0));
		wallKickDataI.put("3>>0_4", new Vector(1, -2));
		wallKickDataI.put("3>>0_5", new Vector(-2, 1));
		
		wallKickDataI.put("0>>3_1", new Vector(0, 0));
		wallKickDataI.put("0>>3_2", new Vector(-1, 0));
		wallKickDataI.put("0>>3_3", new Vector(2, 0));
		wallKickDataI.put("0>>3_4", new Vector(-1, 2));
		wallKickDataI.put("0>>3_5", new Vector(2, -1));
	}
		

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
		Vector trans = rotationVectors.get(ope);
		
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
		Vector trans = rotationVectors.get(ope);
		
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+trans.getX());
			newCoord.setY(newCoord.getY()+trans.getY());
			ft.setCoordinates(newCoord);
		}
	}
	
	/**
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
				trans = wallKickDataI.get(ope);
				
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
		//Apply the result of the test
		if (isPossible) {
			if (direction < 0) {
				this.rotateLeft();
			} else if (direction > 0) {
				this.rotateRight();
			}
			ope = "" + actualRot + ">>" + wantedRot + "_" + test;
			trans = wallKickDataI.get(ope);
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
