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
	
	/**
	 * Makes the timer update its elapsed time (if it is not paused or finished)
	 */
	public void update() {
		if (!isFinished && !isPaused) {
			elapsedMillis += System.currentTimeMillis() - lastMillis;
			lastMillis = System.currentTimeMillis();
		}
	}
	
	/**
	 * Sets the timer state to paused or unpaused, which prevents it from updating its elapsed time
	 * @param isPaused
	 */
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
		if(isPaused == false) {
			lastMillis = System.currentTimeMillis();
		}
	}

	/**
	 * Gets the number of fully elapsed minutes since the timer started
	 * @return the number of elapsed minutes
	 */
	public long getMinutes() {
		return (long)Math.floor((elapsedMillis/1000)/60);
	}
	
	/**
	 * Gets the number of elapsed seconds since the timer started
	 * @return the number of elapsed seconds
	 */
	public long getSeconds() {
		return (elapsedMillis/1000)%60;
	}
	
	/**
	 * Sets the state of the timer to finished, which is irreversible and prevents it from updating its elapsed time
	 */
	public void setFinished() {
		this.isFinished = false;
	}
	
	/*
	 * Sets the elapsed time back to zero
	 */
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
	
	/**
	 * Returns the current state of the timer
	 * @return true if the timer isn't paused or finished, false otherwise
	 */
	public boolean isRunning() {
		return !isPaused && !isFinished;
	}

	/**
	 * Returns the time between the timer initialization and its last update
	 * @return the elapsed time in milliseconds
	 */
	public long getElapsedTime() {
		return elapsedMillis;
	}
	
	/**
	 * Sets the elapsed time of the timer to the wanted value
	 * @param elapsedMillis the elapsed time in milliseconds
	 */
	public void setElapsedTime(long elapsedMillis) {
		this.elapsedMillis = elapsedMillis;
	}
}
