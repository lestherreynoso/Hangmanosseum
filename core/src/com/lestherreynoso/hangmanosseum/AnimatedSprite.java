package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by lesther on 1/26/2015.
 */

public class AnimatedSprite extends FieldObject{

    private static final int FRAMES_COL = 4;

    private static final int FRAMES_ROW = 1;
    public static final int SHIP_SPEED = 300;



    private int FrameCol;
    private int FrameRow;
    //    private Sprite sprite;
    private Animation animation;
    private TextureRegion[] frames;
    private TextureRegion currentFrame;

    private float stateTime;
    float frameDuration = 0.3f;

    public void initAnimation(){
        Texture texture = this.getTexture();
        FrameCol = 4;
        FrameRow = 1;

        TextureRegion[][] temp = TextureRegion.split(texture, (int) getSpriteWidth(), texture.getHeight() / FrameRow);
        frames = new TextureRegion[FrameCol * FrameRow];
        int index = 0;
        for (int i = 0; i < FrameRow; i++){
            for(int j = 0; j < FrameCol; j++){
                frames[index++] = temp[i][j];
            }
        }

        animation = new Animation(frameDuration, frames);
        stateTime = 0f;
    }

    public void setFrameCol(int frameCol) {
        FrameCol = frameCol;
    }

    public void setFrameRow(int frameRow) {
        FrameRow = frameRow;
    }

    public float getSpriteCenterWidthOffset(){
        return getSpriteWidth()/2;
    }
    public float getSpriteCenterHeightOffset(){
        return getSpriteHeight()/2;
    }

    protected float getSpriteWidth() {
        return this.getWidth() / FrameCol;
    }
    protected float getSpriteHeight() {
        float blah = this.getHeight();
        return this.getHeight() / FrameRow;
    }

//    @Override
//    public void setPosition(float x, float y) {
//        super.setPosition(x - getSpriteCenterWidthOffset() / 2, y);
//    }

    @Override
    public void draw(Batch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, this.getX(), this.getY());
//        super.draw(batch);
    }

//    @Override
//    public float getX() {
//        return super.getX() + getSpriteCenterWidthOffset();
//    }
//
//    @Override
//    public float getY() {
//        return super.getY() + getSpriteCenterHeightOffset();
//    }
}

