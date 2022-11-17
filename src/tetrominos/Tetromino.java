package tetrominos;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;
import scenes.gamescene.Grid;
import utils.*;
import tiles.FallingTile;
import tiles.Tile;

public abstract class Tetromino {
	
	/*
	 * Datas for the wall kicks
	 */
	private final static HashMap<String, Vector> wallKickDataJLTSZ;
	
	static {
		wallKickDataJLTSZ = new HashMap<String, Vector>();
		wallKickDataJLTSZ.put("0>>1_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("0>>1_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("0>>1_3", new Vector(-1, 1));
		wallKickDataJLTSZ.put("0>>1_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("0>>1_5", new Vector(-1, -2));
		
		wallKickDataJLTSZ.put("1>>0_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("1>>0_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("1>>0_3", new Vector(1, -1));
		wallKickDataJLTSZ.put("1>>0_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("1>>0_5", new Vector(1, 2));
		
		wallKickDataJLTSZ.put("1>>2_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("1>>2_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("1>>2_3", new Vector(1, -1));
		wallKickDataJLTSZ.put("1>>2_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("1>>2_5", new Vector(1, 2));
		
		wallKickDataJLTSZ.put("2>>1_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("2>>1_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("2>>1_3", new Vector(-1, 1));
		wallKickDataJLTSZ.put("2>>1_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("2>>1_5", new Vector(-1, -2));
		
		wallKickDataJLTSZ.put("2>>3_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("2>>3_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("2>>3_3", new Vector(1, 1));
		wallKickDataJLTSZ.put("2>>3_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("2>>3_5", new Vector(1, -2));
		
		wallKickDataJLTSZ.put("3>>2_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("3>>2_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("3>>2_3", new Vector(-1, -1));
		wallKickDataJLTSZ.put("3>>2_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("3>>2_5", new Vector(-1, 2));
		
		wallKickDataJLTSZ.put("3>>0_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("3>>0_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("3>>0_3", new Vector(-1, -1));
		wallKickDataJLTSZ.put("3>>0_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("3>>0_5", new Vector(-1, 2));
		
		wallKickDataJLTSZ.put("0>>3_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("0>>3_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("0>>3_3", new Vector(1, 1));
		wallKickDataJLTSZ.put("0>>3_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("0>>3_5", new Vector(1, -2));
	}

	protected ArrayList<FallingTile> tiles;
	protected Color color;
	protected FallingTile centerTile;
	protected int actualRot = 0;
	
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
	
	/**
	 * Method used to check if a tetromino has collided with other tiles
	 * @param grid: the actual game grid
	 * @return isColliding: boolean
	 */
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
	
	/**
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
				trans = wallKickDataJLTSZ.get(ope);
				
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
			trans = wallKickDataJLTSZ.get(ope);
			for (FallingTile fallingTile : tiles) {
				coord = fallingTile.getCoordinates();
				coord.setX(coord.getX()+trans.getX());
				coord.setY(coord.getY()+trans.getY());
				fallingTile.setCoordinates(coord);
			}
			this.actualRot = wantedRot;
		}
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
  
	/**
	 * Method used to make a tetromino fall of 1 case
	 * @param grid : the actual game grid
	 * @return boolean: true = tetromino fell of 1 case, false = it didn't fall
	 */
	public boolean fall(Grid grid) {
		if (this.hasCollided(grid) == false) {
			for (FallingTile fallingTile : tiles) {
				fallingTile.getCoordinates().setY(fallingTile.getCoordinates().getY()-1);
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * As the tetromino spawn outside the grid, this method is used to fix the height of spawn of the tetromino.
	 * Note :
	 * If the tetromino can't spawn totally inside the grid, then it spawn if possible 1 line upper. If it can't spawn 1
	 * line upper, then we make it spawn 2 lines upper.
	 * @param grid : the actual game grid 
	 * @return boolean
	 */
	public boolean fixDefaultHeight(Grid grid) {
		if (this.fall(grid)) {
			this.fall(grid);
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Method used to check if an horizontal movement in a particular direction is possible
	 * @param direction: int
	 * @param grid : the actual game grid
	 * @return boolean: true if the movement is possible, false else
	 */
	protected boolean horizontalMoveCheck(int direction, Grid grid) {
		Tetromino preview = this.clone();
		boolean isPossible = true;
		
		// Check if a translation to the right is possible
		if (direction == 1) {
			preview.moveRight();
			for (FallingTile fallingTile : preview.tiles) {
				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height+1 && fallingTile.getCoordinates().getY() >= 0) {
					if (grid.getTile(fallingTile.getCoordinates()).isNull() == false){
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
		// Check if a translation to the left is possible
		} else if (direction == -1) {
			preview.moveLeft();
			for (FallingTile fallingTile : preview.tiles) {
				if (fallingTile.getCoordinates().getX() < grid.width && fallingTile.getCoordinates().getX() >= 0
						&& fallingTile.getCoordinates().getY() < grid.height+1 && fallingTile.getCoordinates().getY() >= 0) {
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
	
	/**
	 * Method used to move the tetromino to the right
	 * @param grid: the actual game grid
	 */
	public void moveRight(Grid grid) {
		if (this.horizontalMoveCheck(1, grid)) {
			for (FallingTile fallingTile : tiles) {
				fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()+1);
			}
		}
	}
	
	/**
	 * Method used to move the tetromino to the left
	 * @param grid: the actual game grid
	 */
	public void moveLeft(Grid grid) {
		if (this.horizontalMoveCheck(-1, grid)) {
			for (FallingTile fallingTile : tiles) {
				fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()-1);
			}
		}
	}
	
	/**
	 * Method to move to the right a preview of a tetromino
	 */
	protected void moveRight() {
		for (FallingTile fallingTile : tiles) {
			fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()+1);
		}
	}
	
	/**
	 * Method to move to the left a preview of a tetromino
	 */
	protected void moveLeft() {
		for (FallingTile fallingTile : tiles) {
			fallingTile.getCoordinates().setX(fallingTile.getCoordinates().getX()-1);
		}
	}
	
	/**
	 * Method used to fix the tetromino inside the grid
	 * @param grid: the actual grid
	 */
	public void makeStatic(Grid grid) {
		for (FallingTile fallingTile : tiles) {
			grid.setTile(fallingTile.getCoordinates().getX(), fallingTile.getCoordinates().getY(), fallingTile);
		}
	}
	
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
	
	/**
	 * Method used to display a tetromino in a sidebar
	 */
	public void displayHolder(PApplet w) {
		for (FallingTile t : tiles) {			
			int x = centerTile.getCoordinates().getX();
			int y = centerTile.getCoordinates().getY();
			
			w.push();
			
			//w.scale(1, -1);
			
			w.translate((t.getCoordinates().getX()-x)*Tile.SIZE, -(t.getCoordinates().getY()-y)*Tile.SIZE);
			t.display(w);
			w.pop();
		}
	}
	
	/**
	 * Method used to display the falling tetromino ghost
	 */
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
	
	/**
	 * Method used to clone a tetromino
	 */
	protected abstract Tetromino clone();
}
