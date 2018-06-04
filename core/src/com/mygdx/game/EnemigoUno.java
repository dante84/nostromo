package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemigoUno extends AbstractGameObject{

    private TextureRegion tRegion;
    //Borde visible para propositos de pruebas de colisiones y debugeo
    private Borde borde;

    public EnemigoUno(){ init(); }

    public void init(){

        dimension.set(1.5f,1.25f);
        tRegion = Assets.instance.enemigoUno.head;
        origin.set(dimension.x, dimension.y);
        bounds.set( 0, 0, dimension.x, dimension.y );
        velocity.set(1,1);
        position.set(Constants.VIEWPORT_WIDTH,0);
        bounds.setPosition(position);
        borde = new Borde(bounds);

    }

    @Override
    public void update(float deltaTime, OrthographicCamera camara) {

           position.x -= velocity.x * deltaTime;
           bounds.x += -1 * (position.x / 10);
           borde.contorno.setPosition(position);
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camara) {

        TextureRegion reg = null;
        // Draw image
        reg = tRegion;
        batch.draw(reg.getTexture(),
                position.x, position.y,
                //Constants.VIEWPORT_WIDTH,position.y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
                true, false);
        // Reset color to white
        batch.setColor(1, 1, 1, 1);

        borde.render(batch);

    }
}
