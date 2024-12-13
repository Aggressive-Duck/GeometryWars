package com.yuntech.GeometryWars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    final GameRoot game;
    private SpriteBatch spriteBatch;

    public GameScreen(GameRoot game) {
        this.game = game;
        this.spriteBatch = game.getSpriteBatch();

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
        logic();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update game logic


        // Draw entities
        spriteBatch.begin();
        EntityManager.draw(spriteBatch);
        spriteBatch.draw(Art.pointer, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        spriteBatch.end();
    }

    private void logic() {

        float delta = Gdx.graphics.getDeltaTime();
        EntityManager.update(delta);

    }

    @Override
    public void resize(int width, int height) {
        GameRoot.Viewport.update(width, height);
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

