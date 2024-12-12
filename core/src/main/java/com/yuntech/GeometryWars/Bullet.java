package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {
    public Bullet(Vector2 position, Vector2 velocity) {
        this.image = Art.bullet;
        this.position = position;
        this.velocity = velocity;
        this.orientation = velocity.angleRad();
        this.radius = 8;
    }

    @Override
    public void update(float delta) {
        float bulletWidth = image.getWidth();
        float bulletHeight = image.getHeight();
        if (velocity.len2() > 0) {
            orientation = velocity.angleRad();
        }

        position.add(velocity);

        // delete bullets that go off-screen
        if (position.x < -bulletWidth &&  position.y < -bulletHeight) {
            isExpired = true;
        }
    }
}

