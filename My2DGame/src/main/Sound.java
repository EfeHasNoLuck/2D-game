package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/BestGameEver.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/Judas.wav");
		soundURL[6] = getClass().getResource("/sound/ElderScroll.wav");
	}
	
	public void setFile(int i) {
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
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
}
