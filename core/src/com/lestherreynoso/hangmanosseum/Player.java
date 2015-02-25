package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by lesther on 2/19/2015.
 */
public class Player extends AnimatedSprite {

    private Vector2 velocity = new Vector2();

    private int speed = 300;

    public Player(Texture playerTexture) {
        this.set(new Sprite(playerTexture));
        initAnimation();
    }

    public void moveRight(){
        velocity = new Vector2(speed,0);
        move();
    }

    public void moveLeft(){
        velocity = new Vector2(-speed,0);
        move();
    }

    public void moveUp(){
        velocity = new Vector2(0,speed);
        move();
    }

    public void moveDown(){
        velocity = new Vector2(0,-speed);
        move();
    }

    public void move() {
        int xMovement = (int) (velocity.x * Gdx.graphics.getDeltaTime());
        int yMovement = (int) (velocity.y * Gdx.graphics.getDeltaTime());
        this.setPosition(this.getX()+xMovement, this.getY()+yMovement);

        checkScreenBounds();

    }

    private void checkScreenBounds() {
        if (this.getX() < 0){
            this.setX(0);
            velocity.x = 0;
        }
        if(this.getX() + getSpriteWidth() > Hangmanosseum.SCREEN_WIDTH){
            this.setX(Hangmanosseum.SCREEN_WIDTH - getSpriteWidth());
            velocity.x = 0;
        }
        if (this.getY() < 0){
            this.setY(0);
            velocity.y = 0;
        }
        if(this.getY() + getSpriteHeight() > Hangmanosseum.SCREEN_HEIGHT){
            this.setY(Hangmanosseum.SCREEN_HEIGHT - getSpriteHeight());
            float blah = getSpriteHeight();
            velocity.y = 0;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
