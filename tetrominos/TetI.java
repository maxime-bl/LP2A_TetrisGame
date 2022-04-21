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
	
	/*
	 * Method used to rotate tetrominoes
	 * @param direction: int --> 1: clockwise rotation, -1: anti-clockwise rotation
	 * @param grid: Grid
	 */
	@Override
	public void rotate(int direction, Grid grid) {
		//Check if the rotation is possible without wallkicks
		boolean isPossible = rotationTest(direction, grid);
		
		//Check if a wallkick is possible according to a right rotation
		if (direction == 1 && isPossible == false) {
			if (wallKick(wallKickTest(direction, grid), grid)) {
				rotateRight();
			}
		//Check if a wallkick is possible according to a left rotation
		} else if (direction == -1 && isPossible == false){
			if (wallKick(wallKickTest(direction, grid), grid)) {
				rotateLeft();
			}
		//Simply do the rotation
		} else {
			if (direction == 1) {
				rotateRight();
			} else if (direction == -1) {
				rotateLeft();
			}
		}
	}
	
	/*
	 * A wall kick happens when a player rotates a piece when no space exists in the squares where 
	 * that tetromino would normally occupy after the rotation.
	 * @param direction: int --> 1: Right, -1: Left
	 * @return boolean: true if a wall kick has been done, false else
	 */
	@Override
	protected boolean wallKick(int direction, Grid grid) {
		if (direction == 2) {
			this.fall(grid);
			return true;
		} else if (direction == 1) {
			this.moveRight(grid);
			return true;
		} else if (direction == -1) {
			this.moveLeft(grid);
			return true;
		}
		return false;
	}
	
	/*
	 * Method used to test if a wallkick is possible
	 * @param direction: int --> 1: Right, -1: Left
	 * @param grid : Grid
	 * @return int: 2: down wall kick, 1: right wall kick, -1: left wallkick or 0 if we can't do a wallkick
	 */
	@Override
	protected int wallKickTest(int direction, Grid grid) {
		Tetromino preview = null;
		boolean downWK = true;
		if (direction == 1) {
			preview = rotateRightPrev();	
		} else if (direction == -1) {
			preview = rotateLeftPrev();
		}
		for (FallingTile fallingTile : tiles) {
			if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height+1 && fallingTile.getCoordinates().getY() >= 0) {
				if (grid.getTile(fallingTile.getCoordinates()).isNull() == false){
					downWK = false;
				}
			} else {
				downWK = false;
			}
		} 
		if (downWK) {
			return 2;
		} else if (preview.horizontalMoveCheck(1, grid)) {
			return 1;
		} else if (preview.horizontalMoveCheck(-1, grid)) {
			return -1;
		}
		return 0;
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
	
	/*
	 * Method used to simulate rotation of the tetromino
	 * @return preview: the simulated tetromino
	 */
	@Override
	protected Tetromino rotateLeftPrev() {
		Tetromino preview = this.clone();
		preview.rotateLeft();
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
		return preview;
	}
	
	/*
	 * Method used to simulate rotation of the tetromino
	 * @return preview: the simulated tetromino
	 */
	@Override
	protected Tetromino rotateRightPrev() {
		Tetromino preview = this.clone();
		preview.rotateRight();
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
		return preview;
	}


	@Override
	protected Tetromino clone() {
		Tetromino clone = new TetI();
		Vector newCoord;
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			newCoord = new Vector(this.tiles.get(i).getCoordinates().getX(), this.tiles.get(i).getCoordinates().getY());
			clone.tiles.get(i).setCoordinates(newCoord);
		}
		return clone;
	}
	
	
}
