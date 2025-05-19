package com.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main implements ApplicationListener {
    private SpriteBatch batch;
    private Player player;
    private Joystick joystick;
    private ShapeRenderer shapeRenderer;


    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player(batch);
        joystick = new Joystick();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        joystick.update();
        player.update(joystick.getDirection());

        player.draw();

        // begin y end controlado aquí
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        joystick.draw(shapeRenderer);
        shapeRenderer.end();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
