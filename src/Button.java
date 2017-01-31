import processing.core.*;

public class Button {
	
	float xpos, ypos;
	float width, height;
	int textSize;
	PApplet applet;
	int[] fillColor = new int[3];
	String text = "";
	int ButtonType;
	boolean toggled = false;
	
	int menuPosition;
	PImage image;
	static boolean clickedImpulse = true;
	
	Button(
			PApplet p,
			float x, float y,
			float w, float h,
			int pos,
			int r, int g, int b) {
		
		applet = p;
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		menuPosition = pos;
		
		fillColor[0] = r;
		fillColor[1] = g;
		fillColor[2] = b;
		
		ButtonType = 1;
		
	}
	
	Button(
			PApplet p,
			float x, float y,
			float w, float h,
			int pos,
			int r, int g, int b,
			String t) {
		
		applet = p;
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		menuPosition = pos;
		
		fillColor[0] = r;
		fillColor[1] = g;
		fillColor[2] = b;
		
		text = t;

		ButtonType = 2;
		
	}
	
	Button(
			PApplet p,
			float x, float y,
			float w, float h,
			int pos,
			PImage img) {
		
		applet = p;
		xpos = x;
		ypos = y;
		width = w;
		height = h;
		menuPosition = pos;
		
		image = img;
		
		ButtonType = 3;
		
	}
	
	void show() {
		
		if(ButtonType < 3) {
		
			applet.rectMode(PConstants.CENTER);
			applet.noStroke();
			
			if(hover()) {
				applet.fill(255, 0, 0);
			}
			else if(toggled) {
				applet.fill(0, 255, 0);
			}
			else {
				applet.fill(
						fillColor[0],
						fillColor[1],
						fillColor[2],
						200);
			}
			
			if(hover()) {
				applet.rect(xpos + applet.random(-5, 5), ypos + applet.random(-5, 5), width, height);
			}
			else {
				applet.rect(xpos, ypos, width, height);
			}
			applet.rectMode(PConstants.CORNER);
			
			if(ButtonType == 2) {
				applet.textAlign(PConstants.CENTER, PConstants.CENTER);
				
				textSize = (int) (height / 2);
				
				if(textSize <= 1) { textSize = 1; }
				
				applet.textSize(textSize);
				
				while(applet.textWidth(text) >= width - width / 4 && textSize > 1) {
					textSize--;
					applet.textSize(textSize);
				}
				if(applet.brightness(applet.color(fillColor[0], fillColor[1], fillColor[2])) > 127) {
					applet.fill(0);
				}
				else {
					applet.fill(255);
				}
				if(hover()) {
					applet.textSize(textSize - 5);
				}
				if(textSize < 10) {
					applet.textSize(10);
				}
				applet.text(text, xpos, ypos);
			}
			
		}
		
		if(ButtonType == 3) {
			
			applet.imageMode(PConstants.CENTER);
			
			if(hover()) {
				applet.rectMode(PConstants.CENTER);
				applet.fill(255, 0, 0, 150);
				float tempXpos = xpos + applet.random(-5, 5);
				float tempYpos = ypos + applet.random(-5, 5);
				applet.image(image, tempXpos, tempYpos, width, height);
				applet.rect(tempXpos, tempYpos, width, height);
			}
			
			else {
				applet.image(image, xpos, ypos, width, height);
			}
			applet.rectMode(PConstants.CORNER);
			
		}
		
	}
	
	boolean clicked() {
		
		if(	hover() &&	
			applet.mousePressed &&
			!clickedImpulse) {
			
			clickedImpulse = true;
			return true;
			
		} else if(	applet.mousePressed &&
					clickedImpulse) {
			
			return false;
		
		} else if(	!applet.mousePressed &&
					clickedImpulse) {
			
			clickedImpulse = false;
			return false;
			
		}
		return false;
		
	}
	
	boolean hover() {
		
		if(	(applet.mouseX >= xpos - width / 2 &&
			applet.mouseX <= xpos + width / 2 &&
			applet.mouseY >= ypos - height / 2 &&
			applet.mouseY <= ypos + height / 2)) {
			
			return true;
			
		} else { return false; }
		
	}
	
	void toggle(boolean b) {
		toggled = b;
	}
	
	void changeColor(int r, int g, int b) {
		fillColor[0] = r;
		fillColor[1] = g;
		fillColor[2] = b;
	}

}
