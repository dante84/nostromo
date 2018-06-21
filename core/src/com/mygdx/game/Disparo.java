package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Disparo extends AbstractGameObject {

    private TextureRegion tRegion,regionCargado001,regionCargado002;
    protected boolean activo;

    public Disparo(float px, float py){
           init(px,py);
    }

    public void init(float px, float py){

           tRegion = Assets.instance.disparo.head;
           regionCargado001 = Assets.instance.disparoCargado001.head;
           regionCargado002 = Assets.instance.disparoCargado002.head;
           //dimension.set(1.6f,1.35f);
           velocity.set(25.0f,50.0f);
           activo = false;
           position.set(px + dimension.x, py - 1.25f);
           bounds.set( 0, 0, dimension.x, dimension.y );
           bounds.setPosition(position);

    }

    @Override
    public void update(float deltaTime) {

           position.x += (velocity.x + 1.5f) * deltaTime;
           bounds.setPosition(position);

    }

    @Override
    public void render(SpriteBatch batch) {

        TextureRegion reg = null;
        // Draw image
        reg = tRegion;

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

    public void renderCargado001(SpriteBatch batch){

        TextureRegion reg = regionCargado001;
        // Draw image

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

    public void renderCargado002(SpriteBatch batch){

        TextureRegion reg = regionCargado002;
        // Draw image

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
