package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Joystick {
    private Vector2 center;
    private Vector2 knob;
    private float outerRadius = 140; // antes 80
    private float innerRadius = 50;  // antes 35
    private boolean touched;

    public Joystick() {
        center = new Vector2(150, 150); // esquina inferior izquierda
        knob = new Vector2(center);
    }

    public void update() {
        touched = false;
        for (int i = 0; i < 10; i++) {
            if (Gdx.input.isTouched(i)) {
                float tx = Gdx.input.getX(i);
                float ty = Gdx.graphics.getHeight() - Gdx.input.getY(i);
                if (new Vector2(tx, ty).dst(center) < outerRadius * 1.5f) {
                    touched = true;
                    Vector2 dir = new Vector2(tx, ty).sub(center);
                    if (dir.len() > outerRadius) dir.nor().scl(outerRadius);
                    knob.set(center).add(dir);
                    return;
                }
            }
        }
        knob.set(center);
    }

    public Vector2 getDirection() {
        return new Vector2(knob).sub(center).scl(1 / outerRadius);
    }


    // Joystick.java
    public void draw(ShapeRenderer renderer) {
        renderer.setColor(0.3f, 0.3f, 0.3f, 0.5f);
        renderer.circle(center.x, center.y, outerRadius);
        renderer.setColor(1, 1, 1, 0.8f);
        renderer.circle(knob.x, knob.y, innerRadius);
    }

}
