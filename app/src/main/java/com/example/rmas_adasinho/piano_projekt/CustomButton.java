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

/**
 * Klasa opisująca przycisk
 */
public class CustomButton implements SceneComponent {

    private String text;
    private Rect rect;
    private int fontSize;
    private int padding = 10;
    private Point point;
    private boolean transparent;


    /**
     * @param text tekst jaki będzie wyświetlany na przycisku
     * @param point miejsce na ekranie gdzie zostanie on wyświetlony
     * @param transparent czy ma być bez tła
     */
    public CustomButton(String text, Point point, boolean transparent) {
        this.text = text;
        fontSize = GameSetting.width/13;
        int textLength = text.length() * fontSize;
        this.point = point;
        this.transparent = transparent;

        rect = new Rect(point.x - textLength/2, point.y - fontSize, point.x + textLength/2, point.y + fontSize);
    }

    /**
     *
     * @param text tekst jaki będzie wyświetlany na przycisku
     * @param point miejsce na ekranie gdzie zostanie on wyświetlony
     * @param transparent czy ma być bez tła
     * @param fontSize wielkość czcionki
     */
    public CustomButton(String text, Point point, boolean transparent, int fontSize) {
        this.text = text;
        this.fontSize = fontSize;
        int textLength = text.length() * fontSize;
        this.point = point;
        this.transparent = transparent;

        rect = new Rect(point.x - textLength/2, point.y - fontSize, point.x + textLength/2, point.y + fontSize);
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
        paint.setTextSize(fontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        canvas.drawText(text,point.x,point.y + fontSize/3,paint);
    }

    @Override
    public boolean reciveTouch(MotionEvent event) {
        if((event.getX() > rect.left) && (event.getX() < rect.right) && (event.getY() > rect.top) && (event.getY() < rect.bottom)) return true;
            else return false;
    }
}
