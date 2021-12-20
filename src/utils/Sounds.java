package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sounds {

    private static void playSound(String musicName){
        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(new File(Constant.SOUND_PATH + musicName).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(input);
            c.start();

        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void buttonSound(Boolean option){
        if (option) {
            playSound("button.wav");
        }
    }
    public static void dragSound(Boolean option){
        if (option) {
            playSound("drag.wav");
        }
    }
    public static void failSound(Boolean option){
        if (option) {
            playSound("fail.wav");
        }
    }
    public static void victorySound(Boolean option){
        if (option) {
            playSound("victory.wav");
        }
    }
}
