package com.yuntech.GeometryWars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Art {
    public static Texture Player;
    public static Texture Seeker;
    public static Texture Wanderer;
    public static Texture Bullet;
    public static Texture Pointer;


    public static void load() {
        Player = new Texture(Gdx.files.internal("Art/Player.png"));
        Seeker = new Texture(Gdx.files.internal("Art/Seeker.png"));
        Wanderer = new Texture(Gdx.files.internal("Art/Wanderer.png"));
        Bullet = new Texture(Gdx.files.internal("Art/Bullet.png"));
        Pointer = new Texture(Gdx.files.internal("Art/Pointer.png"));

    }

    public static void dispose() {
//        pointer.dispose();
    }
}

