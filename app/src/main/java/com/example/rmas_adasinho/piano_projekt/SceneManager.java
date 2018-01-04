package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by RMAS-Adasinho on 2017-12-31.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        ACTIVE_SCENE = 0;
        scenes.add(new GameplayScene());
        scenes.add(new GameOverScene());
        scenes.add(new QuizScene());
    }

    public void reciveToutch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).reciveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
