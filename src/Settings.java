import java.io.IOException;

import java.io.FileReader;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.ArrayList;

/*
 * http://www.homeandlearn.co.uk/java/read_a_textfile_in_java.html
 * https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
 *  
 * Zugriff: 5. Januar 2016
 */

public class Settings {
	
	public static ArrayList<String> settings;
	
	public static ArrayList<String> OpenFile() throws IOException, InterruptedException {
		
		settings = new ArrayList<String>();
		
		BufferedReader settingsReader = new BufferedReader(new FileReader("settings.txt"));
		
		@SuppressWarnings("unused")
		int i = 0;
		String line;
		
		while((line = settingsReader.readLine()) != null) {
			settings.add(line);
			i++;
			Thread.sleep(100);
		}
		
		settingsReader.close();
		
		return settings;
		
	}
	
	public static void SaveFile() throws IOException {
		
		BufferedWriter settingsWriter = new BufferedWriter(new FileWriter("settings.txt"));
		
		String newSettings =
				"fullscreen="
				+ Configurator.fullscreen
				+ "\n"
				+ "opengl="
				+ Configurator.opengl
				+ "\n"
				+ "gameWidth="
				+ Configurator.width
				+ "\n"
				+ "gameHeight="
				+ Configurator.height;
		
		settingsWriter.write(newSettings);
		settingsWriter.close();	
		
	}
	
	public static void CreateFile() throws IOException {
		
		BufferedWriter settingsWriter = new BufferedWriter(new FileWriter("settings.txt"));
		
		String defaultSettings =
				"fullscreen=false\n"
				+ "opengl=true\n"
				+ "gameWidth=800\n"
				+ "gameHeight=600\n";
		
		settingsWriter.write(defaultSettings);
		settingsWriter.close();		
		
	}
	
}
