package scenes.gamescene;

import java.util.*;

import processing.core.PApplet;
import scenes.Scene;
import tetrominos.Tetromino;
import tiles.Tile;

public class GameScene implements Scene {
	private Tetromino currentTet;
	private TetQueue queue;
	private TetHolder holder;
	private Grid grid;
	private ScoreManager scoreManager;
	private long fallingTime;
	private long lastFallingMillis, lastInputMillis;
	private boolean hasSwapped;
	private int minDelayBeetwenFalls;
	private boolean isSoftDropPressed, wasKeyPressed;
	
	public GameScene() {
		queue = new TetQueue(5);
		holder = new TetHolder();
		currentTet = queue.getNext();
		scoreManager = new ScoreManager(0);
		grid = new Grid();		
		fallingTime = 300;
		lastFallingMillis = lastInputMillis = 0;
		hasSwapped = false;
		minDelayBeetwenFalls = 100;
		isSoftDropPressed = false;
	}
	

	@Override
	public void processInput(PApplet w) {
		if (w.keyPressed && !wasKeyPressed) {
			if (w.keyCode == w.DOWN) isSoftDropPressed = true;
			if (w.keyCode == w.CONTROL || w.key == 'w' || w.key == 'W') currentTet.rotateLeft();
			if (w.keyCode == w.UP || w.key == 'x' || w.key == 'X') currentTet.rotateRight();
			if (w.keyCode == w.LEFT) currentTet.moveLeft();
			if (w.keyCode == w.RIGHT) currentTet.moveRight();
			if (w.key == ' ') { 
				//hard drop//
			}
		} else {
			if (w.keyCode == w.DOWN) {
				isSoftDropPressed = false;
			}
		}
		
		if (isSoftDropPressed && System.currentTimeMillis() > lastFallingMillis + minDelayBeetwenFalls) {
			currentTet.fall();
		}
		
		wasKeyPressed = w.keyPressed;
	}

	@Override
	public void update() {
		boolean gameOver = false;
		
		if (System.currentTimeMillis() > lastFallingMillis + fallingTime) {
			if (currentTet.hasCollided(grid)) {
				currentTet.makeStatic(grid);
				hasSwapped = false;
				//Check if the game is lost
				if (!grid.checkLines()) {
				currentTet = queue.getNext();
				}
			} else {
				currentTet.fall();
			}
			lastFallingMillis = System.currentTimeMillis();
		} 
	}

	@Override
	public void render(PApplet w) {
		w.background(70);
		
		w.push();
		
		w.translate(70,70);
		holder.display(w);
		
		w.translate(25 + 4 * Tile.SIZE, 0);
		grid.display(w);
		w.push();
		w.translate(5,5);
		currentTet.display(w);
		w.pop();
		
		w.translate(10 + 10*Tile.SIZE, 0);
		queue.display(w);
		
		w.translate(-40, 300);
		scoreManager.display(w);
		
		w.pop();
		
		
	}
	
}
