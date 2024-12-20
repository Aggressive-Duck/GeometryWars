package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {


    public Bullet(Vector2 position,Vector2 velocity) {
        this.image = Art.bullet;
        this.Position = position;
        this.Velocity = velocity;
        this.Orientation = velocity.angleDeg();
        this.Radius = 8;
        this.sprite = new Sprite(this.image);
        sprite.setSize(0.5f,0.25f);
        sprite.setOrigin(PlayerShip.getInstance().sprite.getWidth() / 2, PlayerShip.getInstance().sprite.getHeight() / 2);
        sprite.setPosition(position.x, position.y);
        sprite.setRotation(velocity.angleDeg());


    }

    @Override
    public void update(float delta) {
        float bulletWidth = sprite.getWidth();
        float bulletHeight = sprite.getHeight();


//        System.out.println(velocity);


        if (Velocity.len2() > 0) {
            Orientation = Velocity.angleDeg();
//            System.out.println(orientation);

        }
        sprite.setRotation(Orientation);
        sprite.translate((Velocity.x) * delta, (Velocity.y) * delta);


        // delete bullets that go off-screen
        if (sprite.getX() < -bulletWidth &&  sprite.getY() < -bulletHeight) {
            IsExpired = true;
        }
    }
}

