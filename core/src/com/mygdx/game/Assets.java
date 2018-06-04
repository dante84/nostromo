package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class Assets implements Disposable,AssetErrorListener{

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;
    public AssetFonts fonts;

    public AssetNave nave;
    public AssetEnemigoUno enemigoUno;
    public AssetFondo fondo;
    public AssetBotonVerde botonVerde;
    public AssetDisparo disparo;
    public AssetPad pad;
    public AssetBorde borde;

    // singleton: prevent instantiation from other classes
    private Assets () {}

    public void init (AssetManager assetManager) {
        this.assetManager = assetManager;
// set asset manager error handler
        assetManager.setErrorListener(this);
// load texture atlas
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS,
                TextureAtlas.class);
// start loading assets and wait until finished
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: "
                + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        TextureAtlas atlas =
                assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
// enable texture filtering for pixel smoothing



        for (Texture t : atlas.getTextures()) {
            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }

        // create game resource objects
        fondo = new AssetFondo(atlas);
        nave  = new AssetNave(atlas);
        enemigoUno = new AssetEnemigoUno(atlas);
        botonVerde = new AssetBotonVerde(atlas);
        fonts = new AssetFonts();
        disparo = new AssetDisparo(atlas);
        pad = new AssetPad(atlas);
        borde = new AssetBorde(atlas);

    }
    @Override
    public void dispose () {

           assetManager.dispose();
           fonts.defaultSmall.dispose();
           fonts.defaultNormal.dispose();
           fonts.defaultBig.dispose();

    }

    public void error (String filename, Class type,
                       Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '"
                + filename + "'", (Exception)throwable);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" +
                asset.fileName + "'", (Exception)throwable);
    }

    public class AssetFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;
        public AssetFonts () {
// create three fonts using Libgdx's 15px bitmap font
            defaultSmall = new BitmapFont(
                    Gdx.files.internal(Constants.FUENTE), true);
            defaultNormal = new BitmapFont(
                    Gdx.files.internal(Constants.FUENTE), true);
            defaultBig = new BitmapFont(
                    Gdx.files.internal(Constants.FUENTE), true);
// set font sizes

// enable linear texture filtering for smooth fonts
            defaultSmall.getRegion().getTexture().setFilter(
                    TextureFilter.Linear, TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(
                    TextureFilter.Linear, TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(
                    TextureFilter.Linear, TextureFilter.Linear);
        }
    }

    public class AssetNave {
        public final AtlasRegion head;
        public AssetNave (TextureAtlas atlas) {
            head = atlas.findRegion("nave");
        }
    }

    public class AssetEnemigoUno {
        public final AtlasRegion head;
        public AssetEnemigoUno (TextureAtlas atlas) {
            head = atlas.findRegion("enemigo");
        }
    }

    public class AssetPad {
        public final AtlasRegion head;
        public AssetPad (TextureAtlas atlas) {
            head = atlas.findRegion("pad");
        }
    }

    public class AssetFondo {
        public final AtlasRegion head;
        public AssetFondo (TextureAtlas atlas) {

               head = atlas.findRegion("fondo");

        }
    }

    public class AssetBotonVerde {
        public final AtlasRegion head;
        public AssetBotonVerde (TextureAtlas atlas) {

            head = atlas.findRegion("bverde");

        }
    }

    public class AssetDisparo {
        public final AtlasRegion head;
        public AssetDisparo (TextureAtlas atlas) {

            head = atlas.findRegion("disparo");

        }
    }

    public class AssetBorde {
        public final AtlasRegion head;
        public AssetBorde (TextureAtlas atlas) {

            head = atlas.findRegion("borde");

        }
    }


}
