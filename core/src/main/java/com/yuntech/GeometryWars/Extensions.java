package com.yuntech.GeometryWars;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Extensions {
    public static float toAngle(Vector2 vector) {
        return (float) Math.atan2(vector.y, vector.x);
    }

    public static Vector2 scaleTo(Vector2 vector, float length) {
        return vector.scl(length / vector.len());
    }

    public static com.badlogic.gdx.math.Vector2 toPoint(Vector2 vector) {
        return new com.badlogic.gdx.math.Vector2((int) vector.x, (int) vector.y);
    }

    public static float nextFloat(Random rand, float minValue, float maxValue) {
        return rand.nextFloat() * (maxValue - minValue) + minValue;
    }

    public static Vector2 nextVector2(Random rand, float minLength, float maxLength) {
        double theta = rand.nextDouble() * 2 * Math.PI;
        float length = nextFloat(rand, minLength, maxLength);
        return new Vector2(length * (float) Math.cos(theta), length * (float) Math.sin(theta));
    }
}

