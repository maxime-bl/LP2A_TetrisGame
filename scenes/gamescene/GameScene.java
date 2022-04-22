package scenes.gamescene;

import java.util.*;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import scenes.Scene;
import tetris_game.Game;
import tetrominos.Tetromino;
import tiles.Tile;
import utils.Button;
import utils.ColorConstants;
import utils.InputManager;

public class GameScene implements Scene {
	private final int fallingTimes[] = {1000,650,350,130,45};
	
	private Tetromino currentTet;
	private TetQueue queue;
	private TetHolder holder;
	private Grid grid;
	private ScoreManager scoreManager;
	private long fallingTime, lastFallingMillis, lastInputMillis;
	private boolean hasSwapped, isPaused;
	private int inputCooldown, level;
	private TimerClock clock;
	Button pauseBtn, restartBtn;
	PApplet w;
	
	
	public GameScene(PApplet window, int level) {
		this.w = window;
		queue = new TetQueue(5);
		holder = new TetHolder();
		currentTet = queue.getNext();
		scoreManager = new ScoreManager(0);
		grid = new Grid();		
		lastFallingMillis = lastInputMillis = 0;
		hasSwapped = false;
		inputCooldown = 70;
		this.level = level;
		fallingTime = fallingTimes[level-1];
		clock = new TimerClock();
		pauseBtn = new Button((w.width - 510)/2 + 125,600,120,45, "PAUSE", w);
		restartBtn = new Button((w.width - 510)/2 + 265,600,120,45, "RESTART", w);
		isPaused = false;
	}
	
	@Override
	public void processInput() {
		if (pauseBtn.isReleased()) {
			isPaused = !isPaused;
		}
		
		if (restartBtn.isReleased()) {
			Game.setCurrentScene(new GameScene(w, level));
		}
				
		if (!isPaused) {
			if (InputManager.getKeyDown(PConstants.UP) || InputManager.getKeyDown('x')) {
				currentTet.rotate(1, grid);
			}
			if (InputManager.getKeyDown(PConstants.CONTROL) || InputManager.getKeyDown('w')) {
				currentTet.rotate(-1, grid);
			}
			if ((InputManager.getKeyDown(PConstants.SHIFT) || InputManager.getKeyDown('c')) && !hasSwapped) {
				currentTet = holder.swap(currentTet, queue);
				currentTet.fall(grid);
				currentTet.fall(grid);
				hasSwapped = true;
			}
			if (InputManager.getKeyDown(' ')) {
				currentTet.hardDrop(grid);
				currentTet.makeStatic(grid);
			}
			
			
			
			if (System.currentTimeMillis() > lastInputMillis+inputCooldown) {	
				lastInputMillis = System.currentTimeMillis();
				if (InputManager.getKey(PConstants.LEFT)) {
					currentTet.moveLeft(grid);
				}
				if (InputManager.getKey(PConstants.RIGHT)) {
					currentTet.moveRight(grid);
				}
				if (InputManager.getKey(PConstants.DOWN)) {
					currentTet.fall(grid);
				}
			}
		}
	}


	@Override
	public void update() {
		if (!isPaused) {
			boolean gameOver = false;
			
			if (System.currentTimeMillis() > lastFallingMillis + fallingTime) {
				if (currentTet.hasCollided(grid)) {
					currentTet.makeStatic(grid);
					hasSwapped = false;
					//Check if the game is lost
					if (!grid.checkLines(scoreManager)) {
						currentTet = queue.getNext();
						currentTet.fall(grid);
						currentTet.fall(grid);
					} else {
						clock.setFinish(true);
					}
				} else {
					currentTet.fall(grid);
				}
				lastFallingMillis = System.currentTimeMillis();
			} 
		}
	}

	@Override
	public void render() {
		clock.update(w);
			
		w.background(70);
		
		w.push();
		pauseBtn.display();
		restartBtn.display();
		
		w.translate((w.width - 510) / 2,70);
		holder.display(w);
		w.translate(0,190);
		displayInfo(w, "TIMER", clock.toString());
		w.translate(0,80);
		scoreManager.display(w);
		w.translate(0,140);
		displayInfo(w, "LINES", "" + scoreManager.getNbLines());	
		
		w.translate(25 + 4 * Tile.SIZE, -410);
		grid.display(w);
		w.push();
		w.translate(5,5);
		currentTet.displayGhost(w, grid);
		currentTet.display(w);
		w.pop();
		
		w.translate(10 + 10*Tile.SIZE, 0);
		queue.display(w);
		w.translate(0, 445);
		displayLevel(w);
		
		w.pop();
	}
	
	
	private void displayInfo(PApplet w, String name, String value) {
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
		
		w.noStroke();
		w.push();
				
		int r = ColorConstants.CYAN.getRed();
		int g = ColorConstants.CYAN.getGreen();
		int b = ColorConstants.CYAN.getBlue();
		
		w.fill(r,g,b);
		w.rect(0, 0, 125, 65, 9, 0, 0, 9);
		
		w.fill(0);
		w.rect(5, 25, 120, 35, 9, 0, 0, 9);

		w.pop();
		
		w.push();
		
		w.textFont(font, 16);
		w.fill(0);
		w.text(name, 42, 20);
		
		w.textFont(font, 20);
		w.fill(255);
		w.textAlign(PConstants.CENTER);
		w.text(value, 65, 50);
		
		w.pop();
	}
	
	
	private void displayLevel(PApplet w) {
		int r = ColorConstants.CYAN.getRed();
		int g = ColorConstants.CYAN.getGreen();
		int b = ColorConstants.CYAN.getBlue();
		
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
				
		w.push();
		w.noStroke();
		
		w.fill(r,g,b);
		w.rect(0, 0, 4*Tile.SIZE + 25, 65, 0, 9, 9, 0);
		
		w.fill(0);
		w.rect(0, 25, 4*Tile.SIZE + 20, 35, 0, 9, 9, 0);
		
		
		//display the text
		w.textFont(font, 16);
		w.fill(0);
		w.textAlign(PConstants.CENTER);
		w.text("LEVEL", 60, 20);
		w.textFont(font, 20);
		w.fill(255);
		w.text("" + level, 60, 50);
				
		w.pop();
	}
}
