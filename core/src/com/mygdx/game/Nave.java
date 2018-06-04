package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Nave extends AbstractGameObject {

       private TextureRegion tRegion;
       private List<Disparo> disparos;
       private List<Integer> punteros;
       private Map<Integer, Disparo> shoots;
       private static Disparo shoot;
       private ShapeRenderer sr;
       //Borde visible para propositos de pruebas de colisiones y debugeo
       private Borde borde;

       public Nave(){
              init();
       }

       public void init(){

              dimension.set(1.5f,1.25f);
              tRegion = Assets.instance.nave.head;
              origin.set(dimension.x, dimension.y);
              bounds.set( 0, 0, dimension.x, dimension.y );
              position.set(-12.5f,0);
              bounds.setPosition(position.x, position.y);
              disparos = new ArrayList<Disparo>();
              shoots = new HashMap<Integer,Disparo>();
              punteros = new ArrayList<Integer>();
              velocity.set(5,5);
              borde = new Borde(bounds);

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

           int pointer = 0;

           if( Gdx.input.isTouched() ){

               int x = Gdx.input.getX();
               int y = Gdx.input.getY();

               System.out.println(x + " - " + y);

               if( ( x >= 810 && x <= 900 ) && ( y >= 400 && y <= 460 ) ) {

                   Disparo disparo = new Disparo(position.x + dimension.x, dimension.y + position.y);
                   disparo.render(batch);
                   disparos.add(disparo);
                   //Integer puntero = new Integer(pointer);
                   //shoots.put( puntero, disparo);
                   //punteros.add( puntero );

               }

               /*Iterator it = shoots.entrySet().iterator();
           int i = 0;
           while ( it.hasNext() ){

                   Map.Entry entry = (Map.Entry)it.next();

                   //if( entry.getKey() == punteros.get(i) ){
                   ((Disparo)(entry.getValue())).render(batch);
                       //}

                   i++;*/

           }



           if( disparos.size() > 0 ) {

               for( Disparo disparo : disparos ){
                    disparo.position.x += (disparo.velocity.x * Gdx.graphics.getDeltaTime()) + 0.5f;
                    disparo.render(batch);
               }

           }





           //System.out.println("render - " + disparos.size());

           borde.render(batch);

       }

       @Override
       public void update(float deltaTime,OrthographicCamera camara){

              if( Gdx.input.isTouched() ) {

                  int x = Gdx.input.getX();
                  int y = Gdx.input.getY();

                  Vector3 posicion = camara.unproject(new Vector3(x, y, 0));

                  if ( x >= 30 && x <= 200 ) {

                      if ( y <= 520 && y >= 490 ) {
                          //position.x -= ( velocity.x + posicion.x ) * deltaTime;
                          //position.y += (velocity.y + posicion.y) * deltaTime;
                          position.y -= velocity.y * deltaTime;
                          bounds.y -= position.y;

                      }

                      if ( y < 490 && y >= 430 ) {

                          if ( x <= 120 ) {
                              //position.x += (velocity.x + posicion.x) * deltaTime;
                              position.x -= velocity.x * deltaTime;
                              bounds.x -= position.x;
                          }

                          if ( x > 120 ) {
                              //position.x -= (velocity.x + posicion.x) * deltaTime;
                              position.x += velocity.x * deltaTime;
                              bounds.x += position.x;
                          }

                      }

                      if ( y < 430 && y >= 360 ) {
                          //position.y -= ( velocity.y + posicion.y - 5.0f ) * deltaTime;
                          position.y += ( velocity.y + 5.0f ) * deltaTime;
                          bounds.y += position.y;

                      }

                  }

              }


              if( position.x < -Constants.VIEWPORT_WIDTH + 2.5f ){ position.x = -12.5f; }

              if( position.x >= Constants.VIEWPORT_WIDTH - 3.5f ){ position.x = 11.5f; }

              if( position.y < ( Constants.VIEWPORT_HEIGHT / -2 ) ){ position.y = -7.5f; }

              if( position.y >= ( ( Constants.VIEWPORT_HEIGHT / 2 ) - dimension.y ) ){
                  position.y = ( Constants.VIEWPORT_HEIGHT / 2 ) - dimension.y;
              }

              borde.contorno.setPosition(position);

              /*if ( disparos.size() > 0 ) {
                   for ( Disparo disparo : disparos ) {
                         disparo.update(deltaTime, camara);
                   }
              }*/


           /*Iterator it = shoots.entrySet().iterator();
           int i = 0;
           while ( it.hasNext() ){

               Map.Entry entry = (Map.Entry)it.next();

               //if( entry.getKey() == punteros.get(i) ){
                   ((Disparo)(entry.getValue())).update(deltaTime,camara);
               //}

               i++;

           }
*/
       }

    @Override
    public void render(SpriteBatch batch) {}

    public void dibujaRectangulo(float x,float y,float ancho, float alto){

        sr.begin(ShapeRenderer.ShapeType.Line);
           sr.setColor(Color.GREEN);
           sr.rect(x,y,ancho,alto);
        sr.end();

    }

}
