package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Kpable on 3/24/2015.
 */
public class PedestalManager {
    public static final int DECIDING_PEDESTAL_DOWNSCALE_BY = 15;
    public static final int LETTER_PEDESTAL_DOWNSCALE_BY = 30;
    private DecidingPedestal playerDecidingPedestal;
    private DecidingPedestal opponentDecidingPedestal;
    private ArrayList<LetterPedestal> playerWord;
    private ArrayList<LetterPedestal> opponentWord;

    private Texture letterPedistal;

    public PedestalManager(){
        letterPedistal = new Texture(Gdx.files.internal("imgs/game/field/wordPedestal.png"));
        playerWord = new ArrayList<LetterPedestal>();
        opponentWord = new ArrayList<LetterPedestal>();
    }
    public void spawnDecidingPedestals() {

        Texture decidingPedestal = new Texture(Gdx.files.internal("imgs/game/field/decidingPedestal.png"));

        playerDecidingPedestal = new DecidingPedestal(new Sprite(decidingPedestal));
        opponentDecidingPedestal = new DecidingPedestal(new Sprite(decidingPedestal));

        playerDecidingPedestal.setSize(
                playerDecidingPedestal.getWidth() / DECIDING_PEDESTAL_DOWNSCALE_BY,
                playerDecidingPedestal.getHeight() / DECIDING_PEDESTAL_DOWNSCALE_BY);
        playerDecidingPedestal.setPosition(0, 0);
        opponentDecidingPedestal.setSize(
                opponentDecidingPedestal.getWidth() / DECIDING_PEDESTAL_DOWNSCALE_BY,
                opponentDecidingPedestal.getHeight() / DECIDING_PEDESTAL_DOWNSCALE_BY);
        opponentDecidingPedestal.setPosition(Gdx.graphics.getWidth() - opponentDecidingPedestal.getWidth(), 0);
    }

    public void spawnPlayerLetterPedestals(String chosenPlayerWord){
        float xpos, ypos;
        xpos = playerDecidingPedestal.getWidth() + 15;
        ypos = 0;

        for(int i = 0; i < chosenPlayerWord.length(); i++){
            LetterPedestal l = new LetterPedestal(new Sprite(letterPedistal), chosenPlayerWord.substring(i, i+1).toLowerCase());
            l.setSize(l.getWidth()/LETTER_PEDESTAL_DOWNSCALE_BY, l.getHeight()/ LETTER_PEDESTAL_DOWNSCALE_BY);
            l.setPosition(xpos, ypos);
            playerWord.add(l);
            xpos += l.getWidth();
        }
    }

    public void spawnOpponentLetterPedestals(String chosenOpponentWord){
        float xpos, ypos;
        //TODO figure out why this getX is at the center as opposed to the bottom left
        xpos = opponentDecidingPedestal.getX() - (opponentDecidingPedestal.getWidth() / 2) - 15;
        ypos = 0;

        for(int i = 0; i < chosenOpponentWord.length(); i++){
            LetterPedestal l = new LetterPedestal(new Sprite(letterPedistal), chosenOpponentWord.substring(i, i+1).toLowerCase());
            l.setSize(l.getWidth()/LETTER_PEDESTAL_DOWNSCALE_BY, l.getHeight()/LETTER_PEDESTAL_DOWNSCALE_BY);
            l.setPosition(xpos, ypos);
            opponentWord.add(l);
            xpos -= l.getWidth();
        }
    }

    public void drawDecidingPedestals(SpriteBatch batch){
        playerDecidingPedestal.draw(batch);
        opponentDecidingPedestal.draw(batch);
    }

    public void drawLetterPedestals(SpriteBatch batch){
        for(LetterPedestal lp : playerWord){
            lp.draw(batch);
        }

        for(LetterPedestal lp : opponentWord){
            lp.draw(batch);
        }

    }

    public void drawPedestals(SpriteBatch batch) {
        drawDecidingPedestals(batch);
        drawLetterPedestals(batch);
    }

    public DecidingPedestal getPlayerDecidingPedestal() {
        return playerDecidingPedestal;
    }

    public Boolean checkPlayerLetter(Letter carryingLetter) {
        String letterValue = carryingLetter.getLetterValue();
        boolean found = false;
        for(LetterPedestal lp : playerWord){
            if(letterValue.equals(lp.getLetterValue()) ){
                lp.rise(carryingLetter);
                found = true;
            }
        }
        return found;
//        playerDecidingPedestal.decide(carryingLetter.getLetterValue());
    }
}
