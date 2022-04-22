package scenes;

import processing.core.PApplet;
import scenes.gamescene.GameScene;
import tetris_game.Game;
import utils.Button;
import utils.Color;
import utils.ColorConstants;
import utils.Button.ButtonMode;

public class GameOverMenu implements Scene{
	private Button restartBtn;
	private Button homeBtn;
	private Button quitBtn;
	private PApplet w;
	private int level;
	
	public GameOverMenu(PApplet window, int level) {
		this.w = window;
		this.level = level;
		this.restartBtn = new Button(w.width/2 - 65, 500, 110,45,"RESTART",w, ButtonMode.CENTER);
		this.homeBtn = new Button(w.width/2 + 65, 500, 110,45,"HOME",w, ButtonMode.CENTER, ColorConstants.GREEN);
		this.quitBtn = new Button(w.width/2, 560, 240,45,"QUIT GAME",w, ButtonMode.CENTER, ColorConstants.RED);
	}

	@Override
	public void processInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (restartBtn.isReleased()) {
			Game.setCurrentScene(new GameScene(w, level));
		} 
		if (homeBtn.isReleased()) {
			Game.setCurrentScene(new MainMenu(w));
		}
		if(quitBtn.isReleased()) {
			w.exit();
		}
	}

	@Override
	public void render() {
		w.background(40);
		restartBtn.display();
		homeBtn.display();
		quitBtn.display();		
	}

}
