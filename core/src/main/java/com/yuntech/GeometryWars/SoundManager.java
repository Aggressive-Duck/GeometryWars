package com.yuntech.GeometryWars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class SoundManager {
    public static Music music;

    private static final Random rand = new Random();

    private static Sound[] explosions;
    public static Sound getExplosion() {
        return explosions[rand.nextInt(explosions.length)];
    }

    private static Sound[] shots;
    public static Sound getShot() {
        return shots[rand.nextInt(shots.length)];
    }

    private static Sound[] spawns;
    public static Sound getSpawn() {
        return spawns[rand.nextInt(spawns.length)];
    }

    public static void load() {
//        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Music.mp3"));

        explosions = new Sound[8];
        for (int i = 0; i < explosions.length; i++) {
            explosions[i] = Gdx.audio.newSound(Gdx.files.internal("Music/explosion-0" + (i + 1) + ".wav"));
        }

        shots = new Sound[4];
        for (int i = 0; i < shots.length; i++) {
            shots[i] = Gdx.audio.newSound(Gdx.files.internal("Music/shoot-0" + (i + 1) + ".wav"));
        }

        spawns = new Sound[8];
        for (int i = 0; i < spawns.length; i++) {
            spawns[i] = Gdx.audio.newSound(Gdx.files.internal("Music/spawn-0" + (i + 1) + ".wav"));
        }
    }
}


