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

	public boolean isPressed() {
	    return isHovered() && InputManager.isMousePressed() && w.focused;
	  }


	  public boolean isReleased() {
	    return isHovered() && InputManager.isMouseUp() && w.focused;
	  }

	  public boolean isClicked() {
	    return isHovered() && InputManager.isMouseDown() && w.focused;
	  }


	  public boolean isHovered() {
	    if (mode == PConstants.CENTER) {
	      return w.mouseX>x-this.width/2 && w.mouseX<x+this.width/2 && w.mouseY>y-this.height/2 && w.mouseY<y+this.height/2;
	    } else {
	      return w.mouseX>x && w.mouseX<x+this.width && w.mouseY>y && w.mouseY<y+this.height;
	    }
	  }
	  
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
	  
	  public void setText(String text) {
		  this.text = text;
	  }
}
