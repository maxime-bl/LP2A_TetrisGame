package tetris_game;


import processing.core.*;
import scenes.Scene;
import tiles.FallingTile;
import tiles.Tile;
import utils.*;

public class Game extends PApplet{
	private int windowWidth = 1280, windowHeight = 720;
	private Scene currentScene;
	Tile tile1 = new Tile(new Color());
	/*Tile tile2 = new Tile(Color.blu);
	Tile tile3 = new Tile(Color.YELLOW);
	Tile tile4 = new Tile(Color.magenta);
	Tile tile5 = new Tile(Color.cyan);
	Tile tile6 = new Tile(Color.red);*/

	public static void main(String[] args) {
		PApplet.main("tetris_game.Game");
	}

	//function that is called before creating the window (PApplet)
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	//called once after the PApplet is created
	public void setup() {
		frameRate(50);
		
	}
	
	//called every 1/60 of second
	public void draw() {
		background(0);
		
		push();
		translate(50,50);
		
		tile1.display(this);
		
		translate(25,0);
		tile2.display(this);
		
		translate(25,0);
		tile3.display(this);
		
		translate(25,0);
		tile4.display(this);
		
		translate(25,0);
		tile5.display(this);
		
		translate(25,0);
		tile6.display(this);
		pop();
		
	}
	
	public void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}
}
