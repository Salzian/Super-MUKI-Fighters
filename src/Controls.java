import processing.core.PConstants;
import ch.aplu.xboxcontroller.*;

public class Controls {
	
	static XboxController gamepad1 = new XboxController(1);
	static XboxController gamepad2 = new XboxController(2);
		
	static boolean
					Up,
					Down,
					Left,
					Right,
					Space,
					Control,
					Shift,
					Alt,
					W,
					A,
					S,
					D;
	
	static boolean
					mLeft,
					mRight;
	
	static boolean
					c1A,
					c1B,
					c1X,
					c1Y,
					
					c1Start,
					c1Select,

					c1leftThumbUp,
					c1leftThumbDown,
					c1leftThumbLeft,
					c1leftThumbRight,
					
					c1DpadPressed;
	
	static int 		c1DpadDirection;
	
	static double
					c1leftThumbMag;
	
	static boolean
					c2A,
					c2B,
					c2X,
					c2Y,
	
					c2Start,
					c2Select,

					c2leftThumbUp,
					c2leftThumbDown,
					c2leftThumbLeft,
					c2leftThumbRight,
	
					c2DpadPressed;

	static int 		c2DpadDirection;

	static double
					c2leftThumbMag;
	
	public static float 	vibTimer;
	
	int keyUsed;
	
	static void configureGamepads() {

		if(gamepad1.isConnected()) {
			
			System.out.println("Controller 1 connected");
			
			gamepad1.addXboxControllerListener(new XboxControllerAdapter() {
				
				public void buttonA(boolean pressed) { c1A = pressed; }
				public void buttonB(boolean pressed) { c1B = pressed; }
				public void buttonX(boolean pressed) { c1X = pressed; }
				public void buttonY(boolean pressed) { c1Y = pressed; }

				public void start(boolean pressed) { c1Start = pressed; }
				public void back(boolean pressed) { c1Select = pressed; }
				
				public void leftThumbMagnitude(double magnitude) { c1leftThumbMag = magnitude; }
				
				public void leftThumbDirection(double direction) {

					if(direction >= 315 && direction <= 45 && Math.abs(c1leftThumbMag) > 0.2) { c1leftThumbUp = true; } else { c1leftThumbUp = false; }
					if(direction >= 135 && direction <= 225 && Math.abs(c1leftThumbMag) > 0.2) { c1leftThumbDown = true; } else { c1leftThumbDown = false; }
					if(direction >= 225 && direction <= 315 && Math.abs(c1leftThumbMag) > 0.2) { c1leftThumbLeft = true; } else { c1leftThumbLeft = false; }
					if(direction >= 45 && direction <= 135 && Math.abs(c1leftThumbMag) > 0.2) { c1leftThumbRight = true; } else { c1leftThumbRight = false; }
					
				}
				
				public void dpad(int direction, boolean pressed) {
					
					c1DpadDirection = direction;
					c1DpadPressed = pressed;
					
				}
				
			});
			
			if(gamepad2.isConnected()) {
				
				System.out.println("Controller 2 connected");
				
				gamepad2.addXboxControllerListener(new XboxControllerAdapter() {
					
					public void buttonA(boolean pressed) { c2A = pressed; }
					public void buttonB(boolean pressed) { c2B = pressed; }
					public void buttonX(boolean pressed) { c2X = pressed; }
					public void buttonY(boolean pressed) { c2Y = pressed; }

					public void start(boolean pressed) { c2Start = pressed; }
					public void back(boolean pressed) { c2Select = pressed; }
					
					public void leftThumbMagnitude(double magnitude) { c2leftThumbMag = magnitude;}
					
					public void leftThumbDirection(double direction) {

						if(direction >= 315 && direction <= 45 && Math.abs(c2leftThumbMag) > 0.2) { c2leftThumbUp = true; } else { c2leftThumbUp = false; }
						if(direction >= 135 && direction <= 225 && Math.abs(c2leftThumbMag) > 0.2) { c2leftThumbDown = true; } else { c2leftThumbDown = false; }
						if(direction >= 225 && direction <= 315 && Math.abs(c2leftThumbMag) > 0.2) { c2leftThumbLeft = true; } else { c2leftThumbLeft = false; }
						if(direction >= 45 && direction <= 135 && Math.abs(c2leftThumbMag) > 0.2) { c2leftThumbRight = true; } else { c2leftThumbRight = false; }
						
					}
					
					public void dpad(int direction, boolean pressed) {
						
						c2DpadDirection = direction;
						c2DpadPressed = pressed;
						
					}
					
				});
				
			}
			
		}
		
	}
			
	static boolean setKey(int key, boolean b) {
		
		switch (key) {
		
		case PConstants.UP:
			
			return Up = b;
				
		case PConstants.DOWN:
			
			return Down = b;
			
		case PConstants.LEFT:
			
			return Left = b;
			
		case PConstants.RIGHT:
			
			return Right = b;
			
		case 32:
			
			return Space = b;
			
		case PConstants.CONTROL:
			
			return Control = b;
			
		case PConstants.SHIFT:
			
			return Shift = b;		
			
		case PConstants.ALT:
			
			return Alt = b;
			
		case 87:
			
			return W = b;
			
		case 65:
			
			return A = b;
			
		case 83:
			
			return S = b;
			
		case 68:
			
			return D = b;
			
		default:
			
			return b;
	
		}
	
	}
	
	static boolean setMouse(int key, boolean b) {
		
		switch (key) {
		
		case PConstants.LEFT:
			
			return mLeft = b;
			
		case PConstants.RIGHT:
				
			return mRight = b;
			
		default:
			
			return b;
			
		}
		
	}

		
}
