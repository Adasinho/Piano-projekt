package com.example.rmas_adasinho.piano_projekt;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Random;

/**
 * Created by RMAS-Adasinho on 2017-12-29.
 */

/**
 * Klasa odpowiadająca za obsługę dźwięku w aplikacji
 */
public class AudioPlayer {

    private String name;

    /**
     * Ilośc nut
     */
    private int frames;

    /**
     *  Przechowuje aktualną nutę
     */
    private int actualFrame;

    private MediaPlayer mp;

    private Context context;

    public AudioPlayer (Context context) {
        this.context = context;
        mp = new MediaPlayer();
        actualFrame = 1;
    }

    public void loadNewAudio() {
        Random r = new Random();
        int randomNumber = r.nextInt(4);
        this.name = GameSetting.TRACKS.get(randomNumber).getFileName();
        this.frames = GameSetting.TRACKS.get(randomNumber).getFrames();
        actualFrame = 1;

        mp = MediaPlayer.create(context, GameSetting.getResId(name+"_"+actualFrame, R.raw.class));
    }

    /**
     * Wczytuje oraz odtwarza kolejną nutę utworu
     */
    public void playFrame() {
        if(actualFrame <= frames) {
            //mp.stop();
            mp.reset();

            mp = MediaPlayer.create(context, GameSetting.getResId(name+"_"+actualFrame, R.raw.class));
            mp.start();
            actualFrame++;
        } //else loadNewAudio();
    }

    public String getName() {
        return name;
    }

    public int getFrames() {
        return frames;
    }
}
