package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Nivel {

    public static final String TAG = Nivel.class.getName();

    private Fondo fondo;
    private Nave nave;
    protected List<EnemigoUno> lEnemigoUno;
    private EnemigoUno enemigoU;
    private float tiempoActual;
    private Random random;
    private boolean banderaModulo;
    private boolean choco;
    protected int vidas;
    protected long puntaje;
    protected int tiempoNivel;
    private int tiempoPasado;

    protected Nivel() {
        init();
    }

    private void init() {

            fondo = new Fondo();
            nave = new Nave();
            lEnemigoUno = new ArrayList<EnemigoUno>(10);
            tiempoActual = 0;
            random = new Random();
            banderaModulo = true;
            enemigoU = new EnemigoUno();
            choco = true;
            vidas = 1;
            puntaje = 000000000L;
            tiempoNivel = 99;
            tiempoPasado = 0;

    }

    public void render(SpriteBatch batch,OrthographicCamera camera) {

           fondo.render(batch);
           nave.render(batch,camera);

           tiempoActual += Gdx.graphics.getDeltaTime();
           int parteEnteraTiempoActual = (int)tiempoActual;
           int modulo = parteEnteraTiempoActual % 2;

           //System.out.println(tiempoPasado + " - " + parteEnteraTiempoActual);

           if( parteEnteraTiempoActual - tiempoPasado == 2 ) {
               tiempoPasado = parteEnteraTiempoActual;
               tiempoNivel--;
           }

           if ( modulo != 0 ){
                banderaModulo = true;
           }

           if( modulo == 0 && banderaModulo ) {

               EnemigoUno eu = new EnemigoUno();
               float vh = ( Constants.VIEWPORT_HEIGHT / 2 ) - 1;
               float y = ( random.nextFloat() * ((vh + 10) - vh)) - vh;

               eu.position.y = y;
               lEnemigoUno.add(eu);
               banderaModulo = false;

           }

           for( Iterator<EnemigoUno> iterator = lEnemigoUno.iterator(); iterator.hasNext(); ){

                EnemigoUno eu = iterator.next();

                boolean colisiono = colisionEnemigoUno(nave.bounds, eu.bounds);

                if( colisiono ){

                    Gdx.app.error("colisiono = " , colisiono + " | ");
                    Gdx.app.error("enemigo" ,eu.bounds.x + " | " + eu.bounds.y + " | " + eu.bounds.height + " | " + eu.bounds.width);
                    AudioManager.instance.play(Assets.instance.sonidos.explosion);
                    iterator.remove();
                    nave = new Nave();
                    vidas--;
                    continue;

                }

                eu.render(batch,camera);

           }


           List<Disparo> shoots = nave.disparos;

           //if( choco ) { enemigoU.render(batch,camera); }

           //if ( shoots.size() > 0 ){

                for( Iterator<EnemigoUno> iterator = lEnemigoUno.iterator(); iterator.hasNext(); ){

                     EnemigoUno eu = iterator.next();

                     for( Iterator<Disparo> iteratorD = shoots.iterator(); iteratorD.hasNext(); ){

                          Disparo disparo = iteratorD.next();
                          boolean colisiono = colisionEnemigoUno(disparo.bounds, eu.bounds);

                          if( colisiono ){

                              /*Gdx.app.error("colisiono = " , colisiono + " | ");
                              Gdx.app.error("disparo" ,disparo.bounds.x + " | " + disparo.bounds.y + " | " + disparo.bounds.height + " | " + disparo.bounds.width);
                              Gdx.app.error("enemigo" ,eu.bounds.x + " | " + eu.bounds.y + " | " + eu.bounds.height + " | " + eu.bounds.width);*/


                              eu.bounds = new Rectangle();
                              iterator.remove();
                              iteratorD.remove();
                              puntaje += 100L;
                              AudioManager.instance.play(Assets.instance.sonidos.explosion);
                              break;

                              //choco = false;

                          }

                     }

                }

           //}

           /*boolean colisiono = colisionEnemigoUno(nave.bounds, enemigoU.bounds);
           System.out.print(" | " + colisiono + " | ");

           if( colisiono ){
               choco = false;
           }

           if( choco )
               enemigoU.render(batch,camera);*/

    }

    public void update (float deltaTime) {

           if( !(tiempoNivel < 0) )
               fondo.update(deltaTime);

           nave.update(deltaTime);

           /*if( choco )
             enemigoU.update(deltaTime,camara);
           */

           if( !lEnemigoUno.isEmpty() ){

               //System.out.println("lEnemigoUno size = " + lEnemigoUno.size());

               for( Iterator<EnemigoUno> iterator = lEnemigoUno.iterator(); iterator.hasNext(); ){

                    EnemigoUno eu = iterator.next();

                    if( eu.position.x < -eu.dimension.x - Constants.VIEWPORT_WIDTH ){
                        iterator.remove();
                        continue;
                    }

                     eu.update(deltaTime);

               }

           }

    }

    public boolean colisionEnemigoUno(Rectangle jugador,Rectangle enemigo){

        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();

        r1.set(jugador);
        r2.set(enemigo);

        if( r2.overlaps(r1) ) return true;

        return false;

    }

}
