package scenes.gamescene;

import processing.core.*;
import java.lang.Math;

public class TimerClock {
	private long lastMillis;
	private long elapsedMillis;
	private boolean isPaused = false;
	private boolean isFinished = false;
	private PApplet w;	
	
	public TimerClock(PApplet window) {
		this.w = window;
		lastMillis = w.millis();
		elapsedMillis = 0;
	}
	
	public void update(PApplet w) {
		if (!isFinished && !isPaused) {
			elapsedMillis += w.millis() - lastMillis;
			lastMillis = w.millis();
		}
	}
	
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
		if(isPaused == false) {
			lastMillis = w.millis();
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
}
