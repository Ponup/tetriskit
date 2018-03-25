package com.ponup.tetriskit.resources;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Preloads sounds so that the application does not have to load them
 * repeatedly.
 *
 * @todo close audioclips after use
 */
public class SoundLoader {
	
	private static final Logger logger = Logger.getLogger(SoundLoader.class.getName());

	private Map<Sounds, Clip> sounds;

	public void loadSounds() {
		sounds = new HashMap<>();
		try {
			sounds.put(Sounds.LineCompleted, loadAudioFile("sounds/line.wav"));
			sounds.put(Sounds.CollisionDetected,
					loadAudioFile("sounds/collision.wav"));
			sounds.put(Sounds.GameOver,
					loadAudioFile("sounds/gameover.wav"));
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	private Clip loadAudioFile(final String name) throws Exception {
	    URL audioFile = buildURL(name);
        AudioInputStream audioIs = AudioSystem.getAudioInputStream(audioFile);
        DataLine.Info info = new DataLine.Info(Clip.class, audioIs.getFormat());
        Clip audioClip = (Clip)AudioSystem.getLine(info);
        audioClip.open(audioIs);
        return audioClip;
	}

	public void playSound(final Sounds sound) {
		if (sounds.containsKey(sound)) {
			sounds.get(sound).start();
		}
	}

	private URL buildURL(final String file) {
		return SoundLoader.class.getResource(file);
	}
}
