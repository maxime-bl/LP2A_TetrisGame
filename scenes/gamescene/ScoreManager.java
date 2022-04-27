package scenes.gamescene;

import processing.core.*;
import utils.ColorConstants;

public class ScoreManager {
	
	public ScoreSaver saver = new ScoreSaver();
	
	private int score;
	private boolean isBack2Back = false;
	private int nbLines;
	
	public ScoreManager() {
		this.score = 0;
		this.nbLines = 0;
	}
	
	public ScoreManager(int startScore) {
		this.score = startScore;
	}
	
	/**
	 * Method used to update the player's score
	 * @param nbLines: int -> number of line filled
	 */
	public void update(int nbLines) {
		if (nbLines < 4 && nbLines > 0) {
			this.score += nbLines * 100;
			this.isBack2Back = false;
		} else if (nbLines >= 4) {
			if (this.isBack2Back == true) {
				this.score += 1200;
			} else {
				this.score += 800;
			}
			isBack2Back = true;
		}
		this.nbLines += nbLines;
	}
	
	public String toString() {
		return "Score : " + this.score;
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
		w.rect(0, 0, 125, 125, 9, 0, 0, 9);
		
		w.fill(0);
		w.rect(5, 25, 120, 35, 9, 0, 0, 9);
		w.rect(5, 85, 120, 35, 9, 0, 0, 9);

		
		w.pop();
		
		w.push();
		
		w.textAlign(PConstants.CENTER);
		w.textFont(font, 16);
		w.fill(0);
		w.text("SCORE", 65, 20);
		w.text("TOP SCORE", 65, 80);
		w.fill(255);
		w.text(""+saver.getBestScore(), 65, 110);
		
		w.textSize(20);
		w.fill(255);
		w.textAlign(PConstants.CENTER);
		w.text(this. score, 65, 50);
		
		w.pop();
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getNbLines() {
		return this.nbLines;
	}
}
