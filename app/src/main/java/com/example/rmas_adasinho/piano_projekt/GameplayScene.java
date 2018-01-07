package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by RMAS-Adasinho on 2017-12-31.
 */

/**
 * Scena z rozgrywką
 */
public class GameplayScene implements Scene {

    /**
     * Przechowuje klawisze spadające od góry ekranu
     */
    private ArrayList<Tile> tile = new ArrayList<>(20);
    //private boolean done = false;

    public GameplayScene() {
        GameSetting.audioPlayer = new AudioPlayer(MainActivity.getAppContext());
        GameSetting.audioPlayer.loadNewAudio();
    }


    /**
     * Wczytuje rozgrywkę od nowa
     */
    public void restart() {
        for(Tile t : tile)
            tile.remove(t);

        GameSetting.tileSpawnCounter = 0;

        tile = new ArrayList<>(20);
        GameSetting.count = 0;
        GameSetting.endGame = false;
        GameSetting.restart = false;
        //GameSetting.score = 0;

        GameSetting.audioPlayer.loadNewAudio();
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void reciveTouch(MotionEvent event) {
        try {
            for (Tile t : tile) {
                t.trigger(new Point((int) event.getX(), (int) event.getY()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        for(Tile t : tile)
            t.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(GameSetting.width/10);
        paint.setTextAlign(Paint.Align.CENTER);
        Shader shader = new Shader();
        paint.setShader(shader);

        if(GameSetting.score < 10) canvas.drawText(String.valueOf("0"+GameSetting.score),GameSetting.width/2,GameSetting.height/10,paint);
        else canvas.drawText(String.valueOf(GameSetting.score),GameSetting.width/2,GameSetting.height/10,paint);
    }

    @Override
    public void update() {
        if(!GameSetting.endGame) {
            if(GameSetting.tileSpawnCounter != GameSetting.audioPlayer.getFrames()) {
                Tile.spawn(tile);
            } else {
                if(GameSetting.count == 0) {
                    GameSetting.tileSpawnCounter = 0;
                    SceneManager.ACTIVE_SCENE = 2;
                }
            }

            try {
                for (int i = 0; i < 4; i++) {

                    if (tile.get(0).getPosition().y > (GameSetting.height + GameSetting.tileHeight / 2)) {
                        if(tile.get(0).missing()) {
                            GameSetting.endGame = true;
                            SceneManager.ACTIVE_SCENE = 1;
                        }
                        tile.remove(tile.get(0));
                        GameSetting.count--;
                    }
                }

                for (Tile t : tile) {

                    t.move();
                    t.update();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if(GameSetting.restart) restart();
        }
    }
}
