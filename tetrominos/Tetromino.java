package tetrominos;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;
import scenes.gamescene.Grid;
import utils.*;
import tiles.FallingTile;
import tiles.Tile;

public abstract class Tetromino {

	protected ArrayList<FallingTile> tiles;
	protected Color color;
	protected FallingTile centerTile;
	
	public Tetromino() {
		this.tiles = new ArrayList<FallingTile>();
		this.color = ColorConstants.BLACK;
		centerTile = new FallingTile();
	}
	
	public Tetromino(Color color, int posHorizontal, int posVertical) {
		this.tiles = new ArrayList<FallingTile>();
		this.color = color;
		this.centerTile = new FallingTile(color, posHorizontal, posVertical);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public boolean hasCollided(Grid grid) {
		boolean isColliding = false;
		
		for (FallingTile t : this.tiles) {
			
			if (t.getCoordinates().getY() == 0) {
				isColliding = true;
				break;
			} else if (grid.getTile(t.getCoordinates().getX(), t.getCoordinates().getY()-1).isNull() == false) {
				isColliding = true;
				break;
			}
		}
		
		return isColliding;
	}
	
	/*
	 * Method used to rotate tetrominoes
	 * @param direction: int --> 1: clockwise rotation, -1: anti-clockwise rotation
	 * @param grid: Grid
	 */
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
	 * Method used to test if the rotation of the tetromino is possible without wallkick
	 * @param direction : int
	 * @param grid : Grid
	 * @return boolean
	 */
	protected boolean rotationTest(int direction, Grid grid) {
		boolean isPossible = true;
		//Check if the right rotation is possible
		if (direction == 1) {
			Tetromino preview = rotateRightPrev();
			for (FallingTile fallingTile : preview.tiles) {
				//Check if the tile will be inside the grid
				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height && fallingTile.getCoordinates().getY() >= 0) {
					//Check if there is already a tile at these coordinates
					if (grid.getTile(fallingTile.getCoordinates()).isNull() == false) {
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
		////Check if the left rotation is possible
		} else if (direction == -1){
			Tetromino preview = rotateLeftPrev();
			for (FallingTile fallingTile : preview.tiles) {
				//Check if the tile will be inside the grid
				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height && fallingTile.getCoordinates().getY() >= 0) {
					//Check if there is already a tile at these coordinates
					if (grid.getTile(fallingTile.getCoordinates()).isNull() == false) {
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
		}
		return isPossible;
	}
	
	/*
	 * Method used to test if a wallkick is possible
	 * @param direction: int --> 1: Right, -1: Left
	 * @param grid : Grid
	 * @return int: 1, -1 or 0 if we can't do a wallkick
	 */
	protected int wallKickTest(int direction, Grid grid) {
		Tetromino preview = null;
		if (direction == 1) {
			preview = rotateRightPrev();	
		} else if (direction == -1) {
			preview = rotateLeftPrev();
		}
		if (preview.horizontalMoveCheck(1, grid)) {
			return 1;
		} else if (preview.horizontalMoveCheck(-1, grid)) {
			return -1;
		}
		return 0;
//		//Test the right wallkick
//		preview.wallKick(1);
//		returnDirection = 1;
//		for (FallingTile fallingTile : preview.tiles) {
//			//Check if the tile will be inside the grid
//			if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
//					&& fallingTile.getCoordinates().getY() < grid.height && fallingTile.getCoordinates().getY() >= 0) {
//				//Check if there is already a tile at these coordinates
//				if (grid.getTile(fallingTile.getCoordinates()).isNull() == false){
//					returnDirection = 0;
//				}
//			} else {
//				returnDirection = 0;
//			}
//		}
//		
//		if (returnDirection == 1) {
//			return returnDirection;
//		} else {
//			//Test the left wallkick
//			preview.wallKick(-1);
//			returnDirection = -1;
//			for (FallingTile fallingTile : preview.tiles) {
//				//Check if the tile will be inside the grid
//				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
//						&& fallingTile.getCoordinates().getY() < grid.height && fallingTile.getCoordinates().getY() >= 0) {
//					//Check if there is already a tile at these coordinates
//					if (grid.getTile(fallingTile.getCoordinates()).isNull() == false){
//						returnDirection = 0;
//					}
//				} else {
//					returnDirection = 0;
//				}
//			}
//		}
	}
	
	/*
	 * A wall kick happens when a player rotates a piece when no space exists in the squares where 
	 * that tetromino would normally occupy after the rotation.
	 * @param direction: int --> 1: Right, -1: Left
	 * @return boolean: true if a wall kick has been done, false else
	 */
	private boolean wallKick(int direction, Grid grid) {
		if (direction == 1) {
			this.moveRight(grid);
			return true;
		} else if (direction == -1) {
			this.moveLeft(grid);
			return true;
		}
		return false;
	}
	
	public void rotateLeft() {
		for (FallingTile ft: tiles) {
			Vector centerCoord = centerTile.getCoordinates();
			Vector ftCoord = ft.getCoordinates();
			Vector newCoord = new Vector(centerCoord.getX()+(centerCoord.getY()-ftCoord.getY()),
					centerCoord.getY()-(centerCoord.getX()-ftCoord.getX()));
			ft.setCoordinates(newCoord);
		}
	}
	
	public void rotateRight() {
		for (FallingTile ft: tiles) {
			Vector centerCoord = centerTile.getCoordinates();
			Vector ftCoord = ft.getCoordinates();
			Vector newCoord = new Vector(centerCoord.getX()-(centerCoord.getY()-ftCoord.getY()),
					centerCoord.getY()+(centerCoord.getX()-ftCoord.getX()));
			ft.setCoordinates(newCoord);
		}
	}
	
	/*
	 * Method used to simulate rotation of the tetromino
	 * @return preview: the simulated tetromino
	 */
	protected Tetromino rotateLeftPrev() {
		Tetromino preview = this.clone();
		for (FallingTile ft: preview.tiles) {
			Vector centerCoord = centerTile.getCoordinates();
			Vector ftCoord = ft.getCoordinates();
			Vector newCoord = new Vector(centerCoord.getX()+(centerCoord.getY()-ftCoord.getY()),
					centerCoord.getY()-(centerCoord.getX()-ftCoord.getX()));
			ft.setCoordinates(newCoord);
		}
		return preview;
	}
	
	/*
	 * Method used to simulate rotation of the tetromino
	 * @return preview: the simulated tetromino
	 */
	protected Tetromino rotateRightPrev() {
		Tetromino preview = this.clone();
		for (FallingTile ft: preview.tiles) {
			Vector centerCoord = centerTile.getCoordinates();
			Vector ftCoord = ft.getCoordinates();
			Vector newCoord = new Vector(centerCoord.getX()-(centerCoord.getY()-ftCoord.getY()),
					centerCoord.getY()+(centerCoord.getX()-ftCoord.getX()));
			ft.setCoordinates(newCoord);
		}
		return preview;
	}
	
	public void fall(Grid grid) {
		if (this.hasCollided(grid) == false) {
			for (FallingTile fallingTile : tiles) {
				fallingTile.getCoordinates().setY(fallingTile.getCoordinates().getY()-1);
			}
		}
	}
	
	protected boolean horizontalMoveCheck(int direction, Grid grid) {
		Tetromino preview = this.clone();
		boolean isPossible = true;
		
		if (direction == 1) {
			preview.moveRight();
			for (FallingTile fallingTile : preview.tiles) {
				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height && fallingTile.getCoordinates().getY() >= 0) {
					if (grid.getTile(fallingTile.getCoordinates()).isNull() == false){
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
		} else if (direction == -1) {
			preview.moveLeft();
			for (FallingTile fallingTile : preview.tiles) {
				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height && fallingTile.getCoordinates().getY() >= 0) {
					if (grid.getTile(fallingTile.getCoordinates()).isNull() == false){
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
		}
		return isPossible;
	}
	
	public void moveRight(Grid grid) {
		if (this.horizontalMoveCheck(1, grid)) {
			for (FallingTile fallingTile : tiles) {
				fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()+1);
			}
		}
	}
	
	public void moveLeft(Grid grid) {
		if (this.horizontalMoveCheck(-1, grid)) {
			for (FallingTile fallingTile : tiles) {
				fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()-1);
			}
		}
	}
	
	protected void moveRight() {
		for (FallingTile fallingTile : tiles) {
			fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()+1);
		}
	}
	
	protected void moveLeft() {
		for (FallingTile fallingTile : tiles) {
			fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()-1);
		}
	}
	
	public void makeStatic(Grid grid) {
		for (FallingTile fallingTile : tiles) {
			grid.setTile(fallingTile.getCoordinates().getX(), fallingTile.getCoordinates().getY(), fallingTile);
		}
	}
	
	protected abstract Tetromino clone();
	
	public void display(PApplet w) {
		for (FallingTile t : tiles) {
			int x = t.getCoordinates().getX();
			int y = t.getCoordinates().getY();
			
			if (y<20) {
				w.push();
				w.translate(x*Tile.SIZE,(19-y)*Tile.SIZE);
				t.display(w);
				w.pop();
			}
		}
	}
	
	public void displayHolder(PApplet w) {
		for (FallingTile t : tiles) {			
			int x = centerTile.getCoordinates().getX();
			int y = centerTile.getCoordinates().getY();
			
			w.push();
			
			w.scale(1, -1);
			
			w.translate((t.getCoordinates().getX()-x)*Tile.SIZE, (t.getCoordinates().getY()-y)*Tile.SIZE);
			t.display(w);
			w.pop();
		}
	}
	
	public void displayGhost(PApplet w, Grid grid) {
		Tetromino preview = this.clone();
		preview.hardDrop(grid);
		
		for (FallingTile t : preview.tiles) {
			int x = t.getCoordinates().getX();
			int y = t.getCoordinates().getY();
			
			if (y<20) {
				w.push();
				w.translate(x*Tile.SIZE,(19-y)*Tile.SIZE);
				t.display(w);
				w.fill(0,0,0,150);
				w.rect(0,0,Tile.SIZE,Tile.SIZE);
				w.pop();
			}
		}
	}
	
	public void hardDrop(Grid grid) {
		while (this.hasCollided(grid) == false) {
			this.fall(grid);
		}
	}
}
