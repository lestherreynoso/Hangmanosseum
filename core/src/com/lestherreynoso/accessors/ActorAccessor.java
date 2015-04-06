package com.lestherreynoso.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Kpable on 3/21/2015.
 */
public class ActorAccessor implements TweenAccessor<Actor> {
    public static final int RGB = 0;
    public static final int ALPHA = 1;
    public static final int Y = 2;

    @Override
    public int getValues(Actor actor, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case RGB:
                returnValues[0] = actor.getColor().r;
                returnValues[1] = actor.getColor().g;
                returnValues[2] = actor.getColor().b;
                return 3;
            case ALPHA:
                returnValues[0] = actor.getColor().a;
                return 1;
            case Y:
                returnValues[0] = actor.getY();
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Actor actor, int tweenType, float[] newValues) {
        switch (tweenType) {
            case RGB:
                actor.setColor(newValues[0], newValues[1], newValues[2], actor.getColor().a);
                break;
            case ALPHA:
                actor.setColor(actor.getColor().r, actor.getColor().g, actor.getColor().r, newValues[0]);
                break;
            case Y:
                actor.setY(newValues[0]);
                break;
            default:
                assert false;
                break;
        }
    }
}
