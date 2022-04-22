package scenes.gamescene;

import processing.core.*;
import tetrominos.*;
import utils.ColorConstants;

public class TetHolder {
	
	private Tetromino tetInHold;
	
	public TetHolder() {
		tetInHold = null;
	}
	
	/*
	 * Switch between the tetromino in the holder and the current one
	 * @current : the current tetromino
	 * @return : a tetromino if there is one in the holder, null else
	 */
	public Tetromino swap(Tetromino current, TetQueue queue){
		//reset the current Tetromino
		if (current.getColor() == ColorConstants.BLUE) {
			current = new TetJ();
		} else if (current.getColor() == ColorConstants.GREEN) {
			current = new TetS();
		} else if (current.getColor() == ColorConstants.ORANGE) {
			current = new TetL();
		} else if (current.getColor() == ColorConstants.PURPLE) {
			current = new TetT();
		} else if (current.getColor() == ColorConstants.RED) {
			current = new TetZ();
		} else if (current.getColor() == ColorConstants.SKY_BLUE) {
			current = new TetI();
		} else {
			current = new TetO();
		}
		
		if (tetInHold == null) {
			this.tetInHold = current;
			return queue.getNext();
		} else {
			Tetromino temp = tetInHold;
			this.tetInHold = current;
			return temp;
		}
	}
	
	public void display(PApplet w) {
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
		
		w.noStroke();
		w.push();
				
		int r = ColorConstants.CYAN.getRed();
		int g = ColorConstants.CYAN.getGreen();
		int b = ColorConstants.CYAN.getBlue();
		
		w.fill(r,g,b);
		w.rect(0, 0, 125, 160, 9, 0, 0, 9);
		
		w.fill(0);
		w.rect(5, 25, 120, 130, 9, 0, 0, 9);
		
		w.pop();
		
		w.push();
		
		w.textFont(font, 16);
		w.fill(0);
		w.text("HOLD", 42, 20);
		
		w.pop();
		
		if (tetInHold != null) {
			w.push();
			
			w.translate(40, 90);
			tetInHold.displayHolder(w);
			
			w.pop();
		}
	}
}
