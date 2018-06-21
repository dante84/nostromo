package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen extends AbstractGameScreen {

    private static final String TAG = MenuScreen.class.getName();

    private Stage stage;
    private Skin skin;
    // menu
    private Image imgBackground;
    private Image imgLogo;
    private Image imgInfo;
    private Image imgCoins;
    private Image imgBunny;
    private Button btnMenuPlay;
    private Button btnMenuOptions;

    private Window winOptions;
    private TextButton btnWinOptSave;
    private TextButton btnWinOptCancel;
    private CheckBox chkSound;
    private Slider sldSound;
    private CheckBox chkMusic;
    private Slider sldMusic;
    //private SelectBox<CharacterSkin> selCharSkin;
    private Image imgCharSkin;
    private CheckBox chkShowFpsCounter;
    // debug
    private final float DEBUG_REBUILD_INTERVAL = 5.0f;
    private boolean debugEnabled = false;
    private float debugRebuildStage;

    public MenuScreen (Game game) {
        super(game);
    }

    @Override
    public void render (float deltaTime) {

        Gdx.gl.glClearColor(1.0f, 2.0f, 5.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (debugEnabled) {
            debugRebuildStage -= deltaTime;
            if (debugRebuildStage <= 0) {
                debugRebuildStage = DEBUG_REBUILD_INTERVAL;
                rebuildStage();
            }
        }

        stage.act(deltaTime);
        stage.draw();
        stage.setDebugAll(true);

    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show () {

        stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH,
                Constants.VIEWPORT_GUI_HEIGHT));

        Gdx.input.setInputProcessor(stage);

        rebuildStage();

    }

    @Override
    public void hide () {
        stage.dispose();
        skin.dispose();
    }

    @Override public void pause () { }

    private void rebuildStage () {

        skin = new Skin(
                Gdx.files.internal(Constants.SKIN_CANYONBUNNY_UI),
                new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
// build all layers
        Table layerBackground = buildBackgroundLayer();
        Table layerObjects = buildObjectsLayer();
        Table layerLogos = buildLogosLayer();
        Table layerControls = buildControlsLayer();
        Table layerOptionsWindow = buildOptionsWindowLayer();

        // assemble stage for menu screen
        stage.clear();

        layerBackground.add(layerControls);
        stage.addActor(layerBackground);

        Stack stack = new Stack();
        stack.setFillParent(true);
        //stage.addActor(stack);

        stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        //stack.add(layerObjects);

        //stack.add(layerLogos);
        //stack.add(layerControls);


        //stage.addActor(layerOptionsWindow);

    }

    private Table buildBackgroundLayer () {

        Table layer = new Table();
        layer.setFillParent(true);
        // + Background
        imgBackground = new Image(skin, "fondo");
        //layer.setTransform(true);
        //layer.setScale(1.0f);
        layer.background(imgBackground.getDrawable());
        layer.columnDefaults(5);

        return layer;

    }

    private Table buildObjectsLayer () {
        Table layer = new Table();
        return layer;
    }

    private Table buildLogosLayer () {
        Table layer = new Table();
        return layer;
    }

    private Table buildControlsLayer () {

        Table layer = new Table();

// + Play Button
        btnMenuPlay = new Button(skin, "play");

        layer.add(btnMenuPlay).width(125).height(45).right().bottom();

        btnMenuPlay.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                onPlayClicked();
            }
        });

        //layer.row();

// + Options Button
        /*btnMenuOptions = new Button(skin, "options");
        layer.add(btnMenuOptions);
        btnMenuOptions.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                onOptionsClicked();
            }
        });*/

        layer.pack();

        if (debugEnabled) layer.debug();


        return layer;

    }

    private void onPlayClicked () {
        game.setScreen(new GameScreen(game));
    }

    private void onOptionsClicked () { }

    private Table buildOptionsWindowLayer () {
        Table layer = new Table();
           return layer;
    }



}
