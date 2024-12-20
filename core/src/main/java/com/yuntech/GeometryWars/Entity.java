package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Texture image;
    protected com.badlogic.gdx.graphics.Color color = com.badlogic.gdx.graphics.Color.WHITE;
    public Vector2 position, velocity;
    public float orientation;
    public float radius = 20;
    public boolean isExpired;
    public Sprite sprite;


    public Vector2 getSize() {
        return image == null ? new Vector2(0, 0) : new Vector2(image.getWidth(), image.getHeight());
    }

    public abstract void update(float delta);

    public void draw(SpriteBatch spriteBatch) {
//        spriteBatch.draw(image, position.x, position.y, getSize().x / 2, getSize().y / 2, getSize().x, getSize().y, 1, 1, orientation, 0, 0, image.getWidth(), image.getHeight(), false, false);
        sprite.draw(spriteBatch);

    }
}
