package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Kpable on 5/31/2015.
 */
public class AnimatedFieldSprite extends AnimatedSprite {
    public AnimatedFieldSprite(Sprite playerSprite, int frameCol, int frameRow, float frameDuration) {
        super(playerSprite, frameCol, frameRow, frameDuration);
    }

    @Override
    public void draw(Batch batch) {
        //TODO Scale sprite before drawing
//        Sprite fieldSprite = new Sprite(this.getTexture());
        double scaledHeight = (Hangmanosseum.SCREEN_HEIGHT + (Hangmanosseum.SCREEN_HEIGHT * Hangmanosseum.MIN_SCALE));
        double scale = (scaledHeight - this.getY()) / scaledHeight;
//        fieldSprite.setSize(fieldSprite.getWidth() * ((float)scale), fieldSprite.getHeight() * ((float)scale) );
////        Gdx.app.log("AnimatedFieldSprite", "");
//        setSprite(fieldSprite);
        setScale(scale);
        super.draw(batch);
    }
}
