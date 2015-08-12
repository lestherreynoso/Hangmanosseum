package com.lestherreynoso.hangmanosseum;

import aurelienribon.tweenengine.*;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.paths.CatmullRom;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.lestherreynoso.accessors.SpriteAccessor;
import com.lestherreynoso.splashes.CheeseSplash;

import java.util.Random;

/**
 * Created by Kpable on 5/31/2015.
 */
public class Tosser extends AnimatedSprite {
    private boolean tossing;
    private boolean carrying;
    private TweenManager tweenManager;

    public Tosser(Sprite tosserSprite){
        super(tosserSprite, 4, 1, 0.5f);
        this.tossing = false;
        this.carrying = false;
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

    }

    public void setTossing(boolean tossing) {
        this.tossing = tossing;
    }

    public boolean isTossing() {
        return tossing;
    }

    @Override
    public void draw(Batch batch) {
        if(isTossing() || getCurrentFrame() == null){
            super.draw(batch);
        }else{
            setTossing(false);
            setCarrying(false);
            batch.draw(getCurrentSprite(), this.getX(), this.getY());
        }

        if(Gdx.graphics.getDeltaTime() != 0){
            tweenManager.update(Gdx.graphics.getDeltaTime());
        }
    }

    public boolean isCarrying() {
        return carrying;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }


    public void throwLetter(Letter temp) {
        Random random = new Random();
        setCarrying(true);
        setTossing(true);

//        Vector2[] points = new Vector2[2];
//        points[0] = new Vector2(this.getX() + (this.getSpriteWidth() / 2), this.getY() + this.getSpriteHeight() + 5);
//        points[1] = new Vector2((float)random.nextInt((int)this.getSpriteWidth() + (int)this.getX()), random.nextInt((int)this.getSpriteHeight() + (int)this.getY()));
//        points[2] = new Vector2(random.nextInt(Hangmanosseum.SCREEN_WIDTH), random.nextInt(Hangmanosseum.SCREEN_HEIGHT));
//        final CatmullRomSpline<Vector2> throwingArc = new CatmullRomSpline<Vector2>(points, false);
//
        temp.setPosition(this.getX() + (this.getSpriteWidth() / 2), this.getY() + this.getSpriteHeight() + 5);
        Timeline.createSequence()
                .push(Tween.set(temp, SpriteAccessor.POS).target(temp.getX(), temp.getY()))
                .pushPause(1.5f)
                .push(Tween.to(temp, SpriteAccessor.POS, 2)
                        .target(random.nextInt(Hangmanosseum.SCREEN_WIDTH), random.nextInt(Hangmanosseum.SCREEN_HEIGHT))
                        .ease(Bounce.OUT)
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                        setTossing(false);
//                        setCarrying(false);
                            }
                        }))

                .start(tweenManager);
        tweenManager.update(Float.MIN_VALUE);

    }
}
