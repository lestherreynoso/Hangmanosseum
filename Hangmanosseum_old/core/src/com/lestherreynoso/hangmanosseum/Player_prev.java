package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Kpable on 1/19/2015.
 */
public class Player_prev extends Sprite {
    public static final int DEFAULT_SPEED = 500;
    int speed = DEFAULT_SPEED;

    public Player_prev(Texture player) {
        this.set(new Sprite(player));
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        float scale = (450 - this.getY()) / 450;
        if(scale <= 0.25){
            scale = 0.25f;
        }
        float w = 250 * scale;
        float h = 250 * scale;
        this.setSize(w, h);
//        this.setScale(scale);
    }

    public void move(String direction) {

        if (direction.equals("up")){
            this.setY(this.getY() + speed * Gdx.graphics.getDeltaTime());
            checkBounds();
        }
        if (direction.equals("down")){
            this.setY(this.getY() - speed * Gdx.graphics.getDeltaTime());
            checkBounds();
        }
        if (direction.equals("left")){
            this.setX(this.getX() - speed * Gdx.graphics.getDeltaTime());
            checkBounds();
        }
        if (direction.equals("right")){
            this.setX(this.getX() + speed * Gdx.graphics.getDeltaTime());
            checkBounds();
        }
    }

    private void checkBounds() {
        if (this.getX() >= Hangmanosseum.SCREEN_WIDTH - this.getWidth()){
            setPosition(Hangmanosseum.SCREEN_WIDTH - this.getWidth(), this.getY());
        }
        if (this.getX() <= 0){
            setPosition(0, this.getY());
        }
        if (this.getY() >= Hangmanosseum.SCREEN_HEIGHT - this.getHeight()){
            setPosition(this.getX(), Hangmanosseum.SCREEN_HEIGHT - this.getHeight());
        }
        if (this.getY() <= 0){
            setPosition(this.getX(), 0);
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

