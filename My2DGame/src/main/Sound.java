package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
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
		soundURL[18] = getClass().getResource("/sound/drinking.wav");
		soundURL[18] = getClass().getResource("/sound/rpg_ambience.wav");
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			checkVolume();
			
		}catch(Exception e) {
		}
	}
	
    public void setVolume(float volume) {
        if (clip != null) {
            try {
                // Map the volume from [0, 1] to [-80.0, 6.0] dB
                float decibelValue = mapToDecibels(volume);

                // Get the volume control (MASTER_GAIN)
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(decibelValue);  // Set the volume in dB
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private float mapToDecibels(float volume) {
        // Map 0 -> -80.0 dB (mute), 1 -> 6.0 dB (max volume)
        return -80.0f + (volume * 86.0f);  // 86.0 = 6.0 - (-80.0)
    }
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
	
		clip.stop();
	}
	
	public void checkVolume() {
		
		switch(volumeScale) {
		case 0: volume = -80f; break;
		case 1: volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;
		}
		
		fc.setValue(volume);
	}
}
