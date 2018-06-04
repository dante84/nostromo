package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;
    public Nivel nivel;

    public WorldController() {
        init();
    }

    private void init() {

        Gdx.input.setInputProcessor(this);

        cameraHelper = new CameraHelper();
        initNivel();

    }

    private void initNivel () {

        nivel = new Nivel();

    }

    public void update(float deltaTime,OrthographicCamera camara) {
           nivel.update(deltaTime,camara);
           cameraHelper.update(deltaTime);
    }


}