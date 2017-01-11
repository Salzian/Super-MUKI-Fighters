package prototyp;
import processing.core.*;

public class Clock extends PApplet {

	float curTime = millis(), prevTime = millis(), elapTime = 0;
	
	void update() {
	
		curTime = millis();
		elapTime = (float)1e-3 * (curTime - prevTime);
		prevTime = curTime;
	
	}
	
}