package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;

public class PlayerShip extends Entity {
    private static PlayerShip instance;
    public static PlayerShip getInstance() {
        if (instance == null) instance = new PlayerShip();
        return instance;
    }

    private static final int cooldownFrames = 6;
    private int cooldownRemaining = 0;
    private int framesUntilRespawn = 0;
    private static final float speed = 8;
    private static final float bulletSpeed = 11f;

    private PlayerShip() {
        this.image = Art.player;
        this.position = new Vector2(GameRoot.ScreenWidth / 2, GameRoot.ScreenHeight / 2);
        this.velocity = new Vector2(); // Initialize velocity
        this.radius = 10;
    }

    public boolean isDead() {
        return framesUntilRespawn > 0;
    }

    @Override
    public void update(float delta) {
        if (isDead()) {
            framesUntilRespawn--;
            return;
        }

        Vector2 aim = InputHandler.getAimDirection();
        if (aim.len2() > 0 && cooldownRemaining <= 0) {
            cooldownRemaining = cooldownFrames;
            float aimAngle = aim.angleRad();
            Quaternion aimQuat = new Quaternion().setEulerAnglesRad(0, 0, aimAngle);

            float randomSpread = MathUtils.random(-0.04f, 0.04f) + MathUtils.random(-0.04f, 0.04f);
            Vector2 vel = new Vector2(bulletSpeed, 0).rotateRad(aimAngle + randomSpread);

            Vector2 offset = new Vector2(35, -8).rotateRad(aimAngle);
            EntityManager.add(new Bullet(position.cpy().add(offset), vel));

            offset.set(35, 8).rotateRad(aimAngle);
            EntityManager.add(new Bullet(position.cpy().add(offset), vel));

        }

        if (cooldownRemaining > 0) cooldownRemaining--;

        velocity.set(InputHandler.getMovementDirection()).scl(speed);
        position.add(velocity);
        position.clamp(GameRoot.ScreenWidth, GameRoot.ScreenHeight);

        if (velocity.len2() > 0) orientation = velocity.angleRad();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!isDead()) super.draw(spriteBatch);
    }

    public void kill() {
        framesUntilRespawn = 60;
    }
}

