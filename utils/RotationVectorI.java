package utils;

import java.util.HashMap;

public class RotationVectorI {

	private static final HashMap<String, Vector> rotationVectors = new HashMap<String, Vector>();
	
	static {
		rotationVectors.put("0>>1", new Vector(1, 0));
		rotationVectors.put("1>>2", new Vector(0, -1));
		rotationVectors.put("2>>3", new Vector(-1, 0));
		rotationVectors.put("3>>0", new Vector(0, 1));
		rotationVectors.put("1>>0", new Vector(-1, 0));
		rotationVectors.put("2>>1", new Vector(0, 1));
		rotationVectors.put("3>>2", new Vector(1, 0));
		rotationVectors.put("0>>3", new Vector(0, -1));
	}
	
	public static Vector get(String key) {
		return rotationVectors.get(key);
	}
}
