package com.santiagolizardo.tetriskit.resources;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Preloads sounds so that the application does not have to load them
 * repeatedly.
 */
public class SoundLoader {
	
	private static final Logger logger = Logger.getLogger(SoundLoader.class.getName());

	private static HashMap<Sounds, AudioClip> sounds;

	public static void loadSounds() {
		sounds = new HashMap<Sounds, AudioClip>();
		try {
			sounds.put(Sounds.LineCompleted,
					Applet.newAudioClip(buildURL("sounds/line.au")));
			sounds.put(Sounds.CollisionDetected,
					Applet.newAudioClip(buildURL("sounds/collision.au")));
			sounds.put(Sounds.GameOver,
					Applet.newAudioClip(buildURL("sounds/gameover.au")));
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	public static void playSound(Sounds sound) {
		if (sounds.containsKey(sound)) {
			sounds.get(sound).play();
		}
	}

	private static URL buildURL(String file) throws MalformedURLException {
		return SoundLoader.class.getResource(file);
	}
}
