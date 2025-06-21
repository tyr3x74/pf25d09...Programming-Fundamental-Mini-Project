package Graphical_TicTacToe;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private Clip backgroundMusic;

    public static void playSound(String filepath){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e){
            System.out.println("Error playing sound:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void playBackground(String filepath){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filepath));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audio);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e){
            System.out.println("Error playing background music:" + e.getMessage());
        }
    }

    public void stopBackground(){
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }
}
