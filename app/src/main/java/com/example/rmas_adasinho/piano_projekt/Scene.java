package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by RMAS-Adasinho on 2017-12-31.
 */

/**
 * Opisuję scenę
 */
public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void reciveTouch(MotionEvent event);
}
