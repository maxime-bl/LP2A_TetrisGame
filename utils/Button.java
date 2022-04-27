package utils;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class Button {
	private float x, y;
	private int width, height;
	private String text;
	private PApplet w;
	private int mode;
	private Color color;

	
	public Button(float x, float y, int width, int height, String text, PApplet window) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.w = window;
		this.mode = PConstants.CORNER;
		this.color = ColorConstants.CYAN;
	}
	
	public Button(float x, float y, int width, int height, String text, PApplet window, ButtonMode mode) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.w = window;
		this.setMode(mode);
		this.color = ColorConstants.CYAN;
	}
	
	public Button(float x, float y, int width, int height, String text, PApplet window, ButtonMode mode, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.w = window;
		this.setMode(mode);
		this.color = color;
	}
	
	/**
	 * Displays the button on the window
	 */
	public void display() {
		PFont font;
		font = w.loadFont("./resources/Ebrima-Bold-48.vlw");
		
		w.push();
		
		w.noStroke();
		w.rectMode(mode);
		w.textAlign(PConstants.CENTER, PConstants.TOP);
	
		w.fill(color.getRed(), color.getGreen(), color.getBlue());
		w.rect(x,y,width,height,9);
		if (this.isPressed()) {
			w.fill(255,255,255,50);
		} else if (this.isHovered()) {
			w.fill(255,255,255,30);
		} else {
			w.fill(255,255,255,0);
		}		
		w.rect(x,y,width,height,9);
	
		
		if (this.isPressed()) {
			w.fill(50);
		} else if (this.isHovered()) {
			w.fill(30);
		} else {
			w.fill(0);
		}		
		w.textSize(20);
		
	    if (mode == PConstants.CENTER) {
	    	w.rect(x,y,width-10,height-10,9);
	    	w.fill(255);
	    	w.text(text, x, y-getTextHeight()/2);
	    }
	    else {
	    	w.rect(5+x,5+y,width-10,height-10,9);
	    	w.fill(255);
	    	w.text(text, x+this.width/2, y+(this.height-getTextHeight())/2);
	    }
		
		w.pop();
	}
	
	private float getTextHeight() {
		  return w.textAscent()+w.textDescent();
		}

	/**
	 * Tells if the button is currently being pressed by the user
	 * @return true if the cursor is over the button and one of the mouse button is pressed, false otherwise
	 */
	public boolean isPressed() {
	    return isHovered() && InputManager.isMousePressed() && w.focused;
	  }

	/**
	 * Tells if the button has just been released by the user
	 * @return true if the cursor is over the button and one of the mouse button has just been released, false otherwise
	 */
	public boolean isReleased() {
		return isHovered() && InputManager.isMouseUp() && w.focused;
	}

	/**
	 * Tells if the button has just been pressed by the user
	 * @return true if the cursor is over the button and one of the mouse button has just been pressed, false otherwise
	 */
	public boolean isClicked() {
		return isHovered() && InputManager.isMouseDown() && w.focused;
	}

	/**
	 * Tells if the user is hovering the button with the mouse
	 * @return true if the mouse cursor is inside the boundaries of the button, false otherwise
	 */
	public boolean isHovered() {
		if (mode == PConstants.CENTER) {
			return w.mouseX>x-this.width/2 && w.mouseX<x+this.width/2 && w.mouseY>y-this.height/2 && w.mouseY<y+this.height/2;
	    } else {
	    	return w.mouseX>x && w.mouseX<x+this.width && w.mouseY>y && w.mouseY<y+this.height;
	    }
	}
	  
	/**
	 * Sets the coordinates mode of the button to either CENTER or CORNER
	 * <p>
	 * In CORNER mode, the x and y coordinates of the button are interpreted as the coordinates of its top-left corner.
	 * <p>
	 * In CENTER mode, the x and y coordinates of the button are interpreted as the coordinates of its center point.
	 * 
	 * @param mode either CENTER or CORNER
	 */
	public void setMode(ButtonMode mode) {
		if (mode == ButtonMode.CENTER) {
			this.mode = PConstants.CENTER;
		} else {
			this.mode = PConstants.CORNER;
		}
	}
	  
	public enum ButtonMode{
		CENTER, CORNER;
	}
	  
	/**
	 * Sets the text displayed on the button
	 * @param text String to write on the button when display() is called
	 */
	public void setText(String text) {
		this.text = text;
	}
}
