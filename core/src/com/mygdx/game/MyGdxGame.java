package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.assets.AssetManager;

public class MyGdxGame implements ApplicationListener {

    private static final String TAG = MyGdxGame.class.getName();
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    private boolean paused;

    @Override
    public void create() {

        // Set Libgdx log level to DEBUG
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        Assets.instance.init(new AssetManager());

        // Initialize controller and renderer
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);

        paused = false;

    }

    @Override
    public void render() {

        if (!paused) {
            worldController.update(Gdx.graphics.getDeltaTime(),worldRenderer.camera);
        }
        // Sets the clear screen color to: Cornflower Blue
        Gdx.gl.glClearColor(255.0f, 255.0f, 255.0f, 255.0f);
        // Clears the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Render game world to screen
        worldRenderer.render();

    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void pause() {

        paused = true;

    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }
}