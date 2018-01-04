package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.EventListener;

/**
 * Created by RMAS-Adasinho on 2018-01-01.
 */

public interface SceneComponent {
    public boolean reciveTouch(MotionEvent event);
    public void draw(Canvas canvas);
}
