package tetris_game;

import scenes.gamescene.ScoreManager;

import java.util.*;

import processing.core.*;
import scenes.MainMenu;
import scenes.*;
import tetrominos.*;
import tiles.*;
import utils.*;

public class Game extends PApplet{
	private final int windowWidth = 1280, windowHeight = 720;
	private static Scene currentScene;

	public static void main(String[] args) {
		PApplet.main("tetris_game.Game");
	}
	
	public static void setCurrentScene(Scene scene) {
		currentScene = scene;
	}

	
	//function that is called before creating the window (PApplet)
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	//called once after the PApplet is created
	public void setup() {
		frameRate(60);
		currentScene = new MainMenu(this);
	}
	
	//called every 1/60 of second
	public void draw() {
		currentScene.processInput();
		currentScene.update();
		currentScene.render();		
		
		InputManager.clear();
	}
	
	
	//called every time a key is pressed
	public void keyPressed() {
		if (key == PConstants.CODED) {
			InputManager.setKeyPressed(this.keyCode);
		} else {
			InputManager.setKeyPressed(this.key);
		}
	}
	
	//called once every time a key is released
	public void keyReleased() {
		if (key == PConstants.CODED) {
			InputManager.setKeyReleased(this.keyCode);
		} else {
			InputManager.setKeyReleased(this.key);
		}
	}
	
	//called every time a mouse button is pressed
	public void mousePressed() {
		InputManager.setMousePressed();
	}
	
	
	//called once every time a mouse buttonn is released
	public void mouseReleased() {
		InputManager.setMouseReleased();
	}
}
