package scenes.gamescene;

import java.util.ArrayList;
import java.util.List;

import processing.core.*;
import tiles.*;
import utils.ColorConstants;
import utils.Vector;

public class Grid {
	
	public class Line {
		
		private Tile[] line;
		
		public Line() {
			line = new Tile[10];
		}
		
		public Line(int width) {
			line = new Tile[width];
		}
		
		protected Tile[] getLine() {
			return this.line;
		}
		
		protected void setLine(Tile[] line) {
			this.line = line;
		}
	}
	
	final public int height= 20, width = 10;
	private List<Line> table;
	
	public Grid() {
		table = new ArrayList<Line>();
		for (int i = 0; i < this.height+2; i++) {
			table.add(new Line(width));
		}
		for (Line line : table) {
			for (int i = 0; i < this.width; i++) {
				line.getLine()[i] = new NullTile();
			}
		}
	}
	
	public Tile getTile(Vector vect) {
		return table.get(vect.getY()).getLine()[vect.getX()];
	}
	
	public Tile getTile(int posWidth, int posHeight) {
		return table.get(posHeight).getLine()[posWidth];
	}
	
	public void setTile(int posWidth, int posHeight, Tile tile) {
		table.get(posHeight).getLine()[posWidth] = tile;
	}
	
	public boolean checkLines() {
		int nbLines = 0;
		int index = 0;
		boolean isFull = true;
		boolean isLost = false;
		
		for (Line l: table) {
			if (index < 20) {
				for (int i = 0; i < this.width; i++) {
					if (l.getLine()[i].isNull()) {
						isFull = false;
					}
				}
				if (isFull) {
					nbLines++;
					table.remove(index);
					table.add(new Line(this.width));
				}
				isFull = true;
				index++;
			} else {
				for (int i = 0; i < this.width; i++) {
					if ( l.getLine()[i].isNull() == false) {
						isLost = true;
					}
				}
			}
		}
		ScoreManager.update(nbLines);
		return isLost;
	}
	
	public void display(PApplet w) {
		w.push();
		
		w.noStroke();
		
		int r = ColorConstants.CYAN.getRed();
		int g = ColorConstants.CYAN.getGreen();
		int b = ColorConstants.CYAN.getBlue();
		
		w.fill(r,g,b);
		w.rect(0, 0, Tile.SIZE*this.width+10, Tile.SIZE*this.height+10);
		
		w.translate(5f, 5f);
		
		w.translate(0, 19*Tile.SIZE);
		
		int index = 0;
		for (Line line : table) {
			if (index < 20) {
				for (int i = 0; i < this.width; i++) {
					line.getLine()[i].display(w);;
					w.translate(Tile.SIZE, 0);
				}
				w.translate(-width*Tile.SIZE, -Tile.SIZE);
			}
			index++;
		}
		
		w.pop();
	}
}
