package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;

import static java.lang.Math.toDegrees;

public class PlayerShip extends Entity {
    private static PlayerShip instance;
    public static PlayerShip getInstance() {
        if (instance == null) instance = new PlayerShip();
        return instance;
    }

    private static final int cooldownFrames = 6;
    private int cooldownRemaining = 0;
    private int framesUntilRespawn = 0;
    private static final float speed = 8f;
    private static final float bulletSpeed = 11f;
    public Sprite sprite;

    private PlayerShip() {
        this.image = Art.player;
//        this.position = new Vector2(GameRoot.Viewport.getWorldWidth() / 2, GameRoot.Viewport.getWorldHeight() / 2);
        this.velocity = new Vector2(); // Initialize velocity
        this.radius = 10;
        sprite = new Sprite(this.image);
        sprite.setSize(1,1);
        sprite.setOriginCenter();


    }


    public boolean isDead() {
        return framesUntilRespawn > 0;
    }

    @Override
    public void update(float delta) {
        float playerWidth = sprite.getWidth();
        float playerHeight = sprite.getHeight();
        float worldWidth = GameRoot.Viewport.getWorldWidth();
        float worldHeight = GameRoot.Viewport.getWorldHeight();

        if (isDead()) {
            framesUntilRespawn--;
            return;
        }

        // 瞄準和射擊邏輯
//        Vector2 aim = InputHandler.getAimDirection();
//        if (aim.len2() > 0 && cooldownRemaining <= 0) {
//            cooldownRemaining = cooldownFrames;
//            float aimAngle = aim.angleRad();
//            Quaternion aimQuat = new Quaternion().setEulerAnglesRad(0, 0, aimAngle);
//
//            float randomSpread = MathUtils.random(-0.04f, 0.04f) + MathUtils.random(-0.04f, 0.04f);
//            Vector2 vel = new Vector2(bulletSpeed, 0).rotateRad(aimAngle + randomSpread);
//
//            Vector2 offset = new Vector2(35, -8).rotateRad(aimAngle);
//            EntityManager.add(new Bullet(position.cpy().add(offset), vel));
//
//            offset.set(35, 8).rotateRad(aimAngle);
//            EntityManager.add(new Bullet(position.cpy().add(offset), vel));
//        }



        // 移動邏輯
        velocity.set(InputHandler.getMovementDirection()).scl(speed);

        sprite.translate((velocity.x) * delta, (velocity.y) * delta);

        // 限制玩家在螢幕邊界內
        sprite.setX(MathUtils.clamp(sprite.getX(), 0, worldWidth - playerWidth));
        sprite.setY(MathUtils.clamp(sprite.getY(), 0, worldHeight - playerHeight));

        if (velocity.len2() > 0) {
            orientation = (float) Math.toDegrees(velocity.angleRad());
        }
        System.out.println(orientation);

        sprite.setRotation(orientation);




    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!isDead()) {

            sprite.draw(spriteBatch);
        }

    }

    public void kill() {
        framesUntilRespawn = 60;
    }
}

