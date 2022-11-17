package scenes;

public interface Scene {
	/**
	 * Processes all the keyboard and mouse inputs from the user
	 */
	abstract void processInput();
	
	/**
	 * Updates the elements of the scene which need to be updated regularly
	 */
	abstract void update();
	
	/**
	 * Generates the image to display on the screen
	 */
	abstract void render();
}
