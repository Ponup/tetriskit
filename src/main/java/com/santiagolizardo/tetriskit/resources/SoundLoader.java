package com.santiagolizardo.tetriskit.resources;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * Preloads sounds so that the application does not have to load them
 * repeatedly.
 */
public class SoundLoader {

	private static Hashtable<Sounds, AudioClip> sounds;

	public static void loadSounds() {
		sounds = new Hashtable<Sounds, AudioClip>();
		try {
			sounds.put(Sounds.LineCompleted,
					Applet.newAudioClip(buildURL("sounds/line.au")));
			sounds.put(Sounds.CollisionDetected,
					Applet.newAudioClip(buildURL("sounds/collision.au")));
			sounds.put(Sounds.GameOver,
					Applet.newAudioClip(buildURL("sounds/gameover.au")));
		} catch (Exception e) {
			e.printStackTrace();
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
