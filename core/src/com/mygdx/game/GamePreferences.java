package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
public class GamePreferences {
    public static final String TAG =
            GamePreferences.class.getName();
    public static final GamePreferences instance =
            new GamePreferences();
    public boolean sound;
    public boolean music;
    public float volSound;
    public float volMusic;
    public int charSkin;
    public boolean showFpsCounter;
    private Preferences prefs;
    // singleton: prevent instantiation from other classes
    private GamePreferences () {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }
    public void load () { }
    public void save () { }
}
