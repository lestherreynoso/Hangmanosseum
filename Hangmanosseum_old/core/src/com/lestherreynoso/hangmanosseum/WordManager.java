package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by lesther on 2/24/2015.
 */
public class WordManager {
    private String chosenPlayerWord;
    private String chosenEnemyWord;
    private Texture letterPedistalTexture;

    public WordManager(){
        this.chosenPlayerWord = chooseWord();
        this.chosenEnemyWord = chooseWord();
        letterPedistalTexture = new Texture("letterPedistal.png");
    }

    private String chooseWord() {
        String[] PossibleOptions = new String[]{"Red", "Blue", "Orange", "Violet"};
        Random random = new Random();
        return PossibleOptions[random.nextInt(PossibleOptions.length)];
    }

    public void drawWordPedistals(SpriteBatch batch) {
        float xpos, ypos;
        xpos = 15 + Hangmanosseum.MAIN_PEDISTAL_WIDTH;
        ypos = 0;
        for(int i = 0; i < this.chosenPlayerWord.length(); i++){
            Sprite letterPedistal = new Sprite(letterPedistalTexture);
            letterPedistal.setSize(Hangmanosseum.LETTER_PEDISTAL_WIDTH, Hangmanosseum.LETTER_PEDISTAL_HEIGHT);
            letterPedistal.setPosition(xpos, ypos);
            letterPedistal.draw(batch);
            xpos += Hangmanosseum.LETTER_PEDISTAL_WIDTH;
        }

    }
}
