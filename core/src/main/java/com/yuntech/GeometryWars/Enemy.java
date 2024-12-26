package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Enemy extends Entity {

    private int timeUntilStart = 60;
    private List<Iterator<Integer>> behaviours = new ArrayList<>();
    public boolean IsActive() {
        return timeUntilStart <= 0;
    }

    public Enemy(Texture image, Vector2 position) {
        this.image = image;
        Position = position;
        Radius = image.getWidth() / 2f;
        color = new Color(0, 0, 0, 0);
        Sprite = new Sprite(image);
    }

    @Override
    public void update(float delta) {
        if (timeUntilStart <= 0) {
            //敵人行為擺這
            ApplyBehaviors();
        }
        else {
            timeUntilStart--;
            Color white = Color.WHITE;
            white.a *= 1 - timeUntilStart / 60f;
            color = white;
        }

        Sprite.translate((Velocity.x) * delta, (Velocity.y) * delta);
        Sprite.setX(MathUtils.clamp(Sprite.getX(), 0, GameRoot.ScreenWidth - getSize().x));
        Sprite.setY(MathUtils.clamp(Sprite.getY(), 0, GameRoot.ScreenHeight - getSize().y));

        Velocity.mulAdd(Velocity,0.8f);
//        Position = velocity;
    }

    private void AddBehaviour(Iterator<Integer> behaviour)
    {
        behaviours.add(behaviour);
    }

    private void ApplyBehaviors()
    {
        for (int i = 0; i < behaviours.size(); i++)
        {
            if (!behaviours.get(i).hasNext())
            {
                behaviours.remove(i--); //what?
            }
        }
    }

    public void WasShot() {
        IsExpired = true;
    }

    public Iterator<Integer> followPlayer(final float acceleration) {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                Vector2 playerPosition = PlayerShip.getInstance().Position;
                Vector2 direction = playerPosition.sub(Position).nor().scl(acceleration);
                Velocity.add(direction);
                if (!Velocity.isZero()) {
                    Orientation = Velocity.angleDeg();
                    Sprite.setRotation(Orientation);
                }
                return 0;
            }
        };
    }

    public Iterator<Integer> followPlayer() {
        return followPlayer(1f);
    }

}
