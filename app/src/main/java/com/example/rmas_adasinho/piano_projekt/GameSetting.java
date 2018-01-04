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

    public static int width;
    public static int height;
    public static int tileHeight;
    public static int tileWidth;
    public static float speed = 2f;
    public static int [] position = new int[4];
    public static int count = 0;
    public static int spawnPoint;
    public static Tile lastSpawnTile = new Tile();
    public static int score = 0;
    public static boolean endGame = false;
    public static boolean restart = false;
    public static int tileSpawnCounter = 0;

    public static AudioPlayer audioPlayer;
    public static ArrayList<TrackInfo> TRACKS;

    //public static Vector<String> namesOfMusics;

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
