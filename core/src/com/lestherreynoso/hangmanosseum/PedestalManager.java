package com.lestherreynoso.hangmanosseum;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lestherreynoso.accessors.SpriteAccessor;

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

    private TweenManager tweenManager;
    private Timeline timeline;

    private Texture letterPedestal;

    public PedestalManager(){
        letterPedestal = new Texture(Gdx.files.internal("imgs/game/field/wordPedestal.png"));
        playerWord = new ArrayList<LetterPedestal>();
        opponentWord = new ArrayList<LetterPedestal>();

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

    }

    public void animate() {
        timeline = Timeline.createParallel();

        timeline.beginSequence();
        for(LetterPedestal lp : playerWord){
            timeline.push(Tween.set(lp, SpriteAccessor.Y).target(0 - lp.getHeight()));
        }
        for(LetterPedestal lp : playerWord){
            timeline.push(Tween.to(lp, SpriteAccessor.Y, 0.5f).target(0));
        }
        timeline.end();

        timeline.beginSequence();
        for(LetterPedestal lp : opponentWord){
            timeline.push(Tween.set(lp, SpriteAccessor.Y).target(0 - lp.getHeight()));
        }
        for(LetterPedestal lp : opponentWord){
            timeline.push(Tween.to(lp, SpriteAccessor.Y, 0.5f).target(0));
        }
        timeline.end().start(tweenManager);
        tweenManager.update(Float.MIN_VALUE);

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
            LetterPedestal l = new LetterPedestal(new Sprite(letterPedestal), chosenPlayerWord.substring(i, i+1).toLowerCase());
            l.setSize(l.getWidth()/LETTER_PEDESTAL_DOWNSCALE_BY, l.getHeight()/ LETTER_PEDESTAL_DOWNSCALE_BY);
            l.setPosition(xpos, ypos);
//            l.loadAnimation();
//            timeline.push(Tween.set(l, SpriteAccessor.Y).target(0 - l.getHeight()));
//            timeline.push(Tween.to(l, SpriteAccessor.Y, 2).target(0));
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
            LetterPedestal l = new LetterPedestal(new Sprite(letterPedestal), chosenOpponentWord.substring(i, i+1).toLowerCase());
            l.setSize(l.getWidth()/LETTER_PEDESTAL_DOWNSCALE_BY, l.getHeight()/LETTER_PEDESTAL_DOWNSCALE_BY);
            l.setPosition(xpos, ypos);
//            l.loadAnimation();
//            timeline.push(Tween.set(l, SpriteAccessor.Y).target(0 - l.getHeight()));
//            timeline.push(Tween.to(l, SpriteAccessor.Y, 2).target(0));
            opponentWord.add(l);
            xpos -= l.getWidth();
        }

//        timeline.end();
//        timeline.start(tweenManager);
//        tweenManager.update(Float.MIN_VALUE);
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

        if(Gdx.graphics.getDeltaTime() != 0){
            tweenManager.update(Gdx.graphics.getDeltaTime());
        }
    }

    public DecidingPedestal getPlayerDecidingPedestal() {
        return playerDecidingPedestal;
    }

    public Boolean checkPlayerLetter(Letter carryingLetter) {
        String letterValue = carryingLetter.getLetterValue();
        boolean found = false;
        for(LetterPedestal lp : playerWord){
            if(letterValue.equals(lp.getLetterValue()) && !lp.isRisen()){
                lp.rise(carryingLetter);
                found = true;
            }
        }
        return found;
//        playerDecidingPedestal.decide(carryingLetter.getLetterValue());
    }
}
