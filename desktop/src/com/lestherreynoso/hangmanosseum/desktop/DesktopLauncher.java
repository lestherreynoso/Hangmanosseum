package com.lestherreynoso.hangmanosseum.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lestherreynoso.hangmanosseum.Hangmanosseum;

public class DesktopLauncher {

    public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Hangmanosseum.TITLE + " " + Hangmanosseum.VERSION_NUMBER;
        config.width = 1280;
        config.height = 720;
		new LwjglApplication(new Hangmanosseum(), config);
	}
}
