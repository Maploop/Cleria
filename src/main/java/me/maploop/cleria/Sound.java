package me.maploop.cleria;

import me.maploop.cleria.helper.AssetHelper;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sound
{
    Clip clip;
    Map<String, URL> soundUrl = new HashMap<>();

    public Sound() {
        soundUrl.put("cleria", AssetHelper.assetUrl("/assets/sound/Cleria.wav"));
        soundUrl.put("coin", AssetHelper.assetUrl("/assets/sound/coin.wav"));
        soundUrl.put("powerup", AssetHelper.assetUrl("/assets/sound/powerup.wav"));
        soundUrl.put("unlock", AssetHelper.assetUrl("/assets/sound/unlock.wav"));
        soundUrl.put("fanfare", AssetHelper.assetUrl("/assets/sound/fanfare.wav"));
    }

    public void setFile(String i) {
        try {
            clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(soundUrl.get(i)).getFormat()));
            clip.open(AudioSystem.getAudioInputStream(soundUrl.get(i)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
