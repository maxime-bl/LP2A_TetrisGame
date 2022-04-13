package scenes;

import processing.core.PApplet;

public interface Scene {
	abstract void processInput(PApplet window);
	abstract void update();
	abstract void render(PApplet window);
}
