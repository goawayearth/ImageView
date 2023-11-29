package com.example.imageview;

public class Vector2D {

    public float x;
    public float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static double angleBetween(Vector2D v1, Vector2D v2) {
        double angle = Math.atan2(v1.x*v2.y - v1.y*v2.x, v1.x*v2.x + v1.y*v2.y);
        return angle;
    }
}