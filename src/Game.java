import processing.core.*;
import processing.opengl.PJOGL;

import java.io.IOException;

public class Game extends PApplet{
	
	public static void main(String[] args) {
		Configurator.main(platformNames);
	}	
	
	static boolean fullscreen = false;
	static boolean opengl = true;
	static int gameWidth = 800, gameHeight = 600;
	static double	main_volume = 1,
					music_volume = 1,
					soundfx_volume = 1;
	
	public static void startGame() throws InterruptedException {
		try {
			loadSettings();
		}
		catch (IOException e) {
			System.out.println("File not found or cannot be read. Trying to create new file...");
			try {
				Settings.CreateFile();
			}
			catch (IOException f) {
				System.out.println("File cannot be created. Check write access permissions! Loading game with default settings...");
			}
			try {
				loadSettings();
			} catch(IOException g) {
				System.out.println("New file cannot be read. Check read access permissions! Loading game with default settings...");
			}
		}
		

		PApplet.main("Game");
	}
	
	static boolean loaded = false;
	PFont font;
	
	static PImage[] entoni = new PImage[14];
	static PImage[] entoni2 = new PImage[14];
	
	static PImage[] background = new PImage[3];
	
	static PImage[] logos = new PImage[3];

	static PShape
					PShealthbar1_bg,
					PShealthbar2_bg;


	public void settings() {
		
		if(fullscreen) {
			
			gameWidth = displayWidth;
			gameHeight = displayHeight;
			
			
			if(opengl) {
				fullScreen(P2D);
			} else {
				fullScreen();
			}
		} else {
			if(opengl) {
				size(gameWidth, gameHeight, P2D);
			} else {
				size(gameWidth, gameHeight);
			}
		}
		
		PJOGL.setIcon("data/icon.png");
		
	}
	
	public void setup() {
		
		background(255, 0, 0);
		text("Loading", width / 2, height / 2);
		
		surface.setTitle("Super MUKI Fighters");
		
		loadSprites();
		setupShapes();
		Controls.configureGamepads();
		Level.setupLevels(this);
		Sound.setupSoundEngine(this);
		font = createFont("data/fonts/8-bit pusab.ttf", 100);
		textFont(font);
		
	}
		
	public void draw() {
		
		loaded = true;
		Clock.update(this);
		Level.drawLevel();
		
	}
	
	static void loadSettings() throws IOException, InterruptedException {
		
		Settings.OpenFile();
		
		int hash = 0;
		
		for(int i = 0; i < Settings.settings.size(); i++) {
			
			if(Settings.settings.get(i).startsWith("fullscreen")) {
				
				try {
					fullscreen = Boolean.parseBoolean(Settings.settings.get(i).split("=")[1]);
				} catch(NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("opengl")) {
				try {
					opengl = Boolean.parseBoolean(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("gameWidth")) {
				try {
					gameWidth = Integer.parseInt(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("gameHeight")) {
				try {
					gameHeight = Integer.parseInt(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("main_volume")) {
				try {
					main_volume = Double.parseDouble(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
			if(Settings.settings.get(i).startsWith("music_volume")) {
				try {
					music_volume = Double.parseDouble(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			if(Settings.settings.get(i).startsWith("soundfx_volume")) {
				try {
					soundfx_volume = Double.parseDouble(Settings.settings.get(i).split("=")[1]);
				} catch (NumberFormatException e) {
					throw new IOException();
				}
				hash++;
			}
			
		}
		
		if(hash != 7) {
			throw new IOException();
		}
		
	}
	
	void loadSprites() {
		
		//Character Sprites
		
		for(int i = 0; i < entoni.length; i++) {
			entoni[i] = loadImage("data/characters/Entoni/" + i + ".png");
			entoni[i].resize(gameHeight / 4, gameHeight / 4);
		}
		for(int i = 0; i < entoni2.length; i++) {
			entoni2[i] = loadImage("data/characters/Entoni2/" + i + ".png");
			entoni2[i].resize(gameHeight / 4, gameHeight / 4);
			delay(100);
		}
		
		//Backgrounds
		
		background[0] = loadImage("data/backgrounds/woods.png");
		background[0].resize((int) (gameHeight * (background[0].width / background[0].height)), gameHeight);
		
		background[1] = loadImage("data/backgrounds/night_street.png");
		background[1].resize((int) (gameHeight * (background[1].width / background[1].height)), gameHeight);
		
		background[2] = loadImage("data/backgrounds/island.png");
		background[2].resize((int) (gameHeight * (background[2].width / background[2].height)), gameHeight);
		
		
		//Logos
		
		logos[0] = loadImage("data/logo.png");
		logos[0].resize((logos[0].width / logos[0].height) * (gameHeight / 4), gameHeight / 4);
		
		logos[1] = loadImage("data/hsrw_logo.png");
		logos[1].resize((logos[1].width / logos[1].height) * (gameHeight / 3), gameHeight / 3);
		
		logos[2] = loadImage("data/processing_logo.png");
		logos[2].resize(gameHeight / 3, gameHeight / 3);
		
	}
	
	void setupShapes() {
		
		PShealthbar1_bg = createShape();
		PShealthbar2_bg = createShape();
		
		PShealthbar1_bg.beginShape();
		PShealthbar1_bg.fill(0);
		PShealthbar1_bg.noStroke();
		PShealthbar1_bg.vertex(gameHeight / 32, gameHeight / 32);
		PShealthbar1_bg.vertex(gameHeight / 32 + gameWidth / 16 * 6, gameHeight / 32);
		PShealthbar1_bg.vertex(gameHeight / 32 + gameWidth / 16 * 6, gameHeight / 32 * 4);
		PShealthbar1_bg.vertex(gameHeight / 32 + gameWidth / 16 * 4, gameHeight / 32 * 4);
		PShealthbar1_bg.vertex(gameHeight / 32 + gameWidth / 16 * 3, gameHeight / 32 * 5);
		PShealthbar1_bg.vertex(gameHeight / 32, gameHeight / 32 * 5);
		PShealthbar1_bg.endShape(PConstants.CLOSE);
		
		PShealthbar2_bg.beginShape();
		PShealthbar2_bg.fill(0);
		PShealthbar2_bg.noStroke();
		PShealthbar2_bg.vertex(gameWidth - gameHeight / 32, gameHeight / 32);
		PShealthbar2_bg.vertex(gameWidth - gameHeight / 32 - gameWidth / 16 * 6, gameHeight / 32);
		PShealthbar2_bg.vertex(gameWidth - gameHeight / 32 - gameWidth / 16 * 6, gameHeight / 32 * 4);
		PShealthbar2_bg.vertex(gameWidth - gameHeight / 32 - gameWidth / 16 * 4, gameHeight / 32 * 4);
		PShealthbar2_bg.vertex(gameWidth - gameHeight / 32 - gameWidth / 16 * 3, gameHeight / 32 * 5);
		PShealthbar2_bg.vertex(gameWidth - gameHeight / 32, gameHeight / 32 * 5);
		PShealthbar2_bg.endShape(PConstants.CLOSE);
		
	}
	
	public void keyPressed() {
		Controls.setKey(keyCode, true);
	}
	public void keyReleased() {
		Controls.setKey(keyCode, false);
	}
	public void mousePressed() {
		Controls.setMouse(mouseButton, true);
	}
	public void mouseReleased() {
		Controls.setMouse(mouseButton, false);
	}
	
}