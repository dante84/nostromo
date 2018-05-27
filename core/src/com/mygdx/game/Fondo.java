package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Fondo extends AbstractGameObject {

       private TextureRegion tRegion;

       public Fondo(){ initFondo(); }

       public void initFondo(){

           dimension.set(28.0f,16.0f);
           tRegion = Assets.instance.fondo.head;
           velocity.set(0.25f, 0);
           position.set(-14.0f, -8.0f);
           //origin.set(dimension.x, dimension.y);
           //bounds.set( 0, 0, dimension.x, dimension.y );

       }

       @Override
       public void update(float deltaTime, OrthographicCamera camara) {

              position.x -= velocity.x * deltaTime;
              position.y += velocity.y * deltaTime;


       }

       @Override
       public void render(SpriteBatch batch) {

           TextureRegion reg = null;

           // Draw image
           reg = tRegion;

               batch.draw(
                       reg.getTexture()
                       , position.x, position.y,
                       origin.x, origin.y,
                       dimension.x * 3, dimension.y * 3,
                       scale.x, scale.y, rotation,
                       reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
                       false, false
               );
               // Reset color to white
               batch.setColor(1, 1, 1, 1);


       }

       @Override
       public void render(SpriteBatch batch,OrthographicCamera camara) {}

}
