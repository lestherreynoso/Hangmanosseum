package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lestherreynoso.screens.TitleScreen;
import com.lestherreynoso.splashes.KpableSplash;

public class Hangmanosseum extends Game {
    public static final String TITLE = "Hangmanosseum";
    public static final String VERSION_NUMBER = "0.0.1";
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final double MIN_SCALE = 0.25;
//    SpriteBatch batch;
//	Texture img;

    @Override
    public void create() {
//        setScreen(new KpableSplash());
        setScreen(new Colosseum());
//        batch = new SpriteBatch();
//        img = new Texture("badlogic.jpg");
    }


    @Override
    public void render() {
        super.render();
//        Gdx.gl.glClearColor(1, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(img, 0, 0);
//        batch.end();
    }
}
