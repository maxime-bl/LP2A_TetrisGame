package scenes.gamescene;

import processing.core.*;

public class ScoreManager {

	private static int score;
	private static boolean isBack2Back = false;
	
	public ScoreManager() {
		score = 0;
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
	}
	
	public String toString() {
		return "Score : " + ScoreManager.score;
	}
	
	public void display(PApplet w) {
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
		
		w.push();
		w.textFont(font, 30);
		w.fill(255);
		w.text(this.toString(), 50, 50); 
		w.pop();
	}
}
