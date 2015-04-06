package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by Kpable on 3/22/2015.
 */
public class Colosseum implements Screen, InputProcessor {
    private Player player;
    private LetterManager letterManager;
    private CollisionManager collisionManager;
    private PedestalManager pedestalManager;
    SpriteBatch batch;


    Random random;
    private float stateTime;
    private WordManager wordManager;

    @Override
    public void show() {
        //TODO Make all the managers independent of each other and make them talk to colosseum only.
        batch = new SpriteBatch();
        letterManager = new LetterManager();
        wordManager = new WordManager();    //word is chosen upon creation at the moment
        pedestalManager = new PedestalManager();
        player = new Player(new Sprite(new Texture(Gdx.files.internal("imgs/game/characters/player-front-running-spritesheet.png"))));

        //TODO find a better way to check letter collisions that's not passing the letterManager
        collisionManager = new CollisionManager(player, letterManager, pedestalManager);

        pedestalManager.spawnDecidingPedestals();
        pedestalManager.spawnPlayerLetterPedestals(wordManager.getChosenPlayerWord());
        pedestalManager.spawnOpponentLetterPedestals(wordManager.getChosenOpponentWord());

        Gdx.input.setInputProcessor(this);
        random = new Random();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.draw(batch);
        spawnLetters(batch);
        pedestalManager.drawPedestals(batch);
        batch.end();

        collisionManager.handleCollisions();
//        handleInput();

    }

    private void spawnLetters(SpriteBatch batch) {

        stateTime += Gdx.graphics.getDeltaTime();
        if(letterManager.getFieldLettersCount() <= 10) {
            if (stateTime >= 3f) {
                Letter temp = letterManager.generateRandomLetter();
                collisionManager.addToCollisionCheck(temp);
                temp.setPosition(random.nextInt(Hangmanosseum.SCREEN_WIDTH), random.nextInt(Hangmanosseum.SCREEN_HEIGHT));
                temp.setSize(30, 30);
                letterManager.addToFieldLetters(temp);
                stateTime = 0;
            }
        }
//        if(player.isCarrying()){
//            letterManager.setPositionOfCarryingLetter(player.getCarryingLetterPositionX(), player.getCarryingLetterPositionY());
//        }
        letterManager.drawAllLetters(batch);
    }

    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.isCollidingWithLetter() && !player.isCarrying()){
            player.pickUpLetter();
        }else {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.isCarrying()) {
                player.dropLetter();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INPUT PROCESSOR METHODS
////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                player.setMoving(true);
                player.setMovingLeft(true);
//                Gdx.app.log("keydown", "movin left");
                break;
            case Input.Keys.RIGHT:
                player.setMoving(true);
                player.setMovingRight(true);
//                Gdx.app.log("keydown", "movin right");
                break;
            case Input.Keys.UP:
                player.setMoving(true);
                player.setMovingUp(true);
//                Gdx.app.log("keydown", "movin up");
                break;
            case Input.Keys.DOWN:
                player.setMoving(true);
                player.setMovingDown(true);
//                Gdx.app.log("keydown", "movin down");
                break;

            case Input.Keys.SPACE:
//                Gdx.app.log("Keydown", "space pressed");
                if (player.isCollidingWithLetter() && !player.isCarrying()){
                    Gdx.app.log("Keydown", "colliding and not carrying");
                    player.pickUpLetter();
                }else if(player.isCarrying() && !player.isCollidingWithDecidingPedestal()){
                    Gdx.app.log("Keydown", "carrying and not colliding with pedistal");
                    player.dropLetter();
                }else if(player.isCarrying() && player.isCollidingWithDecidingPedestal()){
                    Gdx.app.log("Keydown", "carrying and colliding with pedistal");
                    if(!pedestalManager.checkPlayerLetter(player.getCarryingLetter())){
                        collisionManager.removeFromCollisionCheck(player.getCarryingLetter());
                        letterManager.addToIncorrectLetters(player.getCarryingLetter());
                    }else{
                        collisionManager.removeFromCollisionCheck(player.getCarryingLetter());
                        letterManager.removeFromFieldLetters(player.getCarryingLetter());
                    }
                    player.dropLetter();
        }
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                player.setMovingLeft(false);
                checkOtherKeys();
//                Gdx.app.log("keyup", "movin left");
                break;
            case Input.Keys.RIGHT:
                player.setMovingRight(false);
                checkOtherKeys();
//                Gdx.app.log("keyup", "movin right");
                break;
            case Input.Keys.UP:
                player.setMovingUp(false);
                checkOtherKeys();
//                Gdx.app.log("keyup", "movin up");
                break;
            case Input.Keys.DOWN:
                player.setMovingDown(false);
                checkOtherKeys();
//                Gdx.app.log("keyup", "movin down");
                break;
        }
        return true;
    }

    private void checkOtherKeys() {
        if( !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
            !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
            !Gdx.input.isKeyPressed(Input.Keys.UP) &&
            !Gdx.input.isKeyPressed(Input.Keys.DOWN) ){

            player.setMoving(false);
        }
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
