package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Entity {


    public Bullet(Vector2 position,Vector2 velocity) {
        this.image = Art.Bullet;
        this.Position = position;
        this.Velocity = velocity;
        this.Orientation = velocity.angleDeg();
        this.Radius = 8;
        this.Sprite = new Sprite(this.image);
        Sprite.setSize(0.5f,0.25f);
        Sprite.setOrigin(PlayerShip.getInstance().Sprite.getWidth() / 2, PlayerShip.getInstance().Sprite.getHeight() / 2);
        Sprite.setPosition(position.x, position.y);
        Sprite.setRotation(velocity.angleDeg());


    }

    @Override
    public void update(float delta) {
        float bulletWidth = Sprite.getWidth();
        float bulletHeight = Sprite.getHeight();


//        System.out.println(velocity);


        if (Velocity.len2() > 0) {
            Orientation = Velocity.angleDeg();
//            System.out.println(orientation);

        }
        Sprite.setRotation(Orientation);
        Sprite.translate((Velocity.x) * delta, (Velocity.y) * delta);


        // delete bullets that go off-screen
        if (Sprite.getX() < -bulletWidth &&  Sprite.getY() < -bulletHeight) {
            IsExpired = true;
        }
    }
}

