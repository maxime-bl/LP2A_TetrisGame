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
	private long lastFallingMillis;
	private boolean hasSwapped;
	private int delayBeetwenFalls;
	
	public GameScene() {
		queue = new TetQueue(5);
		holder = new TetHolder();
		currentTet = queue.getNext();
		scoreManager = new ScoreManager(0);
		grid = new Grid();		
		fallingTime = 1000;
		lastFallingMillis = 0;
		hasSwapped = false;
		
	}
	

	@Override
	public void processInput(PApplet window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (System.currentTimeMillis() > lastFallingMillis + fallingTime) {
			if (currentTet.hasCollided(grid)) {
				currentTet.makeStatic();
				hasSwapped = false;
				currentTet = queue.getNext();
			} else {
				currentTet.fall();
			}
			lastFallingMillis = System.currentTimeMillis();
		} 
		
		//check for complete lines
		
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
