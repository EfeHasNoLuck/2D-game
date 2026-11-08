package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	int volumeScale = 3;
	float volume;
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/BestGameEver.wav");
		soundURL[1] = getClass().getResource("/sound/hit_monster.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/coin.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/Judas.wav");
		soundURL[6] = getClass().getResource("/sound/background_loop.wav");
		soundURL[7] = getClass().getResource("/sound/sword_swing.wav");
		soundURL[8] = getClass().getResource("/sound/damaged.wav");
		soundURL[9] = getClass().getResource("/sound/hit.wav");
		soundURL[10] = getClass().getResource("/sound/levelup.wav");
		soundURL[11] = getClass().getResource("/sound/cursor.wav");
		soundURL[12] = getClass().getResource("/sound/purple.wav");
		soundURL[13] = getClass().getResource("/sound/axe.wav");
		soundURL[14] = getClass().getResource("/sound/respawn.wav");
		soundURL[15] = getClass().getResource("/sound/chill.wav");
		soundURL[16] = getClass().getResource("/sound/died.wav");
		soundURL[17] = getClass().getResource("/sound/popup.wav");
		soundURL[18] = getClass().getResource("/sound/rpg_ambience.wav");
		soundURL[19] = getClass().getResource("/sound/drinking.wav");
		soundURL[20] = getClass().getResource("/sound/tp.wav");
		soundURL[21] = getClass().getResource("/sound/door.wav");
		soundURL[22] = getClass().getResource("/sound/blocked.wav");
		soundURL[23] = getClass().getResource("/sound/parry.wav");
		soundURL[24] = getClass().getResource("/sound/speak.wav"); 
		soundURL[25] = getClass().getResource("/sound/unlock.wav");
		soundURL[26] = getClass().getResource("/sound/boss_music.wav");
		soundURL[27] = getClass().getResource("/sound/end.wav");
	}
	
	public synchronized void setFile(int i) {
		// This slot owns the current music clip; effects have independent lifetimes.
		stop();
		clip = loadClip(i);
	}

	private Clip loadClip(int i) {
		if(i < 0 || i >= soundURL.length || soundURL[i] == null) {
			System.err.println("Sound resource not found: " + i);
			return null;
		}

		Clip loadedClip = null;
		try(AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i])) {
			loadedClip = AudioSystem.getClip();
			loadedClip.open(ais);
			applyVolume(loadedClip, getScaledVolume());
			return loadedClip;
		}catch(Exception e) {
			if(loadedClip != null) {
				loadedClip.close();
			}
			System.err.println("Unable to load sound " + i + ": " + e.getMessage());
			return null;
		}
	}

	public void playEffect(int i) {
		// Do not replace a playing effect: combat/UI sounds may overlap.
		startOneShot(loadClip(i));
	}

	private void startOneShot(Clip playingClip) {
		if(playingClip == null || !playingClip.isOpen()) {
			return;
		}
		playingClip.addLineListener(event -> {
			if(event.getType() == LineEvent.Type.STOP) {
				// Capture this clip, never the mutable music slot.
				playingClip.close();
			}
		});
		try {
			playingClip.start();
		}catch(RuntimeException e) {
			playingClip.close();
			System.err.println("Unable to play sound: " + e.getMessage());
		}
	}

    public synchronized void setVolume(float volume) {
        applyVolume(clip, mapToDecibels(volume));
    }

	private void applyVolume(Clip target, float decibels) {
		if(target != null && target.isOpen()
				&& target.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			FloatControl control = (FloatControl)target.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(Math.max(control.getMinimum(), Math.min(control.getMaximum(), decibels)));
		}
	}

    private float mapToDecibels(float volume) {
        // Map 0 -> -80.0 dB (mute), 1 -> 6.0 dB (max volume)
        return -80.0f + (volume * 86.0f);  // 86.0 = 6.0 - (-80.0)
    }
	
	public synchronized void play() {
		startOneShot(clip);
	}
	
	public synchronized void loop() {
		if(clip != null && clip.isOpen()) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public synchronized void stop() {
		if(clip != null) {
			clip.stop();
			clip.close();
			clip = null;
		}
	}
	
	public synchronized void checkVolume() {
		applyVolume(clip, getScaledVolume());
	}

	private float getScaledVolume() {
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		
		return volume;
	}
}
