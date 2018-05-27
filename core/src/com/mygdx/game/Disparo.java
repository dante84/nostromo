package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Disparo extends AbstractGameObject {

    private TextureRegion tRegion;
    protected boolean activo;

    public Disparo(float px, float py){
           init(px,py);
    }

    public void init(float px, float py){

           tRegion = Assets.instance.disparo.head;
           dimension.set(1.5f,1.25f);
           velocity.set(25.0f,50.0f);
           activo = false;
           position.set(px, py - 1.25f);

           //System.out.println("init = " + position.x + " - " + position.y);

    }

    @Override
    public void update(float deltaTime, OrthographicCamera camara) {

           position.x += velocity.x * deltaTime;

    }

    @Override
    public void render(SpriteBatch batch) {

        TextureRegion reg = null;
        // Draw image
        reg = tRegion;

        //System.out.println("render = " + position.x + " - " + position.y);

        batch.draw(reg.getTexture(),
                position.x, position.y,
                //this.posicionX, this.posicionY - 1.0f,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y, rotation,
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                reg.getRegionHeight(), false,
                false);
        // Reset color to white
        batch.setColor(1, 1, 1, 1);

    }

    @Override
    public void render(SpriteBatch batch,OrthographicCamera camara) {}

}
