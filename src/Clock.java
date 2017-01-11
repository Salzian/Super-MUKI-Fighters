import processing.core.*;

public class Clock {

	static float curTime, prevTime, elapTime;
	
	static void update(PApplet applet) {
	
		curTime = applet.millis();
		elapTime = (float)1e-3 * (curTime - prevTime);
		prevTime = curTime;
	
	}
	
}