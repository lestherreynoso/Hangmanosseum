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
//    private ScalableAnimation animation;
    private TextureRegion[] frames;
    private float stateTime;
    private TextureRegion currentFrame;
    private double scale;



    private Sprite currentSprite;


    public AnimatedSprite(Sprite animatedSprite, int frameCol, int frameRow, float frameDuration ){
        this.sprite = animatedSprite;
        this.FrameCol = frameCol;
        this.FrameRow = frameRow;
        this.FrameDuration = frameDuration;
        this.scale = 1.0;

        createAnimation();
    }

    private void createAnimation() {
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
//        animation = new ScalableAnimation(FrameDuration, frames);
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
//        Gdx.app.log("AnimatedSprite", "Drawing");
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);
//
        currentSprite = new Sprite(animation.getKeyFrame(stateTime, true));
        currentSprite.setSize(currentSprite.getWidth() * (float)scale, currentSprite.getHeight() * (float)scale);

//        sprite.setRegion(currentFrame);
//        sprite.setr
//        sprite.setRegionHeight((int) sprite.getHeight());
//        sprite.setRegionWidth((int) sprite.getWidth());
//        sprite.draw(batch);

//        batch.draw(currentFrame, sprite.getX(), sprite.getY()); //works
        batch.draw(currentSprite, sprite.getX(), sprite.getY(), 0, 0, currentSprite.getWidth(), currentSprite.getHeight(), 1, 1, 0);
//        animation.setScaling((float)scale);
//        animation.draw(stateTime, batch, sprite.getX(), sprite.getY());
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

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }
//    public Texture getTexture(){
//        return sprite.getTexture();
//    }
//
//    public void setSprite(Sprite fieldSprite) {
//        this.sprite.set(fieldSprite);
//        createAnimation();
//    }
}
