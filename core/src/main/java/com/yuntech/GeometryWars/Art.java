package com.yuntech.GeometryWars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Art {
    public static Texture player;
    public static Texture seeker;
    public static Texture wanderer;
    public static Texture bullet;
    public static Texture pointer;


    public static void load() {
        player = new Texture(Gdx.files.internal("Art/Player.png"));
        seeker = new Texture(Gdx.files.internal("Art/Seeker.png"));
        wanderer = new Texture(Gdx.files.internal("Art/Wanderer.png"));
        bullet = new Texture(Gdx.files.internal("Art/Bullet.png"));
        pointer = new Texture(Gdx.files.internal("Art/Pointer.png"));

    }

    public static void dispose() {
//        pointer.dispose();
    }
}

