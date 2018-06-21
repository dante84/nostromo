package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class PantallaPrueba extends AbstractGameScreen {

    public PantallaPrueba(Game game) {
        super(game);
        System.out.println("PantallaPrueba");
    }

    @Override
    public void render(float deltaTime) {

        Gdx.gl.glClearColor(255.0f, 255.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.justTouched())
            game.setScreen(new MenuScreen(game));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }
}
