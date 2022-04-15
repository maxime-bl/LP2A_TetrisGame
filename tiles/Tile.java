package tiles;

import processing.core.PApplet;
import utils.*;

/*
 *	The tile class represent one tile composing a tetromino
 */
public class Tile {

	private Color color;

	public Tile() {
		this.color = ColorConstants.BLACK;
	}
	
	public Tile(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}	
	
	public void display(PApplet w) {
		w.push();
		
		w.noStroke();
		float r = color.getRed();
		float g = color.getGreen();
		float b = color.getBlue();
				
		w.fill(r,g,b);
		w.triangle(0,0,25,0,13,13);
				
		w.fill(r*0.6f, g*0.6f, b*0.6f);
		w.triangle(0,25,25,25,13,13);		
		
		w.fill(r*0.9f, g*0.9f, b*0.9f);
		w.triangle(0,0,0,25,13,13);
		
		w.fill(r*0.7f, g*0.7f, b*0.7f);
		w.triangle(25,0,25,25,13,13);
		
		w.fill(r*0.85f, g*0.85f, b*0.85f);
		w.rect(4, 4, 17, 17);
		
		w.fill(255, 255, 255, 170);
		w.triangle(4,4,21,4,4,9);
		
		w.pop();
	}
	
	public boolean isNull() {
		return color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0;
	}
}
