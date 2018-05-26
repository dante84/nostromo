package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Disparo extends AbstractGameObject {

    private TextureRegion tRegion;
    protected boolean activo;

    public Disparo(){
           init();
    }

    public void init(){

           tRegion = Assets.instance.disparo.head;
           dimension.set(1.5f,1.25f);
           velocity.set(25.0f,50.0f);
           activo = false;

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

        batch.draw(reg.getTexture(), position.x, position.y, origin.x,
                origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                reg.getRegionHeight(), false,
                false);
        // Reset color to white
        batch.setColor(1, 1, 1, 1);

    }

}
