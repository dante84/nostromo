package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class WorldController
        //extends InputAdapter
{

    private static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;
    public Nivel nivel;
    private Game game;

    public WorldController(Game game) {
        this.game = game;
        init();
    }

    private void init() {

        //Gdx.input.setInputProcessor(this);

        cameraHelper = new CameraHelper();
        initNivel();

    }

    private void initNivel () {

        nivel = new Nivel();

    }

    public void update(float deltaTime) {

           if( nivel.vidas == 0 ){
               backToMenu();
           }

           nivel.update(deltaTime);
           cameraHelper.update(deltaTime);

    }

    private void backToMenu () {
// switch to menu screen

        game.setScreen(new MenuScreen(game));

    }

}