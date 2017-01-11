package prototyp;
import processing.core.*;

public class Game extends PApplet{

	public static void main(String[] args) {
		PApplet.main("prototyp.Game");
	}
	
	PImage[] images = new PImage[14];
	PImage bg;
	PFont fpsFont;
	
	Controls keyboard = new Controls();
	Clock tick = new Clock();

	public void settings() {
		
		//fullScreen();
		size(800, 500);
		loop();
		noSmooth();
		
		for(int i = 0; i < images.length; i++) {
			images[i] = loadImage("data/characters/harambe/frame" + i + ".png");
		}
		
		bg = loadImage("data/backgrounds/forest.jpg");
	}
	
	public void setup() {
		
		frameRate(60);
		
	}
	
	int frame = 0;
	float framefloat = 0;
	float xpos = 100;
	float ypos = 100;
	float triggerrate = 0;
	
	int speed = 1;
	int dir = 1;
	boolean moving = false,
			triggered = false;
	
	public void draw() {
		
		tick.update();
		
		if(moving) {
			framefloat = framefloat + images.length * tick.elapTime;
		}
		
		else {
			framefloat = 0;
		}
		
		frame = (int)framefloat;
		
		if(framefloat > images.length) { framefloat = framefloat - images.length; }
		if(frame > images.length - 1) { frame = frame - images.length; }
		
		
		image(bg, 0, 0, (int)(height * 1.8), height);
		fill(0);
		textSize(20);
		textAlign(LEFT);
		text("FPS: " + (int) frameRate, 0, 20);
		
		fill(triggerrate / 100 * 255, 0, 0);
		textAlign(CENTER);
		textSize(20 + triggerrate);
		text(
				(int)(triggerrate) + "%",
				xpos + images[0].width / 2 + triggerrate / 20 * random(-1, 1),
				ypos - triggerrate + triggerrate / 20 * random(-1, 1));
		
		if (triggered && triggerrate > 100) { triggered = false; }
		
		
		pushMatrix();
		
			scale(dir, 1);
			
			if(triggered) { translate(triggerrate * random(-1, 1),
					  				  triggerrate * random(-1, 1));
							
							triggerrate = (float)(triggerrate + 20 * tick.elapTime);
							
							tint(255, 255 - triggerrate / 100 * 255, 255 - triggerrate / 100 * 255);
							
							}
			else if(triggerrate > 0) {
				translate(triggerrate * random(-1, 1),
		  				  triggerrate * random(-1, 1));
				
				triggerrate = (float)(triggerrate - 20 * tick.elapTime);
				
				tint(255, 255 - triggerrate / 100 * 255, 255 - triggerrate / 100 * 255);
			}
			
			if(dir == -1) { translate(- images[0].width, 0); }
			image(images[frame], xpos * dir, ypos);
			tint(255);
			
		popMatrix();
		
		moving = false;
		
		movement();
		
	}
		
	public void keyPressed() {
		keyboard.setMove(keyCode, true);
	}
	
	public void keyReleased() {
		keyboard.setMove(keyCode, false);
	}
	
	public void movement() {
		
		if(keyboard.goUp) {
			if(ypos > 0) {
				moving = true;
				ypos = ypos - speed * 100 * tick.elapTime;
			}
		}
		
		if(keyboard.goDown) {
			if(ypos < height - images[0].height) {
				moving = true;
				ypos = ypos + speed * 100 * tick.elapTime;
			}
		}
		
		if(keyboard.goLeft) {
			if(xpos > 0) {
				dir = 1;
				moving = true;
				xpos = xpos - speed * 100 * tick.elapTime;
			}
		}
		
		if(keyboard.goRight) {
			if(xpos < width - images[0].width) {
				dir = -1;
				moving = true;
				xpos = xpos + speed * 100 * tick.elapTime;
			}
		}
		
		if(keyboard.triggered && !triggered) {
			triggered = true;
		}

	}
	
}