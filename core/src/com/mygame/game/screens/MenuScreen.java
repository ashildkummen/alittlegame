package com.mygame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;



public class MenuScreen extends InputAdapter implements Screen {

  MyGame game;
  SpriteBatch batch;
  OrthographicCamera camera;
  FitViewport viewport;
  Music music;


  BitmapFont font;

  Texture backgroundImage;


  public MenuScreen(MyGame game) {
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

      music = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_URL));
  		music.setLooping(true);
  		music.play();

      font = new BitmapFont();
      font.getData().setScale(Constants.MENU_LABEL_SCALE); // text size
      //font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear); // smoothing

      // background
      backgroundImage = new Texture(Constants.BACKGROUND_IMG_URL);

      // To start Game
      Gdx.input.setInputProcessor(this); // each screen should have its own input processor specific to its needs

      Gdx.input.setInputProcessor(new InputAdapter() {
        @Override
        public boolean keyDown(int keyCode) {
          if (keyCode == Input.Keys.SPACE) {
              game.setScreen(new GameScreen(game));
          }
          return true;
        }
      });
  }

  @Override
  public void render(float delta) {
    // drawing background
    Gdx.gl.glClearColor(0,0,0,0); // setting outside frame color
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clearing color buffer

		batch.begin();
		batch.draw(backgroundImage, 0, 0, camera.viewportWidth, camera.viewportHeight);
    batch.setProjectionMatrix(camera.combined);

    // writing
    final GlyphLayout layout = new GlyphLayout(font, Constants.MENU_TEXT);
    font.draw(batch, Constants.MENU_TEXT, Constants.CENTER_POS.x, Constants.CENTER_POS.y + layout.height / 2, 20, Align.center, false);


    batch.end();
  }


  @Override
  public void dispose() {
    batch.dispose();
    music.dispose();
    font.dispose();
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
    Gdx.input.setInputProcessor(null);
    batch.dispose();
    font.dispose();
  }
}
