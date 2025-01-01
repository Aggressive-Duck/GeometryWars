package com.yuntech.GeometryWars;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MathUtil {
    public static Vector2 FromPolar(Vector2 vector2, float magnitude, float angle) {
        return new Vector2(vector2).nor().scl(magnitude).rotateRad(angle);

    }

    public static float WrapAngle(float angle) {
        while (angle < -MathUtils.PI) angle += 2 * MathUtils.PI;
        while (angle > MathUtils.PI) angle -= 2 * MathUtils.PI;
        return angle;
    }
}

