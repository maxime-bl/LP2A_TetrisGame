package tetrominos;

import scenes.gamescene.Grid;
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

	public Vector getTranslationL() {
		return translationL;
	}

	public void setTranslationL(Vector translationL) {
		this.translationL = translationL;
	}

	public Vector getTranslationR() {
		return translationR;
	}

	public void setTranslationR(Vector translationR) {
		this.translationR = translationR;
	}

//	/*
//	 * A wall kick happens when a player rotates a piece when no space exists in the squares where 
//	 * that tetromino would normally occupy after the rotation.
//	 * @param direction: int --> 1: Right, -1: Left
//	 * @return boolean: true if a wall kick has been done, false else
//	 */
//	@Override
//	protected boolean wallKick(int direction, Grid grid) {
//		if (direction == 1) {
//			this.moveRight(grid);
//			return true;
//		} else if (direction == -1) {
//			this.moveLeft(grid);
//			return true;
//		} else if (direction == 2) {
//			this.moveRight();
//			this.moveRight();
//			return true;
//		} else if (direction == -2) {
//			this.moveLeft();
//			this.moveLeft();
//			return true;
//		}
//		return false;
//	}
//	
//	
//	/*
//	 * Method used to test if a wallkick is possible
//	 * @param direction: int --> 1: Right, -1: Left
//	 * @param grid : Grid
//	 * @return int: 1, -1 or 0 if we can't do a wallkick
//	 */
//	@Override
//	protected int wallKickTest(int direction, Grid grid) {
//		Tetromino preview = null;
//		if (direction == 1) {
//			preview = rotateRightPrev();	
//		} else if (direction == -1) {
//			preview = rotateLeftPrev();
//		}
//		//Check single wall kick to the right
//		if (preview.horizontalMoveCheck(1, grid)) {
//			System.out.println("1");
//			return 1;
//		}
//		//Check single wall kick to the left
//		if (preview.horizontalMoveCheck(-1, grid)) {
//			System.out.println("-1");
//			return -1;
//		}
//		//Check double wall kick to the right
//		preview.moveRight();
//		if (preview.horizontalMoveCheck(1, grid)) {
//			System.out.println("2");
//			return 2;
//		}
//		preview.moveLeft();
//		//Check double wall kick to the left
//		preview.moveLeft();
//		if (preview.horizontalMoveCheck(-1, grid)) {
//			System.out.println("-2");
//			return -2;
//		}
//		preview.moveRight();
//		System.out.println("0");
//		return 0;
//	}

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
		int temp = translationL.getX();
		this.translationL.setX(-translationL.getY());
		this.translationL.setY(temp);
		
		temp = translationR.getX();
		this.translationR.setX(-translationR.getY());
		this.translationR.setY(temp);
	}

	@Override
	public void rotateRight() {
		super.rotateRight();
		for (FallingTile ft: tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+translationR.getX());
			newCoord.setY(newCoord.getY()+translationR.getY());
			ft.setCoordinates(newCoord);
		}
		
		// Translation vectors modification
		int temp = translationL.getX();
		this.translationL.setX(translationL.getY());
		this.translationL.setY(-temp);
		
		temp = translationR.getX();
		this.translationR.setX(translationR.getY());
		this.translationR.setY(-temp);
	}
	
	/*
	 * Method used to rotate a tetromino
	 * @param direction: int --> -1: anti-clockwise, 1: clockwise
	 * @param grid: the actual game grid
	 */
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
	
//	/*
//	 * Method used to simulate rotation of the tetromino
//	 * @return preview: the simulated tetromino
//	 */
//	@Override
//	protected TetI rotateLeftPrev() {
//		TetI preview = this.clone();
//		preview.rotateLeft();
//		for (FallingTile ft: preview.tiles) {
//			Vector newCoord = ft.getCoordinates();
//			newCoord.setX(newCoord.getX()+preview.translationL.getX());
//			newCoord.setY(newCoord.getY()+preview.translationL.getY());
//			ft.setCoordinates(newCoord);
//		}
//	
//		return preview;
//	}
//	
//	/*
//	 * Method used to simulate rotation of the tetromino
//	 * @return preview: the simulated tetromino
//	 */
//	@Override
//	protected TetI rotateRightPrev() {
//		TetI preview = this.clone();
//		preview.rotateRight();
//		for (FallingTile ft: preview.tiles) {
//			Vector newCoord = ft.getCoordinates();
//			newCoord.setX(newCoord.getX()+preview.translationR.getX());
//			newCoord.setY(newCoord.getY()+preview.translationR.getY());
//			ft.setCoordinates(newCoord);
//		}
//		
//		return preview;
//	}


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
		clone.setTranslationL(this.translationL);
		clone.setTranslationR(this.translationR);
		return clone;
	}
	
	
}
