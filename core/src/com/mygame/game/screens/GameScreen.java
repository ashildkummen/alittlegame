package com.mygame.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;



public class GameScreen extends InputAdapter implements Screen {

    MyGame game;
    FitViewport viewport;
    OrthographicCamera camera;
    SpriteBatch batch;
  	Texture backgroundImage;

    Player player;

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

      player = new Player(viewport);

  	}

    @Override
    public void dispose () {
  		batch.dispose();
  		backgroundImage.dispose();
  	}

    @Override
    public void render(float delta) {

      player.update(delta);


      viewport.apply(true);
      Gdx.gl.glClearColor(0,0,0,0); // setting outside frame color
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clearing color buffer

  		batch.begin();

      // drawing background
      batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);
      batch.setProjectionMatrix(camera.combined);

      player.render(batch);

  		batch.end();

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
        player.init();
    }

    @Override
    public void hide() {
        batch.dispose();
    }

  }
