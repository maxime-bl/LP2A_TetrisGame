package tetris_game;


import processing.core.*;
import scenes.Scene;
import tiles.FallingTile;
import tiles.Tile;
import utils.*;

public class Game extends PApplet{
	private int windowWidth = 1280, windowHeight = 720;
	private Scene currentScene;
	Tile tile1 = new Tile(new Color(ColorConstants.GREEN));
	Tile tile2 = new Tile(new Color(ColorConstants.BLUE));
	Tile tile3 = new Tile(new Color(ColorConstants.YELLOW));
	Tile tile4 = new Tile(new Color(ColorConstants.PURPLE));
	Tile tile5 = new Tile(new Color(ColorConstants.SKY_BLUE));
	Tile tile6 = new Tile(new Color(ColorConstants.ORANGE));
	Tile tile7 = new Tile(new Color(ColorConstants.RED));

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
		
		translate(25,0);
		tile7.display(this);
		pop();
		
	}
	
	public void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}
}
