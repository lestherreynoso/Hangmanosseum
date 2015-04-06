package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Kpable on 3/22/2015.
 */
public class AnimatedSprite {
    private final float FrameDuration;
    private Sprite sprite;
    private final int FrameCol;
    private final int FrameRow;

    private Animation animation;
    private TextureRegion[] frames;
    private float stateTime;
    private TextureRegion currentFrame;


    public AnimatedSprite(Sprite playerSprite, int frameCol, int frameRow, float frameDuration ){
        this.sprite = playerSprite;
        this.FrameCol = frameCol;
        this.FrameRow = frameRow;
        this.FrameDuration = frameDuration;

        Texture playerSpriteSheetTexture = this.sprite.getTexture();
        TextureRegion[][] temp = TextureRegion.split(playerSpriteSheetTexture, (int) getSpriteWidth(), playerSpriteSheetTexture.getHeight() / FrameRow);
        frames = new TextureRegion[FrameCol * FrameRow];
        int index = 0;
        for (int i = 0; i < FrameRow; i++){
            for(int j = 0; j < FrameCol; j++){
                frames[index++] = temp[i][j];
            }
        }

        animation = new Animation(FrameDuration, frames);
        stateTime = 0f;
    }

    protected float getSpriteWidth() {
        return sprite.getWidth() / FrameCol;
    }
    protected float getSpriteHeight() {
        return sprite.getHeight() / FrameRow;
    }

    public float getSpriteCenterWidthOffset(){
        return getSpriteWidth()/2;
    }
    public float getSpriteCenterHeightOffset(){
        return getSpriteHeight()/2;
    }

    public void draw(Batch batch) {

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);

//        sprite.setRegion(currentFrame);
//        sprite.setr
//        sprite.setRegionHeight((int) sprite.getHeight());
//        sprite.setRegionWidth((int) sprite.getWidth());
//        sprite.draw(batch);

        batch.draw(currentFrame, sprite.getX(), sprite.getY());
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(sprite.getX(), sprite.getY(), getSpriteWidth(), getSpriteHeight());
    }

    public void setPosition(float x, float y) {
//        sprite.setPosition(x - getSpriteCenterWidthOffset() / 2, y);
        sprite.setPosition(x, y);
    }

    public float getX() {
        return sprite.getX();
//        return sprite.getX() + getSpriteCenterWidthOffset();
    }

    public float getY() {
        return sprite.getY();
//        return sprite.getY() + getSpriteCenterHeightOffset();
    }

    public void setX(float x){
        sprite.setX(x);
    }

    public void setY(float y){
        sprite.setY(y);
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }
}
