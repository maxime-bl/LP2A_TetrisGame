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

	/*
	 * A wall kick happens when a player rotates a piece when no space exists in the squares where 
	 * that tetromino would normally occupy after the rotation.
	 * @param direction: int --> 1: Right, -1: Left
	 * @return boolean: true if a wall kick has been done, false else
	 */
	@Override
	protected boolean wallKick(int direction, Grid grid) {
		if (direction == 1) {
			this.moveRight(grid);
			return true;
		} else if (direction == -1) {
			this.moveLeft(grid);
			return true;
		} else if (direction == 2) {
			this.moveRight(grid);
			this.moveRight(grid);
			return true;
		} else if (direction == -2) {
			this.moveLeft(grid);
			this.moveLeft(grid);
			return true;
		}
		return false;
	}
	
	
	/*
	 * Method used to test if a wallkick is possible
	 * @param direction: int --> 1: Right, -1: Left
	 * @param grid : Grid
	 * @return int: 1, -1 or 0 if we can't do a wallkick
	 */
	@Override
	protected int wallKickTest(int direction, Grid grid) {
		Tetromino preview = null;
		if (direction == 1) {
			preview = rotateRightPrev();	
		} else if (direction == -1) {
			preview = rotateLeftPrev();
		}
		//Check single wall kick to the right
		if (preview.horizontalMoveCheck(1, grid)) {
			System.out.println("1");
			return 1;
		}
		//Check single wall kick to the left
		if (preview.horizontalMoveCheck(-1, grid)) {
			System.out.println("-1");
			return -1;
		}
		//Check double wall kick to the right
		preview.moveRight();
		if (preview.horizontalMoveCheck(1, grid)) {
			System.out.println("2");
			return 2;
		}
		preview.moveLeft();
		//Check double wall kick to the left
		preview.moveLeft();
		if (preview.horizontalMoveCheck(-1, grid)) {
			System.out.println("-2");
			return -2;
		}
		preview.moveRight();
		System.out.println("0");
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
	 * Method used to simulate rotation of the tetromino
	 * @return preview: the simulated tetromino
	 */
	@Override
	protected TetI rotateLeftPrev() {
		TetI preview = this.clone();
		preview.rotateLeft();
		for (FallingTile ft: preview.tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+preview.translationL.getX());
			newCoord.setY(newCoord.getY()+preview.translationL.getY());
			ft.setCoordinates(newCoord);
		}
	
		return preview;
	}
	
	/*
	 * Method used to simulate rotation of the tetromino
	 * @return preview: the simulated tetromino
	 */
	@Override
	protected TetI rotateRightPrev() {
		TetI preview = this.clone();
		preview.rotateRight();
		for (FallingTile ft: preview.tiles) {
			Vector newCoord = ft.getCoordinates();
			newCoord.setX(newCoord.getX()+preview.translationR.getX());
			newCoord.setY(newCoord.getY()+preview.translationR.getY());
			ft.setCoordinates(newCoord);
		}
		
		return preview;
	}


	@Override
	protected TetI clone() {
		TetI clone = new TetI();
		Vector newCoord;
		clone.centerTile = this.centerTile;
		for (int i = 0; i < 4; i++) {
			newCoord = new Vector(this.tiles.get(i).getCoordinates().getX(), this.tiles.get(i).getCoordinates().getY());
			clone.tiles.get(i).setCoordinates(newCoord);
		}
		clone.setTranslationL(this.translationL);
		clone.setTranslationR(this.translationR);
		return clone;
	}
	
	
}
