package com.example.rmas_adasinho.piano_projekt;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by RMAS-Adasinho on 2017-10-21.
 */

/**
 * Pojedyńczy klawisz
 */
public class Tile implements GameObject{

    /**
     * Określa czy jest to klawisz czarny (ten który możemy naciskać)
     */
    private boolean blackTile;

    private Rect rectangle;

    /**
     * Punkt w jakim znajduje się klawisz
     */
    private Point point;

    /**
     * Kolor klawisza
     */
    private int color;

    /**
     * Sprawia że klawisz staje się nieaktywny
     */
    private boolean disable;

    public Tile() {
        this.point = new Point(0,0);
    }

    public Tile(Point point, boolean blackTile) {
        if(blackTile) this.color = Color.DKGRAY;
            else this.color = Color.WHITE;

        this.rectangle = new Rect(1, 1, (GameSetting.width / 4 - 2), (GameSetting.height / 4 - 2));
        this.point = point;
        this.blackTile = blackTile;
        this.disable = false;
    }

    /**
     * Sprawdza jaki klawisz został naciśniety
     * @param point
     */
    public void trigger(Point point) {
        if((!disable) && (!GameSetting.endGame)) {
            if (onColliderEnter(point)) {
                disable = true;
                if(blackTile) {
                    this.color = Color.LTGRAY;
                    GameSetting.score++;
                    GameSetting.audioPlayer.playFrame();
                } else {
                    this.color = Color.RED;
                    GameSetting.endGame = true;
                    SceneManager.ACTIVE_SCENE = 1;
                }
            }
        }
    }

    /**
     * Sprawdza czy jakiś czarny klawisz został pominięty
     * @return czarny = true, inny = false
     */
    public boolean missing() {
        if((disable == false) && (blackTile == true)) return true;
            else return false;
    }

    /**
     * Sprawdza czy klawisz został naciśniety
     * @param point
     * @return
     */
    private boolean onColliderEnter(Point point) {
        if((rectangle.left < point.x) && (rectangle.right > point.x) && (rectangle.top < point.y) && (rectangle.bottom > point.y)) return true;
        else return false;
    }

    /**
     * Odpowiada za poruszanie się (spadanie) klawisza
     */
    void move() {
        point.set(point.x, point.y + (int)(10 * GameSetting.speed));
    }

    /**
     * Generuje nowe klawisze na scenie
     * @param tiles
     */
    public static void spawn(ArrayList<Tile> tiles) {
        if(GameSetting.count != 20) {
            Random r = new Random();
            int randomNumber = r.nextInt(4);
            if (GameSetting.count == 0) { // pierwszy spawn

                for (int i = 0; i < 4; i++) {
                    if (i != randomNumber)
                        tiles.add(new Tile(new Point(GameSetting.position[i], GameSetting.spawnPoint), false));
                    else
                        tiles.add(new Tile(new Point(GameSetting.position[i], GameSetting.spawnPoint), true));
                    GameSetting.count++;
                }
                GameSetting.lastSpawnTile = tiles.get(tiles.size()-1);
                //GameSetting.spawnPoint = tiles[GameSetting.count - 1].getPosition().y - GameSetting.tileHeight;

                GameSetting.tileSpawnCounter++;

            } else { // kolejne spawny
                if ((GameSetting.count < 20) && (GameSetting.lastSpawnTile.getPosition().y > -GameSetting.tileHeight)) {

                    GameSetting.spawnPoint = GameSetting.lastSpawnTile.getPosition().y - GameSetting.tileHeight;

                    for (int i = 0; i < 4; i++) {
                        if (i % 4 == randomNumber)
                            tiles.add(new Tile(new Point(GameSetting.position[i], GameSetting.spawnPoint), true));
                        else
                            tiles.add(new Tile(new Point(GameSetting.position[i], GameSetting.spawnPoint), false));
                        GameSetting.count++;

                    }

                    GameSetting.lastSpawnTile = tiles.get(tiles.size()-1);

                    GameSetting.tileSpawnCounter++;
                }

            }
        }
    }

    public Point getPosition() {
        return point;
    }

    @Override
    public void update() {
        // l,t,r,b
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }
}
