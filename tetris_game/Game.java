package tetris_game;

import scenes.gamescene.ScoreManager;

import java.util.*;

import processing.core.*;
import scenes.Scene;
import scenes.gamescene.*;
import tetrominos.*;
import tiles.*;
import utils.*;

public class Game extends PApplet{
	private int windowWidth = 1280, windowHeight = 720;
	private Scene currentScene;
	private Set<Character> keysPressed;
	private Set<Integer> codedKeysPressed;
	
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
	
	public void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}

	
	//function that is called before creating the window (PApplet)
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	//called once after the PApplet is created
	public void setup() {
		frameRate(60);
		currentScene = new GameScene();
		
		//hold.swap(new TetL());
		
		keysPressed = new HashSet<>();
		codedKeysPressed = new HashSet<>();
	}
	
	//called every 1/60 of second
	public void draw() {
		currentScene.processInput(this);
		currentScene.update();
		currentScene.render(this);		
		
		InputManager.clear();
	}
	
	
	//called every time a key is pressed
	public void keyPressed() {
		if (key == this.CODED) {
			InputManager.setKeyPressed(this.keyCode);
		} else {
			InputManager.setKeyPressed(this.key);
		}
	}
	
	//called once every time a key is released
	public void keyReleased() {
		if (key == this.CODED) {
			InputManager.setKeyReleased(this.keyCode);
		} else {
			InputManager.setKeyReleased(this.key);
		}
	}
	
	
}
