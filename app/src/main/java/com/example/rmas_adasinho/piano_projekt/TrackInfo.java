package com.example.rmas_adasinho.piano_projekt;

/**
 * Created by RMAS-Adasinho on 2018-01-02.
 */

public class TrackInfo {
    private String name;
    private String fileName;
    private int frames;

    public TrackInfo(String name, String fileName, int frames) {
        this.name = name;
        this.fileName = fileName;
        this.frames = frames;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFrames() {
        return frames;
    }
}
