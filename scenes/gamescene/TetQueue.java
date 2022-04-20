package scenes.gamescene;

import java.util.*;

import processing.core.PApplet;
import tetrominos.*;
import tiles.Tile;

public class TetQueue {
	private Queue<Tetromino> queue;
	private int size;
	
	public TetQueue(int size) {
		this.size = size;
		queue = new LinkedList<>();
		queue.addAll(generate7Bag());
	}

	public Tetromino getNext() {
		Tetromino nextTet = queue.remove();
		if (queue.size() < this.size) {
			queue.addAll(generate7Bag());
		}
		return nextTet;
	}
	
	
	public static HashSet<Tetromino> generate7Bag() {
		HashSet<Tetromino> bag = new HashSet<>();
		bag.add(new TetI());
		bag.add(new TetJ());
		bag.add(new TetL());
		bag.add(new TetO());
		bag.add(new TetS());
		bag.add(new TetT());
		bag.add(new TetZ());
		
		return bag;
	}
	

	
	public void display(PApplet w) {
		w.push();
		w.fill(0);
		w.rect(0, 0, 4*Tile.SIZE, 3*Tile.SIZE*queue.size()+1);
		
		w.translate(Tile.SIZE, Tile.SIZE);
		
		int i=0;
		for (Tetromino t : this.queue) {
			if (i >= this.size) break;
			t.display(w);
			w.translate(0, 3*Tile.SIZE);
			i ++;
		}
		
		w.pop();
	}
	
}
