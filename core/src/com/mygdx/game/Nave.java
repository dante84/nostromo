package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Nave extends AbstractGameObject implements InputProcessor{

       private TextureRegion tRegion;
       protected List<Disparo> disparos;
       private List<Integer> punteros;
       private Map<Integer, Disparo> shoots;
       private static Disparo shoot;
       private float tiempoActual,tiempoPasado;
       private int punteroCargado,x1,y1;

       //Para propositos de pruebas de colisiones y debugeo
       private Borde borde;

       public Nave(){
              init();
       }

       public void init(){

              Gdx.input.setInputProcessor(this);
              //dimension.set(1.5f,1.25f);
              tRegion = Assets.instance.nave.head;
              origin.set(dimension.x, dimension.y);
              bounds.set( 0, 0, dimension.x, dimension.y );
              position.set(-12.5f,0);
              bounds.setPosition(position.x, position.y);
              disparos = new ArrayList<Disparo>();
              shoots = new HashMap<Integer,Disparo>();
              punteros = new ArrayList<Integer>();
              velocity.set(10,7);
              borde = new Borde(bounds);
              shoot = new Disparo(position.x + dimension.x, dimension.y + position.y);
              tiempoActual = 0;
              tiempoPasado = 0;
              x1 = y1 = punteroCargado = -1;


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
                   true, false);

           // Reset color to white
           batch.setColor(1, 1, 1, 1);

           for( int i = 0; i < 5; i++ ){

                if( Gdx.input.isTouched(i) ){

                    int x = Gdx.input.getX(i);
                    int y = Gdx.input.getY(i);
                    //System.out.println(x + " - " + y);

                    if( ( x >= 810 && x <= 900 ) && ( y >= 400 && y <= 460 ) ) {

                          tiempoActual += Gdx.graphics.getDeltaTime();
                          //System.out.println(tiempoPasado + " - " + tiempoActual + " = " + (tiempoActual - tiempoPasado) );


                        if( tiempoActual - tiempoPasado >= 0.3f ) {
                            tiempoPasado = tiempoActual;
                            shoot.renderCargado001(batch);
                            punteroCargado = i;
                        }else{ shoot.renderCargado002(batch); }

                        x1 = x;
                        y1 = y;

                    }

                 }

           }

           if( Gdx.input.justTouched() ){

               int x = Gdx.input.getX();
               int y = Gdx.input.getY();

               //System.out.println(x + " - " + y);

               if( ( x >= 810 && x <= 900 ) && ( y >= 400 && y <= 460 ) ) {

                   Disparo disparo = new Disparo(position.x , dimension.y + position.y);
                   disparos.add(disparo);
                   AudioManager.instance.play(Assets.instance.sonidos.disparo);

               }

           }

           for( Disparo disparo : disparos ) {

                disparo.render(batch);

           }

           /*if( !disparos.isEmpty() ) {

               //System.out.println("disparos.size = " + disparos.size());

               for (Iterator<Disparo> iterator = disparos.iterator(); iterator.hasNext(); ){

                   Disparo d = iterator.next();
                   if( d.position.x > Constants.VIEWPORT_WIDTH + d.dimension.x ){
                       iterator.remove();
                       continue;
                   }

                   d.render(batch);

               }

           }*/

           //Borde para colisiones
           //borde.render(batch);

       }

       @Override
       public void update(float deltaTime){

              for( int i = 0; i < 5; i++ ) {

                  if ( Gdx.input.isTouched(i) ) {

                      int x = Gdx.input.getX(i);
                      int y = Gdx.input.getY(i);

                      if (x >= 30 && x <= 200) {

                          if (y <= 520 && y >= 490) {
                              //position.x -= ( velocity.x + posicion.x ) * deltaTime;
                              //position.y += (velocity.y + posicion.y) * deltaTime;
                              position.y -= velocity.y * deltaTime;
                              bounds.y -= position.y;

                          }

                          if (y < 490 && y >= 430) {

                              if (x <= 120) {
                                  //position.x += (velocity.x + posicion.x) * deltaTime;
                                  position.x -= velocity.x * deltaTime;
                                  bounds.x -= position.x;
                              }

                              if (x > 120) {
                                  //position.x -= (velocity.x + posicion.x) * deltaTime;
                                  position.x += velocity.x * deltaTime;
                                  bounds.x += position.x;
                              }

                          }

                          if (y < 430 && y >= 360) {
                              //position.y -= ( velocity.y + posicion.y - 5.0f ) * deltaTime;
                              position.y += (velocity.y + 5.0f) * deltaTime;
                              bounds.y += position.y;

                          }

                      }

                  }



              }

              if( position.x < -Constants.VIEWPORT_WIDTH + 2.5f ){ position.x = -12.5f; }

              if( position.x >= Constants.VIEWPORT_WIDTH - 3.5f ){ position.x = 11.5f; }

              if( position.y < ( Constants.VIEWPORT_HEIGHT / -2 ) ){ position.y = -7.5f; }

              if( position.y >= ( ( Constants.VIEWPORT_HEIGHT / 2 ) - dimension.y ) ){
                  position.y = ( Constants.VIEWPORT_HEIGHT / 2 ) - dimension.y;
              }

              //Collision box
              //borde.contorno.setPosition(position);
              bounds.setPosition(position);

              if( !disparos.isEmpty() ) {

                  //System.out.println("disparos.size = " + disparos.size());

                  for (Iterator<Disparo> iterator = disparos.iterator(); iterator.hasNext(); ){

                       Disparo d = iterator.next();
                       if( d.position.x > Constants.VIEWPORT_WIDTH + d.dimension.x ){
                           iterator.remove();
                           continue;
                       }

                       d.update(deltaTime);

                  }

              }


              //System.out.println(x1 + " - " + y1 + " - " + punteroCargado);

              //if( !touchUp(x1,y1,punteroCargado, Input.Buttons.LEFT) ){
              //    System.out.println("ia.touchUp");
                  shoot.update(deltaTime);
              //}



       }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
