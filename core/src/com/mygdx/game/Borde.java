package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Borde extends AbstractGameObject {

    private TextureRegion tRegion;
    protected Rectangle contorno;

    public Borde(Rectangle r1){

           init(r1);

    }

    public void init(Rectangle rectangle){

        tRegion = Assets.instance.borde.head;
        dimension.set(1.75f,1.5f);
        contorno = rectangle;

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {

        TextureRegion reg = null;
        // Draw image
        reg = tRegion;

        batch.draw(reg.getTexture(),
                contorno.x, contorno.y,
                contorno.x, contorno.y,
                contorno.width, contorno.height,
                scale.x, scale.y, rotation,
                reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                reg.getRegionHeight(), false,
                false);
        // Reset color to white
        batch.setColor(1, 1, 1, 1);


    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camara) {

    }
}
