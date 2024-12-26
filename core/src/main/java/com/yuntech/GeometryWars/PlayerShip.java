package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;

public class PlayerShip extends Entity {
    private static PlayerShip instance;
    public static PlayerShip getInstance() {
        if (instance == null) instance = new PlayerShip();
        return instance;
    }

    private static final int cooldownFrames = 0;
    private int cooldownRemaining = 0;
    private int framesUntilRespawn = 0;
    private static final float speed = 8f;
    private static final float bulletSpeed =22f;

    private PlayerShip() {
        this.image = Art.player;
//        this.position = new Vector2(GameRoot.Viewport.getWorldWidth() / 2, GameRoot.Viewport.getWorldHeight() / 2);
        this.Velocity = new Vector2(); // Initialize velocity
        this.Radius = 10;
        Sprite = new Sprite(this.image);
        Sprite.setSize(1,1);
        Sprite.setOrigin(Sprite.getWidth() / 2, Sprite.getHeight() / 2);



    }


    public boolean isDead() {
        return framesUntilRespawn > 0;
    }

    @Override
    public void update(float delta) {
        float playerWidth = Sprite.getWidth();
        float playerHeight = Sprite.getHeight();
        float worldWidth = GameRoot.Viewport.getWorldWidth();
        float worldHeight = GameRoot.Viewport.getWorldHeight();

        if (isDead()) {
            framesUntilRespawn--;
            return;
        }


        // 移動邏輯
        Velocity.set(InputHandler.getMovementDirection()).scl(speed);

        Sprite.translate((Velocity.x) * delta, (Velocity.y) * delta);

        // 限制玩家在螢幕邊界內
        Sprite.setX(MathUtils.clamp(Sprite.getX(), 0, worldWidth - playerWidth));
        Sprite.setY(MathUtils.clamp(Sprite.getY(), 0, worldHeight - playerHeight));

        if (Velocity.len2() > 0) {
            Orientation = (float) Math.toDegrees(Velocity.angleRad());
        }

        Sprite.setRotation(Orientation);

        // 瞄準和射擊邏輯
        Vector2 aim = InputHandler.getMouseAimDirection();
//        System.out.println(aim);
        if (aim.len2() > 0 && cooldownRemaining <= 0) {
            cooldownRemaining = cooldownFrames;
            float aimAngle = aim.angleDeg();
            Quaternion aimQuat = new Quaternion().setEulerAnglesRad(0, 0, aimAngle);


            Matrix3 matrix = new Matrix3();

            // Set up the matrix (example values)
//            System.out.println(aimAngle);
            matrix.setToRotation(aimAngle); // Rotate by 45 degrees


            float randomSpread = MathUtils.random(-0.04f, 0.04f) + MathUtils.random(-0.04f, 0.04f);
            Vector2 vel = new Vector2(aim).nor().scl(bulletSpeed).rotateRad(randomSpread);
//            System.out.println(aimAngle);
            //Vector2 vel = MathUtil.fromPolar(aimAngle + randomSpread, bulletSpeed);
//            System.out.println(vel + "shipsdsdsdsd");
//            Vector2 offset = new Vector2(-playerWidth/5f, 1f);
            //do your transformations
            Vector2 offset = new Vector2(playerWidth, 0.6f);
            offset.mul(matrix);
            Vector2 pos = new Vector2(Sprite.getX(), Sprite.getY());
            EntityManager.add(new Bullet(pos.cpy().add(offset), vel));

            offset = new Vector2( playerWidth, 0.1f);
            offset.mul(matrix);
            EntityManager.add(new Bullet(pos.cpy().add(offset), vel));

        }
        Position = new Vector2(Sprite.getX(), Sprite.getY());
        if (cooldownRemaining > 0)
            cooldownRemaining--;


    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (!isDead()) {

            Sprite.draw(spriteBatch);
        }

    }

    public void kill() {
        framesUntilRespawn = 60;
    }
}

