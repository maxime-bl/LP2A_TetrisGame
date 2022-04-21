package scenes.gamescene;

import java.util.*;

import processing.core.PApplet;
import scenes.Scene;
import tetrominos.Tetromino;
import tiles.Tile;
import utils.InputManager;

public class GameScene implements Scene {
	private Tetromino currentTet;
	private TetQueue queue;
	private TetHolder holder;
	private Grid grid;
	private ScoreManager scoreManager;
	private long fallingTime;
	private long lastFallingMillis, lastInputMillis;
	private boolean hasSwapped;
	private int inputCooldown;
	private boolean isSoftDropPressed;
	
	public GameScene() {
		queue = new TetQueue(5);
		holder = new TetHolder();
		currentTet = queue.getNext();
		scoreManager = new ScoreManager(0);
		grid = new Grid();		
		fallingTime = 300;
		lastFallingMillis = lastInputMillis = 0;
		hasSwapped = false;
		inputCooldown = 50;
		isSoftDropPressed = false;
	}
	
	@Override
	public void processInput(PApplet w) {
		
		
		if (InputManager.getKeyDown(w.UP) || InputManager.getKeyDown('x')) {
			currentTet.rotate(1, grid);
		}
		
		if (InputManager.getKeyDown(w.CONTROL) || InputManager.getKeyDown('w')) {
			currentTet.rotate(-1, grid);
		}
		
		if ((InputManager.getKeyDown(w.SHIFT) || InputManager.getKeyDown('c')) && !hasSwapped) {
			currentTet = holder.swap(currentTet, queue);
			hasSwapped = true;
		}
		
		
			
			
		if (System.currentTimeMillis() > lastInputMillis+inputCooldown) {	
			lastInputMillis = System.currentTimeMillis();
			
			if (InputManager.getKey(w.LEFT)) {
				currentTet.moveLeft(grid);
			}
			
			if (InputManager.getKey(w.RIGHT)) {
				currentTet.moveRight(grid);
			}
			
			if (InputManager.getKey(w.DOWN)) {
				currentTet.fall(grid);
			}
		}
		
	
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
				currentTet.fall(grid);
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
