package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kpable on 3/22/2015.
 */
public class Player extends AnimatedSprite{

    // MOVEMENT BOOLEANS //
    private boolean moving;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;

    // COLLISION BOOLEANS //
    private boolean collidingWithLetter;
    private boolean collidingWithDecidingPedestal;

    private Vector2 velocity = new Vector2();
    private int speed = 350;

    private boolean carrying;
//    private float carryingLetterPositionX;
//    private float carryingLetterPositionY;
    private Letter carryingLetter;


    public Player(Sprite playerSprite) {
        super(playerSprite, 4, 1, 0.3f);
        this.moving = false;
        this.movingUp = false;


        setPosition(Hangmanosseum.SCREEN_WIDTH/2, 0);
    }

    @Override
    public void draw(Batch batch) {
        if( moving || getCurrentFrame() == null) {
            if(isMovingLeft()){
                moveLeft();
            }
            if(isMovingRight()){
                moveRight();
            }
            if(isMovingUp()){
                moveUp();
            }
            if(isMovingDown()){
                moveDown();
            }
            super.draw(batch);
//            this.moving = false;
        }else{
            this.moving = false;
            batch.draw(getCurrentFrame(), this.getX(), this.getY());
        }
        if(isCarrying()){
            float lposx = getX() + getSpriteCenterWidthOffset();
            float lposy = getY() + getSpriteHeight() + 10;
            setCarryingLetterPosition(lposx, lposy);
//            setCarryingLetterPositionX(lposx);
//            setCarryingLetterPositionY(lposy);
        }
    }

    private void checkScreenBounds() {
        if (getX() < 0){
            setX(0);
            velocity.x = 0;
        }
        if(getX() + getSpriteWidth() > Hangmanosseum.SCREEN_WIDTH){
            setX(Hangmanosseum.SCREEN_WIDTH - getSpriteWidth());
            velocity.x = 0;
        }
        if (getY() < 0){
            setY(0);
            velocity.y = 0;
        }
        if(getY() + getSpriteHeight() > Hangmanosseum.SCREEN_HEIGHT){
            setY(Hangmanosseum.SCREEN_HEIGHT - getSpriteHeight());
            velocity.y = 0;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void pickUpLetter() {
        //TODO change to pick up animation
        //TODO set carrying to true
        setCarrying(true);
        //TODO while carrying is true set letter position to above player (in draw method) so its not just a press down but a toggle

        float lposx = getX() + getSpriteCenterWidthOffset();
        float lposy = getY() + getSpriteHeight() + 10;
        setCarryingLetterPosition(lposx, lposy);
        this.carryingLetter.setPosition(lposx, lposy);
//        setCarryingLetterPositionX(lposx);
//        setCarryingLetterPositionY(lposy);
    }

    private void setCarryingLetterPosition(float lposx, float lposy) {
        //TODO center offset letters for this reason
        this.carryingLetter.setPosition(lposx - this.carryingLetter.getWidth()/2, lposy);
    }

    public boolean isCarrying() {
        return carrying;
    }

    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    public void dropLetter() {
        //TODO change to drop animation
        //TODO set carrying to false
        setCarrying(false);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MOVEMENT METHODS
////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        setPosition(getX()+xMovement, getY()+yMovement);

        checkScreenBounds();
        this.moving = true;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MOVEMENT GETTERS AND SETTERS
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }
//
    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

//    public float getCarryingLetterPositionX() {
//        return carryingLetterPositionX;
//    }

//    public void setCarryingLetterPositionX(float carryingLetterPositionX) {
//        this.carryingLetterPositionX = carryingLetterPositionX;
//    }
//
//    public float getCarryingLetterPositionY() {
//        return carryingLetterPositionY;
//    }

//    public void setCarryingLetterPositionY(float carryingLetterPositionY) {
//        this.carryingLetterPositionY = carryingLetterPositionY;
//    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// COLLISION GETTERS AND SETTERS
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setCollidingWithLetter(boolean colliding) {
        this.collidingWithLetter = colliding;
    }

    public boolean isCollidingWithLetter() {
        return collidingWithLetter;
    }

    public void setCollidingWithDecidingPedestal(boolean collidingWithDecidingPedestal) {
        this.collidingWithDecidingPedestal = collidingWithDecidingPedestal;
    }

    public boolean isCollidingWithDecidingPedestal() {
        return collidingWithDecidingPedestal;
    }

    //lets the player instance actually hold the letter
    public Letter getCarryingLetter() {
        return carryingLetter;
    }

    public void setCarryingLetter(Letter carryingLetter) {
        this.carryingLetter = carryingLetter;
    }
}
