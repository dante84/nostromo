package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class Nave extends AbstractGameObject {

       private TextureRegion tRegion;
       private List<Disparo> disparos;

       public Nave(){
              init();
       }

       public void init(){

              dimension.set(1.5f,1.25f);
              tRegion = Assets.instance.nave.head;
              origin.set(dimension.x, dimension.y);
              bounds.set( 0, 0, dimension.x, dimension.y );
              position.set(-1.0f,0);
              disparos = new ArrayList<Disparo>();

       }

       @Override
       public void render(SpriteBatch batch,OrthographicCamera camera) {

           TextureRegion reg = null;
           // Draw image
           reg = tRegion;
           batch.draw(reg.getTexture(),
                   position.x, position.y,
                   origin.x, origin.y,
                   dimension.x, dimension.y,
                   scale.x, scale.y,
                   rotation,
                   reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
                   false, false);
           // Reset color to white
           batch.setColor(1, 1, 1, 1);

           if( Gdx.input.justTouched() ){

               int x = Gdx.input.getX();
               int y = Gdx.input.getY();

               System.out.println(x + " - " + y);

               Vector3 posicion = camera.unproject(new Vector3(x, y, 0));

               if( ( x >= 810 && x <= 900 ) && ( y >= 400 && y <= 460 ) ) {

                   Disparo disparo = new Disparo(position.x + dimension.x, dimension.y + position.y);
                   disparo.render(batch);
                   disparos.add(disparo);

               }

           }else{

                 if( disparos.size() > 0 ) {
                   for( Disparo disparo : disparos ){
                       disparo.render(batch);
                   }
               }

           }

           //System.out.println("render - " + disparos.size());

       }

       @Override
       public void update(float deltaTime,OrthographicCamera camara){

              //position.x += velocity.x * deltaTime;
              //position.y += velocity.y * deltaTime;

              if( Gdx.input.isTouched() ){

                  int x = Gdx.input.getX();
                  int y = Gdx.input.getY();

                  Vector3 posicion = camara.unproject(new Vector3(x,y,0));

                  if( x >= 50 && x <= 100 ){
                      if( y >= 230 ){
                         //position.x -= ( velocity.x + posicion.x ) * deltaTime;
                         position.y += ( velocity.y + posicion.y ) * deltaTime;
                      }
                  }else{
                      //if( y >= )
                      if( x > 100 && x <= 150 ){

                          if( x > 100 && x <= 125 ){
                              position.x -= ( velocity.x + posicion.x ) * deltaTime;
                          }

                          if( x > 125 && x <= 150 ){
                              position.x += ( velocity.x + posicion.x ) * deltaTime;
                          }
                      }
                  }



                  //System.out.println("update - " + disparos.size());

              }

              if( disparos.size() > 0 ) {
                  for( Disparo disparo : disparos ){
                       disparo.update(deltaTime,camara);
                  }
              }

       }

    @Override
    public void render(SpriteBatch batch) {}

}
