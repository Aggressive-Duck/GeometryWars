package com.yuntech.GeometryWars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

//bbggggbgbgg
//dasdadsasddsad
public class GameRoot extends Game {
    public static GameRoot Instance;
    public static Viewport Viewport;
    public static OrthographicCamera Camera;
    public static float ScreenWidth = 24f;
    public static float ScreenHeight = 18f;


    private SpriteBatch spriteBatch;
    private Music backgroundMusic;

    @Override
    public void create() {
        Instance = this;
        Camera = new OrthographicCamera();
        Viewport = new FitViewport(ScreenWidth, ScreenHeight, Camera);
        spriteBatch = new SpriteBatch();
//        spriteBatch.setProjectionMatrix(Camera.combined);
        // Load assets
        Art.load();
//        SoundManager.load();

        // Set up background music
//        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();

        // Set initial screen
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        Art.dispose();
//        backgroundMusic.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}

