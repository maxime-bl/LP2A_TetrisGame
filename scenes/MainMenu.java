package scenes;

import processing.core.PApplet;
import processing.core.PImage;
import scenes.gamescene.GameScene;
import scenes.gamescene.ScoreSaver;
import tetris_game.Game;
import utils.Button;
import utils.Button.ButtonMode;
import utils.Color;
import utils.ColorConstants;

public class MainMenu implements Scene {
	private int level = 1;
	private PApplet w;
	private Button playBtn;
	private Button levelBtn;
	private Button quitBtn;
	private ScoreSaver scoreSaver;
	
	public MainMenu(PApplet window) {
		this.w = window;
		playBtn = new Button(w.width/2 - 70, 530, 100, 45, "PLAY", w, ButtonMode.CENTER);
		levelBtn = new Button(w.width/2 + 60, 530, 120, 45, "LEVEL : " + level, w, ButtonMode.CENTER, new Color(200,200,200));
		quitBtn = new Button(w.width/2, 590, 240, 45, "QUIT GAME", w, ButtonMode.CENTER, ColorConstants.RED);
		scoreSaver = new ScoreSaver();
	}
	
	@Override
	public void processInput() {
		
	}

	
	@Override
	public void update() {
		if (playBtn.isReleased()) {
			Game.setCurrentScene(new GameScene(w, level));
		} 
		if (levelBtn.isReleased()) {
			level ++;
			if (level > 5) {
				level = 1;
			}
			levelBtn.setText("LEVEL : " + level);
		}
		if(quitBtn.isReleased()) {
			w.exit();
		}
	}

	@Override
	public void render() {
		w.background(40);
		
		PImage img = w.loadImage("./resources/Tetris_concept.png");
		w.image(img, 385, 50, img.width/2, img.height/2);
		
		playBtn.display();
		levelBtn.display();
		quitBtn.display();
		
		w.push();
		w.translate(w.width/2 - 120, 300);
		scoreSaver.displayBestScores(w);
		w.pop();
		
	}

}
