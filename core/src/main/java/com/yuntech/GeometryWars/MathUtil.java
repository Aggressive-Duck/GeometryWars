package com.yuntech.GeometryWars;

import com.badlogic.gdx.math.Vector2;

public class MathUtil {
    public static Vector2 fromPolar(float angle, float magnitude) {
        return new Vector2(magnitude * (float) Math.cos(angle), magnitude * (float) Math.sin(angle));
    }
}

