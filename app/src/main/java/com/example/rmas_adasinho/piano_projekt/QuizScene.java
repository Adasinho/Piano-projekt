package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by RMAS-Adasinho on 2018-01-01.
 */

/**
 * Scena występująca po pomyślnym przejściu danego etapu
 */
public class QuizScene implements Scene {

    /**
     * Przechowuje odpowiedzi dotyczące nazwy utworu
     */
    private ArrayList<CustomButton> quizAnswers;

    public QuizScene() {
        quizAnswers = new ArrayList<>(4);
        for(int i = 0; i < 4; i++)
            quizAnswers.add(new CustomButton(GameSetting.TRACKS.get(i).getName(),new Point(GameSetting.width/2,GameSetting.height/8*(i+1)),true));
    }

    @Override
    public void terminate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0; i < 4; i++)
            quizAnswers.get(i).draw(canvas);

    }

    @Override
    public void reciveTouch(MotionEvent event) {
        for(int i = 0; i < 4; i++) {
            if(quizAnswers.get(i).reciveTouch(event)) {
                if(GameSetting.TRACKS.get(i).getFileName() == GameSetting.audioPlayer.getName())
                    GameSetting.score += 10;
                GameSetting.endGame = true;
                GameSetting.restart = true;
                GameSetting.speed += 0.25f;
                SceneManager.ACTIVE_SCENE = 0;
            }
        }
    }
}
