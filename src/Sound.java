import ddf.minim.*;
import processing.core.PApplet;

public class Sound {
	
	static PApplet applet;
	static Minim minim;
	static AudioPlayer[] music = new AudioPlayer[2];
	static AudioPlayer[] sounds = new AudioPlayer[1];
	
	static void setupSoundEngine(PApplet p) {
		applet = p;
		minim = new Minim(applet);
		
		for(int i = 0; i < music.length; i++) {
			
			music[i].setVolume((float) (Game.main_volume * Game.music_volume));
			
		}
		
		for(int i = 0; i < sounds.length; i++) {
			
			sounds[i].setVolume((float) (Game.main_volume * Game.soundfx_volume));
			
		}
		
		loadSamples();
	}
	
	static void stopSoundEngine() {
		minim.stop();
	}
	
//	static void setMusic(String musicTrack) {
//		if(music == null) {
//			
//			switch(musicTrack) {
//			case "music.main_menu":
//				music = music_main_menu; break;
//			case "music.earrape-mode":
//				music = music_earrape_mode; break;
//			}
//		}
//		
//	}
	
	static void controlMusic(AudioPlayer audioPlayer, String command) {
		
		switch(command) {
		
		case "play":
			if(!audioPlayer.isLooping()) { audioPlayer.loop(); }
			break;
		
		case "stop":
			if(audioPlayer.isLooping()) { audioPlayer.pause(); audioPlayer.rewind(); }
			break;
			
		}
	
	}
	
	static void playSound(AudioPlayer sound) { sound.play(); }
	static void stopSound(AudioPlayer sound) { sound.pause(); sound.rewind(); }
	
	static void loadSamples() {
		
		music[0] = minim.loadFile("data/audio/music/music.main_menu.mp3", 4096);
		music[1] = minim.loadFile("data/audio/music/earrape-mode.mp3", 4096);
		
		sounds[0] = minim.loadFile("data/audio/boot.mp3", 4096);
		
	}
	
}
