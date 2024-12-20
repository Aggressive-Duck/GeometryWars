package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {


    public Bullet(Vector2 position,Vector2 velocity) {
        this.image = Art.bullet;
        this.position = position;
        this.velocity = velocity;
        this.orientation = velocity.angleDeg();
        this.radius = 8;
        this.sprite = new Sprite(this.image);
        sprite.setSize(1,1);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setPosition(position.x, position.y);



    }

    @Override
    public void update(float delta) {
        float bulletWidth = sprite.getWidth();
        float bulletHeight = sprite.getHeight();

        sprite.translate((velocity.x) * delta, (velocity.y) * delta);
//        System.out.println(velocity);


        if (velocity.len2() > 0) {
            orientation = velocity.angleDeg();
//            System.out.println(orientation);

        }

        sprite.setRotation(orientation);

        // delete bullets that go off-screen
        if (sprite.getX() < -bulletWidth &&  sprite.getY() < -bulletHeight) {
            isExpired = true;
        }
    }
}

