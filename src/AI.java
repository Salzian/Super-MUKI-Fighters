import processing.core.PApplet;

public class AI {

	static void doAI(PApplet a) {
		
		if(getRandomBoolean(0.5)) {
			Level.player2.moveLeft();
		}
		else if (getRandomBoolean(0.5)) {
			Level.player2.moveRight();
		}
		if(getRandomBoolean(0.8)) {
			Level.player2.jump();
		}
	}
	
	static boolean getRandomBoolean(double d) {
		
		return Math.random() < d;
		
	}
	 
}
