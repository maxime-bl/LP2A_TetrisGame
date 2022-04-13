package utils;

public class Vector {

	private int x, y;

	public Vector() {
		x = 0;
		y = 0;
	}
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
