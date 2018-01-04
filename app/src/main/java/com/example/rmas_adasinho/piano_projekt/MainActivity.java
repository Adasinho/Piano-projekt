package com.example.rmas_adasinho.piano_projekt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        myDb = new DatabaseHelper(context);

        GameSetting.height = size.y;
        GameSetting.width = size.x;
        GameSetting.tileHeight = size.y/4;
        GameSetting.tileWidth = size.x/4;
        GameSetting.spawnPoint = -GameSetting.tileHeight/2;
        GameSetting.setPosition();


        GameSetting.TRACKS = new ArrayList<>(4);
        GameSetting.TRACKS.add(new TrackInfo("Ode To Joy","beethoven_otj",61));
        GameSetting.TRACKS.add(new TrackInfo("Sorcerer","dukas_sa",64));
        GameSetting.TRACKS.add(new TrackInfo("Spring","vivaldi_s",90));
        GameSetting.TRACKS.add(new TrackInfo("Jingle Bells","jb",101));

        context = getApplicationContext();

        setContentView(new GamePanel(context));
    }

    public static Context getAppContext() {
        return context;
    }
}
