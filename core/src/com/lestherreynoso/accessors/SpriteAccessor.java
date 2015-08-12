package com.lestherreynoso.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Kpable on 3/21/2015.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
    public static final int ALPHA = 0;
    public static final int POS = 1;
    public static final int X = 2;
    public static final int Y = 3;

    @Override
    public int getValues(Sprite sprite, int tweenType, float[] returnValues) {
        switch (tweenType){
            case ALPHA:
                returnValues[0] = sprite.getColor().a;
                return 1;
            case POS:
                returnValues[0] = sprite.getX();
                returnValues[1] = sprite.getY();
                return 2;
            case X:
                returnValues[0] = sprite.getX();
                return 1;
            case Y:
                returnValues[0] = sprite.getY();
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Sprite sprite, int tweenType, float[] newValues) {
        switch (tweenType){
            case ALPHA:
                sprite.setColor(sprite.getColor().r, sprite.getColor().g, sprite.getColor().r, newValues[0]);
                break;
            case POS:
                sprite.setPosition(newValues[0], newValues[1]);
                break;
            case X:
                sprite.setX(newValues[0]);
                break;
            case Y:
                sprite.setY(newValues[0]);
                break;
            default:
                assert false;
                break;
        }
    }
}
