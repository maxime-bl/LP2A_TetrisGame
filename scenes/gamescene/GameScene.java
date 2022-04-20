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
	private float fallingTime;
	private float lastFallingTime;
	private boolean hasSwapped;
	
	public GameScene() {
		queue = new TetQueue(5);
		holder = new TetHolder();
		currentTet = queue.getNext();
		scoreManager = new ScoreManager(0);
		grid = new Grid();		
		fallingTime = 1.0f;
		lastFallingTime = 0f;
		hasSwapped = false;
	}
	

	@Override
	public void processInput(PApplet window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(PApplet w) {
		w.background(70);
		
		w.push();
		
		w.translate(70,70);
		holder.display(w);
		
		w.translate(25 + 4 * Tile.SIZE, 0);
		grid.display(w);
		
		w.translate(10 + 10*Tile.SIZE, 0);
		queue.display(w);
		
		w.translate(-40, 300);
		scoreManager.display(w);
		
		w.pop();
		
		
	}
	
}
