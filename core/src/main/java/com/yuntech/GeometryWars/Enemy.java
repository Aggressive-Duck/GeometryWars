package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends Entity {

    private int timeUntilStart = 60;
    public boolean IsActive() {
        return timeUntilStart <= 0;
    }

    public Enemy(Texture image, Vector2 position) {
        this.image = image;
        Position = position;
        Radius = image.getWidth() / 2f;
        color = new Color(0, 0, 0, 0);
    }

    @Override
    public void update(float delta) {
        if (timeUntilStart <= 0) {
            //敵人行為擺這
        }
        else {
            timeUntilStart--;
            Color white = Color.WHITE;
            white.a *= 1 - timeUntilStart / 60f;
            color = white;
        }

//        Position = velocity;


    }
}
