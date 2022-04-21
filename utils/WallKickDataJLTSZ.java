package utils;

import java.util.HashMap;

public class WallKickDataJLTSZ {


	private final static HashMap<String, Vector> wallKickDataJLTSZ;
	
	static {
		wallKickDataJLTSZ = new HashMap<String, Vector>();
		wallKickDataJLTSZ.put("0>>1_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("0>>1_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("0>>1_3", new Vector(-1, 1));
		wallKickDataJLTSZ.put("0>>1_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("0>>1_5", new Vector(-1, -2));
		
		wallKickDataJLTSZ.put("1>>0_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("1>>0_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("1>>0_3", new Vector(1, -1));
		wallKickDataJLTSZ.put("1>>0_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("1>>0_5", new Vector(1, 2));
		
		wallKickDataJLTSZ.put("1>>2_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("1>>2_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("1>>2_3", new Vector(1, -1));
		wallKickDataJLTSZ.put("1>>2_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("1>>2_5", new Vector(1, 2));
		
		wallKickDataJLTSZ.put("2>>1_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("2>>1_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("2>>1_3", new Vector(-1, 1));
		wallKickDataJLTSZ.put("2>>1_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("2>>1_5", new Vector(-1, -2));
		
		wallKickDataJLTSZ.put("2>>3_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("2>>3_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("2>>3_3", new Vector(1, 1));
		wallKickDataJLTSZ.put("2>>3_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("2>>3_5", new Vector(1, -2));
		
		wallKickDataJLTSZ.put("3>>2_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("3>>2_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("3>>2_3", new Vector(-1, -1));
		wallKickDataJLTSZ.put("3>>2_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("3>>2_5", new Vector(-1, 2));
		
		wallKickDataJLTSZ.put("3>>0_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("3>>0_2", new Vector(-1, 0));
		wallKickDataJLTSZ.put("3>>0_3", new Vector(-1, -1));
		wallKickDataJLTSZ.put("3>>0_4", new Vector(0, 2));
		wallKickDataJLTSZ.put("3>>0_5", new Vector(-1, 2));
		
		wallKickDataJLTSZ.put("0>>3_1", new Vector(0, 0));
		wallKickDataJLTSZ.put("0>>3_2", new Vector(1, 0));
		wallKickDataJLTSZ.put("0>>3_3", new Vector(1, 1));
		wallKickDataJLTSZ.put("0>>3_4", new Vector(0, -2));
		wallKickDataJLTSZ.put("0>>3_5", new Vector(1, -2));
	}
	
	public static Vector get(String key) {
		return wallKickDataJLTSZ.get(key);
	}
}
