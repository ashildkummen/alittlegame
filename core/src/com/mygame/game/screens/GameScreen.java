package com.mygame.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;



public class GameScreen extends InputAdapter implements Screen {

    MyGame game;
    //SpriteBatch batch;
    FitViewport viewport;
    OrthographicCamera camera;
    SpriteBatch batch;
  	Texture backgroundImage;



  	Texture playerImg;
  	Rectangle player;
  	Sound jumpSound;
  	boolean isFacingRight;
  	float jumpStrength;
  	int nJumps;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
  		batch = new SpriteBatch();

      camera = new OrthographicCamera(Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y);
      viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
      viewport.apply();
      camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
      camera.update();

  		backgroundImage = new Texture(Constants.BACKGROUND_IMG_URL);
  		playerImg = new Texture(Constants.PLAYER_IMG_URL);

  		// Get sounds and play background  music
  		jumpSound = Gdx.audio.newSound(Gdx.files.internal(Constants.JUMP_SOUND_URL));


  		// create rectangle to represent player
  		player = new Rectangle();
  		player.x = 50; // starting x position
  		player.y = Constants.FLOOR_HEIGHT;
    	player.width = Constants.Player.WIDTH;
  		player.height = Constants.Player.HEIGHT;
  		isFacingRight = true;

  		// set movement parameters

  	}

    @Override
    public void dispose () {
  		batch.dispose();
  		backgroundImage.dispose();
  		playerImg.dispose();
  		jumpSound.dispose();
  	}

    @Override
    public void render(float delta) {
      Gdx.gl.glClearColor(0,0,0,0); // setting outside frame color
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clearing color buffer

  		batch.begin();

      // drawing background
      batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);
      batch.setProjectionMatrix(camera.combined);

  		if (isFacingRight) {
  			batch.draw(playerImg, player.x, player.y, player.width, player.height);
  		}
  		else {
  			batch.draw(playerImg, player.x+player.width, player.y, -player.width, player.height);
  		}
  		batch.end();

  		// move player with key press
  		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
  			player.x -= Constants.Player.SPEED;
  			isFacingRight = false;
  		}
  		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
  			player.x += Constants.Player.SPEED;
  			isFacingRight = true;
  		}

  		// jumping
  		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && player.y >= Constants.FLOOR_HEIGHT && nJumps < 7) { // Must be on the ground to jump.
  			jumpSound.play();
  			if (nJumps == 0) {
  				jumpStrength = Constants.Player.FIRST_JUMP_STRENGTH; // Will result in the player moving upwards.
  			}
  			else {
  				jumpStrength = Constants.Player.REST_JUMP_STRENGTH;
  			}
  			nJumps += 1;
  		}
    	player.y += jumpStrength; // Move the player on the y-axis based on the strength of the jump.
    	jumpStrength -= Constants.Player.WEIGHT; // Gradually decrease the strength of the jump by the player's WEIGHT.

  		// make sure player stays within the screen bounds
  		if(player.x < 0) player.x = 0;
  		if(player.x > Constants.WORLD_WIDTH - player.width-4) player.x = Constants.WORLD_WIDTH - player.width-4;
  		if (player.y <= Constants.FLOOR_HEIGHT) {
  			player.y = Constants.FLOOR_HEIGHT;
  			nJumps = 0;
  		}
  		if (player.y >= Constants.WORLD_HEIGHT - player.height-4) player.y = Constants.WORLD_HEIGHT - player.height-4;

  	}


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void hide() {
        batch.dispose();
    }

  }
