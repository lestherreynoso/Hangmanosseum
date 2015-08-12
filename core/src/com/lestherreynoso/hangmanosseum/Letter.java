package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Kpable on 3/22/2015.
 */
public class Letter extends FieldSprite{

    private String letterValue;

    public Letter(Sprite letterSprite, String letterValue){
        this.set(letterSprite); ;
        this.letterValue = letterValue;
    }

    public String getLetterValue() {
        return letterValue;
    }

    public void checkScreenBounds() {
        if (getX() < 0){
            setX(0);
        }
        if(getX() + getWidth() > Hangmanosseum.SCREEN_WIDTH){
            setX(Hangmanosseum.SCREEN_WIDTH - getWidth());
        }
        if (getY() < 0){
            setY(0);
        }
        if(getY() + getHeight() > Hangmanosseum.SCREEN_HEIGHT){
            setY(Hangmanosseum.SCREEN_HEIGHT - getHeight());
        }
    }
}
