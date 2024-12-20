package com.yuntech.GeometryWars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class InputHandler extends InputAdapter {
    public static Vector2 mousePosition = new Vector2();
    private static boolean isAimingWithMouse = false;


    public static void update() {
        mousePosition.set(Gdx.input.getX(), Gdx.input.getY());
        GameRoot.Viewport.unproject(mousePosition);
        System.out.println(mousePosition);
        // If the player pressed one of the arrow keys or is using a gamepad to aim, we want to disable mouse aiming. Otherwise,
        // if the player moves the mouse, enable mouse aiming.
        isAimingWithMouse = true;
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
//            Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            isAimingWithMouse = false;
//        } else if (mousePosition.x != Gdx.input.getX() || mousePosition.y != Gdx.input.getY()) {
//            isAimingWithMouse = true;
//        }
    }

    public static boolean wasKeyPressed(int key) {
        return Gdx.input.isKeyJustPressed(key);
    }

    public static Vector2 getMovementDirection() {
        Vector2 direction = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.A)) direction.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) direction.x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) direction.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) direction.y -= 1;

        if (direction.len2() > 1) direction.nor();

        return direction;
    }

    public static Vector2 getAimDirection() {
        if (isAimingWithMouse) return getMouseAimDirection();

        Vector2 direction = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) direction.x -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) direction.x += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) direction.y += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) direction.y -= 1;

        if (direction.len2() == 0) return new Vector2(0, 0);
        else return direction.nor();
    }

    public static Vector2 getMouseAimDirection() {
//        Vector2 direction = new Vector2(mousePosition).sub(PlayerShip.getInstance().position);
        Vector2 direction = new Vector2(mousePosition.sub(PlayerShip.getInstance().sprite.getX(), PlayerShip.getInstance().sprite.getY()));
//        Vector2 direction = new Vector2(mousePosition);



        if (direction.len2() == 0) return new Vector2(0, 0);
        else return direction.nor();
    }


}

