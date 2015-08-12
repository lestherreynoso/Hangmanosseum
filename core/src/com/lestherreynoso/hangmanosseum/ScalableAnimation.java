package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Kpable on 6/5/2015.
 */
public class ScalableAnimation extends Animation {
    float scaleX = 1;
    float scaleY = 1;

    public ScalableAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        super(frameDuration, keyFrames);
    }

    public ScalableAnimation(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
    }
    public ScalableAnimation(float frameDuration, TextureRegion... keyFrames) {
        super(frameDuration, keyFrames);
    }

    public void setScaling(float scale){
        scaleX = scale;
        scaleY = scale;
    }

    public void draw (float stateTime, Batch batch, float x, float y) {
        TextureRegion region = getKeyFrame(stateTime);
        batch.draw(region, x, y, region.getRegionWidth()*scaleX, region.getRegionHeight()*scaleY);
    }
}
