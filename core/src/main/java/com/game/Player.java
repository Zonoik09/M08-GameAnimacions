package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Animation<TextureRegion> downAnimation, leftAnimation, rightAnimation, upAnimation;
    private SpriteBatch batch;
    private float stateTime;
    private Texture spriteSheet;
    private String direction;
    private static final int SPRITE_SIZE = 72;

    public Player(SpriteBatch batch) {
        this.batch = batch;
        this.position = new Vector2(500, 500);
        this.stateTime = 0;
        this.direction = "down";

        // Cargar la hoja de sprites
        spriteSheet = new Texture("Char.png");

        // Cortar la hoja de sprites en regiones (4x4, cada sprite es de 48x48)
        TextureRegion[][] frames = TextureRegion.split(spriteSheet, SPRITE_SIZE, SPRITE_SIZE);

        // Crear las animaciones para cada dirección (4 direcciones, 4 sprites por dirección)
        // La hoja de sprites tiene 4 filas (dirección) y 4 columnas (animación)
        downAnimation = new Animation<>(0.1f, frames[0][0], frames[0][1], frames[0][2], frames[0][3]);
        leftAnimation = new Animation<>(0.1f, frames[1][0], frames[1][1], frames[1][2], frames[1][3]);
        rightAnimation = new Animation<>(0.1f, frames[2][0], frames[2][1], frames[2][2], frames[2][3]);
        upAnimation = new Animation<>(0.1f, frames[3][0], frames[3][1], frames[3][2], frames[3][3]);
    }

    // Actualizar la posición y la animación
    public void update(Vector2 directionInput) {
        boolean isMoving = false;

        if (directionInput.len() > 0.1f) {
            position.add(directionInput.scl(2f)); // Escala la velocidad
            isMoving = true;

            float angle = directionInput.angleDeg();

            if (angle > 45 && angle <= 135) direction = "up";
            else if (angle > 135 && angle <= 225) direction = "left";
            else if (angle > 225 && angle <= 315) direction = "down";
            else direction = "right";
        }

        if (isMoving) {
            stateTime += 0.008f;
        }
    }


    // Dibujar la animación
    public void draw() {
        TextureRegion currentFrame = getCurrentFrame();
        batch.begin();
        float scale = 2f; // o el tamaño que prefieras
        batch.draw(currentFrame, position.x, position.y, SPRITE_SIZE * scale, SPRITE_SIZE * scale);
        batch.end();
    }

    // Obtener la animación actual dependiendo de la dirección
    private TextureRegion getCurrentFrame() {
        switch (direction) {
            case "down":
                return downAnimation.getKeyFrame(stateTime, true);
            case "left":
                return leftAnimation.getKeyFrame(stateTime, true);
            case "right":
                return rightAnimation.getKeyFrame(stateTime, true);
            case "up":
                return upAnimation.getKeyFrame(stateTime, true);
            default:
                return downAnimation.getKeyFrame(stateTime, true); // Dirección por defecto
        }
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
