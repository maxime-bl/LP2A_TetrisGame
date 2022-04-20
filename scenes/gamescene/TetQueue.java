package scenes.gamescene;

import java.util.*;

import processing.core.PApplet;
import tetrominos.*;
import tiles.Tile;

public class TetQueue {
	private Random random;
	private Queue<Tetromino> queue;
	
	public static void main(String[] args) {
		TetQueue test = new TetQueue();
	}
	
	public TetQueue() {
		for (int i=0; i<3; i++) {
			addRandomTet();
		}
	}
	
	public Tetromino getNext() {
		Tetromino tetToReturn = queue.remove();
		addRandomTet();
		return tetToReturn;
	}
	
	private void addRandomTet() {
		switch (random.nextInt(7)) {
		case 0:
			queue.add(new TetI());
			break;
		case 1:
			queue.add(new TetJ());
			break;
		case 2:
			queue.add(new TetL());
			break;
		case 3:
			queue.add(new TetO());
			break;
		case 4:
			queue.add(new TetS());
			break;
		case 5:
			queue.add(new TetT());
			break;
		case 6:
			queue.add(new TetZ());
			break;
		}
	}
	
	public void display(PApplet w) {
		w.push();
		w.fill(0);
		w.rect(0, 0, 4*Tile.SIZE, 3*Tile.SIZE*queue.size()+1);
		
		w.translate(Tile.SIZE, Tile.SIZE);
		for (Tetromino t : this.queue) {
			t.display(w);
			w.translate(0, 3*Tile.SIZE);
		}
		
		w.pop();
	}
	
}
