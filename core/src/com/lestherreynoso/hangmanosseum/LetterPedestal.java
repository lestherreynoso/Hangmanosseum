package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Kpable on 3/24/2015.
 */
public class LetterPedestal extends Sprite {
    private String letterValue;
    private Sprite correctLetter;

    public LetterPedestal(Sprite letterPedistal, String letterValue){
        this.set(letterPedistal);
        this.letterValue = letterValue;
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

    }

    @Override
    public void draw(Batch batch) {
        if(correctLetter != null){
            correctLetter.draw(batch);
        }
        super.draw(batch);
    }
}
