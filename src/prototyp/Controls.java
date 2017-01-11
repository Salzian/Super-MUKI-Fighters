package prototyp;
import processing.core.PConstants;

public class Controls {
		
	boolean goUp, goDown, goLeft, goRight, triggered;
	
	int keyUsed;
			
	boolean setMove(int key, boolean b) {
		
		switch (key) {
		
		case PConstants.UP:
			
			return goUp = b;
				
		case PConstants.DOWN:
			
			return goDown = b;
			
		case PConstants.LEFT:
			
			return goLeft = b;
			
		case PConstants.RIGHT:
			
			return goRight = b;
			
		case ' ':
			
			return triggered = b;
			
		default:
			
			return b;
	
		}
	
	}
		
}
