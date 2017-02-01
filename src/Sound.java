import ddf.minim.*;
import processing.core.PApplet;

public class Sound {
	
	static PApplet applet;
	static Minim minim;
	static AudioPlayer[] music = new AudioPlayer[5];
	static AudioPlayer[] sounds = new AudioPlayer[1];
	
	static void setupSoundEngine(PApplet p) {
		applet = p;
		minim = new Minim(applet);
		
		loadSamples();
		
		music[0].setGain(-6f);
		music[1].setGain(-12f);
		music[2].setGain(-12f);
		music[3].setGain(-12f);
		music[4].setGain(-6f);
		
		for(int i = 0; i < sounds.length; i++) {
			
			sounds[i].setGain(1f);
			
		}
	}
	
	static void stopSoundEngine() {
		minim.stop();
	}
	
	static void controlMusic(AudioPlayer audioPlayer, String command) {
		
		switch(command) {
		
		case "play":
			if(!audioPlayer.isPlaying()) { audioPlayer.loop(); }
			break;
		
		case "stop":
			if(audioPlayer.isPlaying()) { audioPlayer.pause(); audioPlayer.rewind(); }
			break;
			
		}
	
	}
	
	static void playSound(AudioPlayer sound) { if(!sound.isPlaying()) { sound.pause(); sound.rewind(); sound.play(); }; }
	static void stopSound(AudioPlayer sound) { sound.pause(); sound.rewind(); }
	
	static void loadSamples() {
		
		music[0] = minim.loadFile("data/audio/music/main_menu.mp3", 4096);
		music[1] = minim.loadFile("data/audio/music/woods.mp3", 4096);
		music[2] = minim.loadFile("data/audio/music/night.mp3", 4096);
		music[3] = minim.loadFile("data/audio/music/island.mp3", 4096);
		music[4] = minim.loadFile("data/audio/music/credits.mp3", 4096);
		
		sounds[0] = minim.loadFile("data/audio/boot.mp3", 4096);
		
	}
	
}
