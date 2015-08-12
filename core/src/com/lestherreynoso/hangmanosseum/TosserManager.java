package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kpable on 5/31/2015.
 */
public class TosserManager {
    private Texture tosser;
    private int PlayerTosserCount = 1;
    private int OpponentTosserCount = 2;
    private ArrayList<Tosser> playerTossers;
    private ArrayList<Tosser> opponentTossers;

    public TosserManager(){
        tosser = new Texture(Gdx.files.internal("imgs/game/characters/thrower-throwing.png"));
        playerTossers =  new ArrayList<Tosser>();
        opponentTossers =  new ArrayList<Tosser>();
    }
    public void spawnTossers() {
        spawnPlayerTossers();
        spawnOpponentTossers();
    }

    private void spawnOpponentTossers() {
        float xpos, ypos;
        xpos = Hangmanosseum.SCREEN_WIDTH - 100;
        ypos = Hangmanosseum.SCREEN_HEIGHT - tosser.getHeight() - 30;
        for(int i = 0; i< OpponentTosserCount; i++){
            Tosser t = new Tosser(new Sprite(tosser));
            t.setPosition(xpos, ypos);
            opponentTossers.add(t);
            xpos -= t.getSpriteWidth();
        }
    }

    private void spawnPlayerTossers() {
        float xpos, ypos;
        xpos = 0;
        ypos = Hangmanosseum.SCREEN_HEIGHT - tosser.getHeight() - 30;
        for(int i = 0; i< PlayerTosserCount; i++){
            Tosser t = new Tosser(new Sprite(tosser));
            t.setPosition(xpos, ypos);
            playerTossers.add(t);
            xpos += t.getSpriteWidth();
        }
    }

    public void drawTossers(SpriteBatch batch) {
        drawPlayerTossers(batch);
        drawOpponentTossers(batch);
    }

    private void drawOpponentTossers(SpriteBatch batch) {
        for(Tosser t : opponentTossers){
            t.draw(batch);
        }
    }

    private void drawPlayerTossers(SpriteBatch batch) {
        for(Tosser t : playerTossers){
            t.draw(batch);
        }
    }

    public void giveLetterToTosser(Letter temp) {
        Random random = new Random();
        if(random.nextBoolean()){
            playerTossers.get(random.nextInt(playerTossers.size())).throwLetter(temp);
        }else{
            opponentTossers.get(random.nextInt(playerTossers.size())).throwLetter(temp);
        }
    }
}
