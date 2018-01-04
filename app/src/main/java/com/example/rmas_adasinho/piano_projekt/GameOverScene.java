package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * Created by RMAS-Adasinho on 2018-01-01.
 */

public class GameOverScene implements Scene {

    CustomButton retry;

    public GameOverScene() {
        retry = new CustomButton("Retry?",new Point(GameSetting.width/2,GameSetting.height*5/8), false);
    }

    @Override
    public void reciveTouch(MotionEvent event) {
        if(retry.reciveTouch(event)) {
            GameSetting.restart = true;
            GameSetting.score = 0;
            GameSetting.speed = 2f;
            SceneManager.ACTIVE_SCENE = 0;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        retry.draw(canvas);
    }

    @Override
    public void terminate() {

    }
}
