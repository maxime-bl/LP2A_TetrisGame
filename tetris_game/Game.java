package tetris_game;

import scenes.gamescene.ScoreManager;
import scenes.gamescene.TimerClock;

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
	
	/**
	 * Sets the scene that is updated and rendered on the screen
	 * @param scene The Scene to set to current
	 */
	public static void setCurrentScene(Scene scene) {
		currentScene = scene;
	}

	
	/**
	 * Called by the PApplet once, before creating the window
	 */
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	/**
	 * Called by the PApplet once, after the window was created
	 */
	public void setup() {
		frameRate(60);
		currentScene = new MainMenu(this);
	}
	
	/**
	 * Called by the PApplet 60 times a second (if the frameRate is set 60) after the window was created
	 */
	public void draw() {
		currentScene.processInput();
		currentScene.update();
		currentScene.render();		
		
		InputManager.clear();
	}
	
	
	/**
	 * Called by the PApplet has long as at least one key of the keyboard is pressed
	 */
	public void keyPressed() {
		if (key == PConstants.CODED) {
			InputManager.setKeyPressed(this.keyCode);
		} else {
			InputManager.setKeyPressed(this.key);
		}
	}
	
	/**
	 * Called by the PApplet once every time a key of the keyboard is released
	 */
	public void keyReleased() {
		if (key == PConstants.CODED) {
			InputManager.setKeyReleased(this.keyCode);
		} else {
			InputManager.setKeyReleased(this.key);
		}
	}
	
	/**
	 * Called by the PApplet once every time a mouse button is pressed
	 */
	public void mousePressed() {
		InputManager.setMousePressed();
	}
	
	
	/**
	 * Called by the PApplet once every time a mouse button is released
	 */
	public void mouseReleased() {
		InputManager.setMouseReleased();
	}
}
