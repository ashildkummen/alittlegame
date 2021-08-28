package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Player {

  Rectangle player;
  boolean isFacingRight;
  float jumpStrength;
  int nJumps;
  Texture playerImg;
  Sound jumpSound;
  FitViewport viewport;


  public Player(FitViewport viewport) {
    this.viewport = viewport;
    init();
  }

  public void init() {
    player = new Rectangle();
    player.x = 50; // starting x position
    player.y = Constants.FLOOR_HEIGHT;
    player.width = Constants.Player.WIDTH;
    player.height = Constants.Player.HEIGHT;
    isFacingRight = true;

    playerImg = new Texture(Constants.PLAYER_IMG_URL);
    jumpSound = Gdx.audio.newSound(Gdx.files.internal(Constants.JUMP_SOUND_URL));
  }

  public void update(float delta) {
    // move player with key press
    if(Gdx.input.isKeyPressed(Keys.LEFT)) {
      player.x -= Constants.Player.SPEED;
      isFacingRight = false;
    }
    if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
      player.x += Constants.Player.SPEED;
      isFacingRight = true;
    }

    // jumping with spacebar
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

    ensureInBounds();

  }

  public void ensureInBounds() {
    // make sure player stays within the screen bounds
    if(player.x < 0) player.x = 0;
    if(player.x > Constants.WORLD_WIDTH - player.width-4) player.x = Constants.WORLD_WIDTH - player.width-4;
    if (player.y <= Constants.FLOOR_HEIGHT) {
      player.y = Constants.FLOOR_HEIGHT;
      nJumps = 0;
    }
    if (player.y >= Constants.WORLD_HEIGHT - player.height-4) player.y = Constants.WORLD_HEIGHT - player.height-4;
  }

  public void render(SpriteBatch batch) {
    if (isFacingRight) {
      batch.draw(playerImg, player.x, player.y, player.width, player.height);
    }
    else {
      batch.draw(playerImg, player.x+player.width, player.y, -player.width, player.height);
    }

  }

  public void dispose() {
    playerImg.dispose();
    jumpSound.dispose();
  }
}
