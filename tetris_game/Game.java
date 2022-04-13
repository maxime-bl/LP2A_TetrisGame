package tetris_game;

import java.awt.Color;

import processing.core.*;
import scenes.Scene;
import tiles.FallingTile;
import tiles.Tile;

public class Game extends PApplet{
	private int windowWidth = 1280, windowHeight = 720;
	private Scene currentScene;

	public static void main(String[] args) {
		PApplet.main("tetris_game.Game");
	}

	//function that is called before creating the window (PApplet)
	public void settings() {
		size(windowWidth, windowHeight);
	}
	
	//called once after the PApplet is created
	public void setup() {
		
	}
	
	//called every 1/60 of second
	public void draw() {

	}
	
	public void setCurrentScene(Scene scene) {
		this.currentScene = scene;
	}
}
