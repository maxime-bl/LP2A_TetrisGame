package scenes.gamescene;

import processing.core.*;
import utils.ColorConstants;

public class ScoreManager {

	private static int score;
	private static boolean isBack2Back = false;
	private static int nbLines;
	
	public ScoreManager() {
		score = 0;
		nbLines = 0;
	}
	
	public ScoreManager(int startScore) {
		ScoreManager.score = startScore;
	}
	
	public static void update(int nbLines) {
		if (nbLines < 4) {
			ScoreManager.score += nbLines * 100;
			ScoreManager.isBack2Back = false;
		} else {
			if (ScoreManager.isBack2Back == true) {
				ScoreManager.score += 1200;
			} else {
				ScoreManager.score += 800;
			}
			ScoreManager.isBack2Back = true;
		}
		ScoreManager.nbLines = nbLines;
	}
	
	public String toString() {
		return "Score : " + ScoreManager.score;
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
		
		w.textSize(20);
		w.fill(255);
		w.textAlign(PConstants.CENTER);
		w.text(score, 65, 50);
		
		w.pop();
	}
	
	public int getScore() {
		return ScoreManager.score;
	}
	
	public int getNbLines() {
		return ScoreManager.nbLines;
	}
}
