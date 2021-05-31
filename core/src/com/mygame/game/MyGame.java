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
	Texture kirbyImg;
	Rectangle kirby;
	Music music;
	Sound jumpSound;
	int height = 750;
	int floorHeight = height - 495;
	int speed, weight;
	float jumpStrength;
	boolean isFacingRight;
	int nJumps = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroundImage = new Texture("background.jpeg");
		kirbyImg = new Texture("kirby.png");

		// get sounds
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

		// play background  music
		music.setLooping(true);
		music.play();


		// create rectangle to represent kirby
		kirby = new Rectangle();
		kirby.x = 50;
		kirby.y = floorHeight;
		kirby.width = 76;
		kirby.height = 71;
		isFacingRight = true;

		// set movement parameters
		speed = 5;
		weight = 1;

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(backgroundImage, 0, 0);
		if (isFacingRight) {
			batch.draw(kirbyImg, kirby.x, kirby.y, kirby.width, kirby.height);
		}
		else {
			batch.draw(kirbyImg, kirby.x+kirby.width, kirby.y, -kirby.width, kirby.height);
		}
		batch.end();

		// move kirby with key press
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			kirby.x -= speed;
			isFacingRight = false;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			kirby.x += speed;
			isFacingRight = true;
		}

		// jumping:
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && kirby.y >= floorHeight && nJumps < 7) { // Must be on the ground to jump.
			jumpSound.play();
			if (nJumps == 0) {
				jumpStrength = 18; // Will result in the player moving upwards.
			}
			else {
				jumpStrength = 11;
			}
			nJumps += 1;
			Gdx.app.log("Kirby", "is jumping. nJumps is " + nJumps);
		}
  	kirby.y += jumpStrength; // Move the player on the y-axis based on the strength of the jump.
  	jumpStrength -= weight*0.9; // Gradually decrease the strength of the jump by the player's weight.

		// make sure kirby stays within the screen bounds
		if(kirby.x < 0) kirby.x = 0;
		if(kirby.x > 1200 - kirby.width-4) kirby.x = 1200 - kirby.width-4;
		if (kirby.y <= floorHeight) {
			kirby.y = floorHeight;
			nJumps = 0;
		}
		if (kirby.y >= height - kirby.height-4) kirby.y = height - kirby.height-4;

	}

	@Override
	public void dispose () {
		batch.dispose();
		backgroundImage.dispose();
		kirbyImg.dispose();
		music.dispose();
		jumpSound.dispose();
	}
}
