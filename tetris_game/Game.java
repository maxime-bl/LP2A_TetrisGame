package tetris_game;

import scenes.gamescene.ScoreManager;

import processing.core.*;
import scenes.Scene;
import scenes.gamescene.*;
import tetrominos.*;
import tiles.*;
import utils.*;

public class Game extends PApplet{
	private int windowWidth = 1280, windowHeight = 720;
	private Scene currentScene;
	
	private ScoreManager score = new ScoreManager(250);
	Grid grid = new Grid();
	TetHolder hold = new TetHolder();
	Tile tile1 = new Tile(ColorConstants.GREEN);
	Tile tile2 = new Tile(ColorConstants.BLUE);
	Tile tile3 = new Tile(ColorConstants.YELLOW);
	Tile tile4 = new Tile(ColorConstants.PURPLE);
	Tile tile5 = new Tile(ColorConstants.SKY_BLUE);
	Tile tile6 = new Tile(ColorConstants.ORANGE);
	Tile tile7 = new Tile(ColorConstants.RED);

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
		currentScene = new GameScene();
		
		hold.swap(new TetL());
	}
	
	//called every 1/60 of second
	public void draw() {
		push();
		background(0);
		
		hold.display(this);
		
		translate(125, 0);
		
		grid.display(this);
		pop();
		/*
		
		//translate(50,50);
		Tetromino t = new TetI();
		t.display(this);
		
		*/
		
	}
	
	public void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}
}
