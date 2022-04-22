package scenes;

import processing.core.PApplet;

public interface Scene {
	abstract void processInput();
	abstract void update();
	abstract void render();
}
