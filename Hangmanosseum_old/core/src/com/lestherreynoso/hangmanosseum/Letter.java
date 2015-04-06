package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by lesther on 2/20/2015.
 */
public class Letter extends FieldObject {

    public Letter(Texture letter){
        this.set(new Sprite(letter));
    }
}
