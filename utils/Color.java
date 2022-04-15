package utils;

public class Color implements ColorConstants{
	public int r, g, b;
	
	public Color(int[] color) {
		this.r = color[0];
		this.g = color[1];
		this.b = color[2];
	}
	
	public Color() {
		r = 0;
		g = 0;
		b = 0;
	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}
	
	
}
