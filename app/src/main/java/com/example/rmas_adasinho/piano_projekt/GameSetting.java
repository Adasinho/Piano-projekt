package com.example.rmas_adasinho.piano_projekt;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by RMAS-Adasinho on 2017-10-29.
 */

public class GameSetting {

    /**
     * Szerokość ekranu
     */
    public static int width;

    /**
     * Wysokość ekranu
     */
    public static int height;

    /**
     * Wysokość klawisza
     */
    public static int tileHeight;

    /**
     * Szerokość klawisza
     */
    public static int tileWidth;

    /**
     * Prędkość spadania klawiszy
     */
    public static float speed = 2f;

    /**
     * Pozycje w których pojawiają się nowe klawisze
     */
    public static int [] position = new int[4];

    /**
     * Ilość klawiszy na ekranie
     */
    public static int count = 0;

    /**
     * Pomocnicza zmienna przy wyświetlaniu klawiszy
     */
    public static int spawnPoint;

    /**
     * Pomocnicza zmienna, pomaga w ustaleniu odległości w jakiej powinien się pojawić nowy klawisz
     */
    public static Tile lastSpawnTile = new Tile();

    /**
     * Wynik gracza
     */
    public static int score = 0;

    /**
     * Pomocnicza zmienna zatrzymująca rozgrywkę
     */
    public static boolean endGame = false;

    /**
     * Pomocnicza zmienna uruchamiająca grę od nowa
     */
    public static boolean restart = false;


    public static int tileSpawnCounter = 0;

    /**
     * Zarządza audio gry
     */
    public static AudioPlayer audioPlayer;

    /**
     * Przechowuje dane o utworach
     */
    public static ArrayList<TrackInfo> TRACKS;

    //public static Vector<String> namesOfMusics;

    /**
     * Ustala pozycję dla klawiszy w poziomie
     */
    public static void setPosition() {
        position[0] = width/8;
        position[1] = width/8*3;
        position[2] = width/8*5;
        position[3] = width/8*7;
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
