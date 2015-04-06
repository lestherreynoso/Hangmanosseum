package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by Kpable on 3/24/2015.
 */
public class WordManager {
    private String chosenPlayerWord;
    private String chosenOpponentWord;

    public WordManager(){
        this.chosenPlayerWord = choosePlayerWord();
        this.chosenOpponentWord = chooseOpponentWord();
    }

    public String choosePlayerWord(){
        return chooseWord();
    }

    public String chooseOpponentWord(){
        return chooseWord();
    }
    private String chooseWord() {
        String[] PossibleOptions = new String[]{"Red", "Blue", "Orange", "Violet", "Green"};
//        String[] PossibleOptions = new String[]{"Green"};

        Random random = new Random();
        return PossibleOptions[random.nextInt(PossibleOptions.length)];
    }

    public String getChosenPlayerWord() {
        return chosenPlayerWord;
    }

    public String getChosenOpponentWord() {
        return chosenOpponentWord;
    }
}
