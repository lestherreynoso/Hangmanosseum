package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kpable on 3/22/2015.
 */
public class LetterManager {
    TextureAtlas atlas;
    private String[] lettersList = new String[] {
            "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"};
    private ArrayList<Letter> fieldLetters;
    private ArrayList<Letter> incorrectLetters;
    private int fieldLettersCount;
//    private Sprite collidingLetter;


    public LetterManager(){
        this.atlas = new TextureAtlas(Gdx.files.internal("imgs/game/letters/letterTextures.pack"));
        fieldLetters = new ArrayList<Letter>();
        incorrectLetters = new ArrayList<Letter>();
    }

    public Letter generateRandomLetter() {
        Random random = new Random();
        int n = random.nextInt(lettersList.length);
        Letter randomLetter = new Letter(new Sprite(atlas.createSprite(lettersList[n])), lettersList[n]);
        return randomLetter;

    }

    public void addToFieldLetters(Letter temp) {
        fieldLettersCount += 1;
        fieldLetters.add(temp);
    }

    public void removeFromFieldLetters(Letter temp) {
        fieldLettersCount -= 1;
        fieldLetters.remove(temp);
    }

    public void drawAllLetters(SpriteBatch batch) {
        for(Letter l : fieldLetters){
            l.draw(batch);
        }
    }

    public int getFieldLettersCount() {
        return fieldLettersCount;
    }

    public void addToIncorrectLetters(Letter carryingLetter) {
        removeFromFieldLetters(carryingLetter);
        incorrectLetters.add(carryingLetter);
    }

//    public void setCollidingLetter(Sprite collidingLetter) {
//        this.collidingLetter = collidingLetter;
//    }
//
//    public Sprite getCollidingLetter() {
//        return collidingLetter;
//    }

//    public void setPositionOfCarryingLetter(float x, float y) {
//        //TODO center offset letters for this reason
//        collidingLetter.setPosition(x - collidingLetter.getWidth()/2, y);
//    }
}
