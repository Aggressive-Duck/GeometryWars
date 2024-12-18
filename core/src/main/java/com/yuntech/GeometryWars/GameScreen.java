package com.yuntech.GeometryWars;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameScreen implements Screen {
    final GameRoot game;
    private SpriteBatch spriteBatch;
    public static Pixmap pixmap;
    public static int xHotSpot;
    public static int yHotSpot;

    public GameScreen(GameRoot game) {
        this.game = game;
        this.spriteBatch = game.getSpriteBatch();
        pixmap = new Pixmap(Gdx.files.internal("Art/Cursor.png"));
        xHotSpot = pixmap.getWidth() / 2;
        yHotSpot = pixmap.getHeight() / 2;
        Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotSpot, yHotSpot);
 // We don't need the pixmap anymore
        Gdx.graphics.setCursor(cursor);

        // Initialize entities
        EntityManager.add(PlayerShip.getInstance());

        // Set input processor
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    Gdx.app.exit();
                }
                return true;
            }
        });
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        EntityManager.update(delta);
        InputHandler.update();
        // Update game logic

        GameRoot.Viewport.apply();
        spriteBatch.setProjectionMatrix(GameRoot.Viewport.getCamera().combined);
        // Draw entities
        spriteBatch.begin();
        EntityManager.draw(spriteBatch);
        spriteBatch.draw(Art.pointer, Gdx.input.getX(), Gdx.input.getY(), 1, 1);
        spriteBatch.end();
    }


    @Override
    public void resize(int width, int height) {
        GameRoot.Viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}

