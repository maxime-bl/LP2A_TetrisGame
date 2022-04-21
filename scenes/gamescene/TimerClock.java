package scenes.gamescene;

import processing.core.*;
import java.lang.Math;

public class TimerClock {

	private long millisec;
	private long seconds;
	private long minutes;
	private boolean isFinished = false;
	
	public TimerClock() {
		millisec = 0;
		seconds = 0;
		minutes = 0;
	}
	
	public void update(PApplet w) {
		if (!isFinished) {
			millisec = w.millis();
			seconds = (millisec/1000)%60;
			minutes = (long)Math.floor((millisec/1000)/60);
		}
	}
	
	public long getMinutes() {
		return minutes;
	}
	
	public long getSeconds() {
		return seconds;
	}
	
	public void setFinish(boolean b) {
		this.isFinished = b;
	}
	
	public String toString() {
		String timer = "";
		if (minutes < 10) {
			timer += "0" + minutes;
		} else {
			timer += minutes;
		}
		timer += ":";
		if (seconds < 10) {
			timer += "0" + seconds;
		} else {
			timer += seconds;
		}
		return timer;
	}
}
