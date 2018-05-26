package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

public class DesktopLauncher {

	public static void main (String[] arg) {

	       Settings settings = new Settings();
	       settings.maxHeight = 2048;
           settings.maxWidth = 2048;
           settings.duplicatePadding = false;

           try{
               TexturePacker.process(settings,
                                    "raw",
                                    "../android/assets/imagenes",
                                    "pack");
           }catch(Exception e){ e.printStackTrace(); }

		   LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		   new LwjglApplication(new MyGdxGame(), config);

	}

}
