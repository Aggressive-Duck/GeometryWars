package com.yuntech.GeometryWars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Enemy extends Entity {

    private int timeUntilStart = 60;
    private List<Iterator<Integer>> behaviours = new ArrayList<>();
    private float worldWidth = GameRoot.Viewport.getWorldWidth();
    private float worldHeight = GameRoot.Viewport.getWorldHeight();
    private Vector2 ScreenSize = new Vector2(worldWidth, worldHeight);
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

    public static Enemy CreateSeeker(Vector2 position)
    {
        var enemy = new Enemy(Art.Seeker, position);
        enemy.AddBehaviour(enemy.FollowPlayer());

        return enemy;
    }

    public static Enemy createWanderer(Vector2 position) {
        Enemy enemy = new Enemy(Art.Wanderer, position);
        enemy.AddBehaviour(enemy.MoveRandomly());

        return enemy;
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


    //region Behaviors
    public Iterator<Integer> FollowPlayer(final float acceleration) {
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

    public Iterator<Integer> FollowPlayer() {
        return FollowPlayer(1f);
    }

    public Iterator<Integer> MoveRandomly() {
        return new Iterator<Integer>() {
            float direction = MathUtils.random(0f, MathUtils.PI2 );

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                direction += MathUtils.random(-0.1f, 0.1f);
                direction = MathUtil.WrapAngle(direction);

                for (int i = 0; i < 6; i++) {
                    Velocity.rotateRad(direction*0.4f);
                    Orientation -= 0.05f;

                    Rectangle bounds = new Rectangle();
                    bounds.set(0, 0, worldWidth - Sprite.getWidth(), worldHeight - Sprite.getHeight());
                    // if the enemy is outside the bounds, make it move away from the edge
                    if (!bounds.contains(Position)) {
                        direction = Position.cpy().sub(ScreenSize.scl(0.5f)).angleRad() + MathUtils.random.nextFloat(-MathUtils.PI / 2, MathUtils.PI / 2);
                    }
                    return 0;
                }
                return 0;
            }
        };
    }
    //endregion

}
