package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by RMAS-Adasinho on 2018-01-01.
 */

public class CustomButton implements SceneComponent {

    private String text;
    private Rect rect;
    private static int FONT_SIZE;
    private int padding = 10;
    private Point point;
    private boolean transparent;

    public CustomButton(String text, Point point, boolean transparent) {
        this.text = text;
        FONT_SIZE = GameSetting.width/13;
        int textLength = text.length() * FONT_SIZE;
        this.point = point;
        this.transparent = transparent;

        rect = new Rect(point.x - textLength/2, point.y - FONT_SIZE, point.x + textLength/2, point.y + FONT_SIZE);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        if(!transparent) {
            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(0);
            canvas.drawRect(rect, paint);
        }

        paint.setStrokeWidth(0);
        paint.setTextSize(FONT_SIZE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        canvas.drawText(text,point.x,point.y + FONT_SIZE/3,paint);
    }

    @Override
    public boolean reciveTouch(MotionEvent event) {
        if((event.getX() > rect.left) && (event.getX() < rect.right) && (event.getY() > rect.top) && (event.getY() < rect.bottom)) return true;
            else return false;
    }
}
