package scenes.gamescene;

import processing.core.*;
import java.lang.Math;

public class TimerClock {
	private long lastMillis;
	private long elapsedMillis;
	private boolean isPaused = false;
	private boolean isFinished = false;
	
	public TimerClock() {
		lastMillis = System.currentTimeMillis();
		elapsedMillis = 0;
		
	}
	
	public void update() {
		if (!isFinished && !isPaused) {
			elapsedMillis += System.currentTimeMillis() - lastMillis;
			lastMillis = System.currentTimeMillis();
		}
	}
	
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
		if(isPaused == false) {
			lastMillis = System.currentTimeMillis();
		}
	}

	public long getMinutes() {
		return (long)Math.floor((elapsedMillis/1000)/60);
	}
	
	public long getSeconds() {
		return (elapsedMillis/1000)%60;
	}
	
	public void setFinish(boolean b) {
		this.isFinished = b;
	}
	
	public void reset() {
		elapsedMillis = 0;
		lastMillis = System.currentTimeMillis();
	}
	
	public String toString() {
		String timer = "";
		if (getMinutes() < 10) {
			timer += "0" + getMinutes();
		} else {
			timer += getMinutes();
		}
		timer += ":";
		if (getSeconds() < 10) {
			timer += "0" + getSeconds();
		} else {
			timer += getSeconds();
		}
		return timer;
	}
	
	public boolean isRunning() {
		return !isPaused && !isFinished;
	}

	public long getElapsedTime() {
		return elapsedMillis;
	}
	
	public void setElapsedTime(long elapsedMillis) {
		this.elapsedMillis = elapsedMillis;
	}
}
