package com.lestherreynoso.hangmanosseum;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lestherreynoso.accessors.SpriteAccessor;

/**
 * Created by Kpable on 3/24/2015.
 */
public class LetterPedestal extends Sprite {
    private String letterValue;
    private Sprite correctLetter;
    private boolean risen;
    private TweenManager tweenManager;


    public LetterPedestal(Sprite letterPedestal, String letterValue){
        this.set(letterPedestal);
        this.letterValue = letterValue;
        this.risen = false;

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
    }

    public String getLetterValue() {
        return letterValue;
    }

    public void rise(Letter carryingLetter) {
        //TODO Animate rising
        //TODO check if the letter is already correct and placed so it doesnt create extra sprites over each other
        Gdx.app.log("LetterPedestal", "rising");
        correctLetter = new Sprite(carryingLetter);
        correctLetter.setPosition(getX()+ correctLetter.getWidth() / 2, getHeight() + 5);
        setRisen(true);

    }

    @Override
    public void draw(Batch batch) {
        if(correctLetter != null){
            correctLetter.draw(batch);
        }
        super.draw(batch);
        if(Gdx.graphics.getDeltaTime() != 0){
            tweenManager.update(Gdx.graphics.getDeltaTime());
        }
    }

    public boolean isRisen() {
        return risen;
    }

    public void setRisen(boolean risen) {
        this.risen = risen;
    }

    public void loadAnimation() {
        Tween.set(this, SpriteAccessor.Y).target(0 - this.getHeight()).start(tweenManager);
        Tween.to(this, SpriteAccessor.Y, 2).target(0)
                .start(tweenManager);
        tweenManager.update(Float.MIN_VALUE);
    }
}
