package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputManager {
	private static Set<Integer> codedKeysPressed = new HashSet<>();
	private static Set<Integer> codedKeysUp = new HashSet<>();
	private static Set<Integer> codedKeysDown = new HashSet<>();
	
	private static Set<Character> keysPressed = new HashSet<>();
	private static Set<Character> keysUp = new HashSet<>();
	private static Set<Character> keysDown = new HashSet<>();
	
	private static boolean mousePressed = false;
	private static boolean mouseDown = false;
	private static boolean mouseUp = false;
	
	/**
	 * Function to call when one of the mouse button is pressed to update the InputManager data
	 */
	public static void setMousePressed() {
		if (!mousePressed) {
			mouseDown = true;
		}
		mousePressed = true;
	}
	
	/**
	 * Function to call when one of the mouse button is released to update the InputManager data
	 */
	public static void setMouseReleased() {
		mouseUp = true;
		mousePressed = false;
	}
	
	/**
	 * Function to call when a modifier key (like ALT, CTRL, a directional arrow, etc.) of the keyboard was pressed to update the InputManager data
	 * @param keyCode The int associated with the that was pressed, in the PConstants
	 */
	public static void setKeyPressed(int keyCode) {	
		if (!codedKeysPressed.contains(keyCode)) {
			codedKeysDown.add(keyCode);
		}
		codedKeysPressed.add(keyCode);
	}
	
	/**
	 * Function to call when a character key of the keyboard was pressed to update the InputManager data
	 * @param key The character of the key that was pressed
	 */
	public static void setKeyPressed(char key) {	
		if (!keysPressed.contains(Character.toLowerCase(key))) {
			keysDown.add(Character.toLowerCase(key));
		}
		keysPressed.add(Character.toLowerCase(key));
	}
	
	/**
	 * Function to call when a modifier key (like ALT, CTRL, a directional arrow, etc.) of the keyboard was released to update the InputManager data
	 * @param keyCode The int associated with the key that was released in the PConstants
	 */
	public static void setKeyReleased(int keyCode) {
		codedKeysPressed.remove(keyCode);
		codedKeysUp.add(keyCode);
	}
	
	/**
	 * Function to call when a character key of the keyboard was released to update the InputManager data
	 * @param key The character of the key that was released
	 */
	public static void setKeyReleased(char key) {
		keysPressed.remove(Character.toLowerCase(key));
		keysUp.add(Character.toLowerCase(key));
	}
	
	/**
	 * Function to call regularly to clear the list of the mouse buttons and keyboard keys that were just pressed or released
	 */
	static public void clear() {
		codedKeysUp.clear();
		keysUp.clear();
		codedKeysDown.clear();
		keysDown.clear();
		mouseUp = false;
		mouseDown = false;
	}
	
	/**
	 * Tells if the key has just been pressed
	 * @param keyCode The code of the modifier key (like ALT, CTRL, a directional arrow, etc.) to check, from the PConstants
	 * @return true if the key was pressed since the last time InputManager.clear() was called, false otherwise
	 */
	public static boolean getKeyDown(int keyCode) {
		return codedKeysDown.contains(keyCode);
	}
	
	/**
	 * Tells if the key has just been pressed
	 * @param key The character of the key to check
	 * @return true if the key was pressed since the last time InputManager.clear() was called, false otherwise
	 */
	public static boolean getKeyDown(char key) {
		return keysDown.contains(Character.toLowerCase(key));
	}
	
	/**
	 * Tells if the key has just been released by the user
	 * @param keyCode The code of the modifier key (like ALT, CTRL, a directional arrow, etc.) to check, from the PConstants
	 * @return true if the key was released since the last time InputManager.clear() was called, false otherwise
	 */
	public static boolean getKeyUp(int keyCode) {
		return codedKeysUp.contains(keyCode);
	}
	
	/**
	 * Tells if the key has just been released by the user
	 * @param key The character of the key to check
	 * @return true if the key was released since the last time InputManager.clear() was called, false otherwise
	 */
	public static boolean getKeyUp(char key) {
		return keysUp.contains(Character.toLowerCase(key));
	}
	
	/**
	 * Tells if the key is currently held down by the user
	 * @param keyCode The code of the modifier key (like ALT, CTRL, a directional arrow, etc.) to check, from the PConstants
	 * @return true if the key is held down, false otherwise
	 */
	public static boolean getKey(int keyCode) {
		return codedKeysPressed.contains(keyCode);
	}
	
	/**
	 * Tells if the key is currently held down by the user
	 * @param key The character of the key to check
	 * @return true if the key is held down, false otherwise
	 */
	public static boolean getKey(char key) {
		return keysPressed.contains(Character.toLowerCase(key));
	}

	/**
	 * Tells if one of the mouse buttons is currently held down
	 * @return true if one the button is held down, false otherwise
	 */
	public static boolean isMousePressed() {
		return mousePressed;
	}

	/**
	 * Tells if one of the mouse buttons has just been pressed by the user
	 * @return true if one of the mouse button was pressed since the last time InputManager.clear() was called, false otherwise
	 */
	public static boolean isMouseDown() {
		return mouseDown;
	}

	/**
	 * Tells if one of the mouse buttons has just been released by the user
	 * @return true if one of the mouse button was released since the last time InputManager.clear() was called, false otherwise
	 */
	public static boolean isMouseUp() {
		return mouseUp;
	}
	
	
}
