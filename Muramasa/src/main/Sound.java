package main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Map<Integer, Clip> clips = new HashMap<>();
    private Clip clip;
    private URL soundURL[] = new URL[30];
    private FloatControl fc;
    public int VolumeScale = 3;
    private float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/outside.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = null;
        soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/sound/levelup.wav");
        soundURL[9] = getClass().getResource("/sound/cursor.wav");
        soundURL[10] = getClass().getResource("/sound/burning.wav");
        soundURL[11] = getClass().getResource("/sound/cuttree.wav");
        soundURL[12] = getClass().getResource("/sound/gameover.wav");
        soundURL[13] = getClass().getResource("/sound/stairs.wav");
        soundURL[14] = getClass().getResource("/sound/sleep.wav");
        soundURL[15] = getClass().getResource("/sound/blocked.wav");
        soundURL[16] = getClass().getResource("/sound/parry.wav");
        soundURL[17] = getClass().getResource("/sound/speak.wav");
        soundURL[18] = getClass().getResource("/sound/merchant.wav");
        soundURL[19] = getClass().getResource("/sound/dungeon.wav");
        soundURL[20] = getClass().getResource("/sound/chipwall.wav");
        soundURL[21] = getClass().getResource("/sound/dooropen.wav");
        soundURL[22] = getClass().getResource("/sound/death.wav");
        soundURL[23] = getClass().getResource("/sound/incantation.wav");    
        soundURL[24] = getClass().getResource("/sound/thunder.wav");
        soundURL[25] = getClass().getResource("/sound/summon.wav");
        soundURL[26] = getClass().getResource("/sound/opening.wav");
    }

    public void setFile(int i) {
        try {
            if (!clips.containsKey(i)) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
                Clip newClip = AudioSystem.getClip();
                newClip.open(ais);
                clips.put(i, newClip);
            }
            clip = clips.get(i);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Reset clip to the beginning
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0); // Reset clip to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void checkVolume() {
        if (fc != null) {
            switch (VolumeScale) {
                case 0:
                    volume = -80f;
                    break;
                case 1:
                    volume = -20f;
                    break;
                case 2:
                    volume = -12f;
                    break;
                case 3:
                    volume = -5f;
                    break;
                case 4:
                    volume = 1f;
                    break;
                case 5:
                    volume = 6f;
                    break;
            }
            fc.setValue(volume);
        }
    }
}
