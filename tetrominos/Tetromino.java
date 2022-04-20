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
	
	protected boolean hasCollided(Grid grid) {
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
	
	public void update() {
		
	}
	
	private void makeStatic() {
		
	}
	
	public void display(PApplet w) {
		for (FallingTile t : tiles) {
			int x = t.getCoordinates().getX();
			int y = t.getCoordinates().getY();
			
			w.push();
			w.translate(x*Tile.SIZE,(19-y)*Tile.SIZE);
			t.display(w);
			w.pop();
		}
	}
}
