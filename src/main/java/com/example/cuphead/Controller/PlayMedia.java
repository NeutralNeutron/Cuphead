package com.example.cuphead.Controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayMedia {
     public static AudioClip playMusic(Media media){
        AudioClip mediaPlayertemp= new AudioClip(media.getSource());
        mediaPlayertemp.setVolume(0.15);
        mediaPlayertemp.play();
        return mediaPlayertemp;
    }
}
