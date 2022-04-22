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
	
	
	public static void setMousePressed() {
		if (!mousePressed) {
			mouseDown = true;
		}
		mousePressed = true;
	}
	
	public static void setMouseReleased() {
		mouseUp = true;
		mousePressed = false;
	}
	
	
	public static void setKeyPressed(int keyCode) {	
		if (!codedKeysPressed.contains(keyCode)) {
			codedKeysDown.add(keyCode);
		}
		codedKeysPressed.add(keyCode);
	}
	
	public static void setKeyPressed(char key) {	
		if (!keysPressed.contains(Character.toLowerCase(key))) {
			keysDown.add(Character.toLowerCase(key));
		}
		keysPressed.add(Character.toLowerCase(key));
	}
	
	
	public static void setKeyReleased(int keyCode) {
		codedKeysPressed.remove(keyCode);
		codedKeysUp.add(keyCode);
	}
	
	public static void setKeyReleased(char key) {
		keysPressed.remove(Character.toLowerCase(key));
		keysUp.add(Character.toLowerCase(key));
	}
	
	
	static public void clear() {
		codedKeysUp.clear();
		keysUp.clear();
		codedKeysDown.clear();
		keysDown.clear();
		mouseUp = false;
		mouseDown = false;
	}
	
	
	public static boolean getKeyDown(int keyCode) {
		return codedKeysDown.contains(keyCode);
	}
	
	public static boolean getKeyDown(char key) {
		return keysDown.contains(Character.toLowerCase(key));
	}
	
	
	public static boolean getKeyUp(int keyCode) {
		return codedKeysUp.contains(keyCode);
	}
	
	public static boolean getKeyUp(char key) {
		return keysUp.contains(Character.toLowerCase(key));
	}
	
	
	public static boolean getKey(int keyCode) {
		return codedKeysPressed.contains(keyCode);
	}
	
	public static boolean getKey(char key) {
		return keysPressed.contains(Character.toLowerCase(key));
	}

	public static boolean isMousePressed() {
		return mousePressed;
	}

	public static boolean isMouseDown() {
		return mouseDown;
	}

	public static boolean isMouseUp() {
		return mouseUp;
	}
	
	
}
