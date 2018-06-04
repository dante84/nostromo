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
    private List<EnemigoUno> lEnemigoUno;
    private EnemigoUno enemigoU;
    private float tiempoActual;
    private Random random;
    private boolean banderaModulo;
    private boolean choco;

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

    }

    public void render(SpriteBatch batch,OrthographicCamera camera) {

           fondo.render(batch);
           nave.render(batch,camera);

           tiempoActual += Gdx.graphics.getDeltaTime();
           int parteEnteraTiempoActual = (int)tiempoActual;
           int modulo = parteEnteraTiempoActual % 5;

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
                //System.out.print(" - " + colisiono + " - ");
                if( colisiono ){
                    iterator.remove();
                    nave = new Nave();
                    continue;
                }

                eu.render(batch,camera);

           }

           /*boolean colisiono = colisionEnemigoUno(nave.bounds, enemigoU.bounds);
           System.out.print(" | " + colisiono + " | ");

           if( colisiono ){
               choco = false;
           }

           if( choco )
               enemigoU.render(batch,camera);*/

    }

    public void update (float deltaTime,OrthographicCamera camara) {

           fondo.update(deltaTime,camara);
           nave.update(deltaTime,camara);

           //if( choco )
             //  enemigoU.update(deltaTime,camara);

           //System.out.println(lEnemigoUno.size());

           for( int i = 0; i < lEnemigoUno.size(); i++ ){

               EnemigoUno eu = lEnemigoUno.get(i);
               eu.update(deltaTime,camara);

           }


    }

    public boolean colisionEnemigoUno(Rectangle jugador,Rectangle enemigo){

        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();

        //System.out.println(jugador.x + " | " + jugador.y + " | " + jugador.height + " | " + jugador.width);
        //System.out.println(enemigo.x + " | " + enemigo.y + " | " + enemigo.height + " | " + enemigo.width);

        r1.set(jugador);
        r2.set(enemigo);

        if( r2.overlaps(r1) ) return true;

        return false;

    }

}
