package com.example.rmas_adasinho.piano_projekt;

import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by RMAS-Adasinho on 2018-01-01.
 */

/**
 * Scena występująca gdy gracz naciśnie klawisz bądź ominie jakiś czarny
 */
public class GameOverScene implements Scene {

    DatabaseHelper myDb;

    /**
     * Przycisk odpowiadający za możliwość uruchomienia gry jeszcze raz (nie jest traktowany jako przycisk)
     */

    CustomButton retry;
    /**
     * Tekst "Najlepsze wyniki" na górze ekranu (nie jest traktowany jako przycisk)
     */
    CustomButton text1;

    /**
     * Tekst "Ciekawostka" na dole ekranu (nie jest traktowany jako przycisk)
     */
    CustomButton text2;

    /**
     * Tekst na dole pod napisaem "Ciekawostka" (nie są traktowane jako przyciski)
     */
    ArrayList<CustomButton> info;

    /**
     * Przechowuje 10 najlepszych wyników, wyświetlane są one w górnej połowie ekranu tej sceny (nie są traktowane jako przyciski)
     */
    ArrayList<CustomButton> scores;

    /**
     * Pomocnicza zmienna pozwlająca wykonać instrukcję w momencie przełączenia na tą scenę
     */
    private boolean firstShow = true;

    /**
     * Przechowuje tylko tekst z sekcji "Ciekawostka"
     */
    private static ArrayList<String> infoBox;

    /**
     * Pomocnicza zmienna do wyświetlania tekstu w sekcji ciekawostka
     */
    private int indexOfInfoBox;

    public GameOverScene() {
        retry = new CustomButton("Restart?",new Point(GameSetting.width/2,GameSetting.height*5/8), false);
        text1 = new CustomButton("Najlepsze wyniki", new Point(GameSetting.width/2, GameSetting.height/11), true);
        text2 = new CustomButton("Ciekawostka", new Point(GameSetting.width/2, GameSetting.height*15/20), true);

        scores = new ArrayList<>(10);
        infoBox = new ArrayList<>(9);
        infoBox.add("Czy wiesz, że");
        infoBox.add("Wolfgang Amadeus Mozart zaczął tworzyć");
        infoBox.add("gdy miał zaledwie 5 lat?");

        infoBox.add("Słynne 'Jingle Bells'");
        infoBox.add("pierwotnie posiadało tytuł");
        infoBox.add("'The One Horse Open Sleigh'");

        infoBox.add("Jednym z najsłynniejszych");
        infoBox.add("kompozytorów muzyki poważnej w Japonii");
        infoBox.add("jest Fryderyk Chopin");
        //saveScore();
    }

    @Override
    public void reciveTouch(MotionEvent event) {
        if(retry.reciveTouch(event)) {
            GameSetting.restart = true;
            GameSetting.score = 0;
            GameSetting.speed = 2f;

            firstShow = true;
            scores = new ArrayList<>(10);

            SceneManager.ACTIVE_SCENE = 0;
        }
    }

    @Override
    public void update() {
        if(firstShow) {
            saveScore();
            loadScore();
            firstShow = false;
            indexOfInfoBox = new Random().nextInt(3);
            info = new ArrayList<>(3);

            for(int i = 0; i < 3; i++)
                info.add(new CustomButton(infoBox.get(3*indexOfInfoBox+i), new Point(GameSetting.width/2, GameSetting.height*4/5 + (GameSetting.width/24*i)), true, GameSetting.width/24));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        retry.draw(canvas);
        text1.draw(canvas);
        text2.draw(canvas);
        if(!firstShow) {
            showScore(canvas);
            for(int i = 0; i < 3; i++)
                info.get(i).draw(canvas);
        }

    }

    @Override
    public void terminate() {

    }

    /**
     * Zapisanie wyniku do bazy
     */
    private void saveScore() {

        myDb = new DatabaseHelper(MainActivity.getAppContext());

        myDb.insertData(String.valueOf(GameSetting.score));
    }

    /**
     * Wczytanie najlepszych wyników
     */
    private void loadScore() {
        Cursor cursor = myDb.getAllData();

        int i = 0;
        while(cursor.moveToNext() && (i < 10)) {
            scores.add(new CustomButton((i+1) + ": " + cursor.getString(1),new Point(GameSetting.width/2,GameSetting.height/6 + GameSetting.height/26*i),true,GameSetting.width/17));
            i++;
        }
    }

    /**
     * Wyświetlenie danych na scenie
     * @param canvas
     */
    private void showScore(Canvas canvas) {
        for(int i = 0; i < scores.size(); i++) {
            scores.get(i).draw(canvas);
        }
    }
}
