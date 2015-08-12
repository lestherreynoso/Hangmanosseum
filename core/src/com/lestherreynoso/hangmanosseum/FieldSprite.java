package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Kpable on 5/31/2015.
 */
public class FieldSprite extends Sprite{
//    private boolean moving;
//    private double scaledHeight;
//    private double scale;
//
//    @Override
//    public void setPosition(float x, float y) {
//        super.setPosition(x, y);
//        this.scale = (scaledHeight - this.getY()) / scaledHeight;
//        setSize(this.getWidth() *(float)scale, this.getHeight() * (float)scale);
//    }
//
//    public FieldSprite() {
//        init();
//    }
//
//    private void init() {
//        this.moving = false;
//        scaledHeight = (Hangmanosseum.SCREEN_HEIGHT + (Hangmanosseum.SCREEN_HEIGHT * Hangmanosseum.MIN_SCALE));
//        this.scale = 1;
//        setSize(this.getWidth() *(float)scale, this.getHeight() * (float)scale);
//    }
//
//    public FieldSprite(Texture texture) {
//        super(texture);
//        init();
//    }
//
//    public FieldSprite(Texture texture, int srcWidth, int srcHeight) {
//        super(texture, srcWidth, srcHeight);
//        init();
//    }
//
//    public FieldSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
//        super(texture, srcX, srcY, srcWidth, srcHeight);
//        init();
//    }
//
//    public FieldSprite(TextureRegion region) {
//        super(region);
//        init();
//    }
//
//    public FieldSprite(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
//        super(region, srcX, srcY, srcWidth, srcHeight);
//        init();
//    }
//
//    public FieldSprite(Sprite sprite) {
//        super(sprite);
//        init();
//    }
//
//    @Override
//    public void draw(Batch batch) {
//
//        if (isMoving()){
//            scale = (scaledHeight - this.getY()) / scaledHeight;
//            setSize(this.getWidth() *(float)scale, this.getHeight() * (float)scale);
//        }
//
//        super.draw(batch);
//    }
//
//    public boolean isMoving() {
//        return moving;
//    }
//
//    public void setMoving(boolean moving) {
//        this.moving = moving;
//    }
}
