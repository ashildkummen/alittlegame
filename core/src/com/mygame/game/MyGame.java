package com.mygame.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;



public class MyGame extends Game {
	SpriteBatch batch;
	Texture backgroundImage;
	Texture playerImg;
	Rectangle player;
	Music music;
	Sound jumpSound;
	boolean isFacingRight;
	float jumpStrength;
	int nJumps;

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture(Constants.backgroundImageUrl);
		playerImg = new Texture(Constants.playerImgUrl);

		// Get sounds and play background  music
		jumpSound = Gdx.audio.newSound(Gdx.files.internal(Constants.jumpSoundUrl));
		music = Gdx.audio.newMusic(Gdx.files.internal(Constants.musicUrl));
		music.setLooping(true);
		music.play();

		// create rectangle to represent player
		player = new Rectangle();
		player.x = 50; // starting x position
		player.y = Constants.floorHeight;
		player.width = Constants.Player.width;
		player.height = Constants.Player.height;
		isFacingRight = true;

		// set movement parameters

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(backgroundImage, 0, 0);
		if (isFacingRight) {
			batch.draw(playerImg, player.x, player.y, player.width, player.height);
		}
		else {
			batch.draw(playerImg, player.x+player.width, player.y, -player.width, player.height);
		}
		batch.end();

		// move player with key press
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.x -= Constants.Player.speed;
			isFacingRight = false;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.x += Constants.Player.speed;
			isFacingRight = true;
		}

		// jumping
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && player.y >= Constants.floorHeight && nJumps < 7) { // Must be on the ground to jump.
			jumpSound.play();
			if (nJumps == 0) {
				jumpStrength = Constants.Player.firstJumpStrength; // Will result in the player moving upwards.
			}
			else {
				jumpStrength = Constants.Player.restJumpStrength;
			}
			nJumps += 1;
		}
  	player.y += jumpStrength; // Move the player on the y-axis based on the strength of the jump.
  	jumpStrength -= Constants.Player.weight; // Gradually decrease the strength of the jump by the player's weight.

		// make sure player stays within the screen bounds
		if(player.x < 0) player.x = 0;
		if(player.x > Constants.screenWidth - player.width-4) player.x = Constants.screenWidth - player.width-4;
		if (player.y <= Constants.floorHeight) {
			player.y = Constants.floorHeight;
			nJumps = 0;
		}
		if (player.y >= Constants.screenHeight - player.height-4) player.y = Constants.screenHeight - player.height-4;

	}

	@Override
	public void dispose () {
		batch.dispose();
		backgroundImage.dispose();
		playerImg.dispose();
		music.dispose();
		jumpSound.dispose();
	}
}
