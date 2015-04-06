package com.lestherreynoso.hangmanosseum;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hangmanosseum extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 600;
    public static final int MAIN_PEDISTAL_HEIGHT = 150;
    public static final int MAIN_PEDISTAL_WIDTH = 110;
    public static final int LETTER_PEDISTAL_WIDTH = 70;
    public static final int LETTER_PEDISTAL_HEIGHT = 110;
    SpriteBatch batch;
    Texture playerTexture, colosseumWallTexture, hangingThingTexture, letterPedistalTexture, mainPedistalTexture, playerTexture2;
    OrthographicCamera cam;
    Player_prev player;
    Sprite playerSprite;
    Sprite AnimatedPlayerSprite;
    Player playerAnimated;
    private Sprite ColosseumWall;
    private Sprite PlayerPedistal;
    private Sprite EnemyPedistal;
    private Sprite LetterPedistal;
    private WordManager wordManager;


    @Override
    public void create () {
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        AnimatedPlayerSprite = new Sprite();
        playerTexture2 = new Texture("frontRunningSpritesheet.png");
        playerAnimated = new Player(playerTexture2);
        playerAnimated.setPosition(SCREEN_WIDTH/4, 0);

        playerTexture = new Texture("player.png");
        playerSprite = new Sprite(playerTexture);
        player = new Player_prev(playerTexture);
        playerSprite.setPosition(512, 0);
        player.setPosition(512, 0);
        colosseumWallTexture = new Texture("colosseumWall.png");
        ColosseumWall = new Sprite(colosseumWallTexture);
        ColosseumWall.setSize(SCREEN_WIDTH, 300);
        ColosseumWall.setPosition(0, 600 - ColosseumWall.getHeight());
        hangingThingTexture = new Texture("hangingThing.png");
        letterPedistalTexture = new Texture("letterPedistal.png");
        mainPedistalTexture = new Texture("mainPedistal.png");
        PlayerPedistal = new Sprite(mainPedistalTexture);
        PlayerPedistal.setSize(MAIN_PEDISTAL_WIDTH, MAIN_PEDISTAL_HEIGHT);
        PlayerPedistal.setPosition(0, 0);
        EnemyPedistal = new Sprite(mainPedistalTexture);
        EnemyPedistal.setSize(MAIN_PEDISTAL_WIDTH, MAIN_PEDISTAL_HEIGHT);
        EnemyPedistal.setPosition(SCREEN_WIDTH - EnemyPedistal.getWidth(), 0);
        LetterPedistal = new Sprite(letterPedistalTexture);
        LetterPedistal.setSize(LETTER_PEDISTAL_WIDTH, LETTER_PEDISTAL_HEIGHT);
        wordManager = new WordManager();

    }



    @Override
    public void render () {
        Gdx.gl.glClearColor(153, 153, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
//		batch.draw(img, 0, 0);
        ColosseumWall.draw(batch);
//        batch.draw(mainPedistalTexture, 0, 0);
//		batch.draw(playerSprite.getTexture(), playerSprite.getX(), playerSprite.getY());
//        player.draw(batch);
        playerAnimated.draw(batch);


        wordManager.drawWordPedistals(batch);

        PlayerPedistal.draw(batch);
        EnemyPedistal.draw(batch);

//        batch.draw(mainPedistalTexture, 1024 - mainPedistalTexture.getWidth(), 0);
//		batch.draw(colosseumWall,0, 600 - colosseumWall.getHeight());
//		batch.draw(playerTexture, 512- playerTexture.getWidth(), 0);
//		batch.draw(player, player.getX(), player.getY());

        batch.end();
        handleInput();

    }

    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//			float newx = playerSprite.getX() - 200 * Gdx.graphics.getDeltaTime();
//			playerSprite.setX(newx);
//			float newx = player.getX() - 200 * Gdx.graphics.getDeltaTime();
//			player.setX(newx);
//            player.move("left");
            playerAnimated.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//			float newx = playerSprite.getX() + 200 * Gdx.graphics.getDeltaTime();
//			playerSprite.setX(newx);
//			float newx = player.getX() + 200 * Gdx.graphics.getDeltaTime();
//			player.setX(newx);
//            player.move("right");
            playerAnimated.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//			float newy = playerSprite.getY() + 200 * Gdx.graphics.getDeltaTime();
//			playerSprite.setY(newy);
//			float newy = player.getY() + 200 * Gdx.graphics.getDeltaTime();
//			player.setY(newy);
//            player.move("up");
            playerAnimated.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//			float newy = playerSprite.getY() - 200 * Gdx.graphics.getDeltaTime();
//			playerSprite.setY(newy);
//			float newy = player.getY() - 200 * Gdx.graphics.getDeltaTime();
//			player.setY(newy);
//            player.move("down");
            playerAnimated.moveDown();
        }
    }
}
