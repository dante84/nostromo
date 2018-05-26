package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Nivel {

    public static final String TAG = Nivel.class.getName();

    public Fondo fondo;
    public Nave nave;

    public Nivel() {
        init();
    }

    private void init() {

            fondo = new Fondo();
            nave = new Nave();

    }

    public void render(SpriteBatch batch) {

           fondo.render(batch);
           nave.render(batch);

    }

    public void update (float deltaTime,OrthographicCamera camara) {

           fondo.update(deltaTime,camara);
           nave.update(deltaTime,camara);

    }

}
