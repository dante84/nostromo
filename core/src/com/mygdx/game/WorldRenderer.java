package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class WorldRenderer implements Disposable {

    public  OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    private OrthographicCamera cameraGUI;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {

        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();

        cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        cameraGUI.position.set(0, 0, 0);
        cameraGUI.setToOrtho(true); // flip y-axis
        cameraGUI.update();

    }

    public void render() {

           renderWorld(batch);
           renderGui(batch);

    }

    private void renderWorld (SpriteBatch batch) {
        worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
              worldController.nivel.render(batch);
        batch.end();
    }

    private void renderPad(SpriteBatch batch) {

        float x = -15;
        float y = -15;

        batch.draw(Assets.instance.pad.head,
                x, y,
                50, 230,
                150, 150,
                1.0f, -1.0f,
                0);

    }

    private void renderBotonVerde(SpriteBatch batch) {

        float x = -15;
        float y = -15;

        batch.draw(Assets.instance.botonVerde.head,
                   x, y,
                1150, 325,
                150, 150,
                0.35f, -0.35f,
                0);

    }

    private void renderGuiFpsCounter (SpriteBatch batch) {
        float x = cameraGUI.viewportWidth - 55;
        float y = cameraGUI.viewportHeight - 15;
        int fps = Gdx.graphics.getFramesPerSecond();
        BitmapFont fpsFont = Assets.instance.fonts.defaultNormal;
        if (fps >= 45) {
// 45 or more FPS show up in green
            fpsFont.setColor(0, 1, 0, 1);
        } else if (fps >= 30) {
// 30 or more FPS show up in yellow
            fpsFont.setColor(1, 1, 0, 1);
        } else {
// less than 30 FPS show up in red
            fpsFont.setColor(1, 0, 0, 1);
        }
        fpsFont.draw(batch, "FPS: " + fps, x, y);
        fpsFont.setColor(1, 1, 1, 1); // white
    }

    private void renderGui (SpriteBatch batch) {

        batch.setProjectionMatrix(cameraGUI.combined);
        batch.begin();

        renderPad(batch);
        renderBotonVerde(batch);
        renderGuiFpsCounter(batch);

        batch.end();

    }


    public void resize(int width, int height) {

        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();

        cameraGUI.viewportHeight = Constants.VIEWPORT_GUI_HEIGHT;
        cameraGUI.viewportWidth = (Constants.VIEWPORT_GUI_HEIGHT
                / (float)height) * (float)width;
        cameraGUI.position.set(cameraGUI.viewportWidth / 2,
                cameraGUI.viewportHeight / 2, 0);
        cameraGUI.update();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}