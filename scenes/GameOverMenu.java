package scenes;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import scenes.gamescene.GameScene;
import scenes.gamescene.ScoreManager;
import scenes.gamescene.ScoreSaver;
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
	private ScoreSaver scoreSaver;
	private int score;
	
	public GameOverMenu(PApplet window, int level, int score) {
		this.w = window;
		this.level = level;
		this.score = score;
		this.scoreSaver = new ScoreSaver();
		this.restartBtn = new Button(w.width/2 - 65, 540, 110,45,"RESTART",w, ButtonMode.CENTER);
		this.homeBtn = new Button(w.width/2 + 65, 540, 110,45,"HOME",w, ButtonMode.CENTER, ColorConstants.GREEN);
		this.quitBtn = new Button(w.width/2, 600, 240,45,"QUIT GAME",w, ButtonMode.CENTER, ColorConstants.RED);
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
		
		w.push();
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
		
		w.translate(w.width/2, 180);
		w.noStroke();
		w.fill(ColorConstants.RED.getRed(), ColorConstants.RED.getGreen(), ColorConstants.RED.getBlue());
		w.textSize(80);
		w.textAlign(PConstants.CENTER);
		w.text("GAME OVER",0,0);


		w.translate(-120, 50);
		w.fill(ColorConstants.CYAN.getRed(), ColorConstants.CYAN.getGreen(), ColorConstants.CYAN.getBlue());
		w.rect(0,0,240,60,9);
		
		w.textFont(font, 16);
		w.fill(0);
		w.text("YOUR SCORE", 120, 20);
		
		w.fill(0);
		w.rect(5,25,230,30,9);
		
		w.textFont(font, 20);
		w.fill(255);
		w.text(score, 120,48);
		
		w.translate(0, 80);
		scoreSaver.displayBestScores(w);
		w.pop();
	}

}
