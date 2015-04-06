package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Kpable on 3/22/2015.
 */
public class Letter extends Sprite{

    private String letterValue;

    public Letter(Sprite letterSprite, String letterValue){
        this.set(letterSprite); ;
        this.letterValue = letterValue;
    }

    public String getLetterValue() {
        return letterValue;
    }

}
