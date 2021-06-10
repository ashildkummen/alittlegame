package com.mygame.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;


public class Constants {

  // --------- WORLD -------- //
  static final float WORLD_WIDTH = 640f; // 16:19 aspect ratio
  static final float WORLD_HEIGHT = 360f; // 16:19 aspect ratio

  public static final Vector2 WORLD_SIZE = new Vector2(WORLD_WIDTH, WORLD_HEIGHT);

  public static final float FLOOR_HEIGHT = WORLD_HEIGHT * 0.22f;
  public static final Vector2 CENTER_POS = new Vector2(WORLD_SIZE.x / 2, WORLD_SIZE.y / 2);

  // --------- MENU SCREEN -------- //
  public static final String MENU_TEXT = "PRESS SPACE";
  public static final float MENU_LABEL_SCALE = 2.5f;


  // --------- PLAYER -------- //
  public class Player {
    public static final int WIDTH = 45; // taken from player image size
    public static final int HEIGHT = 40; // taken from player image size
    public static final float SPEED = 5f;
    public static final float WEIGHT = 0.8f;
    public static final float FIRST_JUMP_STRENGTH = 14f;
    public static final float REST_JUMP_STRENGTH = 10f;
  }


  // --------- ASSETS -------- //
  public static final String BACKGROUND_IMG_URL = "backgrounds/background.jpeg"; // should be 16:9 aspect ratio!
  public static final String PLAYER_IMG_URL = "chars/player.png";

  public static final String JUMP_SOUND_URL = "sounds/jump.wav";
  public static final String MUSIC_URL = "music/music.mp3";




}
