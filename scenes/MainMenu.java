package scenes;

import processing.core.PApplet;
import processing.core.PImage;
import scenes.gamescene.GameScene;
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
	
	public MainMenu(PApplet window) {
		this.w = window;
		playBtn = new Button(w.width/2 - 70, 500, 100, 45, "PLAY", w, ButtonMode.CENTER);
		levelBtn = new Button(w.width/2 + 60, 500, 120, 45, "LEVEL : " + level, w, ButtonMode.CENTER, new Color(200,200,200));
		quitBtn = new Button(w.width/2, 560, 240, 45, "QUIT GAME", w, ButtonMode.CENTER, ColorConstants.RED);
	}
	
	@Override
	public void processInput() {
		// TODO Auto-generated method stub
		
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
		w.image(img, 470, 50, img.width/3, img.height/3);
		
		playBtn.display();
		levelBtn.display();
		quitBtn.display();
	}

}
