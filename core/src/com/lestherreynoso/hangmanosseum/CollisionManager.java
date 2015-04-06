package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Created by Kpable on 3/22/2015.
 */
public class CollisionManager {
    private Player player;
    private boolean letterCollision;
    private ArrayList<Letter> lettersOnTheField;
    private LetterManager letterManager;
    private PedestalManager pedestalManager;

    public CollisionManager(Player player, LetterManager letterManager, PedestalManager pedestalManager){
        this.player = player;
        this.letterManager = letterManager;
        this.pedestalManager = pedestalManager;
        lettersOnTheField = new ArrayList<Letter>();
    }

    public void addToCollisionCheck(Letter object){
        lettersOnTheField.add(object);
    }

    public void removeFromCollisionCheck(Letter object){
        lettersOnTheField.remove(object);
    }

    public void handleCollisions(){
        handlePlayerCollisionsWithLetters();
        handlePlayerCollisionWithDecidingPedestal();
    }

    private void handlePlayerCollisionWithDecidingPedestal() {
        Rectangle intersection = new Rectangle();
        boolean decidingPedestalCollision = Intersector.intersectRectangles(player.getBoundingBox(), pedestalManager.getPlayerDecidingPedestal().getBoundingRectangle(), intersection);
        player.setCollidingWithDecidingPedestal(decidingPedestalCollision);


    }

    private void handlePlayerCollisionsWithLetters() {
        Rectangle intersection = new Rectangle();
        for(Letter letter : lettersOnTheField){
            letterCollision = Intersector.intersectRectangles(player.getBoundingBox(), letter.getBoundingRectangle(), intersection);
            //TODO clean this, somewhere carrying is set to false before i can check it
//            player.setCollidingWithLetter(letterCollision);
//            if(player.isCollidingWithLetter() && !player.isCarrying()){
//                player.setCarryingLetter(letter);
//            }
            if(letterCollision){
                player.setCollidingWithLetter(true);
                if(!player.isCarrying()) {
                    player.setCarryingLetter(letter);
//                    letterManager.setCollidingLetter(letter);
                }
            }
        }
    }
}
